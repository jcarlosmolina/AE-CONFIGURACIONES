package com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.ds.DSMotivoExcepcionSolNov;

public class PIUMotivoExcepcionSolNovResponse extends QueryResponse {

    private List<DSMotivoExcepcionSolNov> results;

    public PIUMotivoExcepcionSolNovResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUMotivoExcepcionSolNovResponse(List<DSMotivoExcepcionSolNov> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSMotivoExcepcionSolNov> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSMotivoExcepcionSolNov> results) {
        this.results = results;
    }
}
