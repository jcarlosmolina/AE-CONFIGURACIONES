package com.keralty.aeconfiguraciones.viewmodel.seccion.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.seccion.ds.DSSeccionPIU;

public class PIUSeccionResponse extends QueryResponse {

    private List<DSSeccionPIU> results;

    public PIUSeccionResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUSeccionResponse(List<DSSeccionPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSSeccionPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSSeccionPIU> results) {
        this.results = results;
    }
}
