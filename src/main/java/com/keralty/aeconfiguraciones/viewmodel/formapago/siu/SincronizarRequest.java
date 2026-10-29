package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class SincronizarRequest {

    @JsonProperty("p_thisformapago_oid")
    private FormaPagoOid formaPagosincronizarpthisFormaPago;
    
    @JsonIgnore
    private FormaPago formaPagosincronizarpthisFormaPagoInstance;

    @JsonProperty("p_descripcion")
    private String formaPagosincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagosincronizarpthisFormaPago();
        checkFormaPagosincronizarpDescripcion();
    }

    @JsonIgnore
    public FormaPagoOid getFormaPagosincronizarpthisFormaPago() {
        return this.formaPagosincronizarpthisFormaPago;
    }
    
    public void setFormaPagosincronizarpthisFormaPago(FormaPagoOid formaPagosincronizarpthisFormaPago) {
        this.formaPagosincronizarpthisFormaPago = formaPagosincronizarpthisFormaPago;
    }

    @JsonIgnore
    public FormaPago getFormaPagosincronizarpthisFormaPagoInstance() {
        return this.formaPagosincronizarpthisFormaPagoInstance;
    }
    
    public void setFormaPagosincronizarpthisFormaPagoInstance(FormaPago formaPagosincronizarpthisFormaPago) {
        this.formaPagosincronizarpthisFormaPagoInstance = formaPagosincronizarpthisFormaPago;
        this.formaPagosincronizarpthisFormaPago = formaPagosincronizarpthisFormaPago != null ? formaPagosincronizarpthisFormaPago.getOid() : null;
    }

    private void checkFormaPagosincronizarpthisFormaPago() throws NotNullArgumentException {
        if(this.getFormaPagosincronizarpthisFormaPagoInstance() == null || this.getFormaPagosincronizarpthisFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_SINCRONIZAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_SINCRONIZAR_FORMAPAGOSINCRONIZARPTHISFORMAPAGO, FormaPagoConstants.SERV_ALIAS_SINCRONIZAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_SINCRONIZAR_FORMAPAGOSINCRONIZARPTHISFORMAPAGO);
        }
    }

    @JsonIgnore
    public String getFormaPagosincronizarpDescripcion() {
        return this.formaPagosincronizarpDescripcion;
    }
    
    public void setFormaPagosincronizarpDescripcion(String formaPagosincronizarpDescripcion) {
        this.formaPagosincronizarpDescripcion = formaPagosincronizarpDescripcion;
    }

    private void checkFormaPagosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getFormaPagosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_SINCRONIZAR, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_SINCRONIZAR_FORMAPAGOSINCRONIZARPDESCRIPCION, FormaPagoConstants.SERV_ALIAS_SINCRONIZAR, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_SINCRONIZAR_FORMAPAGOSINCRONIZARPDESCRIPCION);
        }
    }
}
