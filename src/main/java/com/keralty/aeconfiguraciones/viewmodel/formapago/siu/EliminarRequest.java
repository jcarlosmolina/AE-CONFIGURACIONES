package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class EliminarRequest {

    @JsonProperty("p_thisformapago_oid")
    private FormaPagoOid formaPagoeliminarpthisFormaPago;
    
    @JsonIgnore
    private FormaPago formaPagoeliminarpthisFormaPagoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagoeliminarpthisFormaPago();
    }

    @JsonIgnore
    public FormaPagoOid getFormaPagoeliminarpthisFormaPago() {
        return this.formaPagoeliminarpthisFormaPago;
    }
    
    public void setFormaPagoeliminarpthisFormaPago(FormaPagoOid formaPagoeliminarpthisFormaPago) {
        this.formaPagoeliminarpthisFormaPago = formaPagoeliminarpthisFormaPago;
    }

    @JsonIgnore
    public FormaPago getFormaPagoeliminarpthisFormaPagoInstance() {
        return this.formaPagoeliminarpthisFormaPagoInstance;
    }
    
    public void setFormaPagoeliminarpthisFormaPagoInstance(FormaPago formaPagoeliminarpthisFormaPago) {
        this.formaPagoeliminarpthisFormaPagoInstance = formaPagoeliminarpthisFormaPago;
        this.formaPagoeliminarpthisFormaPago = formaPagoeliminarpthisFormaPago != null ? formaPagoeliminarpthisFormaPago.getOid() : null;
    }

    private void checkFormaPagoeliminarpthisFormaPago() throws NotNullArgumentException {
        if(this.getFormaPagoeliminarpthisFormaPagoInstance() == null || this.getFormaPagoeliminarpthisFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_ELIMINAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_ELIMINAR_FORMAPAGOELIMINARPTHISFORMAPAGO, FormaPagoConstants.SERV_ALIAS_ELIMINAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_ELIMINAR_FORMAPAGOELIMINARPTHISFORMAPAGO);
        }
    }
}
