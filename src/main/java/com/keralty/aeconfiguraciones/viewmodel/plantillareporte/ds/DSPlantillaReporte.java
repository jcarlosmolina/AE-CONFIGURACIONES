package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.ds;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
public class DSPlantillaReporte {

    @JsonProperty("tipo")
    private Long dSPlantillaReporteTipo;

    @JsonProperty("titulo")
    private String dSPlantillaReporteTitulo;

    @JsonProperty("fechavigencia")
    private Date dSPlantillaReporteFechaVigencia;

    @JsonProperty("nombrearchivopdf")
    private String dSPlantillaReporteNombreArchivoPDF;

    @JsonProperty("rutaarchivos")
    private String dSPlantillaReporteRutaArchivos;

    public DSPlantillaReporte() {
    
    }

    public DSPlantillaReporte(PlantillaReporte instance) {
        if (instance != null) {
            this.dSPlantillaReporteTipo = instance.getPlantillaReporteTipo();
            this.dSPlantillaReporteTitulo = instance.getPlantillaReporteTitulo();
            this.dSPlantillaReporteFechaVigencia = instance.getPlantillaReporteFechaVigencia();
            this.dSPlantillaReporteNombreArchivoPDF = instance.getPlantillaReporteNombreArchivoPDF();
            this.dSPlantillaReporteRutaArchivos = instance.getPlantillaReporteRutaArchivos();
        }
    }

    @JsonIgnore
    public Long getDSPlantillaReporteTipo() {
        return this.dSPlantillaReporteTipo;
    }
    
    public void setDSPlantillaReporteTipo(Long dSPlantillaReporteTipo) {
        this.dSPlantillaReporteTipo = dSPlantillaReporteTipo;
    }

    @JsonIgnore
    public String getDSPlantillaReporteTitulo() {
        return this.dSPlantillaReporteTitulo;
    }
    
    public void setDSPlantillaReporteTitulo(String dSPlantillaReporteTitulo) {
        this.dSPlantillaReporteTitulo = dSPlantillaReporteTitulo;
    }

    @JsonIgnore
    public Date getDSPlantillaReporteFechaVigencia() {
        return this.dSPlantillaReporteFechaVigencia;
    }
    
    public void setDSPlantillaReporteFechaVigencia(Date dSPlantillaReporteFechaVigencia) {
        this.dSPlantillaReporteFechaVigencia = dSPlantillaReporteFechaVigencia;
    }

    @JsonIgnore
    public String getDSPlantillaReporteNombreArchivoPDF() {
        return this.dSPlantillaReporteNombreArchivoPDF;
    }
    
    public void setDSPlantillaReporteNombreArchivoPDF(String dSPlantillaReporteNombreArchivoPDF) {
        this.dSPlantillaReporteNombreArchivoPDF = dSPlantillaReporteNombreArchivoPDF;
    }

    @JsonIgnore
    public String getDSPlantillaReporteRutaArchivos() {
        return this.dSPlantillaReporteRutaArchivos;
    }
    
    public void setDSPlantillaReporteRutaArchivos(String dSPlantillaReporteRutaArchivos) {
        this.dSPlantillaReporteRutaArchivos = dSPlantillaReporteRutaArchivos;
    }
}
