package com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogGrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrgrupoasignacion_oid")
    private GrupoAsignacionOid logGrupoAsignacionTCREARptpagrGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion logGrupoAsignacionTCREARptpagrGrupoAsignacionInstance;

    @JsonProperty("pt_p_atraccion")
    private String logGrupoAsignacionTCREARptpatrAccion;

    @JsonProperty("pt_p_atrvalor")
    private String logGrupoAsignacionTCREARptpatrValor;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGrupoAsignacionTCREARptpagrGrupoAsignacion();
        checkLogGrupoAsignacionTCREARptpatrAccion();
        checkLogGrupoAsignacionTCREARptpatrValor();
    }

    @JsonIgnore
    public GrupoAsignacionOid getLogGrupoAsignacionTCREARptpagrGrupoAsignacion() {
        return this.logGrupoAsignacionTCREARptpagrGrupoAsignacion;
    }
    
    public void setLogGrupoAsignacionTCREARptpagrGrupoAsignacion(GrupoAsignacionOid logGrupoAsignacionTCREARptpagrGrupoAsignacion) {
        this.logGrupoAsignacionTCREARptpagrGrupoAsignacion = logGrupoAsignacionTCREARptpagrGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance() {
        return this.logGrupoAsignacionTCREARptpagrGrupoAsignacionInstance;
    }
    
    public void setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(GrupoAsignacion logGrupoAsignacionTCREARptpagrGrupoAsignacion) {
        this.logGrupoAsignacionTCREARptpagrGrupoAsignacionInstance = logGrupoAsignacionTCREARptpagrGrupoAsignacion;
        this.logGrupoAsignacionTCREARptpagrGrupoAsignacion = logGrupoAsignacionTCREARptpagrGrupoAsignacion != null ? logGrupoAsignacionTCREARptpagrGrupoAsignacion.getOid() : null;
    }

    private void checkLogGrupoAsignacionTCREARptpagrGrupoAsignacion() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance() == null || this.getLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_TCREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_TCREAR_LOGGRUPOASIGNACIONTCREARPTPAGRGRUPOASIGNACION, LogGrupoAsignacionConstants.SERV_ALIAS_TCREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_TCREAR_LOGGRUPOASIGNACIONTCREARPTPAGRGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public String getLogGrupoAsignacionTCREARptpatrAccion() {
        return this.logGrupoAsignacionTCREARptpatrAccion;
    }
    
    public void setLogGrupoAsignacionTCREARptpatrAccion(String logGrupoAsignacionTCREARptpatrAccion) {
        this.logGrupoAsignacionTCREARptpatrAccion = logGrupoAsignacionTCREARptpatrAccion;
    }

    private void checkLogGrupoAsignacionTCREARptpatrAccion() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacionTCREARptpatrAccion() == null) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_TCREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_TCREAR_LOGGRUPOASIGNACIONTCREARPTPATRACCION, LogGrupoAsignacionConstants.SERV_ALIAS_TCREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_TCREAR_LOGGRUPOASIGNACIONTCREARPTPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogGrupoAsignacionTCREARptpatrValor() {
        return this.logGrupoAsignacionTCREARptpatrValor;
    }
    
    public void setLogGrupoAsignacionTCREARptpatrValor(String logGrupoAsignacionTCREARptpatrValor) {
        this.logGrupoAsignacionTCREARptpatrValor = logGrupoAsignacionTCREARptpatrValor;
    }

    private void checkLogGrupoAsignacionTCREARptpatrValor() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacionTCREARptpatrValor() == null) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_TCREAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_TCREAR_LOGGRUPOASIGNACIONTCREARPTPATRVALOR, LogGrupoAsignacionConstants.SERV_ALIAS_TCREAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_TCREAR_LOGGRUPOASIGNACIONTCREARPTPATRVALOR);
        }
    }
}
