package com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long periodicidadcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String periodicidadcrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkPeriodicidadcrearpatrCodigo();
        checkPeriodicidadcrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getPeriodicidadcrearpatrCodigo() {
        return this.periodicidadcrearpatrCodigo;
    }
    
    public void setPeriodicidadcrearpatrCodigo(Long periodicidadcrearpatrCodigo) {
        this.periodicidadcrearpatrCodigo = periodicidadcrearpatrCodigo;
    }

    private void checkPeriodicidadcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getPeriodicidadcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_CREAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_CREAR_PERIODICIDADCREARPATRCODIGO, PeriodicidadConstants.SERV_ALIAS_CREAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_CREAR_PERIODICIDADCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getPeriodicidadcrearpatrDescripcion() {
        return this.periodicidadcrearpatrDescripcion;
    }
    
    public void setPeriodicidadcrearpatrDescripcion(String periodicidadcrearpatrDescripcion) {
        this.periodicidadcrearpatrDescripcion = periodicidadcrearpatrDescripcion;
    }

    private void checkPeriodicidadcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getPeriodicidadcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_CREAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_CREAR_PERIODICIDADCREARPATRDESCRIPCION, PeriodicidadConstants.SERV_ALIAS_CREAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_CREAR_PERIODICIDADCREARPATRDESCRIPCION);
        }
    }
}
