package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
public class DSCuestionarioMedPIU {

    @JsonProperty("numero")
    private Long dSCuestionarioMedPIUNumero;

    @JsonProperty("nombre")
    private String dSCuestionarioMedPIUNombre;

    public DSCuestionarioMedPIU() {
    
    }

    public DSCuestionarioMedPIU(CuestionarioMed instance) {
        if (instance != null) {
            this.dSCuestionarioMedPIUNumero = instance.getCuestionarioMedNumero();
            this.dSCuestionarioMedPIUNombre = instance.getCuestionarioMedNombre();
        }
    }

    @JsonIgnore
    public Long getDSCuestionarioMedPIUNumero() {
        return this.dSCuestionarioMedPIUNumero;
    }
    
    public void setDSCuestionarioMedPIUNumero(Long dSCuestionarioMedPIUNumero) {
        this.dSCuestionarioMedPIUNumero = dSCuestionarioMedPIUNumero;
    }

    @JsonIgnore
    public String getDSCuestionarioMedPIUNombre() {
        return this.dSCuestionarioMedPIUNombre;
    }
    
    public void setDSCuestionarioMedPIUNombre(String dSCuestionarioMedPIUNombre) {
        this.dSCuestionarioMedPIUNombre = dSCuestionarioMedPIUNombre;
    }
}
