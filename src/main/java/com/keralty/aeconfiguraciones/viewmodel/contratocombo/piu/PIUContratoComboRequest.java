package com.keralty.aeconfiguraciones.viewmodel.contratocombo.piu;

import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.contratocombo.filter.FContratoComboPorNumero;

public class PIUContratoComboRequest extends PopulationRequestViewModel{

    private FContratoComboPorNumero fContratoComboPorNumero;
    
    public void setFContratoComboPorNumero(FContratoComboPorNumero fContratoComboPorNumero) {
        this.fContratoComboPorNumero = fContratoComboPorNumero;
    }
    
    public FContratoComboPorNumero getFContratoComboPorNumero() {
        return fContratoComboPorNumero;
    }

    @Override
    public QueryRequest buildQueryRequest() {
        QueryRequest queryRequest = super.buildQueryRequest();

        if (fContratoComboPorNumero != null)
            queryRequest.addFilterRequest(fContratoComboPorNumero.buildFilterRequest());
        return queryRequest;
    }
}
