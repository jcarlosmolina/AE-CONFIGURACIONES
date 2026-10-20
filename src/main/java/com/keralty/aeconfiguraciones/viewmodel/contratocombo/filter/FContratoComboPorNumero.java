package com.keralty.aeconfiguraciones.viewmodel.contratocombo.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.ContratoComboConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FContratoComboPorNumero {

    @JsonProperty("vfnum")
    private String vfNum;

    public FContratoComboPorNumero() {
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
        filterRequest.setName(ContratoComboConstants.FILTER_NAME_FCONTRATOCOMBOPORNUMERO);
        filterRequest.addFilterVariable(ContratoComboConstants.VAR_NAME_FCONTRATOCOMBOPORNUMERO_VFNUM, vfNum);
        return filterRequest;
    }
}
