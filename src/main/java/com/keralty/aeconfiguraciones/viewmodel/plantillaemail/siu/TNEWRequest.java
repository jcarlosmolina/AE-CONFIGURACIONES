package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;

public class TNEWRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long plantillaEmailTNEWptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String plantillaEmailTNEWptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailTNEWptpatrCodigo();
        checkPlantillaEmailTNEWptpatrDescripcion();
    }

    @JsonIgnore
    public Long getPlantillaEmailTNEWptpatrCodigo() {
        return this.plantillaEmailTNEWptpatrCodigo;
    }
    
    public void setPlantillaEmailTNEWptpatrCodigo(Long plantillaEmailTNEWptpatrCodigo) {
        this.plantillaEmailTNEWptpatrCodigo = plantillaEmailTNEWptpatrCodigo;
    }

    private void checkPlantillaEmailTNEWptpatrCodigo() throws NotNullArgumentException {
        if(this.getPlantillaEmailTNEWptpatrCodigo() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_TNEW, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_TNEW_PLANTILLAEMAILTNEWPTPATRCODIGO, PlantillaEmailConstants.SERV_ALIAS_TNEW, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_TNEW_PLANTILLAEMAILTNEWPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailTNEWptpatrDescripcion() {
        return this.plantillaEmailTNEWptpatrDescripcion;
    }
    
    public void setPlantillaEmailTNEWptpatrDescripcion(String plantillaEmailTNEWptpatrDescripcion) {
        this.plantillaEmailTNEWptpatrDescripcion = plantillaEmailTNEWptpatrDescripcion;
    }

    private void checkPlantillaEmailTNEWptpatrDescripcion() throws NotNullArgumentException {
        if(this.getPlantillaEmailTNEWptpatrDescripcion() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_TNEW, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_TNEW_PLANTILLAEMAILTNEWPTPATRDESCRIPCION, PlantillaEmailConstants.SERV_ALIAS_TNEW, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_TNEW_PLANTILLAEMAILTNEWPTPATRDESCRIPCION);
        }
    }
}
