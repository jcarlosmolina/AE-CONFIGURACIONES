package com.keralty.aeconfiguraciones.viewmodel.valor.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Valor;
public class ICValor {

    @JsonProperty("valorposible")
    private String iCValorValorPosible;

    public ICValor() {
    
    }

    public ICValor(Valor instance) {
        if (instance != null) {
            this.iCValorValorPosible = instance.getValorValorPosible();
        }
    }

    @JsonIgnore
    public String getICValorValorPosible() {
        return this.iCValorValorPosible;
    }
    
    public void setICValorValorPosible(String iCValorValorPosible) {
        this.iCValorValorPosible = iCValorValorPosible;
    }
}
