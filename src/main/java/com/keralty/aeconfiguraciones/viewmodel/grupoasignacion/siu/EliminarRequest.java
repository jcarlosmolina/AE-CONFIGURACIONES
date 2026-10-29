package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class EliminarRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacioneliminarpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacioneliminarpthisGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacioneliminarpthisGrupoAsignacion();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacioneliminarpthisGrupoAsignacion() {
        return this.grupoAsignacioneliminarpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacioneliminarpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacioneliminarpthisGrupoAsignacion) {
        this.grupoAsignacioneliminarpthisGrupoAsignacion = grupoAsignacioneliminarpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacioneliminarpthisGrupoAsignacionInstance() {
        return this.grupoAsignacioneliminarpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacioneliminarpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacioneliminarpthisGrupoAsignacion) {
        this.grupoAsignacioneliminarpthisGrupoAsignacionInstance = grupoAsignacioneliminarpthisGrupoAsignacion;
        this.grupoAsignacioneliminarpthisGrupoAsignacion = grupoAsignacioneliminarpthisGrupoAsignacion != null ? grupoAsignacioneliminarpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacioneliminarpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacioneliminarpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacioneliminarpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_ELIMINAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_ELIMINAR_GRUPOASIGNACIONELIMINARPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_ELIMINAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_ELIMINAR_GRUPOASIGNACIONELIMINARPTHISGRUPOASIGNACION);
        }
    }
}
