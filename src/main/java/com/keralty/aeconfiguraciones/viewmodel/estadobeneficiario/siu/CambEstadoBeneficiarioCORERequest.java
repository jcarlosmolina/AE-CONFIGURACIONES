package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;

public class CambEstadoBeneficiarioCORERequest {

    @JsonProperty("p_thisestadobeneficiario_oid")
    private EstadoBeneficiarioOid estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance;

    @JsonProperty("p_evcestadobeneficiarioae_oid")
    private EstadoBeneficiarioAEOid estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE;
    
    @JsonIgnore
    private EstadoBeneficiarioAE estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario();
        checkEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE();
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario() {
        return this.estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario(EstadoBeneficiarioOid estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario) {
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario = estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance() {
        return this.estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario) {
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance = estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario;
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario = estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario != null ? estadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario.getOid() : null;
    }

    private void checkEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOCAMBESTADOBENEFICIARIOCOREPTHISESTADOBENEFICIARIO, EstadoBeneficiarioConstants.SERV_ALIAS_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOCAMBESTADOBENEFICIARIOCOREPTHISESTADOBENEFICIARIO);
        }
    }

    @JsonIgnore
    public EstadoBeneficiarioAEOid getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE() {
        return this.estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE;
    }
    
    public void setEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE(EstadoBeneficiarioAEOid estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE) {
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE = estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE;
    }

    @JsonIgnore
    public EstadoBeneficiarioAE getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance() {
        return this.estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance;
    }
    
    public void setEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance(EstadoBeneficiarioAE estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE) {
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance = estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE;
        this.estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE = estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE != null ? estadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE.getOid() : null;
    }

    private void checkEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance() == null || this.getEstadoBeneficiarioCambEstadoBeneficiarioCOREpevcEstadoBeneficiarioAEInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOCAMBESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIOAE, EstadoBeneficiarioConstants.SERV_ALIAS_CAMBESTADOBENEFICIARIOCORE, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_CAMBESTADOBENEFICIARIOCORE_ESTADOBENEFICIARIOCAMBESTADOBENEFICIARIOCOREPEVCESTADOBENEFICIARIOAE);
        }
    }
}
