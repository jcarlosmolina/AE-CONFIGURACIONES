package com.keralty.aeconfiguraciones.viewmodel.eps.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Eps;
public class ICEPS {

    @JsonProperty("descripcion")
    private String iCEPSDescripcion;

    public ICEPS() {
    
    }

    public ICEPS(Eps instance) {
        if (instance != null) {
            this.iCEPSDescripcion = instance.getEpsDescripcion();
        }
    }

    @JsonIgnore
    public String getICEPSDescripcion() {
        return this.iCEPSDescripcion;
    }
    
    public void setICEPSDescripcion(String iCEPSDescripcion) {
        this.iCEPSDescripcion = iCEPSDescripcion;
    }
}
