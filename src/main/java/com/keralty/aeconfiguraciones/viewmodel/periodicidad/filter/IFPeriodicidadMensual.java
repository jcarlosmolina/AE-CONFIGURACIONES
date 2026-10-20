package com.keralty.aeconfiguraciones.viewmodel.periodicidad.filter;


import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFPeriodicidadMensual {

    public IFPeriodicidadMensual() {
        // Default constructor
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(PeriodicidadConstants.FILTER_NAME_IFPERIODICIDADMENSUAL);
        return filterRequest;
    }
}
