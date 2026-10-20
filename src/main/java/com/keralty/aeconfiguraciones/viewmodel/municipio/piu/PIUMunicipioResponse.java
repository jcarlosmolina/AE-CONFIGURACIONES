package com.keralty.aeconfiguraciones.viewmodel.municipio.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.municipio.ds.DSMunicipioPIU;

public class PIUMunicipioResponse extends QueryResponse {

    private List<DSMunicipioPIU> results;

    public PIUMunicipioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUMunicipioResponse(List<DSMunicipioPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSMunicipioPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSMunicipioPIU> results) {
        this.results = results;
    }
}
