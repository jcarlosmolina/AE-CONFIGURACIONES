package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class ModificarRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionmodificarpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionmodificarpthisGrupoAsignacionInstance;

    @JsonProperty("p_nombre")
    private String grupoAsignacionmodificarpNombre;

    @JsonProperty("p_descripcion")
    private String grupoAsignacionmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionmodificarpthisGrupoAsignacion();
        checkGrupoAsignacionmodificarpNombre();
        checkGrupoAsignacionmodificarpDescripcion();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionmodificarpthisGrupoAsignacion() {
        return this.grupoAsignacionmodificarpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionmodificarpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionmodificarpthisGrupoAsignacion) {
        this.grupoAsignacionmodificarpthisGrupoAsignacion = grupoAsignacionmodificarpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionmodificarpthisGrupoAsignacionInstance() {
        return this.grupoAsignacionmodificarpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionmodificarpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionmodificarpthisGrupoAsignacion) {
        this.grupoAsignacionmodificarpthisGrupoAsignacionInstance = grupoAsignacionmodificarpthisGrupoAsignacion;
        this.grupoAsignacionmodificarpthisGrupoAsignacion = grupoAsignacionmodificarpthisGrupoAsignacion != null ? grupoAsignacionmodificarpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionmodificarpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionmodificarpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionmodificarpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_MODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_MODIFICAR_GRUPOASIGNACIONMODIFICARPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_MODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_MODIFICAR_GRUPOASIGNACIONMODIFICARPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionmodificarpNombre() {
        return this.grupoAsignacionmodificarpNombre;
    }
    
    public void setGrupoAsignacionmodificarpNombre(String grupoAsignacionmodificarpNombre) {
        this.grupoAsignacionmodificarpNombre = grupoAsignacionmodificarpNombre;
    }

    private void checkGrupoAsignacionmodificarpNombre() throws NotNullArgumentException {
        if(this.getGrupoAsignacionmodificarpNombre() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_MODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_MODIFICAR_GRUPOASIGNACIONMODIFICARPNOMBRE, GrupoAsignacionConstants.SERV_ALIAS_MODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_MODIFICAR_GRUPOASIGNACIONMODIFICARPNOMBRE);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionmodificarpDescripcion() {
        return this.grupoAsignacionmodificarpDescripcion;
    }
    
    public void setGrupoAsignacionmodificarpDescripcion(String grupoAsignacionmodificarpDescripcion) {
        this.grupoAsignacionmodificarpDescripcion = grupoAsignacionmodificarpDescripcion;
    }

    private void checkGrupoAsignacionmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_MODIFICAR, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_MODIFICAR_GRUPOASIGNACIONMODIFICARPDESCRIPCION, GrupoAsignacionConstants.SERV_ALIAS_MODIFICAR, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_MODIFICAR_GRUPOASIGNACIONMODIFICARPDESCRIPCION);
        }
    }
}
