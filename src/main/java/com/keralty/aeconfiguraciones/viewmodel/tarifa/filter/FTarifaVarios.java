package com.keralty.aeconfiguraciones.viewmodel.tarifa.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FTarifaVarios {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    public FTarifaVarios() {
        // Default constructor
    }

    @JsonIgnore
    public ProductosOid getVoProducto() {
        return this.voProducto;
    }
    
    public void setVoProducto(ProductosOid voProducto) {
        this.voProducto = voProducto;
    }

    @JsonIgnore
    public PlanesOid getVoPlan() {
        return this.voPlan;
    }
    
    public void setVoPlan(PlanesOid voPlan) {
        this.voPlan = voPlan;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TarifaConstants.FILTER_NAME_FTARIFAVARIOS);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPLAN, voPlan);
        return filterRequest;
    }
}
