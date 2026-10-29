package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
public class ICContratosExentosFIngreso {

    @JsonProperty("numcontrato")
    private String iCContratosExentosFIngresoNumContrato;

    public ICContratosExentosFIngreso() {
    
    }

    public ICContratosExentosFIngreso(ContratosExentosFIngreso instance) {
        if (instance != null) {
            this.iCContratosExentosFIngresoNumContrato = instance.getContratosExentosFIngresoNumContrato();
        }
    }

    @JsonIgnore
    public String getICContratosExentosFIngresoNumContrato() {
        return this.iCContratosExentosFIngresoNumContrato;
    }
    
    public void setICContratosExentosFIngresoNumContrato(String iCContratosExentosFIngresoNumContrato) {
        this.iCContratosExentosFIngresoNumContrato = iCContratosExentosFIngresoNumContrato;
    }
}
