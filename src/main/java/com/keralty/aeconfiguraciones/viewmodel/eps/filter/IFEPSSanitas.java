package com.keralty.aeconfiguraciones.viewmodel.eps.filter;


import com.keralty.aeconfiguraciones.global.EpsConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFEPSSanitas {

    public IFEPSSanitas() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(EpsConstants.FILTER_NAME_IFEPSSANITAS);
        return filterRequest;
    }
}
