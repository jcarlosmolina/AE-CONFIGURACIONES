package com.keralty.aeconfiguraciones.viewmodel.reportesdominio.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.global.ReportesDominioConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FIParaSolicitud {

    @JsonProperty("vfsolicitud")
    private SolicitudOid vfSolicitud;

    @JsonProperty("vftipo")
    private Long vfTipo;

    public FIParaSolicitud() {
        // Default constructor
    }

    @JsonIgnore
    public SolicitudOid getVfSolicitud() {
        return this.vfSolicitud;
    }
    
    public void setVfSolicitud(SolicitudOid vfSolicitud) {
        this.vfSolicitud = vfSolicitud;
    }

    @JsonIgnore
    public Long getVfTipo() {
        return this.vfTipo;
    }
    
    public void setVfTipo(Long vfTipo) {
        this.vfTipo = vfTipo;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ReportesDominioConstants.FILTER_NAME_FIPARASOLICITUD);
        filterRequest.addFilterVariable(ReportesDominioConstants.VAR_NAME_FIPARASOLICITUD_VFSOLICITUD, vfSolicitud);
        filterRequest.addFilterVariable(ReportesDominioConstants.VAR_NAME_FIPARASOLICITUD_VFTIPO, vfTipo);
        return filterRequest;
    }
}
