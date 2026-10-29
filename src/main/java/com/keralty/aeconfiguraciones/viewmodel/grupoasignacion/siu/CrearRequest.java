package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;

public class CrearRequest {

    @JsonProperty("p_atrtipo")
    private Long grupoAsignacioncrearpatrTipo;

    @JsonProperty("p_atrnombre")
    private String grupoAsignacioncrearpatrNombre;

    @JsonProperty("p_atrdescripcion")
    private String grupoAsignacioncrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacioncrearpatrTipo();
        checkGrupoAsignacioncrearpatrNombre();
        checkGrupoAsignacioncrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getGrupoAsignacioncrearpatrTipo() {
        return this.grupoAsignacioncrearpatrTipo;
    }
    
    public void setGrupoAsignacioncrearpatrTipo(Long grupoAsignacioncrearpatrTipo) {
        this.grupoAsignacioncrearpatrTipo = grupoAsignacioncrearpatrTipo;
    }

    private void checkGrupoAsignacioncrearpatrTipo() throws NotNullArgumentException {
        if(this.getGrupoAsignacioncrearpatrTipo() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_CREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_CREAR_GRUPOASIGNACIONCREARPATRTIPO, GrupoAsignacionConstants.SERV_ALIAS_CREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_CREAR_GRUPOASIGNACIONCREARPATRTIPO);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacioncrearpatrNombre() {
        return this.grupoAsignacioncrearpatrNombre;
    }
    
    public void setGrupoAsignacioncrearpatrNombre(String grupoAsignacioncrearpatrNombre) {
        this.grupoAsignacioncrearpatrNombre = grupoAsignacioncrearpatrNombre;
    }

    private void checkGrupoAsignacioncrearpatrNombre() throws NotNullArgumentException {
        if(this.getGrupoAsignacioncrearpatrNombre() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_CREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_CREAR_GRUPOASIGNACIONCREARPATRNOMBRE, GrupoAsignacionConstants.SERV_ALIAS_CREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_CREAR_GRUPOASIGNACIONCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacioncrearpatrDescripcion() {
        return this.grupoAsignacioncrearpatrDescripcion;
    }
    
    public void setGrupoAsignacioncrearpatrDescripcion(String grupoAsignacioncrearpatrDescripcion) {
        this.grupoAsignacioncrearpatrDescripcion = grupoAsignacioncrearpatrDescripcion;
    }

    private void checkGrupoAsignacioncrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsignacioncrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_CREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_CREAR_GRUPOASIGNACIONCREARPATRDESCRIPCION, GrupoAsignacionConstants.SERV_ALIAS_CREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_CREAR_GRUPOASIGNACIONCREARPATRDESCRIPCION);
        }
    }
}
