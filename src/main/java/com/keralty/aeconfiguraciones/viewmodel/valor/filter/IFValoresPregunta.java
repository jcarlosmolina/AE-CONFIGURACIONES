package com.keralty.aeconfiguraciones.viewmodel.valor.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFValoresPregunta {

    @JsonProperty("vo_pregunta")
    private PreguntaOid voPregunta;

    public IFValoresPregunta() {
        // Default constructor
    }

    @JsonIgnore
    public PreguntaOid getVoPregunta() {
        return this.voPregunta;
    }
    
    public void setVoPregunta(PreguntaOid voPregunta) {
        this.voPregunta = voPregunta;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ValorConstants.FILTER_NAME_IFVALORESPREGUNTA);
        filterRequest.addFilterVariable(ValorConstants.VAR_NAME_IFVALORESPREGUNTA_VOPREGUNTA, voPregunta);
        return filterRequest;
    }
}
