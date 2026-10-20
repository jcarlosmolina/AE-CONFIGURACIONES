package com.keralty.aeconfiguraciones.viewmodel.eps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EpsConstants;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;

public class EliminarRequest {

    @JsonProperty("p_thiseps_oid")
    private EpsOid epseliminarpthisEPS;
    
    @JsonIgnore
    private Eps epseliminarpthisEPSInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEpseliminarpthisEPS();
    }

    @JsonIgnore
    public EpsOid getEpseliminarpthisEPS() {
        return this.epseliminarpthisEPS;
    }
    
    public void setEpseliminarpthisEPS(EpsOid epseliminarpthisEPS) {
        this.epseliminarpthisEPS = epseliminarpthisEPS;
    }

    @JsonIgnore
    public Eps getEpseliminarpthisEPSInstance() {
        return this.epseliminarpthisEPSInstance;
    }
    
    public void setEpseliminarpthisEPSInstance(Eps epseliminarpthisEPS) {
        this.epseliminarpthisEPSInstance = epseliminarpthisEPS;
        this.epseliminarpthisEPS = epseliminarpthisEPS != null ? epseliminarpthisEPS.getOid() : null;
    }

    private void checkEpseliminarpthisEPS() throws NotNullArgumentException {
        if(this.getEpseliminarpthisEPSInstance() == null || this.getEpseliminarpthisEPSInstance().isNull()) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_ELIMINAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_ELIMINAR_EPSELIMINARPTHISEPS, EpsConstants.SERV_ALIAS_ELIMINAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_ELIMINAR_EPSELIMINARPTHISEPS);
        }
    }
}
