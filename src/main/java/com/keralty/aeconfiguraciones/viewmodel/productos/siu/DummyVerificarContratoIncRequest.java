package com.keralty.aeconfiguraciones.viewmodel.productos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class DummyVerificarContratoIncRequest {

    @JsonProperty("p_thisproducto_oid")
    private ProductosOid productosdummyVerificarContratoIncpthisProducto;
    
    @JsonIgnore
    private Productos productosdummyVerificarContratoIncpthisProductoInstance;

    @JsonProperty("p_contratoc_oid")
    private ContratoCOid productosdummyVerificarContratoIncpContratoC;
    
    @JsonIgnore
    private ContratoC productosdummyVerificarContratoIncpContratoCInstance;

    @JsonProperty("p_numfamilia")
    private String productosdummyVerificarContratoIncpNumFamilia;

    @JsonProperty("p_fechainiciocontrato")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date productosdummyVerificarContratoIncpFechaInicioContrato;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkProductosdummyVerificarContratoIncpthisProducto();
        checkProductosdummyVerificarContratoIncpFechaInicioContrato();
    }

    @JsonIgnore
    public ProductosOid getProductosdummyVerificarContratoIncpthisProducto() {
        return this.productosdummyVerificarContratoIncpthisProducto;
    }
    
    public void setProductosdummyVerificarContratoIncpthisProducto(ProductosOid productosdummyVerificarContratoIncpthisProducto) {
        this.productosdummyVerificarContratoIncpthisProducto = productosdummyVerificarContratoIncpthisProducto;
    }

    @JsonIgnore
    public Productos getProductosdummyVerificarContratoIncpthisProductoInstance() {
        return this.productosdummyVerificarContratoIncpthisProductoInstance;
    }
    
    public void setProductosdummyVerificarContratoIncpthisProductoInstance(Productos productosdummyVerificarContratoIncpthisProducto) {
        this.productosdummyVerificarContratoIncpthisProductoInstance = productosdummyVerificarContratoIncpthisProducto;
        this.productosdummyVerificarContratoIncpthisProducto = productosdummyVerificarContratoIncpthisProducto != null ? productosdummyVerificarContratoIncpthisProducto.getOid() : null;
    }

    private void checkProductosdummyVerificarContratoIncpthisProducto() throws NotNullArgumentException {
        if(this.getProductosdummyVerificarContratoIncpthisProductoInstance() == null || this.getProductosdummyVerificarContratoIncpthisProductoInstance().isNull()) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_DUMMYVERIFICARCONTRATOINC, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPTHISPRODUCTO, ProductosConstants.SERV_ALIAS_DUMMYVERIFICARCONTRATOINC, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPTHISPRODUCTO);
        }
    }

    @JsonIgnore
    public ContratoCOid getProductosdummyVerificarContratoIncpContratoC() {
        return this.productosdummyVerificarContratoIncpContratoC;
    }
    
    public void setProductosdummyVerificarContratoIncpContratoC(ContratoCOid productosdummyVerificarContratoIncpContratoC) {
        this.productosdummyVerificarContratoIncpContratoC = productosdummyVerificarContratoIncpContratoC;
    }

    @JsonIgnore
    public ContratoC getProductosdummyVerificarContratoIncpContratoCInstance() {
        return this.productosdummyVerificarContratoIncpContratoCInstance;
    }
    
    public void setProductosdummyVerificarContratoIncpContratoCInstance(ContratoC productosdummyVerificarContratoIncpContratoC) {
        this.productosdummyVerificarContratoIncpContratoCInstance = productosdummyVerificarContratoIncpContratoC;
        this.productosdummyVerificarContratoIncpContratoC = productosdummyVerificarContratoIncpContratoC != null ? productosdummyVerificarContratoIncpContratoC.getOid() : null;
    }

    @JsonIgnore
    public String getProductosdummyVerificarContratoIncpNumFamilia() {
        return this.productosdummyVerificarContratoIncpNumFamilia;
    }
    
    public void setProductosdummyVerificarContratoIncpNumFamilia(String productosdummyVerificarContratoIncpNumFamilia) {
        this.productosdummyVerificarContratoIncpNumFamilia = productosdummyVerificarContratoIncpNumFamilia;
    }

    @JsonIgnore
    public Date getProductosdummyVerificarContratoIncpFechaInicioContrato() {
        return this.productosdummyVerificarContratoIncpFechaInicioContrato;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setProductosdummyVerificarContratoIncpFechaInicioContrato(Date productosdummyVerificarContratoIncpFechaInicioContrato) {
        this.productosdummyVerificarContratoIncpFechaInicioContrato = productosdummyVerificarContratoIncpFechaInicioContrato;
    }

    private void checkProductosdummyVerificarContratoIncpFechaInicioContrato() throws NotNullArgumentException {
        if(this.getProductosdummyVerificarContratoIncpFechaInicioContrato() == null) {
            throw new NotNullArgumentException(ProductosConstants.SERV_ID_DUMMYVERIFICARCONTRATOINC, ProductosConstants.CLASS_ID, ProductosConstants.ARG_ID_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPFECHAINICIOCONTRATO, ProductosConstants.SERV_ALIAS_DUMMYVERIFICARCONTRATOINC, ProductosConstants.CLASS_ALIAS, ProductosConstants.ARG_ALIAS_DUMMYVERIFICARCONTRATOINC_PRODUCTOSDUMMYVERIFICARCONTRATOINCPFECHAINICIOCONTRATO);
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
