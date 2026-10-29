package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
public class DSContratosExentosFIngreso {

    @JsonProperty("numcontrato")
    private String dSContratosExentosFIngresoNumContrato;

    public DSContratosExentosFIngreso() {
    
    }

    public DSContratosExentosFIngreso(ContratosExentosFIngreso instance) {
        if (instance != null) {
            this.dSContratosExentosFIngresoNumContrato = instance.getContratosExentosFIngresoNumContrato();
        }
    }

    @JsonIgnore
    public String getDSContratosExentosFIngresoNumContrato() {
        return this.dSContratosExentosFIngresoNumContrato;
    }
    
    public void setDSContratosExentosFIngresoNumContrato(String dSContratosExentosFIngresoNumContrato) {
        this.dSContratosExentosFIngresoNumContrato = dSContratosExentosFIngresoNumContrato;
    }
}
