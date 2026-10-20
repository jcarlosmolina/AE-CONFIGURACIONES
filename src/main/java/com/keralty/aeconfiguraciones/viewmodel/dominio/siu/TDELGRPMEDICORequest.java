package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TDELGRPMEDICORequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTDELGRPMEDICOpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTDELGRPMEDICOpthisDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTDELGRPMEDICOpthisDominio();
    }

    @JsonIgnore
    public DominioOid getDominioTDELGRPMEDICOpthisDominio() {
        return this.dominioTDELGRPMEDICOpthisDominio;
    }
    
    public void setDominioTDELGRPMEDICOpthisDominio(DominioOid dominioTDELGRPMEDICOpthisDominio) {
        this.dominioTDELGRPMEDICOpthisDominio = dominioTDELGRPMEDICOpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTDELGRPMEDICOpthisDominioInstance() {
        return this.dominioTDELGRPMEDICOpthisDominioInstance;
    }
    
    public void setDominioTDELGRPMEDICOpthisDominioInstance(Dominio dominioTDELGRPMEDICOpthisDominio) {
        this.dominioTDELGRPMEDICOpthisDominioInstance = dominioTDELGRPMEDICOpthisDominio;
        this.dominioTDELGRPMEDICOpthisDominio = dominioTDELGRPMEDICOpthisDominio != null ? dominioTDELGRPMEDICOpthisDominio.getOid() : null;
    }

    private void checkDominioTDELGRPMEDICOpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTDELGRPMEDICOpthisDominioInstance() == null || this.getDominioTDELGRPMEDICOpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TDELGRPMEDICO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TDELGRPMEDICO_DOMINIOTDELGRPMEDICOPTHISDOMINIO, DominioConstants.SERV_ALIAS_TDELGRPMEDICO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TDELGRPMEDICO_DOMINIOTDELGRPMEDICOPTHISDOMINIO);
        }
    }
}
