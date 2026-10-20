package com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.MotDevAreaMedica;
public class ICMotDevAreaMedica {

    @JsonProperty("descripcion")
    private String iCMotDevAreaMedicaDescripcion;

    public ICMotDevAreaMedica() {
    
    }

    public ICMotDevAreaMedica(MotDevAreaMedica instance) {
        if (instance != null) {
            this.iCMotDevAreaMedicaDescripcion = instance.getMotDevAreaMedicaDescripcion();
        }
    }

    @JsonIgnore
    public String getICMotDevAreaMedicaDescripcion() {
        return this.iCMotDevAreaMedicaDescripcion;
    }
    
    public void setICMotDevAreaMedicaDescripcion(String iCMotDevAreaMedicaDescripcion) {
        this.iCMotDevAreaMedicaDescripcion = iCMotDevAreaMedicaDescripcion;
    }
}
