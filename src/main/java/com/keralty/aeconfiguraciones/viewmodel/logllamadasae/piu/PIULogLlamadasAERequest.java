package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.logllamadasae.filter.FLogLlamadasAE;

public class PIULogLlamadasAERequest extends PopulationRequestViewModel{

    private FLogLlamadasAE fLogLlamadasAE;
    
    public void setFLogLlamadasAE(FLogLlamadasAE fLogLlamadasAE) {
        this.fLogLlamadasAE = fLogLlamadasAE;
    }
    
    public FLogLlamadasAE getFLogLlamadasAE() {
        return fLogLlamadasAE;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fLogLlamadasAE != null)
            queryRequest.addFilterRequest(fLogLlamadasAE.buildFilterRequest());
        return queryRequest;
    }
}
