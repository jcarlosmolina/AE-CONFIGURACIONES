package com.keralty.aeconfiguraciones.viewmodel.seccion.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Seccion;
public class ICSeccion {

    @JsonProperty("nombre")
    private String iCSeccionNombre;

    public ICSeccion() {
    
    }

    public ICSeccion(Seccion instance) {
        if (instance != null) {
            this.iCSeccionNombre = instance.getSeccionNombre();
        }
    }

    @JsonIgnore
    public String getICSeccionNombre() {
        return this.iCSeccionNombre;
    }
    
    public void setICSeccionNombre(String iCSeccionNombre) {
        this.iCSeccionNombre = iCSeccionNombre;
    }
}
