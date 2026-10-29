package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long estadoBeneficiariocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String estadoBeneficiariocrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiariocrearpatrCodigo();
        checkEstadoBeneficiariocrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getEstadoBeneficiariocrearpatrCodigo() {
        return this.estadoBeneficiariocrearpatrCodigo;
    }
    
    public void setEstadoBeneficiariocrearpatrCodigo(Long estadoBeneficiariocrearpatrCodigo) {
        this.estadoBeneficiariocrearpatrCodigo = estadoBeneficiariocrearpatrCodigo;
    }

    private void checkEstadoBeneficiariocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_CREAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_CREAR_ESTADOBENEFICIARIOCREARPATRCODIGO, EstadoBeneficiarioConstants.SERV_ALIAS_CREAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_CREAR_ESTADOBENEFICIARIOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiariocrearpatrDescripcion() {
        return this.estadoBeneficiariocrearpatrDescripcion;
    }
    
    public void setEstadoBeneficiariocrearpatrDescripcion(String estadoBeneficiariocrearpatrDescripcion) {
        this.estadoBeneficiariocrearpatrDescripcion = estadoBeneficiariocrearpatrDescripcion;
    }

    private void checkEstadoBeneficiariocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_CREAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_CREAR_ESTADOBENEFICIARIOCREARPATRDESCRIPCION, EstadoBeneficiarioConstants.SERV_ALIAS_CREAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_CREAR_ESTADOBENEFICIARIOCREARPATRDESCRIPCION);
        }
    }
}
