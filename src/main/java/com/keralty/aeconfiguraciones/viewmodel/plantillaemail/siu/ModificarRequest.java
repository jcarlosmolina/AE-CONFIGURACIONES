package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class ModificarRequest {

    @JsonProperty("p_thisplantillasemail_oid")
    private PlantillaEmailOid plantillaEmailmodificarpthisPlantillasEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailmodificarpthisPlantillasEmailInstance;

    @JsonProperty("p_asunto")
    private String plantillaEmailmodificarpAsunto;

    @JsonProperty("p_textomensaje")
    private String plantillaEmailmodificarpTextoMensaje;

    @JsonProperty("p_emailconf")
    private String plantillaEmailmodificarpEmailConf;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailmodificarpthisPlantillasEmail();
        checkPlantillaEmailmodificarpAsunto();
        checkPlantillaEmailmodificarpTextoMensaje();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailmodificarpthisPlantillasEmail() {
        return this.plantillaEmailmodificarpthisPlantillasEmail;
    }
    
    public void setPlantillaEmailmodificarpthisPlantillasEmail(PlantillaEmailOid plantillaEmailmodificarpthisPlantillasEmail) {
        this.plantillaEmailmodificarpthisPlantillasEmail = plantillaEmailmodificarpthisPlantillasEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmailmodificarpthisPlantillasEmailInstance() {
        return this.plantillaEmailmodificarpthisPlantillasEmailInstance;
    }
    
    public void setPlantillaEmailmodificarpthisPlantillasEmailInstance(PlantillaEmail plantillaEmailmodificarpthisPlantillasEmail) {
        this.plantillaEmailmodificarpthisPlantillasEmailInstance = plantillaEmailmodificarpthisPlantillasEmail;
        this.plantillaEmailmodificarpthisPlantillasEmail = plantillaEmailmodificarpthisPlantillasEmail != null ? plantillaEmailmodificarpthisPlantillasEmail.getOid() : null;
    }

    private void checkPlantillaEmailmodificarpthisPlantillasEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmailmodificarpthisPlantillasEmailInstance() == null || this.getPlantillaEmailmodificarpthisPlantillasEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_MODIFICAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_MODIFICAR_PLANTILLAEMAILMODIFICARPTHISPLANTILLASEMAIL, PlantillaEmailConstants.SERV_ALIAS_MODIFICAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_MODIFICAR_PLANTILLAEMAILMODIFICARPTHISPLANTILLASEMAIL);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailmodificarpAsunto() {
        return this.plantillaEmailmodificarpAsunto;
    }
    
    public void setPlantillaEmailmodificarpAsunto(String plantillaEmailmodificarpAsunto) {
        this.plantillaEmailmodificarpAsunto = plantillaEmailmodificarpAsunto;
    }

    private void checkPlantillaEmailmodificarpAsunto() throws NotNullArgumentException {
        if(this.getPlantillaEmailmodificarpAsunto() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_MODIFICAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_MODIFICAR_PLANTILLAEMAILMODIFICARPASUNTO, PlantillaEmailConstants.SERV_ALIAS_MODIFICAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_MODIFICAR_PLANTILLAEMAILMODIFICARPASUNTO);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailmodificarpTextoMensaje() {
        return this.plantillaEmailmodificarpTextoMensaje;
    }
    
    public void setPlantillaEmailmodificarpTextoMensaje(String plantillaEmailmodificarpTextoMensaje) {
        this.plantillaEmailmodificarpTextoMensaje = plantillaEmailmodificarpTextoMensaje;
    }

    private void checkPlantillaEmailmodificarpTextoMensaje() throws NotNullArgumentException {
        if(this.getPlantillaEmailmodificarpTextoMensaje() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_MODIFICAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_MODIFICAR_PLANTILLAEMAILMODIFICARPTEXTOMENSAJE, PlantillaEmailConstants.SERV_ALIAS_MODIFICAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_MODIFICAR_PLANTILLAEMAILMODIFICARPTEXTOMENSAJE);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailmodificarpEmailConf() {
        return this.plantillaEmailmodificarpEmailConf;
    }
    
    public void setPlantillaEmailmodificarpEmailConf(String plantillaEmailmodificarpEmailConf) {
        this.plantillaEmailmodificarpEmailConf = plantillaEmailmodificarpEmailConf;
    }
}
