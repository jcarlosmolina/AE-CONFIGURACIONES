package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.filter.FLogContSinDocSol;

public class PIULogContSinDocSolRequest extends PopulationRequestViewModel{

    private FLogContSinDocSol fLogContSinDocSol;
    
    public void setFLogContSinDocSol(FLogContSinDocSol fLogContSinDocSol) {
        this.fLogContSinDocSol = fLogContSinDocSol;
    }
    
    public FLogContSinDocSol getFLogContSinDocSol() {
        return fLogContSinDocSol;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fLogContSinDocSol != null)
            queryRequest.addFilterRequest(fLogContSinDocSol.buildFilterRequest());
        return queryRequest;
    }
}
