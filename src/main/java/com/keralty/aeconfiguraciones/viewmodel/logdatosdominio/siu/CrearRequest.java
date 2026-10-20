package com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogDatosDominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class CrearRequest {

    @JsonProperty("p_agrdominio_oid")
    private DominioOid logDatosDominiocrearpagrDominio;
    
    @JsonIgnore
    private Dominio logDatosDominiocrearpagrDominioInstance;

    @JsonProperty("p_atrloginfuncionario")
    private String logDatosDominiocrearpatrLoginFuncionario;

    @JsonProperty("p_atrtramiteenlinea")
    private Boolean logDatosDominiocrearpatrTramiteEnLinea;

    @JsonProperty("p_atrvigenciaradicacion")
    private Boolean logDatosDominiocrearpatrVigenciaRadicacion;

    public void checkArguments() throws NotNullArgumentException {
        checkLogDatosDominiocrearpagrDominio();
        checkLogDatosDominiocrearpatrLoginFuncionario();
        checkLogDatosDominiocrearpatrTramiteEnLinea();
        checkLogDatosDominiocrearpatrVigenciaRadicacion();
    }

    @JsonIgnore
    public DominioOid getLogDatosDominiocrearpagrDominio() {
        return this.logDatosDominiocrearpagrDominio;
    }
    
    public void setLogDatosDominiocrearpagrDominio(DominioOid logDatosDominiocrearpagrDominio) {
        this.logDatosDominiocrearpagrDominio = logDatosDominiocrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogDatosDominiocrearpagrDominioInstance() {
        return this.logDatosDominiocrearpagrDominioInstance;
    }
    
    public void setLogDatosDominiocrearpagrDominioInstance(Dominio logDatosDominiocrearpagrDominio) {
        this.logDatosDominiocrearpagrDominioInstance = logDatosDominiocrearpagrDominio;
        this.logDatosDominiocrearpagrDominio = logDatosDominiocrearpagrDominio != null ? logDatosDominiocrearpagrDominio.getOid() : null;
    }

    private void checkLogDatosDominiocrearpagrDominio() throws NotNullArgumentException {
        if(this.getLogDatosDominiocrearpagrDominioInstance() == null || this.getLogDatosDominiocrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_CREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_CREAR_LOGDATOSDOMINIOCREARPAGRDOMINIO, LogDatosDominioConstants.SERV_ALIAS_CREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_CREAR_LOGDATOSDOMINIOCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getLogDatosDominiocrearpatrLoginFuncionario() {
        return this.logDatosDominiocrearpatrLoginFuncionario;
    }
    
    public void setLogDatosDominiocrearpatrLoginFuncionario(String logDatosDominiocrearpatrLoginFuncionario) {
        this.logDatosDominiocrearpatrLoginFuncionario = logDatosDominiocrearpatrLoginFuncionario;
    }

    private void checkLogDatosDominiocrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogDatosDominiocrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_CREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_CREAR_LOGDATOSDOMINIOCREARPATRLOGINFUNCIONARIO, LogDatosDominioConstants.SERV_ALIAS_CREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_CREAR_LOGDATOSDOMINIOCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public Boolean getLogDatosDominiocrearpatrTramiteEnLinea() {
        return this.logDatosDominiocrearpatrTramiteEnLinea;
    }
    
    public void setLogDatosDominiocrearpatrTramiteEnLinea(Boolean logDatosDominiocrearpatrTramiteEnLinea) {
        this.logDatosDominiocrearpatrTramiteEnLinea = logDatosDominiocrearpatrTramiteEnLinea;
    }

    private void checkLogDatosDominiocrearpatrTramiteEnLinea() throws NotNullArgumentException {
        if(this.getLogDatosDominiocrearpatrTramiteEnLinea() == null) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_CREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_CREAR_LOGDATOSDOMINIOCREARPATRTRAMITEENLINEA, LogDatosDominioConstants.SERV_ALIAS_CREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_CREAR_LOGDATOSDOMINIOCREARPATRTRAMITEENLINEA);
        }
    }

    @JsonIgnore
    public Boolean getLogDatosDominiocrearpatrVigenciaRadicacion() {
        return this.logDatosDominiocrearpatrVigenciaRadicacion;
    }
    
    public void setLogDatosDominiocrearpatrVigenciaRadicacion(Boolean logDatosDominiocrearpatrVigenciaRadicacion) {
        this.logDatosDominiocrearpatrVigenciaRadicacion = logDatosDominiocrearpatrVigenciaRadicacion;
    }

    private void checkLogDatosDominiocrearpatrVigenciaRadicacion() throws NotNullArgumentException {
        if(this.getLogDatosDominiocrearpatrVigenciaRadicacion() == null) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_CREAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_CREAR_LOGDATOSDOMINIOCREARPATRVIGENCIARADICACION, LogDatosDominioConstants.SERV_ALIAS_CREAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_CREAR_LOGDATOSDOMINIOCREARPATRVIGENCIARADICACION);
        }
    }
}
