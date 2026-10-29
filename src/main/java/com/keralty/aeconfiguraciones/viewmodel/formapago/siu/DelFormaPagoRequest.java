package com.keralty.aeconfiguraciones.viewmodel.formapago.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FormaPagoConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class DelFormaPagoRequest {

    @JsonProperty("p_thisformapago_oid")
    private FormaPagoOid formaPagoDelFormaPagopthisFormaPago;
    
    @JsonIgnore
    private FormaPago formaPagoDelFormaPagopthisFormaPagoInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid formaPagoDelFormaPagopevcDominio;
    
    @JsonIgnore
    private Dominio formaPagoDelFormaPagopevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFormaPagoDelFormaPagopthisFormaPago();
        checkFormaPagoDelFormaPagopevcDominio();
    }

    @JsonIgnore
    public FormaPagoOid getFormaPagoDelFormaPagopthisFormaPago() {
        return this.formaPagoDelFormaPagopthisFormaPago;
    }
    
    public void setFormaPagoDelFormaPagopthisFormaPago(FormaPagoOid formaPagoDelFormaPagopthisFormaPago) {
        this.formaPagoDelFormaPagopthisFormaPago = formaPagoDelFormaPagopthisFormaPago;
    }

    @JsonIgnore
    public FormaPago getFormaPagoDelFormaPagopthisFormaPagoInstance() {
        return this.formaPagoDelFormaPagopthisFormaPagoInstance;
    }
    
    public void setFormaPagoDelFormaPagopthisFormaPagoInstance(FormaPago formaPagoDelFormaPagopthisFormaPago) {
        this.formaPagoDelFormaPagopthisFormaPagoInstance = formaPagoDelFormaPagopthisFormaPago;
        this.formaPagoDelFormaPagopthisFormaPago = formaPagoDelFormaPagopthisFormaPago != null ? formaPagoDelFormaPagopthisFormaPago.getOid() : null;
    }

    private void checkFormaPagoDelFormaPagopthisFormaPago() throws NotNullArgumentException {
        if(this.getFormaPagoDelFormaPagopthisFormaPagoInstance() == null || this.getFormaPagoDelFormaPagopthisFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_DELFORMAPAGO, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_DELFORMAPAGO_FORMAPAGODELFORMAPAGOPTHISFORMAPAGO, FormaPagoConstants.SERV_ALIAS_DELFORMAPAGO, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_DELFORMAPAGO_FORMAPAGODELFORMAPAGOPTHISFORMAPAGO);
        }
    }

    @JsonIgnore
    public DominioOid getFormaPagoDelFormaPagopevcDominio() {
        return this.formaPagoDelFormaPagopevcDominio;
    }
    
    public void setFormaPagoDelFormaPagopevcDominio(DominioOid formaPagoDelFormaPagopevcDominio) {
        this.formaPagoDelFormaPagopevcDominio = formaPagoDelFormaPagopevcDominio;
    }

    @JsonIgnore
    public Dominio getFormaPagoDelFormaPagopevcDominioInstance() {
        return this.formaPagoDelFormaPagopevcDominioInstance;
    }
    
    public void setFormaPagoDelFormaPagopevcDominioInstance(Dominio formaPagoDelFormaPagopevcDominio) {
        this.formaPagoDelFormaPagopevcDominioInstance = formaPagoDelFormaPagopevcDominio;
        this.formaPagoDelFormaPagopevcDominio = formaPagoDelFormaPagopevcDominio != null ? formaPagoDelFormaPagopevcDominio.getOid() : null;
    }

    private void checkFormaPagoDelFormaPagopevcDominio() throws NotNullArgumentException {
        if(this.getFormaPagoDelFormaPagopevcDominioInstance() == null || this.getFormaPagoDelFormaPagopevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(FormaPagoConstants.SERV_ID_DELFORMAPAGO, FormaPagoConstants.CLASS_ID, FormaPagoConstants.ARG_ID_DELFORMAPAGO_FORMAPAGODELFORMAPAGOPEVCDOMINIO, FormaPagoConstants.SERV_ALIAS_DELFORMAPAGO, FormaPagoConstants.CLASS_ALIAS, FormaPagoConstants.ARG_ALIAS_DELFORMAPAGO_FORMAPAGODELFORMAPAGOPEVCDOMINIO);
        }
    }
}
