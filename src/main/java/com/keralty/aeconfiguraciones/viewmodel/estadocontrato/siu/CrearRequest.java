package com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoContratoConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long estadoContratocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String estadoContratocrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoContratocrearpatrCodigo();
        checkEstadoContratocrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getEstadoContratocrearpatrCodigo() {
        return this.estadoContratocrearpatrCodigo;
    }
    
    public void setEstadoContratocrearpatrCodigo(Long estadoContratocrearpatrCodigo) {
        this.estadoContratocrearpatrCodigo = estadoContratocrearpatrCodigo;
    }

    private void checkEstadoContratocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEstadoContratocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EstadoContratoConstants.SERV_ID_CREAR, EstadoContratoConstants.CLASS_ID, EstadoContratoConstants.ARG_ID_CREAR_ESTADOCONTRATOCREARPATRCODIGO, EstadoContratoConstants.SERV_ALIAS_CREAR, EstadoContratoConstants.CLASS_ALIAS, EstadoContratoConstants.ARG_ALIAS_CREAR_ESTADOCONTRATOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEstadoContratocrearpatrDescripcion() {
        return this.estadoContratocrearpatrDescripcion;
    }
    
    public void setEstadoContratocrearpatrDescripcion(String estadoContratocrearpatrDescripcion) {
        this.estadoContratocrearpatrDescripcion = estadoContratocrearpatrDescripcion;
    }

    private void checkEstadoContratocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEstadoContratocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EstadoContratoConstants.SERV_ID_CREAR, EstadoContratoConstants.CLASS_ID, EstadoContratoConstants.ARG_ID_CREAR_ESTADOCONTRATOCREARPATRDESCRIPCION, EstadoContratoConstants.SERV_ALIAS_CREAR, EstadoContratoConstants.CLASS_ALIAS, EstadoContratoConstants.ARG_ALIAS_CREAR_ESTADOCONTRATOCREARPATRDESCRIPCION);
        }
    }
}
