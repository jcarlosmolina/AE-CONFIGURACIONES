package com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContratoComboConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;

public class ModificarRequest {

    @JsonProperty("p_thiscontratocombo_oid")
    private ContratoComboOid contratoCombomodificarpthisContratoCombo;
    
    @JsonIgnore
    private ContratoCombo contratoCombomodificarpthisContratoComboInstance;

    @JsonProperty("p_contratointegral")
    private String contratoCombomodificarpContratoIntegral;

    @JsonProperty("p_contratoodontologico")
    private String contratoCombomodificarpContratoOdontologico;

    public void checkArguments() throws NotNullArgumentException {
        checkContratoCombomodificarpthisContratoCombo();
        checkContratoCombomodificarpContratoIntegral();
        checkContratoCombomodificarpContratoOdontologico();
    }

    @JsonIgnore
    public ContratoComboOid getContratoCombomodificarpthisContratoCombo() {
        return this.contratoCombomodificarpthisContratoCombo;
    }
    
    public void setContratoCombomodificarpthisContratoCombo(ContratoComboOid contratoCombomodificarpthisContratoCombo) {
        this.contratoCombomodificarpthisContratoCombo = contratoCombomodificarpthisContratoCombo;
    }

    @JsonIgnore
    public ContratoCombo getContratoCombomodificarpthisContratoComboInstance() {
        return this.contratoCombomodificarpthisContratoComboInstance;
    }
    
    public void setContratoCombomodificarpthisContratoComboInstance(ContratoCombo contratoCombomodificarpthisContratoCombo) {
        this.contratoCombomodificarpthisContratoComboInstance = contratoCombomodificarpthisContratoCombo;
        this.contratoCombomodificarpthisContratoCombo = contratoCombomodificarpthisContratoCombo != null ? contratoCombomodificarpthisContratoCombo.getOid() : null;
    }

    private void checkContratoCombomodificarpthisContratoCombo() throws NotNullArgumentException {
        if(this.getContratoCombomodificarpthisContratoComboInstance() == null || this.getContratoCombomodificarpthisContratoComboInstance().isNull()) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_MODIFICAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_MODIFICAR_CONTRATOCOMBOMODIFICARPTHISCONTRATOCOMBO, ContratoComboConstants.SERV_ALIAS_MODIFICAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_MODIFICAR_CONTRATOCOMBOMODIFICARPTHISCONTRATOCOMBO);
        }
    }

    @JsonIgnore
    public String getContratoCombomodificarpContratoIntegral() {
        return this.contratoCombomodificarpContratoIntegral;
    }
    
    public void setContratoCombomodificarpContratoIntegral(String contratoCombomodificarpContratoIntegral) {
        this.contratoCombomodificarpContratoIntegral = contratoCombomodificarpContratoIntegral;
    }

    private void checkContratoCombomodificarpContratoIntegral() throws NotNullArgumentException {
        if(this.getContratoCombomodificarpContratoIntegral() == null) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_MODIFICAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_MODIFICAR_CONTRATOCOMBOMODIFICARPCONTRATOINTEGRAL, ContratoComboConstants.SERV_ALIAS_MODIFICAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_MODIFICAR_CONTRATOCOMBOMODIFICARPCONTRATOINTEGRAL);
        }
    }

    @JsonIgnore
    public String getContratoCombomodificarpContratoOdontologico() {
        return this.contratoCombomodificarpContratoOdontologico;
    }
    
    public void setContratoCombomodificarpContratoOdontologico(String contratoCombomodificarpContratoOdontologico) {
        this.contratoCombomodificarpContratoOdontologico = contratoCombomodificarpContratoOdontologico;
    }

    private void checkContratoCombomodificarpContratoOdontologico() throws NotNullArgumentException {
        if(this.getContratoCombomodificarpContratoOdontologico() == null) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_MODIFICAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_MODIFICAR_CONTRATOCOMBOMODIFICARPCONTRATOODONTOLOGICO, ContratoComboConstants.SERV_ALIAS_MODIFICAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_MODIFICAR_CONTRATOCOMBOMODIFICARPCONTRATOODONTOLOGICO);
        }
    }
}
