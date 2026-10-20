package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TINSINCOMPATIBLERequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTINSINCOMPATIBLEpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTINSINCOMPATIBLEpthisDominioInstance;

    @JsonProperty("pt_p_evcdominio_oid")
    private DominioOid dominioTINSINCOMPATIBLEptpevcDominio;
    
    @JsonIgnore
    private Dominio dominioTINSINCOMPATIBLEptpevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTINSINCOMPATIBLEpthisDominio();
        checkDominioTINSINCOMPATIBLEptpevcDominio();
    }

    @JsonIgnore
    public DominioOid getDominioTINSINCOMPATIBLEpthisDominio() {
        return this.dominioTINSINCOMPATIBLEpthisDominio;
    }
    
    public void setDominioTINSINCOMPATIBLEpthisDominio(DominioOid dominioTINSINCOMPATIBLEpthisDominio) {
        this.dominioTINSINCOMPATIBLEpthisDominio = dominioTINSINCOMPATIBLEpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTINSINCOMPATIBLEpthisDominioInstance() {
        return this.dominioTINSINCOMPATIBLEpthisDominioInstance;
    }
    
    public void setDominioTINSINCOMPATIBLEpthisDominioInstance(Dominio dominioTINSINCOMPATIBLEpthisDominio) {
        this.dominioTINSINCOMPATIBLEpthisDominioInstance = dominioTINSINCOMPATIBLEpthisDominio;
        this.dominioTINSINCOMPATIBLEpthisDominio = dominioTINSINCOMPATIBLEpthisDominio != null ? dominioTINSINCOMPATIBLEpthisDominio.getOid() : null;
    }

    private void checkDominioTINSINCOMPATIBLEpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTINSINCOMPATIBLEpthisDominioInstance() == null || this.getDominioTINSINCOMPATIBLEpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSINCOMPATIBLE_DOMINIOTINSINCOMPATIBLEPTHISDOMINIO, DominioConstants.SERV_ALIAS_TINSINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSINCOMPATIBLE_DOMINIOTINSINCOMPATIBLEPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public DominioOid getDominioTINSINCOMPATIBLEptpevcDominio() {
        return this.dominioTINSINCOMPATIBLEptpevcDominio;
    }
    
    public void setDominioTINSINCOMPATIBLEptpevcDominio(DominioOid dominioTINSINCOMPATIBLEptpevcDominio) {
        this.dominioTINSINCOMPATIBLEptpevcDominio = dominioTINSINCOMPATIBLEptpevcDominio;
    }

    @JsonIgnore
    public Dominio getDominioTINSINCOMPATIBLEptpevcDominioInstance() {
        return this.dominioTINSINCOMPATIBLEptpevcDominioInstance;
    }
    
    public void setDominioTINSINCOMPATIBLEptpevcDominioInstance(Dominio dominioTINSINCOMPATIBLEptpevcDominio) {
        this.dominioTINSINCOMPATIBLEptpevcDominioInstance = dominioTINSINCOMPATIBLEptpevcDominio;
        this.dominioTINSINCOMPATIBLEptpevcDominio = dominioTINSINCOMPATIBLEptpevcDominio != null ? dominioTINSINCOMPATIBLEptpevcDominio.getOid() : null;
    }

    private void checkDominioTINSINCOMPATIBLEptpevcDominio() throws NotNullArgumentException {
        if(this.getDominioTINSINCOMPATIBLEptpevcDominioInstance() == null || this.getDominioTINSINCOMPATIBLEptpevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSINCOMPATIBLE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSINCOMPATIBLE_DOMINIOTINSINCOMPATIBLEPTPEVCDOMINIO, DominioConstants.SERV_ALIAS_TINSINCOMPATIBLE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSINCOMPATIBLE_DOMINIOTINSINCOMPATIBLEPTPEVCDOMINIO);
        }
    }
}
