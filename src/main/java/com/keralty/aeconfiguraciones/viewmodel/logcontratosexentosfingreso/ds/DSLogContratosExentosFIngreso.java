package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
public class DSLogContratosExentosFIngreso {

    @JsonProperty("fecha")
    private Timestamp dSLogContratosExentosFIngresoFecha;

    @JsonProperty("loginfuncionario")
    private String dSLogContratosExentosFIngresoLoginFuncionario;

    @JsonProperty("accion")
    private String dSLogContratosExentosFIngresoAccion;

    @JsonProperty("numcontrato")
    private String dSLogContratosExentosFIngresoNumContrato;

    public DSLogContratosExentosFIngreso() {
    
    }

    public DSLogContratosExentosFIngreso(LogContratosExentosFIngreso instance) {
        if (instance != null) {
            this.dSLogContratosExentosFIngresoFecha = instance.getLogContratosExentosFIngresoFecha();
            this.dSLogContratosExentosFIngresoLoginFuncionario = instance.getLogContratosExentosFIngresoLoginFuncionario();
            this.dSLogContratosExentosFIngresoAccion = instance.getLogContratosExentosFIngresoAccion();
            this.dSLogContratosExentosFIngresoNumContrato = instance.getLogContratosExentosFIngresoNumContrato();
        }
    }

    @JsonIgnore
    public Timestamp getDSLogContratosExentosFIngresoFecha() {
        return this.dSLogContratosExentosFIngresoFecha;
    }
    
    public void setDSLogContratosExentosFIngresoFecha(Timestamp dSLogContratosExentosFIngresoFecha) {
        this.dSLogContratosExentosFIngresoFecha = dSLogContratosExentosFIngresoFecha;
    }

    @JsonIgnore
    public String getDSLogContratosExentosFIngresoLoginFuncionario() {
        return this.dSLogContratosExentosFIngresoLoginFuncionario;
    }
    
    public void setDSLogContratosExentosFIngresoLoginFuncionario(String dSLogContratosExentosFIngresoLoginFuncionario) {
        this.dSLogContratosExentosFIngresoLoginFuncionario = dSLogContratosExentosFIngresoLoginFuncionario;
    }

    @JsonIgnore
    public String getDSLogContratosExentosFIngresoAccion() {
        return this.dSLogContratosExentosFIngresoAccion;
    }
    
    public void setDSLogContratosExentosFIngresoAccion(String dSLogContratosExentosFIngresoAccion) {
        this.dSLogContratosExentosFIngresoAccion = dSLogContratosExentosFIngresoAccion;
    }

    @JsonIgnore
    public String getDSLogContratosExentosFIngresoNumContrato() {
        return this.dSLogContratosExentosFIngresoNumContrato;
    }
    
    public void setDSLogContratosExentosFIngresoNumContrato(String dSLogContratosExentosFIngresoNumContrato) {
        this.dSLogContratosExentosFIngresoNumContrato = dSLogContratosExentosFIngresoNumContrato;
    }
}
