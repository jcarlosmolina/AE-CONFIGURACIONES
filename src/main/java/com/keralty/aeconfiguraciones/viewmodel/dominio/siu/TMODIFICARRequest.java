package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TMODIFICARRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTMODIFICARpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTMODIFICARpthisDominioInstance;

    @JsonProperty("p_reqcuesmed")
    private Boolean dominioTMODIFICARpReqCuesMed;

    @JsonProperty("p_nocuesmedtras")
    private Boolean dominioTMODIFICARpNoCuesMedTras;

    @JsonProperty("p_nuevaafiliacion")
    private Boolean dominioTMODIFICARpNuevaAfiliacion;

    @JsonProperty("p_inclusion")
    private Boolean dominioTMODIFICARpInclusion;

    @JsonProperty("p_acepcuesmat")
    private Boolean dominioTMODIFICARpAcepCuesMat;

    @JsonProperty("p_acepcuesneo")
    private Boolean dominioTMODIFICARpAcepCuesNeo;

    @JsonProperty("p_acepbbgestante")
    private Boolean dominioTMODIFICARpAcepBBGestante;

    @JsonProperty("p_ignorarpreexistencias")
    private Boolean dominioTMODIFICARpIgnorarPreexistencias;

    @JsonProperty("p_prorrateousuexistente")
    private Boolean dominioTMODIFICARpProrrateoUsuExistente;

    @JsonProperty("p_inclusionmodgrupoasoc")
    private Boolean dominioTMODIFICARpInclusionModGrupoAsoc;

    @JsonProperty("p_permiterechazadosafi")
    private Boolean dominioTMODIFICARpPermiteRechazadosAfi;

    @JsonProperty("p_aceppolizainter")
    private Boolean dominioTMODIFICARpAcepPolizaInter;

    @JsonProperty("p_mostrarpreexanexos")
    private Boolean dominioTMODIFICARpMostrarPreexAnexos;

    @JsonProperty("p_mostrarpreexcontrato")
    private Boolean dominioTMODIFICARpMostrarPreexContrato;

    @JsonProperty("p_tramiteenlinea")
    private Boolean dominioTMODIFICARpTramiteEnLinea;

    @JsonProperty("ptvigenciaradicacion")
    private Boolean dominioTMODIFICARptVigenciaRadicacion;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTMODIFICARpthisDominio();
        checkDominioTMODIFICARpReqCuesMed();
        checkDominioTMODIFICARpNoCuesMedTras();
        checkDominioTMODIFICARpNuevaAfiliacion();
        checkDominioTMODIFICARpInclusion();
        checkDominioTMODIFICARpAcepCuesMat();
        checkDominioTMODIFICARpAcepCuesNeo();
        checkDominioTMODIFICARpAcepBBGestante();
        checkDominioTMODIFICARpIgnorarPreexistencias();
        checkDominioTMODIFICARpProrrateoUsuExistente();
        checkDominioTMODIFICARpInclusionModGrupoAsoc();
        checkDominioTMODIFICARpPermiteRechazadosAfi();
        checkDominioTMODIFICARpAcepPolizaInter();
        checkDominioTMODIFICARpMostrarPreexAnexos();
        checkDominioTMODIFICARpMostrarPreexContrato();
        checkDominioTMODIFICARpTramiteEnLinea();
        checkDominioTMODIFICARptVigenciaRadicacion();
    }

    @JsonIgnore
    public DominioOid getDominioTMODIFICARpthisDominio() {
        return this.dominioTMODIFICARpthisDominio;
    }
    
    public void setDominioTMODIFICARpthisDominio(DominioOid dominioTMODIFICARpthisDominio) {
        this.dominioTMODIFICARpthisDominio = dominioTMODIFICARpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTMODIFICARpthisDominioInstance() {
        return this.dominioTMODIFICARpthisDominioInstance;
    }
    
    public void setDominioTMODIFICARpthisDominioInstance(Dominio dominioTMODIFICARpthisDominio) {
        this.dominioTMODIFICARpthisDominioInstance = dominioTMODIFICARpthisDominio;
        this.dominioTMODIFICARpthisDominio = dominioTMODIFICARpthisDominio != null ? dominioTMODIFICARpthisDominio.getOid() : null;
    }

    private void checkDominioTMODIFICARpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpthisDominioInstance() == null || this.getDominioTMODIFICARpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPTHISDOMINIO, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpReqCuesMed() {
        return this.dominioTMODIFICARpReqCuesMed;
    }
    
    public void setDominioTMODIFICARpReqCuesMed(Boolean dominioTMODIFICARpReqCuesMed) {
        this.dominioTMODIFICARpReqCuesMed = dominioTMODIFICARpReqCuesMed;
    }

    private void checkDominioTMODIFICARpReqCuesMed() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpReqCuesMed() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPREQCUESMED, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPREQCUESMED);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpNoCuesMedTras() {
        return this.dominioTMODIFICARpNoCuesMedTras;
    }
    
    public void setDominioTMODIFICARpNoCuesMedTras(Boolean dominioTMODIFICARpNoCuesMedTras) {
        this.dominioTMODIFICARpNoCuesMedTras = dominioTMODIFICARpNoCuesMedTras;
    }

    private void checkDominioTMODIFICARpNoCuesMedTras() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpNoCuesMedTras() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPNOCUESMEDTRAS, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPNOCUESMEDTRAS);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpNuevaAfiliacion() {
        return this.dominioTMODIFICARpNuevaAfiliacion;
    }
    
    public void setDominioTMODIFICARpNuevaAfiliacion(Boolean dominioTMODIFICARpNuevaAfiliacion) {
        this.dominioTMODIFICARpNuevaAfiliacion = dominioTMODIFICARpNuevaAfiliacion;
    }

    private void checkDominioTMODIFICARpNuevaAfiliacion() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpNuevaAfiliacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPNUEVAAFILIACION, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPNUEVAAFILIACION);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpInclusion() {
        return this.dominioTMODIFICARpInclusion;
    }
    
    public void setDominioTMODIFICARpInclusion(Boolean dominioTMODIFICARpInclusion) {
        this.dominioTMODIFICARpInclusion = dominioTMODIFICARpInclusion;
    }

    private void checkDominioTMODIFICARpInclusion() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpInclusion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPINCLUSION, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPINCLUSION);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpAcepCuesMat() {
        return this.dominioTMODIFICARpAcepCuesMat;
    }
    
    public void setDominioTMODIFICARpAcepCuesMat(Boolean dominioTMODIFICARpAcepCuesMat) {
        this.dominioTMODIFICARpAcepCuesMat = dominioTMODIFICARpAcepCuesMat;
    }

    private void checkDominioTMODIFICARpAcepCuesMat() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpAcepCuesMat() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPACEPCUESMAT, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPACEPCUESMAT);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpAcepCuesNeo() {
        return this.dominioTMODIFICARpAcepCuesNeo;
    }
    
    public void setDominioTMODIFICARpAcepCuesNeo(Boolean dominioTMODIFICARpAcepCuesNeo) {
        this.dominioTMODIFICARpAcepCuesNeo = dominioTMODIFICARpAcepCuesNeo;
    }

    private void checkDominioTMODIFICARpAcepCuesNeo() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpAcepCuesNeo() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPACEPCUESNEO, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPACEPCUESNEO);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpAcepBBGestante() {
        return this.dominioTMODIFICARpAcepBBGestante;
    }
    
    public void setDominioTMODIFICARpAcepBBGestante(Boolean dominioTMODIFICARpAcepBBGestante) {
        this.dominioTMODIFICARpAcepBBGestante = dominioTMODIFICARpAcepBBGestante;
    }

    private void checkDominioTMODIFICARpAcepBBGestante() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpAcepBBGestante() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPACEPBBGESTANTE, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPACEPBBGESTANTE);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpIgnorarPreexistencias() {
        return this.dominioTMODIFICARpIgnorarPreexistencias;
    }
    
    public void setDominioTMODIFICARpIgnorarPreexistencias(Boolean dominioTMODIFICARpIgnorarPreexistencias) {
        this.dominioTMODIFICARpIgnorarPreexistencias = dominioTMODIFICARpIgnorarPreexistencias;
    }

    private void checkDominioTMODIFICARpIgnorarPreexistencias() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpIgnorarPreexistencias() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPIGNORARPREEXISTENCIAS, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPIGNORARPREEXISTENCIAS);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpProrrateoUsuExistente() {
        return this.dominioTMODIFICARpProrrateoUsuExistente;
    }
    
    public void setDominioTMODIFICARpProrrateoUsuExistente(Boolean dominioTMODIFICARpProrrateoUsuExistente) {
        this.dominioTMODIFICARpProrrateoUsuExistente = dominioTMODIFICARpProrrateoUsuExistente;
    }

    private void checkDominioTMODIFICARpProrrateoUsuExistente() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpProrrateoUsuExistente() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPPRORRATEOUSUEXISTENTE, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPPRORRATEOUSUEXISTENTE);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpInclusionModGrupoAsoc() {
        return this.dominioTMODIFICARpInclusionModGrupoAsoc;
    }
    
    public void setDominioTMODIFICARpInclusionModGrupoAsoc(Boolean dominioTMODIFICARpInclusionModGrupoAsoc) {
        this.dominioTMODIFICARpInclusionModGrupoAsoc = dominioTMODIFICARpInclusionModGrupoAsoc;
    }

    private void checkDominioTMODIFICARpInclusionModGrupoAsoc() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpInclusionModGrupoAsoc() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPINCLUSIONMODGRUPOASOC, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPINCLUSIONMODGRUPOASOC);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpPermiteRechazadosAfi() {
        return this.dominioTMODIFICARpPermiteRechazadosAfi;
    }
    
    public void setDominioTMODIFICARpPermiteRechazadosAfi(Boolean dominioTMODIFICARpPermiteRechazadosAfi) {
        this.dominioTMODIFICARpPermiteRechazadosAfi = dominioTMODIFICARpPermiteRechazadosAfi;
    }

    private void checkDominioTMODIFICARpPermiteRechazadosAfi() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpPermiteRechazadosAfi() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPPERMITERECHAZADOSAFI, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPPERMITERECHAZADOSAFI);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpAcepPolizaInter() {
        return this.dominioTMODIFICARpAcepPolizaInter;
    }
    
    public void setDominioTMODIFICARpAcepPolizaInter(Boolean dominioTMODIFICARpAcepPolizaInter) {
        this.dominioTMODIFICARpAcepPolizaInter = dominioTMODIFICARpAcepPolizaInter;
    }

    private void checkDominioTMODIFICARpAcepPolizaInter() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpAcepPolizaInter() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPACEPPOLIZAINTER, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPACEPPOLIZAINTER);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpMostrarPreexAnexos() {
        return this.dominioTMODIFICARpMostrarPreexAnexos;
    }
    
    public void setDominioTMODIFICARpMostrarPreexAnexos(Boolean dominioTMODIFICARpMostrarPreexAnexos) {
        this.dominioTMODIFICARpMostrarPreexAnexos = dominioTMODIFICARpMostrarPreexAnexos;
    }

    private void checkDominioTMODIFICARpMostrarPreexAnexos() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpMostrarPreexAnexos() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPMOSTRARPREEXANEXOS, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPMOSTRARPREEXANEXOS);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpMostrarPreexContrato() {
        return this.dominioTMODIFICARpMostrarPreexContrato;
    }
    
    public void setDominioTMODIFICARpMostrarPreexContrato(Boolean dominioTMODIFICARpMostrarPreexContrato) {
        this.dominioTMODIFICARpMostrarPreexContrato = dominioTMODIFICARpMostrarPreexContrato;
    }

    private void checkDominioTMODIFICARpMostrarPreexContrato() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpMostrarPreexContrato() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPMOSTRARPREEXCONTRATO, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPMOSTRARPREEXCONTRATO);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARpTramiteEnLinea() {
        return this.dominioTMODIFICARpTramiteEnLinea;
    }
    
    public void setDominioTMODIFICARpTramiteEnLinea(Boolean dominioTMODIFICARpTramiteEnLinea) {
        this.dominioTMODIFICARpTramiteEnLinea = dominioTMODIFICARpTramiteEnLinea;
    }

    private void checkDominioTMODIFICARpTramiteEnLinea() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARpTramiteEnLinea() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPTRAMITEENLINEA, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPTRAMITEENLINEA);
        }
    }

    @JsonIgnore
    public Boolean getDominioTMODIFICARptVigenciaRadicacion() {
        return this.dominioTMODIFICARptVigenciaRadicacion;
    }
    
    public void setDominioTMODIFICARptVigenciaRadicacion(Boolean dominioTMODIFICARptVigenciaRadicacion) {
        this.dominioTMODIFICARptVigenciaRadicacion = dominioTMODIFICARptVigenciaRadicacion;
    }

    private void checkDominioTMODIFICARptVigenciaRadicacion() throws NotNullArgumentException {
        if(this.getDominioTMODIFICARptVigenciaRadicacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TMODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TMODIFICAR_DOMINIOTMODIFICARPTVIGENCIARADICACION, DominioConstants.SERV_ALIAS_TMODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TMODIFICAR_DOMINIOTMODIFICARPTVIGENCIARADICACION);
        }
    }
}
