package com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.ds.DSLogContratoCombo;

public class PIULogContratoComboResponse extends QueryResponse {

    private List<DSLogContratoCombo> results;

    public PIULogContratoComboResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIULogContratoComboResponse(List<DSLogContratoCombo> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSLogContratoCombo> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSLogContratoCombo> results) {
        this.results = results;
    }
}
