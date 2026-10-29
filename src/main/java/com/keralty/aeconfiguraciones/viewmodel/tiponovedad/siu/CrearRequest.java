package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoNovedadConstants;

public class CrearRequest {

    @JsonProperty("p_atrtipo")
    private Long tipoNovedadcrearpatrTipo;

    @JsonProperty("p_atraliastipo")
    private String tipoNovedadcrearpatrAliasTipo;

    @JsonProperty("p_atrnombre")
    private String tipoNovedadcrearpatrNombre;

    @JsonProperty("p_atrdescripcion")
    private String tipoNovedadcrearpatrDescripcion;

    @JsonProperty("p_atrcodigolegal")
    private String tipoNovedadcrearpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoNovedadcrearpatrTipo();
        checkTipoNovedadcrearpatrAliasTipo();
        checkTipoNovedadcrearpatrNombre();
        checkTipoNovedadcrearpatrDescripcion();
        checkTipoNovedadcrearpatrCodigoLegal();
    }

    @JsonIgnore
    public Long getTipoNovedadcrearpatrTipo() {
        return this.tipoNovedadcrearpatrTipo;
    }
    
    public void setTipoNovedadcrearpatrTipo(Long tipoNovedadcrearpatrTipo) {
        this.tipoNovedadcrearpatrTipo = tipoNovedadcrearpatrTipo;
    }

    private void checkTipoNovedadcrearpatrTipo() throws NotNullArgumentException {
        if(this.getTipoNovedadcrearpatrTipo() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_CREAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_CREAR_TIPONOVEDADCREARPATRTIPO, TipoNovedadConstants.SERV_ALIAS_CREAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_CREAR_TIPONOVEDADCREARPATRTIPO);
        }
    }

    @JsonIgnore
    public String getTipoNovedadcrearpatrAliasTipo() {
        return this.tipoNovedadcrearpatrAliasTipo;
    }
    
    public void setTipoNovedadcrearpatrAliasTipo(String tipoNovedadcrearpatrAliasTipo) {
        this.tipoNovedadcrearpatrAliasTipo = tipoNovedadcrearpatrAliasTipo;
    }

    private void checkTipoNovedadcrearpatrAliasTipo() throws NotNullArgumentException {
        if(this.getTipoNovedadcrearpatrAliasTipo() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_CREAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_CREAR_TIPONOVEDADCREARPATRALIASTIPO, TipoNovedadConstants.SERV_ALIAS_CREAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_CREAR_TIPONOVEDADCREARPATRALIASTIPO);
        }
    }

    @JsonIgnore
    public String getTipoNovedadcrearpatrNombre() {
        return this.tipoNovedadcrearpatrNombre;
    }
    
    public void setTipoNovedadcrearpatrNombre(String tipoNovedadcrearpatrNombre) {
        this.tipoNovedadcrearpatrNombre = tipoNovedadcrearpatrNombre;
    }

    private void checkTipoNovedadcrearpatrNombre() throws NotNullArgumentException {
        if(this.getTipoNovedadcrearpatrNombre() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_CREAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_CREAR_TIPONOVEDADCREARPATRNOMBRE, TipoNovedadConstants.SERV_ALIAS_CREAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_CREAR_TIPONOVEDADCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getTipoNovedadcrearpatrDescripcion() {
        return this.tipoNovedadcrearpatrDescripcion;
    }
    
    public void setTipoNovedadcrearpatrDescripcion(String tipoNovedadcrearpatrDescripcion) {
        this.tipoNovedadcrearpatrDescripcion = tipoNovedadcrearpatrDescripcion;
    }

    private void checkTipoNovedadcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoNovedadcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_CREAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_CREAR_TIPONOVEDADCREARPATRDESCRIPCION, TipoNovedadConstants.SERV_ALIAS_CREAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_CREAR_TIPONOVEDADCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTipoNovedadcrearpatrCodigoLegal() {
        return this.tipoNovedadcrearpatrCodigoLegal;
    }
    
    public void setTipoNovedadcrearpatrCodigoLegal(String tipoNovedadcrearpatrCodigoLegal) {
        this.tipoNovedadcrearpatrCodigoLegal = tipoNovedadcrearpatrCodigoLegal;
    }

    private void checkTipoNovedadcrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTipoNovedadcrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_CREAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_CREAR_TIPONOVEDADCREARPATRCODIGOLEGAL, TipoNovedadConstants.SERV_ALIAS_CREAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_CREAR_TIPONOVEDADCREARPATRCODIGOLEGAL);
        }
    }
}
