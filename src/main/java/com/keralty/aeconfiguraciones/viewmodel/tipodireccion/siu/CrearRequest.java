package com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long tipoDireccioncrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String tipoDireccioncrearpatrDescripcion;

    @JsonProperty("p_atresresidencia")
    private Boolean tipoDireccioncrearpatrEsResidencia;

    @JsonProperty("p_atrescorrespondencia")
    private Boolean tipoDireccioncrearpatrEsCorrespondencia;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoDireccioncrearpatrCodigo();
        checkTipoDireccioncrearpatrDescripcion();
        checkTipoDireccioncrearpatrEsResidencia();
        checkTipoDireccioncrearpatrEsCorrespondencia();
    }

    @JsonIgnore
    public Long getTipoDireccioncrearpatrCodigo() {
        return this.tipoDireccioncrearpatrCodigo;
    }
    
    public void setTipoDireccioncrearpatrCodigo(Long tipoDireccioncrearpatrCodigo) {
        this.tipoDireccioncrearpatrCodigo = tipoDireccioncrearpatrCodigo;
    }

    private void checkTipoDireccioncrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTipoDireccioncrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_CREAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_CREAR_TIPODIRECCIONCREARPATRCODIGO, TipoDireccionConstants.SERV_ALIAS_CREAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_CREAR_TIPODIRECCIONCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTipoDireccioncrearpatrDescripcion() {
        return this.tipoDireccioncrearpatrDescripcion;
    }
    
    public void setTipoDireccioncrearpatrDescripcion(String tipoDireccioncrearpatrDescripcion) {
        this.tipoDireccioncrearpatrDescripcion = tipoDireccioncrearpatrDescripcion;
    }

    private void checkTipoDireccioncrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoDireccioncrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_CREAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_CREAR_TIPODIRECCIONCREARPATRDESCRIPCION, TipoDireccionConstants.SERV_ALIAS_CREAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_CREAR_TIPODIRECCIONCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getTipoDireccioncrearpatrEsResidencia() {
        return this.tipoDireccioncrearpatrEsResidencia;
    }
    
    public void setTipoDireccioncrearpatrEsResidencia(Boolean tipoDireccioncrearpatrEsResidencia) {
        this.tipoDireccioncrearpatrEsResidencia = tipoDireccioncrearpatrEsResidencia;
    }

    private void checkTipoDireccioncrearpatrEsResidencia() throws NotNullArgumentException {
        if(this.getTipoDireccioncrearpatrEsResidencia() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_CREAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_CREAR_TIPODIRECCIONCREARPATRESRESIDENCIA, TipoDireccionConstants.SERV_ALIAS_CREAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_CREAR_TIPODIRECCIONCREARPATRESRESIDENCIA);
        }
    }

    @JsonIgnore
    public Boolean getTipoDireccioncrearpatrEsCorrespondencia() {
        return this.tipoDireccioncrearpatrEsCorrespondencia;
    }
    
    public void setTipoDireccioncrearpatrEsCorrespondencia(Boolean tipoDireccioncrearpatrEsCorrespondencia) {
        this.tipoDireccioncrearpatrEsCorrespondencia = tipoDireccioncrearpatrEsCorrespondencia;
    }

    private void checkTipoDireccioncrearpatrEsCorrespondencia() throws NotNullArgumentException {
        if(this.getTipoDireccioncrearpatrEsCorrespondencia() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_CREAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_CREAR_TIPODIRECCIONCREARPATRESCORRESPONDENCIA, TipoDireccionConstants.SERV_ALIAS_CREAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_CREAR_TIPODIRECCIONCREARPATRESCORRESPONDENCIA);
        }
    }
}
