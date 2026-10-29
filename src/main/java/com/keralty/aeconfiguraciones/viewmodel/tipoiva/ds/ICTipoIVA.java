package com.keralty.aeconfiguraciones.viewmodel.tipoiva.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
public class ICTipoIVA {

    @JsonProperty("descripcion")
    private String iCTipoIVADescripcion;

    public ICTipoIVA() {
    
    }

    public ICTipoIVA(TipoIVA instance) {
        if (instance != null) {
            this.iCTipoIVADescripcion = instance.getTipoIVADescripcion();
        }
    }

    @JsonIgnore
    public String getICTipoIVADescripcion() {
        return this.iCTipoIVADescripcion;
    }
    
    public void setICTipoIVADescripcion(String iCTipoIVADescripcion) {
        this.iCTipoIVADescripcion = iCTipoIVADescripcion;
    }
}
