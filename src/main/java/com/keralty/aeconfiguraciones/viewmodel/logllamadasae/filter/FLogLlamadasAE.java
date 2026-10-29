package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.filter;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.LogLlamadasAEConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FLogLlamadasAE {

    @JsonProperty("vftiposervicio")
    private String vfTipoServicio;

    @JsonProperty("vfservicio")
    private String vfServicio;

    @JsonProperty("vfcapa")
    private String vfCapa;

    @JsonProperty("vfdesde")
    private Timestamp vfDesde;

    @JsonProperty("vfhasta")
    private Timestamp vfHasta;

    @JsonProperty("vfnumsolicitud")
    private String vfNumSolicitud;

    public FLogLlamadasAE() {
        // Default constructor
    }

    @JsonIgnore
    public String getVfTipoServicio() {
        return this.vfTipoServicio;
    }
    
    public void setVfTipoServicio(String vfTipoServicio) {
        this.vfTipoServicio = vfTipoServicio;
    }

    @JsonIgnore
    public String getVfServicio() {
        return this.vfServicio;
    }
    
    public void setVfServicio(String vfServicio) {
        this.vfServicio = vfServicio;
    }

    @JsonIgnore
    public String getVfCapa() {
        return this.vfCapa;
    }
    
    public void setVfCapa(String vfCapa) {
        this.vfCapa = vfCapa;
    }

    @JsonIgnore
    public Timestamp getVfDesde() {
        return this.vfDesde;
    }
    
    public void setVfDesde(Timestamp vfDesde) {
        this.vfDesde = vfDesde;
    }

    @JsonIgnore
    public Timestamp getVfHasta() {
        return this.vfHasta;
    }
    
    public void setVfHasta(Timestamp vfHasta) {
        this.vfHasta = vfHasta;
    }

    @JsonIgnore
    public String getVfNumSolicitud() {
        return this.vfNumSolicitud;
    }
    
    public void setVfNumSolicitud(String vfNumSolicitud) {
        this.vfNumSolicitud = vfNumSolicitud;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(LogLlamadasAEConstants.FILTER_NAME_FLOGLLAMADASAE);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFTIPOSERVICIO, vfTipoServicio);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFSERVICIO, vfServicio);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFCAPA, vfCapa);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFDESDE, vfDesde);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFHASTA, vfHasta);
        filterRequest.addFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFNUMSOLICITUD, vfNumSolicitud);
        return filterRequest;
    }
}
