package com.keralty.aeconfiguraciones.viewmodel.tipodireccion.filter;


import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFTipoDireccionCorrespondenci {

    public IFTipoDireccionCorrespondenci() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TipoDireccionConstants.FILTER_NAME_IFTIPODIRECCIONCORRESPONDENCI);
        return filterRequest;
    }
}
