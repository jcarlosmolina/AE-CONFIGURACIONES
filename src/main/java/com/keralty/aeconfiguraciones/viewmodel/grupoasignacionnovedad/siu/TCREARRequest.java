package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrdominio_oid")
    private DominioOid grupoAsignacionNovedadTCREARptpagrDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionNovedadTCREARptpagrDominioInstance;

    @JsonProperty("pt_p_agrcausal_json")
    private String grupoAsignacionNovedadTCREARptpagrCausal;
    
    @JsonIgnore
    private Causal grupoAsignacionNovedadTCREARptpagrCausalInstance;

    @JsonProperty("pt_p_agrgrupousuarios_oid")
    private GrupoAsignacionOid grupoAsignacionNovedadTCREARptpagrGrupoUsuarios;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance;

    @JsonProperty("ptnovedad_json")
    private String grupoAsignacionNovedadTCREARptNovedad;
    
    @JsonIgnore
    private Novedad grupoAsignacionNovedadTCREARptNovedadInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadTCREARptpagrDominio();
        checkGrupoAsignacionNovedadTCREARptpagrCausal();
        checkGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios();
        checkGrupoAsignacionNovedadTCREARptNovedad();
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionNovedadTCREARptpagrDominio() {
        return this.grupoAsignacionNovedadTCREARptpagrDominio;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrDominio(DominioOid grupoAsignacionNovedadTCREARptpagrDominio) {
        this.grupoAsignacionNovedadTCREARptpagrDominio = grupoAsignacionNovedadTCREARptpagrDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionNovedadTCREARptpagrDominioInstance() {
        return this.grupoAsignacionNovedadTCREARptpagrDominioInstance;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrDominioInstance(Dominio grupoAsignacionNovedadTCREARptpagrDominio) {
        this.grupoAsignacionNovedadTCREARptpagrDominioInstance = grupoAsignacionNovedadTCREARptpagrDominio;
        this.grupoAsignacionNovedadTCREARptpagrDominio = grupoAsignacionNovedadTCREARptpagrDominio != null ? grupoAsignacionNovedadTCREARptpagrDominio.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadTCREARptpagrDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTCREARptpagrDominioInstance() == null || this.getGrupoAsignacionNovedadTCREARptpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO, GrupoAsignacionNovedadConstants.SERV_ALIAS_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionNovedadTCREARptpagrCausal() {
        return this.grupoAsignacionNovedadTCREARptpagrCausal;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrCausal(String grupoAsignacionNovedadTCREARptpagrCausal) {
        this.grupoAsignacionNovedadTCREARptpagrCausal = grupoAsignacionNovedadTCREARptpagrCausal;
    }

    @JsonIgnore
    public Causal getGrupoAsignacionNovedadTCREARptpagrCausalInstance() {
        return this.grupoAsignacionNovedadTCREARptpagrCausalInstance;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrCausalInstance(Causal grupoAsignacionNovedadTCREARptpagrCausal) {
        this.grupoAsignacionNovedadTCREARptpagrCausalInstance = grupoAsignacionNovedadTCREARptpagrCausal;
        this.grupoAsignacionNovedadTCREARptpagrCausal = grupoAsignacionNovedadTCREARptpagrCausal != null ? grupoAsignacionNovedadTCREARptpagrCausal.getOid().toJson() : "";
    }

    private void checkGrupoAsignacionNovedadTCREARptpagrCausal() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTCREARptpagrCausalInstance() == null || this.getGrupoAsignacionNovedadTCREARptpagrCausalInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRCAUSAL, GrupoAsignacionNovedadConstants.SERV_ALIAS_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRCAUSAL);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios() {
        return this.grupoAsignacionNovedadTCREARptpagrGrupoUsuarios;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios(GrupoAsignacionOid grupoAsignacionNovedadTCREARptpagrGrupoUsuarios) {
        this.grupoAsignacionNovedadTCREARptpagrGrupoUsuarios = grupoAsignacionNovedadTCREARptpagrGrupoUsuarios;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance() {
        return this.grupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance;
    }
    
    public void setGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance(GrupoAsignacion grupoAsignacionNovedadTCREARptpagrGrupoUsuarios) {
        this.grupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance = grupoAsignacionNovedadTCREARptpagrGrupoUsuarios;
        this.grupoAsignacionNovedadTCREARptpagrGrupoUsuarios = grupoAsignacionNovedadTCREARptpagrGrupoUsuarios != null ? grupoAsignacionNovedadTCREARptpagrGrupoUsuarios.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadTCREARptpagrGrupoUsuarios() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance() == null || this.getGrupoAsignacionNovedadTCREARptpagrGrupoUsuariosInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRGRUPOUSUARIOS, GrupoAsignacionNovedadConstants.SERV_ALIAS_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTPAGRGRUPOUSUARIOS);
        }
    }

    @JsonIgnore
    public String getGrupoAsignacionNovedadTCREARptNovedad() {
        return this.grupoAsignacionNovedadTCREARptNovedad;
    }
    
    public void setGrupoAsignacionNovedadTCREARptNovedad(String grupoAsignacionNovedadTCREARptNovedad) {
        this.grupoAsignacionNovedadTCREARptNovedad = grupoAsignacionNovedadTCREARptNovedad;
    }

    @JsonIgnore
    public Novedad getGrupoAsignacionNovedadTCREARptNovedadInstance() {
        return this.grupoAsignacionNovedadTCREARptNovedadInstance;
    }
    
    public void setGrupoAsignacionNovedadTCREARptNovedadInstance(Novedad grupoAsignacionNovedadTCREARptNovedad) {
        this.grupoAsignacionNovedadTCREARptNovedadInstance = grupoAsignacionNovedadTCREARptNovedad;
        this.grupoAsignacionNovedadTCREARptNovedad = grupoAsignacionNovedadTCREARptNovedad != null ? grupoAsignacionNovedadTCREARptNovedad.getOid().toJson() : "";
    }

    private void checkGrupoAsignacionNovedadTCREARptNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTCREARptNovedadInstance() == null || this.getGrupoAsignacionNovedadTCREARptNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD, GrupoAsignacionNovedadConstants.SERV_ALIAS_TCREAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
