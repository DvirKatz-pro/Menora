package com.example.Menora.Repositories.Entities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlRootElement(name = "root")
@XmlType(propOrder = { "requestDetails", "events" })
public class Root {
    private RequestDetails requestDetails;

    private List<Event> events;

    public RequestDetails getRequestDetails() {
        return requestDetails;
    }

    @XmlElement
    public void setRequestDetails(RequestDetails requestDetails) {
        this.requestDetails = requestDetails;
    }

    public List<Event> getEvents() {
        return events;
    }

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "event")
    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
