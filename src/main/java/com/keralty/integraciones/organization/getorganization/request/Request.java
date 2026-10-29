package com.keralty.integraciones.organization.getorganization.request;

import com.fasterxml.jackson.annotation.*;

public class Request {
    private String method;
    private String url;

    @JsonProperty("method")
    public String getMethod() { return method; }
    @JsonProperty("method")
    public void setMethod(String value) { this.method = value; }

    @JsonProperty("url")
    public String getURL() { return url; }
    @JsonProperty("url")
    public void setURL(String value) { this.url = value; }
}
