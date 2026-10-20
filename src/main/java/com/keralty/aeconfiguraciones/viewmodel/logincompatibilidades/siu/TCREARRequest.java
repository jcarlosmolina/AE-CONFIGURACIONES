package com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogIncompatibilidadesConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrdominio_oid")
    private DominioOid logIncompatibilidadesTCREARptpagrDominio;
    
    @JsonIgnore
    private Dominio logIncompatibilidadesTCREARptpagrDominioInstance;

    @JsonProperty("pt_p_atraccion")
    private String logIncompatibilidadesTCREARptpatrAccion;

    @JsonProperty("pt_p_atrnomdominio")
    private String logIncompatibilidadesTCREARptpatrNomDominio;

    public void checkArguments() throws NotNullArgumentException {
        checkLogIncompatibilidadesTCREARptpagrDominio();
        checkLogIncompatibilidadesTCREARptpatrAccion();
        checkLogIncompatibilidadesTCREARptpatrNomDominio();
    }

    @JsonIgnore
    public DominioOid getLogIncompatibilidadesTCREARptpagrDominio() {
        return this.logIncompatibilidadesTCREARptpagrDominio;
    }
    
    public void setLogIncompatibilidadesTCREARptpagrDominio(DominioOid logIncompatibilidadesTCREARptpagrDominio) {
        this.logIncompatibilidadesTCREARptpagrDominio = logIncompatibilidadesTCREARptpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogIncompatibilidadesTCREARptpagrDominioInstance() {
        return this.logIncompatibilidadesTCREARptpagrDominioInstance;
    }
    
    public void setLogIncompatibilidadesTCREARptpagrDominioInstance(Dominio logIncompatibilidadesTCREARptpagrDominio) {
        this.logIncompatibilidadesTCREARptpagrDominioInstance = logIncompatibilidadesTCREARptpagrDominio;
        this.logIncompatibilidadesTCREARptpagrDominio = logIncompatibilidadesTCREARptpagrDominio != null ? logIncompatibilidadesTCREARptpagrDominio.getOid() : null;
    }

    private void checkLogIncompatibilidadesTCREARptpagrDominio() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadesTCREARptpagrDominioInstance() == null || this.getLogIncompatibilidadesTCREARptpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_TCREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPAGRDOMINIO, LogIncompatibilidadesConstants.SERV_ALIAS_TCREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getLogIncompatibilidadesTCREARptpatrAccion() {
        return this.logIncompatibilidadesTCREARptpatrAccion;
    }
    
    public void setLogIncompatibilidadesTCREARptpatrAccion(String logIncompatibilidadesTCREARptpatrAccion) {
        this.logIncompatibilidadesTCREARptpatrAccion = logIncompatibilidadesTCREARptpatrAccion;
    }

    private void checkLogIncompatibilidadesTCREARptpatrAccion() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadesTCREARptpatrAccion() == null) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_TCREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPATRACCION, LogIncompatibilidadesConstants.SERV_ALIAS_TCREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogIncompatibilidadesTCREARptpatrNomDominio() {
        return this.logIncompatibilidadesTCREARptpatrNomDominio;
    }
    
    public void setLogIncompatibilidadesTCREARptpatrNomDominio(String logIncompatibilidadesTCREARptpatrNomDominio) {
        this.logIncompatibilidadesTCREARptpatrNomDominio = logIncompatibilidadesTCREARptpatrNomDominio;
    }

    private void checkLogIncompatibilidadesTCREARptpatrNomDominio() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadesTCREARptpatrNomDominio() == null) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_TCREAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPATRNOMDOMINIO, LogIncompatibilidadesConstants.SERV_ALIAS_TCREAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_TCREAR_LOGINCOMPATIBILIDADESTCREARPTPATRNOMDOMINIO);
        }
    }
}
