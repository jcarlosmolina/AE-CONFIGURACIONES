package com.keralty.aeconfiguraciones.viewmodel.novedad.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.novedad.filter.FNovedad;

public class PIUNovedadRequest extends PopulationRequestViewModel{

    private FNovedad fNovedad;
    
    public void setFNovedad(FNovedad fNovedad) {
        this.fNovedad = fNovedad;
    }
    
    public FNovedad getFNovedad() {
        return fNovedad;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fNovedad != null)
            queryRequest.addFilterRequest(fNovedad.buildFilterRequest());
        return queryRequest;
    }
}
