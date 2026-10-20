package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoUsuariosRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance;

    @JsonProperty("p_evcgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacion();
        checkGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacion() {
        return this.grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion = grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance() {
        return this.grupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance = grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion;
        this.grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion = grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion != null ? grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOUSUARIOS_GRUPOASIGNACIONINSGRUPOUSUARIOSPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOUSUARIOS_GRUPOASIGNACIONINSGRUPOUSUARIOSPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad() {
        return this.grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad) {
        this.grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad = grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad) {
        this.grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance = grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad;
        this.grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad = grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad != null ? grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOUSUARIOS_GRUPOASIGNACIONINSGRUPOUSUARIOSPEVCGRUPOASIGNACIONNOVEDAD, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOUSUARIOS, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOUSUARIOS_GRUPOASIGNACIONINSGRUPOUSUARIOSPEVCGRUPOASIGNACIONNOVEDAD);
        }
    }
}
