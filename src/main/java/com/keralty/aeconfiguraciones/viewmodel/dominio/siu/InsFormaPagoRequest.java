package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;

public class InsFormaPagoRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioInsFormaPagopthisDominio;
    
    @JsonIgnore
    private Dominio dominioInsFormaPagopthisDominioInstance;

    @JsonProperty("p_evcformapago_oid")
    private FormaPagoOid dominioInsFormaPagopevcFormaPago;
    
    @JsonIgnore
    private FormaPago dominioInsFormaPagopevcFormaPagoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioInsFormaPagopthisDominio();
        checkDominioInsFormaPagopevcFormaPago();
    }

    @JsonIgnore
    public DominioOid getDominioInsFormaPagopthisDominio() {
        return this.dominioInsFormaPagopthisDominio;
    }
    
    public void setDominioInsFormaPagopthisDominio(DominioOid dominioInsFormaPagopthisDominio) {
        this.dominioInsFormaPagopthisDominio = dominioInsFormaPagopthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsFormaPagopthisDominioInstance() {
        return this.dominioInsFormaPagopthisDominioInstance;
    }
    
    public void setDominioInsFormaPagopthisDominioInstance(Dominio dominioInsFormaPagopthisDominio) {
        this.dominioInsFormaPagopthisDominioInstance = dominioInsFormaPagopthisDominio;
        this.dominioInsFormaPagopthisDominio = dominioInsFormaPagopthisDominio != null ? dominioInsFormaPagopthisDominio.getOid() : null;
    }

    private void checkDominioInsFormaPagopthisDominio() throws NotNullArgumentException {
        if(this.getDominioInsFormaPagopthisDominioInstance() == null || this.getDominioInsFormaPagopthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSFORMAPAGO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSFORMAPAGO_DOMINIOINSFORMAPAGOPTHISDOMINIO, DominioConstants.SERV_ALIAS_INSFORMAPAGO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSFORMAPAGO_DOMINIOINSFORMAPAGOPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public FormaPagoOid getDominioInsFormaPagopevcFormaPago() {
        return this.dominioInsFormaPagopevcFormaPago;
    }
    
    public void setDominioInsFormaPagopevcFormaPago(FormaPagoOid dominioInsFormaPagopevcFormaPago) {
        this.dominioInsFormaPagopevcFormaPago = dominioInsFormaPagopevcFormaPago;
    }

    @JsonIgnore
    public FormaPago getDominioInsFormaPagopevcFormaPagoInstance() {
        return this.dominioInsFormaPagopevcFormaPagoInstance;
    }
    
    public void setDominioInsFormaPagopevcFormaPagoInstance(FormaPago dominioInsFormaPagopevcFormaPago) {
        this.dominioInsFormaPagopevcFormaPagoInstance = dominioInsFormaPagopevcFormaPago;
        this.dominioInsFormaPagopevcFormaPago = dominioInsFormaPagopevcFormaPago != null ? dominioInsFormaPagopevcFormaPago.getOid() : null;
    }

    private void checkDominioInsFormaPagopevcFormaPago() throws NotNullArgumentException {
        if(this.getDominioInsFormaPagopevcFormaPagoInstance() == null || this.getDominioInsFormaPagopevcFormaPagoInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSFORMAPAGO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSFORMAPAGO_DOMINIOINSFORMAPAGOPEVCFORMAPAGO, DominioConstants.SERV_ALIAS_INSFORMAPAGO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSFORMAPAGO_DOMINIOINSFORMAPAGOPEVCFORMAPAGO);
        }
    }
}
