package com.keralty.aeconfiguraciones.viewmodel.novedad.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.novedad.ds.DSNovedadDMDDominio;

public class PIUNovedadDDominioResponse extends QueryResponse {

    private List<DSNovedadDMDDominio> results;

    public PIUNovedadDDominioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUNovedadDDominioResponse(List<DSNovedadDMDDominio> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSNovedadDMDDominio> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSNovedadDMDDominio> results) {
        this.results = results;
    }
}
