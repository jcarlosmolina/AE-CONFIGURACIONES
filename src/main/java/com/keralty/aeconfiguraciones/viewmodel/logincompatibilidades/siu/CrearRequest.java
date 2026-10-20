package com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogIncompatibilidadesConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class CrearRequest {

    @JsonProperty("p_agrdominio_oid")
    private DominioOid logIncompatibilidadescrearpagrDominio;
    
    @JsonIgnore
    private Dominio logIncompatibilidadescrearpagrDominioInstance;

    @JsonProperty("p_atrloginfuncionario")
    private String logIncompatibilidadescrearpatrLoginFuncionario;

    @JsonProperty("p_atraccion")
    private String logIncompatibilidadescrearpatrAccion;

    @JsonProperty("p_atrnomdominio")
    private String logIncompatibilidadescrearpatrNomDominio;

    public void checkArguments() throws NotNullArgumentException {
        checkLogIncompatibilidadescrearpagrDominio();
        checkLogIncompatibilidadescrearpatrLoginFuncionario();
        checkLogIncompatibilidadescrearpatrAccion();
        checkLogIncompatibilidadescrearpatrNomDominio();
    }

    @JsonIgnore
    public DominioOid getLogIncompatibilidadescrearpagrDominio() {
        return this.logIncompatibilidadescrearpagrDominio;
    }
    
    public void setLogIncompatibilidadescrearpagrDominio(DominioOid logIncompatibilidadescrearpagrDominio) {
        this.logIncompatibilidadescrearpagrDominio = logIncompatibilidadescrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogIncompatibilidadescrearpagrDominioInstance() {
        return this.logIncompatibilidadescrearpagrDominioInstance;
    }
    
    public void setLogIncompatibilidadescrearpagrDominioInstance(Dominio logIncompatibilidadescrearpagrDominio) {
        this.logIncompatibilidadescrearpagrDominioInstance = logIncompatibilidadescrearpagrDominio;
        this.logIncompatibilidadescrearpagrDominio = logIncompatibilidadescrearpagrDominio != null ? logIncompatibilidadescrearpagrDominio.getOid() : null;
    }

    private void checkLogIncompatibilidadescrearpagrDominio() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadescrearpagrDominioInstance() == null || this.getLogIncompatibilidadescrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_CREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_CREAR_LOGINCOMPATIBILIDADESCREARPAGRDOMINIO, LogIncompatibilidadesConstants.SERV_ALIAS_CREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_CREAR_LOGINCOMPATIBILIDADESCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getLogIncompatibilidadescrearpatrLoginFuncionario() {
        return this.logIncompatibilidadescrearpatrLoginFuncionario;
    }
    
    public void setLogIncompatibilidadescrearpatrLoginFuncionario(String logIncompatibilidadescrearpatrLoginFuncionario) {
        this.logIncompatibilidadescrearpatrLoginFuncionario = logIncompatibilidadescrearpatrLoginFuncionario;
    }

    private void checkLogIncompatibilidadescrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadescrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_CREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_CREAR_LOGINCOMPATIBILIDADESCREARPATRLOGINFUNCIONARIO, LogIncompatibilidadesConstants.SERV_ALIAS_CREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_CREAR_LOGINCOMPATIBILIDADESCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public String getLogIncompatibilidadescrearpatrAccion() {
        return this.logIncompatibilidadescrearpatrAccion;
    }
    
    public void setLogIncompatibilidadescrearpatrAccion(String logIncompatibilidadescrearpatrAccion) {
        this.logIncompatibilidadescrearpatrAccion = logIncompatibilidadescrearpatrAccion;
    }

    private void checkLogIncompatibilidadescrearpatrAccion() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadescrearpatrAccion() == null) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_CREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_CREAR_LOGINCOMPATIBILIDADESCREARPATRACCION, LogIncompatibilidadesConstants.SERV_ALIAS_CREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_CREAR_LOGINCOMPATIBILIDADESCREARPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogIncompatibilidadescrearpatrNomDominio() {
        return this.logIncompatibilidadescrearpatrNomDominio;
    }
    
    public void setLogIncompatibilidadescrearpatrNomDominio(String logIncompatibilidadescrearpatrNomDominio) {
        this.logIncompatibilidadescrearpatrNomDominio = logIncompatibilidadescrearpatrNomDominio;
    }

    private void checkLogIncompatibilidadescrearpatrNomDominio() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadescrearpatrNomDominio() == null) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_CREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_CREAR_LOGINCOMPATIBILIDADESCREARPATRNOMDOMINIO, LogIncompatibilidadesConstants.SERV_ALIAS_CREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_CREAR_LOGINCOMPATIBILIDADESCREARPATRNOMDOMINIO);
        }
    }
}
