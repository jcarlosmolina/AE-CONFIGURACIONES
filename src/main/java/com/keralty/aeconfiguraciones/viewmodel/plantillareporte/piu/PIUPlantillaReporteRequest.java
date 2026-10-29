package com.keralty.aeconfiguraciones.viewmodel.plantillareporte.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillareporte.filter.FPlantillaReporte;

public class PIUPlantillaReporteRequest extends PopulationRequestViewModel{

    private FPlantillaReporte fPlantillaReporte;
    
    public void setFPlantillaReporte(FPlantillaReporte fPlantillaReporte) {
        this.fPlantillaReporte = fPlantillaReporte;
    }
    
    public FPlantillaReporte getFPlantillaReporte() {
        return fPlantillaReporte;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fPlantillaReporte != null)
            queryRequest.addFilterRequest(fPlantillaReporte.buildFilterRequest());
        return queryRequest;
    }
}
