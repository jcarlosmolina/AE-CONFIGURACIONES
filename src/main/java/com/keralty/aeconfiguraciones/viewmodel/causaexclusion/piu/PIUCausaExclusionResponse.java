package com.keralty.aeconfiguraciones.viewmodel.causaexclusion.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.causaexclusion.ds.DSCausaExclusion;

public class PIUCausaExclusionResponse extends QueryResponse {

    private List<DSCausaExclusion> results;

    public PIUCausaExclusionResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUCausaExclusionResponse(List<DSCausaExclusion> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSCausaExclusion> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSCausaExclusion> results) {
        this.results = results;
    }
}
