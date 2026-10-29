package com.keralty.aeconfiguraciones.viewmodel.novedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Novedad;
public class ICNovedad {

    @JsonProperty("nombre")
    private String iCNovedadNombre;

    public ICNovedad() {
    
    }

    public ICNovedad(Novedad instance) {
        if (instance != null) {
            this.iCNovedadNombre = instance.getNovedadNombre();
        }
    }

    @JsonIgnore
    public String getICNovedadNombre() {
        return this.iCNovedadNombre;
    }
    
    public void setICNovedadNombre(String iCNovedadNombre) {
        this.iCNovedadNombre = iCNovedadNombre;
    }
}
