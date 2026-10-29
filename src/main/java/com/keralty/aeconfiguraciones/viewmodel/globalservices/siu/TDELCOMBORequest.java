package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;

public class TDELCOMBORequest {

    @JsonProperty("pt_p_thiscontratocombo_oid")
    private ContratoComboOid tDELCOMBOptpthisContratoCombo;
    
    @JsonIgnore
    private ContratoCombo tDELCOMBOptpthisContratoComboInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTDELCOMBOptpthisContratoCombo();
    }

    @JsonIgnore
    public ContratoComboOid getTDELCOMBOptpthisContratoCombo() {
        return this.tDELCOMBOptpthisContratoCombo;
    }
    
    public void setTDELCOMBOptpthisContratoCombo(ContratoComboOid tDELCOMBOptpthisContratoCombo) {
        this.tDELCOMBOptpthisContratoCombo = tDELCOMBOptpthisContratoCombo;
    }

    @JsonIgnore
    public ContratoCombo getTDELCOMBOptpthisContratoComboInstance() {
        return this.tDELCOMBOptpthisContratoComboInstance;
    }
    
    public void setTDELCOMBOptpthisContratoComboInstance(ContratoCombo tDELCOMBOptpthisContratoCombo) {
        this.tDELCOMBOptpthisContratoComboInstance = tDELCOMBOptpthisContratoCombo;
        this.tDELCOMBOptpthisContratoCombo = tDELCOMBOptpthisContratoCombo != null ? tDELCOMBOptpthisContratoCombo.getOid() : null;
    }

    private void checkTDELCOMBOptpthisContratoCombo() throws NotNullArgumentException {
        if(this.getTDELCOMBOptpthisContratoComboInstance() == null || this.getTDELCOMBOptpthisContratoComboInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TDELCOMBO, GlobalServiceConstants.GS_ARG_ID_TDELCOMBO_TDELCOMBOPTPTHISCONTRATOCOMBO, GlobalServiceConstants.G_SERV_ALIAS_TDELCOMBO, GlobalServiceConstants.GS_ARG_ALIAS_TDELCOMBO_TDELCOMBOPTPTHISCONTRATOCOMBO);
        }
    }
}
