package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class TCREARRequest {

    @JsonProperty("pproducto_json")
    private String tarifaTCREARpProducto;
    
    @JsonIgnore
    private Productos tarifaTCREARpProductoInstance;

    @JsonProperty("pplan_json")
    private String tarifaTCREARpPlan;
    
    @JsonIgnore
    private Planes tarifaTCREARpPlanInstance;

    @JsonProperty("ptipocontrato")
    private Long tarifaTCREARpTipoContrato;

    @JsonProperty("pcodigo")
    private String tarifaTCREARpCodigo;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifaTCREARpProducto();
        checkTarifaTCREARpPlan();
        checkTarifaTCREARpTipoContrato();
        checkTarifaTCREARpCodigo();
    }

    @JsonIgnore
    public String getTarifaTCREARpProducto() {
        return this.tarifaTCREARpProducto;
    }
    
    public void setTarifaTCREARpProducto(String tarifaTCREARpProducto) {
        this.tarifaTCREARpProducto = tarifaTCREARpProducto;
    }

    @JsonIgnore
    public Productos getTarifaTCREARpProductoInstance() {
        return this.tarifaTCREARpProductoInstance;
    }
    
    public void setTarifaTCREARpProductoInstance(Productos tarifaTCREARpProducto) {
        this.tarifaTCREARpProductoInstance = tarifaTCREARpProducto;
        this.tarifaTCREARpProducto = tarifaTCREARpProducto != null ? tarifaTCREARpProducto.getOid().toJson() : "";
    }

    private void checkTarifaTCREARpProducto() throws NotNullArgumentException {
        if(this.getTarifaTCREARpProductoInstance() == null || this.getTarifaTCREARpProductoInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TCREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TCREAR_TARIFATCREARPPRODUCTO, TarifaConstants.SERV_ALIAS_TCREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TCREAR_TARIFATCREARPPRODUCTO);
        }
    }

    @JsonIgnore
    public String getTarifaTCREARpPlan() {
        return this.tarifaTCREARpPlan;
    }
    
    public void setTarifaTCREARpPlan(String tarifaTCREARpPlan) {
        this.tarifaTCREARpPlan = tarifaTCREARpPlan;
    }

    @JsonIgnore
    public Planes getTarifaTCREARpPlanInstance() {
        return this.tarifaTCREARpPlanInstance;
    }
    
    public void setTarifaTCREARpPlanInstance(Planes tarifaTCREARpPlan) {
        this.tarifaTCREARpPlanInstance = tarifaTCREARpPlan;
        this.tarifaTCREARpPlan = tarifaTCREARpPlan != null ? tarifaTCREARpPlan.getOid().toJson() : "";
    }

    private void checkTarifaTCREARpPlan() throws NotNullArgumentException {
        if(this.getTarifaTCREARpPlanInstance() == null || this.getTarifaTCREARpPlanInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TCREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TCREAR_TARIFATCREARPPLAN, TarifaConstants.SERV_ALIAS_TCREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TCREAR_TARIFATCREARPPLAN);
        }
    }

    @JsonIgnore
    public Long getTarifaTCREARpTipoContrato() {
        return this.tarifaTCREARpTipoContrato;
    }
    
    public void setTarifaTCREARpTipoContrato(Long tarifaTCREARpTipoContrato) {
        this.tarifaTCREARpTipoContrato = tarifaTCREARpTipoContrato;
    }

    private void checkTarifaTCREARpTipoContrato() throws NotNullArgumentException {
        if(this.getTarifaTCREARpTipoContrato() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TCREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TCREAR_TARIFATCREARPTIPOCONTRATO, TarifaConstants.SERV_ALIAS_TCREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TCREAR_TARIFATCREARPTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public String getTarifaTCREARpCodigo() {
        return this.tarifaTCREARpCodigo;
    }
    
    public void setTarifaTCREARpCodigo(String tarifaTCREARpCodigo) {
        this.tarifaTCREARpCodigo = tarifaTCREARpCodigo;
    }

    private void checkTarifaTCREARpCodigo() throws NotNullArgumentException {
        if(this.getTarifaTCREARpCodigo() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TCREAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TCREAR_TARIFATCREARPCODIGO, TarifaConstants.SERV_ALIAS_TCREAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TCREAR_TARIFATCREARPCODIGO);
        }
    }
}
