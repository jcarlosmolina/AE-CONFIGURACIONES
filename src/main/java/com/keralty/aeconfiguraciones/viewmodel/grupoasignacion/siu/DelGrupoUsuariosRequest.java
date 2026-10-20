package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class DelGrupoUsuariosRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance;

    @JsonProperty("p_evcgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacion();
        checkGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacion() {
        return this.grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion = grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance() {
        return this.grupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance = grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion;
        this.grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion = grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion != null ? grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOUSUARIOS_GRUPOASIGNACIONDELGRUPOUSUARIOSPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOUSUARIOS_GRUPOASIGNACIONDELGRUPOUSUARIOSPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad() {
        return this.grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad) {
        this.grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad = grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad) {
        this.grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance = grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad;
        this.grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad = grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad != null ? grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOUSUARIOS_GRUPOASIGNACIONDELGRUPOUSUARIOSPEVCGRUPOASIGNACIONNOVEDAD, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOUSUARIOS_GRUPOASIGNACIONDELGRUPOUSUARIOSPEVCGRUPOASIGNACIONNOVEDAD);
        }
    }
}
