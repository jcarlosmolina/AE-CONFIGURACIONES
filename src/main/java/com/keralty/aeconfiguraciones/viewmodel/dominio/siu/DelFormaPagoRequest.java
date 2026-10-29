package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class DelFormaPagoRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelFormaPagopthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelFormaPagopthisDominioInstance;

    @JsonProperty("p_evcformapago_oid")
    private FormaPagoOid dominioDelFormaPagopevcFormaPago;
    
    @JsonIgnore
    private FormaPago dominioDelFormaPagopevcFormaPagoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelFormaPagopthisDominio();
        checkDominioDelFormaPagopevcFormaPago();
    }

    @JsonIgnore
    public DominioOid getDominioDelFormaPagopthisDominio() {
        return this.dominioDelFormaPagopthisDominio;
    }
    
    public void setDominioDelFormaPagopthisDominio(DominioOid dominioDelFormaPagopthisDominio) {
        this.dominioDelFormaPagopthisDominio = dominioDelFormaPagopthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelFormaPagopthisDominioInstance() {
        return this.dominioDelFormaPagopthisDominioInstance;
    }
    
    public void setDominioDelFormaPagopthisDominioInstance(Dominio dominioDelFormaPagopthisDominio) {
        this.dominioDelFormaPagopthisDominioInstance = dominioDelFormaPagopthisDominio;
        this.dominioDelFormaPagopthisDominio = dominioDelFormaPagopthisDominio != null ? dominioDelFormaPagopthisDominio.getOid() : null;
    }

    private void checkDominioDelFormaPagopthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelFormaPagopthisDominioInstance() == null || this.getDominioDelFormaPagopthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELFORMAPAGO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELFORMAPAGO_DOMINIODELFORMAPAGOPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELFORMAPAGO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELFORMAPAGO_DOMINIODELFORMAPAGOPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public FormaPagoOid getDominioDelFormaPagopevcFormaPago() {
        return this.dominioDelFormaPagopevcFormaPago;
    }
    
    public void setDominioDelFormaPagopevcFormaPago(FormaPagoOid dominioDelFormaPagopevcFormaPago) {
        this.dominioDelFormaPagopevcFormaPago = dominioDelFormaPagopevcFormaPago;
    }

    @JsonIgnore
    public FormaPago getDominioDelFormaPagopevcFormaPagoInstance() {
        return this.dominioDelFormaPagopevcFormaPagoInstance;
    }
    
    public void setDominioDelFormaPagopevcFormaPagoInstance(FormaPago dominioDelFormaPagopevcFormaPago) {
        this.dominioDelFormaPagopevcFormaPagoInstance = dominioDelFormaPagopevcFormaPago;
        this.dominioDelFormaPagopevcFormaPago = dominioDelFormaPagopevcFormaPago != null ? dominioDelFormaPagopevcFormaPago.getOid() : null;
    }

    private void checkDominioDelFormaPagopevcFormaPago() throws NotNullArgumentException {
        if(this.getDominioDelFormaPagopevcFormaPagoInstance() == null || this.getDominioDelFormaPagopevcFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELFORMAPAGO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELFORMAPAGO_DOMINIODELFORMAPAGOPEVCFORMAPAGO, DominioConstants.SERV_ALIAS_DELFORMAPAGO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELFORMAPAGO_DOMINIODELFORMAPAGOPEVCFORMAPAGO);
        }
    }
}
