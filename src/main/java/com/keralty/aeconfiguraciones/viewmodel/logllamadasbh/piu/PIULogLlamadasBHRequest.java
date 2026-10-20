package com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.filter.FLogLlamadasBH;

public class PIULogLlamadasBHRequest extends PopulationRequestViewModel{

    private FLogLlamadasBH fLogLlamadasBH;
    
    public void setFLogLlamadasBH(FLogLlamadasBH fLogLlamadasBH) {
        this.fLogLlamadasBH = fLogLlamadasBH;
    }
    
    public FLogLlamadasBH getFLogLlamadasBH() {
        return fLogLlamadasBH;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fLogLlamadasBH != null)
            queryRequest.addFilterRequest(fLogLlamadasBH.buildFilterRequest());
        return queryRequest;
    }
}
