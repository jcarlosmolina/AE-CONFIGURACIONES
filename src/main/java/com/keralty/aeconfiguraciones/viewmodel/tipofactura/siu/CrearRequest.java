package com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoFacturaConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private String tipoFacturacrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String tipoFacturacrearpatrDescripcion;

    @JsonProperty("p_atresemail")
    private Boolean tipoFacturacrearpatrEsEmail;

    @JsonProperty("p_atrescelular")
    private Boolean tipoFacturacrearpatrEsCelular;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoFacturacrearpatrCodigo();
        checkTipoFacturacrearpatrDescripcion();
        checkTipoFacturacrearpatrEsEmail();
        checkTipoFacturacrearpatrEsCelular();
    }

    @JsonIgnore
    public String getTipoFacturacrearpatrCodigo() {
        return this.tipoFacturacrearpatrCodigo;
    }
    
    public void setTipoFacturacrearpatrCodigo(String tipoFacturacrearpatrCodigo) {
        this.tipoFacturacrearpatrCodigo = tipoFacturacrearpatrCodigo;
    }

    private void checkTipoFacturacrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTipoFacturacrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_CREAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_CREAR_TIPOFACTURACREARPATRCODIGO, TipoFacturaConstants.SERV_ALIAS_CREAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_CREAR_TIPOFACTURACREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTipoFacturacrearpatrDescripcion() {
        return this.tipoFacturacrearpatrDescripcion;
    }
    
    public void setTipoFacturacrearpatrDescripcion(String tipoFacturacrearpatrDescripcion) {
        this.tipoFacturacrearpatrDescripcion = tipoFacturacrearpatrDescripcion;
    }

    private void checkTipoFacturacrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoFacturacrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_CREAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_CREAR_TIPOFACTURACREARPATRDESCRIPCION, TipoFacturaConstants.SERV_ALIAS_CREAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_CREAR_TIPOFACTURACREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getTipoFacturacrearpatrEsEmail() {
        return this.tipoFacturacrearpatrEsEmail;
    }
    
    public void setTipoFacturacrearpatrEsEmail(Boolean tipoFacturacrearpatrEsEmail) {
        this.tipoFacturacrearpatrEsEmail = tipoFacturacrearpatrEsEmail;
    }

    private void checkTipoFacturacrearpatrEsEmail() throws NotNullArgumentException {
        if(this.getTipoFacturacrearpatrEsEmail() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_CREAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_CREAR_TIPOFACTURACREARPATRESEMAIL, TipoFacturaConstants.SERV_ALIAS_CREAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_CREAR_TIPOFACTURACREARPATRESEMAIL);
        }
    }

    @JsonIgnore
    public Boolean getTipoFacturacrearpatrEsCelular() {
        return this.tipoFacturacrearpatrEsCelular;
    }
    
    public void setTipoFacturacrearpatrEsCelular(Boolean tipoFacturacrearpatrEsCelular) {
        this.tipoFacturacrearpatrEsCelular = tipoFacturacrearpatrEsCelular;
    }

    private void checkTipoFacturacrearpatrEsCelular() throws NotNullArgumentException {
        if(this.getTipoFacturacrearpatrEsCelular() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_CREAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_CREAR_TIPOFACTURACREARPATRESCELULAR, TipoFacturaConstants.SERV_ALIAS_CREAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_CREAR_TIPOFACTURACREARPATRESCELULAR);
        }
    }
}
