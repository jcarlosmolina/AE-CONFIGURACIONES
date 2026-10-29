package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class DelIncompatibleRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelIncompatiblepthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelIncompatiblepthisDominioInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid dominioDelIncompatiblepevcDominio;
    
    @JsonIgnore
    private Dominio dominioDelIncompatiblepevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelIncompatiblepthisDominio();
        checkDominioDelIncompatiblepevcDominio();
    }

    @JsonIgnore
    public DominioOid getDominioDelIncompatiblepthisDominio() {
        return this.dominioDelIncompatiblepthisDominio;
    }
    
    public void setDominioDelIncompatiblepthisDominio(DominioOid dominioDelIncompatiblepthisDominio) {
        this.dominioDelIncompatiblepthisDominio = dominioDelIncompatiblepthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelIncompatiblepthisDominioInstance() {
        return this.dominioDelIncompatiblepthisDominioInstance;
    }
    
    public void setDominioDelIncompatiblepthisDominioInstance(Dominio dominioDelIncompatiblepthisDominio) {
        this.dominioDelIncompatiblepthisDominioInstance = dominioDelIncompatiblepthisDominio;
        this.dominioDelIncompatiblepthisDominio = dominioDelIncompatiblepthisDominio != null ? dominioDelIncompatiblepthisDominio.getOid() : null;
    }

    private void checkDominioDelIncompatiblepthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelIncompatiblepthisDominioInstance() == null || this.getDominioDelIncompatiblepthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELINCOMPATIBLE_DOMINIODELINCOMPATIBLEPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELINCOMPATIBLE_DOMINIODELINCOMPATIBLEPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public DominioOid getDominioDelIncompatiblepevcDominio() {
        return this.dominioDelIncompatiblepevcDominio;
    }
    
    public void setDominioDelIncompatiblepevcDominio(DominioOid dominioDelIncompatiblepevcDominio) {
        this.dominioDelIncompatiblepevcDominio = dominioDelIncompatiblepevcDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelIncompatiblepevcDominioInstance() {
        return this.dominioDelIncompatiblepevcDominioInstance;
    }
    
    public void setDominioDelIncompatiblepevcDominioInstance(Dominio dominioDelIncompatiblepevcDominio) {
        this.dominioDelIncompatiblepevcDominioInstance = dominioDelIncompatiblepevcDominio;
        this.dominioDelIncompatiblepevcDominio = dominioDelIncompatiblepevcDominio != null ? dominioDelIncompatiblepevcDominio.getOid() : null;
    }

    private void checkDominioDelIncompatiblepevcDominio() throws NotNullArgumentException {
        if(this.getDominioDelIncompatiblepevcDominioInstance() == null || this.getDominioDelIncompatiblepevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELINCOMPATIBLE_DOMINIODELINCOMPATIBLEPEVCDOMINIO, DominioConstants.SERV_ALIAS_DELINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELINCOMPATIBLE_DOMINIODELINCOMPATIBLEPEVCDOMINIO);
        }
    }
}
