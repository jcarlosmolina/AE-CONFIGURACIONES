package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;

public class TADDREPORTERequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTADDREPORTEpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTADDREPORTEpthisDominioInstance;

    @JsonProperty("pt_p_agrplantillareporte_oid")
    private PlantillaReporteOid dominioTADDREPORTEptpagrPlantillaReporte;
    
    @JsonIgnore
    private PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporteInstance;

    @JsonProperty("pt_p_atrenviarauto")
    private Boolean dominioTADDREPORTEptpatrEnviarAuto;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTADDREPORTEpthisDominio();
        checkDominioTADDREPORTEptpagrPlantillaReporte();
        checkDominioTADDREPORTEptpatrEnviarAuto();
    }

    @JsonIgnore
    public DominioOid getDominioTADDREPORTEpthisDominio() {
        return this.dominioTADDREPORTEpthisDominio;
    }
    
    public void setDominioTADDREPORTEpthisDominio(DominioOid dominioTADDREPORTEpthisDominio) {
        this.dominioTADDREPORTEpthisDominio = dominioTADDREPORTEpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTADDREPORTEpthisDominioInstance() {
        return this.dominioTADDREPORTEpthisDominioInstance;
    }
    
    public void setDominioTADDREPORTEpthisDominioInstance(Dominio dominioTADDREPORTEpthisDominio) {
        this.dominioTADDREPORTEpthisDominioInstance = dominioTADDREPORTEpthisDominio;
        this.dominioTADDREPORTEpthisDominio = dominioTADDREPORTEpthisDominio != null ? dominioTADDREPORTEpthisDominio.getOid() : null;
    }

    private void checkDominioTADDREPORTEpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTADDREPORTEpthisDominioInstance() == null || this.getDominioTADDREPORTEpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDREPORTE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO, DominioConstants.SERV_ALIAS_TADDREPORTE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public PlantillaReporteOid getDominioTADDREPORTEptpagrPlantillaReporte() {
        return this.dominioTADDREPORTEptpagrPlantillaReporte;
    }
    
    public void setDominioTADDREPORTEptpagrPlantillaReporte(PlantillaReporteOid dominioTADDREPORTEptpagrPlantillaReporte) {
        this.dominioTADDREPORTEptpagrPlantillaReporte = dominioTADDREPORTEptpagrPlantillaReporte;
    }

    @JsonIgnore
    public PlantillaReporte getDominioTADDREPORTEptpagrPlantillaReporteInstance() {
        return this.dominioTADDREPORTEptpagrPlantillaReporteInstance;
    }
    
    public void setDominioTADDREPORTEptpagrPlantillaReporteInstance(PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte) {
        this.dominioTADDREPORTEptpagrPlantillaReporteInstance = dominioTADDREPORTEptpagrPlantillaReporte;
        this.dominioTADDREPORTEptpagrPlantillaReporte = dominioTADDREPORTEptpagrPlantillaReporte != null ? dominioTADDREPORTEptpagrPlantillaReporte.getOid() : null;
    }

    private void checkDominioTADDREPORTEptpagrPlantillaReporte() throws NotNullArgumentException {
        if(this.getDominioTADDREPORTEptpagrPlantillaReporteInstance() == null || this.getDominioTADDREPORTEptpagrPlantillaReporteInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDREPORTE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDREPORTE_DOMINIOTADDREPORTEPTPAGRPLANTILLAREPORTE, DominioConstants.SERV_ALIAS_TADDREPORTE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDREPORTE_DOMINIOTADDREPORTEPTPAGRPLANTILLAREPORTE);
        }
    }

    @JsonIgnore
    public Boolean getDominioTADDREPORTEptpatrEnviarAuto() {
        return this.dominioTADDREPORTEptpatrEnviarAuto;
    }
    
    public void setDominioTADDREPORTEptpatrEnviarAuto(Boolean dominioTADDREPORTEptpatrEnviarAuto) {
        this.dominioTADDREPORTEptpatrEnviarAuto = dominioTADDREPORTEptpatrEnviarAuto;
    }

    private void checkDominioTADDREPORTEptpatrEnviarAuto() throws NotNullArgumentException {
        if(this.getDominioTADDREPORTEptpatrEnviarAuto() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TADDREPORTE, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TADDREPORTE_DOMINIOTADDREPORTEPTPATRENVIARAUTO, DominioConstants.SERV_ALIAS_TADDREPORTE, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TADDREPORTE_DOMINIOTADDREPORTEPTPATRENVIARAUTO);
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
