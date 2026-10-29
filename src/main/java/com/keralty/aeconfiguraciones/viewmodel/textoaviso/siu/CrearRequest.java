package com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TextoAvisoConstants;

public class CrearRequest {

    @JsonProperty("p_atridentificador")
    private String textoAvisocrearpatrIdentificador;

    @JsonProperty("p_atrdescripcion")
    private String textoAvisocrearpatrDescripcion;

    @JsonProperty("p_atrvalor")
    private String textoAvisocrearpatrValor;

    public void checkArguments() throws NotNullArgumentException {
        checkTextoAvisocrearpatrIdentificador();
        checkTextoAvisocrearpatrValor();
    }

    @JsonIgnore
    public String getTextoAvisocrearpatrIdentificador() {
        return this.textoAvisocrearpatrIdentificador;
    }
    
    public void setTextoAvisocrearpatrIdentificador(String textoAvisocrearpatrIdentificador) {
        this.textoAvisocrearpatrIdentificador = textoAvisocrearpatrIdentificador;
    }

    private void checkTextoAvisocrearpatrIdentificador() throws NotNullArgumentException {
        if(this.getTextoAvisocrearpatrIdentificador() == null) {
            throw new NotNullArgumentException(TextoAvisoConstants.SERV_ID_CREAR, TextoAvisoConstants.CLASS_ID, TextoAvisoConstants.ARG_ID_CREAR_TEXTOAVISOCREARPATRIDENTIFICADOR, TextoAvisoConstants.SERV_ALIAS_CREAR, TextoAvisoConstants.CLASS_ALIAS, TextoAvisoConstants.ARG_ALIAS_CREAR_TEXTOAVISOCREARPATRIDENTIFICADOR);
        }
    }

    @JsonIgnore
    public String getTextoAvisocrearpatrDescripcion() {
        return this.textoAvisocrearpatrDescripcion;
    }
    
    public void setTextoAvisocrearpatrDescripcion(String textoAvisocrearpatrDescripcion) {
        this.textoAvisocrearpatrDescripcion = textoAvisocrearpatrDescripcion;
    }

    @JsonIgnore
    public String getTextoAvisocrearpatrValor() {
        return this.textoAvisocrearpatrValor;
    }
    
    public void setTextoAvisocrearpatrValor(String textoAvisocrearpatrValor) {
        this.textoAvisocrearpatrValor = textoAvisocrearpatrValor;
    }

    private void checkTextoAvisocrearpatrValor() throws NotNullArgumentException {
        if(this.getTextoAvisocrearpatrValor() == null) {
            throw new NotNullArgumentException(TextoAvisoConstants.SERV_ID_CREAR, TextoAvisoConstants.CLASS_ID, TextoAvisoConstants.ARG_ID_CREAR_TEXTOAVISOCREARPATRVALOR, TextoAvisoConstants.SERV_ALIAS_CREAR, TextoAvisoConstants.CLASS_ALIAS, TextoAvisoConstants.ARG_ALIAS_CREAR_TEXTOAVISOCREARPATRVALOR);
        }
    }
}
