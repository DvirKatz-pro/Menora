package com.example.Menora.Controllers;

import com.example.Menora.Controllers.DTO.InsuredDataDetailsDTO;
import com.example.Menora.Repositories.XMLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/Insured")
public class InsuredDataController {
    XMLRepository xmlRepository;
    @Autowired
    public InsuredDataController(XMLRepository xmlRepository) {
        this.xmlRepository = xmlRepository;
    }

    @GetMapping("/{insuredId}")
    public ResponseEntity<InsuredDataDetailsDTO> readCampaignByName(@PathVariable String insuredId) {
        InsuredDataDetailsDTO result = xmlRepository.getProductsForInsuredId(insuredId);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }
}
