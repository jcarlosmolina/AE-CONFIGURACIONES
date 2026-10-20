package com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaEmailConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long plantillaEmailcrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String plantillaEmailcrearpatrDescripcion;

    @JsonProperty("p_atrasunto")
    private String plantillaEmailcrearpatrAsunto;

    @JsonProperty("p_atrtextomensaje")
    private String plantillaEmailcrearpatrTextoMensaje;

    @JsonProperty("p_atremailconf")
    private String plantillaEmailcrearpatrEmailConf;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaEmailcrearpatrCodigo();
        checkPlantillaEmailcrearpatrDescripcion();
        checkPlantillaEmailcrearpatrTextoMensaje();
    }

    @JsonIgnore
    public Long getPlantillaEmailcrearpatrCodigo() {
        return this.plantillaEmailcrearpatrCodigo;
    }
    
    public void setPlantillaEmailcrearpatrCodigo(Long plantillaEmailcrearpatrCodigo) {
        this.plantillaEmailcrearpatrCodigo = plantillaEmailcrearpatrCodigo;
    }

    private void checkPlantillaEmailcrearpatrCodigo() throws NotNullArgumentException {
        if(this.getPlantillaEmailcrearpatrCodigo() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_CREAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_CREAR_PLANTILLAEMAILCREARPATRCODIGO, PlantillaEmailConstants.SERV_ALIAS_CREAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_CREAR_PLANTILLAEMAILCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailcrearpatrDescripcion() {
        return this.plantillaEmailcrearpatrDescripcion;
    }
    
    public void setPlantillaEmailcrearpatrDescripcion(String plantillaEmailcrearpatrDescripcion) {
        this.plantillaEmailcrearpatrDescripcion = plantillaEmailcrearpatrDescripcion;
    }

    private void checkPlantillaEmailcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getPlantillaEmailcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_CREAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_CREAR_PLANTILLAEMAILCREARPATRDESCRIPCION, PlantillaEmailConstants.SERV_ALIAS_CREAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_CREAR_PLANTILLAEMAILCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailcrearpatrAsunto() {
        return this.plantillaEmailcrearpatrAsunto;
    }
    
    public void setPlantillaEmailcrearpatrAsunto(String plantillaEmailcrearpatrAsunto) {
        this.plantillaEmailcrearpatrAsunto = plantillaEmailcrearpatrAsunto;
    }

    @JsonIgnore
    public String getPlantillaEmailcrearpatrTextoMensaje() {
        return this.plantillaEmailcrearpatrTextoMensaje;
    }
    
    public void setPlantillaEmailcrearpatrTextoMensaje(String plantillaEmailcrearpatrTextoMensaje) {
        this.plantillaEmailcrearpatrTextoMensaje = plantillaEmailcrearpatrTextoMensaje;
    }

    private void checkPlantillaEmailcrearpatrTextoMensaje() throws NotNullArgumentException {
        if(this.getPlantillaEmailcrearpatrTextoMensaje() == null) {
            throw new NotNullArgumentException(PlantillaEmailConstants.SERV_ID_CREAR, PlantillaEmailConstants.CLASS_ID, PlantillaEmailConstants.ARG_ID_CREAR_PLANTILLAEMAILCREARPATRTEXTOMENSAJE, PlantillaEmailConstants.SERV_ALIAS_CREAR, PlantillaEmailConstants.CLASS_ALIAS, PlantillaEmailConstants.ARG_ALIAS_CREAR_PLANTILLAEMAILCREARPATRTEXTOMENSAJE);
        }
    }

    @JsonIgnore
    public String getPlantillaEmailcrearpatrEmailConf() {
        return this.plantillaEmailcrearpatrEmailConf;
    }
    
    public void setPlantillaEmailcrearpatrEmailConf(String plantillaEmailcrearpatrEmailConf) {
        this.plantillaEmailcrearpatrEmailConf = plantillaEmailcrearpatrEmailConf;
    }
}
