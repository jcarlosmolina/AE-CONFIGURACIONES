package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CambProductoGrupoAsociadoRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productosCambProductoGrupoAsociadopthisProducto;
    
    @JsonIgnore
    private Productos productosCambProductoGrupoAsociadopthisProductoInstance;

    @JsonProperty("p_evcgrupoasociado_oid")
    private GrupoAsociadoOid productosCambProductoGrupoAsociadopevcGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado productosCambProductoGrupoAsociadopevcGrupoAsociadoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkProductosCambProductoGrupoAsociadopthisProducto();
        checkProductosCambProductoGrupoAsociadopevcGrupoAsociado();
    }

    @JsonIgnore
    public ProductosOid getProductosCambProductoGrupoAsociadopthisProducto() {
        return this.productosCambProductoGrupoAsociadopthisProducto;
    }
    
    public void setProductosCambProductoGrupoAsociadopthisProducto(ProductosOid productosCambProductoGrupoAsociadopthisProducto) {
        this.productosCambProductoGrupoAsociadopthisProducto = productosCambProductoGrupoAsociadopthisProducto;
    }

    @JsonIgnore
    public Productos getProductosCambProductoGrupoAsociadopthisProductoInstance() {
        return this.productosCambProductoGrupoAsociadopthisProductoInstance;
    }
    
    public void setProductosCambProductoGrupoAsociadopthisProductoInstance(Productos productosCambProductoGrupoAsociadopthisProducto) {
        this.productosCambProductoGrupoAsociadopthisProductoInstance = productosCambProductoGrupoAsociadopthisProducto;
        this.productosCambProductoGrupoAsociadopthisProducto = productosCambProductoGrupoAsociadopthisProducto != null ? productosCambProductoGrupoAsociadopthisProducto.getOid() : null;
    }

    private void checkProductosCambProductoGrupoAsociadopthisProducto() throws NotNullArgumentException {
        if(this.getProductosCambProductoGrupoAsociadopthisProductoInstance() == null || this.getProductosCambProductoGrupoAsociadopthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPRODUCTOGRUPOASOCIADO, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPRODUCTOGRUPOASOCIADO_PRODUCTOSCAMBPRODUCTOGRUPOASOCIADOPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_CAMBPRODUCTOGRUPOASOCIADO, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPRODUCTOGRUPOASOCIADO_PRODUCTOSCAMBPRODUCTOGRUPOASOCIADOPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public GrupoAsociadoOid getProductosCambProductoGrupoAsociadopevcGrupoAsociado() {
        return this.productosCambProductoGrupoAsociadopevcGrupoAsociado;
    }
    
    public void setProductosCambProductoGrupoAsociadopevcGrupoAsociado(GrupoAsociadoOid productosCambProductoGrupoAsociadopevcGrupoAsociado) {
        this.productosCambProductoGrupoAsociadopevcGrupoAsociado = productosCambProductoGrupoAsociadopevcGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getProductosCambProductoGrupoAsociadopevcGrupoAsociadoInstance() {
        return this.productosCambProductoGrupoAsociadopevcGrupoAsociadoInstance;
    }
    
    public void setProductosCambProductoGrupoAsociadopevcGrupoAsociadoInstance(GrupoAsociado productosCambProductoGrupoAsociadopevcGrupoAsociado) {
        this.productosCambProductoGrupoAsociadopevcGrupoAsociadoInstance = productosCambProductoGrupoAsociadopevcGrupoAsociado;
        this.productosCambProductoGrupoAsociadopevcGrupoAsociado = productosCambProductoGrupoAsociadopevcGrupoAsociado != null ? productosCambProductoGrupoAsociadopevcGrupoAsociado.getOid() : null;
    }

    private void checkProductosCambProductoGrupoAsociadopevcGrupoAsociado() throws NotNullArgumentException {
        if(this.getProductosCambProductoGrupoAsociadopevcGrupoAsociadoInstance() == null || this.getProductosCambProductoGrupoAsociadopevcGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_CAMBPRODUCTOGRUPOASOCIADO, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_CAMBPRODUCTOGRUPOASOCIADO_PRODUCTOSCAMBPRODUCTOGRUPOASOCIADOPEVCGRUPOASOCIADO, ProductosConstants.SERV_ALIAS_CAMBPRODUCTOGRUPOASOCIADO, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_CAMBPRODUCTOGRUPOASOCIADO_PRODUCTOSCAMBPRODUCTOGRUPOASOCIADOPEVCGRUPOASOCIADO);
        }
    }
}
