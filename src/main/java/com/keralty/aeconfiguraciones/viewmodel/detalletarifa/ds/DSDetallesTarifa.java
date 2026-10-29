package com.keralty.aeconfiguraciones.viewmodel.detalletarifa.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
public class DSDetallesTarifa {

    @JsonProperty("anyo")
    private Long dSDetallesTarifaAnyo;

    @JsonProperty("liminferioredad")
    private Long dSDetallesTarifaLimInferiorEdad;

    @JsonProperty("limsuperioredad")
    private Long dSDetallesTarifaLimSuperiorEdad;

    @JsonProperty("valor")
    private Double dSDetallesTarifaValor;

    public DSDetallesTarifa() {
    
    }

    public DSDetallesTarifa(DetalleTarifa instance) {
        if (instance != null) {
            this.dSDetallesTarifaAnyo = instance.getDetalleTarifaAnyo();
            this.dSDetallesTarifaLimInferiorEdad = instance.getDetalleTarifaLimInferiorEdad();
            this.dSDetallesTarifaLimSuperiorEdad = instance.getDetalleTarifaLimSuperiorEdad();
            this.dSDetallesTarifaValor = instance.getDetalleTarifaValor();
        }
    }

    @JsonIgnore
    public Long getDSDetallesTarifaAnyo() {
        return this.dSDetallesTarifaAnyo;
    }
    
    public void setDSDetallesTarifaAnyo(Long dSDetallesTarifaAnyo) {
        this.dSDetallesTarifaAnyo = dSDetallesTarifaAnyo;
    }

    @JsonIgnore
    public Long getDSDetallesTarifaLimInferiorEdad() {
        return this.dSDetallesTarifaLimInferiorEdad;
    }
    
    public void setDSDetallesTarifaLimInferiorEdad(Long dSDetallesTarifaLimInferiorEdad) {
        this.dSDetallesTarifaLimInferiorEdad = dSDetallesTarifaLimInferiorEdad;
    }

    @JsonIgnore
    public Long getDSDetallesTarifaLimSuperiorEdad() {
        return this.dSDetallesTarifaLimSuperiorEdad;
    }
    
    public void setDSDetallesTarifaLimSuperiorEdad(Long dSDetallesTarifaLimSuperiorEdad) {
        this.dSDetallesTarifaLimSuperiorEdad = dSDetallesTarifaLimSuperiorEdad;
    }

    @JsonIgnore
    public Double getDSDetallesTarifaValor() {
        return this.dSDetallesTarifaValor;
    }
    
    public void setDSDetallesTarifaValor(Double dSDetallesTarifaValor) {
        this.dSDetallesTarifaValor = dSDetallesTarifaValor;
    }
}
