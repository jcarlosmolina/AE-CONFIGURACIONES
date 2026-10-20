package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.filter.FLogContratosExentosFIngreso;

public class PIULogContratosExentosFIngresRequest extends PopulationRequestViewModel{

    private FLogContratosExentosFIngreso fLogContratosExentosFIngreso;
    
    public void setFLogContratosExentosFIngreso(FLogContratosExentosFIngreso fLogContratosExentosFIngreso) {
        this.fLogContratosExentosFIngreso = fLogContratosExentosFIngreso;
    }
    
    public FLogContratosExentosFIngreso getFLogContratosExentosFIngreso() {
        return fLogContratosExentosFIngreso;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fLogContratosExentosFIngreso != null)
            queryRequest.addFilterRequest(fLogContratosExentosFIngreso.buildFilterRequest());
        return queryRequest;
    }
}
