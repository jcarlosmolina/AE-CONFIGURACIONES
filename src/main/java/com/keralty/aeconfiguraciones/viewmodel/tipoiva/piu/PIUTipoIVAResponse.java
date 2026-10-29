package com.keralty.aeconfiguraciones.viewmodel.tipoiva.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipoiva.ds.DSTipoIVAPIU;

public class PIUTipoIVAResponse extends QueryResponse {

    private List<DSTipoIVAPIU> results;

    public PIUTipoIVAResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTipoIVAResponse(List<DSTipoIVAPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSTipoIVAPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSTipoIVAPIU> results) {
        this.results = results;
    }
}
