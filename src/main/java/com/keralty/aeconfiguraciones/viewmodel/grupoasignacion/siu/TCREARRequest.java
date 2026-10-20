package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;

public class TCREARRequest {

    @JsonProperty("pt_p_atrtipo")
    private Long grupoAsignacionTCREARptpatrTipo;

    @JsonProperty("pt_p_atrnombre")
    private String grupoAsignacionTCREARptpatrNombre;

    @JsonProperty("pt_p_atrdescripcion")
    private String grupoAsignacionTCREARptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionTCREARptpatrTipo();
        checkGrupoAsignacionTCREARptpatrNombre();
        checkGrupoAsignacionTCREARptpatrDescripcion();
    }

    @JsonIgnore
    public Long getGrupoAsignacionTCREARptpatrTipo() {
        return this.grupoAsignacionTCREARptpatrTipo;
    }
    
    public void setGrupoAsignacionTCREARptpatrTipo(Long grupoAsignacionTCREARptpatrTipo) {
        this.grupoAsignacionTCREARptpatrTipo = grupoAsignacionTCREARptpatrTipo;
    }

    private void checkGrupoAsignacionTCREARptpatrTipo() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTCREARptpatrTipo() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TCREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TCREAR_GRUPOASIGNACIONTCREARPTPATRTIPO, GrupoAsignacionConstants.SERV_ALIAS_TCREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONTCREARPTPATRTIPO);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionTCREARptpatrNombre() {
        return this.grupoAsignacionTCREARptpatrNombre;
    }
    
    public void setGrupoAsignacionTCREARptpatrNombre(String grupoAsignacionTCREARptpatrNombre) {
        this.grupoAsignacionTCREARptpatrNombre = grupoAsignacionTCREARptpatrNombre;
    }

    private void checkGrupoAsignacionTCREARptpatrNombre() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTCREARptpatrNombre() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TCREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TCREAR_GRUPOASIGNACIONTCREARPTPATRNOMBRE, GrupoAsignacionConstants.SERV_ALIAS_TCREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONTCREARPTPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionTCREARptpatrDescripcion() {
        return this.grupoAsignacionTCREARptpatrDescripcion;
    }
    
    public void setGrupoAsignacionTCREARptpatrDescripcion(String grupoAsignacionTCREARptpatrDescripcion) {
        this.grupoAsignacionTCREARptpatrDescripcion = grupoAsignacionTCREARptpatrDescripcion;
    }

    private void checkGrupoAsignacionTCREARptpatrDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTCREARptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TCREAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TCREAR_GRUPOASIGNACIONTCREARPTPATRDESCRIPCION, GrupoAsignacionConstants.SERV_ALIAS_TCREAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONTCREARPTPATRDESCRIPCION);
        }
    }
}
