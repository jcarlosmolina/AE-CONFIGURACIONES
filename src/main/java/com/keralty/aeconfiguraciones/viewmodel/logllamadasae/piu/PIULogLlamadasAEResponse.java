package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAE;

public class PIULogLlamadasAEResponse extends QueryResponse {

    private List<DSLogLlamadasAE> results;

    public PIULogLlamadasAEResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogLlamadasAEResponse(List<DSLogLlamadasAE> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogLlamadasAE> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogLlamadasAE> results) {
        this.results = results;
    }
}
