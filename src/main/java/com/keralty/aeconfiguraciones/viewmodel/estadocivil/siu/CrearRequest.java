package com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoCivilConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long estadoCivilcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String estadoCivilcrearpatrDescripcion;

    @JsonProperty("p_atrnombrecorto")
    private String estadoCivilcrearpatrNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoCivilcrearpatrCodigo();
        checkEstadoCivilcrearpatrDescripcion();
        checkEstadoCivilcrearpatrNombreCorto();
    }

    @JsonIgnore
    public Long getEstadoCivilcrearpatrCodigo() {
        return this.estadoCivilcrearpatrCodigo;
    }
    
    public void setEstadoCivilcrearpatrCodigo(Long estadoCivilcrearpatrCodigo) {
        this.estadoCivilcrearpatrCodigo = estadoCivilcrearpatrCodigo;
    }

    private void checkEstadoCivilcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEstadoCivilcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_CREAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_CREAR_ESTADOCIVILCREARPATRCODIGO, EstadoCivilConstants.SERV_ALIAS_CREAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_CREAR_ESTADOCIVILCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEstadoCivilcrearpatrDescripcion() {
        return this.estadoCivilcrearpatrDescripcion;
    }
    
    public void setEstadoCivilcrearpatrDescripcion(String estadoCivilcrearpatrDescripcion) {
        this.estadoCivilcrearpatrDescripcion = estadoCivilcrearpatrDescripcion;
    }

    private void checkEstadoCivilcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEstadoCivilcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_CREAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_CREAR_ESTADOCIVILCREARPATRDESCRIPCION, EstadoCivilConstants.SERV_ALIAS_CREAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_CREAR_ESTADOCIVILCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getEstadoCivilcrearpatrNombreCorto() {
        return this.estadoCivilcrearpatrNombreCorto;
    }
    
    public void setEstadoCivilcrearpatrNombreCorto(String estadoCivilcrearpatrNombreCorto) {
        this.estadoCivilcrearpatrNombreCorto = estadoCivilcrearpatrNombreCorto;
    }

    private void checkEstadoCivilcrearpatrNombreCorto() throws NotNullArgumentException {
        if(this.getEstadoCivilcrearpatrNombreCorto() == null) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_CREAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_CREAR_ESTADOCIVILCREARPATRNOMBRECORTO, EstadoCivilConstants.SERV_ALIAS_CREAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_CREAR_ESTADOCIVILCREARPATRNOMBRECORTO);
        }
    }
}
