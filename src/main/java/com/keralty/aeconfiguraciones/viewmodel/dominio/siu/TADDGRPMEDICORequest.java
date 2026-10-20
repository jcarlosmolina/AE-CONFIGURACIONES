package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TADDGRPMEDICORequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTADDGRPMEDICOpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTADDGRPMEDICOpthisDominioInstance;

    @JsonProperty("ptgrupo_oid")
    private GrupoAsignacionOid dominioTADDGRPMEDICOptGrupo;
    
    @JsonIgnore
    private GrupoAsignacion dominioTADDGRPMEDICOptGrupoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTADDGRPMEDICOpthisDominio();
        checkDominioTADDGRPMEDICOptGrupo();
    }

    @JsonIgnore
    public DominioOid getDominioTADDGRPMEDICOpthisDominio() {
        return this.dominioTADDGRPMEDICOpthisDominio;
    }
    
    public void setDominioTADDGRPMEDICOpthisDominio(DominioOid dominioTADDGRPMEDICOpthisDominio) {
        this.dominioTADDGRPMEDICOpthisDominio = dominioTADDGRPMEDICOpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTADDGRPMEDICOpthisDominioInstance() {
        return this.dominioTADDGRPMEDICOpthisDominioInstance;
    }
    
    public void setDominioTADDGRPMEDICOpthisDominioInstance(Dominio dominioTADDGRPMEDICOpthisDominio) {
        this.dominioTADDGRPMEDICOpthisDominioInstance = dominioTADDGRPMEDICOpthisDominio;
        this.dominioTADDGRPMEDICOpthisDominio = dominioTADDGRPMEDICOpthisDominio != null ? dominioTADDGRPMEDICOpthisDominio.getOid() : null;
    }

    private void checkDominioTADDGRPMEDICOpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTADDGRPMEDICOpthisDominioInstance() == null || this.getDominioTADDGRPMEDICOpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDGRPMEDICO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDGRPMEDICO_DOMINIOTADDGRPMEDICOPTHISDOMINIO, DominioConstants.SERV_ALIAS_TADDGRPMEDICO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDGRPMEDICO_DOMINIOTADDGRPMEDICOPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioTADDGRPMEDICOptGrupo() {
        return this.dominioTADDGRPMEDICOptGrupo;
    }
    
    public void setDominioTADDGRPMEDICOptGrupo(GrupoAsignacionOid dominioTADDGRPMEDICOptGrupo) {
        this.dominioTADDGRPMEDICOptGrupo = dominioTADDGRPMEDICOptGrupo;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioTADDGRPMEDICOptGrupoInstance() {
        return this.dominioTADDGRPMEDICOptGrupoInstance;
    }
    
    public void setDominioTADDGRPMEDICOptGrupoInstance(GrupoAsignacion dominioTADDGRPMEDICOptGrupo) {
        this.dominioTADDGRPMEDICOptGrupoInstance = dominioTADDGRPMEDICOptGrupo;
        this.dominioTADDGRPMEDICOptGrupo = dominioTADDGRPMEDICOptGrupo != null ? dominioTADDGRPMEDICOptGrupo.getOid() : null;
    }

    private void checkDominioTADDGRPMEDICOptGrupo() throws NotNullArgumentException {
        if(this.getDominioTADDGRPMEDICOptGrupoInstance() == null || this.getDominioTADDGRPMEDICOptGrupoInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDGRPMEDICO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDGRPMEDICO_DOMINIOTADDGRPMEDICOPTGRUPO, DominioConstants.SERV_ALIAS_TADDGRPMEDICO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDGRPMEDICO_DOMINIOTADDGRPMEDICOPTGRUPO);
        }
    }
}
