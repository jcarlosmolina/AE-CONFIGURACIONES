package com.keralty.aeconfiguraciones.viewmodel.parentesco.filter;


import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFParentescoConyuge {

    public IFParentescoConyuge() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(ParentescoConstants.FILTER_NAME_IFPARENTESCOCONYUGE);
        return filterRequest;
    }
}
