package com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
public class DSParamConfigPIU {

    @JsonProperty("codigo")
    private String dSParamConfigPIUCodigo;

    @JsonProperty("valor")
    private String dSParamConfigPIUValor;

    @JsonProperty("descripcion")
    private String dSParamConfigPIUDescripcion;

    public DSParamConfigPIU() {
    
    }

    public DSParamConfigPIU(ParamConfig instance) {
        if (instance != null) {
            this.dSParamConfigPIUCodigo = instance.getParamConfigCodigo();
            this.dSParamConfigPIUValor = instance.getParamConfigValor();
            this.dSParamConfigPIUDescripcion = instance.getParamConfigDescripcion();
        }
    }

    @JsonIgnore
    public String getDSParamConfigPIUCodigo() {
        return this.dSParamConfigPIUCodigo;
    }
    
    public void setDSParamConfigPIUCodigo(String dSParamConfigPIUCodigo) {
        this.dSParamConfigPIUCodigo = dSParamConfigPIUCodigo;
    }

    @JsonIgnore
    public String getDSParamConfigPIUValor() {
        return this.dSParamConfigPIUValor;
    }
    
    public void setDSParamConfigPIUValor(String dSParamConfigPIUValor) {
        this.dSParamConfigPIUValor = dSParamConfigPIUValor;
    }

    @JsonIgnore
    public String getDSParamConfigPIUDescripcion() {
        return this.dSParamConfigPIUDescripcion;
    }
    
    public void setDSParamConfigPIUDescripcion(String dSParamConfigPIUDescripcion) {
        this.dSParamConfigPIUDescripcion = dSParamConfigPIUDescripcion;
    }
}
