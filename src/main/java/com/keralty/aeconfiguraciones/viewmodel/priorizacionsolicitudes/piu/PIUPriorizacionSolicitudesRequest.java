package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.filter.FPriorizacionSolicitudes;

public class PIUPriorizacionSolicitudesRequest extends PopulationRequestViewModel{

    private FPriorizacionSolicitudes fPriorizacionSolicitudes;
    
    public void setFPriorizacionSolicitudes(FPriorizacionSolicitudes fPriorizacionSolicitudes) {
        this.fPriorizacionSolicitudes = fPriorizacionSolicitudes;
    }
    
    public FPriorizacionSolicitudes getFPriorizacionSolicitudes() {
        return fPriorizacionSolicitudes;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fPriorizacionSolicitudes != null)
            queryRequest.addFilterRequest(fPriorizacionSolicitudes.buildFilterRequest());
        return queryRequest;
    }
}
