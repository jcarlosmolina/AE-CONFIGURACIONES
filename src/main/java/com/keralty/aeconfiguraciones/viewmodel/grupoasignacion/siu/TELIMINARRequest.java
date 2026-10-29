package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TELIMINARRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionTELIMINARpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionTELIMINARpthisGrupoAsignacionInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionTELIMINARpthisGrupoAsignacion();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionTELIMINARpthisGrupoAsignacion() {
        return this.grupoAsignacionTELIMINARpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionTELIMINARpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionTELIMINARpthisGrupoAsignacion) {
        this.grupoAsignacionTELIMINARpthisGrupoAsignacion = grupoAsignacionTELIMINARpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance() {
        return this.grupoAsignacionTELIMINARpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionTELIMINARpthisGrupoAsignacion) {
        this.grupoAsignacionTELIMINARpthisGrupoAsignacionInstance = grupoAsignacionTELIMINARpthisGrupoAsignacion;
        this.grupoAsignacionTELIMINARpthisGrupoAsignacion = grupoAsignacionTELIMINARpthisGrupoAsignacion != null ? grupoAsignacionTELIMINARpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionTELIMINARpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TELIMINAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TELIMINAR_GRUPOASIGNACIONTELIMINARPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_TELIMINAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TELIMINAR_GRUPOASIGNACIONTELIMINARPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
