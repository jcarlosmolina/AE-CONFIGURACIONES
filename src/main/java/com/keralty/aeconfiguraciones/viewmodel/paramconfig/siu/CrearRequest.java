package com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamConfigConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private String paramConfigcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String paramConfigcrearpatrDescripcion;

    @JsonProperty("p_atrvalor")
    private String paramConfigcrearpatrValor;

    @JsonProperty("p_atresnumerico")
    private Boolean paramConfigcrearpatrEsNumerico;

    public void checkArguments() throws NotNullArgumentException {
        checkParamConfigcrearpatrCodigo();
        checkParamConfigcrearpatrDescripcion();
        checkParamConfigcrearpatrValor();
        checkParamConfigcrearpatrEsNumerico();
    }

    @JsonIgnore
    public String getParamConfigcrearpatrCodigo() {
        return this.paramConfigcrearpatrCodigo;
    }
    
    public void setParamConfigcrearpatrCodigo(String paramConfigcrearpatrCodigo) {
        this.paramConfigcrearpatrCodigo = paramConfigcrearpatrCodigo;
    }

    private void checkParamConfigcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getParamConfigcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_CREAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_CREAR_PARAMCONFIGCREARPATRCODIGO, ParamConfigConstants.SERV_ALIAS_CREAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_CREAR_PARAMCONFIGCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getParamConfigcrearpatrDescripcion() {
        return this.paramConfigcrearpatrDescripcion;
    }
    
    public void setParamConfigcrearpatrDescripcion(String paramConfigcrearpatrDescripcion) {
        this.paramConfigcrearpatrDescripcion = paramConfigcrearpatrDescripcion;
    }

    private void checkParamConfigcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getParamConfigcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_CREAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_CREAR_PARAMCONFIGCREARPATRDESCRIPCION, ParamConfigConstants.SERV_ALIAS_CREAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_CREAR_PARAMCONFIGCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getParamConfigcrearpatrValor() {
        return this.paramConfigcrearpatrValor;
    }
    
    public void setParamConfigcrearpatrValor(String paramConfigcrearpatrValor) {
        this.paramConfigcrearpatrValor = paramConfigcrearpatrValor;
    }

    private void checkParamConfigcrearpatrValor() throws NotNullArgumentException {
        if(this.getParamConfigcrearpatrValor() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_CREAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_CREAR_PARAMCONFIGCREARPATRVALOR, ParamConfigConstants.SERV_ALIAS_CREAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_CREAR_PARAMCONFIGCREARPATRVALOR);
        }
    }

    @JsonIgnore
    public Boolean getParamConfigcrearpatrEsNumerico() {
        return this.paramConfigcrearpatrEsNumerico;
    }
    
    public void setParamConfigcrearpatrEsNumerico(Boolean paramConfigcrearpatrEsNumerico) {
        this.paramConfigcrearpatrEsNumerico = paramConfigcrearpatrEsNumerico;
    }

    private void checkParamConfigcrearpatrEsNumerico() throws NotNullArgumentException {
        if(this.getParamConfigcrearpatrEsNumerico() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_CREAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_CREAR_PARAMCONFIGCREARPATRESNUMERICO, ParamConfigConstants.SERV_ALIAS_CREAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_CREAR_PARAMCONFIGCREARPATRESNUMERICO);
        }
    }
}
