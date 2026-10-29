package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class CrearRequest {

    @JsonProperty("p_agrtarifa_oid")
    private TarifaOid detalleTarifacrearpagrTarifa;
    
    @JsonIgnore
    private Tarifa detalleTarifacrearpagrTarifaInstance;

    @JsonProperty("p_atranyo")
    private Long detalleTarifacrearpatrAnyo;

    @JsonProperty("p_atrliminferioredad")
    private Long detalleTarifacrearpatrLimInferiorEdad;

    @JsonProperty("p_atrlimsuperioredad")
    private Long detalleTarifacrearpatrLimSuperiorEdad;

    @JsonProperty("p_atrvalor")
    private Double detalleTarifacrearpatrValor;

    public void checkArguments() throws NotNullArgumentException {
        checkDetalleTarifacrearpagrTarifa();
        checkDetalleTarifacrearpatrAnyo();
        checkDetalleTarifacrearpatrLimInferiorEdad();
        checkDetalleTarifacrearpatrLimSuperiorEdad();
        checkDetalleTarifacrearpatrValor();
    }

    @JsonIgnore
    public TarifaOid getDetalleTarifacrearpagrTarifa() {
        return this.detalleTarifacrearpagrTarifa;
    }
    
    public void setDetalleTarifacrearpagrTarifa(TarifaOid detalleTarifacrearpagrTarifa) {
        this.detalleTarifacrearpagrTarifa = detalleTarifacrearpagrTarifa;
    }

    @JsonIgnore
    public Tarifa getDetalleTarifacrearpagrTarifaInstance() {
        return this.detalleTarifacrearpagrTarifaInstance;
    }
    
    public void setDetalleTarifacrearpagrTarifaInstance(Tarifa detalleTarifacrearpagrTarifa) {
        this.detalleTarifacrearpagrTarifaInstance = detalleTarifacrearpagrTarifa;
        this.detalleTarifacrearpagrTarifa = detalleTarifacrearpagrTarifa != null ? detalleTarifacrearpagrTarifa.getOid() : null;
    }

    private void checkDetalleTarifacrearpagrTarifa() throws NotNullArgumentException {
        if(this.getDetalleTarifacrearpagrTarifaInstance() == null || this.getDetalleTarifacrearpagrTarifaInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_CREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_CREAR_DETALLETARIFACREARPAGRTARIFA, DetalleTarifaConstants.SERV_ALIAS_CREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_CREAR_DETALLETARIFACREARPAGRTARIFA);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifacrearpatrAnyo() {
        return this.detalleTarifacrearpatrAnyo;
    }
    
    public void setDetalleTarifacrearpatrAnyo(Long detalleTarifacrearpatrAnyo) {
        this.detalleTarifacrearpatrAnyo = detalleTarifacrearpatrAnyo;
    }

    private void checkDetalleTarifacrearpatrAnyo() throws NotNullArgumentException {
        if(this.getDetalleTarifacrearpatrAnyo() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_CREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_CREAR_DETALLETARIFACREARPATRANYO, DetalleTarifaConstants.SERV_ALIAS_CREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_CREAR_DETALLETARIFACREARPATRANYO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifacrearpatrLimInferiorEdad() {
        return this.detalleTarifacrearpatrLimInferiorEdad;
    }
    
    public void setDetalleTarifacrearpatrLimInferiorEdad(Long detalleTarifacrearpatrLimInferiorEdad) {
        this.detalleTarifacrearpatrLimInferiorEdad = detalleTarifacrearpatrLimInferiorEdad;
    }

    private void checkDetalleTarifacrearpatrLimInferiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifacrearpatrLimInferiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_CREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_CREAR_DETALLETARIFACREARPATRLIMINFERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_CREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_CREAR_DETALLETARIFACREARPATRLIMINFERIOREDAD);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifacrearpatrLimSuperiorEdad() {
        return this.detalleTarifacrearpatrLimSuperiorEdad;
    }
    
    public void setDetalleTarifacrearpatrLimSuperiorEdad(Long detalleTarifacrearpatrLimSuperiorEdad) {
        this.detalleTarifacrearpatrLimSuperiorEdad = detalleTarifacrearpatrLimSuperiorEdad;
    }

    private void checkDetalleTarifacrearpatrLimSuperiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifacrearpatrLimSuperiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_CREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_CREAR_DETALLETARIFACREARPATRLIMSUPERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_CREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_CREAR_DETALLETARIFACREARPATRLIMSUPERIOREDAD);
        }
    }

    @JsonIgnore
    public Double getDetalleTarifacrearpatrValor() {
        return this.detalleTarifacrearpatrValor;
    }
    
    public void setDetalleTarifacrearpatrValor(Double detalleTarifacrearpatrValor) {
        this.detalleTarifacrearpatrValor = detalleTarifacrearpatrValor;
    }

    private void checkDetalleTarifacrearpatrValor() throws NotNullArgumentException {
        if(this.getDetalleTarifacrearpatrValor() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_CREAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_CREAR_DETALLETARIFACREARPATRVALOR, DetalleTarifaConstants.SERV_ALIAS_CREAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_CREAR_DETALLETARIFACREARPATRVALOR);
        }
    }
}
