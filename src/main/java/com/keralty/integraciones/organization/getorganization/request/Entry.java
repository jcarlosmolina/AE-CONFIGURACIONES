package com.keralty.integraciones.organization.getorganization.request;

import com.fasterxml.jackson.annotation.*;

public class Entry {
    private Request request;

    @JsonProperty("request")
    public Request getRequest() { return request; }
    @JsonProperty("request")
    public void setRequest(Request value) { this.request = value; }
}
