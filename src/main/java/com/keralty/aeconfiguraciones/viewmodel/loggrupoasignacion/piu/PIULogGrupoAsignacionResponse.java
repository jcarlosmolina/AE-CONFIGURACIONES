package com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.ds.DSLogGrupoAsignacion;

public class PIULogGrupoAsignacionResponse extends QueryResponse {

    private List<DSLogGrupoAsignacion> results;

    public PIULogGrupoAsignacionResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogGrupoAsignacionResponse(List<DSLogGrupoAsignacion> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogGrupoAsignacion> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogGrupoAsignacion> results) {
        this.results = results;
    }
}
