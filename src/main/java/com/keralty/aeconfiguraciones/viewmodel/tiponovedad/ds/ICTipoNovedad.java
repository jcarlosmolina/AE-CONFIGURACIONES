package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
public class ICTipoNovedad {

    @JsonProperty("nombre")
    private String iCTipoNovedadNombre;

    public ICTipoNovedad() {
    
    }

    public ICTipoNovedad(TipoNovedad instance) {
        if (instance != null) {
            this.iCTipoNovedadNombre = instance.getTipoNovedadNombre();
        }
    }

    @JsonIgnore
    public String getICTipoNovedadNombre() {
        return this.iCTipoNovedadNombre;
    }
    
    public void setICTipoNovedadNombre(String iCTipoNovedadNombre) {
        this.iCTipoNovedadNombre = iCTipoNovedadNombre;
    }
}
