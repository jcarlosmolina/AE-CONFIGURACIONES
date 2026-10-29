package com.keralty.aeconfiguraciones.viewmodel.valor.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class EliminarRequest {

    @JsonProperty("p_thisvalor_oid")
    private ValorOid valoreliminarpthisValor;
    
    @JsonIgnore
    private Valor valoreliminarpthisValorInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkValoreliminarpthisValor();
    }

    @JsonIgnore
    public ValorOid getValoreliminarpthisValor() {
        return this.valoreliminarpthisValor;
    }
    
    public void setValoreliminarpthisValor(ValorOid valoreliminarpthisValor) {
        this.valoreliminarpthisValor = valoreliminarpthisValor;
    }

    @JsonIgnore
    public Valor getValoreliminarpthisValorInstance() {
        return this.valoreliminarpthisValorInstance;
    }
    
    public void setValoreliminarpthisValorInstance(Valor valoreliminarpthisValor) {
        this.valoreliminarpthisValorInstance = valoreliminarpthisValor;
        this.valoreliminarpthisValor = valoreliminarpthisValor != null ? valoreliminarpthisValor.getOid() : null;
    }

    private void checkValoreliminarpthisValor() throws NotNullArgumentException {
        if(this.getValoreliminarpthisValorInstance() == null || this.getValoreliminarpthisValorInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_ELIMINAR, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_ELIMINAR_VALORELIMINARPTHISVALOR, ValorConstants.SERV_ALIAS_ELIMINAR, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_ELIMINAR_VALORELIMINARPTHISVALOR);
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
