package com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;
import com.keralty.aeconfiguraciones.persistence.LogContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.LogContratoComboOid;

public class EliminarRequest {

    @JsonProperty("p_thislogcontratocombo_oid")
    private LogContratoComboOid logContratoComboeliminarpthisLogContratoCombo;
    
    @JsonIgnore
    private LogContratoCombo logContratoComboeliminarpthisLogContratoComboInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratoComboeliminarpthisLogContratoCombo();
    }

    @JsonIgnore
    public LogContratoComboOid getLogContratoComboeliminarpthisLogContratoCombo() {
        return this.logContratoComboeliminarpthisLogContratoCombo;
    }
    
    public void setLogContratoComboeliminarpthisLogContratoCombo(LogContratoComboOid logContratoComboeliminarpthisLogContratoCombo) {
        this.logContratoComboeliminarpthisLogContratoCombo = logContratoComboeliminarpthisLogContratoCombo;
    }

    @JsonIgnore
    public LogContratoCombo getLogContratoComboeliminarpthisLogContratoComboInstance() {
        return this.logContratoComboeliminarpthisLogContratoComboInstance;
    }
    
    public void setLogContratoComboeliminarpthisLogContratoComboInstance(LogContratoCombo logContratoComboeliminarpthisLogContratoCombo) {
        this.logContratoComboeliminarpthisLogContratoComboInstance = logContratoComboeliminarpthisLogContratoCombo;
        this.logContratoComboeliminarpthisLogContratoCombo = logContratoComboeliminarpthisLogContratoCombo != null ? logContratoComboeliminarpthisLogContratoCombo.getOid() : null;
    }

    private void checkLogContratoComboeliminarpthisLogContratoCombo() throws NotNullArgumentException {
        if(this.getLogContratoComboeliminarpthisLogContratoComboInstance() == null || this.getLogContratoComboeliminarpthisLogContratoComboInstance().isNull()) {
            throw new NotNullArgumentException(LogContratoComboConstants.SERV_ID_ELIMINAR, LogContratoComboConstants.CLASS_ID, LogContratoComboConstants.ARG_ID_ELIMINAR_LOGCONTRATOCOMBOELIMINARPTHISLOGCONTRATOCOMBO, LogContratoComboConstants.SERV_ALIAS_ELIMINAR, LogContratoComboConstants.CLASS_ALIAS, LogContratoComboConstants.ARG_ALIAS_ELIMINAR_LOGCONTRATOCOMBOELIMINARPTHISLOGCONTRATOCOMBO);
        }
    }
}
