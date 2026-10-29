package com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.ds.DSLogConfiguracionANS;

public class PIULogConfiguracionANSResponse extends QueryResponse {

    private List<DSLogConfiguracionANS> results;

    public PIULogConfiguracionANSResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogConfiguracionANSResponse(List<DSLogConfiguracionANS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogConfiguracionANS> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogConfiguracionANS> results) {
        this.results = results;
    }
}
