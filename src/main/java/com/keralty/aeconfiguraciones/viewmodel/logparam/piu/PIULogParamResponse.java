package com.keralty.aeconfiguraciones.viewmodel.logparam.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logparam.ds.DSLogParamPIU;

public class PIULogParamResponse extends QueryResponse {

    private List<DSLogParamPIU> results;

    public PIULogParamResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogParamResponse(List<DSLogParamPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogParamPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogParamPIU> results) {
        this.results = results;
    }
}
