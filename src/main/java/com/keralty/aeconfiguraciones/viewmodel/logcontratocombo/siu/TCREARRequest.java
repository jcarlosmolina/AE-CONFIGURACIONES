package com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;

public class TCREARRequest {

    @JsonProperty("pt_p_atrcontratointegral")
    private String logContratoComboTCREARptpatrContratoIntegral;

    @JsonProperty("pt_p_atrcontratoodontologico")
    private String logContratoComboTCREARptpatrContratoOdontologico;

    @JsonProperty("pt_p_atraccion")
    private String logContratoComboTCREARptpatrAccion;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratoComboTCREARptpatrAccion();
    }

    @JsonIgnore
    public String getLogContratoComboTCREARptpatrContratoIntegral() {
        return this.logContratoComboTCREARptpatrContratoIntegral;
    }
    
    public void setLogContratoComboTCREARptpatrContratoIntegral(String logContratoComboTCREARptpatrContratoIntegral) {
        this.logContratoComboTCREARptpatrContratoIntegral = logContratoComboTCREARptpatrContratoIntegral;
    }

    @JsonIgnore
    public String getLogContratoComboTCREARptpatrContratoOdontologico() {
        return this.logContratoComboTCREARptpatrContratoOdontologico;
    }
    
    public void setLogContratoComboTCREARptpatrContratoOdontologico(String logContratoComboTCREARptpatrContratoOdontologico) {
        this.logContratoComboTCREARptpatrContratoOdontologico = logContratoComboTCREARptpatrContratoOdontologico;
    }

    @JsonIgnore
    public String getLogContratoComboTCREARptpatrAccion() {
        return this.logContratoComboTCREARptpatrAccion;
    }
    
    public void setLogContratoComboTCREARptpatrAccion(String logContratoComboTCREARptpatrAccion) {
        this.logContratoComboTCREARptpatrAccion = logContratoComboTCREARptpatrAccion;
    }

    private void checkLogContratoComboTCREARptpatrAccion() throws NotNullArgumentException {
        if(this.getLogContratoComboTCREARptpatrAccion() == null) {
            throw new NotNullArgumentException(LogContratoComboConstants.SERV_ID_TCREAR, LogContratoComboConstants.CLASS_ID, LogContratoComboConstants.ARG_ID_TCREAR_LOGCONTRATOCOMBOTCREARPTPATRACCION, LogContratoComboConstants.SERV_ALIAS_TCREAR, LogContratoComboConstants.CLASS_ALIAS, LogContratoComboConstants.ARG_ALIAS_TCREAR_LOGCONTRATOCOMBOTCREARPTPATRACCION);
        }
    }
}
