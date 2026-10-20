package com.keralty.aeconfiguraciones.viewmodel.pais.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.pais.ds.AutoDS;

public class PIUPaisResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIUPaisResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUPaisResponse(List<AutoDS> results) {
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
