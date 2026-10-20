package com.keralty.aeconfiguraciones.viewmodel.tipoiva.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FTipoIVAVarios {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    @JsonProperty("v_desde")
    private Date vDesde;

    @JsonProperty("v_hasta")
    private Date vHasta;

    public FTipoIVAVarios() {
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
    public Date getVDesde() {
        return this.vDesde;
    }
    
    public void setVDesde(Date vDesde) {
        this.vDesde = vDesde;
    }

    @JsonIgnore
    public Date getVHasta() {
        return this.vHasta;
    }
    
    public void setVHasta(Date vHasta) {
        this.vHasta = vHasta;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(TipoIVAConstants.FILTER_NAME_FTIPOIVAVARIOS);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPLAN, voPlan);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VDESDE, vDesde);
        filterRequest.addFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VHASTA, vHasta);
        return filterRequest;
    }
}
