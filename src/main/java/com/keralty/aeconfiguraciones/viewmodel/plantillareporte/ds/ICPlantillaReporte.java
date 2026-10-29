package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
public class ICPlantillaReporte {

    @JsonProperty("titulo")
    private String iCPlantillaReporteTitulo;

    public ICPlantillaReporte() {
    
    }

    public ICPlantillaReporte(PlantillaReporte instance) {
        if (instance != null) {
            this.iCPlantillaReporteTitulo = instance.getPlantillaReporteTitulo();
        }
    }

    @JsonIgnore
    public String getICPlantillaReporteTitulo() {
        return this.iCPlantillaReporteTitulo;
    }
    
    public void setICPlantillaReporteTitulo(String iCPlantillaReporteTitulo) {
        this.iCPlantillaReporteTitulo = iCPlantillaReporteTitulo;
    }
}
