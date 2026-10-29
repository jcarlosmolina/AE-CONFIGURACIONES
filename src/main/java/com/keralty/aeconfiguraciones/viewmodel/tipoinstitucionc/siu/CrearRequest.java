package com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoInstitucionCConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long tipoInstitucionCcrearpatrCodigo;

    @JsonProperty("p_atrnombre")
    private String tipoInstitucionCcrearpatrNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoInstitucionCcrearpatrCodigo();
        checkTipoInstitucionCcrearpatrNombre();
    }

    @JsonIgnore
    public Long getTipoInstitucionCcrearpatrCodigo() {
        return this.tipoInstitucionCcrearpatrCodigo;
    }
    
    public void setTipoInstitucionCcrearpatrCodigo(Long tipoInstitucionCcrearpatrCodigo) {
        this.tipoInstitucionCcrearpatrCodigo = tipoInstitucionCcrearpatrCodigo;
    }

    private void checkTipoInstitucionCcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTipoInstitucionCcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TipoInstitucionCConstants.SERV_ID_CREAR, TipoInstitucionCConstants.CLASS_ID, TipoInstitucionCConstants.ARG_ID_CREAR_TIPOINSTITUCIONCCREARPATRCODIGO, TipoInstitucionCConstants.SERV_ALIAS_CREAR, TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ARG_ALIAS_CREAR_TIPOINSTITUCIONCCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTipoInstitucionCcrearpatrNombre() {
        return this.tipoInstitucionCcrearpatrNombre;
    }
    
    public void setTipoInstitucionCcrearpatrNombre(String tipoInstitucionCcrearpatrNombre) {
        this.tipoInstitucionCcrearpatrNombre = tipoInstitucionCcrearpatrNombre;
    }

    private void checkTipoInstitucionCcrearpatrNombre() throws NotNullArgumentException {
        if(this.getTipoInstitucionCcrearpatrNombre() == null) {
            throw new NotNullArgumentException(TipoInstitucionCConstants.SERV_ID_CREAR, TipoInstitucionCConstants.CLASS_ID, TipoInstitucionCConstants.ARG_ID_CREAR_TIPOINSTITUCIONCCREARPATRNOMBRE, TipoInstitucionCConstants.SERV_ALIAS_CREAR, TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ARG_ALIAS_CREAR_TIPOINSTITUCIONCCREARPATRNOMBRE);
        }
    }
}
