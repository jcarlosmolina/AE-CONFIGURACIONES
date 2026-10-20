package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;

public class TMODIFICARRequest {

    @JsonProperty("p_thisestadobeneficiarioae_oid")
    private EstadoBeneficiarioAEOid estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE;
    
    @JsonIgnore
    private EstadoBeneficiarioAE estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance;

    @JsonProperty("pt_p_descripcion")
    private String estadoBeneficiarioAETMODIFICARptpDescripcion;

    @JsonProperty("pt_p_evcestadobeneficiario_json")
    private String estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE();
        checkEstadoBeneficiarioAETMODIFICARptpDescripcion();
        checkEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario();
    }

    @JsonIgnore
    public EstadoBeneficiarioAEOid getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE() {
        return this.estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE;
    }
    
    public void setEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE(EstadoBeneficiarioAEOid estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE = estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE;
    }

    @JsonIgnore
    public EstadoBeneficiarioAE getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance() {
        return this.estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance;
    }
    
    public void setEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance(EstadoBeneficiarioAE estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance = estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE;
        this.estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE = estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE != null ? estadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE.getOid() : null;
    }

    private void checkEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance() == null || this.getEstadoBeneficiarioAETMODIFICARpthisEstadoBeneficiarioAEInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTHISESTADOBENEFICIARIOAE, EstadoBeneficiarioAEConstants.SERV_ALIAS_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTHISESTADOBENEFICIARIOAE);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiarioAETMODIFICARptpDescripcion() {
        return this.estadoBeneficiarioAETMODIFICARptpDescripcion;
    }
    
    public void setEstadoBeneficiarioAETMODIFICARptpDescripcion(String estadoBeneficiarioAETMODIFICARptpDescripcion) {
        this.estadoBeneficiarioAETMODIFICARptpDescripcion = estadoBeneficiarioAETMODIFICARptpDescripcion;
    }

    private void checkEstadoBeneficiarioAETMODIFICARptpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAETMODIFICARptpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTPDESCRIPCION, EstadoBeneficiarioAEConstants.SERV_ALIAS_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario() {
        return this.estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario(String estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario) {
        this.estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario = estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance() {
        return this.estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario) {
        this.estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance = estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario;
        this.estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario = estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario != null ? estadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario.getOid().toJson() : "";
    }

    private void checkEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiarioAETMODIFICARptpevcEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTPEVCESTADOBENEFICIARIO, EstadoBeneficiarioAEConstants.SERV_ALIAS_TMODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_TMODIFICAR_ESTADOBENEFICIARIOAETMODIFICARPTPEVCESTADOBENEFICIARIO);
        }
    }
}
