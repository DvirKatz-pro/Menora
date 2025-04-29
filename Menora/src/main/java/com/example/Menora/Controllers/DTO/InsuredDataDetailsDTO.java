package com.example.Menora.Controllers.DTO;

import java.util.List;

public class InsuredDataDetailsDTO {
    String insuredId;
    List<CompanyDTO> companies;

    public InsuredDataDetailsDTO(String insuredId, List<CompanyDTO> companies) {
        this.insuredId = insuredId;
        this.companies = companies;
    }

    public String getInsuredId() {
        return insuredId;
    }

    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public List<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyDTO> companies) {
        this.companies = companies;
    }
}
