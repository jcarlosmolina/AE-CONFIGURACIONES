package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FPlantillaReporte {

    @JsonProperty("vftipo")
    private Long vfTipo;

    @JsonProperty("vffecha")
    private Date vfFecha;

    public FPlantillaReporte() {
        // Default constructor
    }

    @JsonIgnore
    public Long getVfTipo() {
        return this.vfTipo;
    }
    
    public void setVfTipo(Long vfTipo) {
        this.vfTipo = vfTipo;
    }

    @JsonIgnore
    public Date getVfFecha() {
        return this.vfFecha;
    }
    
    public void setVfFecha(Date vfFecha) {
        this.vfFecha = vfFecha;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(PlantillaReporteConstants.FILTER_NAME_FPLANTILLAREPORTE);
        filterRequest.addFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFTIPO, vfTipo);
        filterRequest.addFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFFECHA, vfFecha);
        return filterRequest;
    }
}
