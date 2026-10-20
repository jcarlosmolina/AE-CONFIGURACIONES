package com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.ds.DSMotDevAreaMedica;

public class PIUMotDevAreaMedicaResponse extends QueryResponse {

    private List<DSMotDevAreaMedica> results;

    public PIUMotDevAreaMedicaResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUMotDevAreaMedicaResponse(List<DSMotDevAreaMedica> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSMotDevAreaMedica> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSMotDevAreaMedica> results) {
        this.results = results;
    }
}
