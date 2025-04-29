package com.example.Menora.Repositories.Entities;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.Date;
import java.util.List;

@XmlRootElement(name = "requestDetails")
@XmlType(propOrder = { "id", "acceptDate", "sourceCompany"})
public class RequestDetails {

    private String id;
    private String acceptDate;
    private String sourceCompany;
    public String getId() {
        return id;
    }

    @XmlElement
    public void setId(String id) {
        this.id = id;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    @XmlElement
    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getSourceCompany() {
        return sourceCompany;
    }

    @XmlElement
    public void setSourceCompany(String sourceCompany) {
        this.sourceCompany = sourceCompany;
    }

}
