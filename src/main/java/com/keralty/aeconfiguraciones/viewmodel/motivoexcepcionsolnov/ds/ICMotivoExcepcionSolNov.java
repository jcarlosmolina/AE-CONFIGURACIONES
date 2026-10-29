package com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
public class ICMotivoExcepcionSolNov {

    @JsonProperty("descripcion")
    private String iCMotivoExcepcionSolNovDescripcion;

    public ICMotivoExcepcionSolNov() {
    
    }

    public ICMotivoExcepcionSolNov(MotivoExcepcionSolNov instance) {
        if (instance != null) {
            this.iCMotivoExcepcionSolNovDescripcion = instance.getMotivoExcepcionSolNovDescripcion();
        }
    }

    @JsonIgnore
    public String getICMotivoExcepcionSolNovDescripcion() {
        return this.iCMotivoExcepcionSolNovDescripcion;
    }
    
    public void setICMotivoExcepcionSolNovDescripcion(String iCMotivoExcepcionSolNovDescripcion) {
        this.iCMotivoExcepcionSolNovDescripcion = iCMotivoExcepcionSolNovDescripcion;
    }
}
