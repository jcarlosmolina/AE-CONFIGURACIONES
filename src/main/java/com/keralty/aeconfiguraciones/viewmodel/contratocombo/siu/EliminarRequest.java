package com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContratoComboConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;

public class EliminarRequest {

    @JsonProperty("p_thiscontratocombo_oid")
    private ContratoComboOid contratoComboeliminarpthisContratoCombo;
    
    @JsonIgnore
    private ContratoCombo contratoComboeliminarpthisContratoComboInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkContratoComboeliminarpthisContratoCombo();
    }

    @JsonIgnore
    public ContratoComboOid getContratoComboeliminarpthisContratoCombo() {
        return this.contratoComboeliminarpthisContratoCombo;
    }
    
    public void setContratoComboeliminarpthisContratoCombo(ContratoComboOid contratoComboeliminarpthisContratoCombo) {
        this.contratoComboeliminarpthisContratoCombo = contratoComboeliminarpthisContratoCombo;
    }

    @JsonIgnore
    public ContratoCombo getContratoComboeliminarpthisContratoComboInstance() {
        return this.contratoComboeliminarpthisContratoComboInstance;
    }
    
    public void setContratoComboeliminarpthisContratoComboInstance(ContratoCombo contratoComboeliminarpthisContratoCombo) {
        this.contratoComboeliminarpthisContratoComboInstance = contratoComboeliminarpthisContratoCombo;
        this.contratoComboeliminarpthisContratoCombo = contratoComboeliminarpthisContratoCombo != null ? contratoComboeliminarpthisContratoCombo.getOid() : null;
    }

    private void checkContratoComboeliminarpthisContratoCombo() throws NotNullArgumentException {
        if(this.getContratoComboeliminarpthisContratoComboInstance() == null || this.getContratoComboeliminarpthisContratoComboInstance().isNull()) {
            throw new NotNullArgumentException(ContratoComboConstants.SERV_ID_ELIMINAR, ContratoComboConstants.CLASS_ID, ContratoComboConstants.ARG_ID_ELIMINAR_CONTRATOCOMBOELIMINARPTHISCONTRATOCOMBO, ContratoComboConstants.SERV_ALIAS_ELIMINAR, ContratoComboConstants.CLASS_ALIAS, ContratoComboConstants.ARG_ALIAS_ELIMINAR_CONTRATOCOMBOELIMINARPTHISCONTRATOCOMBO);
        }
    }
}
