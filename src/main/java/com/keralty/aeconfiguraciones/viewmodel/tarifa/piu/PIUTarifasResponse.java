package com.keralty.aeconfiguraciones.viewmodel.tarifa.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.tarifa.ds.DSTarifas;

public class PIUTarifasResponse extends QueryResponse {

    private List<DSTarifas> results;

    public PIUTarifasResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTarifasResponse(List<DSTarifas> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSTarifas> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSTarifas> results) {
        this.results = results;
    }
}
