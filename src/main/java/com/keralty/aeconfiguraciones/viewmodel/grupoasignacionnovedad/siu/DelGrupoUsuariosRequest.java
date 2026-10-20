package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class DelGrupoUsuariosRequest {

    @JsonProperty("p_thisgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad();
        checkGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion();
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad() {
        return this.grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad = grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance = grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad;
        this.grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad = grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad != null ? grupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_DELGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_DELGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADDELGRUPOUSUARIOSPTHISGRUPOASIGNACIONNOVEDAD, GrupoAsignacionNovedadConstants.SERV_ALIAS_DELGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_DELGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADDELGRUPOUSUARIOSPTHISGRUPOASIGNACIONNOVEDAD);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion() {
        return this.grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion;
    }
    
    public void setGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion(GrupoAsignacionOid grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion) {
        this.grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion = grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance() {
        return this.grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion) {
        this.grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance = grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion;
        this.grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion = grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion != null ? grupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance() == null || this.getGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_DELGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_DELGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADDELGRUPOUSUARIOSPEVCGRUPOASIGNACION, GrupoAsignacionNovedadConstants.SERV_ALIAS_DELGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_DELGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADDELGRUPOUSUARIOSPEVCGRUPOASIGNACION);
        }
    }
}
