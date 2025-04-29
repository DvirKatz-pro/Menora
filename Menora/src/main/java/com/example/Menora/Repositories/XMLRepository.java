package com.example.Menora.Repositories;

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

    public String getProductsForInsuredId(String insuredId) {
        StringBuilder result = new StringBuilder("Products For Insured: " + insuredId + "\n");
        HashMap<String,String>  companies = new HashMap<>();
        List<Event> events =  insuredIdEvent.get(insuredId);
        if (events != null) {
            for (Event e: events) {
                RequestDetails requestDetails = eventIdToRequest.get(e.getId());
                companies.putIfAbsent(requestDetails.getSourceCompany(), "");
                StringBuilder productsForCompany = new StringBuilder();
                for (Product p: e.getProducts()) {
                    productsForCompany.append("\n").append(p.toString());
                }
                String oldValue = companies.get(requestDetails.getSourceCompany());
                companies.put(requestDetails.getSourceCompany(), oldValue + productsForCompany);
            }

            for (Map.Entry<String,String> c: companies.entrySet()) {
                result.append(c.getKey()).append(":").append("\n").append(c.getValue()).append("\n");
            }
            return result.toString();
        }
        return "No Events Found For This Id";
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
