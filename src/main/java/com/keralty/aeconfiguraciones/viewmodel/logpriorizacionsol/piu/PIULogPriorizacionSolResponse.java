package com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.ds.DSLogPriorizacionSol;

public class PIULogPriorizacionSolResponse extends QueryResponse {

    private List<DSLogPriorizacionSol> results;

    public PIULogPriorizacionSolResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogPriorizacionSolResponse(List<DSLogPriorizacionSol> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogPriorizacionSol> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogPriorizacionSol> results) {
        this.results = results;
    }
}
