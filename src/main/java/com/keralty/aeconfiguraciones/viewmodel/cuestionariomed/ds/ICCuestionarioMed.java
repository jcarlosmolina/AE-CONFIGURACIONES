package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
public class ICCuestionarioMed {

    @JsonProperty("nombre")
    private String iCCuestionarioMedNombre;

    public ICCuestionarioMed() {
    
    }

    public ICCuestionarioMed(CuestionarioMed instance) {
        if (instance != null) {
            this.iCCuestionarioMedNombre = instance.getCuestionarioMedNombre();
        }
    }

    @JsonIgnore
    public String getICCuestionarioMedNombre() {
        return this.iCCuestionarioMedNombre;
    }
    
    public void setICCuestionarioMedNombre(String iCCuestionarioMedNombre) {
        this.iCCuestionarioMedNombre = iCCuestionarioMedNombre;
    }
}
