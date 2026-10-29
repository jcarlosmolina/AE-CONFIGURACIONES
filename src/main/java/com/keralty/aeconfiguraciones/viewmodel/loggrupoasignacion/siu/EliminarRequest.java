package com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogGrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.LogGrupoAsignacionOid;

public class EliminarRequest {

    @JsonProperty("p_thisloggrupoasignacion_oid")
    private LogGrupoAsignacionOid logGrupoAsignacioneliminarpthisLogGrupoAsignacion;
    
    @JsonIgnore
    private LogGrupoAsignacion logGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGrupoAsignacioneliminarpthisLogGrupoAsignacion();
    }

    @JsonIgnore
    public LogGrupoAsignacionOid getLogGrupoAsignacioneliminarpthisLogGrupoAsignacion() {
        return this.logGrupoAsignacioneliminarpthisLogGrupoAsignacion;
    }
    
    public void setLogGrupoAsignacioneliminarpthisLogGrupoAsignacion(LogGrupoAsignacionOid logGrupoAsignacioneliminarpthisLogGrupoAsignacion) {
        this.logGrupoAsignacioneliminarpthisLogGrupoAsignacion = logGrupoAsignacioneliminarpthisLogGrupoAsignacion;
    }

    @JsonIgnore
    public LogGrupoAsignacion getLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance() {
        return this.logGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance;
    }
    
    public void setLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance(LogGrupoAsignacion logGrupoAsignacioneliminarpthisLogGrupoAsignacion) {
        this.logGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance = logGrupoAsignacioneliminarpthisLogGrupoAsignacion;
        this.logGrupoAsignacioneliminarpthisLogGrupoAsignacion = logGrupoAsignacioneliminarpthisLogGrupoAsignacion != null ? logGrupoAsignacioneliminarpthisLogGrupoAsignacion.getOid() : null;
    }

    private void checkLogGrupoAsignacioneliminarpthisLogGrupoAsignacion() throws NotNullArgumentException {
        if(this.getLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance() == null || this.getLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(LogGrupoAsignacionConstants.SERV_ID_ELIMINAR, LogGrupoAsignacionConstants.CLASS_ID, LogGrupoAsignacionConstants.ARG_ID_ELIMINAR_LOGGRUPOASIGNACIONELIMINARPTHISLOGGRUPOASIGNACION, LogGrupoAsignacionConstants.SERV_ALIAS_ELIMINAR, LogGrupoAsignacionConstants.CLASS_ALIAS, LogGrupoAsignacionConstants.ARG_ALIAS_ELIMINAR_LOGGRUPOASIGNACIONELIMINARPTHISLOGGRUPOASIGNACION);
        }
    }
}
