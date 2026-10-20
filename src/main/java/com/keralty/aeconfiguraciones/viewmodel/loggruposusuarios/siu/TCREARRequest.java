package com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogGruposUsuariosConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrdominio_oid")
    private DominioOid logGruposUsuariosTCREARptpagrDominio;
    
    @JsonIgnore
    private Dominio logGruposUsuariosTCREARptpagrDominioInstance;

    @JsonProperty("pt_p_atrtipogrupo")
    private Long logGruposUsuariosTCREARptpatrTipoGrupo;

    @JsonProperty("pt_p_atraccion")
    private String logGruposUsuariosTCREARptpatrAccion;

    @JsonProperty("pt_p_atrgrupo")
    private String logGruposUsuariosTCREARptpatrGrupo;

    public void checkArguments() throws NotNullArgumentException {
        checkLogGruposUsuariosTCREARptpagrDominio();
        checkLogGruposUsuariosTCREARptpatrTipoGrupo();
        checkLogGruposUsuariosTCREARptpatrAccion();
        checkLogGruposUsuariosTCREARptpatrGrupo();
    }

    @JsonIgnore
    public DominioOid getLogGruposUsuariosTCREARptpagrDominio() {
        return this.logGruposUsuariosTCREARptpagrDominio;
    }
    
    public void setLogGruposUsuariosTCREARptpagrDominio(DominioOid logGruposUsuariosTCREARptpagrDominio) {
        this.logGruposUsuariosTCREARptpagrDominio = logGruposUsuariosTCREARptpagrDominio;
    }

    @JsonIgnore
    public Dominio getLogGruposUsuariosTCREARptpagrDominioInstance() {
        return this.logGruposUsuariosTCREARptpagrDominioInstance;
    }
    
    public void setLogGruposUsuariosTCREARptpagrDominioInstance(Dominio logGruposUsuariosTCREARptpagrDominio) {
        this.logGruposUsuariosTCREARptpagrDominioInstance = logGruposUsuariosTCREARptpagrDominio;
        this.logGruposUsuariosTCREARptpagrDominio = logGruposUsuariosTCREARptpagrDominio != null ? logGruposUsuariosTCREARptpagrDominio.getOid() : null;
    }

    private void checkLogGruposUsuariosTCREARptpagrDominio() throws NotNullArgumentException {
        if(this.getLogGruposUsuariosTCREARptpagrDominioInstance() == null || this.getLogGruposUsuariosTCREARptpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_TCREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPAGRDOMINIO, LogGruposUsuariosConstants.SERV_ALIAS_TCREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Long getLogGruposUsuariosTCREARptpatrTipoGrupo() {
        return this.logGruposUsuariosTCREARptpatrTipoGrupo;
    }
    
    public void setLogGruposUsuariosTCREARptpatrTipoGrupo(Long logGruposUsuariosTCREARptpatrTipoGrupo) {
        this.logGruposUsuariosTCREARptpatrTipoGrupo = logGruposUsuariosTCREARptpatrTipoGrupo;
    }

    private void checkLogGruposUsuariosTCREARptpatrTipoGrupo() throws NotNullArgumentException {
        if(this.getLogGruposUsuariosTCREARptpatrTipoGrupo() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_TCREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRTIPOGRUPO, LogGruposUsuariosConstants.SERV_ALIAS_TCREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRTIPOGRUPO);
        }
    }

    @JsonIgnore
    public String getLogGruposUsuariosTCREARptpatrAccion() {
        return this.logGruposUsuariosTCREARptpatrAccion;
    }
    
    public void setLogGruposUsuariosTCREARptpatrAccion(String logGruposUsuariosTCREARptpatrAccion) {
        this.logGruposUsuariosTCREARptpatrAccion = logGruposUsuariosTCREARptpatrAccion;
    }

    private void checkLogGruposUsuariosTCREARptpatrAccion() throws NotNullArgumentException {
        if(this.getLogGruposUsuariosTCREARptpatrAccion() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_TCREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRACCION, LogGruposUsuariosConstants.SERV_ALIAS_TCREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRACCION);
        }
    }

    @JsonIgnore
    public String getLogGruposUsuariosTCREARptpatrGrupo() {
        return this.logGruposUsuariosTCREARptpatrGrupo;
    }
    
    public void setLogGruposUsuariosTCREARptpatrGrupo(String logGruposUsuariosTCREARptpatrGrupo) {
        this.logGruposUsuariosTCREARptpatrGrupo = logGruposUsuariosTCREARptpatrGrupo;
    }

    private void checkLogGruposUsuariosTCREARptpatrGrupo() throws NotNullArgumentException {
        if(this.getLogGruposUsuariosTCREARptpatrGrupo() == null) {
            throw new NotNullArgumentException(LogGruposUsuariosConstants.SERV_ID_TCREAR, LogGruposUsuariosConstants.CLASS_ID, LogGruposUsuariosConstants.ARG_ID_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRGRUPO, LogGruposUsuariosConstants.SERV_ALIAS_TCREAR, LogGruposUsuariosConstants.CLASS_ALIAS, LogGruposUsuariosConstants.ARG_ALIAS_TCREAR_LOGGRUPOSUSUARIOSTCREARPTPATRGRUPO);
        }
    }
}
