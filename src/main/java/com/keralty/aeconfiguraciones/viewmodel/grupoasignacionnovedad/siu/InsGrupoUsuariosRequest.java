package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoUsuariosRequest {

    @JsonProperty("p_thisgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad();
        checkGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion();
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad() {
        return this.grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad = grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance = grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad;
        this.grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad = grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad != null ? grupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_INSGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_INSGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADINSGRUPOUSUARIOSPTHISGRUPOASIGNACIONNOVEDAD, GrupoAsignacionNovedadConstants.SERV_ALIAS_INSGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_INSGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADINSGRUPOUSUARIOSPTHISGRUPOASIGNACIONNOVEDAD);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion() {
        return this.grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion;
    }
    
    public void setGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion(GrupoAsignacionOid grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion) {
        this.grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion = grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance() {
        return this.grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion) {
        this.grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance = grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion;
        this.grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion = grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion != null ? grupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance() == null || this.getGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_INSGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_INSGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADINSGRUPOUSUARIOSPEVCGRUPOASIGNACION, GrupoAsignacionNovedadConstants.SERV_ALIAS_INSGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_INSGRUPOUSUARIOS_GRUPOASIGNACIONNOVEDADINSGRUPOUSUARIOSPEVCGRUPOASIGNACION);
        }
    }
}
