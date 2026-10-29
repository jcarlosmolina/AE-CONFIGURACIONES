package com.keralty.aeconfiguraciones.viewmodel.configuracionans.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds.DSConfiguracionANS;

public class PIUConfiguracionANSResponse extends QueryResponse {

    private List<DSConfiguracionANS> results;

    public PIUConfiguracionANSResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUConfiguracionANSResponse(List<DSConfiguracionANS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSConfiguracionANS> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSConfiguracionANS> results) {
        this.results = results;
    }
}
