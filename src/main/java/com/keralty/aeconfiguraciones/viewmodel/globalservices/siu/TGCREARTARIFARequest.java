package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class TGCREARTARIFARequest {

    @JsonProperty("pproducto_json")
    private String tGCREARTARIFApProducto;
    
    @JsonIgnore
    private Productos tGCREARTARIFApProductoInstance;

    @JsonProperty("pplan_json")
    private String tGCREARTARIFApPlan;
    
    @JsonIgnore
    private Planes tGCREARTARIFApPlanInstance;

    @JsonProperty("ptipocontrato")
    private Long tGCREARTARIFApTipoContrato;

    @JsonProperty("pcodigo")
    private String tGCREARTARIFApCodigo;

    public void checkArguments() throws NotNullArgumentException {
        checkTGCREARTARIFApProducto();
        checkTGCREARTARIFApPlan();
        checkTGCREARTARIFApTipoContrato();
        checkTGCREARTARIFApCodigo();
    }

    @JsonIgnore
    public String getTGCREARTARIFApProducto() {
        return this.tGCREARTARIFApProducto;
    }
    
    public void setTGCREARTARIFApProducto(String tGCREARTARIFApProducto) {
        this.tGCREARTARIFApProducto = tGCREARTARIFApProducto;
    }

    @JsonIgnore
    public Productos getTGCREARTARIFApProductoInstance() {
        return this.tGCREARTARIFApProductoInstance;
    }
    
    public void setTGCREARTARIFApProductoInstance(Productos tGCREARTARIFApProducto) {
        this.tGCREARTARIFApProductoInstance = tGCREARTARIFApProducto;
        this.tGCREARTARIFApProducto = tGCREARTARIFApProducto != null ? tGCREARTARIFApProducto.getOid().toJson() : "";
    }

    private void checkTGCREARTARIFApProducto() throws NotNullArgumentException {
        if(this.getTGCREARTARIFApProductoInstance() == null || this.getTGCREARTARIFApProductoInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ID_TGCREARTARIFA_TGCREARTARIFAPPRODUCTO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARTARIFA_TGCREARTARIFAPPRODUCTO);
        }
    }

    @JsonIgnore
    public String getTGCREARTARIFApPlan() {
        return this.tGCREARTARIFApPlan;
    }
    
    public void setTGCREARTARIFApPlan(String tGCREARTARIFApPlan) {
        this.tGCREARTARIFApPlan = tGCREARTARIFApPlan;
    }

    @JsonIgnore
    public Planes getTGCREARTARIFApPlanInstance() {
        return this.tGCREARTARIFApPlanInstance;
    }
    
    public void setTGCREARTARIFApPlanInstance(Planes tGCREARTARIFApPlan) {
        this.tGCREARTARIFApPlanInstance = tGCREARTARIFApPlan;
        this.tGCREARTARIFApPlan = tGCREARTARIFApPlan != null ? tGCREARTARIFApPlan.getOid().toJson() : "";
    }

    private void checkTGCREARTARIFApPlan() throws NotNullArgumentException {
        if(this.getTGCREARTARIFApPlanInstance() == null || this.getTGCREARTARIFApPlanInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ID_TGCREARTARIFA_TGCREARTARIFAPPLAN, GlobalServiceConstants.G_SERV_ALIAS_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARTARIFA_TGCREARTARIFAPPLAN);
        }
    }

    @JsonIgnore
    public Long getTGCREARTARIFApTipoContrato() {
        return this.tGCREARTARIFApTipoContrato;
    }
    
    public void setTGCREARTARIFApTipoContrato(Long tGCREARTARIFApTipoContrato) {
        this.tGCREARTARIFApTipoContrato = tGCREARTARIFApTipoContrato;
    }

    private void checkTGCREARTARIFApTipoContrato() throws NotNullArgumentException {
        if(this.getTGCREARTARIFApTipoContrato() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ID_TGCREARTARIFA_TGCREARTARIFAPTIPOCONTRATO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARTARIFA_TGCREARTARIFAPTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public String getTGCREARTARIFApCodigo() {
        return this.tGCREARTARIFApCodigo;
    }
    
    public void setTGCREARTARIFApCodigo(String tGCREARTARIFApCodigo) {
        this.tGCREARTARIFApCodigo = tGCREARTARIFApCodigo;
    }

    private void checkTGCREARTARIFApCodigo() throws NotNullArgumentException {
        if(this.getTGCREARTARIFApCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ID_TGCREARTARIFA_TGCREARTARIFAPCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARTARIFA, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARTARIFA_TGCREARTARIFAPCODIGO);
        }
    }
}
