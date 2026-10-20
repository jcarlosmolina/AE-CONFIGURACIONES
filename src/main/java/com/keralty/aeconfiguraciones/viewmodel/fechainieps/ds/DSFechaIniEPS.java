package com.keralty.aeconfiguraciones.viewmodel.fechainieps.ds;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
public class DSFechaIniEPS {

    @JsonProperty("tramite")
    private Long dSFechaIniEPSTramite;

    @JsonProperty("tipocontrato")
    private Long dSFechaIniEPSTipoContrato;

    @JsonProperty("desde")
    private Date dSFechaIniEPSDesde;

    @JsonProperty("hasta")
    private Date dSFechaIniEPSHasta;

    @JsonProperty("fechainicioaplicar")
    private Date dSFechaIniEPSFechaInicioAplicar;

    public DSFechaIniEPS() {
    
    }

    public DSFechaIniEPS(FechaIniEPS instance) {
        if (instance != null) {
            this.dSFechaIniEPSTramite = instance.getFechaIniEPSTramite();
            this.dSFechaIniEPSTipoContrato = instance.getFechaIniEPSTipoContrato();
            this.dSFechaIniEPSDesde = instance.getFechaIniEPSDesde();
            this.dSFechaIniEPSHasta = instance.getFechaIniEPSHasta();
            this.dSFechaIniEPSFechaInicioAplicar = instance.getFechaIniEPSFechaInicioAplicar();
        }
    }

    @JsonIgnore
    public Long getDSFechaIniEPSTramite() {
        return this.dSFechaIniEPSTramite;
    }
    
    public void setDSFechaIniEPSTramite(Long dSFechaIniEPSTramite) {
        this.dSFechaIniEPSTramite = dSFechaIniEPSTramite;
    }

    @JsonIgnore
    public Long getDSFechaIniEPSTipoContrato() {
        return this.dSFechaIniEPSTipoContrato;
    }
    
    public void setDSFechaIniEPSTipoContrato(Long dSFechaIniEPSTipoContrato) {
        this.dSFechaIniEPSTipoContrato = dSFechaIniEPSTipoContrato;
    }

    @JsonIgnore
    public Date getDSFechaIniEPSDesde() {
        return this.dSFechaIniEPSDesde;
    }
    
    public void setDSFechaIniEPSDesde(Date dSFechaIniEPSDesde) {
        this.dSFechaIniEPSDesde = dSFechaIniEPSDesde;
    }

    @JsonIgnore
    public Date getDSFechaIniEPSHasta() {
        return this.dSFechaIniEPSHasta;
    }
    
    public void setDSFechaIniEPSHasta(Date dSFechaIniEPSHasta) {
        this.dSFechaIniEPSHasta = dSFechaIniEPSHasta;
    }

    @JsonIgnore
    public Date getDSFechaIniEPSFechaInicioAplicar() {
        return this.dSFechaIniEPSFechaInicioAplicar;
    }
    
    public void setDSFechaIniEPSFechaInicioAplicar(Date dSFechaIniEPSFechaInicioAplicar) {
        this.dSFechaIniEPSFechaInicioAplicar = dSFechaIniEPSFechaInicioAplicar;
    }
}
