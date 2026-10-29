package com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
public class ICTipoEntidad {

    @JsonProperty("descripcion")
    private String iCTipoEntidadDescripcion;

    public ICTipoEntidad() {
    
    }

    public ICTipoEntidad(TipoEntidadC instance) {
        if (instance != null) {
            this.iCTipoEntidadDescripcion = instance.getTipoEntidadCDescripcion();
        }
    }

    @JsonIgnore
    public String getICTipoEntidadDescripcion() {
        return this.iCTipoEntidadDescripcion;
    }
    
    public void setICTipoEntidadDescripcion(String iCTipoEntidadDescripcion) {
        this.iCTipoEntidadDescripcion = iCTipoEntidadDescripcion;
    }
}
