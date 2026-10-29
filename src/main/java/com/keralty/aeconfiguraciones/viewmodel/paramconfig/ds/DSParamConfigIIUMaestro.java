package com.keralty.aeconfiguraciones.viewmodel.paramconfig.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;
public class DSParamConfigIIUMaestro {

    @JsonProperty("codigo")
    private String dSParamConfigIIUMaestroCodigo;

    @JsonProperty("descripcion")
    private String dSParamConfigIIUMaestroDescripcion;

    @JsonProperty("valor")
    private String dSParamConfigIIUMaestroValor;

    public DSParamConfigIIUMaestro() {
    
    }

    public DSParamConfigIIUMaestro(ParamConfig instance) {
        if (instance != null) {
            this.dSParamConfigIIUMaestroCodigo = instance.getParamConfigCodigo();
            this.dSParamConfigIIUMaestroDescripcion = instance.getParamConfigDescripcion();
            this.dSParamConfigIIUMaestroValor = instance.getParamConfigValor();
        }
    }

    @JsonIgnore
    public String getDSParamConfigIIUMaestroCodigo() {
        return this.dSParamConfigIIUMaestroCodigo;
    }
    
    public void setDSParamConfigIIUMaestroCodigo(String dSParamConfigIIUMaestroCodigo) {
        this.dSParamConfigIIUMaestroCodigo = dSParamConfigIIUMaestroCodigo;
    }

    @JsonIgnore
    public String getDSParamConfigIIUMaestroDescripcion() {
        return this.dSParamConfigIIUMaestroDescripcion;
    }
    
    public void setDSParamConfigIIUMaestroDescripcion(String dSParamConfigIIUMaestroDescripcion) {
        this.dSParamConfigIIUMaestroDescripcion = dSParamConfigIIUMaestroDescripcion;
    }

    @JsonIgnore
    public String getDSParamConfigIIUMaestroValor() {
        return this.dSParamConfigIIUMaestroValor;
    }
    
    public void setDSParamConfigIIUMaestroValor(String dSParamConfigIIUMaestroValor) {
        this.dSParamConfigIIUMaestroValor = dSParamConfigIIUMaestroValor;
    }
}
