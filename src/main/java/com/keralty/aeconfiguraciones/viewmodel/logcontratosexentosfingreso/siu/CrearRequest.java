package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratosExentosFIngresoConstants;

public class CrearRequest {

    @JsonProperty("p_atrloginfuncionario")
    private String logContratosExentosFIngresocrearpatrLoginFuncionario;

    @JsonProperty("p_atraccion")
    private String logContratosExentosFIngresocrearpatrAccion;

    @JsonProperty("p_atrnumcontrato")
    private String logContratosExentosFIngresocrearpatrNumContrato;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratosExentosFIngresocrearpatrLoginFuncionario();
        checkLogContratosExentosFIngresocrearpatrAccion();
        checkLogContratosExentosFIngresocrearpatrNumContrato();
    }

    @JsonIgnore
    public String getLogContratosExentosFIngresocrearpatrLoginFuncionario() {
        return this.logContratosExentosFIngresocrearpatrLoginFuncionario;
    }
    
    public void setLogContratosExentosFIngresocrearpatrLoginFuncionario(String logContratosExentosFIngresocrearpatrLoginFuncionario) {
        this.logContratosExentosFIngresocrearpatrLoginFuncionario = logContratosExentosFIngresocrearpatrLoginFuncionario;
    }

    private void checkLogContratosExentosFIngresocrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresocrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_CREAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRLOGINFUNCIONARIO, LogContratosExentosFIngresoConstants.SERV_ALIAS_CREAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public String getLogContratosExentosFIngresocrearpatrAccion() {
        return this.logContratosExentosFIngresocrearpatrAccion;
    }
    
    public void setLogContratosExentosFIngresocrearpatrAccion(String logContratosExentosFIngresocrearpatrAccion) {
        this.logContratosExentosFIngresocrearpatrAccion = logContratosExentosFIngresocrearpatrAccion;
    }

    private void checkLogContratosExentosFIngresocrearpatrAccion() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresocrearpatrAccion() == null) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_CREAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRACCION, LogContratosExentosFIngresoConstants.SERV_ALIAS_CREAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogContratosExentosFIngresocrearpatrNumContrato() {
        return this.logContratosExentosFIngresocrearpatrNumContrato;
    }
    
    public void setLogContratosExentosFIngresocrearpatrNumContrato(String logContratosExentosFIngresocrearpatrNumContrato) {
        this.logContratosExentosFIngresocrearpatrNumContrato = logContratosExentosFIngresocrearpatrNumContrato;
    }

    private void checkLogContratosExentosFIngresocrearpatrNumContrato() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresocrearpatrNumContrato() == null) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_CREAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRNUMCONTRATO, LogContratosExentosFIngresoConstants.SERV_ALIAS_CREAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_CREAR_LOGCONTRATOSEXENTOSFINGRESOCREARPATRNUMCONTRATO);
        }
    }
}
