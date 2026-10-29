package com.keralty.aeconfiguraciones.viewmodel.fechainieps.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FFechaIniEPS {

    @JsonProperty("vftramite")
    private Long vfTramite;

    @JsonProperty("vftipocontrato")
    private Long vfTipoContrato;

    @JsonProperty("vffecha")
    private Date vfFecha;

    public FFechaIniEPS() {
        // Default constructor
    }

    @JsonIgnore
    public Long getVfTramite() {
        return this.vfTramite;
    }
    
    public void setVfTramite(Long vfTramite) {
        this.vfTramite = vfTramite;
    }

    @JsonIgnore
    public Long getVfTipoContrato() {
        return this.vfTipoContrato;
    }
    
    public void setVfTipoContrato(Long vfTipoContrato) {
        this.vfTipoContrato = vfTipoContrato;
    }

    @JsonIgnore
    public Date getVfFecha() {
        return this.vfFecha;
    }
    
    public void setVfFecha(Date vfFecha) {
        this.vfFecha = vfFecha;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(FechaIniEPSConstants.FILTER_NAME_FFECHAINIEPS);
        filterRequest.addFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTRAMITE, vfTramite);
        filterRequest.addFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTIPOCONTRATO, vfTipoContrato);
        filterRequest.addFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFFECHA, vfFecha);
        return filterRequest;
    }
}
