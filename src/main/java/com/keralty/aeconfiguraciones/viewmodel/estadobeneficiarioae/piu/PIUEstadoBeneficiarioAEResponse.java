package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.ds.DSEstadoBeneficiarioAEPIU;

public class PIUEstadoBeneficiarioAEResponse extends QueryResponse {

    private List<DSEstadoBeneficiarioAEPIU> results;

    public PIUEstadoBeneficiarioAEResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUEstadoBeneficiarioAEResponse(List<DSEstadoBeneficiarioAEPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSEstadoBeneficiarioAEPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSEstadoBeneficiarioAEPIU> results) {
        this.results = results;
    }
}
