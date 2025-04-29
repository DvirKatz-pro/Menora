package com.example.Menora.Controllers.DTO;

import com.example.Menora.Repositories.Entities.Product;

public class ProductDTO {
    String type;
    double price;
    String startDate;
    String endDate;

    public ProductDTO(Product product) {
        this.type = product.getType();
        this.price = product.getPrice();
        this.startDate = product.getStartDate();
        this.endDate = product.getEndDate();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
