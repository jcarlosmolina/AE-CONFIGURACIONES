package com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long tipoSucursalCcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String tipoSucursalCcrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoSucursalCcrearpatrCodigo();
        checkTipoSucursalCcrearpatrDescripcion();
    }

    @JsonIgnore
    public Long getTipoSucursalCcrearpatrCodigo() {
        return this.tipoSucursalCcrearpatrCodigo;
    }
    
    public void setTipoSucursalCcrearpatrCodigo(Long tipoSucursalCcrearpatrCodigo) {
        this.tipoSucursalCcrearpatrCodigo = tipoSucursalCcrearpatrCodigo;
    }

    private void checkTipoSucursalCcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTipoSucursalCcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TipoSucursalCConstants.SERV_ID_CREAR, TipoSucursalCConstants.CLASS_ID, TipoSucursalCConstants.ARG_ID_CREAR_TIPOSUCURSALCCREARPATRCODIGO, TipoSucursalCConstants.SERV_ALIAS_CREAR, TipoSucursalCConstants.CLASS_ALIAS, TipoSucursalCConstants.ARG_ALIAS_CREAR_TIPOSUCURSALCCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTipoSucursalCcrearpatrDescripcion() {
        return this.tipoSucursalCcrearpatrDescripcion;
    }
    
    public void setTipoSucursalCcrearpatrDescripcion(String tipoSucursalCcrearpatrDescripcion) {
        this.tipoSucursalCcrearpatrDescripcion = tipoSucursalCcrearpatrDescripcion;
    }

    private void checkTipoSucursalCcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoSucursalCcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoSucursalCConstants.SERV_ID_CREAR, TipoSucursalCConstants.CLASS_ID, TipoSucursalCConstants.ARG_ID_CREAR_TIPOSUCURSALCCREARPATRDESCRIPCION, TipoSucursalCConstants.SERV_ALIAS_CREAR, TipoSucursalCConstants.CLASS_ALIAS, TipoSucursalCConstants.ARG_ALIAS_CREAR_TIPOSUCURSALCCREARPATRDESCRIPCION);
        }
    }
}
