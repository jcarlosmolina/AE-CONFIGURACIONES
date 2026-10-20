package com.keralty.aeconfiguraciones.viewmodel.tarifa.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.tarifa.filter.FTarifaVarios;

public class PIUTarifasRequest extends PopulationRequestViewModel{

    private FTarifaVarios fTarifaVarios;
    
    public void setFTarifaVarios(FTarifaVarios fTarifaVarios) {
        this.fTarifaVarios = fTarifaVarios;
    }
    
    public FTarifaVarios getFTarifaVarios() {
        return fTarifaVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fTarifaVarios != null)
            queryRequest.addFilterRequest(fTarifaVarios.buildFilterRequest());
        return queryRequest;
    }
}
