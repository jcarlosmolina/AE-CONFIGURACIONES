package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import java.util.HashMap;
import java.util.Map;

public class TNEWResponse extends ServiceResponse {


    @JsonProperty("psplantilla")
    private PlantillaEmailOid plantillaEmailTNEWpsPlantilla;
    
    @JsonIgnore
    private PlantillaEmail plantillaEmailTNEWpsPlantillaInstance;

    public TNEWResponse() {
        super();
    }

    @JsonIgnore
    public PlantillaEmailOid getPlantillaEmailTNEWpsPlantilla() {
        return this.plantillaEmailTNEWpsPlantilla;
    }
    
    public void setPlantillaEmailTNEWpsPlantilla(PlantillaEmailOid plantillaEmailTNEWpsPlantilla) {
        this.plantillaEmailTNEWpsPlantilla = plantillaEmailTNEWpsPlantilla;
    }
    @JsonIgnore
    public PlantillaEmail getPlantillaEmailTNEWpsPlantillaInstance() {
        return this.plantillaEmailTNEWpsPlantillaInstance;
    }
    
    public void setPlantillaEmailTNEWpsPlantillaInstance(PlantillaEmail plantillaEmailTNEWpsPlantilla) {
        if (plantillaEmailTNEWpsPlantilla != null && !plantillaEmailTNEWpsPlantilla.isNull()) {
            this.plantillaEmailTNEWpsPlantilla = plantillaEmailTNEWpsPlantilla.getOid();
            this.plantillaEmailTNEWpsPlantillaInstance = plantillaEmailTNEWpsPlantilla;
        } else {
            this.plantillaEmailTNEWpsPlantilla = null;
            this.plantillaEmailTNEWpsPlantillaInstance = null;
        }
    }

    @Override
    public Map<String, Object> buildOutArgs() {
        Map<String, Object> outArgs = new HashMap<>();
        outArgs.put("plantillaEmailTNEWpsPlantilla", plantillaEmailTNEWpsPlantilla);
        return outArgs;
    }
}
