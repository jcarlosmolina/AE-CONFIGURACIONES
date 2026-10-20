package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.plantillareporte.ds.DSPlantillaReporte;

public class PIUPlantillaReporteResponse extends QueryResponse {

    private List<DSPlantillaReporte> results;

    public PIUPlantillaReporteResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUPlantillaReporteResponse(List<DSPlantillaReporte> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSPlantillaReporte> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSPlantillaReporte> results) {
        this.results = results;
    }
}
