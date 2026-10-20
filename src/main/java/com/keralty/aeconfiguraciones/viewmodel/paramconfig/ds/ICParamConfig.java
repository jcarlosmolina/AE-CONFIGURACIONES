package com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
public class ICParamConfig {

    @JsonProperty("descripcion")
    private String iCParamConfigDescripcion;

    public ICParamConfig() {
    
    }

    public ICParamConfig(ParamConfig instance) {
        if (instance != null) {
            this.iCParamConfigDescripcion = instance.getParamConfigDescripcion();
        }
    }

    @JsonIgnore
    public String getICParamConfigDescripcion() {
        return this.iCParamConfigDescripcion;
    }
    
    public void setICParamConfigDescripcion(String iCParamConfigDescripcion) {
        this.iCParamConfigDescripcion = iCParamConfigDescripcion;
    }
}
