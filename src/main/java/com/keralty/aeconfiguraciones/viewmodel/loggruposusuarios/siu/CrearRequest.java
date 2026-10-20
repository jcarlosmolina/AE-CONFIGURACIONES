package com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogGruposUsuariosConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class CrearRequest {

    @JsonProperty("p_agrdominio_oid")
    private DominioOid logGruposUsuarioscrearpagrDominio;
    
    @JsonIgnore
    private Dominio logGruposUsuarioscrearpagrDominioInstance;

    @JsonProperty("p_atrloginfuncionario")
    private String logGruposUsuarioscrearpatrLoginFuncionario;

    @JsonProperty("p_atrtipogrupo")
    private Long logGruposUsuarioscrearpatrTipoGrupo;

    @JsonProperty("p_atraccion")
    private String logGruposUsuarioscrearpatrAccion;

    @JsonProperty("p_atrgrupo")
    private String logGruposUsuarioscrearpatrGrupo;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGruposUsuarioscrearpagrDominio();
        checkLogGruposUsuarioscrearpatrLoginFuncionario();
        checkLogGruposUsuarioscrearpatrTipoGrupo();
        checkLogGruposUsuarioscrearpatrAccion();
        checkLogGruposUsuarioscrearpatrGrupo();
    }

    @JsonIgnore
    public DominioOid getLogGruposUsuarioscrearpagrDominio() {
        return this.logGruposUsuarioscrearpagrDominio;
    }
    
    public void setLogGruposUsuarioscrearpagrDominio(DominioOid logGruposUsuarioscrearpagrDominio) {
        this.logGruposUsuarioscrearpagrDominio = logGruposUsuarioscrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogGruposUsuarioscrearpagrDominioInstance() {
        return this.logGruposUsuarioscrearpagrDominioInstance;
    }
    
    public void setLogGruposUsuarioscrearpagrDominioInstance(Dominio logGruposUsuarioscrearpagrDominio) {
        this.logGruposUsuarioscrearpagrDominioInstance = logGruposUsuarioscrearpagrDominio;
        this.logGruposUsuarioscrearpagrDominio = logGruposUsuarioscrearpagrDominio != null ? logGruposUsuarioscrearpagrDominio.getOid() : null;
    }

    private void checkLogGruposUsuarioscrearpagrDominio() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioscrearpagrDominioInstance() == null || this.getLogGruposUsuarioscrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_CREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_CREAR_LOGGRUPOSUSUARIOSCREARPAGRDOMINIO, LogGruposUsuariosConstants.SERV_ALIAS_CREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_CREAR_LOGGRUPOSUSUARIOSCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getLogGruposUsuarioscrearpatrLoginFuncionario() {
        return this.logGruposUsuarioscrearpatrLoginFuncionario;
    }
    
    public void setLogGruposUsuarioscrearpatrLoginFuncionario(String logGruposUsuarioscrearpatrLoginFuncionario) {
        this.logGruposUsuarioscrearpatrLoginFuncionario = logGruposUsuarioscrearpatrLoginFuncionario;
    }

    private void checkLogGruposUsuarioscrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioscrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_CREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_CREAR_LOGGRUPOSUSUARIOSCREARPATRLOGINFUNCIONARIO, LogGruposUsuariosConstants.SERV_ALIAS_CREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_CREAR_LOGGRUPOSUSUARIOSCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public Long getLogGruposUsuarioscrearpatrTipoGrupo() {
        return this.logGruposUsuarioscrearpatrTipoGrupo;
    }
    
    public void setLogGruposUsuarioscrearpatrTipoGrupo(Long logGruposUsuarioscrearpatrTipoGrupo) {
        this.logGruposUsuarioscrearpatrTipoGrupo = logGruposUsuarioscrearpatrTipoGrupo;
    }

    private void checkLogGruposUsuarioscrearpatrTipoGrupo() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioscrearpatrTipoGrupo() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_CREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_CREAR_LOGGRUPOSUSUARIOSCREARPATRTIPOGRUPO, LogGruposUsuariosConstants.SERV_ALIAS_CREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_CREAR_LOGGRUPOSUSUARIOSCREARPATRTIPOGRUPO);
        }
    }

    @JsonIgnore
    public String getLogGruposUsuarioscrearpatrAccion() {
        return this.logGruposUsuarioscrearpatrAccion;
    }
    
    public void setLogGruposUsuarioscrearpatrAccion(String logGruposUsuarioscrearpatrAccion) {
        this.logGruposUsuarioscrearpatrAccion = logGruposUsuarioscrearpatrAccion;
    }

    private void checkLogGruposUsuarioscrearpatrAccion() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioscrearpatrAccion() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_CREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_CREAR_LOGGRUPOSUSUARIOSCREARPATRACCION, LogGruposUsuariosConstants.SERV_ALIAS_CREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_CREAR_LOGGRUPOSUSUARIOSCREARPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogGruposUsuarioscrearpatrGrupo() {
        return this.logGruposUsuarioscrearpatrGrupo;
    }
    
    public void setLogGruposUsuarioscrearpatrGrupo(String logGruposUsuarioscrearpatrGrupo) {
        this.logGruposUsuarioscrearpatrGrupo = logGruposUsuarioscrearpatrGrupo;
    }

    private void checkLogGruposUsuarioscrearpatrGrupo() throws NotNullArgumentException {
        if(this.getLogGruposUsuarioscrearpatrGrupo() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_CREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_CREAR_LOGGRUPOSUSUARIOSCREARPATRGRUPO, LogGruposUsuariosConstants.SERV_ALIAS_CREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_CREAR_LOGGRUPOSUSUARIOSCREARPATRGRUPO);
        }
    }
}
