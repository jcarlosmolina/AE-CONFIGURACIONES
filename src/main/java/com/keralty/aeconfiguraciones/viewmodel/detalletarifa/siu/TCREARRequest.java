package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class TCREARRequest {

    @JsonProperty("ptarifa_oid")
    private TarifaOid detalleTarifaTCREARpTarifa;
    
    @JsonIgnore
    private Tarifa detalleTarifaTCREARpTarifaInstance;

    @JsonProperty("pproducto_oid")
    private ProductosOid detalleTarifaTCREARpProducto;
    
    @JsonIgnore
    private Productos detalleTarifaTCREARpProductoInstance;

    @JsonProperty("pplan_oid")
    private PlanesOid detalleTarifaTCREARpPlan;
    
    @JsonIgnore
    private Planes detalleTarifaTCREARpPlanInstance;

    @JsonProperty("ptipocontrato")
    private Long detalleTarifaTCREARpTipoContrato;

    @JsonProperty("panyo")
    private Long detalleTarifaTCREARpAnyo;

    @JsonProperty("pliminferioredad")
    private Long detalleTarifaTCREARpLimInferiorEdad;

    @JsonProperty("plimsuperioredad")
    private Long detalleTarifaTCREARpLimSuperiorEdad;

    @JsonProperty("pvalor")
    private Double detalleTarifaTCREARpValor;

    public void checkArguments() throws NotNullArgumentException {
        checkDetalleTarifaTCREARpTarifa();
        checkDetalleTarifaTCREARpProducto();
        checkDetalleTarifaTCREARpPlan();
        checkDetalleTarifaTCREARpTipoContrato();
        checkDetalleTarifaTCREARpAnyo();
        checkDetalleTarifaTCREARpLimInferiorEdad();
        checkDetalleTarifaTCREARpLimSuperiorEdad();
        checkDetalleTarifaTCREARpValor();
    }

    @JsonIgnore
    public TarifaOid getDetalleTarifaTCREARpTarifa() {
        return this.detalleTarifaTCREARpTarifa;
    }
    
    public void setDetalleTarifaTCREARpTarifa(TarifaOid detalleTarifaTCREARpTarifa) {
        this.detalleTarifaTCREARpTarifa = detalleTarifaTCREARpTarifa;
    }

    @JsonIgnore
    public Tarifa getDetalleTarifaTCREARpTarifaInstance() {
        return this.detalleTarifaTCREARpTarifaInstance;
    }
    
    public void setDetalleTarifaTCREARpTarifaInstance(Tarifa detalleTarifaTCREARpTarifa) {
        this.detalleTarifaTCREARpTarifaInstance = detalleTarifaTCREARpTarifa;
        this.detalleTarifaTCREARpTarifa = detalleTarifaTCREARpTarifa != null ? detalleTarifaTCREARpTarifa.getOid() : null;
    }

    private void checkDetalleTarifaTCREARpTarifa() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpTarifaInstance() == null || this.getDetalleTarifaTCREARpTarifaInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPTARIFA, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPTARIFA);
        }
    }

    @JsonIgnore
    public ProductosOid getDetalleTarifaTCREARpProducto() {
        return this.detalleTarifaTCREARpProducto;
    }
    
    public void setDetalleTarifaTCREARpProducto(ProductosOid detalleTarifaTCREARpProducto) {
        this.detalleTarifaTCREARpProducto = detalleTarifaTCREARpProducto;
    }

    @JsonIgnore
    public Productos getDetalleTarifaTCREARpProductoInstance() {
        return this.detalleTarifaTCREARpProductoInstance;
    }
    
    public void setDetalleTarifaTCREARpProductoInstance(Productos detalleTarifaTCREARpProducto) {
        this.detalleTarifaTCREARpProductoInstance = detalleTarifaTCREARpProducto;
        this.detalleTarifaTCREARpProducto = detalleTarifaTCREARpProducto != null ? detalleTarifaTCREARpProducto.getOid() : null;
    }

    private void checkDetalleTarifaTCREARpProducto() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpProductoInstance() == null || this.getDetalleTarifaTCREARpProductoInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPPRODUCTO, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPPRODUCTO);
        }
    }

    @JsonIgnore
    public PlanesOid getDetalleTarifaTCREARpPlan() {
        return this.detalleTarifaTCREARpPlan;
    }
    
    public void setDetalleTarifaTCREARpPlan(PlanesOid detalleTarifaTCREARpPlan) {
        this.detalleTarifaTCREARpPlan = detalleTarifaTCREARpPlan;
    }

    @JsonIgnore
    public Planes getDetalleTarifaTCREARpPlanInstance() {
        return this.detalleTarifaTCREARpPlanInstance;
    }
    
    public void setDetalleTarifaTCREARpPlanInstance(Planes detalleTarifaTCREARpPlan) {
        this.detalleTarifaTCREARpPlanInstance = detalleTarifaTCREARpPlan;
        this.detalleTarifaTCREARpPlan = detalleTarifaTCREARpPlan != null ? detalleTarifaTCREARpPlan.getOid() : null;
    }

    private void checkDetalleTarifaTCREARpPlan() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpPlanInstance() == null || this.getDetalleTarifaTCREARpPlanInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPPLAN, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPPLAN);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTCREARpTipoContrato() {
        return this.detalleTarifaTCREARpTipoContrato;
    }
    
    public void setDetalleTarifaTCREARpTipoContrato(Long detalleTarifaTCREARpTipoContrato) {
        this.detalleTarifaTCREARpTipoContrato = detalleTarifaTCREARpTipoContrato;
    }

    private void checkDetalleTarifaTCREARpTipoContrato() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpTipoContrato() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPTIPOCONTRATO, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTCREARpAnyo() {
        return this.detalleTarifaTCREARpAnyo;
    }
    
    public void setDetalleTarifaTCREARpAnyo(Long detalleTarifaTCREARpAnyo) {
        this.detalleTarifaTCREARpAnyo = detalleTarifaTCREARpAnyo;
    }

    private void checkDetalleTarifaTCREARpAnyo() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpAnyo() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPANYO, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPANYO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTCREARpLimInferiorEdad() {
        return this.detalleTarifaTCREARpLimInferiorEdad;
    }
    
    public void setDetalleTarifaTCREARpLimInferiorEdad(Long detalleTarifaTCREARpLimInferiorEdad) {
        this.detalleTarifaTCREARpLimInferiorEdad = detalleTarifaTCREARpLimInferiorEdad;
    }

    private void checkDetalleTarifaTCREARpLimInferiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpLimInferiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPLIMINFERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPLIMINFERIOREDAD);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTCREARpLimSuperiorEdad() {
        return this.detalleTarifaTCREARpLimSuperiorEdad;
    }
    
    public void setDetalleTarifaTCREARpLimSuperiorEdad(Long detalleTarifaTCREARpLimSuperiorEdad) {
        this.detalleTarifaTCREARpLimSuperiorEdad = detalleTarifaTCREARpLimSuperiorEdad;
    }

    private void checkDetalleTarifaTCREARpLimSuperiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpLimSuperiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPLIMSUPERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPLIMSUPERIOREDAD);
        }
    }

    @JsonIgnore
    public Double getDetalleTarifaTCREARpValor() {
        return this.detalleTarifaTCREARpValor;
    }
    
    public void setDetalleTarifaTCREARpValor(Double detalleTarifaTCREARpValor) {
        this.detalleTarifaTCREARpValor = detalleTarifaTCREARpValor;
    }

    private void checkDetalleTarifaTCREARpValor() throws NotNullArgumentException {
        if(this.getDetalleTarifaTCREARpValor() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TCREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TCREAR_DETALLETARIFATCREARPVALOR, DetalleTarifaConstants.SERV_ALIAS_TCREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TCREAR_DETALLETARIFATCREARPVALOR);
        }
    }
}
