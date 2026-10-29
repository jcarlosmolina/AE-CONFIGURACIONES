package com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.filter.FContratosExentosFIngreso;

public class PIUContratosExentosFIngresoRequest extends PopulationRequestViewModel{

    private FContratosExentosFIngreso fContratosExentosFIngreso;
    
    public void setFContratosExentosFIngreso(FContratosExentosFIngreso fContratosExentosFIngreso) {
        this.fContratosExentosFIngreso = fContratosExentosFIngreso;
    }
    
    public FContratosExentosFIngreso getFContratosExentosFIngreso() {
        return fContratosExentosFIngreso;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fContratosExentosFIngreso != null)
            queryRequest.addFilterRequest(fContratosExentosFIngreso.buildFilterRequest());
        return queryRequest;
    }
}
