package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedPIU;

public class PIUCuestionarioMedResponse extends QueryResponse {

    private List<DSCuestionarioMedPIU> results;

    public PIUCuestionarioMedResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUCuestionarioMedResponse(List<DSCuestionarioMedPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSCuestionarioMedPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSCuestionarioMedPIU> results) {
        this.results = results;
    }
}
