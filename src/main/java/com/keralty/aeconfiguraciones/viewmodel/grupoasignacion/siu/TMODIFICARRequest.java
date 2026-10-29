package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TMODIFICARRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionTMODIFICARpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionTMODIFICARpthisGrupoAsignacionInstance;

    @JsonProperty("pt_p_nombre")
    private String grupoAsignacionTMODIFICARptpNombre;

    @JsonProperty("pt_p_descripcion")
    private String grupoAsignacionTMODIFICARptpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionTMODIFICARpthisGrupoAsignacion();
        checkGrupoAsignacionTMODIFICARptpNombre();
        checkGrupoAsignacionTMODIFICARptpDescripcion();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionTMODIFICARpthisGrupoAsignacion() {
        return this.grupoAsignacionTMODIFICARpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionTMODIFICARpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionTMODIFICARpthisGrupoAsignacion) {
        this.grupoAsignacionTMODIFICARpthisGrupoAsignacion = grupoAsignacionTMODIFICARpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance() {
        return this.grupoAsignacionTMODIFICARpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionTMODIFICARpthisGrupoAsignacion) {
        this.grupoAsignacionTMODIFICARpthisGrupoAsignacionInstance = grupoAsignacionTMODIFICARpthisGrupoAsignacion;
        this.grupoAsignacionTMODIFICARpthisGrupoAsignacion = grupoAsignacionTMODIFICARpthisGrupoAsignacion != null ? grupoAsignacionTMODIFICARpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionTMODIFICARpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TMODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_TMODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionTMODIFICARptpNombre() {
        return this.grupoAsignacionTMODIFICARptpNombre;
    }
    
    public void setGrupoAsignacionTMODIFICARptpNombre(String grupoAsignacionTMODIFICARptpNombre) {
        this.grupoAsignacionTMODIFICARptpNombre = grupoAsignacionTMODIFICARptpNombre;
    }

    private void checkGrupoAsignacionTMODIFICARptpNombre() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTMODIFICARptpNombre() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TMODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTPNOMBRE, GrupoAsignacionConstants.SERV_ALIAS_TMODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTPNOMBRE);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionTMODIFICARptpDescripcion() {
        return this.grupoAsignacionTMODIFICARptpDescripcion;
    }
    
    public void setGrupoAsignacionTMODIFICARptpDescripcion(String grupoAsignacionTMODIFICARptpDescripcion) {
        this.grupoAsignacionTMODIFICARptpDescripcion = grupoAsignacionTMODIFICARptpDescripcion;
    }

    private void checkGrupoAsignacionTMODIFICARptpDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTMODIFICARptpDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TMODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTPDESCRIPCION, GrupoAsignacionConstants.SERV_ALIAS_TMODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TMODIFICAR_GRUPOASIGNACIONTMODIFICARPTPDESCRIPCION);
        }
    }
}
