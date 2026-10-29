package com.keralty.aeconfiguraciones.viewmodel.logparam.ds;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogParam;
public class DSLogParamPIU {

    @JsonProperty("fecha")
    private Date dSLogParamPIUFecha;

    @JsonProperty("usuario")
    private String dSLogParamPIUUsuario;

    @JsonProperty("valoranterior")
    private String dSLogParamPIUValorAnterior;

    @JsonProperty("nuevovalor")
    private String dSLogParamPIUNuevoValor;

    public DSLogParamPIU() {
    
    }

    public DSLogParamPIU(LogParam instance) {
        if (instance != null) {
            this.dSLogParamPIUFecha = instance.getLogParamFecha();
            this.dSLogParamPIUUsuario = instance.getLogParamUsuario();
            this.dSLogParamPIUValorAnterior = instance.getLogParamValorAnterior();
            this.dSLogParamPIUNuevoValor = instance.getLogParamNuevoValor();
        }
    }

    @JsonIgnore
    public Date getDSLogParamPIUFecha() {
        return this.dSLogParamPIUFecha;
    }
    
    public void setDSLogParamPIUFecha(Date dSLogParamPIUFecha) {
        this.dSLogParamPIUFecha = dSLogParamPIUFecha;
    }

    @JsonIgnore
    public String getDSLogParamPIUUsuario() {
        return this.dSLogParamPIUUsuario;
    }
    
    public void setDSLogParamPIUUsuario(String dSLogParamPIUUsuario) {
        this.dSLogParamPIUUsuario = dSLogParamPIUUsuario;
    }

    @JsonIgnore
    public String getDSLogParamPIUValorAnterior() {
        return this.dSLogParamPIUValorAnterior;
    }
    
    public void setDSLogParamPIUValorAnterior(String dSLogParamPIUValorAnterior) {
        this.dSLogParamPIUValorAnterior = dSLogParamPIUValorAnterior;
    }

    @JsonIgnore
    public String getDSLogParamPIUNuevoValor() {
        return this.dSLogParamPIUNuevoValor;
    }
    
    public void setDSLogParamPIUNuevoValor(String dSLogParamPIUNuevoValor) {
        this.dSLogParamPIUNuevoValor = dSLogParamPIUNuevoValor;
    }
}
