package com.keralty.aeconfiguraciones.viewmodel.causal.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.causal.ds.DSCausal;

public class PIUCausalDMDNovedadResponse extends QueryResponse {

    private List<DSCausal> results;

    public PIUCausalDMDNovedadResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUCausalDMDNovedadResponse(List<DSCausal> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSCausal> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSCausal> results) {
        this.results = results;
    }
}
