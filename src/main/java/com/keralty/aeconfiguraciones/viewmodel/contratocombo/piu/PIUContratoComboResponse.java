package com.keralty.aeconfiguraciones.viewmodel.contratocombo.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.contratocombo.ds.DSContratoCombo;

public class PIUContratoComboResponse extends QueryResponse {

    private List<DSContratoCombo> results;

    public PIUContratoComboResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUContratoComboResponse(List<DSContratoCombo> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSContratoCombo> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSContratoCombo> results) {
        this.results = results;
    }
}
