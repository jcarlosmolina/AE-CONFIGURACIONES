package com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long tipoEntidadCcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String tipoEntidadCcrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoEntidadCcrearpatrCodigo();
        checkTipoEntidadCcrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getTipoEntidadCcrearpatrCodigo() {
        return this.tipoEntidadCcrearpatrCodigo;
    }
    
    public void setTipoEntidadCcrearpatrCodigo(Long tipoEntidadCcrearpatrCodigo) {
        this.tipoEntidadCcrearpatrCodigo = tipoEntidadCcrearpatrCodigo;
    }

    private void checkTipoEntidadCcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTipoEntidadCcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_CREAR, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_CREAR_TIPOENTIDADCCREARPATRCODIGO, TipoEntidadCConstants.SERV_ALIAS_CREAR, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_CREAR_TIPOENTIDADCCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTipoEntidadCcrearpatrDescripcion() {
        return this.tipoEntidadCcrearpatrDescripcion;
    }
    
    public void setTipoEntidadCcrearpatrDescripcion(String tipoEntidadCcrearpatrDescripcion) {
        this.tipoEntidadCcrearpatrDescripcion = tipoEntidadCcrearpatrDescripcion;
    }

    private void checkTipoEntidadCcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoEntidadCcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_CREAR, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_CREAR_TIPOENTIDADCCREARPATRDESCRIPCION, TipoEntidadCConstants.SERV_ALIAS_CREAR, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_CREAR_TIPOENTIDADCCREARPATRDESCRIPCION);
        }
    }
}
