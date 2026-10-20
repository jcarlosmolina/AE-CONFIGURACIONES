package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.ds.DSLogContSinDocSol;

public class PIULogContSinDocSolResponse extends QueryResponse {

    private List<DSLogContSinDocSol> results;

    public PIULogContSinDocSolResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogContSinDocSolResponse(List<DSLogContSinDocSol> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogContSinDocSol> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogContSinDocSol> results) {
        this.results = results;
    }
}
