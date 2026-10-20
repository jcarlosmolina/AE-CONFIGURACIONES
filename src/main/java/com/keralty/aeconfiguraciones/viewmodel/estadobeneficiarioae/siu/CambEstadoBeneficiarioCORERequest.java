package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;

public class CambEstadoBeneficiarioCORERequest {

    @JsonProperty("p_thisestadobeneficiarioae_oid")
    private EstadoBeneficiarioAEOid estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE;
    
    @JsonIgnore
    private EstadoBeneficiarioAE estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance;

    @JsonProperty("p_evcestadobeneficiario_oid")
    private EstadoBeneficiarioOid estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE();
        checkEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario();
    }

    @JsonIgnore
    public EstadoBeneficiarioAEOid getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE() {
        return this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE;
    }
    
    public void setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE(EstadoBeneficiarioAEOid estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE = estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE;
    }

    @JsonIgnore
    public EstadoBeneficiarioAE getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance() {
        return this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance;
    }
    
    public void setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance(EstadoBeneficiarioAE estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance = estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE;
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE = estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE != null ? estadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE.getOid() : null;
    }

    private void checkEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance() == null || this.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpthisEstadoBeneficiarioAEInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOAECAMBESTADOBENEFICIARIOCOREPTHISESTADOBENEFICIARIOAE, EstadoBeneficiarioAEConstants.SERV_ALIAS_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOAECAMBESTADOBENEFICIARIOCOREPTHISESTADOBENEFICIARIOAE);
        }
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario() {
        return this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario(EstadoBeneficiarioOid estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario) {
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario = estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance() {
        return this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario) {
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance = estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario;
        this.estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario = estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario != null ? estadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario.getOid() : null;
    }

    private void checkEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiarioAECambEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOAECAMBESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIO, EstadoBeneficiarioAEConstants.SERV_ALIAS_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOAECAMBESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIO);
        }
    }
}
