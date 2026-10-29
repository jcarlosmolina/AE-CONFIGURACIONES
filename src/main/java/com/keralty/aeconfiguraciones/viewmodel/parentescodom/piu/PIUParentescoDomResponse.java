package com.keralty.aeconfiguraciones.viewmodel.parentescodom.piu;

import java.util.ArrayList;
import java.util.List;

import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.parentescodom.ds.DSParentescoDomPIU;

public class PIUParentescoDomResponse extends QueryResponse {

    private List<DSParentescoDomPIU> results;

    public PIUParentescoDomResponse() {
        super();
        results = new ArrayList<>();
    }
    
    public PIUParentescoDomResponse(List<DSParentescoDomPIU> results) {
        super();
        this.results = results;
        this.setTotalItems(results.size());
    }
    
    public List<DSParentescoDomPIU> getResults() {
        return this.results;
    }
    
    public void setResults(List<DSParentescoDomPIU> results) {
        this.results = results;
    }
}
