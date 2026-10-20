package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContratosExentosFIngresoConstants;

public class CrearRequest {

    @JsonProperty("p_atrnumcontrato")
    private String contratosExentosFIngresocrearpatrNumContrato;

    public void checkArguments() throws NotNullArgumentException {
        checkContratosExentosFIngresocrearpatrNumContrato();
    }

    @JsonIgnore
    public String getContratosExentosFIngresocrearpatrNumContrato() {
        return this.contratosExentosFIngresocrearpatrNumContrato;
    }
    
    public void setContratosExentosFIngresocrearpatrNumContrato(String contratosExentosFIngresocrearpatrNumContrato) {
        this.contratosExentosFIngresocrearpatrNumContrato = contratosExentosFIngresocrearpatrNumContrato;
    }

    private void checkContratosExentosFIngresocrearpatrNumContrato() throws NotNullArgumentException {
        if(this.getContratosExentosFIngresocrearpatrNumContrato() == null) {
            throw new NotNullArgumentException(ContratosExentosFIngresoConstants.SERV_ID_CREAR, ContratosExentosFIngresoConstants.CLASS_ID, ContratosExentosFIngresoConstants.ARG_ID_CREAR_CONTRATOSEXENTOSFINGRESOCREARPATRNUMCONTRATO, ContratosExentosFIngresoConstants.SERV_ALIAS_CREAR, ContratosExentosFIngresoConstants.CLASS_ALIAS, ContratosExentosFIngresoConstants.ARG_ALIAS_CREAR_CONTRATOSEXENTOSFINGRESOCREARPATRNUMCONTRATO);
        }
    }
}
