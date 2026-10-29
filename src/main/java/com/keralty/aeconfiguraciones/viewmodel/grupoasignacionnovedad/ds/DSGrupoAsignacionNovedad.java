package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
public class DSGrupoAsignacionNovedad {

    @JsonProperty("causal_novedad_nombre")
    private String dSGrupoAsignacionNovedadCausalDotNovedadDotNombre;

    @JsonProperty("causal_nombre")
    private String dSGrupoAsignacionNovedadCausalDotNombre;

    @JsonProperty("grupousuarios_nombre")
    private String dSGrupoAsignacionNovedadGrupoUsuariosDotNombre;

    public DSGrupoAsignacionNovedad() {
    
    }

    public DSGrupoAsignacionNovedad(GrupoAsignacionNovedad instance) {
        if (instance != null) {
            try {
                this.dSGrupoAsignacionNovedadCausalDotNovedadDotNombre = instance.getCausal().getNovedad().getNovedadNombre();
                this.dSGrupoAsignacionNovedadCausalDotNombre = instance.getCausal().getCausalNombre();
                this.dSGrupoAsignacionNovedadGrupoUsuariosDotNombre = instance.getGrupoUsuarios().getGrupoAsignacionNombre();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public String getDSGrupoAsignacionNovedadCausalDotNovedadDotNombre() {
        return this.dSGrupoAsignacionNovedadCausalDotNovedadDotNombre;
    }
    
    public void setDSGrupoAsignacionNovedadCausalDotNovedadDotNombre(String dSGrupoAsignacionNovedadCausalDotNovedadDotNombre) {
        this.dSGrupoAsignacionNovedadCausalDotNovedadDotNombre = dSGrupoAsignacionNovedadCausalDotNovedadDotNombre;
    }

    @JsonIgnore
    public String getDSGrupoAsignacionNovedadCausalDotNombre() {
        return this.dSGrupoAsignacionNovedadCausalDotNombre;
    }
    
    public void setDSGrupoAsignacionNovedadCausalDotNombre(String dSGrupoAsignacionNovedadCausalDotNombre) {
        this.dSGrupoAsignacionNovedadCausalDotNombre = dSGrupoAsignacionNovedadCausalDotNombre;
    }

    @JsonIgnore
    public String getDSGrupoAsignacionNovedadGrupoUsuariosDotNombre() {
        return this.dSGrupoAsignacionNovedadGrupoUsuariosDotNombre;
    }
    
    public void setDSGrupoAsignacionNovedadGrupoUsuariosDotNombre(String dSGrupoAsignacionNovedadGrupoUsuariosDotNombre) {
        this.dSGrupoAsignacionNovedadGrupoUsuariosDotNombre = dSGrupoAsignacionNovedadGrupoUsuariosDotNombre;
    }
}
