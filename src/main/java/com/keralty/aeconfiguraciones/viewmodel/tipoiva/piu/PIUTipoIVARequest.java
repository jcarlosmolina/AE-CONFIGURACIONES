package com.keralty.aeconfiguraciones.viewmodel.tipoiva.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.tipoiva.filter.FTipoIVAVarios;

public class PIUTipoIVARequest extends PopulationRequestViewModel{

    private FTipoIVAVarios fTipoIVAVarios;
    
    public void setFTipoIVAVarios(FTipoIVAVarios fTipoIVAVarios) {
        this.fTipoIVAVarios = fTipoIVAVarios;
    }
    
    public FTipoIVAVarios getFTipoIVAVarios() {
        return fTipoIVAVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fTipoIVAVarios != null)
            queryRequest.addFilterRequest(fTipoIVAVarios.buildFilterRequest());
        return queryRequest;
    }
}
