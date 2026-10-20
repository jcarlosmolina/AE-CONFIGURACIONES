package com.keralty.aeconfiguraciones.viewmodel.paramconfig.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds.DSParamConfigPIU;

public class PIUParamConfigResponse extends QueryResponse {

    private List<DSParamConfigPIU> results;

    public PIUParamConfigResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUParamConfigResponse(List<DSParamConfigPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSParamConfigPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSParamConfigPIU> results) {
        this.results = results;
    }
}
