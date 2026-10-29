package com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogGrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class CrearRequest {

    @JsonProperty("p_agrgrupoasignacion_oid")
    private GrupoAsignacionOid logGrupoAsignacioncrearpagrGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion logGrupoAsignacioncrearpagrGrupoAsignacionInstance;

    @JsonProperty("p_atrloginfuncionario")
    private String logGrupoAsignacioncrearpatrLoginFuncionario;

    @JsonProperty("p_atraccion")
    private String logGrupoAsignacioncrearpatrAccion;

    @JsonProperty("p_atrvalor")
    private String logGrupoAsignacioncrearpatrValor;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGrupoAsignacioncrearpagrGrupoAsignacion();
        checkLogGrupoAsignacioncrearpatrLoginFuncionario();
        checkLogGrupoAsignacioncrearpatrAccion();
        checkLogGrupoAsignacioncrearpatrValor();
    }

    @JsonIgnore
    public GrupoAsignacionOid getLogGrupoAsignacioncrearpagrGrupoAsignacion() {
        return this.logGrupoAsignacioncrearpagrGrupoAsignacion;
    }
    
    public void setLogGrupoAsignacioncrearpagrGrupoAsignacion(GrupoAsignacionOid logGrupoAsignacioncrearpagrGrupoAsignacion) {
        this.logGrupoAsignacioncrearpagrGrupoAsignacion = logGrupoAsignacioncrearpagrGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getLogGrupoAsignacioncrearpagrGrupoAsignacionInstance() {
        return this.logGrupoAsignacioncrearpagrGrupoAsignacionInstance;
    }
    
    public void setLogGrupoAsignacioncrearpagrGrupoAsignacionInstance(GrupoAsignacion logGrupoAsignacioncrearpagrGrupoAsignacion) {
        this.logGrupoAsignacioncrearpagrGrupoAsignacionInstance = logGrupoAsignacioncrearpagrGrupoAsignacion;
        this.logGrupoAsignacioncrearpagrGrupoAsignacion = logGrupoAsignacioncrearpagrGrupoAsignacion != null ? logGrupoAsignacioncrearpagrGrupoAsignacion.getOid() : null;
    }

    private void checkLogGrupoAsignacioncrearpagrGrupoAsignacion() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacioncrearpagrGrupoAsignacionInstance() == null || this.getLogGrupoAsignacioncrearpagrGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_CREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_CREAR_LOGGRUPOASIGNACIONCREARPAGRGRUPOASIGNACION, LogGrupoAsignacionConstants.SERV_ALIAS_CREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_CREAR_LOGGRUPOASIGNACIONCREARPAGRGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public String getLogGrupoAsignacioncrearpatrLoginFuncionario() {
        return this.logGrupoAsignacioncrearpatrLoginFuncionario;
    }
    
    public void setLogGrupoAsignacioncrearpatrLoginFuncionario(String logGrupoAsignacioncrearpatrLoginFuncionario) {
        this.logGrupoAsignacioncrearpatrLoginFuncionario = logGrupoAsignacioncrearpatrLoginFuncionario;
    }

    private void checkLogGrupoAsignacioncrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacioncrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_CREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_CREAR_LOGGRUPOASIGNACIONCREARPATRLOGINFUNCIONARIO, LogGrupoAsignacionConstants.SERV_ALIAS_CREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_CREAR_LOGGRUPOASIGNACIONCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public String getLogGrupoAsignacioncrearpatrAccion() {
        return this.logGrupoAsignacioncrearpatrAccion;
    }
    
    public void setLogGrupoAsignacioncrearpatrAccion(String logGrupoAsignacioncrearpatrAccion) {
        this.logGrupoAsignacioncrearpatrAccion = logGrupoAsignacioncrearpatrAccion;
    }

    private void checkLogGrupoAsignacioncrearpatrAccion() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacioncrearpatrAccion() == null) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_CREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_CREAR_LOGGRUPOASIGNACIONCREARPATRACCION, LogGrupoAsignacionConstants.SERV_ALIAS_CREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_CREAR_LOGGRUPOASIGNACIONCREARPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogGrupoAsignacioncrearpatrValor() {
        return this.logGrupoAsignacioncrearpatrValor;
    }
    
    public void setLogGrupoAsignacioncrearpatrValor(String logGrupoAsignacioncrearpatrValor) {
        this.logGrupoAsignacioncrearpatrValor = logGrupoAsignacioncrearpatrValor;
    }

    private void checkLogGrupoAsignacioncrearpatrValor() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacioncrearpatrValor() == null) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_CREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_CREAR_LOGGRUPOASIGNACIONCREARPATRVALOR, LogGrupoAsignacionConstants.SERV_ALIAS_CREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_CREAR_LOGGRUPOASIGNACIONCREARPATRVALOR);
        }
    }
}
