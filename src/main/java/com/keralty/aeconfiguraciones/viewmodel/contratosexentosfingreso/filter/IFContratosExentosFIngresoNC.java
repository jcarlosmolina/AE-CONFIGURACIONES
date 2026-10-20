package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.ContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFContratosExentosFIngresoNC {

    @JsonProperty("vfnum")
    private String vfNum;

    public IFContratosExentosFIngresoNC() {
        // Default constructor
    }

    @JsonIgnore
    public String getVfNum() {
        return this.vfNum;
    }
    
    public void setVfNum(String vfNum) {
        this.vfNum = vfNum;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ContratosExentosFIngresoConstants.FILTER_NAME_IFCONTRATOSEXENTOSFINGRESONC);
        filterRequest.addFilterVariable(ContratosExentosFIngresoConstants.VAR_NAME_IFCONTRATOSEXENTOSFINGRESONC_VFNUM, vfNum);
        return filterRequest;
    }
}
