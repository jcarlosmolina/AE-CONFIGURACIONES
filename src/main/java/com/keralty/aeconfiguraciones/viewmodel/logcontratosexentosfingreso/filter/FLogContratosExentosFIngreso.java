package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.LogContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FLogContratosExentosFIngreso {

    @JsonProperty("vfdesde")
    private Date vfDesde;

    @JsonProperty("vfhasta")
    private Date vfHasta;

    @JsonProperty("vfnumcont")
    private String vfNumCont;

    public FLogContratosExentosFIngreso() {
        // Default constructor
    }

    @JsonIgnore
    public Date getVfDesde() {
        return this.vfDesde;
    }
    
    public void setVfDesde(Date vfDesde) {
        this.vfDesde = vfDesde;
    }

    @JsonIgnore
    public Date getVfHasta() {
        return this.vfHasta;
    }
    
    public void setVfHasta(Date vfHasta) {
        this.vfHasta = vfHasta;
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
        filterRequest.setName(LogContratosExentosFIngresoConstants.FILTER_NAME_FLOGCONTRATOSEXENTOSFINGRESO);
        filterRequest.addFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFDESDE, vfDesde);
        filterRequest.addFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFHASTA, vfHasta);
        filterRequest.addFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFNUMCONT, vfNumCont);
        return filterRequest;
    }
}
