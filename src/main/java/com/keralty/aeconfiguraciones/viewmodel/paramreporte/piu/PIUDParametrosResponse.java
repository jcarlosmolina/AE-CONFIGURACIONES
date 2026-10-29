package com.keralty.aeconfiguraciones.viewmodel.paramreporte.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.ds.DSParamReporte;

public class PIUDParametrosResponse extends QueryResponse {

    private List<DSParamReporte> results;

    public PIUDParametrosResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUDParametrosResponse(List<DSParamReporte> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSParamReporte> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSParamReporte> results) {
        this.results = results;
    }
}
