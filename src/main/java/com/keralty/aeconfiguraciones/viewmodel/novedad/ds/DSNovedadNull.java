package com.keralty.aeconfiguraciones.viewmodel.novedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Novedad;
public class DSNovedadNull {

    @JsonProperty("idnovedad")
    private Long dSNovedadNullIdNovedad;

    public DSNovedadNull() {
    
    }

    public DSNovedadNull(Novedad instance) {
        if (instance != null) {
            this.dSNovedadNullIdNovedad = instance.getNovedadidNovedad();
        }
    }

    @JsonIgnore
    public Long getDSNovedadNullIdNovedad() {
        return this.dSNovedadNullIdNovedad;
    }
    
    public void setDSNovedadNullIdNovedad(Long dSNovedadNullIdNovedad) {
        this.dSNovedadNullIdNovedad = dSNovedadNullIdNovedad;
    }
}
