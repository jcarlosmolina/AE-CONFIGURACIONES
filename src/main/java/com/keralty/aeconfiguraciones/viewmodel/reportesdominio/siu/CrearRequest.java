package com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesDominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;

public class CrearRequest {

    @JsonProperty("p_agrplantillareporte_oid")
    private PlantillaReporteOid reportesDominiocrearpagrPlantillaReporte;
    
    @JsonIgnore
    private PlantillaReporte reportesDominiocrearpagrPlantillaReporteInstance;

    @JsonProperty("p_agrdominio_oid")
    private DominioOid reportesDominiocrearpagrDominio;
    
    @JsonIgnore
    private Dominio reportesDominiocrearpagrDominioInstance;

    @JsonProperty("p_atrenviarauto")
    private Boolean reportesDominiocrearpatrEnviarAuto;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesDominiocrearpagrPlantillaReporte();
        checkReportesDominiocrearpagrDominio();
        checkReportesDominiocrearpatrEnviarAuto();
    }

    @JsonIgnore
    public PlantillaReporteOid getReportesDominiocrearpagrPlantillaReporte() {
        return this.reportesDominiocrearpagrPlantillaReporte;
    }
    
    public void setReportesDominiocrearpagrPlantillaReporte(PlantillaReporteOid reportesDominiocrearpagrPlantillaReporte) {
        this.reportesDominiocrearpagrPlantillaReporte = reportesDominiocrearpagrPlantillaReporte;
    }

    @JsonIgnore
    public PlantillaReporte getReportesDominiocrearpagrPlantillaReporteInstance() {
        return this.reportesDominiocrearpagrPlantillaReporteInstance;
    }
    
    public void setReportesDominiocrearpagrPlantillaReporteInstance(PlantillaReporte reportesDominiocrearpagrPlantillaReporte) {
        this.reportesDominiocrearpagrPlantillaReporteInstance = reportesDominiocrearpagrPlantillaReporte;
        this.reportesDominiocrearpagrPlantillaReporte = reportesDominiocrearpagrPlantillaReporte != null ? reportesDominiocrearpagrPlantillaReporte.getOid() : null;
    }

    private void checkReportesDominiocrearpagrPlantillaReporte() throws NotNullArgumentException {
        if(this.getReportesDominiocrearpagrPlantillaReporteInstance() == null || this.getReportesDominiocrearpagrPlantillaReporteInstance().isNull()) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_CREAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_CREAR_REPORTESDOMINIOCREARPAGRPLANTILLAREPORTE, ReportesDominioConstants.SERV_ALIAS_CREAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_CREAR_REPORTESDOMINIOCREARPAGRPLANTILLAREPORTE);
        }
    }

    @JsonIgnore
    public DominioOid getReportesDominiocrearpagrDominio() {
        return this.reportesDominiocrearpagrDominio;
    }
    
    public void setReportesDominiocrearpagrDominio(DominioOid reportesDominiocrearpagrDominio) {
        this.reportesDominiocrearpagrDominio = reportesDominiocrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getReportesDominiocrearpagrDominioInstance() {
        return this.reportesDominiocrearpagrDominioInstance;
    }
    
    public void setReportesDominiocrearpagrDominioInstance(Dominio reportesDominiocrearpagrDominio) {
        this.reportesDominiocrearpagrDominioInstance = reportesDominiocrearpagrDominio;
        this.reportesDominiocrearpagrDominio = reportesDominiocrearpagrDominio != null ? reportesDominiocrearpagrDominio.getOid() : null;
    }

    private void checkReportesDominiocrearpagrDominio() throws NotNullArgumentException {
        if(this.getReportesDominiocrearpagrDominioInstance() == null || this.getReportesDominiocrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_CREAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_CREAR_REPORTESDOMINIOCREARPAGRDOMINIO, ReportesDominioConstants.SERV_ALIAS_CREAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_CREAR_REPORTESDOMINIOCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getReportesDominiocrearpatrEnviarAuto() {
        return this.reportesDominiocrearpatrEnviarAuto;
    }
    
    public void setReportesDominiocrearpatrEnviarAuto(Boolean reportesDominiocrearpatrEnviarAuto) {
        this.reportesDominiocrearpatrEnviarAuto = reportesDominiocrearpatrEnviarAuto;
    }

    private void checkReportesDominiocrearpatrEnviarAuto() throws NotNullArgumentException {
        if(this.getReportesDominiocrearpatrEnviarAuto() == null) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_CREAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_CREAR_REPORTESDOMINIOCREARPATRENVIARAUTO, ReportesDominioConstants.SERV_ALIAS_CREAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_CREAR_REPORTESDOMINIOCREARPATRENVIARAUTO);
        }
    }
}
