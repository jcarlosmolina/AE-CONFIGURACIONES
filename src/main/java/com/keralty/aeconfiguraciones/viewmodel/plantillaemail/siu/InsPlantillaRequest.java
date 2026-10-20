package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class InsPlantillaRequest {

    @JsonProperty("p_thisplantillaemail_oid")
    private PlantillaEmailOid plantillaEmailInsPlantillapthisPlantillaEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailInsPlantillapthisPlantillaEmailInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid plantillaEmailInsPlantillapevcDominio;
    
    @JsonIgnore
    private Dominio plantillaEmailInsPlantillapevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailInsPlantillapthisPlantillaEmail();
        checkPlantillaEmailInsPlantillapevcDominio();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailInsPlantillapthisPlantillaEmail() {
        return this.plantillaEmailInsPlantillapthisPlantillaEmail;
    }
    
    public void setPlantillaEmailInsPlantillapthisPlantillaEmail(PlantillaEmailOid plantillaEmailInsPlantillapthisPlantillaEmail) {
        this.plantillaEmailInsPlantillapthisPlantillaEmail = plantillaEmailInsPlantillapthisPlantillaEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmailInsPlantillapthisPlantillaEmailInstance() {
        return this.plantillaEmailInsPlantillapthisPlantillaEmailInstance;
    }
    
    public void setPlantillaEmailInsPlantillapthisPlantillaEmailInstance(PlantillaEmail plantillaEmailInsPlantillapthisPlantillaEmail) {
        this.plantillaEmailInsPlantillapthisPlantillaEmailInstance = plantillaEmailInsPlantillapthisPlantillaEmail;
        this.plantillaEmailInsPlantillapthisPlantillaEmail = plantillaEmailInsPlantillapthisPlantillaEmail != null ? plantillaEmailInsPlantillapthisPlantillaEmail.getOid() : null;
    }

    private void checkPlantillaEmailInsPlantillapthisPlantillaEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmailInsPlantillapthisPlantillaEmailInstance() == null || this.getPlantillaEmailInsPlantillapthisPlantillaEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_INSPLANTILLA, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_INSPLANTILLA_PLANTILLAEMAILINSPLANTILLAPTHISPLANTILLAEMAIL, PlantillaEmailConstants.SERV_ALIAS_INSPLANTILLA, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_INSPLANTILLA_PLANTILLAEMAILINSPLANTILLAPTHISPLANTILLAEMAIL);
        }
    }

    @JsonIgnore
    public DominioOid getPlantillaEmailInsPlantillapevcDominio() {
        return this.plantillaEmailInsPlantillapevcDominio;
    }
    
    public void setPlantillaEmailInsPlantillapevcDominio(DominioOid plantillaEmailInsPlantillapevcDominio) {
        this.plantillaEmailInsPlantillapevcDominio = plantillaEmailInsPlantillapevcDominio;
    }

    @JsonIgnore
    public Dominio getPlantillaEmailInsPlantillapevcDominioInstance() {
        return this.plantillaEmailInsPlantillapevcDominioInstance;
    }
    
    public void setPlantillaEmailInsPlantillapevcDominioInstance(Dominio plantillaEmailInsPlantillapevcDominio) {
        this.plantillaEmailInsPlantillapevcDominioInstance = plantillaEmailInsPlantillapevcDominio;
        this.plantillaEmailInsPlantillapevcDominio = plantillaEmailInsPlantillapevcDominio != null ? plantillaEmailInsPlantillapevcDominio.getOid() : null;
    }

    private void checkPlantillaEmailInsPlantillapevcDominio() throws NotNullArgumentException {
        if(this.getPlantillaEmailInsPlantillapevcDominioInstance() == null || this.getPlantillaEmailInsPlantillapevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_INSPLANTILLA, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_INSPLANTILLA_PLANTILLAEMAILINSPLANTILLAPEVCDOMINIO, PlantillaEmailConstants.SERV_ALIAS_INSPLANTILLA, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_INSPLANTILLA_PLANTILLAEMAILINSPLANTILLAPEVCDOMINIO);
        }
    }
}
