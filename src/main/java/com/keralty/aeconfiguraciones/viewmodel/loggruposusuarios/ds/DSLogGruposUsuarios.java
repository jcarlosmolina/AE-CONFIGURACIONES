package com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
public class DSLogGruposUsuarios {

    @JsonProperty("fecha")
    private Timestamp dSLogGruposUsuariosFecha;

    @JsonProperty("loginfuncionario")
    private String dSLogGruposUsuariosLoginFuncionario;

    @JsonProperty("tipogrupo")
    private Long dSLogGruposUsuariosTipoGrupo;

    @JsonProperty("accion")
    private String dSLogGruposUsuariosAccion;

    @JsonProperty("grupo")
    private String dSLogGruposUsuariosGrupo;

    public DSLogGruposUsuarios() {
    
    }

    public DSLogGruposUsuarios(LogGruposUsuarios instance) {
        if (instance != null) {
            this.dSLogGruposUsuariosFecha = instance.getLogGruposUsuariosFecha();
            this.dSLogGruposUsuariosLoginFuncionario = instance.getLogGruposUsuariosLoginFuncionario();
            this.dSLogGruposUsuariosTipoGrupo = instance.getLogGruposUsuariosTipoGrupo();
            this.dSLogGruposUsuariosAccion = instance.getLogGruposUsuariosAccion();
            this.dSLogGruposUsuariosGrupo = instance.getLogGruposUsuariosGrupo();
        }
    }

    @JsonIgnore
    public Timestamp getDSLogGruposUsuariosFecha() {
        return this.dSLogGruposUsuariosFecha;
    }
    
    public void setDSLogGruposUsuariosFecha(Timestamp dSLogGruposUsuariosFecha) {
        this.dSLogGruposUsuariosFecha = dSLogGruposUsuariosFecha;
    }

    @JsonIgnore
    public String getDSLogGruposUsuariosLoginFuncionario() {
        return this.dSLogGruposUsuariosLoginFuncionario;
    }
    
    public void setDSLogGruposUsuariosLoginFuncionario(String dSLogGruposUsuariosLoginFuncionario) {
        this.dSLogGruposUsuariosLoginFuncionario = dSLogGruposUsuariosLoginFuncionario;
    }

    @JsonIgnore
    public Long getDSLogGruposUsuariosTipoGrupo() {
        return this.dSLogGruposUsuariosTipoGrupo;
    }
    
    public void setDSLogGruposUsuariosTipoGrupo(Long dSLogGruposUsuariosTipoGrupo) {
        this.dSLogGruposUsuariosTipoGrupo = dSLogGruposUsuariosTipoGrupo;
    }

    @JsonIgnore
    public String getDSLogGruposUsuariosAccion() {
        return this.dSLogGruposUsuariosAccion;
    }
    
    public void setDSLogGruposUsuariosAccion(String dSLogGruposUsuariosAccion) {
        this.dSLogGruposUsuariosAccion = dSLogGruposUsuariosAccion;
    }

    @JsonIgnore
    public String getDSLogGruposUsuariosGrupo() {
        return this.dSLogGruposUsuariosGrupo;
    }
    
    public void setDSLogGruposUsuariosGrupo(String dSLogGruposUsuariosGrupo) {
        this.dSLogGruposUsuariosGrupo = dSLogGruposUsuariosGrupo;
    }
}
