package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds.DSTipoNovedadNull;

public class PIUTipoNovedadNullResponse extends QueryResponse {

    private List<DSTipoNovedadNull> results;

    public PIUTipoNovedadNullResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUTipoNovedadNullResponse(List<DSTipoNovedadNull> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSTipoNovedadNull> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSTipoNovedadNull> results) {
        this.results = results;
    }
}
