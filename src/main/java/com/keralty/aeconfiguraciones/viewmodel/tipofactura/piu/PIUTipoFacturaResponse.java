package com.keralty.aeconfiguraciones.viewmodel.tipofactura.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.tipofactura.ds.DSTipoFactura;

public class PIUTipoFacturaResponse extends QueryResponse {

    private List<DSTipoFactura> results;

    public PIUTipoFacturaResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTipoFacturaResponse(List<DSTipoFactura> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSTipoFactura> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSTipoFactura> results) {
        this.results = results;
    }
}
