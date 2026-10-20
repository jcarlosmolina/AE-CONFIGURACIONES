package com.keralty.aeconfiguraciones.viewmodel.valor.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ValorConstants;

public class CrearRequest {

    @JsonProperty("p_atrvalorposible")
    private String valorcrearpatrValorPosible;

    public void checkArguments() throws NotNullArgumentException {
        checkValorcrearpatrValorPosible();
    }

    @JsonIgnore
    public String getValorcrearpatrValorPosible() {
        return this.valorcrearpatrValorPosible;
    }
    
    public void setValorcrearpatrValorPosible(String valorcrearpatrValorPosible) {
        this.valorcrearpatrValorPosible = valorcrearpatrValorPosible;
    }

    private void checkValorcrearpatrValorPosible() throws NotNullArgumentException {
        if(this.getValorcrearpatrValorPosible() == null) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_CREAR, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_CREAR_VALORCREARPATRVALORPOSIBLE, ValorConstants.SERV_ALIAS_CREAR, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_CREAR_VALORCREARPATRVALORPOSIBLE);
        }
    }
}
