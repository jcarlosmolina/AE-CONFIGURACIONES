package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CambPlanDeProductoRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planesCambPlanDeProductopthisPlan;
    
    @JsonIgnore
    private Planes planesCambPlanDeProductopthisPlanInstance;

    @JsonProperty("p_evcproductos_oid")
    private ProductosOid planesCambPlanDeProductopevcProductos;
    
    @JsonIgnore
    private Productos planesCambPlanDeProductopevcProductosInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanesCambPlanDeProductopthisPlan();
        checkPlanesCambPlanDeProductopevcProductos();
    }

    @JsonIgnore
    public PlanesOid getPlanesCambPlanDeProductopthisPlan() {
        return this.planesCambPlanDeProductopthisPlan;
    }
    
    public void setPlanesCambPlanDeProductopthisPlan(PlanesOid planesCambPlanDeProductopthisPlan) {
        this.planesCambPlanDeProductopthisPlan = planesCambPlanDeProductopthisPlan;
    }

    @JsonIgnore
    public Planes getPlanesCambPlanDeProductopthisPlanInstance() {
        return this.planesCambPlanDeProductopthisPlanInstance;
    }
    
    public void setPlanesCambPlanDeProductopthisPlanInstance(Planes planesCambPlanDeProductopthisPlan) {
        this.planesCambPlanDeProductopthisPlanInstance = planesCambPlanDeProductopthisPlan;
        this.planesCambPlanDeProductopthisPlan = planesCambPlanDeProductopthisPlan != null ? planesCambPlanDeProductopthisPlan.getOid() : null;
    }

    private void checkPlanesCambPlanDeProductopthisPlan() throws NotNullArgumentException {
        if(this.getPlanesCambPlanDeProductopthisPlanInstance() == null || this.getPlanesCambPlanDeProductopthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANDEPRODUCTO, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANDEPRODUCTO_PLANESCAMBPLANDEPRODUCTOPTHISPLAN, PlanesConstants.SERV_ALIAS_CAMBPLANDEPRODUCTO, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANDEPRODUCTO_PLANESCAMBPLANDEPRODUCTOPTHISPLAN);
        }
    }

    @JsonIgnore
    public ProductosOid getPlanesCambPlanDeProductopevcProductos() {
        return this.planesCambPlanDeProductopevcProductos;
    }
    
    public void setPlanesCambPlanDeProductopevcProductos(ProductosOid planesCambPlanDeProductopevcProductos) {
        this.planesCambPlanDeProductopevcProductos = planesCambPlanDeProductopevcProductos;
    }

    @JsonIgnore
    public Productos getPlanesCambPlanDeProductopevcProductosInstance() {
        return this.planesCambPlanDeProductopevcProductosInstance;
    }
    
    public void setPlanesCambPlanDeProductopevcProductosInstance(Productos planesCambPlanDeProductopevcProductos) {
        this.planesCambPlanDeProductopevcProductosInstance = planesCambPlanDeProductopevcProductos;
        this.planesCambPlanDeProductopevcProductos = planesCambPlanDeProductopevcProductos != null ? planesCambPlanDeProductopevcProductos.getOid() : null;
    }

    private void checkPlanesCambPlanDeProductopevcProductos() throws NotNullArgumentException {
        if(this.getPlanesCambPlanDeProductopevcProductosInstance() == null || this.getPlanesCambPlanDeProductopevcProductosInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANDEPRODUCTO, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANDEPRODUCTO_PLANESCAMBPLANDEPRODUCTOPEVCPRODUCTOS, PlanesConstants.SERV_ALIAS_CAMBPLANDEPRODUCTO, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANDEPRODUCTO_PLANESCAMBPLANDEPRODUCTOPEVCPRODUCTOS);
        }
    }
}
