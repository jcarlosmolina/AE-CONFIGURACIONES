package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class InsFormaPagoRequest {

    @JsonProperty("p_thisformapago_oid")
    private FormaPagoOid formaPagoInsFormaPagopthisFormaPago;
    
    @JsonIgnore
    private FormaPago formaPagoInsFormaPagopthisFormaPagoInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid formaPagoInsFormaPagopevcDominio;
    
    @JsonIgnore
    private Dominio formaPagoInsFormaPagopevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagoInsFormaPagopthisFormaPago();
        checkFormaPagoInsFormaPagopevcDominio();
    }

    @JsonIgnore
    public FormaPagoOid getFormaPagoInsFormaPagopthisFormaPago() {
        return this.formaPagoInsFormaPagopthisFormaPago;
    }
    
    public void setFormaPagoInsFormaPagopthisFormaPago(FormaPagoOid formaPagoInsFormaPagopthisFormaPago) {
        this.formaPagoInsFormaPagopthisFormaPago = formaPagoInsFormaPagopthisFormaPago;
    }

    @JsonIgnore
    public FormaPago getFormaPagoInsFormaPagopthisFormaPagoInstance() {
        return this.formaPagoInsFormaPagopthisFormaPagoInstance;
    }
    
    public void setFormaPagoInsFormaPagopthisFormaPagoInstance(FormaPago formaPagoInsFormaPagopthisFormaPago) {
        this.formaPagoInsFormaPagopthisFormaPagoInstance = formaPagoInsFormaPagopthisFormaPago;
        this.formaPagoInsFormaPagopthisFormaPago = formaPagoInsFormaPagopthisFormaPago != null ? formaPagoInsFormaPagopthisFormaPago.getOid() : null;
    }

    private void checkFormaPagoInsFormaPagopthisFormaPago() throws NotNullArgumentException {
        if(this.getFormaPagoInsFormaPagopthisFormaPagoInstance() == null || this.getFormaPagoInsFormaPagopthisFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_INSFORMAPAGO, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_INSFORMAPAGO_FORMAPAGOINSFORMAPAGOPTHISFORMAPAGO, FormaPagoConstants.SERV_ALIAS_INSFORMAPAGO, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_INSFORMAPAGO_FORMAPAGOINSFORMAPAGOPTHISFORMAPAGO);
        }
    }

    @JsonIgnore
    public DominioOid getFormaPagoInsFormaPagopevcDominio() {
        return this.formaPagoInsFormaPagopevcDominio;
    }
    
    public void setFormaPagoInsFormaPagopevcDominio(DominioOid formaPagoInsFormaPagopevcDominio) {
        this.formaPagoInsFormaPagopevcDominio = formaPagoInsFormaPagopevcDominio;
    }

    @JsonIgnore
    public Dominio getFormaPagoInsFormaPagopevcDominioInstance() {
        return this.formaPagoInsFormaPagopevcDominioInstance;
    }
    
    public void setFormaPagoInsFormaPagopevcDominioInstance(Dominio formaPagoInsFormaPagopevcDominio) {
        this.formaPagoInsFormaPagopevcDominioInstance = formaPagoInsFormaPagopevcDominio;
        this.formaPagoInsFormaPagopevcDominio = formaPagoInsFormaPagopevcDominio != null ? formaPagoInsFormaPagopevcDominio.getOid() : null;
    }

    private void checkFormaPagoInsFormaPagopevcDominio() throws NotNullArgumentException {
        if(this.getFormaPagoInsFormaPagopevcDominioInstance() == null || this.getFormaPagoInsFormaPagopevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_INSFORMAPAGO, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_INSFORMAPAGO_FORMAPAGOINSFORMAPAGOPEVCDOMINIO, FormaPagoConstants.SERV_ALIAS_INSFORMAPAGO, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_INSFORMAPAGO_FORMAPAGOINSFORMAPAGOPEVCDOMINIO);
        }
    }
}
