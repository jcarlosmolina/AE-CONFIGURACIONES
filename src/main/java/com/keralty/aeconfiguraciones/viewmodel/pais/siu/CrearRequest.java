package com.keralty.aeconfiguraciones.viewmodel.pais.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PaisConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private String paiscrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String paiscrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkPaiscrearpatrCodigo();
    }

    @JsonIgnore
    public String getPaiscrearpatrCodigo() {
        return this.paiscrearpatrCodigo;
    }
    
    public void setPaiscrearpatrCodigo(String paiscrearpatrCodigo) {
        this.paiscrearpatrCodigo = paiscrearpatrCodigo;
    }

    private void checkPaiscrearpatrCodigo() throws NotNullArgumentException {
        if(this.getPaiscrearpatrCodigo() == null) {
            throw new NotNullArgumentException(PaisConstants.SERV_ID_CREAR, PaisConstants.CLASS_ID, PaisConstants.ARG_ID_CREAR_PAISCREARPATRCODIGO, PaisConstants.SERV_ALIAS_CREAR, PaisConstants.CLASS_ALIAS, PaisConstants.ARG_ALIAS_CREAR_PAISCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getPaiscrearpatrDescripcion() {
        return this.paiscrearpatrDescripcion;
    }
    
    public void setPaiscrearpatrDescripcion(String paiscrearpatrDescripcion) {
        this.paiscrearpatrDescripcion = paiscrearpatrDescripcion;
    }
}
