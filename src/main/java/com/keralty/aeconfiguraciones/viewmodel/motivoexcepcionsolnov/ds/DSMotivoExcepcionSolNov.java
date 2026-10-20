package com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
public class DSMotivoExcepcionSolNov {

    @JsonProperty("descripcion")
    private String dSMotivoExcepcionSolNovDescripcion;

    public DSMotivoExcepcionSolNov() {
    
    }

    public DSMotivoExcepcionSolNov(MotivoExcepcionSolNov instance) {
        if (instance != null) {
            this.dSMotivoExcepcionSolNovDescripcion = instance.getMotivoExcepcionSolNovDescripcion();
        }
    }

    @JsonIgnore
    public String getDSMotivoExcepcionSolNovDescripcion() {
        return this.dSMotivoExcepcionSolNovDescripcion;
    }
    
    public void setDSMotivoExcepcionSolNovDescripcion(String dSMotivoExcepcionSolNovDescripcion) {
        this.dSMotivoExcepcionSolNovDescripcion = dSMotivoExcepcionSolNovDescripcion;
    }
}
