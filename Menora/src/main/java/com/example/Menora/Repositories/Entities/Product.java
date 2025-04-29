package com.example.Menora.Repositories.Entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlRootElement(name = "product")
@XmlType(propOrder = { "type", "price", "startDate", "endDate"})
public class Product {

    private String type;
    private Double price;
    private String startDate;
    private String endDate;
    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    @XmlElement
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @XmlElement
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Type: " + type + "\n" +
               "Price: " + price + "\n" +
               "Start date: " + startDate + "\n" +
               "End date: " + startDate + "\n";
    }
}
