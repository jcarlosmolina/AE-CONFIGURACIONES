package com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.ds.DSLogIncompatibilidades;

public class PIULogIncompatibilidadesResponse extends QueryResponse {

    private List<DSLogIncompatibilidades> results;

    public PIULogIncompatibilidadesResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogIncompatibilidadesResponse(List<DSLogIncompatibilidades> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogIncompatibilidades> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogIncompatibilidades> results) {
        this.results = results;
    }
}
