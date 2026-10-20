package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class DelPlantillaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelPlantillapthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelPlantillapthisDominioInstance;

    @JsonProperty("p_evcplantillaemail_oid")
    private PlantillaEmailOid dominioDelPlantillapevcPlantillaEmail;
    
    @JsonIgnore
    private PlantillaEmail dominioDelPlantillapevcPlantillaEmailInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelPlantillapthisDominio();
        checkDominioDelPlantillapevcPlantillaEmail();
    }

    @JsonIgnore
    public DominioOid getDominioDelPlantillapthisDominio() {
        return this.dominioDelPlantillapthisDominio;
    }
    
    public void setDominioDelPlantillapthisDominio(DominioOid dominioDelPlantillapthisDominio) {
        this.dominioDelPlantillapthisDominio = dominioDelPlantillapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelPlantillapthisDominioInstance() {
        return this.dominioDelPlantillapthisDominioInstance;
    }
    
    public void setDominioDelPlantillapthisDominioInstance(Dominio dominioDelPlantillapthisDominio) {
        this.dominioDelPlantillapthisDominioInstance = dominioDelPlantillapthisDominio;
        this.dominioDelPlantillapthisDominio = dominioDelPlantillapthisDominio != null ? dominioDelPlantillapthisDominio.getOid() : null;
    }

    private void checkDominioDelPlantillapthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelPlantillapthisDominioInstance() == null || this.getDominioDelPlantillapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELPLANTILLA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELPLANTILLA_DOMINIODELPLANTILLAPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELPLANTILLA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELPLANTILLA_DOMINIODELPLANTILLAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public PlantillaEmailOid getDominioDelPlantillapevcPlantillaEmail() {
        return this.dominioDelPlantillapevcPlantillaEmail;
    }
    
    public void setDominioDelPlantillapevcPlantillaEmail(PlantillaEmailOid dominioDelPlantillapevcPlantillaEmail) {
        this.dominioDelPlantillapevcPlantillaEmail = dominioDelPlantillapevcPlantillaEmail;
    }

    @JsonIgnore
    public PlantillaEmail getDominioDelPlantillapevcPlantillaEmailInstance() {
        return this.dominioDelPlantillapevcPlantillaEmailInstance;
    }
    
    public void setDominioDelPlantillapevcPlantillaEmailInstance(PlantillaEmail dominioDelPlantillapevcPlantillaEmail) {
        this.dominioDelPlantillapevcPlantillaEmailInstance = dominioDelPlantillapevcPlantillaEmail;
        this.dominioDelPlantillapevcPlantillaEmail = dominioDelPlantillapevcPlantillaEmail != null ? dominioDelPlantillapevcPlantillaEmail.getOid() : null;
    }

    private void checkDominioDelPlantillapevcPlantillaEmail() throws NotNullArgumentException {
        if(this.getDominioDelPlantillapevcPlantillaEmailInstance() == null || this.getDominioDelPlantillapevcPlantillaEmailInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELPLANTILLA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELPLANTILLA_DOMINIODELPLANTILLAPEVCPLANTILLAEMAIL, DominioConstants.SERV_ALIAS_DELPLANTILLA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELPLANTILLA_DOMINIODELPLANTILLAPEVCPLANTILLAEMAIL);
        }
    }
}
