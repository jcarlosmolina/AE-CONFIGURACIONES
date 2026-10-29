package com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EntidadFinancieraCConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long entidadFinancieraCcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String entidadFinancieraCcrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEntidadFinancieraCcrearpatrCodigo();
        checkEntidadFinancieraCcrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getEntidadFinancieraCcrearpatrCodigo() {
        return this.entidadFinancieraCcrearpatrCodigo;
    }
    
    public void setEntidadFinancieraCcrearpatrCodigo(Long entidadFinancieraCcrearpatrCodigo) {
        this.entidadFinancieraCcrearpatrCodigo = entidadFinancieraCcrearpatrCodigo;
    }

    private void checkEntidadFinancieraCcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEntidadFinancieraCcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EntidadFinancieraCConstants.SERV_ID_CREAR, EntidadFinancieraCConstants.CLASS_ID, EntidadFinancieraCConstants.ARG_ID_CREAR_ENTIDADFINANCIERACCREARPATRCODIGO, EntidadFinancieraCConstants.SERV_ALIAS_CREAR, EntidadFinancieraCConstants.CLASS_ALIAS, EntidadFinancieraCConstants.ARG_ALIAS_CREAR_ENTIDADFINANCIERACCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEntidadFinancieraCcrearpatrDescripcion() {
        return this.entidadFinancieraCcrearpatrDescripcion;
    }
    
    public void setEntidadFinancieraCcrearpatrDescripcion(String entidadFinancieraCcrearpatrDescripcion) {
        this.entidadFinancieraCcrearpatrDescripcion = entidadFinancieraCcrearpatrDescripcion;
    }

    private void checkEntidadFinancieraCcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEntidadFinancieraCcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EntidadFinancieraCConstants.SERV_ID_CREAR, EntidadFinancieraCConstants.CLASS_ID, EntidadFinancieraCConstants.ARG_ID_CREAR_ENTIDADFINANCIERACCREARPATRDESCRIPCION, EntidadFinancieraCConstants.SERV_ALIAS_CREAR, EntidadFinancieraCConstants.CLASS_ALIAS, EntidadFinancieraCConstants.ARG_ALIAS_CREAR_ENTIDADFINANCIERACCREARPATRDESCRIPCION);
        }
    }
}
