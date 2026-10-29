package com.keralty.aeconfiguraciones.viewmodel.municipio.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Municipio;
public class ICMunicipio {

    @JsonProperty("descripcion")
    private String iCMunicipioDescripcion;

    public ICMunicipio() {
    
    }

    public ICMunicipio(Municipio instance) {
        if (instance != null) {
            this.iCMunicipioDescripcion = instance.getMunicipioDescripcion();
        }
    }

    @JsonIgnore
    public String getICMunicipioDescripcion() {
        return this.iCMunicipioDescripcion;
    }
    
    public void setICMunicipioDescripcion(String iCMunicipioDescripcion) {
        this.iCMunicipioDescripcion = iCMunicipioDescripcion;
    }
}
