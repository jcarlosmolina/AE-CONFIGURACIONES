package com.keralty.aeconfiguraciones.viewmodel.valor.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.valor.ds.AutoDS;

public class PIUValorResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIUValorResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUValorResponse(List<AutoDS> results) {
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
