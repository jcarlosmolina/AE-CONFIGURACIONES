package com.keralty.aeconfiguraciones.viewmodel.causal.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;

public class EliminarRequest {

    @JsonProperty("p_thiscausal_oid")
    private CausalOid causaleliminarpthisCausal;
    
    @JsonIgnore
    private Causal causaleliminarpthisCausalInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkCausaleliminarpthisCausal();
    }

    @JsonIgnore
    public CausalOid getCausaleliminarpthisCausal() {
        return this.causaleliminarpthisCausal;
    }
    
    public void setCausaleliminarpthisCausal(CausalOid causaleliminarpthisCausal) {
        this.causaleliminarpthisCausal = causaleliminarpthisCausal;
    }

    @JsonIgnore
    public Causal getCausaleliminarpthisCausalInstance() {
        return this.causaleliminarpthisCausalInstance;
    }
    
    public void setCausaleliminarpthisCausalInstance(Causal causaleliminarpthisCausal) {
        this.causaleliminarpthisCausalInstance = causaleliminarpthisCausal;
        this.causaleliminarpthisCausal = causaleliminarpthisCausal != null ? causaleliminarpthisCausal.getOid() : null;
    }

    private void checkCausaleliminarpthisCausal() throws NotNullArgumentException {
        if(this.getCausaleliminarpthisCausalInstance() == null || this.getCausaleliminarpthisCausalInstance().isNull()) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_ELIMINAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_ELIMINAR_CAUSALELIMINARPTHISCAUSAL, CausalConstants.SERV_ALIAS_ELIMINAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_ELIMINAR_CAUSALELIMINARPTHISCAUSAL);
        }
    }
}
