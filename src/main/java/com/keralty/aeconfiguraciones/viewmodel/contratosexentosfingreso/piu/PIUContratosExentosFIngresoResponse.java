package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.ds.DSContratosExentosFIngreso;

public class PIUContratosExentosFIngresoResponse extends QueryResponse {

    private List<DSContratosExentosFIngreso> results;

    public PIUContratosExentosFIngresoResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUContratosExentosFIngresoResponse(List<DSContratosExentosFIngreso> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSContratosExentosFIngreso> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSContratosExentosFIngreso> results) {
        this.results = results;
    }
}
