package com.keralty.aeconfiguraciones.viewmodel.paramconfig.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.ParamConfigConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFParamConfigpPorCodigo {

    @JsonProperty("v_codigo")
    private String vCodigo;

    public IFParamConfigpPorCodigo() {
        // Default constructor
    }

    @JsonIgnore
    public String getVCodigo() {
        return this.vCodigo;
    }
    
    public void setVCodigo(String vCodigo) {
        this.vCodigo = vCodigo;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ParamConfigConstants.FILTER_NAME_IFPARAMCONFIGPPORCODIGO);
        filterRequest.addFilterVariable(ParamConfigConstants.VAR_NAME_IFPARAMCONFIGPPORCODIGO_VCODIGO, vCodigo);
        return filterRequest;
    }
}
