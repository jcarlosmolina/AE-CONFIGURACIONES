package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class ModDatosRespFirmaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominiomodDatosRespFirmapthisDominio;
    
    @JsonIgnore
    private Dominio dominiomodDatosRespFirmapthisDominioInstance;

    @JsonProperty("pnombre")
    private String dominiomodDatosRespFirmapNombre;

    @JsonProperty("pnumcelula")
    private String dominiomodDatosRespFirmapNumCelula;

    @JsonProperty("pexpedicioncelula")
    private String dominiomodDatosRespFirmapExpedicionCelula;

    @JsonProperty("pcargo")
    private String dominiomodDatosRespFirmapCargo;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiomodDatosRespFirmapthisDominio();
        checkDominiomodDatosRespFirmapNombre();
        checkDominiomodDatosRespFirmapNumCelula();
        checkDominiomodDatosRespFirmapExpedicionCelula();
        checkDominiomodDatosRespFirmapCargo();
    }

    @JsonIgnore
    public DominioOid getDominiomodDatosRespFirmapthisDominio() {
        return this.dominiomodDatosRespFirmapthisDominio;
    }
    
    public void setDominiomodDatosRespFirmapthisDominio(DominioOid dominiomodDatosRespFirmapthisDominio) {
        this.dominiomodDatosRespFirmapthisDominio = dominiomodDatosRespFirmapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominiomodDatosRespFirmapthisDominioInstance() {
        return this.dominiomodDatosRespFirmapthisDominioInstance;
    }
    
    public void setDominiomodDatosRespFirmapthisDominioInstance(Dominio dominiomodDatosRespFirmapthisDominio) {
        this.dominiomodDatosRespFirmapthisDominioInstance = dominiomodDatosRespFirmapthisDominio;
        this.dominiomodDatosRespFirmapthisDominio = dominiomodDatosRespFirmapthisDominio != null ? dominiomodDatosRespFirmapthisDominio.getOid() : null;
    }

    private void checkDominiomodDatosRespFirmapthisDominio() throws NotNullArgumentException {
        if(this.getDominiomodDatosRespFirmapthisDominioInstance() == null || this.getDominiomodDatosRespFirmapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODDATOSRESPFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPTHISDOMINIO, DominioConstants.SERV_ALIAS_MODDATOSRESPFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public String getDominiomodDatosRespFirmapNombre() {
        return this.dominiomodDatosRespFirmapNombre;
    }
    
    public void setDominiomodDatosRespFirmapNombre(String dominiomodDatosRespFirmapNombre) {
        this.dominiomodDatosRespFirmapNombre = dominiomodDatosRespFirmapNombre;
    }

    private void checkDominiomodDatosRespFirmapNombre() throws NotNullArgumentException {
        if(this.getDominiomodDatosRespFirmapNombre() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODDATOSRESPFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPNOMBRE, DominioConstants.SERV_ALIAS_MODDATOSRESPFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPNOMBRE);
        }
    }

    @JsonIgnore
    public String getDominiomodDatosRespFirmapNumCelula() {
        return this.dominiomodDatosRespFirmapNumCelula;
    }
    
    public void setDominiomodDatosRespFirmapNumCelula(String dominiomodDatosRespFirmapNumCelula) {
        this.dominiomodDatosRespFirmapNumCelula = dominiomodDatosRespFirmapNumCelula;
    }

    private void checkDominiomodDatosRespFirmapNumCelula() throws NotNullArgumentException {
        if(this.getDominiomodDatosRespFirmapNumCelula() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODDATOSRESPFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPNUMCELULA, DominioConstants.SERV_ALIAS_MODDATOSRESPFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPNUMCELULA);
        }
    }

    @JsonIgnore
    public String getDominiomodDatosRespFirmapExpedicionCelula() {
        return this.dominiomodDatosRespFirmapExpedicionCelula;
    }
    
    public void setDominiomodDatosRespFirmapExpedicionCelula(String dominiomodDatosRespFirmapExpedicionCelula) {
        this.dominiomodDatosRespFirmapExpedicionCelula = dominiomodDatosRespFirmapExpedicionCelula;
    }

    private void checkDominiomodDatosRespFirmapExpedicionCelula() throws NotNullArgumentException {
        if(this.getDominiomodDatosRespFirmapExpedicionCelula() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODDATOSRESPFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPEXPEDICIONCELULA, DominioConstants.SERV_ALIAS_MODDATOSRESPFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPEXPEDICIONCELULA);
        }
    }

    @JsonIgnore
    public String getDominiomodDatosRespFirmapCargo() {
        return this.dominiomodDatosRespFirmapCargo;
    }
    
    public void setDominiomodDatosRespFirmapCargo(String dominiomodDatosRespFirmapCargo) {
        this.dominiomodDatosRespFirmapCargo = dominiomodDatosRespFirmapCargo;
    }

    private void checkDominiomodDatosRespFirmapCargo() throws NotNullArgumentException {
        if(this.getDominiomodDatosRespFirmapCargo() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODDATOSRESPFIRMA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPCARGO, DominioConstants.SERV_ALIAS_MODDATOSRESPFIRMA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODDATOSRESPFIRMA_DOMINIOMODDATOSRESPFIRMAPCARGO);
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
