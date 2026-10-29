package com.keralty.aeconfiguraciones.viewmodel.causal.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Causal;
public class DSCausalNull {

    @JsonProperty("idcausal")
    private Long dSCausalNullIdCausal;

    public DSCausalNull() {
    
    }

    public DSCausalNull(Causal instance) {
        if (instance != null) {
            this.dSCausalNullIdCausal = instance.getCausalidCausal();
        }
    }

    @JsonIgnore
    public Long getDSCausalNullIdCausal() {
        return this.dSCausalNullIdCausal;
    }
    
    public void setDSCausalNullIdCausal(Long dSCausalNullIdCausal) {
        this.dSCausalNullIdCausal = dSCausalNullIdCausal;
    }
}
