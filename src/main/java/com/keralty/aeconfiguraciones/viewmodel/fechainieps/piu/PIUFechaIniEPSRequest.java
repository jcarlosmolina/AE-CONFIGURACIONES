package com.keralty.aeconfiguraciones.viewmodel.fechainieps.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.fechainieps.filter.FFechaIniEPS;

public class PIUFechaIniEPSRequest extends PopulationRequestViewModel{

    private FFechaIniEPS fFechaIniEPS;
    
    public void setFFechaIniEPS(FFechaIniEPS fFechaIniEPS) {
        this.fFechaIniEPS = fFechaIniEPS;
    }
    
    public FFechaIniEPS getFFechaIniEPS() {
        return fFechaIniEPS;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fFechaIniEPS != null)
            queryRequest.addFilterRequest(fFechaIniEPS.buildFilterRequest());
        return queryRequest;
    }
}
