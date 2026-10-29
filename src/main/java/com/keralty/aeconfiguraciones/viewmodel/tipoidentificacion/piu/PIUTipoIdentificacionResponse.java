package com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.ds.AutoDS;

public class PIUTipoIdentificacionResponse extends QueryResponse {

    private List<AutoDS> results;

    public PIUTipoIdentificacionResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTipoIdentificacionResponse(List<AutoDS> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<AutoDS> getResults() {
        return this.results;
    }
    
    public void setResults(List<AutoDS> results) {
        this.results = results;
    }
}
