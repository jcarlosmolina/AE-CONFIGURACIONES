package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGCREARCOMBORequest {

    @JsonProperty("pt_p_atrcontratointegral")
    private String tGCREARCOMBOptpatrContratoIntegral;

    @JsonProperty("pt_p_atrcontratoodontologico")
    private String tGCREARCOMBOptpatrContratoOdontologico;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGCREARCOMBOptpatrContratoIntegral();
        checkTGCREARCOMBOptpatrContratoOdontologico();
    }

    @JsonIgnore
    public String getTGCREARCOMBOptpatrContratoIntegral() {
        return this.tGCREARCOMBOptpatrContratoIntegral;
    }
    
    public void setTGCREARCOMBOptpatrContratoIntegral(String tGCREARCOMBOptpatrContratoIntegral) {
        this.tGCREARCOMBOptpatrContratoIntegral = tGCREARCOMBOptpatrContratoIntegral;
    }

    private void checkTGCREARCOMBOptpatrContratoIntegral() throws NotNullArgumentException {
        if(this.getTGCREARCOMBOptpatrContratoIntegral() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARCOMBO, GlobalServiceConstants.GS_ARG_ID_TGCREARCOMBO_TGCREARCOMBOPTPATRCONTRATOINTEGRAL, GlobalServiceConstants.G_SERV_ALIAS_TGCREARCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARCOMBO_TGCREARCOMBOPTPATRCONTRATOINTEGRAL);
        }
    }

    @JsonIgnore
    public String getTGCREARCOMBOptpatrContratoOdontologico() {
        return this.tGCREARCOMBOptpatrContratoOdontologico;
    }
    
    public void setTGCREARCOMBOptpatrContratoOdontologico(String tGCREARCOMBOptpatrContratoOdontologico) {
        this.tGCREARCOMBOptpatrContratoOdontologico = tGCREARCOMBOptpatrContratoOdontologico;
    }

    private void checkTGCREARCOMBOptpatrContratoOdontologico() throws NotNullArgumentException {
        if(this.getTGCREARCOMBOptpatrContratoOdontologico() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARCOMBO, GlobalServiceConstants.GS_ARG_ID_TGCREARCOMBO_TGCREARCOMBOPTPATRCONTRATOODONTOLOGICO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARCOMBO_TGCREARCOMBOPTPATRCONTRATOODONTOLOGICO);
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
