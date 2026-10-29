package com.keralty.aeconfiguraciones.viewmodel.textoaviso.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TextoAviso;
public class ICTextoAviso {

    @JsonProperty("identificador")
    private String iCTextoAvisoIdentificador;

    public ICTextoAviso() {
    
    }

    public ICTextoAviso(TextoAviso instance) {
        if (instance != null) {
            this.iCTextoAvisoIdentificador = instance.getTextoAvisoIdentificador();
        }
    }

    @JsonIgnore
    public String getICTextoAvisoIdentificador() {
        return this.iCTextoAvisoIdentificador;
    }
    
    public void setICTextoAvisoIdentificador(String iCTextoAvisoIdentificador) {
        this.iCTextoAvisoIdentificador = iCTextoAvisoIdentificador;
    }
}
