package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TDELGRPASESORESRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTDELGRPASESORESpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTDELGRPASESORESpthisDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTDELGRPASESORESpthisDominio();
    }

    @JsonIgnore
    public DominioOid getDominioTDELGRPASESORESpthisDominio() {
        return this.dominioTDELGRPASESORESpthisDominio;
    }
    
    public void setDominioTDELGRPASESORESpthisDominio(DominioOid dominioTDELGRPASESORESpthisDominio) {
        this.dominioTDELGRPASESORESpthisDominio = dominioTDELGRPASESORESpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTDELGRPASESORESpthisDominioInstance() {
        return this.dominioTDELGRPASESORESpthisDominioInstance;
    }
    
    public void setDominioTDELGRPASESORESpthisDominioInstance(Dominio dominioTDELGRPASESORESpthisDominio) {
        this.dominioTDELGRPASESORESpthisDominioInstance = dominioTDELGRPASESORESpthisDominio;
        this.dominioTDELGRPASESORESpthisDominio = dominioTDELGRPASESORESpthisDominio != null ? dominioTDELGRPASESORESpthisDominio.getOid() : null;
    }

    private void checkDominioTDELGRPASESORESpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTDELGRPASESORESpthisDominioInstance() == null || this.getDominioTDELGRPASESORESpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TDELGRPASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TDELGRPASESORES_DOMINIOTDELGRPASESORESPTHISDOMINIO, DominioConstants.SERV_ALIAS_TDELGRPASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TDELGRPASESORES_DOMINIOTDELGRPASESORESPTHISDOMINIO);
        }
    }
}
