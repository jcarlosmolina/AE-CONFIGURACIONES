package com.keralty.aeconfiguraciones.viewmodel.fechainieps.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.fechainieps.ds.DSFechaIniEPS;

public class PIUFechaIniEPSResponse extends QueryResponse {

    private List<DSFechaIniEPS> results;

    public PIUFechaIniEPSResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUFechaIniEPSResponse(List<DSFechaIniEPS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSFechaIniEPS> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSFechaIniEPS> results) {
        this.results = results;
    }
}
