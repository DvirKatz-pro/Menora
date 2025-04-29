package com.example.Menora.Repositories.Entities;


import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlRootElement(name = "event")
@XmlType(propOrder = { "id", "type", "insuredId", "products"})
public class Event {

    private String id;
    private String type;
    private String insuredId;
    private List<Product> products;

    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public String getInsuredId() {
        return insuredId;
    }

    @XmlElement
    public void setInsuredId(String insuredId) {
        this.insuredId = insuredId;
    }

    public List<Product> getProducts() {
        return products;
    }

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
