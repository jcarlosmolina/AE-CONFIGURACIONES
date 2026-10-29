package com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
public class DSLogGrupoAsignacion {

    @JsonProperty("fecha")
    private Timestamp dSLogGrupoAsignacionFecha;

    @JsonProperty("loginfuncionario")
    private String dSLogGrupoAsignacionLoginFuncionario;

    @JsonProperty("accion")
    private String dSLogGrupoAsignacionAccion;

    @JsonProperty("valor")
    private String dSLogGrupoAsignacionValor;

    public DSLogGrupoAsignacion() {
    
    }

    public DSLogGrupoAsignacion(LogGrupoAsignacion instance) {
        if (instance != null) {
            this.dSLogGrupoAsignacionFecha = instance.getLogGrupoAsignacionFecha();
            this.dSLogGrupoAsignacionLoginFuncionario = instance.getLogGrupoAsignacionLoginFuncionario();
            this.dSLogGrupoAsignacionAccion = instance.getLogGrupoAsignacionAccion();
            this.dSLogGrupoAsignacionValor = instance.getLogGrupoAsignacionValor();
        }
    }

    @JsonIgnore
    public Timestamp getDSLogGrupoAsignacionFecha() {
        return this.dSLogGrupoAsignacionFecha;
    }
    
    public void setDSLogGrupoAsignacionFecha(Timestamp dSLogGrupoAsignacionFecha) {
        this.dSLogGrupoAsignacionFecha = dSLogGrupoAsignacionFecha;
    }

    @JsonIgnore
    public String getDSLogGrupoAsignacionLoginFuncionario() {
        return this.dSLogGrupoAsignacionLoginFuncionario;
    }
    
    public void setDSLogGrupoAsignacionLoginFuncionario(String dSLogGrupoAsignacionLoginFuncionario) {
        this.dSLogGrupoAsignacionLoginFuncionario = dSLogGrupoAsignacionLoginFuncionario;
    }

    @JsonIgnore
    public String getDSLogGrupoAsignacionAccion() {
        return this.dSLogGrupoAsignacionAccion;
    }
    
    public void setDSLogGrupoAsignacionAccion(String dSLogGrupoAsignacionAccion) {
        this.dSLogGrupoAsignacionAccion = dSLogGrupoAsignacionAccion;
    }

    @JsonIgnore
    public String getDSLogGrupoAsignacionValor() {
        return this.dSLogGrupoAsignacionValor;
    }
    
    public void setDSLogGrupoAsignacionValor(String dSLogGrupoAsignacionValor) {
        this.dSLogGrupoAsignacionValor = dSLogGrupoAsignacionValor;
    }
}
