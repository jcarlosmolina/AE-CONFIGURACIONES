package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class InsIncompatibleRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioInsIncompatiblepthisDominio;
    
    @JsonIgnore
    private Dominio dominioInsIncompatiblepthisDominioInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid dominioInsIncompatiblepevcDominio;
    
    @JsonIgnore
    private Dominio dominioInsIncompatiblepevcDominioInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioInsIncompatiblepthisDominio();
        checkDominioInsIncompatiblepevcDominio();
    }

    @JsonIgnore
    public DominioOid getDominioInsIncompatiblepthisDominio() {
        return this.dominioInsIncompatiblepthisDominio;
    }
    
    public void setDominioInsIncompatiblepthisDominio(DominioOid dominioInsIncompatiblepthisDominio) {
        this.dominioInsIncompatiblepthisDominio = dominioInsIncompatiblepthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsIncompatiblepthisDominioInstance() {
        return this.dominioInsIncompatiblepthisDominioInstance;
    }
    
    public void setDominioInsIncompatiblepthisDominioInstance(Dominio dominioInsIncompatiblepthisDominio) {
        this.dominioInsIncompatiblepthisDominioInstance = dominioInsIncompatiblepthisDominio;
        this.dominioInsIncompatiblepthisDominio = dominioInsIncompatiblepthisDominio != null ? dominioInsIncompatiblepthisDominio.getOid() : null;
    }

    private void checkDominioInsIncompatiblepthisDominio() throws NotNullArgumentException {
        if(this.getDominioInsIncompatiblepthisDominioInstance() == null || this.getDominioInsIncompatiblepthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPTHISDOMINIO, DominioConstants.SERV_ALIAS_INSINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public DominioOid getDominioInsIncompatiblepevcDominio() {
        return this.dominioInsIncompatiblepevcDominio;
    }
    
    public void setDominioInsIncompatiblepevcDominio(DominioOid dominioInsIncompatiblepevcDominio) {
        this.dominioInsIncompatiblepevcDominio = dominioInsIncompatiblepevcDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsIncompatiblepevcDominioInstance() {
        return this.dominioInsIncompatiblepevcDominioInstance;
    }
    
    public void setDominioInsIncompatiblepevcDominioInstance(Dominio dominioInsIncompatiblepevcDominio) {
        this.dominioInsIncompatiblepevcDominioInstance = dominioInsIncompatiblepevcDominio;
        this.dominioInsIncompatiblepevcDominio = dominioInsIncompatiblepevcDominio != null ? dominioInsIncompatiblepevcDominio.getOid() : null;
    }

    private void checkDominioInsIncompatiblepevcDominio() throws NotNullArgumentException {
        if(this.getDominioInsIncompatiblepevcDominioInstance() == null || this.getDominioInsIncompatiblepevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPEVCDOMINIO, DominioConstants.SERV_ALIAS_INSINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSINCOMPATIBLE_DOMINIOINSINCOMPATIBLEPEVCDOMINIO);
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
