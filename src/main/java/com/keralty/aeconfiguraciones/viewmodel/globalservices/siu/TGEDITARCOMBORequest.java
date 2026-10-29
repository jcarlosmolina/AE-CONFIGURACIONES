package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;

public class TGEDITARCOMBORequest {

    @JsonProperty("pt_p_thiscontratocombo_oid")
    private ContratoComboOid tGEDITARCOMBOptpthisContratoCombo;
    
    @JsonIgnore
    private ContratoCombo tGEDITARCOMBOptpthisContratoComboInstance;

    @JsonProperty("pt_p_contratointegral")
    private String tGEDITARCOMBOptpContratoIntegral;

    @JsonProperty("pt_p_contratoodontologico")
    private String tGEDITARCOMBOptpContratoOdontologico;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGEDITARCOMBOptpthisContratoCombo();
        checkTGEDITARCOMBOptpContratoIntegral();
        checkTGEDITARCOMBOptpContratoOdontologico();
    }

    @JsonIgnore
    public ContratoComboOid getTGEDITARCOMBOptpthisContratoCombo() {
        return this.tGEDITARCOMBOptpthisContratoCombo;
    }
    
    public void setTGEDITARCOMBOptpthisContratoCombo(ContratoComboOid tGEDITARCOMBOptpthisContratoCombo) {
        this.tGEDITARCOMBOptpthisContratoCombo = tGEDITARCOMBOptpthisContratoCombo;
    }

    @JsonIgnore
    public ContratoCombo getTGEDITARCOMBOptpthisContratoComboInstance() {
        return this.tGEDITARCOMBOptpthisContratoComboInstance;
    }
    
    public void setTGEDITARCOMBOptpthisContratoComboInstance(ContratoCombo tGEDITARCOMBOptpthisContratoCombo) {
        this.tGEDITARCOMBOptpthisContratoComboInstance = tGEDITARCOMBOptpthisContratoCombo;
        this.tGEDITARCOMBOptpthisContratoCombo = tGEDITARCOMBOptpthisContratoCombo != null ? tGEDITARCOMBOptpthisContratoCombo.getOid() : null;
    }

    private void checkTGEDITARCOMBOptpthisContratoCombo() throws NotNullArgumentException {
        if(this.getTGEDITARCOMBOptpthisContratoComboInstance() == null || this.getTGEDITARCOMBOptpthisContratoComboInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ID_TGEDITARCOMBO_TGEDITARCOMBOPTPTHISCONTRATOCOMBO, GlobalServiceConstants.G_SERV_ALIAS_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITARCOMBO_TGEDITARCOMBOPTPTHISCONTRATOCOMBO);
        }
    }

    @JsonIgnore
    public String getTGEDITARCOMBOptpContratoIntegral() {
        return this.tGEDITARCOMBOptpContratoIntegral;
    }
    
    public void setTGEDITARCOMBOptpContratoIntegral(String tGEDITARCOMBOptpContratoIntegral) {
        this.tGEDITARCOMBOptpContratoIntegral = tGEDITARCOMBOptpContratoIntegral;
    }

    private void checkTGEDITARCOMBOptpContratoIntegral() throws NotNullArgumentException {
        if(this.getTGEDITARCOMBOptpContratoIntegral() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ID_TGEDITARCOMBO_TGEDITARCOMBOPTPCONTRATOINTEGRAL, GlobalServiceConstants.G_SERV_ALIAS_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITARCOMBO_TGEDITARCOMBOPTPCONTRATOINTEGRAL);
        }
    }

    @JsonIgnore
    public String getTGEDITARCOMBOptpContratoOdontologico() {
        return this.tGEDITARCOMBOptpContratoOdontologico;
    }
    
    public void setTGEDITARCOMBOptpContratoOdontologico(String tGEDITARCOMBOptpContratoOdontologico) {
        this.tGEDITARCOMBOptpContratoOdontologico = tGEDITARCOMBOptpContratoOdontologico;
    }

    private void checkTGEDITARCOMBOptpContratoOdontologico() throws NotNullArgumentException {
        if(this.getTGEDITARCOMBOptpContratoOdontologico() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ID_TGEDITARCOMBO_TGEDITARCOMBOPTPCONTRATOODONTOLOGICO, GlobalServiceConstants.G_SERV_ALIAS_TGEDITARCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TGEDITARCOMBO_TGEDITARCOMBOPTPCONTRATOODONTOLOGICO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
