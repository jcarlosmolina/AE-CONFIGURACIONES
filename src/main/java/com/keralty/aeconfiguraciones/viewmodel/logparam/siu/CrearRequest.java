package com.keralty.aeconfiguraciones.viewmodel.logparam.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogParamConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamConfigOid;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_agrparamconfig_oid")
    private ParamConfigOid logParamcrearpagrParamConfig;
    
    @JsonIgnore
    private ParamConfig logParamcrearpagrParamConfigInstance;

    @JsonProperty("p_atrfecha")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date logParamcrearpatrFecha;

    @JsonProperty("p_atrusuario")
    private String logParamcrearpatrUsuario;

    @JsonProperty("p_atrvaloranterior")
    private String logParamcrearpatrValorAnterior;

    @JsonProperty("p_atrnuevovalor")
    private String logParamcrearpatrNuevoValor;

    @JsonProperty("p_atrnombreusuario")
    private String logParamcrearpatrNombreUsuario;

    public void checkArguments() throws NotNullArgumentException {
        checkLogParamcrearpagrParamConfig();
        checkLogParamcrearpatrFecha();
        checkLogParamcrearpatrUsuario();
        checkLogParamcrearpatrValorAnterior();
        checkLogParamcrearpatrNuevoValor();
        checkLogParamcrearpatrNombreUsuario();
    }

    @JsonIgnore
    public ParamConfigOid getLogParamcrearpagrParamConfig() {
        return this.logParamcrearpagrParamConfig;
    }
    
    public void setLogParamcrearpagrParamConfig(ParamConfigOid logParamcrearpagrParamConfig) {
        this.logParamcrearpagrParamConfig = logParamcrearpagrParamConfig;
    }

    @JsonIgnore
    public ParamConfig getLogParamcrearpagrParamConfigInstance() {
        return this.logParamcrearpagrParamConfigInstance;
    }
    
    public void setLogParamcrearpagrParamConfigInstance(ParamConfig logParamcrearpagrParamConfig) {
        this.logParamcrearpagrParamConfigInstance = logParamcrearpagrParamConfig;
        this.logParamcrearpagrParamConfig = logParamcrearpagrParamConfig != null ? logParamcrearpagrParamConfig.getOid() : null;
    }

    private void checkLogParamcrearpagrParamConfig() throws NotNullArgumentException {
        if(this.getLogParamcrearpagrParamConfigInstance() == null || this.getLogParamcrearpagrParamConfigInstance().isNull()) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPAGRPARAMCONFIG, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPAGRPARAMCONFIG);
        }
    }

    @JsonIgnore
    public Date getLogParamcrearpatrFecha() {
        return this.logParamcrearpatrFecha;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setLogParamcrearpatrFecha(Date logParamcrearpatrFecha) {
        this.logParamcrearpatrFecha = logParamcrearpatrFecha;
    }

    private void checkLogParamcrearpatrFecha() throws NotNullArgumentException {
        if(this.getLogParamcrearpatrFecha() == null) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPATRFECHA, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPATRFECHA);
        }
    }

    @JsonIgnore
    public String getLogParamcrearpatrUsuario() {
        return this.logParamcrearpatrUsuario;
    }
    
    public void setLogParamcrearpatrUsuario(String logParamcrearpatrUsuario) {
        this.logParamcrearpatrUsuario = logParamcrearpatrUsuario;
    }

    private void checkLogParamcrearpatrUsuario() throws NotNullArgumentException {
        if(this.getLogParamcrearpatrUsuario() == null) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPATRUSUARIO, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPATRUSUARIO);
        }
    }

    @JsonIgnore
    public String getLogParamcrearpatrValorAnterior() {
        return this.logParamcrearpatrValorAnterior;
    }
    
    public void setLogParamcrearpatrValorAnterior(String logParamcrearpatrValorAnterior) {
        this.logParamcrearpatrValorAnterior = logParamcrearpatrValorAnterior;
    }

    private void checkLogParamcrearpatrValorAnterior() throws NotNullArgumentException {
        if(this.getLogParamcrearpatrValorAnterior() == null) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPATRVALORANTERIOR, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPATRVALORANTERIOR);
        }
    }

    @JsonIgnore
    public String getLogParamcrearpatrNuevoValor() {
        return this.logParamcrearpatrNuevoValor;
    }
    
    public void setLogParamcrearpatrNuevoValor(String logParamcrearpatrNuevoValor) {
        this.logParamcrearpatrNuevoValor = logParamcrearpatrNuevoValor;
    }

    private void checkLogParamcrearpatrNuevoValor() throws NotNullArgumentException {
        if(this.getLogParamcrearpatrNuevoValor() == null) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPATRNUEVOVALOR, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPATRNUEVOVALOR);
        }
    }

    @JsonIgnore
    public String getLogParamcrearpatrNombreUsuario() {
        return this.logParamcrearpatrNombreUsuario;
    }
    
    public void setLogParamcrearpatrNombreUsuario(String logParamcrearpatrNombreUsuario) {
        this.logParamcrearpatrNombreUsuario = logParamcrearpatrNombreUsuario;
    }

    private void checkLogParamcrearpatrNombreUsuario() throws NotNullArgumentException {
        if(this.getLogParamcrearpatrNombreUsuario() == null) {
            throw new NotNullArgumentException(LogParamConstants.SERV_ID_CREAR, LogParamConstants.CLASS_ID, LogParamConstants.ARG_ID_CREAR_LOGPARAMCREARPATRNOMBREUSUARIO, LogParamConstants.SERV_ALIAS_CREAR, LogParamConstants.CLASS_ALIAS, LogParamConstants.ARG_ALIAS_CREAR_LOGPARAMCREARPATRNOMBREUSUARIO);
        }
    }
}
