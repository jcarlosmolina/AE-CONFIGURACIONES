package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class ModificarRequest {

    @JsonProperty("p_thisformapago_oid")
    private FormaPagoOid formaPagomodificarpthisFormaPago;
    
    @JsonIgnore
    private FormaPago formaPagomodificarpthisFormaPagoInstance;

    @JsonProperty("p_abrirurl")
    private Boolean formaPagomodificarpAbrirURL;

    @JsonProperty("p_visible")
    private Boolean formaPagomodificarpVisible;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagomodificarpthisFormaPago();
        checkFormaPagomodificarpAbrirURL();
        checkFormaPagomodificarpVisible();
    }

    @JsonIgnore
    public FormaPagoOid getFormaPagomodificarpthisFormaPago() {
        return this.formaPagomodificarpthisFormaPago;
    }
    
    public void setFormaPagomodificarpthisFormaPago(FormaPagoOid formaPagomodificarpthisFormaPago) {
        this.formaPagomodificarpthisFormaPago = formaPagomodificarpthisFormaPago;
    }

    @JsonIgnore
    public FormaPago getFormaPagomodificarpthisFormaPagoInstance() {
        return this.formaPagomodificarpthisFormaPagoInstance;
    }
    
    public void setFormaPagomodificarpthisFormaPagoInstance(FormaPago formaPagomodificarpthisFormaPago) {
        this.formaPagomodificarpthisFormaPagoInstance = formaPagomodificarpthisFormaPago;
        this.formaPagomodificarpthisFormaPago = formaPagomodificarpthisFormaPago != null ? formaPagomodificarpthisFormaPago.getOid() : null;
    }

    private void checkFormaPagomodificarpthisFormaPago() throws NotNullArgumentException {
        if(this.getFormaPagomodificarpthisFormaPagoInstance() == null || this.getFormaPagomodificarpthisFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_MODIFICAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_MODIFICAR_FORMAPAGOMODIFICARPTHISFORMAPAGO, FormaPagoConstants.SERV_ALIAS_MODIFICAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_MODIFICAR_FORMAPAGOMODIFICARPTHISFORMAPAGO);
        }
    }

    @JsonIgnore
    public Boolean getFormaPagomodificarpAbrirURL() {
        return this.formaPagomodificarpAbrirURL;
    }
    
    public void setFormaPagomodificarpAbrirURL(Boolean formaPagomodificarpAbrirURL) {
        this.formaPagomodificarpAbrirURL = formaPagomodificarpAbrirURL;
    }

    private void checkFormaPagomodificarpAbrirURL() throws NotNullArgumentException {
        if(this.getFormaPagomodificarpAbrirURL() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_MODIFICAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_MODIFICAR_FORMAPAGOMODIFICARPABRIRURL, FormaPagoConstants.SERV_ALIAS_MODIFICAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_MODIFICAR_FORMAPAGOMODIFICARPABRIRURL);
        }
    }

    @JsonIgnore
    public Boolean getFormaPagomodificarpVisible() {
        return this.formaPagomodificarpVisible;
    }
    
    public void setFormaPagomodificarpVisible(Boolean formaPagomodificarpVisible) {
        this.formaPagomodificarpVisible = formaPagomodificarpVisible;
    }

    private void checkFormaPagomodificarpVisible() throws NotNullArgumentException {
        if(this.getFormaPagomodificarpVisible() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_MODIFICAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_MODIFICAR_FORMAPAGOMODIFICARPVISIBLE, FormaPagoConstants.SERV_ALIAS_MODIFICAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_MODIFICAR_FORMAPAGOMODIFICARPVISIBLE);
        }
    }
}
