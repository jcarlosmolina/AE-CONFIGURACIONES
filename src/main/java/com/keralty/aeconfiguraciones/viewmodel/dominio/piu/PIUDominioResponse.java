package com.keralty.aeconfiguraciones.viewmodel.dominio.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioPIU;

public class PIUDominioResponse extends QueryResponse {

    private List<DSDominioPIU> results;

    public PIUDominioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUDominioResponse(List<DSDominioPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSDominioPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSDominioPIU> results) {
        this.results = results;
    }
}
