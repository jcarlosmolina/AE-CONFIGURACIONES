package com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.MotDevAreaMedica;
public class DSMotDevAreaMedica {

    @JsonProperty("id")
    private Long dSMotDevAreaMedicaId;

    @JsonProperty("descripcion")
    private String dSMotDevAreaMedicaDescripcion;

    public DSMotDevAreaMedica() {
    
    }

    public DSMotDevAreaMedica(MotDevAreaMedica instance) {
        if (instance != null) {
            this.dSMotDevAreaMedicaId = instance.getMotDevAreaMedicaId();
            this.dSMotDevAreaMedicaDescripcion = instance.getMotDevAreaMedicaDescripcion();
        }
    }

    @JsonIgnore
    public Long getDSMotDevAreaMedicaId() {
        return this.dSMotDevAreaMedicaId;
    }
    
    public void setDSMotDevAreaMedicaId(Long dSMotDevAreaMedicaId) {
        this.dSMotDevAreaMedicaId = dSMotDevAreaMedicaId;
    }

    @JsonIgnore
    public String getDSMotDevAreaMedicaDescripcion() {
        return this.dSMotDevAreaMedicaDescripcion;
    }
    
    public void setDSMotDevAreaMedicaDescripcion(String dSMotDevAreaMedicaDescripcion) {
        this.dSMotDevAreaMedicaDescripcion = dSMotDevAreaMedicaDescripcion;
    }
}
