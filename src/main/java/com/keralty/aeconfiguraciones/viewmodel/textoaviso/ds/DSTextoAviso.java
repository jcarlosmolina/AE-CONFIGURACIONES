package com.keralty.aeconfiguraciones.viewmodel.textoaviso.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TextoAviso;
public class DSTextoAviso {

    @JsonProperty("identificador")
    private String dSTextoAvisoIdentificador;

    @JsonProperty("descripcion")
    private String dSTextoAvisoDescripcion;

    public DSTextoAviso() {
    
    }

    public DSTextoAviso(TextoAviso instance) {
        if (instance != null) {
            this.dSTextoAvisoIdentificador = instance.getTextoAvisoIdentificador();
            this.dSTextoAvisoDescripcion = instance.getTextoAvisoDescripcion();
        }
    }

    @JsonIgnore
    public String getDSTextoAvisoIdentificador() {
        return this.dSTextoAvisoIdentificador;
    }
    
    public void setDSTextoAvisoIdentificador(String dSTextoAvisoIdentificador) {
        this.dSTextoAvisoIdentificador = dSTextoAvisoIdentificador;
    }

    @JsonIgnore
    public String getDSTextoAvisoDescripcion() {
        return this.dSTextoAvisoDescripcion;
    }
    
    public void setDSTextoAvisoDescripcion(String dSTextoAvisoDescripcion) {
        this.dSTextoAvisoDescripcion = dSTextoAvisoDescripcion;
    }
}
