package com.example.Menora.Repositories;

import com.example.Menora.Controllers.DTO.CompanyDTO;
import com.example.Menora.Controllers.DTO.InsuredDataDetailsDTO;
import com.example.Menora.Controllers.DTO.ProductDTO;
import com.example.Menora.Repositories.Entities.Event;
import com.example.Menora.Repositories.Entities.Product;
import com.example.Menora.Repositories.Entities.RequestDetails;
import com.example.Menora.Repositories.Entities.Root;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class XMLRepository {
    private Map<String, RequestDetails> requestDetails = new ConcurrentHashMap<>();
    private Map<String, Event> events = new ConcurrentHashMap<>();
    private Map<String, List<Event>> insuredIdEvent = new ConcurrentHashMap<>();

    private Map<String, RequestDetails> eventIdToRequest = new ConcurrentHashMap<>();

    public void addXmlToRepository(Root root) {
        RequestDetails requestDetail = root.getRequestDetails();
        requestDetails.putIfAbsent(requestDetail.getId(), requestDetail);

        for (Event e: root.getEvents()) {
            events.putIfAbsent(e.getId(),e);
            if (!insuredIdEvent.containsKey(e.getInsuredId())) {
                List<Event> temp = new ArrayList<>();
                temp.add(e);
                insuredIdEvent.put(e.getInsuredId(),temp);
            }
            else {
                insuredIdEvent.get(e.getInsuredId()).add(e);
            }
            eventIdToRequest.putIfAbsent(e.getId(),requestDetail);
        }

    }

    public InsuredDataDetailsDTO getProductsForInsuredId(String insuredId) {
        List<Event> events = insuredIdEvent.get(insuredId);
        if (events == null) {
            return new InsuredDataDetailsDTO(insuredId, new ArrayList<>());
        }

        Map<String, List<ProductDTO>> companyProductsMap = new HashMap<>();

        for (Event e : events) {
            RequestDetails requestDetails = eventIdToRequest.get(e.getId());
            String company = requestDetails.getSourceCompany();

            List<ProductDTO> productDTOs = companyProductsMap.computeIfAbsent(company, k -> new ArrayList<>());

            for (Product p : e.getProducts()) {
                productDTOs.add(new ProductDTO(p));
            }
        }

        List<CompanyDTO> companies = new ArrayList<>();
        for (Map.Entry<String, List<ProductDTO>> entry : companyProductsMap.entrySet()) {
            companies.add(new CompanyDTO(entry.getKey(), entry.getValue()));
        }

        return new InsuredDataDetailsDTO(insuredId, companies);
    }

    public Map<String, RequestDetails> getRequestDetails() {
        return requestDetails;
    }

    public Map<String, Event> getEvents() {
        return events;
    }

    public Map<String, List<Event>> getInsuredIdEvent() {
        return insuredIdEvent;
    }

    public Map<String, RequestDetails> getEventIdToRequest() {
        return eventIdToRequest;
    }
}
