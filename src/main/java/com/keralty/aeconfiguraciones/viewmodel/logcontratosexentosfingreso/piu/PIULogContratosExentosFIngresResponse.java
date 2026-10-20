package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.ds.DSLogContratosExentosFIngreso;

public class PIULogContratosExentosFIngresResponse extends QueryResponse {

    private List<DSLogContratosExentosFIngreso> results;

    public PIULogContratosExentosFIngresResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogContratosExentosFIngresResponse(List<DSLogContratosExentosFIngreso> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogContratosExentosFIngreso> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogContratosExentosFIngreso> results) {
        this.results = results;
    }
}
