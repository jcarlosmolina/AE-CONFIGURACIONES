package com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.ds.DSLogDatosDominio;

public class PIULogDatosDominioResponse extends QueryResponse {

    private List<DSLogDatosDominio> results;

    public PIULogDatosDominioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogDatosDominioResponse(List<DSLogDatosDominio> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogDatosDominio> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogDatosDominio> results) {
        this.results = results;
    }
}
