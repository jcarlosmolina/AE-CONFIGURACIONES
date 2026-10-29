package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class IFTarifaSegunEdad {

    @JsonProperty("vo_producto")
    private ProductosOid voProducto;

    @JsonProperty("vo_plan")
    private PlanesOid voPlan;

    @JsonProperty("v_edad")
    private Long vEdad;

    @JsonProperty("v_tipocontrato")
    private Long vTipoContrato;

    @JsonProperty("v_anyofechacontratacion")
    private Long vAnyoFechaContratacion;

    public IFTarifaSegunEdad() {
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
    public Long getVEdad() {
        return this.vEdad;
    }
    
    public void setVEdad(Long vEdad) {
        this.vEdad = vEdad;
    }

    @JsonIgnore
    public Long getVTipoContrato() {
        return this.vTipoContrato;
    }
    
    public void setVTipoContrato(Long vTipoContrato) {
        this.vTipoContrato = vTipoContrato;
    }

    @JsonIgnore
    public Long getVAnyoFechaContratacion() {
        return this.vAnyoFechaContratacion;
    }
    
    public void setVAnyoFechaContratacion(Long vAnyoFechaContratacion) {
        this.vAnyoFechaContratacion = vAnyoFechaContratacion;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(DetalleTarifaConstants.FILTER_NAME_IFTARIFASEGUNEDAD);
        filterRequest.addFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPRODUCTO, voProducto);
        filterRequest.addFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPLAN, voPlan);
        filterRequest.addFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VEDAD, vEdad);
        filterRequest.addFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VTIPOCONTRATO, vTipoContrato);
        filterRequest.addFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VANYOFECHACONTRATACION, vAnyoFechaContratacion);
        return filterRequest;
    }
}
