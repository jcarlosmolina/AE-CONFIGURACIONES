package com.keralty.aeconfiguraciones.viewmodel.parentescodom.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFParentescoDom {

    @JsonProperty("vo_parentesco")
    private ParentescoOid voParentesco;

    @JsonProperty("vo_dominio")
    private DominioOid voDominio;

    public IFParentescoDom() {
        // Default constructor
    }

    @JsonIgnore
    public ParentescoOid getVoParentesco() {
        return this.voParentesco;
    }
    
    public void setVoParentesco(ParentescoOid voParentesco) {
        this.voParentesco = voParentesco;
    }

    @JsonIgnore
    public DominioOid getVoDominio() {
        return this.voDominio;
    }
    
    public void setVoDominio(DominioOid voDominio) {
        this.voDominio = voDominio;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ParentescoDomConstants.FILTER_NAME_IFPARENTESCODOM);
        filterRequest.addFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VOPARENTESCO, voParentesco);
        filterRequest.addFilterVariable(ParentescoDomConstants.VAR_NAME_IFPARENTESCODOM_VODOMINIO, voDominio);
        return filterRequest;
    }
}
