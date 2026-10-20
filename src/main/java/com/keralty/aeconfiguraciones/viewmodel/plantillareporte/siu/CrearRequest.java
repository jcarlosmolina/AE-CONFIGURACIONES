package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_atrtipo")
    private Long plantillaReportecrearpatrTipo;

    @JsonProperty("p_atrtitulo")
    private String plantillaReportecrearpatrTitulo;

    @JsonProperty("p_atrfechavigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date plantillaReportecrearpatrFechaVigencia;

    @JsonProperty("p_atrrutaarchivos")
    private String plantillaReportecrearpatrRutaArchivos;

    @JsonProperty("p_atrnombrearchivopdf")
    private String plantillaReportecrearpatrNombreArchivoPDF;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaReportecrearpatrTipo();
        checkPlantillaReportecrearpatrTitulo();
        checkPlantillaReportecrearpatrFechaVigencia();
        checkPlantillaReportecrearpatrRutaArchivos();
        checkPlantillaReportecrearpatrNombreArchivoPDF();
    }

    @JsonIgnore
    public Long getPlantillaReportecrearpatrTipo() {
        return this.plantillaReportecrearpatrTipo;
    }
    
    public void setPlantillaReportecrearpatrTipo(Long plantillaReportecrearpatrTipo) {
        this.plantillaReportecrearpatrTipo = plantillaReportecrearpatrTipo;
    }

    private void checkPlantillaReportecrearpatrTipo() throws NotNullArgumentException {
        if(this.getPlantillaReportecrearpatrTipo() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_CREAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_CREAR_PLANTILLAREPORTECREARPATRTIPO, PlantillaReporteConstants.SERV_ALIAS_CREAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_CREAR_PLANTILLAREPORTECREARPATRTIPO);
        }
    }

    @JsonIgnore
    public String getPlantillaReportecrearpatrTitulo() {
        return this.plantillaReportecrearpatrTitulo;
    }
    
    public void setPlantillaReportecrearpatrTitulo(String plantillaReportecrearpatrTitulo) {
        this.plantillaReportecrearpatrTitulo = plantillaReportecrearpatrTitulo;
    }

    private void checkPlantillaReportecrearpatrTitulo() throws NotNullArgumentException {
        if(this.getPlantillaReportecrearpatrTitulo() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_CREAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_CREAR_PLANTILLAREPORTECREARPATRTITULO, PlantillaReporteConstants.SERV_ALIAS_CREAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_CREAR_PLANTILLAREPORTECREARPATRTITULO);
        }
    }

    @JsonIgnore
    public Date getPlantillaReportecrearpatrFechaVigencia() {
        return this.plantillaReportecrearpatrFechaVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setPlantillaReportecrearpatrFechaVigencia(Date plantillaReportecrearpatrFechaVigencia) {
        this.plantillaReportecrearpatrFechaVigencia = plantillaReportecrearpatrFechaVigencia;
    }

    private void checkPlantillaReportecrearpatrFechaVigencia() throws NotNullArgumentException {
        if(this.getPlantillaReportecrearpatrFechaVigencia() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_CREAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_CREAR_PLANTILLAREPORTECREARPATRFECHAVIGENCIA, PlantillaReporteConstants.SERV_ALIAS_CREAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_CREAR_PLANTILLAREPORTECREARPATRFECHAVIGENCIA);
        }
    }

    @JsonIgnore
    public String getPlantillaReportecrearpatrRutaArchivos() {
        return this.plantillaReportecrearpatrRutaArchivos;
    }
    
    public void setPlantillaReportecrearpatrRutaArchivos(String plantillaReportecrearpatrRutaArchivos) {
        this.plantillaReportecrearpatrRutaArchivos = plantillaReportecrearpatrRutaArchivos;
    }

    private void checkPlantillaReportecrearpatrRutaArchivos() throws NotNullArgumentException {
        if(this.getPlantillaReportecrearpatrRutaArchivos() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_CREAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_CREAR_PLANTILLAREPORTECREARPATRRUTAARCHIVOS, PlantillaReporteConstants.SERV_ALIAS_CREAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_CREAR_PLANTILLAREPORTECREARPATRRUTAARCHIVOS);
        }
    }

    @JsonIgnore
    public String getPlantillaReportecrearpatrNombreArchivoPDF() {
        return this.plantillaReportecrearpatrNombreArchivoPDF;
    }
    
    public void setPlantillaReportecrearpatrNombreArchivoPDF(String plantillaReportecrearpatrNombreArchivoPDF) {
        this.plantillaReportecrearpatrNombreArchivoPDF = plantillaReportecrearpatrNombreArchivoPDF;
    }

    private void checkPlantillaReportecrearpatrNombreArchivoPDF() throws NotNullArgumentException {
        if(this.getPlantillaReportecrearpatrNombreArchivoPDF() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_CREAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_CREAR_PLANTILLAREPORTECREARPATRNOMBREARCHIVOPDF, PlantillaReporteConstants.SERV_ALIAS_CREAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_CREAR_PLANTILLAREPORTECREARPATRNOMBREARCHIVOPDF);
        }
    }
}
