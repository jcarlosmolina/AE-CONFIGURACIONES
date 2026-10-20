package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TADDGRPASESORESRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTADDGRPASESORESpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTADDGRPASESORESpthisDominioInstance;

    @JsonProperty("ptgrupo_oid")
    private GrupoAsignacionOid dominioTADDGRPASESORESptGrupo;
    
    @JsonIgnore
    private GrupoAsignacion dominioTADDGRPASESORESptGrupoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTADDGRPASESORESpthisDominio();
        checkDominioTADDGRPASESORESptGrupo();
    }

    @JsonIgnore
    public DominioOid getDominioTADDGRPASESORESpthisDominio() {
        return this.dominioTADDGRPASESORESpthisDominio;
    }
    
    public void setDominioTADDGRPASESORESpthisDominio(DominioOid dominioTADDGRPASESORESpthisDominio) {
        this.dominioTADDGRPASESORESpthisDominio = dominioTADDGRPASESORESpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTADDGRPASESORESpthisDominioInstance() {
        return this.dominioTADDGRPASESORESpthisDominioInstance;
    }
    
    public void setDominioTADDGRPASESORESpthisDominioInstance(Dominio dominioTADDGRPASESORESpthisDominio) {
        this.dominioTADDGRPASESORESpthisDominioInstance = dominioTADDGRPASESORESpthisDominio;
        this.dominioTADDGRPASESORESpthisDominio = dominioTADDGRPASESORESpthisDominio != null ? dominioTADDGRPASESORESpthisDominio.getOid() : null;
    }

    private void checkDominioTADDGRPASESORESpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTADDGRPASESORESpthisDominioInstance() == null || this.getDominioTADDGRPASESORESpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDGRPASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDGRPASESORES_DOMINIOTADDGRPASESORESPTHISDOMINIO, DominioConstants.SERV_ALIAS_TADDGRPASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDGRPASESORES_DOMINIOTADDGRPASESORESPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioTADDGRPASESORESptGrupo() {
        return this.dominioTADDGRPASESORESptGrupo;
    }
    
    public void setDominioTADDGRPASESORESptGrupo(GrupoAsignacionOid dominioTADDGRPASESORESptGrupo) {
        this.dominioTADDGRPASESORESptGrupo = dominioTADDGRPASESORESptGrupo;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioTADDGRPASESORESptGrupoInstance() {
        return this.dominioTADDGRPASESORESptGrupoInstance;
    }
    
    public void setDominioTADDGRPASESORESptGrupoInstance(GrupoAsignacion dominioTADDGRPASESORESptGrupo) {
        this.dominioTADDGRPASESORESptGrupoInstance = dominioTADDGRPASESORESptGrupo;
        this.dominioTADDGRPASESORESptGrupo = dominioTADDGRPASESORESptGrupo != null ? dominioTADDGRPASESORESptGrupo.getOid() : null;
    }

    private void checkDominioTADDGRPASESORESptGrupo() throws NotNullArgumentException {
        if(this.getDominioTADDGRPASESORESptGrupoInstance() == null || this.getDominioTADDGRPASESORESptGrupoInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDGRPASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDGRPASESORES_DOMINIOTADDGRPASESORESPTGRUPO, DominioConstants.SERV_ALIAS_TADDGRPASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDGRPASESORES_DOMINIOTADDGRPASESORESPTGRUPO);
        }
    }
}
