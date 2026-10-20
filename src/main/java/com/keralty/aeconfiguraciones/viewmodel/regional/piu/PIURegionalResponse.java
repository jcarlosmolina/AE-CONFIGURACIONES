package com.keralty.aeconfiguraciones.viewmodel.regional.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.regional.ds.AutoDS;

public class PIURegionalResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIURegionalResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIURegionalResponse(List<AutoDS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<AutoDS> getResults() {
        return this.results;
    }
    
    public void setResults(List<AutoDS> results) {
        this.results = results;
    }
}
