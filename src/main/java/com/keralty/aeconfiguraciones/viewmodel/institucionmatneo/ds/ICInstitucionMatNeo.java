package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
public class ICInstitucionMatNeo {

    @JsonProperty("nombre")
    private String iCInstitucionMatNeoNombre;

    public ICInstitucionMatNeo() {
    
    }

    public ICInstitucionMatNeo(InstitucionMatNeo instance) {
        if (instance != null) {
            this.iCInstitucionMatNeoNombre = instance.getInstitucionMatNeoNombre();
        }
    }

    @JsonIgnore
    public String getICInstitucionMatNeoNombre() {
        return this.iCInstitucionMatNeoNombre;
    }
    
    public void setICInstitucionMatNeoNombre(String iCInstitucionMatNeoNombre) {
        this.iCInstitucionMatNeoNombre = iCInstitucionMatNeoNombre;
    }
}
