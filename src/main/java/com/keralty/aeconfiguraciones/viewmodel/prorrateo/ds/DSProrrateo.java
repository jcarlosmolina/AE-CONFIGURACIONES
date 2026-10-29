package com.keralty.aeconfiguraciones.viewmodel.prorrateo.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
public class DSProrrateo {

    @JsonProperty("dia")
    private Long dSProrrateoDia;

    @JsonProperty("porcentaje")
    private Double dSProrrateoPorcentaje;

    public DSProrrateo() {
    
    }

    public DSProrrateo(Prorrateo instance) {
        if (instance != null) {
            this.dSProrrateoDia = instance.getProrrateoDia();
            this.dSProrrateoPorcentaje = instance.getProrrateoPorcentaje();
        }
    }

    @JsonIgnore
    public Long getDSProrrateoDia() {
        return this.dSProrrateoDia;
    }
    
    public void setDSProrrateoDia(Long dSProrrateoDia) {
        this.dSProrrateoDia = dSProrrateoDia;
    }

    @JsonIgnore
    public Double getDSProrrateoPorcentaje() {
        return this.dSProrrateoPorcentaje;
    }
    
    public void setDSProrrateoPorcentaje(Double dSProrrateoPorcentaje) {
        this.dSProrrateoPorcentaje = dSProrrateoPorcentaje;
    }
}
