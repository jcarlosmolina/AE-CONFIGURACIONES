package com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.ds.AutoDS;

public class PIUEntidadFinancieraResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIUEntidadFinancieraResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUEntidadFinancieraResponse(List<AutoDS> results) {
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
