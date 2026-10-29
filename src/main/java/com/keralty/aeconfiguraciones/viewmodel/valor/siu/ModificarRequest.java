package com.keralty.aeconfiguraciones.viewmodel.valor.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class ModificarRequest {

    @JsonProperty("p_thisvalor_oid")
    private ValorOid valormodificarpthisValor;
    
    @JsonIgnore
    private Valor valormodificarpthisValorInstance;

    @JsonProperty("p_valorposible")
    private String valormodificarpValorPosible;

    public void checkArguments() throws NotNullArgumentException {
        checkValormodificarpthisValor();
        checkValormodificarpValorPosible();
    }

    @JsonIgnore
    public ValorOid getValormodificarpthisValor() {
        return this.valormodificarpthisValor;
    }
    
    public void setValormodificarpthisValor(ValorOid valormodificarpthisValor) {
        this.valormodificarpthisValor = valormodificarpthisValor;
    }

    @JsonIgnore
    public Valor getValormodificarpthisValorInstance() {
        return this.valormodificarpthisValorInstance;
    }
    
    public void setValormodificarpthisValorInstance(Valor valormodificarpthisValor) {
        this.valormodificarpthisValorInstance = valormodificarpthisValor;
        this.valormodificarpthisValor = valormodificarpthisValor != null ? valormodificarpthisValor.getOid() : null;
    }

    private void checkValormodificarpthisValor() throws NotNullArgumentException {
        if(this.getValormodificarpthisValorInstance() == null || this.getValormodificarpthisValorInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_MODIFICAR, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_MODIFICAR_VALORMODIFICARPTHISVALOR, ValorConstants.SERV_ALIAS_MODIFICAR, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_MODIFICAR_VALORMODIFICARPTHISVALOR);
        }
    }

    @JsonIgnore
    public String getValormodificarpValorPosible() {
        return this.valormodificarpValorPosible;
    }
    
    public void setValormodificarpValorPosible(String valormodificarpValorPosible) {
        this.valormodificarpValorPosible = valormodificarpValorPosible;
    }

    private void checkValormodificarpValorPosible() throws NotNullArgumentException {
        if(this.getValormodificarpValorPosible() == null) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_MODIFICAR, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_MODIFICAR_VALORMODIFICARPVALORPOSIBLE, ValorConstants.SERV_ALIAS_MODIFICAR, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_MODIFICAR_VALORMODIFICARPVALORPOSIBLE);
        }
    }
}
