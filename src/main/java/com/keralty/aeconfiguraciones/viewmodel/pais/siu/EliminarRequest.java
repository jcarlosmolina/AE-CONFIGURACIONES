package com.keralty.aeconfiguraciones.viewmodel.pais.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PaisConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.Pais;

public class EliminarRequest {

    @JsonProperty("p_thispais_oid")
    private PaisOid paiseliminarpthisPais;
    
    @JsonIgnore
    private Pais paiseliminarpthisPaisInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkPaiseliminarpthisPais();
    }

    @JsonIgnore
    public PaisOid getPaiseliminarpthisPais() {
        return this.paiseliminarpthisPais;
    }
    
    public void setPaiseliminarpthisPais(PaisOid paiseliminarpthisPais) {
        this.paiseliminarpthisPais = paiseliminarpthisPais;
    }

    @JsonIgnore
    public Pais getPaiseliminarpthisPaisInstance() {
        return this.paiseliminarpthisPaisInstance;
    }
    
    public void setPaiseliminarpthisPaisInstance(Pais paiseliminarpthisPais) {
        this.paiseliminarpthisPaisInstance = paiseliminarpthisPais;
        this.paiseliminarpthisPais = paiseliminarpthisPais != null ? paiseliminarpthisPais.getOid() : null;
    }

    private void checkPaiseliminarpthisPais() throws NotNullArgumentException {
        if(this.getPaiseliminarpthisPaisInstance() == null || this.getPaiseliminarpthisPaisInstance().isNull()) {
            throw new NotNullArgumentException(PaisConstants.SERV_ID_ELIMINAR, PaisConstants.CLASS_ID, PaisConstants.ARG_ID_ELIMINAR_PAISELIMINARPTHISPAIS, PaisConstants.SERV_ALIAS_ELIMINAR, PaisConstants.CLASS_ALIAS, PaisConstants.ARG_ALIAS_ELIMINAR_PAISELIMINARPTHISPAIS);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
