package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class CambProductoTarifaRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productosCambProductoTarifapthisProducto;
    
    @JsonIgnore
    private Productos productosCambProductoTarifapthisProductoInstance;

    @JsonProperty("p_evctarifa_oid")
    private TarifaOid productosCambProductoTarifapevcTarifa;
    
    @JsonIgnore
    private Tarifa productosCambProductoTarifapevcTarifaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkProductosCambProductoTarifapthisProducto();
        checkProductosCambProductoTarifapevcTarifa();
    }

    @JsonIgnore
    public ProductosOid getProductosCambProductoTarifapthisProducto() {
        return this.productosCambProductoTarifapthisProducto;
    }
    
    public void setProductosCambProductoTarifapthisProducto(ProductosOid productosCambProductoTarifapthisProducto) {
        this.productosCambProductoTarifapthisProducto = productosCambProductoTarifapthisProducto;
    }

    @JsonIgnore
    public Productos getProductosCambProductoTarifapthisProductoInstance() {
        return this.productosCambProductoTarifapthisProductoInstance;
    }
    
    public void setProductosCambProductoTarifapthisProductoInstance(Productos productosCambProductoTarifapthisProducto) {
        this.productosCambProductoTarifapthisProductoInstance = productosCambProductoTarifapthisProducto;
        this.productosCambProductoTarifapthisProducto = productosCambProductoTarifapthisProducto != null ? productosCambProductoTarifapthisProducto.getOid() : null;
    }

    private void checkProductosCambProductoTarifapthisProducto() throws NotNullArgumentException {
        if(this.getProductosCambProductoTarifapthisProductoInstance() == null || this.getProductosCambProductoTarifapthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPRODUCTOTARIFA, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPRODUCTOTARIFA_PRODUCTOSCAMBPRODUCTOTARIFAPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_CAMBPRODUCTOTARIFA, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPRODUCTOTARIFA_PRODUCTOSCAMBPRODUCTOTARIFAPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public TarifaOid getProductosCambProductoTarifapevcTarifa() {
        return this.productosCambProductoTarifapevcTarifa;
    }
    
    public void setProductosCambProductoTarifapevcTarifa(TarifaOid productosCambProductoTarifapevcTarifa) {
        this.productosCambProductoTarifapevcTarifa = productosCambProductoTarifapevcTarifa;
    }

    @JsonIgnore
    public Tarifa getProductosCambProductoTarifapevcTarifaInstance() {
        return this.productosCambProductoTarifapevcTarifaInstance;
    }
    
    public void setProductosCambProductoTarifapevcTarifaInstance(Tarifa productosCambProductoTarifapevcTarifa) {
        this.productosCambProductoTarifapevcTarifaInstance = productosCambProductoTarifapevcTarifa;
        this.productosCambProductoTarifapevcTarifa = productosCambProductoTarifapevcTarifa != null ? productosCambProductoTarifapevcTarifa.getOid() : null;
    }

    private void checkProductosCambProductoTarifapevcTarifa() throws NotNullArgumentException {
        if(this.getProductosCambProductoTarifapevcTarifaInstance() == null || this.getProductosCambProductoTarifapevcTarifaInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPRODUCTOTARIFA, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPRODUCTOTARIFA_PRODUCTOSCAMBPRODUCTOTARIFAPEVCTARIFA, ProductosConstants.SERV_ALIAS_CAMBPRODUCTOTARIFA, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPRODUCTOTARIFA_PRODUCTOSCAMBPRODUCTOTARIFAPEVCTARIFA);
        }
    }
}
