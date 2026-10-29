package com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContratoComboConstants;

public class CrearRequest {

    @JsonProperty("p_atrcontratointegral")
    private String contratoCombocrearpatrContratoIntegral;

    @JsonProperty("p_atrcontratoodontologico")
    private String contratoCombocrearpatrContratoOdontologico;

    public void checkArguments() throws NotNullArgumentException {
        checkContratoCombocrearpatrContratoIntegral();
        checkContratoCombocrearpatrContratoOdontologico();
    }

    @JsonIgnore
    public String getContratoCombocrearpatrContratoIntegral() {
        return this.contratoCombocrearpatrContratoIntegral;
    }
    
    public void setContratoCombocrearpatrContratoIntegral(String contratoCombocrearpatrContratoIntegral) {
        this.contratoCombocrearpatrContratoIntegral = contratoCombocrearpatrContratoIntegral;
    }

    private void checkContratoCombocrearpatrContratoIntegral() throws NotNullArgumentException {
        if(this.getContratoCombocrearpatrContratoIntegral() == null) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_CREAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_CREAR_CONTRATOCOMBOCREARPATRCONTRATOINTEGRAL, ContratoComboConstants.SERV_ALIAS_CREAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_CREAR_CONTRATOCOMBOCREARPATRCONTRATOINTEGRAL);
        }
    }

    @JsonIgnore
    public String getContratoCombocrearpatrContratoOdontologico() {
        return this.contratoCombocrearpatrContratoOdontologico;
    }
    
    public void setContratoCombocrearpatrContratoOdontologico(String contratoCombocrearpatrContratoOdontologico) {
        this.contratoCombocrearpatrContratoOdontologico = contratoCombocrearpatrContratoOdontologico;
    }

    private void checkContratoCombocrearpatrContratoOdontologico() throws NotNullArgumentException {
        if(this.getContratoCombocrearpatrContratoOdontologico() == null) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_CREAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_CREAR_CONTRATOCOMBOCREARPATRCONTRATOODONTOLOGICO, ContratoComboConstants.SERV_ALIAS_CREAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_CREAR_CONTRATOCOMBOCREARPATRCONTRATOODONTOLOGICO);
        }
    }
}
