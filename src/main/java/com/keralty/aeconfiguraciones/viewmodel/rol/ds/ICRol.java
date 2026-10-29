package com.keralty.aeconfiguraciones.viewmodel.rol.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Rol;
public class ICRol {

    @JsonProperty("nombre")
    private String iCRolNombre;

    public ICRol() {
    
    }

    public ICRol(Rol instance) {
        if (instance != null) {
            this.iCRolNombre = instance.getRolNombre();
        }
    }

    @JsonIgnore
    public String getICRolNombre() {
        return this.iCRolNombre;
    }
    
    public void setICRolNombre(String iCRolNombre) {
        this.iCRolNombre = iCRolNombre;
    }
}
