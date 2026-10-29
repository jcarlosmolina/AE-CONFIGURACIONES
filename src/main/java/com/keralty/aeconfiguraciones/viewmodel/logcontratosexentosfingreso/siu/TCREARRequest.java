package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratosExentosFIngresoConstants;

public class TCREARRequest {

    @JsonProperty("pt_p_atraccion")
    private String logContratosExentosFIngresoTCREARptpatrAccion;

    @JsonProperty("pt_p_atrnumcontrato")
    private String logContratosExentosFIngresoTCREARptpatrNumContrato;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratosExentosFIngresoTCREARptpatrAccion();
        checkLogContratosExentosFIngresoTCREARptpatrNumContrato();
    }

    @JsonIgnore
    public String getLogContratosExentosFIngresoTCREARptpatrAccion() {
        return this.logContratosExentosFIngresoTCREARptpatrAccion;
    }
    
    public void setLogContratosExentosFIngresoTCREARptpatrAccion(String logContratosExentosFIngresoTCREARptpatrAccion) {
        this.logContratosExentosFIngresoTCREARptpatrAccion = logContratosExentosFIngresoTCREARptpatrAccion;
    }

    private void checkLogContratosExentosFIngresoTCREARptpatrAccion() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresoTCREARptpatrAccion() == null) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_TCREAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_TCREAR_LOGCONTRATOSEXENTOSFINGRESOTCREARPTPATRACCION, LogContratosExentosFIngresoConstants.SERV_ALIAS_TCREAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_TCREAR_LOGCONTRATOSEXENTOSFINGRESOTCREARPTPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogContratosExentosFIngresoTCREARptpatrNumContrato() {
        return this.logContratosExentosFIngresoTCREARptpatrNumContrato;
    }
    
    public void setLogContratosExentosFIngresoTCREARptpatrNumContrato(String logContratosExentosFIngresoTCREARptpatrNumContrato) {
        this.logContratosExentosFIngresoTCREARptpatrNumContrato = logContratosExentosFIngresoTCREARptpatrNumContrato;
    }

    private void checkLogContratosExentosFIngresoTCREARptpatrNumContrato() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresoTCREARptpatrNumContrato() == null) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_TCREAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_TCREAR_LOGCONTRATOSEXENTOSFINGRESOTCREARPTPATRNUMCONTRATO, LogContratosExentosFIngresoConstants.SERV_ALIAS_TCREAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_TCREAR_LOGCONTRATOSEXENTOSFINGRESOTCREARPTPATRNUMCONTRATO);
        }
    }
}
