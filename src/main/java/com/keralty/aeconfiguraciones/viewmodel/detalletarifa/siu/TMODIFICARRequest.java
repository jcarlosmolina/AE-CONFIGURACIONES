package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.oid.DetalleTarifaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class TMODIFICARRequest {

    @JsonProperty("p_thisdetalletarifa_oid")
    private DetalleTarifaOid detalleTarifaTMODIFICARpthisDetalleTarifa;
    
    @JsonIgnore
    private DetalleTarifa detalleTarifaTMODIFICARpthisDetalleTarifaInstance;

    @JsonProperty("pproducto_oid")
    private ProductosOid detalleTarifaTMODIFICARpProducto;
    
    @JsonIgnore
    private Productos detalleTarifaTMODIFICARpProductoInstance;

    @JsonProperty("pplan_oid")
    private PlanesOid detalleTarifaTMODIFICARpPlan;
    
    @JsonIgnore
    private Planes detalleTarifaTMODIFICARpPlanInstance;

    @JsonProperty("ptipocontrato")
    private Long detalleTarifaTMODIFICARpTipoContrato;

    @JsonProperty("panyo")
    private Long detalleTarifaTMODIFICARpAnyo;

    @JsonProperty("pliminferioredad")
    private Long detalleTarifaTMODIFICARpLimInferiorEdad;

    @JsonProperty("plimsuperioredad")
    private Long detalleTarifaTMODIFICARpLimSuperiorEdad;

    @JsonProperty("pvalor")
    private Double detalleTarifaTMODIFICARpValor;

    public void checkArguments() throws NotNullArgumentException {
        checkDetalleTarifaTMODIFICARpthisDetalleTarifa();
        checkDetalleTarifaTMODIFICARpProducto();
        checkDetalleTarifaTMODIFICARpPlan();
        checkDetalleTarifaTMODIFICARpTipoContrato();
        checkDetalleTarifaTMODIFICARpAnyo();
        checkDetalleTarifaTMODIFICARpLimInferiorEdad();
        checkDetalleTarifaTMODIFICARpLimSuperiorEdad();
        checkDetalleTarifaTMODIFICARpValor();
    }

    @JsonIgnore
    public DetalleTarifaOid getDetalleTarifaTMODIFICARpthisDetalleTarifa() {
        return this.detalleTarifaTMODIFICARpthisDetalleTarifa;
    }
    
    public void setDetalleTarifaTMODIFICARpthisDetalleTarifa(DetalleTarifaOid detalleTarifaTMODIFICARpthisDetalleTarifa) {
        this.detalleTarifaTMODIFICARpthisDetalleTarifa = detalleTarifaTMODIFICARpthisDetalleTarifa;
    }

    @JsonIgnore
    public DetalleTarifa getDetalleTarifaTMODIFICARpthisDetalleTarifaInstance() {
        return this.detalleTarifaTMODIFICARpthisDetalleTarifaInstance;
    }
    
    public void setDetalleTarifaTMODIFICARpthisDetalleTarifaInstance(DetalleTarifa detalleTarifaTMODIFICARpthisDetalleTarifa) {
        this.detalleTarifaTMODIFICARpthisDetalleTarifaInstance = detalleTarifaTMODIFICARpthisDetalleTarifa;
        this.detalleTarifaTMODIFICARpthisDetalleTarifa = detalleTarifaTMODIFICARpthisDetalleTarifa != null ? detalleTarifaTMODIFICARpthisDetalleTarifa.getOid() : null;
    }

    private void checkDetalleTarifaTMODIFICARpthisDetalleTarifa() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpthisDetalleTarifaInstance() == null || this.getDetalleTarifaTMODIFICARpthisDetalleTarifaInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPTHISDETALLETARIFA, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPTHISDETALLETARIFA);
        }
    }

    @JsonIgnore
    public ProductosOid getDetalleTarifaTMODIFICARpProducto() {
        return this.detalleTarifaTMODIFICARpProducto;
    }
    
    public void setDetalleTarifaTMODIFICARpProducto(ProductosOid detalleTarifaTMODIFICARpProducto) {
        this.detalleTarifaTMODIFICARpProducto = detalleTarifaTMODIFICARpProducto;
    }

    @JsonIgnore
    public Productos getDetalleTarifaTMODIFICARpProductoInstance() {
        return this.detalleTarifaTMODIFICARpProductoInstance;
    }
    
    public void setDetalleTarifaTMODIFICARpProductoInstance(Productos detalleTarifaTMODIFICARpProducto) {
        this.detalleTarifaTMODIFICARpProductoInstance = detalleTarifaTMODIFICARpProducto;
        this.detalleTarifaTMODIFICARpProducto = detalleTarifaTMODIFICARpProducto != null ? detalleTarifaTMODIFICARpProducto.getOid() : null;
    }

    private void checkDetalleTarifaTMODIFICARpProducto() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpProductoInstance() == null || this.getDetalleTarifaTMODIFICARpProductoInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPPRODUCTO, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPPRODUCTO);
        }
    }

    @JsonIgnore
    public PlanesOid getDetalleTarifaTMODIFICARpPlan() {
        return this.detalleTarifaTMODIFICARpPlan;
    }
    
    public void setDetalleTarifaTMODIFICARpPlan(PlanesOid detalleTarifaTMODIFICARpPlan) {
        this.detalleTarifaTMODIFICARpPlan = detalleTarifaTMODIFICARpPlan;
    }

    @JsonIgnore
    public Planes getDetalleTarifaTMODIFICARpPlanInstance() {
        return this.detalleTarifaTMODIFICARpPlanInstance;
    }
    
    public void setDetalleTarifaTMODIFICARpPlanInstance(Planes detalleTarifaTMODIFICARpPlan) {
        this.detalleTarifaTMODIFICARpPlanInstance = detalleTarifaTMODIFICARpPlan;
        this.detalleTarifaTMODIFICARpPlan = detalleTarifaTMODIFICARpPlan != null ? detalleTarifaTMODIFICARpPlan.getOid() : null;
    }

    private void checkDetalleTarifaTMODIFICARpPlan() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpPlanInstance() == null || this.getDetalleTarifaTMODIFICARpPlanInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPPLAN, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPPLAN);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTMODIFICARpTipoContrato() {
        return this.detalleTarifaTMODIFICARpTipoContrato;
    }
    
    public void setDetalleTarifaTMODIFICARpTipoContrato(Long detalleTarifaTMODIFICARpTipoContrato) {
        this.detalleTarifaTMODIFICARpTipoContrato = detalleTarifaTMODIFICARpTipoContrato;
    }

    private void checkDetalleTarifaTMODIFICARpTipoContrato() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpTipoContrato() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPTIPOCONTRATO, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTMODIFICARpAnyo() {
        return this.detalleTarifaTMODIFICARpAnyo;
    }
    
    public void setDetalleTarifaTMODIFICARpAnyo(Long detalleTarifaTMODIFICARpAnyo) {
        this.detalleTarifaTMODIFICARpAnyo = detalleTarifaTMODIFICARpAnyo;
    }

    private void checkDetalleTarifaTMODIFICARpAnyo() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpAnyo() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPANYO, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPANYO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTMODIFICARpLimInferiorEdad() {
        return this.detalleTarifaTMODIFICARpLimInferiorEdad;
    }
    
    public void setDetalleTarifaTMODIFICARpLimInferiorEdad(Long detalleTarifaTMODIFICARpLimInferiorEdad) {
        this.detalleTarifaTMODIFICARpLimInferiorEdad = detalleTarifaTMODIFICARpLimInferiorEdad;
    }

    private void checkDetalleTarifaTMODIFICARpLimInferiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpLimInferiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPLIMINFERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPLIMINFERIOREDAD);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifaTMODIFICARpLimSuperiorEdad() {
        return this.detalleTarifaTMODIFICARpLimSuperiorEdad;
    }
    
    public void setDetalleTarifaTMODIFICARpLimSuperiorEdad(Long detalleTarifaTMODIFICARpLimSuperiorEdad) {
        this.detalleTarifaTMODIFICARpLimSuperiorEdad = detalleTarifaTMODIFICARpLimSuperiorEdad;
    }

    private void checkDetalleTarifaTMODIFICARpLimSuperiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpLimSuperiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPLIMSUPERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPLIMSUPERIOREDAD);
        }
    }

    @JsonIgnore
    public Double getDetalleTarifaTMODIFICARpValor() {
        return this.detalleTarifaTMODIFICARpValor;
    }
    
    public void setDetalleTarifaTMODIFICARpValor(Double detalleTarifaTMODIFICARpValor) {
        this.detalleTarifaTMODIFICARpValor = detalleTarifaTMODIFICARpValor;
    }

    private void checkDetalleTarifaTMODIFICARpValor() throws NotNullArgumentException {
        if(this.getDetalleTarifaTMODIFICARpValor() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_TMODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_TMODIFICAR_DETALLETARIFATMODIFICARPVALOR, DetalleTarifaConstants.SERV_ALIAS_TMODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_TMODIFICAR_DETALLETARIFATMODIFICARPVALOR);
        }
    }
}
