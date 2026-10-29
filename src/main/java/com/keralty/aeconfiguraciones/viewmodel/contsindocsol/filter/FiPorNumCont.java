package com.keralty.aeconfiguraciones.viewmodel.contsindocsol.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.ContSinDocSolConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FiPorNumCont {

    @JsonProperty("vfnumcont")
    private String vfNumCont;

    public FiPorNumCont() {
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
        filterRequest.setName(ContSinDocSolConstants.FILTER_NAME_FIPORNUMCONT);
        filterRequest.addFilterVariable(ContSinDocSolConstants.VAR_NAME_FIPORNUMCONT_VFNUMCONT, vfNumCont);
        return filterRequest;
    }
}
