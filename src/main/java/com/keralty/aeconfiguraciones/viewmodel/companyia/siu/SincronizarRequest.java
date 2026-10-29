package com.keralty.aeconfiguraciones.viewmodel.companyia.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;

public class SincronizarRequest {

    @JsonProperty("p_thiscompanyia_oid")
    private CompanyiaOid companyiasincronizarpthisCompanyia;
    
    @JsonIgnore
    private Companyia companyiasincronizarpthisCompanyiaInstance;

    @JsonProperty("p_nombre")
    private String companyiasincronizarpNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkCompanyiasincronizarpthisCompanyia();
        checkCompanyiasincronizarpNombre();
    }

    @JsonIgnore
    public CompanyiaOid getCompanyiasincronizarpthisCompanyia() {
        return this.companyiasincronizarpthisCompanyia;
    }
    
    public void setCompanyiasincronizarpthisCompanyia(CompanyiaOid companyiasincronizarpthisCompanyia) {
        this.companyiasincronizarpthisCompanyia = companyiasincronizarpthisCompanyia;
    }

    @JsonIgnore
    public Companyia getCompanyiasincronizarpthisCompanyiaInstance() {
        return this.companyiasincronizarpthisCompanyiaInstance;
    }
    
    public void setCompanyiasincronizarpthisCompanyiaInstance(Companyia companyiasincronizarpthisCompanyia) {
        this.companyiasincronizarpthisCompanyiaInstance = companyiasincronizarpthisCompanyia;
        this.companyiasincronizarpthisCompanyia = companyiasincronizarpthisCompanyia != null ? companyiasincronizarpthisCompanyia.getOid() : null;
    }

    private void checkCompanyiasincronizarpthisCompanyia() throws NotNullArgumentException {
        if(this.getCompanyiasincronizarpthisCompanyiaInstance() == null || this.getCompanyiasincronizarpthisCompanyiaInstance().isNull()) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_SINCRONIZAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_SINCRONIZAR_COMPANYIASINCRONIZARPTHISCOMPANYIA, CompanyiaConstants.SERV_ALIAS_SINCRONIZAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_SINCRONIZAR_COMPANYIASINCRONIZARPTHISCOMPANYIA);
        }
    }

    @JsonIgnore
    public String getCompanyiasincronizarpNombre() {
        return this.companyiasincronizarpNombre;
    }
    
    public void setCompanyiasincronizarpNombre(String companyiasincronizarpNombre) {
        this.companyiasincronizarpNombre = companyiasincronizarpNombre;
    }

    private void checkCompanyiasincronizarpNombre() throws NotNullArgumentException {
        if(this.getCompanyiasincronizarpNombre() == null) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_SINCRONIZAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_SINCRONIZAR_COMPANYIASINCRONIZARPNOMBRE, CompanyiaConstants.SERV_ALIAS_SINCRONIZAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_SINCRONIZAR_COMPANYIASINCRONIZARPNOMBRE);
        }
    }
}
