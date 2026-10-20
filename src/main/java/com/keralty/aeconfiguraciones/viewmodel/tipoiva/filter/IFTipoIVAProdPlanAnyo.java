package com.keralty.aeconfiguraciones.viewmodel.tipoiva.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFTipoIVAProdPlanAnyo {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    @JsonProperty("v_anyo")
    private Long vAnyo;

    @JsonProperty("v_fecha")
    private Date vFecha;

    public IFTipoIVAProdPlanAnyo() {
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
    public Long getVAnyo() {
        return this.vAnyo;
    }
    
    public void setVAnyo(Long vAnyo) {
        this.vAnyo = vAnyo;
    }

    @JsonIgnore
    public Date getVFecha() {
        return this.vFecha;
    }
    
    public void setVFecha(Date vFecha) {
        this.vFecha = vFecha;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TipoIVAConstants.FILTER_NAME_IFTIPOIVAPRODPLANANYO);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPLAN, voPlan);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VANYO, vAnyo);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VFECHA, vFecha);
        return filterRequest;
    }
}
