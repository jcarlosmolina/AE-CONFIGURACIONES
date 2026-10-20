package com.keralty.aeconfiguraciones.viewmodel.asesor.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.asesor.ds.DSAsesorMDirectores;

public class PIUDirectoresResponse extends QueryResponse {

    private List<DSAsesorMDirectores> results;

    public PIUDirectoresResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUDirectoresResponse(List<DSAsesorMDirectores> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSAsesorMDirectores> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSAsesorMDirectores> results) {
        this.results = results;
    }
}
