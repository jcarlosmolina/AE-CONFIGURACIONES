package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.ds.DSPriorizacionSolicitudes;

public class PIUPriorizacionSolicitudesResponse extends QueryResponse {

    private List<DSPriorizacionSolicitudes> results;

    public PIUPriorizacionSolicitudesResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUPriorizacionSolicitudesResponse(List<DSPriorizacionSolicitudes> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSPriorizacionSolicitudes> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSPriorizacionSolicitudes> results) {
        this.results = results;
    }
}
