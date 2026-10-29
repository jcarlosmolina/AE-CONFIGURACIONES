package com.keralty.aeconfiguraciones.viewmodel.contratocombo.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
public class DSContratoCombo {

    @JsonProperty("contratointegral")
    private String dSContratoComboContratoIntegral;

    @JsonProperty("contratoodontologico")
    private String dSContratoComboContratoOdontologico;

    public DSContratoCombo() {
    
    }

    public DSContratoCombo(ContratoCombo instance) {
        if (instance != null) {
            this.dSContratoComboContratoIntegral = instance.getContratoComboContratoIntegral();
            this.dSContratoComboContratoOdontologico = instance.getContratoComboContratoOdontologico();
        }
    }

    @JsonIgnore
    public String getDSContratoComboContratoIntegral() {
        return this.dSContratoComboContratoIntegral;
    }
    
    public void setDSContratoComboContratoIntegral(String dSContratoComboContratoIntegral) {
        this.dSContratoComboContratoIntegral = dSContratoComboContratoIntegral;
    }

    @JsonIgnore
    public String getDSContratoComboContratoOdontologico() {
        return this.dSContratoComboContratoOdontologico;
    }
    
    public void setDSContratoComboContratoOdontologico(String dSContratoComboContratoOdontologico) {
        this.dSContratoComboContratoOdontologico = dSContratoComboContratoOdontologico;
    }
}
