package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class ModificarTextoMensajeRequest {

    @JsonProperty("p_thisplantillaemail_oid")
    private PlantillaEmailOid plantillaEmailmodificarTextoMensajepthisPlantillaEmail;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance;

    @JsonProperty("p_textomensaje")
    private String plantillaEmailmodificarTextoMensajepTextoMensaje;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailmodificarTextoMensajepthisPlantillaEmail();
        checkPlantillaEmailmodificarTextoMensajepTextoMensaje();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailmodificarTextoMensajepthisPlantillaEmail() {
        return this.plantillaEmailmodificarTextoMensajepthisPlantillaEmail;
    }
    
    public void setPlantillaEmailmodificarTextoMensajepthisPlantillaEmail(PlantillaEmailOid plantillaEmailmodificarTextoMensajepthisPlantillaEmail) {
        this.plantillaEmailmodificarTextoMensajepthisPlantillaEmail = plantillaEmailmodificarTextoMensajepthisPlantillaEmail;
    }

    @JsonIgnore
    public PlantillaEmail getPlantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance() {
        return this.plantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance;
    }
    
    public void setPlantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance(PlantillaEmail plantillaEmailmodificarTextoMensajepthisPlantillaEmail) {
        this.plantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance = plantillaEmailmodificarTextoMensajepthisPlantillaEmail;
        this.plantillaEmailmodificarTextoMensajepthisPlantillaEmail = plantillaEmailmodificarTextoMensajepthisPlantillaEmail != null ? plantillaEmailmodificarTextoMensajepthisPlantillaEmail.getOid() : null;
    }

    private void checkPlantillaEmailmodificarTextoMensajepthisPlantillaEmail() throws NotNullArgumentException {
        if(this.getPlantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance() == null || this.getPlantillaEmailmodificarTextoMensajepthisPlantillaEmailInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_MODIFICARTEXTOMENSAJE, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_MODIFICARTEXTOMENSAJE_PLANTILLAEMAILMODIFICARTEXTOMENSAJEPTHISPLANTILLAEMAIL, PlantillaEmailConstants.SERV_ALIAS_MODIFICARTEXTOMENSAJE, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_MODIFICARTEXTOMENSAJE_PLANTILLAEMAILMODIFICARTEXTOMENSAJEPTHISPLANTILLAEMAIL);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailmodificarTextoMensajepTextoMensaje() {
        return this.plantillaEmailmodificarTextoMensajepTextoMensaje;
    }
    
    public void setPlantillaEmailmodificarTextoMensajepTextoMensaje(String plantillaEmailmodificarTextoMensajepTextoMensaje) {
        this.plantillaEmailmodificarTextoMensajepTextoMensaje = plantillaEmailmodificarTextoMensajepTextoMensaje;
    }

    private void checkPlantillaEmailmodificarTextoMensajepTextoMensaje() throws NotNullArgumentException {
        if(this.getPlantillaEmailmodificarTextoMensajepTextoMensaje() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_MODIFICARTEXTOMENSAJE, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_MODIFICARTEXTOMENSAJE_PLANTILLAEMAILMODIFICARTEXTOMENSAJEPTEXTOMENSAJE, PlantillaEmailConstants.SERV_ALIAS_MODIFICARTEXTOMENSAJE, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_MODIFICARTEXTOMENSAJE_PLANTILLAEMAILMODIFICARTEXTOMENSAJEPTEXTOMENSAJE);
        }
    }
}
