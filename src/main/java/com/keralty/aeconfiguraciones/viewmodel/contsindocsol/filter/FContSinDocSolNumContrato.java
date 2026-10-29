package com.keralty.aeconfiguraciones.viewmodel.contsindocsol.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.ContSinDocSolConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FContSinDocSolNumContrato {

    @JsonProperty("vfnumcont")
    private String vfNumCont;

    public FContSinDocSolNumContrato() {
        // Default constructor
    }

    @JsonIgnore
    public String getVfNumCont() {
        return this.vfNumCont;
    }
    
    public void setVfNumCont(String vfNumCont) {
        this.vfNumCont = vfNumCont;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ContSinDocSolConstants.FILTER_NAME_FCONTSINDOCSOLNUMCONTRATO);
        filterRequest.addFilterVariable(ContSinDocSolConstants.VAR_NAME_FCONTSINDOCSOLNUMCONTRATO_VFNUMCONT, vfNumCont);
        return filterRequest;
    }
}
