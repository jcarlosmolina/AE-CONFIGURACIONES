package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class ModificarRequest {

    @JsonProperty("p_thisplantillareporte_oid")
    private PlantillaReporteOid plantillaReportemodificarpthisPlantillaReporte;
    
    @JsonIgnore
    private PlantillaReporte plantillaReportemodificarpthisPlantillaReporteInstance;

    @JsonProperty("p_titulo")
    private String plantillaReportemodificarpTitulo;

    @JsonProperty("p_fechavigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date plantillaReportemodificarpFechaVigencia;

    @JsonProperty("p_rutaarchivos")
    private String plantillaReportemodificarpRutaArchivos;

    @JsonProperty("p_nombrearchivopdf")
    private String plantillaReportemodificarpNombreArchivoPDF;

    public void checkArguments() throws NotNullArgumentException {
        checkPlantillaReportemodificarpthisPlantillaReporte();
        checkPlantillaReportemodificarpTitulo();
        checkPlantillaReportemodificarpFechaVigencia();
        checkPlantillaReportemodificarpRutaArchivos();
        checkPlantillaReportemodificarpNombreArchivoPDF();
    }

    @JsonIgnore
    public PlantillaReporteOid getPlantillaReportemodificarpthisPlantillaReporte() {
        return this.plantillaReportemodificarpthisPlantillaReporte;
    }
    
    public void setPlantillaReportemodificarpthisPlantillaReporte(PlantillaReporteOid plantillaReportemodificarpthisPlantillaReporte) {
        this.plantillaReportemodificarpthisPlantillaReporte = plantillaReportemodificarpthisPlantillaReporte;
    }

    @JsonIgnore
    public PlantillaReporte getPlantillaReportemodificarpthisPlantillaReporteInstance() {
        return this.plantillaReportemodificarpthisPlantillaReporteInstance;
    }
    
    public void setPlantillaReportemodificarpthisPlantillaReporteInstance(PlantillaReporte plantillaReportemodificarpthisPlantillaReporte) {
        this.plantillaReportemodificarpthisPlantillaReporteInstance = plantillaReportemodificarpthisPlantillaReporte;
        this.plantillaReportemodificarpthisPlantillaReporte = plantillaReportemodificarpthisPlantillaReporte != null ? plantillaReportemodificarpthisPlantillaReporte.getOid() : null;
    }

    private void checkPlantillaReportemodificarpthisPlantillaReporte() throws NotNullArgumentException {
        if(this.getPlantillaReportemodificarpthisPlantillaReporteInstance() == null || this.getPlantillaReportemodificarpthisPlantillaReporteInstance().isNull()) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_MODIFICAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_MODIFICAR_PLANTILLAREPORTEMODIFICARPTHISPLANTILLAREPORTE, PlantillaReporteConstants.SERV_ALIAS_MODIFICAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_MODIFICAR_PLANTILLAREPORTEMODIFICARPTHISPLANTILLAREPORTE);
        }
    }

    @JsonIgnore
    public String getPlantillaReportemodificarpTitulo() {
        return this.plantillaReportemodificarpTitulo;
    }
    
    public void setPlantillaReportemodificarpTitulo(String plantillaReportemodificarpTitulo) {
        this.plantillaReportemodificarpTitulo = plantillaReportemodificarpTitulo;
    }

    private void checkPlantillaReportemodificarpTitulo() throws NotNullArgumentException {
        if(this.getPlantillaReportemodificarpTitulo() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_MODIFICAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_MODIFICAR_PLANTILLAREPORTEMODIFICARPTITULO, PlantillaReporteConstants.SERV_ALIAS_MODIFICAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_MODIFICAR_PLANTILLAREPORTEMODIFICARPTITULO);
        }
    }

    @JsonIgnore
    public Date getPlantillaReportemodificarpFechaVigencia() {
        return this.plantillaReportemodificarpFechaVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setPlantillaReportemodificarpFechaVigencia(Date plantillaReportemodificarpFechaVigencia) {
        this.plantillaReportemodificarpFechaVigencia = plantillaReportemodificarpFechaVigencia;
    }

    private void checkPlantillaReportemodificarpFechaVigencia() throws NotNullArgumentException {
        if(this.getPlantillaReportemodificarpFechaVigencia() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_MODIFICAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_MODIFICAR_PLANTILLAREPORTEMODIFICARPFECHAVIGENCIA, PlantillaReporteConstants.SERV_ALIAS_MODIFICAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_MODIFICAR_PLANTILLAREPORTEMODIFICARPFECHAVIGENCIA);
        }
    }

    @JsonIgnore
    public String getPlantillaReportemodificarpRutaArchivos() {
        return this.plantillaReportemodificarpRutaArchivos;
    }
    
    public void setPlantillaReportemodificarpRutaArchivos(String plantillaReportemodificarpRutaArchivos) {
        this.plantillaReportemodificarpRutaArchivos = plantillaReportemodificarpRutaArchivos;
    }

    private void checkPlantillaReportemodificarpRutaArchivos() throws NotNullArgumentException {
        if(this.getPlantillaReportemodificarpRutaArchivos() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_MODIFICAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_MODIFICAR_PLANTILLAREPORTEMODIFICARPRUTAARCHIVOS, PlantillaReporteConstants.SERV_ALIAS_MODIFICAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_MODIFICAR_PLANTILLAREPORTEMODIFICARPRUTAARCHIVOS);
        }
    }

    @JsonIgnore
    public String getPlantillaReportemodificarpNombreArchivoPDF() {
        return this.plantillaReportemodificarpNombreArchivoPDF;
    }
    
    public void setPlantillaReportemodificarpNombreArchivoPDF(String plantillaReportemodificarpNombreArchivoPDF) {
        this.plantillaReportemodificarpNombreArchivoPDF = plantillaReportemodificarpNombreArchivoPDF;
    }

    private void checkPlantillaReportemodificarpNombreArchivoPDF() throws NotNullArgumentException {
        if(this.getPlantillaReportemodificarpNombreArchivoPDF() == null) {
            throw new NotNullArgumentException(PlantillaReporteConstants.SERV_ID_MODIFICAR, PlantillaReporteConstants.CLASS_ID, PlantillaReporteConstants.ARG_ID_MODIFICAR_PLANTILLAREPORTEMODIFICARPNOMBREARCHIVOPDF, PlantillaReporteConstants.SERV_ALIAS_MODIFICAR, PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ARG_ALIAS_MODIFICAR_PLANTILLAREPORTEMODIFICARPNOMBREARCHIVOPDF);
        }
    }
}
