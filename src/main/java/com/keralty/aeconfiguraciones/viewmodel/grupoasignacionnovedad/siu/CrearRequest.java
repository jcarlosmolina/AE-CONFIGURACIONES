package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class CrearRequest {

    @JsonProperty("p_agrcausal_oid")
    private CausalOid grupoAsignacionNovedadcrearpagrCausal;
    
    @JsonIgnore
    private Causal grupoAsignacionNovedadcrearpagrCausalInstance;

    @JsonProperty("p_agrgrupousuarios_oid")
    private GrupoAsignacionOid grupoAsignacionNovedadcrearpagrGrupoUsuarios;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionNovedadcrearpagrGrupoUsuariosInstance;

    @JsonProperty("p_agrdominio_oid")
    private DominioOid grupoAsignacionNovedadcrearpagrDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionNovedadcrearpagrDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadcrearpagrCausal();
        checkGrupoAsignacionNovedadcrearpagrDominio();
    }

    @JsonIgnore
    public CausalOid getGrupoAsignacionNovedadcrearpagrCausal() {
        return this.grupoAsignacionNovedadcrearpagrCausal;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrCausal(CausalOid grupoAsignacionNovedadcrearpagrCausal) {
        this.grupoAsignacionNovedadcrearpagrCausal = grupoAsignacionNovedadcrearpagrCausal;
    }

    @JsonIgnore
    public Causal getGrupoAsignacionNovedadcrearpagrCausalInstance() {
        return this.grupoAsignacionNovedadcrearpagrCausalInstance;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrCausalInstance(Causal grupoAsignacionNovedadcrearpagrCausal) {
        this.grupoAsignacionNovedadcrearpagrCausalInstance = grupoAsignacionNovedadcrearpagrCausal;
        this.grupoAsignacionNovedadcrearpagrCausal = grupoAsignacionNovedadcrearpagrCausal != null ? grupoAsignacionNovedadcrearpagrCausal.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadcrearpagrCausal() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadcrearpagrCausalInstance() == null || this.getGrupoAsignacionNovedadcrearpagrCausalInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_CREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_CREAR_GRUPOASIGNACIONNOVEDADCREARPAGRCAUSAL, GrupoAsignacionNovedadConstants.SERV_ALIAS_CREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_CREAR_GRUPOASIGNACIONNOVEDADCREARPAGRCAUSAL);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionNovedadcrearpagrGrupoUsuarios() {
        return this.grupoAsignacionNovedadcrearpagrGrupoUsuarios;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrGrupoUsuarios(GrupoAsignacionOid grupoAsignacionNovedadcrearpagrGrupoUsuarios) {
        this.grupoAsignacionNovedadcrearpagrGrupoUsuarios = grupoAsignacionNovedadcrearpagrGrupoUsuarios;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionNovedadcrearpagrGrupoUsuariosInstance() {
        return this.grupoAsignacionNovedadcrearpagrGrupoUsuariosInstance;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrGrupoUsuariosInstance(GrupoAsignacion grupoAsignacionNovedadcrearpagrGrupoUsuarios) {
        this.grupoAsignacionNovedadcrearpagrGrupoUsuariosInstance = grupoAsignacionNovedadcrearpagrGrupoUsuarios;
        this.grupoAsignacionNovedadcrearpagrGrupoUsuarios = grupoAsignacionNovedadcrearpagrGrupoUsuarios != null ? grupoAsignacionNovedadcrearpagrGrupoUsuarios.getOid() : null;
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionNovedadcrearpagrDominio() {
        return this.grupoAsignacionNovedadcrearpagrDominio;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrDominio(DominioOid grupoAsignacionNovedadcrearpagrDominio) {
        this.grupoAsignacionNovedadcrearpagrDominio = grupoAsignacionNovedadcrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionNovedadcrearpagrDominioInstance() {
        return this.grupoAsignacionNovedadcrearpagrDominioInstance;
    }
    
    public void setGrupoAsignacionNovedadcrearpagrDominioInstance(Dominio grupoAsignacionNovedadcrearpagrDominio) {
        this.grupoAsignacionNovedadcrearpagrDominioInstance = grupoAsignacionNovedadcrearpagrDominio;
        this.grupoAsignacionNovedadcrearpagrDominio = grupoAsignacionNovedadcrearpagrDominio != null ? grupoAsignacionNovedadcrearpagrDominio.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadcrearpagrDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadcrearpagrDominioInstance() == null || this.getGrupoAsignacionNovedadcrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_CREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_CREAR_GRUPOASIGNACIONNOVEDADCREARPAGRDOMINIO, GrupoAsignacionNovedadConstants.SERV_ALIAS_CREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_CREAR_GRUPOASIGNACIONNOVEDADCREARPAGRDOMINIO);
        }
    }
}
