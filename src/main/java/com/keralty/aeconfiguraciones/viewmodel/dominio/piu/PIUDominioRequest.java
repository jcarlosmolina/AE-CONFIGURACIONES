package com.keralty.aeconfiguraciones.viewmodel.dominio.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.filter.FDominioVarios;

public class PIUDominioRequest extends PopulationRequestViewModel{

    private FDominioVarios fDominioVarios;
    
    public void setFDominioVarios(FDominioVarios fDominioVarios) {
        this.fDominioVarios = fDominioVarios;
    }
    
    public FDominioVarios getFDominioVarios() {
        return fDominioVarios;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fDominioVarios != null)
            queryRequest.addFilterRequest(fDominioVarios.buildFilterRequest());
        return queryRequest;
    }
}
