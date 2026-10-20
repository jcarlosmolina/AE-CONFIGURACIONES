package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CambProductoGrupoAsociadoRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance;

    @JsonProperty("p_evcproductos_oid")
    private ProductosOid grupoAsociadoCambProductoGrupoAsociadopevcProductos;
    
    @JsonIgnore
    private Productos grupoAsociadoCambProductoGrupoAsociadopevcProductosInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado();
        checkGrupoAsociadoCambProductoGrupoAsociadopevcProductos();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado() {
        return this.grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado;
    }
    
    public void setGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado(GrupoAsociadoOid grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado) {
        this.grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado = grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance() {
        return this.grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado) {
        this.grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance = grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado;
        this.grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado = grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado != null ? grupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance() == null || this.getGrupoAsociadoCambProductoGrupoAsociadopthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CAMBPRODUCTOGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CAMBPRODUCTOGRUPOASOCIADO_GRUPOASOCIADOCAMBPRODUCTOGRUPOASOCIADOPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_CAMBPRODUCTOGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CAMBPRODUCTOGRUPOASOCIADO_GRUPOASOCIADOCAMBPRODUCTOGRUPOASOCIADOPTHISGRUPOASOCIADO);
        }
    }

    @JsonIgnore
    public ProductosOid getGrupoAsociadoCambProductoGrupoAsociadopevcProductos() {
        return this.grupoAsociadoCambProductoGrupoAsociadopevcProductos;
    }
    
    public void setGrupoAsociadoCambProductoGrupoAsociadopevcProductos(ProductosOid grupoAsociadoCambProductoGrupoAsociadopevcProductos) {
        this.grupoAsociadoCambProductoGrupoAsociadopevcProductos = grupoAsociadoCambProductoGrupoAsociadopevcProductos;
    }

    @JsonIgnore
    public Productos getGrupoAsociadoCambProductoGrupoAsociadopevcProductosInstance() {
        return this.grupoAsociadoCambProductoGrupoAsociadopevcProductosInstance;
    }
    
    public void setGrupoAsociadoCambProductoGrupoAsociadopevcProductosInstance(Productos grupoAsociadoCambProductoGrupoAsociadopevcProductos) {
        this.grupoAsociadoCambProductoGrupoAsociadopevcProductosInstance = grupoAsociadoCambProductoGrupoAsociadopevcProductos;
        this.grupoAsociadoCambProductoGrupoAsociadopevcProductos = grupoAsociadoCambProductoGrupoAsociadopevcProductos != null ? grupoAsociadoCambProductoGrupoAsociadopevcProductos.getOid() : null;
    }

    private void checkGrupoAsociadoCambProductoGrupoAsociadopevcProductos() throws NotNullArgumentException {
        if(this.getGrupoAsociadoCambProductoGrupoAsociadopevcProductosInstance() == null || this.getGrupoAsociadoCambProductoGrupoAsociadopevcProductosInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CAMBPRODUCTOGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CAMBPRODUCTOGRUPOASOCIADO_GRUPOASOCIADOCAMBPRODUCTOGRUPOASOCIADOPEVCPRODUCTOS, GrupoAsociadoConstants.SERV_ALIAS_CAMBPRODUCTOGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CAMBPRODUCTOGRUPOASOCIADO_GRUPOASOCIADOCAMBPRODUCTOGRUPOASOCIADOPEVCPRODUCTOS);
        }
    }
}
