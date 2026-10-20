package com.keralty.aeconfiguraciones.viewmodel.causal.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Causal;
public class ICCausal {

    @JsonProperty("nombre")
    private String iCCausalNombre;

    public ICCausal() {
    
    }

    public ICCausal(Causal instance) {
        if (instance != null) {
            this.iCCausalNombre = instance.getCausalNombre();
        }
    }

    @JsonIgnore
    public String getICCausalNombre() {
        return this.iCCausalNombre;
    }
    
    public void setICCausalNombre(String iCCausalNombre) {
        this.iCCausalNombre = iCCausalNombre;
    }
}
