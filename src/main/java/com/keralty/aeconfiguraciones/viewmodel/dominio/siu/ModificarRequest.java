package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class ModificarRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominiomodificarpthisDominio;
    
    @JsonIgnore
    private Dominio dominiomodificarpthisDominioInstance;

    @JsonProperty("p_reqcuesmed")
    private Boolean dominiomodificarpReqCuesMed;

    @JsonProperty("p_nocuesmedtras")
    private Boolean dominiomodificarpNoCuesMedTras;

    @JsonProperty("p_nuevaafiliacion")
    private Boolean dominiomodificarpNuevaAfiliacion;

    @JsonProperty("p_inclusion")
    private Boolean dominiomodificarpInclusion;

    @JsonProperty("p_acepcuesmat")
    private Boolean dominiomodificarpAcepCuesMat;

    @JsonProperty("p_acepcuesneo")
    private Boolean dominiomodificarpAcepCuesNeo;

    @JsonProperty("p_acepbbgestante")
    private Boolean dominiomodificarpAcepBBGestante;

    @JsonProperty("p_ignorarpreexistencias")
    private Boolean dominiomodificarpIgnorarPreexistencias;

    @JsonProperty("p_prorrateousuexistente")
    private Boolean dominiomodificarpProrrateoUsuExistente;

    @JsonProperty("p_inclusionmodgrupoasoc")
    private Boolean dominiomodificarpInclusionModGrupoAsoc;

    @JsonProperty("p_permiterechazadosafi")
    private Boolean dominiomodificarpPermiteRechazadosAfi;

    @JsonProperty("p_aceppolizainter")
    private Boolean dominiomodificarpAcepPolizaInter;

    @JsonProperty("p_mostrarpreexanexos")
    private Boolean dominiomodificarpMostrarPreexAnexos;

    @JsonProperty("p_mostrarpreexcontrato")
    private Boolean dominiomodificarpMostrarPreexContrato;

    @JsonProperty("p_tramiteenlinea")
    private Boolean dominiomodificarpTramiteEnLinea;

    @JsonProperty("pevigenciaradicacion")
    private Boolean dominiomodificarpeVigenciaRadicacion;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiomodificarpthisDominio();
        checkDominiomodificarpReqCuesMed();
        checkDominiomodificarpNoCuesMedTras();
        checkDominiomodificarpNuevaAfiliacion();
        checkDominiomodificarpInclusion();
        checkDominiomodificarpAcepCuesMat();
        checkDominiomodificarpAcepCuesNeo();
        checkDominiomodificarpAcepBBGestante();
        checkDominiomodificarpIgnorarPreexistencias();
        checkDominiomodificarpProrrateoUsuExistente();
        checkDominiomodificarpInclusionModGrupoAsoc();
        checkDominiomodificarpPermiteRechazadosAfi();
        checkDominiomodificarpAcepPolizaInter();
        checkDominiomodificarpMostrarPreexAnexos();
        checkDominiomodificarpMostrarPreexContrato();
        checkDominiomodificarpTramiteEnLinea();
        checkDominiomodificarpeVigenciaRadicacion();
    }

    @JsonIgnore
    public DominioOid getDominiomodificarpthisDominio() {
        return this.dominiomodificarpthisDominio;
    }
    
    public void setDominiomodificarpthisDominio(DominioOid dominiomodificarpthisDominio) {
        this.dominiomodificarpthisDominio = dominiomodificarpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominiomodificarpthisDominioInstance() {
        return this.dominiomodificarpthisDominioInstance;
    }
    
    public void setDominiomodificarpthisDominioInstance(Dominio dominiomodificarpthisDominio) {
        this.dominiomodificarpthisDominioInstance = dominiomodificarpthisDominio;
        this.dominiomodificarpthisDominio = dominiomodificarpthisDominio != null ? dominiomodificarpthisDominio.getOid() : null;
    }

    private void checkDominiomodificarpthisDominio() throws NotNullArgumentException {
        if(this.getDominiomodificarpthisDominioInstance() == null || this.getDominiomodificarpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPTHISDOMINIO, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpReqCuesMed() {
        return this.dominiomodificarpReqCuesMed;
    }
    
    public void setDominiomodificarpReqCuesMed(Boolean dominiomodificarpReqCuesMed) {
        this.dominiomodificarpReqCuesMed = dominiomodificarpReqCuesMed;
    }

    private void checkDominiomodificarpReqCuesMed() throws NotNullArgumentException {
        if(this.getDominiomodificarpReqCuesMed() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPREQCUESMED, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPREQCUESMED);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpNoCuesMedTras() {
        return this.dominiomodificarpNoCuesMedTras;
    }
    
    public void setDominiomodificarpNoCuesMedTras(Boolean dominiomodificarpNoCuesMedTras) {
        this.dominiomodificarpNoCuesMedTras = dominiomodificarpNoCuesMedTras;
    }

    private void checkDominiomodificarpNoCuesMedTras() throws NotNullArgumentException {
        if(this.getDominiomodificarpNoCuesMedTras() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPNOCUESMEDTRAS, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPNOCUESMEDTRAS);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpNuevaAfiliacion() {
        return this.dominiomodificarpNuevaAfiliacion;
    }
    
    public void setDominiomodificarpNuevaAfiliacion(Boolean dominiomodificarpNuevaAfiliacion) {
        this.dominiomodificarpNuevaAfiliacion = dominiomodificarpNuevaAfiliacion;
    }

    private void checkDominiomodificarpNuevaAfiliacion() throws NotNullArgumentException {
        if(this.getDominiomodificarpNuevaAfiliacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPNUEVAAFILIACION, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPNUEVAAFILIACION);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpInclusion() {
        return this.dominiomodificarpInclusion;
    }
    
    public void setDominiomodificarpInclusion(Boolean dominiomodificarpInclusion) {
        this.dominiomodificarpInclusion = dominiomodificarpInclusion;
    }

    private void checkDominiomodificarpInclusion() throws NotNullArgumentException {
        if(this.getDominiomodificarpInclusion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPINCLUSION, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPINCLUSION);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpAcepCuesMat() {
        return this.dominiomodificarpAcepCuesMat;
    }
    
    public void setDominiomodificarpAcepCuesMat(Boolean dominiomodificarpAcepCuesMat) {
        this.dominiomodificarpAcepCuesMat = dominiomodificarpAcepCuesMat;
    }

    private void checkDominiomodificarpAcepCuesMat() throws NotNullArgumentException {
        if(this.getDominiomodificarpAcepCuesMat() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPACEPCUESMAT, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPACEPCUESMAT);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpAcepCuesNeo() {
        return this.dominiomodificarpAcepCuesNeo;
    }
    
    public void setDominiomodificarpAcepCuesNeo(Boolean dominiomodificarpAcepCuesNeo) {
        this.dominiomodificarpAcepCuesNeo = dominiomodificarpAcepCuesNeo;
    }

    private void checkDominiomodificarpAcepCuesNeo() throws NotNullArgumentException {
        if(this.getDominiomodificarpAcepCuesNeo() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPACEPCUESNEO, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPACEPCUESNEO);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpAcepBBGestante() {
        return this.dominiomodificarpAcepBBGestante;
    }
    
    public void setDominiomodificarpAcepBBGestante(Boolean dominiomodificarpAcepBBGestante) {
        this.dominiomodificarpAcepBBGestante = dominiomodificarpAcepBBGestante;
    }

    private void checkDominiomodificarpAcepBBGestante() throws NotNullArgumentException {
        if(this.getDominiomodificarpAcepBBGestante() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPACEPBBGESTANTE, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPACEPBBGESTANTE);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpIgnorarPreexistencias() {
        return this.dominiomodificarpIgnorarPreexistencias;
    }
    
    public void setDominiomodificarpIgnorarPreexistencias(Boolean dominiomodificarpIgnorarPreexistencias) {
        this.dominiomodificarpIgnorarPreexistencias = dominiomodificarpIgnorarPreexistencias;
    }

    private void checkDominiomodificarpIgnorarPreexistencias() throws NotNullArgumentException {
        if(this.getDominiomodificarpIgnorarPreexistencias() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPIGNORARPREEXISTENCIAS, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPIGNORARPREEXISTENCIAS);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpProrrateoUsuExistente() {
        return this.dominiomodificarpProrrateoUsuExistente;
    }
    
    public void setDominiomodificarpProrrateoUsuExistente(Boolean dominiomodificarpProrrateoUsuExistente) {
        this.dominiomodificarpProrrateoUsuExistente = dominiomodificarpProrrateoUsuExistente;
    }

    private void checkDominiomodificarpProrrateoUsuExistente() throws NotNullArgumentException {
        if(this.getDominiomodificarpProrrateoUsuExistente() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPPRORRATEOUSUEXISTENTE, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPPRORRATEOUSUEXISTENTE);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpInclusionModGrupoAsoc() {
        return this.dominiomodificarpInclusionModGrupoAsoc;
    }
    
    public void setDominiomodificarpInclusionModGrupoAsoc(Boolean dominiomodificarpInclusionModGrupoAsoc) {
        this.dominiomodificarpInclusionModGrupoAsoc = dominiomodificarpInclusionModGrupoAsoc;
    }

    private void checkDominiomodificarpInclusionModGrupoAsoc() throws NotNullArgumentException {
        if(this.getDominiomodificarpInclusionModGrupoAsoc() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPINCLUSIONMODGRUPOASOC, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPINCLUSIONMODGRUPOASOC);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpPermiteRechazadosAfi() {
        return this.dominiomodificarpPermiteRechazadosAfi;
    }
    
    public void setDominiomodificarpPermiteRechazadosAfi(Boolean dominiomodificarpPermiteRechazadosAfi) {
        this.dominiomodificarpPermiteRechazadosAfi = dominiomodificarpPermiteRechazadosAfi;
    }

    private void checkDominiomodificarpPermiteRechazadosAfi() throws NotNullArgumentException {
        if(this.getDominiomodificarpPermiteRechazadosAfi() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPPERMITERECHAZADOSAFI, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPPERMITERECHAZADOSAFI);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpAcepPolizaInter() {
        return this.dominiomodificarpAcepPolizaInter;
    }
    
    public void setDominiomodificarpAcepPolizaInter(Boolean dominiomodificarpAcepPolizaInter) {
        this.dominiomodificarpAcepPolizaInter = dominiomodificarpAcepPolizaInter;
    }

    private void checkDominiomodificarpAcepPolizaInter() throws NotNullArgumentException {
        if(this.getDominiomodificarpAcepPolizaInter() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPACEPPOLIZAINTER, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPACEPPOLIZAINTER);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpMostrarPreexAnexos() {
        return this.dominiomodificarpMostrarPreexAnexos;
    }
    
    public void setDominiomodificarpMostrarPreexAnexos(Boolean dominiomodificarpMostrarPreexAnexos) {
        this.dominiomodificarpMostrarPreexAnexos = dominiomodificarpMostrarPreexAnexos;
    }

    private void checkDominiomodificarpMostrarPreexAnexos() throws NotNullArgumentException {
        if(this.getDominiomodificarpMostrarPreexAnexos() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPMOSTRARPREEXANEXOS, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPMOSTRARPREEXANEXOS);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpMostrarPreexContrato() {
        return this.dominiomodificarpMostrarPreexContrato;
    }
    
    public void setDominiomodificarpMostrarPreexContrato(Boolean dominiomodificarpMostrarPreexContrato) {
        this.dominiomodificarpMostrarPreexContrato = dominiomodificarpMostrarPreexContrato;
    }

    private void checkDominiomodificarpMostrarPreexContrato() throws NotNullArgumentException {
        if(this.getDominiomodificarpMostrarPreexContrato() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPMOSTRARPREEXCONTRATO, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPMOSTRARPREEXCONTRATO);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpTramiteEnLinea() {
        return this.dominiomodificarpTramiteEnLinea;
    }
    
    public void setDominiomodificarpTramiteEnLinea(Boolean dominiomodificarpTramiteEnLinea) {
        this.dominiomodificarpTramiteEnLinea = dominiomodificarpTramiteEnLinea;
    }

    private void checkDominiomodificarpTramiteEnLinea() throws NotNullArgumentException {
        if(this.getDominiomodificarpTramiteEnLinea() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPTRAMITEENLINEA, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPTRAMITEENLINEA);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarpeVigenciaRadicacion() {
        return this.dominiomodificarpeVigenciaRadicacion;
    }
    
    public void setDominiomodificarpeVigenciaRadicacion(Boolean dominiomodificarpeVigenciaRadicacion) {
        this.dominiomodificarpeVigenciaRadicacion = dominiomodificarpeVigenciaRadicacion;
    }

    private void checkDominiomodificarpeVigenciaRadicacion() throws NotNullArgumentException {
        if(this.getDominiomodificarpeVigenciaRadicacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICAR_DOMINIOMODIFICARPEVIGENCIARADICACION, DominioConstants.SERV_ALIAS_MODIFICAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICAR_DOMINIOMODIFICARPEVIGENCIARADICACION);
        }
    }
}
