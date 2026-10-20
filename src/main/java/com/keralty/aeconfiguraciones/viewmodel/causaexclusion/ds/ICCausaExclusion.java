package com.keralty.aeconfiguraciones.viewmodel.causaexclusion.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
public class ICCausaExclusion {

    @JsonProperty("descripcion")
    private String iCCausaExclusionDescripcion;

    public ICCausaExclusion() {
    
    }

    public ICCausaExclusion(CausaExclusion instance) {
        if (instance != null) {
            this.iCCausaExclusionDescripcion = instance.getCausaExclusionDescripcion();
        }
    }

    @JsonIgnore
    public String getICCausaExclusionDescripcion() {
        return this.iCCausaExclusionDescripcion;
    }
    
    public void setICCausaExclusionDescripcion(String iCCausaExclusionDescripcion) {
        this.iCCausaExclusionDescripcion = iCCausaExclusionDescripcion;
    }
}
