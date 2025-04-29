package com.example.Menora.Controllers.DTO;

import java.util.List;


public class CompanyDTO {
    String name;
    List<ProductDTO> products;

    public CompanyDTO(String name, List<ProductDTO> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
