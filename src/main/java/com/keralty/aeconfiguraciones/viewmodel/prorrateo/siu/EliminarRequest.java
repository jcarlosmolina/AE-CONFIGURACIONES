package com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProrrateoOid;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;

public class EliminarRequest {

    @JsonProperty("p_thisprorrateo_oid")
    private ProrrateoOid prorrateoeliminarpthisProrrateo;
    
    @JsonIgnore
    private Prorrateo prorrateoeliminarpthisProrrateoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkProrrateoeliminarpthisProrrateo();
    }

    @JsonIgnore
    public ProrrateoOid getProrrateoeliminarpthisProrrateo() {
        return this.prorrateoeliminarpthisProrrateo;
    }
    
    public void setProrrateoeliminarpthisProrrateo(ProrrateoOid prorrateoeliminarpthisProrrateo) {
        this.prorrateoeliminarpthisProrrateo = prorrateoeliminarpthisProrrateo;
    }

    @JsonIgnore
    public Prorrateo getProrrateoeliminarpthisProrrateoInstance() {
        return this.prorrateoeliminarpthisProrrateoInstance;
    }
    
    public void setProrrateoeliminarpthisProrrateoInstance(Prorrateo prorrateoeliminarpthisProrrateo) {
        this.prorrateoeliminarpthisProrrateoInstance = prorrateoeliminarpthisProrrateo;
        this.prorrateoeliminarpthisProrrateo = prorrateoeliminarpthisProrrateo != null ? prorrateoeliminarpthisProrrateo.getOid() : null;
    }

    private void checkProrrateoeliminarpthisProrrateo() throws NotNullArgumentException {
        if(this.getProrrateoeliminarpthisProrrateoInstance() == null || this.getProrrateoeliminarpthisProrrateoInstance().isNull()) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_ELIMINAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_ELIMINAR_PRORRATEOELIMINARPTHISPRORRATEO, ProrrateoConstants.SERV_ALIAS_ELIMINAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_ELIMINAR_PRORRATEOELIMINARPTHISPRORRATEO);
        }
    }
}
