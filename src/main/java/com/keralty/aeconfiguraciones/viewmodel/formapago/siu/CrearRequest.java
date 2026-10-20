package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long formaPagocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String formaPagocrearpatrDescripcion;

    @JsonProperty("p_atrabrirurl")
    private Boolean formaPagocrearpatrAbrirURL;

    @JsonProperty("p_atresvisible")
    private Boolean formaPagocrearpatrEsVisible;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagocrearpatrCodigo();
        checkFormaPagocrearpatrDescripcion();
        checkFormaPagocrearpatrAbrirURL();
        checkFormaPagocrearpatrEsVisible();
    }

    @JsonIgnore
    public Long getFormaPagocrearpatrCodigo() {
        return this.formaPagocrearpatrCodigo;
    }
    
    public void setFormaPagocrearpatrCodigo(Long formaPagocrearpatrCodigo) {
        this.formaPagocrearpatrCodigo = formaPagocrearpatrCodigo;
    }

    private void checkFormaPagocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getFormaPagocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_CREAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_CREAR_FORMAPAGOCREARPATRCODIGO, FormaPagoConstants.SERV_ALIAS_CREAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_CREAR_FORMAPAGOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getFormaPagocrearpatrDescripcion() {
        return this.formaPagocrearpatrDescripcion;
    }
    
    public void setFormaPagocrearpatrDescripcion(String formaPagocrearpatrDescripcion) {
        this.formaPagocrearpatrDescripcion = formaPagocrearpatrDescripcion;
    }

    private void checkFormaPagocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getFormaPagocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_CREAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_CREAR_FORMAPAGOCREARPATRDESCRIPCION, FormaPagoConstants.SERV_ALIAS_CREAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_CREAR_FORMAPAGOCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getFormaPagocrearpatrAbrirURL() {
        return this.formaPagocrearpatrAbrirURL;
    }
    
    public void setFormaPagocrearpatrAbrirURL(Boolean formaPagocrearpatrAbrirURL) {
        this.formaPagocrearpatrAbrirURL = formaPagocrearpatrAbrirURL;
    }

    private void checkFormaPagocrearpatrAbrirURL() throws NotNullArgumentException {
        if(this.getFormaPagocrearpatrAbrirURL() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_CREAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_CREAR_FORMAPAGOCREARPATRABRIRURL, FormaPagoConstants.SERV_ALIAS_CREAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_CREAR_FORMAPAGOCREARPATRABRIRURL);
        }
    }

    @JsonIgnore
    public Boolean getFormaPagocrearpatrEsVisible() {
        return this.formaPagocrearpatrEsVisible;
    }
    
    public void setFormaPagocrearpatrEsVisible(Boolean formaPagocrearpatrEsVisible) {
        this.formaPagocrearpatrEsVisible = formaPagocrearpatrEsVisible;
    }

    private void checkFormaPagocrearpatrEsVisible() throws NotNullArgumentException {
        if(this.getFormaPagocrearpatrEsVisible() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_CREAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_CREAR_FORMAPAGOCREARPATRESVISIBLE, FormaPagoConstants.SERV_ALIAS_CREAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_CREAR_FORMAPAGOCREARPATRESVISIBLE);
        }
    }
}
