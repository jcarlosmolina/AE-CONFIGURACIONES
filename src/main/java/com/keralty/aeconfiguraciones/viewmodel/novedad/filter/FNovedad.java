package com.keralty.aeconfiguraciones.viewmodel.novedad.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.TipoNovedadOid;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FNovedad {

    @JsonProperty("vftipo")
    private TipoNovedadOid vfTipo;

    @JsonProperty("vfnombre")
    private String vfNombre;

    public FNovedad() {
        // Default constructor
    }

    @JsonIgnore
    public TipoNovedadOid getVfTipo() {
        return this.vfTipo;
    }
    
    public void setVfTipo(TipoNovedadOid vfTipo) {
        this.vfTipo = vfTipo;
    }

    @JsonIgnore
    public String getVfNombre() {
        return this.vfNombre;
    }
    
    public void setVfNombre(String vfNombre) {
        this.vfNombre = vfNombre;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(NovedadConstants.FILTER_NAME_FNOVEDAD);
        filterRequest.addFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFTIPO, vfTipo);
        filterRequest.addFilterVariable(NovedadConstants.VAR_NAME_FNOVEDAD_VFNOMBRE, vfNombre);
        return filterRequest;
    }
}
