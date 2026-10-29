package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CambPlanDeProductoRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productosCambPlanDeProductopthisProducto;
    
    @JsonIgnore
    private Productos productosCambPlanDeProductopthisProductoInstance;

    @JsonProperty("p_evcplanes_oid")
    private PlanesOid productosCambPlanDeProductopevcPlanes;
    
    @JsonIgnore
    private Planes productosCambPlanDeProductopevcPlanesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkProductosCambPlanDeProductopthisProducto();
        checkProductosCambPlanDeProductopevcPlanes();
    }

    @JsonIgnore
    public ProductosOid getProductosCambPlanDeProductopthisProducto() {
        return this.productosCambPlanDeProductopthisProducto;
    }
    
    public void setProductosCambPlanDeProductopthisProducto(ProductosOid productosCambPlanDeProductopthisProducto) {
        this.productosCambPlanDeProductopthisProducto = productosCambPlanDeProductopthisProducto;
    }

    @JsonIgnore
    public Productos getProductosCambPlanDeProductopthisProductoInstance() {
        return this.productosCambPlanDeProductopthisProductoInstance;
    }
    
    public void setProductosCambPlanDeProductopthisProductoInstance(Productos productosCambPlanDeProductopthisProducto) {
        this.productosCambPlanDeProductopthisProductoInstance = productosCambPlanDeProductopthisProducto;
        this.productosCambPlanDeProductopthisProducto = productosCambPlanDeProductopthisProducto != null ? productosCambPlanDeProductopthisProducto.getOid() : null;
    }

    private void checkProductosCambPlanDeProductopthisProducto() throws NotNullArgumentException {
        if(this.getProductosCambPlanDeProductopthisProductoInstance() == null || this.getProductosCambPlanDeProductopthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPLANDEPRODUCTO, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPLANDEPRODUCTO_PRODUCTOSCAMBPLANDEPRODUCTOPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_CAMBPLANDEPRODUCTO, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPLANDEPRODUCTO_PRODUCTOSCAMBPLANDEPRODUCTOPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public PlanesOid getProductosCambPlanDeProductopevcPlanes() {
        return this.productosCambPlanDeProductopevcPlanes;
    }
    
    public void setProductosCambPlanDeProductopevcPlanes(PlanesOid productosCambPlanDeProductopevcPlanes) {
        this.productosCambPlanDeProductopevcPlanes = productosCambPlanDeProductopevcPlanes;
    }

    @JsonIgnore
    public Planes getProductosCambPlanDeProductopevcPlanesInstance() {
        return this.productosCambPlanDeProductopevcPlanesInstance;
    }
    
    public void setProductosCambPlanDeProductopevcPlanesInstance(Planes productosCambPlanDeProductopevcPlanes) {
        this.productosCambPlanDeProductopevcPlanesInstance = productosCambPlanDeProductopevcPlanes;
        this.productosCambPlanDeProductopevcPlanes = productosCambPlanDeProductopevcPlanes != null ? productosCambPlanDeProductopevcPlanes.getOid() : null;
    }

    private void checkProductosCambPlanDeProductopevcPlanes() throws NotNullArgumentException {
        if(this.getProductosCambPlanDeProductopevcPlanesInstance() == null || this.getProductosCambPlanDeProductopevcPlanesInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPLANDEPRODUCTO, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPLANDEPRODUCTO_PRODUCTOSCAMBPLANDEPRODUCTOPEVCPLANES, ProductosConstants.SERV_ALIAS_CAMBPLANDEPRODUCTO, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPLANDEPRODUCTO_PRODUCTOSCAMBPLANDEPRODUCTOPEVCPLANES);
        }
    }
}
