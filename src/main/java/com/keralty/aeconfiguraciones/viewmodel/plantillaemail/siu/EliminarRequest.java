package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class EliminarRequest {

    @JsonProperty("p_thisplantillasemail_oid")
    private PlantillaEmailOid plantillaEmaileliminarpthisPlantillasEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmaileliminarpthisPlantillasEmailInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmaileliminarpthisPlantillasEmail();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmaileliminarpthisPlantillasEmail() {
        return this.plantillaEmaileliminarpthisPlantillasEmail;
    }
    
    public void setPlantillaEmaileliminarpthisPlantillasEmail(PlantillaEmailOid plantillaEmaileliminarpthisPlantillasEmail) {
        this.plantillaEmaileliminarpthisPlantillasEmail = plantillaEmaileliminarpthisPlantillasEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmaileliminarpthisPlantillasEmailInstance() {
        return this.plantillaEmaileliminarpthisPlantillasEmailInstance;
    }
    
    public void setPlantillaEmaileliminarpthisPlantillasEmailInstance(PlantillaEmail plantillaEmaileliminarpthisPlantillasEmail) {
        this.plantillaEmaileliminarpthisPlantillasEmailInstance = plantillaEmaileliminarpthisPlantillasEmail;
        this.plantillaEmaileliminarpthisPlantillasEmail = plantillaEmaileliminarpthisPlantillasEmail != null ? plantillaEmaileliminarpthisPlantillasEmail.getOid() : null;
    }

    private void checkPlantillaEmaileliminarpthisPlantillasEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmaileliminarpthisPlantillasEmailInstance() == null || this.getPlantillaEmaileliminarpthisPlantillasEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_ELIMINAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_ELIMINAR_PLANTILLAEMAILELIMINARPTHISPLANTILLASEMAIL, PlantillaEmailConstants.SERV_ALIAS_ELIMINAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_ELIMINAR_PLANTILLAEMAILELIMINARPTHISPLANTILLASEMAIL);
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
