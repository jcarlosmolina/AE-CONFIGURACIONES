package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class ModificarRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productosmodificarpthisProducto;
    
    @JsonIgnore
    private Productos productosmodificarpthisProductoInstance;

    @JsonProperty("p_codanexomat")
    private Long productosmodificarpCodAnexoMat;

    @JsonProperty("p_codanexoneo")
    private Long productosmodificarpCodAnexoNeo;

    @JsonProperty("p_nit")
    private String productosmodificarpNIT;

    @JsonProperty("p_fechadocsolicitud")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date productosmodificarpFechaDocSolicitud;

    public void checkArguments() throws NotNullArgumentException {
        checkProductosmodificarpthisProducto();
    }

    @JsonIgnore
    public ProductosOid getProductosmodificarpthisProducto() {
        return this.productosmodificarpthisProducto;
    }
    
    public void setProductosmodificarpthisProducto(ProductosOid productosmodificarpthisProducto) {
        this.productosmodificarpthisProducto = productosmodificarpthisProducto;
    }

    @JsonIgnore
    public Productos getProductosmodificarpthisProductoInstance() {
        return this.productosmodificarpthisProductoInstance;
    }
    
    public void setProductosmodificarpthisProductoInstance(Productos productosmodificarpthisProducto) {
        this.productosmodificarpthisProductoInstance = productosmodificarpthisProducto;
        this.productosmodificarpthisProducto = productosmodificarpthisProducto != null ? productosmodificarpthisProducto.getOid() : null;
    }

    private void checkProductosmodificarpthisProducto() throws NotNullArgumentException {
        if(this.getProductosmodificarpthisProductoInstance() == null || this.getProductosmodificarpthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_MODIFICAR, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_MODIFICAR_PRODUCTOSMODIFICARPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_MODIFICAR, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_MODIFICAR_PRODUCTOSMODIFICARPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public Long getProductosmodificarpCodAnexoMat() {
        return this.productosmodificarpCodAnexoMat;
    }
    
    public void setProductosmodificarpCodAnexoMat(Long productosmodificarpCodAnexoMat) {
        this.productosmodificarpCodAnexoMat = productosmodificarpCodAnexoMat;
    }

    @JsonIgnore
    public Long getProductosmodificarpCodAnexoNeo() {
        return this.productosmodificarpCodAnexoNeo;
    }
    
    public void setProductosmodificarpCodAnexoNeo(Long productosmodificarpCodAnexoNeo) {
        this.productosmodificarpCodAnexoNeo = productosmodificarpCodAnexoNeo;
    }

    @JsonIgnore
    public String getProductosmodificarpNIT() {
        return this.productosmodificarpNIT;
    }
    
    public void setProductosmodificarpNIT(String productosmodificarpNIT) {
        this.productosmodificarpNIT = productosmodificarpNIT;
    }

    @JsonIgnore
    public Date getProductosmodificarpFechaDocSolicitud() {
        return this.productosmodificarpFechaDocSolicitud;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setProductosmodificarpFechaDocSolicitud(Date productosmodificarpFechaDocSolicitud) {
        this.productosmodificarpFechaDocSolicitud = productosmodificarpFechaDocSolicitud;
    }
}
