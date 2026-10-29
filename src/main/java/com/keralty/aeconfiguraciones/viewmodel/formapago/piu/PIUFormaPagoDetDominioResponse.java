package com.keralty.aeconfiguraciones.viewmodel.formapago.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.formapago.ds.DSFormaPagoDetDominio;

public class PIUFormaPagoDetDominioResponse extends QueryResponse {

    private List<DSFormaPagoDetDominio> results;

    public PIUFormaPagoDetDominioResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUFormaPagoDetDominioResponse(List<DSFormaPagoDetDominio> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSFormaPagoDetDominio> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSFormaPagoDetDominio> results) {
        this.results = results;
    }
}
