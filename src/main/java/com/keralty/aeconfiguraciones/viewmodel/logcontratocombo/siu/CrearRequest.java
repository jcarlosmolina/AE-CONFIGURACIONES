package com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;

public class CrearRequest {

    @JsonProperty("p_atrloginfuncionario")
    private String logContratoCombocrearpatrLoginFuncionario;

    @JsonProperty("p_atrcontratointegral")
    private String logContratoCombocrearpatrContratoIntegral;

    @JsonProperty("p_atrcontratoodontologico")
    private String logContratoCombocrearpatrContratoOdontologico;

    @JsonProperty("p_atraccion")
    private String logContratoCombocrearpatrAccion;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratoCombocrearpatrLoginFuncionario();
        checkLogContratoCombocrearpatrAccion();
    }

    @JsonIgnore
    public String getLogContratoCombocrearpatrLoginFuncionario() {
        return this.logContratoCombocrearpatrLoginFuncionario;
    }
    
    public void setLogContratoCombocrearpatrLoginFuncionario(String logContratoCombocrearpatrLoginFuncionario) {
        this.logContratoCombocrearpatrLoginFuncionario = logContratoCombocrearpatrLoginFuncionario;
    }

    private void checkLogContratoCombocrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogContratoCombocrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogContratoComboConstants.SERV_ID_CREAR, LogContratoComboConstants.CLASS_ID, LogContratoComboConstants.ARG_ID_CREAR_LOGCONTRATOCOMBOCREARPATRLOGINFUNCIONARIO, LogContratoComboConstants.SERV_ALIAS_CREAR, LogContratoComboConstants.CLASS_ALIAS, LogContratoComboConstants.ARG_ALIAS_CREAR_LOGCONTRATOCOMBOCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public String getLogContratoCombocrearpatrContratoIntegral() {
        return this.logContratoCombocrearpatrContratoIntegral;
    }
    
    public void setLogContratoCombocrearpatrContratoIntegral(String logContratoCombocrearpatrContratoIntegral) {
        this.logContratoCombocrearpatrContratoIntegral = logContratoCombocrearpatrContratoIntegral;
    }

    @JsonIgnore
    public String getLogContratoCombocrearpatrContratoOdontologico() {
        return this.logContratoCombocrearpatrContratoOdontologico;
    }
    
    public void setLogContratoCombocrearpatrContratoOdontologico(String logContratoCombocrearpatrContratoOdontologico) {
        this.logContratoCombocrearpatrContratoOdontologico = logContratoCombocrearpatrContratoOdontologico;
    }

    @JsonIgnore
    public String getLogContratoCombocrearpatrAccion() {
        return this.logContratoCombocrearpatrAccion;
    }
    
    public void setLogContratoCombocrearpatrAccion(String logContratoCombocrearpatrAccion) {
        this.logContratoCombocrearpatrAccion = logContratoCombocrearpatrAccion;
    }

    private void checkLogContratoCombocrearpatrAccion() throws NotNullArgumentException {
        if(this.getLogContratoCombocrearpatrAccion() == null) {
            throw new NotNullArgumentException(LogContratoComboConstants.SERV_ID_CREAR, LogContratoComboConstants.CLASS_ID, LogContratoComboConstants.ARG_ID_CREAR_LOGCONTRATOCOMBOCREARPATRACCION, LogContratoComboConstants.SERV_ALIAS_CREAR, LogContratoComboConstants.CLASS_ALIAS, LogContratoComboConstants.ARG_ALIAS_CREAR_LOGCONTRATOCOMBOCREARPATRACCION);
        }
    }
}
