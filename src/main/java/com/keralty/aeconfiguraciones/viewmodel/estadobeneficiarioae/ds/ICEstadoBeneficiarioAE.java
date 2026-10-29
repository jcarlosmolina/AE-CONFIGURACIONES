package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
public class ICEstadoBeneficiarioAE {

    @JsonProperty("descripcion")
    private String iCEstadoBeneficiarioAEDescripcion;

    public ICEstadoBeneficiarioAE() {
    
    }

    public ICEstadoBeneficiarioAE(EstadoBeneficiarioAE instance) {
        if (instance != null) {
            this.iCEstadoBeneficiarioAEDescripcion = instance.getEstadoBeneficiarioAEDescripcion();
        }
    }

    @JsonIgnore
    public String getICEstadoBeneficiarioAEDescripcion() {
        return this.iCEstadoBeneficiarioAEDescripcion;
    }
    
    public void setICEstadoBeneficiarioAEDescripcion(String iCEstadoBeneficiarioAEDescripcion) {
        this.iCEstadoBeneficiarioAEDescripcion = iCEstadoBeneficiarioAEDescripcion;
    }
}
