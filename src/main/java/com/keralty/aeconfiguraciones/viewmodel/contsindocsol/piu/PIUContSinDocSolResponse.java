package com.keralty.aeconfiguraciones.viewmodel.contsindocsol.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.contsindocsol.ds.DSContSinDocSol;

public class PIUContSinDocSolResponse extends QueryResponse {

    private List<DSContSinDocSol> results;

    public PIUContSinDocSolResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUContSinDocSolResponse(List<DSContSinDocSol> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSContSinDocSol> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSContSinDocSol> results) {
        this.results = results;
    }
}
