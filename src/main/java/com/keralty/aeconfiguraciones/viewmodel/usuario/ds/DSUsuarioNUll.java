package com.keralty.aeconfiguraciones.viewmodel.usuario.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Usuario;
public class DSUsuarioNUll {

    @JsonProperty("identificador")
    private Long dSUsuarioNUllIdentificador;

    public DSUsuarioNUll() {
    
    }

    public DSUsuarioNUll(Usuario instance) {
        if (instance != null) {
            this.dSUsuarioNUllIdentificador = instance.getUsuarioIdentificador();
        }
    }

    @JsonIgnore
    public Long getDSUsuarioNUllIdentificador() {
        return this.dSUsuarioNUllIdentificador;
    }
    
    public void setDSUsuarioNUllIdentificador(Long dSUsuarioNUllIdentificador) {
        this.dSUsuarioNUllIdentificador = dSUsuarioNUllIdentificador;
    }
}
