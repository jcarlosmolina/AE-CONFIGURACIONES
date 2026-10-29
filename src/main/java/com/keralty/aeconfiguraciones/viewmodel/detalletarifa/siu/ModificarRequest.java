package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.oid.DetalleTarifaOid;

public class ModificarRequest {

    @JsonProperty("p_thisdetalletarifa_oid")
    private DetalleTarifaOid detalleTarifamodificarpthisDetalleTarifa;
    
    @JsonIgnore
    private DetalleTarifa detalleTarifamodificarpthisDetalleTarifaInstance;

    @JsonProperty("p_anyo")
    private Long detalleTarifamodificarpAnyo;

    @JsonProperty("p_liminferioredad")
    private Long detalleTarifamodificarpLimInferiorEdad;

    @JsonProperty("p_limsuperioredad")
    private Long detalleTarifamodificarpLimSuperiorEdad;

    @JsonProperty("p_valor")
    private Double detalleTarifamodificarpValor;

    public void checkArguments() throws NotNullArgumentException {
        checkDetalleTarifamodificarpthisDetalleTarifa();
        checkDetalleTarifamodificarpAnyo();
        checkDetalleTarifamodificarpLimInferiorEdad();
        checkDetalleTarifamodificarpLimSuperiorEdad();
        checkDetalleTarifamodificarpValor();
    }

    @JsonIgnore
    public DetalleTarifaOid getDetalleTarifamodificarpthisDetalleTarifa() {
        return this.detalleTarifamodificarpthisDetalleTarifa;
    }
    
    public void setDetalleTarifamodificarpthisDetalleTarifa(DetalleTarifaOid detalleTarifamodificarpthisDetalleTarifa) {
        this.detalleTarifamodificarpthisDetalleTarifa = detalleTarifamodificarpthisDetalleTarifa;
    }

    @JsonIgnore
    public DetalleTarifa getDetalleTarifamodificarpthisDetalleTarifaInstance() {
        return this.detalleTarifamodificarpthisDetalleTarifaInstance;
    }
    
    public void setDetalleTarifamodificarpthisDetalleTarifaInstance(DetalleTarifa detalleTarifamodificarpthisDetalleTarifa) {
        this.detalleTarifamodificarpthisDetalleTarifaInstance = detalleTarifamodificarpthisDetalleTarifa;
        this.detalleTarifamodificarpthisDetalleTarifa = detalleTarifamodificarpthisDetalleTarifa != null ? detalleTarifamodificarpthisDetalleTarifa.getOid() : null;
    }

    private void checkDetalleTarifamodificarpthisDetalleTarifa() throws NotNullArgumentException {
        if(this.getDetalleTarifamodificarpthisDetalleTarifaInstance() == null || this.getDetalleTarifamodificarpthisDetalleTarifaInstance().isNull()) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_MODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_MODIFICAR_DETALLETARIFAMODIFICARPTHISDETALLETARIFA, DetalleTarifaConstants.SERV_ALIAS_MODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_MODIFICAR_DETALLETARIFAMODIFICARPTHISDETALLETARIFA);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifamodificarpAnyo() {
        return this.detalleTarifamodificarpAnyo;
    }
    
    public void setDetalleTarifamodificarpAnyo(Long detalleTarifamodificarpAnyo) {
        this.detalleTarifamodificarpAnyo = detalleTarifamodificarpAnyo;
    }

    private void checkDetalleTarifamodificarpAnyo() throws NotNullArgumentException {
        if(this.getDetalleTarifamodificarpAnyo() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_MODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_MODIFICAR_DETALLETARIFAMODIFICARPANYO, DetalleTarifaConstants.SERV_ALIAS_MODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_MODIFICAR_DETALLETARIFAMODIFICARPANYO);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifamodificarpLimInferiorEdad() {
        return this.detalleTarifamodificarpLimInferiorEdad;
    }
    
    public void setDetalleTarifamodificarpLimInferiorEdad(Long detalleTarifamodificarpLimInferiorEdad) {
        this.detalleTarifamodificarpLimInferiorEdad = detalleTarifamodificarpLimInferiorEdad;
    }

    private void checkDetalleTarifamodificarpLimInferiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifamodificarpLimInferiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_MODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_MODIFICAR_DETALLETARIFAMODIFICARPLIMINFERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_MODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_MODIFICAR_DETALLETARIFAMODIFICARPLIMINFERIOREDAD);
        }
    }

    @JsonIgnore
    public Long getDetalleTarifamodificarpLimSuperiorEdad() {
        return this.detalleTarifamodificarpLimSuperiorEdad;
    }
    
    public void setDetalleTarifamodificarpLimSuperiorEdad(Long detalleTarifamodificarpLimSuperiorEdad) {
        this.detalleTarifamodificarpLimSuperiorEdad = detalleTarifamodificarpLimSuperiorEdad;
    }

    private void checkDetalleTarifamodificarpLimSuperiorEdad() throws NotNullArgumentException {
        if(this.getDetalleTarifamodificarpLimSuperiorEdad() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_MODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_MODIFICAR_DETALLETARIFAMODIFICARPLIMSUPERIOREDAD, DetalleTarifaConstants.SERV_ALIAS_MODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_MODIFICAR_DETALLETARIFAMODIFICARPLIMSUPERIOREDAD);
        }
    }

    @JsonIgnore
    public Double getDetalleTarifamodificarpValor() {
        return this.detalleTarifamodificarpValor;
    }
    
    public void setDetalleTarifamodificarpValor(Double detalleTarifamodificarpValor) {
        this.detalleTarifamodificarpValor = detalleTarifamodificarpValor;
    }

    private void checkDetalleTarifamodificarpValor() throws NotNullArgumentException {
        if(this.getDetalleTarifamodificarpValor() == null) {
            throw new NotNullArgumentException(DetalleTarifaConstants.SERV_ID_MODIFICAR, DetalleTarifaConstants.CLASS_ID, DetalleTarifaConstants.ARG_ID_MODIFICAR_DETALLETARIFAMODIFICARPVALOR, DetalleTarifaConstants.SERV_ALIAS_MODIFICAR, DetalleTarifaConstants.CLASS_ALIAS, DetalleTarifaConstants.ARG_ALIAS_MODIFICAR_DETALLETARIFAMODIFICARPVALOR);
        }
    }
}
