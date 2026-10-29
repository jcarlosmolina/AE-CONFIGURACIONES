package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
public class DSCuestionarioMedIIU {

    @JsonProperty("nombre")
    private String dSCuestionarioMedIIUNombre;

    public DSCuestionarioMedIIU() {
    
    }

    public DSCuestionarioMedIIU(CuestionarioMed instance) {
        if (instance != null) {
            this.dSCuestionarioMedIIUNombre = instance.getCuestionarioMedNombre();
        }
    }

    @JsonIgnore
    public String getDSCuestionarioMedIIUNombre() {
        return this.dSCuestionarioMedIIUNombre;
    }
    
    public void setDSCuestionarioMedIIUNombre(String dSCuestionarioMedIIUNombre) {
        this.dSCuestionarioMedIIUNombre = dSCuestionarioMedIIUNombre;
    }
}
