package com.keralty.aeconfiguraciones.viewmodel.causal.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Causal;
public class DSCausal {

    @JsonProperty("aliascausal")
    private String dSCausalAliasCausal;

    @JsonProperty("nombre")
    private String dSCausalNombre;

    @JsonProperty("descripcion")
    private String dSCausalDescripcion;

    @JsonProperty("codigolegal")
    private String dSCausalCodigoLegal;

    @JsonProperty("reqdocpersona")
    private Boolean dSCausalReqDocPersona;

    @JsonProperty("reqdocsolicitud")
    private Boolean dSCausalReqDocSolicitud;

    public DSCausal() {
    
    }

    public DSCausal(Causal instance) {
        if (instance != null) {
            this.dSCausalAliasCausal = instance.getCausalAliasCausal();
            this.dSCausalNombre = instance.getCausalNombre();
            this.dSCausalDescripcion = instance.getCausalDescripcion();
            this.dSCausalCodigoLegal = instance.getCausalCodigoLegal();
            this.dSCausalReqDocPersona = instance.getCausalReqDocPersona();
            this.dSCausalReqDocSolicitud = instance.getCausalReqDocSolicitud();
        }
    }

    @JsonIgnore
    public String getDSCausalAliasCausal() {
        return this.dSCausalAliasCausal;
    }
    
    public void setDSCausalAliasCausal(String dSCausalAliasCausal) {
        this.dSCausalAliasCausal = dSCausalAliasCausal;
    }

    @JsonIgnore
    public String getDSCausalNombre() {
        return this.dSCausalNombre;
    }
    
    public void setDSCausalNombre(String dSCausalNombre) {
        this.dSCausalNombre = dSCausalNombre;
    }

    @JsonIgnore
    public String getDSCausalDescripcion() {
        return this.dSCausalDescripcion;
    }
    
    public void setDSCausalDescripcion(String dSCausalDescripcion) {
        this.dSCausalDescripcion = dSCausalDescripcion;
    }

    @JsonIgnore
    public String getDSCausalCodigoLegal() {
        return this.dSCausalCodigoLegal;
    }
    
    public void setDSCausalCodigoLegal(String dSCausalCodigoLegal) {
        this.dSCausalCodigoLegal = dSCausalCodigoLegal;
    }

    @JsonIgnore
    public Boolean getDSCausalReqDocPersona() {
        return this.dSCausalReqDocPersona;
    }
    
    public void setDSCausalReqDocPersona(Boolean dSCausalReqDocPersona) {
        this.dSCausalReqDocPersona = dSCausalReqDocPersona;
    }

    @JsonIgnore
    public Boolean getDSCausalReqDocSolicitud() {
        return this.dSCausalReqDocSolicitud;
    }
    
    public void setDSCausalReqDocSolicitud(Boolean dSCausalReqDocSolicitud) {
        this.dSCausalReqDocSolicitud = dSCausalReqDocSolicitud;
    }
}
