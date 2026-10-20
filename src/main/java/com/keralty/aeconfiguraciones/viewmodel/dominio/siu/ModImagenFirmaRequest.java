package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class ModImagenFirmaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominiomodImagenFirmapthisDominio;
    
    @JsonIgnore
    private Dominio dominiomodImagenFirmapthisDominioInstance;

    @JsonProperty("pfirma")
    private byte[] dominiomodImagenFirmapFirma;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiomodImagenFirmapthisDominio();
        checkDominiomodImagenFirmapFirma();
    }

    @JsonIgnore
    public DominioOid getDominiomodImagenFirmapthisDominio() {
        return this.dominiomodImagenFirmapthisDominio;
    }
    
    public void setDominiomodImagenFirmapthisDominio(DominioOid dominiomodImagenFirmapthisDominio) {
        this.dominiomodImagenFirmapthisDominio = dominiomodImagenFirmapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominiomodImagenFirmapthisDominioInstance() {
        return this.dominiomodImagenFirmapthisDominioInstance;
    }
    
    public void setDominiomodImagenFirmapthisDominioInstance(Dominio dominiomodImagenFirmapthisDominio) {
        this.dominiomodImagenFirmapthisDominioInstance = dominiomodImagenFirmapthisDominio;
        this.dominiomodImagenFirmapthisDominio = dominiomodImagenFirmapthisDominio != null ? dominiomodImagenFirmapthisDominio.getOid() : null;
    }

    private void checkDominiomodImagenFirmapthisDominio() throws NotNullArgumentException {
        if(this.getDominiomodImagenFirmapthisDominioInstance() == null || this.getDominiomodImagenFirmapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIMAGENFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIMAGENFIRMA_DOMINIOMODIMAGENFIRMAPTHISDOMINIO, DominioConstants.SERV_ALIAS_MODIMAGENFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIMAGENFIRMA_DOMINIOMODIMAGENFIRMAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public byte[] getDominiomodImagenFirmapFirma() {
        return this.dominiomodImagenFirmapFirma;
    }
    
    public void setDominiomodImagenFirmapFirma(byte[] dominiomodImagenFirmapFirma) {
        this.dominiomodImagenFirmapFirma = dominiomodImagenFirmapFirma;
    }

    private void checkDominiomodImagenFirmapFirma() throws NotNullArgumentException {
        if(this.getDominiomodImagenFirmapFirma() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIMAGENFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIMAGENFIRMA_DOMINIOMODIMAGENFIRMAPFIRMA, DominioConstants.SERV_ALIAS_MODIMAGENFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIMAGENFIRMA_DOMINIOMODIMAGENFIRMAPFIRMA);
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
