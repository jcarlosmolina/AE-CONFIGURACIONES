package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class DelPlantillaRequest {

    @JsonProperty("p_thisplantillaemail_oid")
    private PlantillaEmailOid plantillaEmailDelPlantillapthisPlantillaEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailDelPlantillapthisPlantillaEmailInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid plantillaEmailDelPlantillapevcDominio;
    
    @JsonIgnore
    private Dominio plantillaEmailDelPlantillapevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailDelPlantillapthisPlantillaEmail();
        checkPlantillaEmailDelPlantillapevcDominio();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailDelPlantillapthisPlantillaEmail() {
        return this.plantillaEmailDelPlantillapthisPlantillaEmail;
    }
    
    public void setPlantillaEmailDelPlantillapthisPlantillaEmail(PlantillaEmailOid plantillaEmailDelPlantillapthisPlantillaEmail) {
        this.plantillaEmailDelPlantillapthisPlantillaEmail = plantillaEmailDelPlantillapthisPlantillaEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmailDelPlantillapthisPlantillaEmailInstance() {
        return this.plantillaEmailDelPlantillapthisPlantillaEmailInstance;
    }
    
    public void setPlantillaEmailDelPlantillapthisPlantillaEmailInstance(PlantillaEmail plantillaEmailDelPlantillapthisPlantillaEmail) {
        this.plantillaEmailDelPlantillapthisPlantillaEmailInstance = plantillaEmailDelPlantillapthisPlantillaEmail;
        this.plantillaEmailDelPlantillapthisPlantillaEmail = plantillaEmailDelPlantillapthisPlantillaEmail != null ? plantillaEmailDelPlantillapthisPlantillaEmail.getOid() : null;
    }

    private void checkPlantillaEmailDelPlantillapthisPlantillaEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmailDelPlantillapthisPlantillaEmailInstance() == null || this.getPlantillaEmailDelPlantillapthisPlantillaEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_DELPLANTILLA, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_DELPLANTILLA_PLANTILLAEMAILDELPLANTILLAPTHISPLANTILLAEMAIL, PlantillaEmailConstants.SERV_ALIAS_DELPLANTILLA, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_DELPLANTILLA_PLANTILLAEMAILDELPLANTILLAPTHISPLANTILLAEMAIL);
        }
    }

    @JsonIgnore
    public DominioOid getPlantillaEmailDelPlantillapevcDominio() {
        return this.plantillaEmailDelPlantillapevcDominio;
    }
    
    public void setPlantillaEmailDelPlantillapevcDominio(DominioOid plantillaEmailDelPlantillapevcDominio) {
        this.plantillaEmailDelPlantillapevcDominio = plantillaEmailDelPlantillapevcDominio;
    }

    @JsonIgnore
    public Dominio getPlantillaEmailDelPlantillapevcDominioInstance() {
        return this.plantillaEmailDelPlantillapevcDominioInstance;
    }
    
    public void setPlantillaEmailDelPlantillapevcDominioInstance(Dominio plantillaEmailDelPlantillapevcDominio) {
        this.plantillaEmailDelPlantillapevcDominioInstance = plantillaEmailDelPlantillapevcDominio;
        this.plantillaEmailDelPlantillapevcDominio = plantillaEmailDelPlantillapevcDominio != null ? plantillaEmailDelPlantillapevcDominio.getOid() : null;
    }

    private void checkPlantillaEmailDelPlantillapevcDominio() throws NotNullArgumentException {
        if(this.getPlantillaEmailDelPlantillapevcDominioInstance() == null || this.getPlantillaEmailDelPlantillapevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_DELPLANTILLA, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_DELPLANTILLA_PLANTILLAEMAILDELPLANTILLAPEVCDOMINIO, PlantillaEmailConstants.SERV_ALIAS_DELPLANTILLA, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_DELPLANTILLA_PLANTILLAEMAILDELPLANTILLAPEVCDOMINIO);
        }
    }
}
