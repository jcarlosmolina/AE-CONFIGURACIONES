package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class CambProductoTarifaRequest {

    @JsonProperty("p_thistarifa_oid")
    private TarifaOid tarifaCambProductoTarifapthisTarifa;
    
    @JsonIgnore
    private Tarifa tarifaCambProductoTarifapthisTarifaInstance;

    @JsonProperty("p_evcproductos_oid")
    private ProductosOid tarifaCambProductoTarifapevcProductos;
    
    @JsonIgnore
    private Productos tarifaCambProductoTarifapevcProductosInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifaCambProductoTarifapthisTarifa();
        checkTarifaCambProductoTarifapevcProductos();
    }

    @JsonIgnore
    public TarifaOid getTarifaCambProductoTarifapthisTarifa() {
        return this.tarifaCambProductoTarifapthisTarifa;
    }
    
    public void setTarifaCambProductoTarifapthisTarifa(TarifaOid tarifaCambProductoTarifapthisTarifa) {
        this.tarifaCambProductoTarifapthisTarifa = tarifaCambProductoTarifapthisTarifa;
    }

    @JsonIgnore
    public Tarifa getTarifaCambProductoTarifapthisTarifaInstance() {
        return this.tarifaCambProductoTarifapthisTarifaInstance;
    }
    
    public void setTarifaCambProductoTarifapthisTarifaInstance(Tarifa tarifaCambProductoTarifapthisTarifa) {
        this.tarifaCambProductoTarifapthisTarifaInstance = tarifaCambProductoTarifapthisTarifa;
        this.tarifaCambProductoTarifapthisTarifa = tarifaCambProductoTarifapthisTarifa != null ? tarifaCambProductoTarifapthisTarifa.getOid() : null;
    }

    private void checkTarifaCambProductoTarifapthisTarifa() throws NotNullArgumentException {
        if(this.getTarifaCambProductoTarifapthisTarifaInstance() == null || this.getTarifaCambProductoTarifapthisTarifaInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CAMBPRODUCTOTARIFA, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CAMBPRODUCTOTARIFA_TARIFACAMBPRODUCTOTARIFAPTHISTARIFA, TarifaConstants.SERV_ALIAS_CAMBPRODUCTOTARIFA, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CAMBPRODUCTOTARIFA_TARIFACAMBPRODUCTOTARIFAPTHISTARIFA);
        }
    }

    @JsonIgnore
    public ProductosOid getTarifaCambProductoTarifapevcProductos() {
        return this.tarifaCambProductoTarifapevcProductos;
    }
    
    public void setTarifaCambProductoTarifapevcProductos(ProductosOid tarifaCambProductoTarifapevcProductos) {
        this.tarifaCambProductoTarifapevcProductos = tarifaCambProductoTarifapevcProductos;
    }

    @JsonIgnore
    public Productos getTarifaCambProductoTarifapevcProductosInstance() {
        return this.tarifaCambProductoTarifapevcProductosInstance;
    }
    
    public void setTarifaCambProductoTarifapevcProductosInstance(Productos tarifaCambProductoTarifapevcProductos) {
        this.tarifaCambProductoTarifapevcProductosInstance = tarifaCambProductoTarifapevcProductos;
        this.tarifaCambProductoTarifapevcProductos = tarifaCambProductoTarifapevcProductos != null ? tarifaCambProductoTarifapevcProductos.getOid() : null;
    }

    private void checkTarifaCambProductoTarifapevcProductos() throws NotNullArgumentException {
        if(this.getTarifaCambProductoTarifapevcProductosInstance() == null || this.getTarifaCambProductoTarifapevcProductosInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CAMBPRODUCTOTARIFA, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CAMBPRODUCTOTARIFA_TARIFACAMBPRODUCTOTARIFAPEVCPRODUCTOS, TarifaConstants.SERV_ALIAS_CAMBPRODUCTOTARIFA, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CAMBPRODUCTOTARIFA_TARIFACAMBPRODUCTOTARIFAPEVCPRODUCTOS);
        }
    }
}
