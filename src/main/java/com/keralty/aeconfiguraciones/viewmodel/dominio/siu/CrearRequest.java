package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CrearRequest {

    @JsonProperty("p_agrplant_json")
    private String dominiocrearpagrPlanT;
    
    @JsonIgnore
    private Planes dominiocrearpagrPlanTInstance;

    @JsonProperty("p_agrproductot_json")
    private String dominiocrearpagrProductoT;
    
    @JsonIgnore
    private Productos dominiocrearpagrProductoTInstance;

    @JsonProperty("p_agrgrupoasesores_oid")
    private GrupoAsignacionOid dominiocrearpagrGrupoAsesores;
    
    @JsonIgnore
    private GrupoAsignacion dominiocrearpagrGrupoAsesoresInstance;

    @JsonProperty("p_agrgrupoareamedica_oid")
    private GrupoAsignacionOid dominiocrearpagrGrupoAreaMedica;
    
    @JsonIgnore
    private GrupoAsignacion dominiocrearpagrGrupoAreaMedicaInstance;

    @JsonProperty("p_atrreqcuesmed")
    private Boolean dominiocrearpatrReqCuesMed;

    @JsonProperty("p_atrnocuesmedtras")
    private Boolean dominiocrearpatrNoCuesMedTras;

    @JsonProperty("p_atrnuevaafiliacion")
    private Boolean dominiocrearpatrNuevaAfiliacion;

    @JsonProperty("p_atrinclusion")
    private Boolean dominiocrearpatrInclusion;

    @JsonProperty("p_atracepcuesmat")
    private Boolean dominiocrearpatrAcepCuesMat;

    @JsonProperty("p_atracepcuesneo")
    private Boolean dominiocrearpatrAcepCuesNeo;

    @JsonProperty("p_atracepbbgestante")
    private Boolean dominiocrearpatrAcepBBGestante;

    @JsonProperty("p_atrmostrarpreexanexos")
    private Boolean dominiocrearpatrMostrarPreexAnexos;

    @JsonProperty("p_atrmostrarpreexcontrato")
    private Boolean dominiocrearpatrMostrarPreexContrato;

    @JsonProperty("p_atrignorarpreexistencias")
    private Boolean dominiocrearpatrIgnorarPreexistencias;

    @JsonProperty("p_atrprorrateousuexistente")
    private Boolean dominiocrearpatrProrrateoUsuExistente;

    @JsonProperty("p_atrinclusionmodgrupoasoc")
    private Boolean dominiocrearpatrInclusionModGrupoAsoc;

    @JsonProperty("p_atrpermiterechazadosafi")
    private Boolean dominiocrearpatrPermiteRechazadosAfi;

    @JsonProperty("p_atraceppolizainter")
    private Boolean dominiocrearpatrAcepPolizaInter;

    @JsonProperty("p_atrnovexcepcionreqdoc")
    private Boolean dominiocrearpatrNovExcepcionReqDoc;

    @JsonProperty("p_atrsolnovcoloroscuro")
    private String dominiocrearpatrSolNovColorOscuro;

    @JsonProperty("p_atrsolnovcolorclaro")
    private String dominiocrearpatrSolNovColorClaro;

    @JsonProperty("p_atrsolnovlogo")
    private String dominiocrearpatrSolNovLogo;

    @JsonProperty("p_atrtramiteenlinea")
    private Boolean dominiocrearpatrTramiteEnLinea;

    @JsonProperty("p_atrvigenciaradicacion")
    private Boolean dominiocrearpatrVigenciaRadicacion;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiocrearpagrPlanT();
        checkDominiocrearpagrProductoT();
        checkDominiocrearpatrReqCuesMed();
        checkDominiocrearpatrNoCuesMedTras();
        checkDominiocrearpatrNuevaAfiliacion();
        checkDominiocrearpatrInclusion();
        checkDominiocrearpatrAcepCuesMat();
        checkDominiocrearpatrAcepCuesNeo();
        checkDominiocrearpatrAcepBBGestante();
        checkDominiocrearpatrMostrarPreexAnexos();
        checkDominiocrearpatrMostrarPreexContrato();
        checkDominiocrearpatrIgnorarPreexistencias();
        checkDominiocrearpatrProrrateoUsuExistente();
        checkDominiocrearpatrInclusionModGrupoAsoc();
        checkDominiocrearpatrPermiteRechazadosAfi();
        checkDominiocrearpatrAcepPolizaInter();
        checkDominiocrearpatrNovExcepcionReqDoc();
        checkDominiocrearpatrTramiteEnLinea();
        checkDominiocrearpatrVigenciaRadicacion();
    }

    @JsonIgnore
    public String getDominiocrearpagrPlanT() {
        return this.dominiocrearpagrPlanT;
    }
    
    public void setDominiocrearpagrPlanT(String dominiocrearpagrPlanT) {
        this.dominiocrearpagrPlanT = dominiocrearpagrPlanT;
    }

    @JsonIgnore
    public Planes getDominiocrearpagrPlanTInstance() {
        return this.dominiocrearpagrPlanTInstance;
    }
    
    public void setDominiocrearpagrPlanTInstance(Planes dominiocrearpagrPlanT) {
        this.dominiocrearpagrPlanTInstance = dominiocrearpagrPlanT;
        this.dominiocrearpagrPlanT = dominiocrearpagrPlanT != null ? dominiocrearpagrPlanT.getOid().toJson() : "";
    }

    private void checkDominiocrearpagrPlanT() throws NotNullArgumentException {
        if(this.getDominiocrearpagrPlanTInstance() == null || this.getDominiocrearpagrPlanTInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPAGRPLANT, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPAGRPLANT);
        }
    }

    @JsonIgnore
    public String getDominiocrearpagrProductoT() {
        return this.dominiocrearpagrProductoT;
    }
    
    public void setDominiocrearpagrProductoT(String dominiocrearpagrProductoT) {
        this.dominiocrearpagrProductoT = dominiocrearpagrProductoT;
    }

    @JsonIgnore
    public Productos getDominiocrearpagrProductoTInstance() {
        return this.dominiocrearpagrProductoTInstance;
    }
    
    public void setDominiocrearpagrProductoTInstance(Productos dominiocrearpagrProductoT) {
        this.dominiocrearpagrProductoTInstance = dominiocrearpagrProductoT;
        this.dominiocrearpagrProductoT = dominiocrearpagrProductoT != null ? dominiocrearpagrProductoT.getOid().toJson() : "";
    }

    private void checkDominiocrearpagrProductoT() throws NotNullArgumentException {
        if(this.getDominiocrearpagrProductoTInstance() == null || this.getDominiocrearpagrProductoTInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPAGRPRODUCTOT, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPAGRPRODUCTOT);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominiocrearpagrGrupoAsesores() {
        return this.dominiocrearpagrGrupoAsesores;
    }
    
    public void setDominiocrearpagrGrupoAsesores(GrupoAsignacionOid dominiocrearpagrGrupoAsesores) {
        this.dominiocrearpagrGrupoAsesores = dominiocrearpagrGrupoAsesores;
    }

    @JsonIgnore
    public GrupoAsignacion getDominiocrearpagrGrupoAsesoresInstance() {
        return this.dominiocrearpagrGrupoAsesoresInstance;
    }
    
    public void setDominiocrearpagrGrupoAsesoresInstance(GrupoAsignacion dominiocrearpagrGrupoAsesores) {
        this.dominiocrearpagrGrupoAsesoresInstance = dominiocrearpagrGrupoAsesores;
        this.dominiocrearpagrGrupoAsesores = dominiocrearpagrGrupoAsesores != null ? dominiocrearpagrGrupoAsesores.getOid() : null;
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominiocrearpagrGrupoAreaMedica() {
        return this.dominiocrearpagrGrupoAreaMedica;
    }
    
    public void setDominiocrearpagrGrupoAreaMedica(GrupoAsignacionOid dominiocrearpagrGrupoAreaMedica) {
        this.dominiocrearpagrGrupoAreaMedica = dominiocrearpagrGrupoAreaMedica;
    }

    @JsonIgnore
    public GrupoAsignacion getDominiocrearpagrGrupoAreaMedicaInstance() {
        return this.dominiocrearpagrGrupoAreaMedicaInstance;
    }
    
    public void setDominiocrearpagrGrupoAreaMedicaInstance(GrupoAsignacion dominiocrearpagrGrupoAreaMedica) {
        this.dominiocrearpagrGrupoAreaMedicaInstance = dominiocrearpagrGrupoAreaMedica;
        this.dominiocrearpagrGrupoAreaMedica = dominiocrearpagrGrupoAreaMedica != null ? dominiocrearpagrGrupoAreaMedica.getOid() : null;
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrReqCuesMed() {
        return this.dominiocrearpatrReqCuesMed;
    }
    
    public void setDominiocrearpatrReqCuesMed(Boolean dominiocrearpatrReqCuesMed) {
        this.dominiocrearpatrReqCuesMed = dominiocrearpatrReqCuesMed;
    }

    private void checkDominiocrearpatrReqCuesMed() throws NotNullArgumentException {
        if(this.getDominiocrearpatrReqCuesMed() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRREQCUESMED, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRREQCUESMED);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrNoCuesMedTras() {
        return this.dominiocrearpatrNoCuesMedTras;
    }
    
    public void setDominiocrearpatrNoCuesMedTras(Boolean dominiocrearpatrNoCuesMedTras) {
        this.dominiocrearpatrNoCuesMedTras = dominiocrearpatrNoCuesMedTras;
    }

    private void checkDominiocrearpatrNoCuesMedTras() throws NotNullArgumentException {
        if(this.getDominiocrearpatrNoCuesMedTras() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRNOCUESMEDTRAS, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRNOCUESMEDTRAS);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrNuevaAfiliacion() {
        return this.dominiocrearpatrNuevaAfiliacion;
    }
    
    public void setDominiocrearpatrNuevaAfiliacion(Boolean dominiocrearpatrNuevaAfiliacion) {
        this.dominiocrearpatrNuevaAfiliacion = dominiocrearpatrNuevaAfiliacion;
    }

    private void checkDominiocrearpatrNuevaAfiliacion() throws NotNullArgumentException {
        if(this.getDominiocrearpatrNuevaAfiliacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRNUEVAAFILIACION, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRNUEVAAFILIACION);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrInclusion() {
        return this.dominiocrearpatrInclusion;
    }
    
    public void setDominiocrearpatrInclusion(Boolean dominiocrearpatrInclusion) {
        this.dominiocrearpatrInclusion = dominiocrearpatrInclusion;
    }

    private void checkDominiocrearpatrInclusion() throws NotNullArgumentException {
        if(this.getDominiocrearpatrInclusion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRINCLUSION, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRINCLUSION);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrAcepCuesMat() {
        return this.dominiocrearpatrAcepCuesMat;
    }
    
    public void setDominiocrearpatrAcepCuesMat(Boolean dominiocrearpatrAcepCuesMat) {
        this.dominiocrearpatrAcepCuesMat = dominiocrearpatrAcepCuesMat;
    }

    private void checkDominiocrearpatrAcepCuesMat() throws NotNullArgumentException {
        if(this.getDominiocrearpatrAcepCuesMat() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRACEPCUESMAT, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRACEPCUESMAT);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrAcepCuesNeo() {
        return this.dominiocrearpatrAcepCuesNeo;
    }
    
    public void setDominiocrearpatrAcepCuesNeo(Boolean dominiocrearpatrAcepCuesNeo) {
        this.dominiocrearpatrAcepCuesNeo = dominiocrearpatrAcepCuesNeo;
    }

    private void checkDominiocrearpatrAcepCuesNeo() throws NotNullArgumentException {
        if(this.getDominiocrearpatrAcepCuesNeo() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRACEPCUESNEO, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRACEPCUESNEO);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrAcepBBGestante() {
        return this.dominiocrearpatrAcepBBGestante;
    }
    
    public void setDominiocrearpatrAcepBBGestante(Boolean dominiocrearpatrAcepBBGestante) {
        this.dominiocrearpatrAcepBBGestante = dominiocrearpatrAcepBBGestante;
    }

    private void checkDominiocrearpatrAcepBBGestante() throws NotNullArgumentException {
        if(this.getDominiocrearpatrAcepBBGestante() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRACEPBBGESTANTE, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRACEPBBGESTANTE);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrMostrarPreexAnexos() {
        return this.dominiocrearpatrMostrarPreexAnexos;
    }
    
    public void setDominiocrearpatrMostrarPreexAnexos(Boolean dominiocrearpatrMostrarPreexAnexos) {
        this.dominiocrearpatrMostrarPreexAnexos = dominiocrearpatrMostrarPreexAnexos;
    }

    private void checkDominiocrearpatrMostrarPreexAnexos() throws NotNullArgumentException {
        if(this.getDominiocrearpatrMostrarPreexAnexos() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRMOSTRARPREEXANEXOS, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRMOSTRARPREEXANEXOS);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrMostrarPreexContrato() {
        return this.dominiocrearpatrMostrarPreexContrato;
    }
    
    public void setDominiocrearpatrMostrarPreexContrato(Boolean dominiocrearpatrMostrarPreexContrato) {
        this.dominiocrearpatrMostrarPreexContrato = dominiocrearpatrMostrarPreexContrato;
    }

    private void checkDominiocrearpatrMostrarPreexContrato() throws NotNullArgumentException {
        if(this.getDominiocrearpatrMostrarPreexContrato() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRMOSTRARPREEXCONTRATO, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRMOSTRARPREEXCONTRATO);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrIgnorarPreexistencias() {
        return this.dominiocrearpatrIgnorarPreexistencias;
    }
    
    public void setDominiocrearpatrIgnorarPreexistencias(Boolean dominiocrearpatrIgnorarPreexistencias) {
        this.dominiocrearpatrIgnorarPreexistencias = dominiocrearpatrIgnorarPreexistencias;
    }

    private void checkDominiocrearpatrIgnorarPreexistencias() throws NotNullArgumentException {
        if(this.getDominiocrearpatrIgnorarPreexistencias() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRIGNORARPREEXISTENCIAS, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRIGNORARPREEXISTENCIAS);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrProrrateoUsuExistente() {
        return this.dominiocrearpatrProrrateoUsuExistente;
    }
    
    public void setDominiocrearpatrProrrateoUsuExistente(Boolean dominiocrearpatrProrrateoUsuExistente) {
        this.dominiocrearpatrProrrateoUsuExistente = dominiocrearpatrProrrateoUsuExistente;
    }

    private void checkDominiocrearpatrProrrateoUsuExistente() throws NotNullArgumentException {
        if(this.getDominiocrearpatrProrrateoUsuExistente() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRPRORRATEOUSUEXISTENTE, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRPRORRATEOUSUEXISTENTE);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrInclusionModGrupoAsoc() {
        return this.dominiocrearpatrInclusionModGrupoAsoc;
    }
    
    public void setDominiocrearpatrInclusionModGrupoAsoc(Boolean dominiocrearpatrInclusionModGrupoAsoc) {
        this.dominiocrearpatrInclusionModGrupoAsoc = dominiocrearpatrInclusionModGrupoAsoc;
    }

    private void checkDominiocrearpatrInclusionModGrupoAsoc() throws NotNullArgumentException {
        if(this.getDominiocrearpatrInclusionModGrupoAsoc() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRINCLUSIONMODGRUPOASOC, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRINCLUSIONMODGRUPOASOC);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrPermiteRechazadosAfi() {
        return this.dominiocrearpatrPermiteRechazadosAfi;
    }
    
    public void setDominiocrearpatrPermiteRechazadosAfi(Boolean dominiocrearpatrPermiteRechazadosAfi) {
        this.dominiocrearpatrPermiteRechazadosAfi = dominiocrearpatrPermiteRechazadosAfi;
    }

    private void checkDominiocrearpatrPermiteRechazadosAfi() throws NotNullArgumentException {
        if(this.getDominiocrearpatrPermiteRechazadosAfi() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRPERMITERECHAZADOSAFI, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRPERMITERECHAZADOSAFI);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrAcepPolizaInter() {
        return this.dominiocrearpatrAcepPolizaInter;
    }
    
    public void setDominiocrearpatrAcepPolizaInter(Boolean dominiocrearpatrAcepPolizaInter) {
        this.dominiocrearpatrAcepPolizaInter = dominiocrearpatrAcepPolizaInter;
    }

    private void checkDominiocrearpatrAcepPolizaInter() throws NotNullArgumentException {
        if(this.getDominiocrearpatrAcepPolizaInter() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRACEPPOLIZAINTER, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRACEPPOLIZAINTER);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrNovExcepcionReqDoc() {
        return this.dominiocrearpatrNovExcepcionReqDoc;
    }
    
    public void setDominiocrearpatrNovExcepcionReqDoc(Boolean dominiocrearpatrNovExcepcionReqDoc) {
        this.dominiocrearpatrNovExcepcionReqDoc = dominiocrearpatrNovExcepcionReqDoc;
    }

    private void checkDominiocrearpatrNovExcepcionReqDoc() throws NotNullArgumentException {
        if(this.getDominiocrearpatrNovExcepcionReqDoc() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRNOVEXCEPCIONREQDOC, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRNOVEXCEPCIONREQDOC);
        }
    }

    @JsonIgnore
    public String getDominiocrearpatrSolNovColorOscuro() {
        return this.dominiocrearpatrSolNovColorOscuro;
    }
    
    public void setDominiocrearpatrSolNovColorOscuro(String dominiocrearpatrSolNovColorOscuro) {
        this.dominiocrearpatrSolNovColorOscuro = dominiocrearpatrSolNovColorOscuro;
    }

    @JsonIgnore
    public String getDominiocrearpatrSolNovColorClaro() {
        return this.dominiocrearpatrSolNovColorClaro;
    }
    
    public void setDominiocrearpatrSolNovColorClaro(String dominiocrearpatrSolNovColorClaro) {
        this.dominiocrearpatrSolNovColorClaro = dominiocrearpatrSolNovColorClaro;
    }

    @JsonIgnore
    public String getDominiocrearpatrSolNovLogo() {
        return this.dominiocrearpatrSolNovLogo;
    }
    
    public void setDominiocrearpatrSolNovLogo(String dominiocrearpatrSolNovLogo) {
        this.dominiocrearpatrSolNovLogo = dominiocrearpatrSolNovLogo;
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrTramiteEnLinea() {
        return this.dominiocrearpatrTramiteEnLinea;
    }
    
    public void setDominiocrearpatrTramiteEnLinea(Boolean dominiocrearpatrTramiteEnLinea) {
        this.dominiocrearpatrTramiteEnLinea = dominiocrearpatrTramiteEnLinea;
    }

    private void checkDominiocrearpatrTramiteEnLinea() throws NotNullArgumentException {
        if(this.getDominiocrearpatrTramiteEnLinea() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRTRAMITEENLINEA, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRTRAMITEENLINEA);
        }
    }

    @JsonIgnore
    public Boolean getDominiocrearpatrVigenciaRadicacion() {
        return this.dominiocrearpatrVigenciaRadicacion;
    }
    
    public void setDominiocrearpatrVigenciaRadicacion(Boolean dominiocrearpatrVigenciaRadicacion) {
        this.dominiocrearpatrVigenciaRadicacion = dominiocrearpatrVigenciaRadicacion;
    }

    private void checkDominiocrearpatrVigenciaRadicacion() throws NotNullArgumentException {
        if(this.getDominiocrearpatrVigenciaRadicacion() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CREAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CREAR_DOMINIOCREARPATRVIGENCIARADICACION, DominioConstants.SERV_ALIAS_CREAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CREAR_DOMINIOCREARPATRVIGENCIARADICACION);
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
