package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FPriorizacionSolicitudes {

    @JsonProperty("vfestadosol")
    private Long vfEstadoSol;

    @JsonProperty("vfestadodoc")
    private Long vfEstadoDoc;

    @JsonProperty("vfpriordad")
    private Long vfPriordad;

    public FPriorizacionSolicitudes() {
        // Default constructor
    }

    @JsonIgnore
    public Long getVfEstadoSol() {
        return this.vfEstadoSol;
    }
    
    public void setVfEstadoSol(Long vfEstadoSol) {
        this.vfEstadoSol = vfEstadoSol;
    }

    @JsonIgnore
    public Long getVfEstadoDoc() {
        return this.vfEstadoDoc;
    }
    
    public void setVfEstadoDoc(Long vfEstadoDoc) {
        this.vfEstadoDoc = vfEstadoDoc;
    }

    @JsonIgnore
    public Long getVfPriordad() {
        return this.vfPriordad;
    }
    
    public void setVfPriordad(Long vfPriordad) {
        this.vfPriordad = vfPriordad;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(PriorizacionSolicitudesConstants.FILTER_NAME_FPRIORIZACIONSOLICITUDES);
        filterRequest.addFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADOSOL, vfEstadoSol);
        filterRequest.addFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFESTADODOC, vfEstadoDoc);
        filterRequest.addFilterVariable(PriorizacionSolicitudesConstants.VAR_NAME_FPRIORIZACIONSOLICITUDES_VFPRIORDAD, vfPriordad);
        return filterRequest;
    }
}
