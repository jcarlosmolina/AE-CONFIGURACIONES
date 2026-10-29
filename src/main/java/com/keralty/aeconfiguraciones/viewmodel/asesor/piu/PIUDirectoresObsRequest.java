package com.keralty.aeconfiguraciones.viewmodel.asesor.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.asesor.filter.FDirectores;

public class PIUDirectoresObsRequest extends PopulationRequestViewModel{

    private FDirectores fDirectores;
    
    public void setFDirectores(FDirectores fDirectores) {
        this.fDirectores = fDirectores;
    }
    
    public FDirectores getFDirectores() {
        return fDirectores;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fDirectores != null)
            queryRequest.addFilterRequest(fDirectores.buildFilterRequest());
        return queryRequest;
    }
}
