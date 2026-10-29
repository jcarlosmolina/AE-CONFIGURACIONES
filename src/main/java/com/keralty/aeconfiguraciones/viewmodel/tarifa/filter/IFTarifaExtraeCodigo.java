package com.keralty.aeconfiguraciones.viewmodel.tarifa.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFTarifaExtraeCodigo {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    @JsonProperty("v_tipocontrato")
    private Long vTipoContrato;

    @JsonProperty("vfanyo")
    private Long vfAnyo;

    public IFTarifaExtraeCodigo() {
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

    @JsonIgnore
    public Long getVTipoContrato() {
        return this.vTipoContrato;
    }
    
    public void setVTipoContrato(Long vTipoContrato) {
        this.vTipoContrato = vTipoContrato;
    }

    @JsonIgnore
    public Long getVfAnyo() {
        return this.vfAnyo;
    }
    
    public void setVfAnyo(Long vfAnyo) {
        this.vfAnyo = vfAnyo;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TarifaConstants.FILTER_NAME_IFTARIFAEXTRAECODIGO);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPLAN, voPlan);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VTIPOCONTRATO, vTipoContrato);
        filterRequest.addFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VFANYO, vfAnyo);
        return filterRequest;
    }
}
