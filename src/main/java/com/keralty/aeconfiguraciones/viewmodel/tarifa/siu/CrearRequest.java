package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CrearRequest {

    @JsonProperty("p_agrplant_oid")
    private PlanesOid tarifacrearpagrPlanT;
    
    @JsonIgnore
    private Planes tarifacrearpagrPlanTInstance;

    @JsonProperty("p_agrproductot_oid")
    private ProductosOid tarifacrearpagrProductoT;
    
    @JsonIgnore
    private Productos tarifacrearpagrProductoTInstance;

    @JsonProperty("p_atrcodigo")
    private String tarifacrearpatrCodigo;

    @JsonProperty("p_atrtipocontrato")
    private Long tarifacrearpatrTipoContrato;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifacrearpagrPlanT();
        checkTarifacrearpagrProductoT();
        checkTarifacrearpatrCodigo();
        checkTarifacrearpatrTipoContrato();
    }

    @JsonIgnore
    public PlanesOid getTarifacrearpagrPlanT() {
        return this.tarifacrearpagrPlanT;
    }
    
    public void setTarifacrearpagrPlanT(PlanesOid tarifacrearpagrPlanT) {
        this.tarifacrearpagrPlanT = tarifacrearpagrPlanT;
    }

    @JsonIgnore
    public Planes getTarifacrearpagrPlanTInstance() {
        return this.tarifacrearpagrPlanTInstance;
    }
    
    public void setTarifacrearpagrPlanTInstance(Planes tarifacrearpagrPlanT) {
        this.tarifacrearpagrPlanTInstance = tarifacrearpagrPlanT;
        this.tarifacrearpagrPlanT = tarifacrearpagrPlanT != null ? tarifacrearpagrPlanT.getOid() : null;
    }

    private void checkTarifacrearpagrPlanT() throws NotNullArgumentException {
        if(this.getTarifacrearpagrPlanTInstance() == null || this.getTarifacrearpagrPlanTInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CREAR_TARIFACREARPAGRPLANT, TarifaConstants.SERV_ALIAS_CREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CREAR_TARIFACREARPAGRPLANT);
        }
    }

    @JsonIgnore
    public ProductosOid getTarifacrearpagrProductoT() {
        return this.tarifacrearpagrProductoT;
    }
    
    public void setTarifacrearpagrProductoT(ProductosOid tarifacrearpagrProductoT) {
        this.tarifacrearpagrProductoT = tarifacrearpagrProductoT;
    }

    @JsonIgnore
    public Productos getTarifacrearpagrProductoTInstance() {
        return this.tarifacrearpagrProductoTInstance;
    }
    
    public void setTarifacrearpagrProductoTInstance(Productos tarifacrearpagrProductoT) {
        this.tarifacrearpagrProductoTInstance = tarifacrearpagrProductoT;
        this.tarifacrearpagrProductoT = tarifacrearpagrProductoT != null ? tarifacrearpagrProductoT.getOid() : null;
    }

    private void checkTarifacrearpagrProductoT() throws NotNullArgumentException {
        if(this.getTarifacrearpagrProductoTInstance() == null || this.getTarifacrearpagrProductoTInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CREAR_TARIFACREARPAGRPRODUCTOT, TarifaConstants.SERV_ALIAS_CREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CREAR_TARIFACREARPAGRPRODUCTOT);
        }
    }

    @JsonIgnore
    public String getTarifacrearpatrCodigo() {
        return this.tarifacrearpatrCodigo;
    }
    
    public void setTarifacrearpatrCodigo(String tarifacrearpatrCodigo) {
        this.tarifacrearpatrCodigo = tarifacrearpatrCodigo;
    }

    private void checkTarifacrearpatrCodigo() throws NotNullArgumentException {
        if(this.getTarifacrearpatrCodigo() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CREAR_TARIFACREARPATRCODIGO, TarifaConstants.SERV_ALIAS_CREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CREAR_TARIFACREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public Long getTarifacrearpatrTipoContrato() {
        return this.tarifacrearpatrTipoContrato;
    }
    
    public void setTarifacrearpatrTipoContrato(Long tarifacrearpatrTipoContrato) {
        this.tarifacrearpatrTipoContrato = tarifacrearpatrTipoContrato;
    }

    private void checkTarifacrearpatrTipoContrato() throws NotNullArgumentException {
        if(this.getTarifacrearpatrTipoContrato() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_CREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_CREAR_TARIFACREARPATRTIPOCONTRATO, TarifaConstants.SERV_ALIAS_CREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_CREAR_TARIFACREARPATRTIPOCONTRATO);
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
