package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class TSINCRONIZARRequest {

    @JsonProperty("p_thistarifa_oid")
    private TarifaOid tarifaTSINCRONIZARpthisTarifa;
    
    @JsonIgnore
    private Tarifa tarifaTSINCRONIZARpthisTarifaInstance;

    @JsonProperty("pt_p_tipocontrato")
    private Long tarifaTSINCRONIZARptpTipoContrato;

    @JsonProperty("pt_p_evcplan_oid")
    private PlanesOid tarifaTSINCRONIZARptpevcPlan;
    
    @JsonIgnore
    private Planes tarifaTSINCRONIZARptpevcPlanInstance;

    @JsonProperty("pt_p_evcproducto_oid")
    private ProductosOid tarifaTSINCRONIZARptpevcProducto;
    
    @JsonIgnore
    private Productos tarifaTSINCRONIZARptpevcProductoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifaTSINCRONIZARpthisTarifa();
        checkTarifaTSINCRONIZARptpTipoContrato();
        checkTarifaTSINCRONIZARptpevcPlan();
        checkTarifaTSINCRONIZARptpevcProducto();
    }

    @JsonIgnore
    public TarifaOid getTarifaTSINCRONIZARpthisTarifa() {
        return this.tarifaTSINCRONIZARpthisTarifa;
    }
    
    public void setTarifaTSINCRONIZARpthisTarifa(TarifaOid tarifaTSINCRONIZARpthisTarifa) {
        this.tarifaTSINCRONIZARpthisTarifa = tarifaTSINCRONIZARpthisTarifa;
    }

    @JsonIgnore
    public Tarifa getTarifaTSINCRONIZARpthisTarifaInstance() {
        return this.tarifaTSINCRONIZARpthisTarifaInstance;
    }
    
    public void setTarifaTSINCRONIZARpthisTarifaInstance(Tarifa tarifaTSINCRONIZARpthisTarifa) {
        this.tarifaTSINCRONIZARpthisTarifaInstance = tarifaTSINCRONIZARpthisTarifa;
        this.tarifaTSINCRONIZARpthisTarifa = tarifaTSINCRONIZARpthisTarifa != null ? tarifaTSINCRONIZARpthisTarifa.getOid() : null;
    }

    private void checkTarifaTSINCRONIZARpthisTarifa() throws NotNullArgumentException {
        if(this.getTarifaTSINCRONIZARpthisTarifaInstance() == null || this.getTarifaTSINCRONIZARpthisTarifaInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TSINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TSINCRONIZAR_TARIFATSINCRONIZARPTHISTARIFA, TarifaConstants.SERV_ALIAS_TSINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TSINCRONIZAR_TARIFATSINCRONIZARPTHISTARIFA);
        }
    }

    @JsonIgnore
    public Long getTarifaTSINCRONIZARptpTipoContrato() {
        return this.tarifaTSINCRONIZARptpTipoContrato;
    }
    
    public void setTarifaTSINCRONIZARptpTipoContrato(Long tarifaTSINCRONIZARptpTipoContrato) {
        this.tarifaTSINCRONIZARptpTipoContrato = tarifaTSINCRONIZARptpTipoContrato;
    }

    private void checkTarifaTSINCRONIZARptpTipoContrato() throws NotNullArgumentException {
        if(this.getTarifaTSINCRONIZARptpTipoContrato() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TSINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TSINCRONIZAR_TARIFATSINCRONIZARPTPTIPOCONTRATO, TarifaConstants.SERV_ALIAS_TSINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TSINCRONIZAR_TARIFATSINCRONIZARPTPTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public PlanesOid getTarifaTSINCRONIZARptpevcPlan() {
        return this.tarifaTSINCRONIZARptpevcPlan;
    }
    
    public void setTarifaTSINCRONIZARptpevcPlan(PlanesOid tarifaTSINCRONIZARptpevcPlan) {
        this.tarifaTSINCRONIZARptpevcPlan = tarifaTSINCRONIZARptpevcPlan;
    }

    @JsonIgnore
    public Planes getTarifaTSINCRONIZARptpevcPlanInstance() {
        return this.tarifaTSINCRONIZARptpevcPlanInstance;
    }
    
    public void setTarifaTSINCRONIZARptpevcPlanInstance(Planes tarifaTSINCRONIZARptpevcPlan) {
        this.tarifaTSINCRONIZARptpevcPlanInstance = tarifaTSINCRONIZARptpevcPlan;
        this.tarifaTSINCRONIZARptpevcPlan = tarifaTSINCRONIZARptpevcPlan != null ? tarifaTSINCRONIZARptpevcPlan.getOid() : null;
    }

    private void checkTarifaTSINCRONIZARptpevcPlan() throws NotNullArgumentException {
        if(this.getTarifaTSINCRONIZARptpevcPlanInstance() == null || this.getTarifaTSINCRONIZARptpevcPlanInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TSINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TSINCRONIZAR_TARIFATSINCRONIZARPTPEVCPLAN, TarifaConstants.SERV_ALIAS_TSINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TSINCRONIZAR_TARIFATSINCRONIZARPTPEVCPLAN);
        }
    }

    @JsonIgnore
    public ProductosOid getTarifaTSINCRONIZARptpevcProducto() {
        return this.tarifaTSINCRONIZARptpevcProducto;
    }
    
    public void setTarifaTSINCRONIZARptpevcProducto(ProductosOid tarifaTSINCRONIZARptpevcProducto) {
        this.tarifaTSINCRONIZARptpevcProducto = tarifaTSINCRONIZARptpevcProducto;
    }

    @JsonIgnore
    public Productos getTarifaTSINCRONIZARptpevcProductoInstance() {
        return this.tarifaTSINCRONIZARptpevcProductoInstance;
    }
    
    public void setTarifaTSINCRONIZARptpevcProductoInstance(Productos tarifaTSINCRONIZARptpevcProducto) {
        this.tarifaTSINCRONIZARptpevcProductoInstance = tarifaTSINCRONIZARptpevcProducto;
        this.tarifaTSINCRONIZARptpevcProducto = tarifaTSINCRONIZARptpevcProducto != null ? tarifaTSINCRONIZARptpevcProducto.getOid() : null;
    }

    private void checkTarifaTSINCRONIZARptpevcProducto() throws NotNullArgumentException {
        if(this.getTarifaTSINCRONIZARptpevcProductoInstance() == null || this.getTarifaTSINCRONIZARptpevcProductoInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TSINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TSINCRONIZAR_TARIFATSINCRONIZARPTPEVCPRODUCTO, TarifaConstants.SERV_ALIAS_TSINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TSINCRONIZAR_TARIFATSINCRONIZARPTPEVCPRODUCTO);
        }
    }
}
