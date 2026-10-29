package com.keralty.aeconfiguraciones.viewmodel.causaexclusion.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
public class DSCausaExclusion {

    @JsonProperty("aliascausa")
    private String dSCausaExclusionAliasCausa;

    @JsonProperty("descripcion")
    private String dSCausaExclusionDescripcion;

    public DSCausaExclusion() {
    
    }

    public DSCausaExclusion(CausaExclusion instance) {
        if (instance != null) {
            this.dSCausaExclusionAliasCausa = instance.getCausaExclusionAliasCausa();
            this.dSCausaExclusionDescripcion = instance.getCausaExclusionDescripcion();
        }
    }

    @JsonIgnore
    public String getDSCausaExclusionAliasCausa() {
        return this.dSCausaExclusionAliasCausa;
    }
    
    public void setDSCausaExclusionAliasCausa(String dSCausaExclusionAliasCausa) {
        this.dSCausaExclusionAliasCausa = dSCausaExclusionAliasCausa;
    }

    @JsonIgnore
    public String getDSCausaExclusionDescripcion() {
        return this.dSCausaExclusionDescripcion;
    }
    
    public void setDSCausaExclusionDescripcion(String dSCausaExclusionDescripcion) {
        this.dSCausaExclusionDescripcion = dSCausaExclusionDescripcion;
    }
}
