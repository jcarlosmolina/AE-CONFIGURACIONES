package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class EliminarRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productoseliminarpthisProducto;
    
    @JsonIgnore
    private Productos productoseliminarpthisProductoInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkProductoseliminarpthisProducto();
    }

    @JsonIgnore
    public ProductosOid getProductoseliminarpthisProducto() {
        return this.productoseliminarpthisProducto;
    }
    
    public void setProductoseliminarpthisProducto(ProductosOid productoseliminarpthisProducto) {
        this.productoseliminarpthisProducto = productoseliminarpthisProducto;
    }

    @JsonIgnore
    public Productos getProductoseliminarpthisProductoInstance() {
        return this.productoseliminarpthisProductoInstance;
    }
    
    public void setProductoseliminarpthisProductoInstance(Productos productoseliminarpthisProducto) {
        this.productoseliminarpthisProductoInstance = productoseliminarpthisProducto;
        this.productoseliminarpthisProducto = productoseliminarpthisProducto != null ? productoseliminarpthisProducto.getOid() : null;
    }

    private void checkProductoseliminarpthisProducto() throws NotNullArgumentException {
        if(this.getProductoseliminarpthisProductoInstance() == null || this.getProductoseliminarpthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_ELIMINAR, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_ELIMINAR_PRODUCTOSELIMINARPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_ELIMINAR, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_ELIMINAR_PRODUCTOSELIMINARPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
