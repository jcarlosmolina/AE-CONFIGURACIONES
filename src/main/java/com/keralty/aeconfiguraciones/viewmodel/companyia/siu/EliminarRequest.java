package com.keralty.aeconfiguraciones.viewmodel.companyia.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;

public class EliminarRequest {

    @JsonProperty("p_thiscompanyia_oid")
    private CompanyiaOid companyiaeliminarpthisCompanyia;
    
    @JsonIgnore
    private Companyia companyiaeliminarpthisCompanyiaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkCompanyiaeliminarpthisCompanyia();
    }

    @JsonIgnore
    public CompanyiaOid getCompanyiaeliminarpthisCompanyia() {
        return this.companyiaeliminarpthisCompanyia;
    }
    
    public void setCompanyiaeliminarpthisCompanyia(CompanyiaOid companyiaeliminarpthisCompanyia) {
        this.companyiaeliminarpthisCompanyia = companyiaeliminarpthisCompanyia;
    }

    @JsonIgnore
    public Companyia getCompanyiaeliminarpthisCompanyiaInstance() {
        return this.companyiaeliminarpthisCompanyiaInstance;
    }
    
    public void setCompanyiaeliminarpthisCompanyiaInstance(Companyia companyiaeliminarpthisCompanyia) {
        this.companyiaeliminarpthisCompanyiaInstance = companyiaeliminarpthisCompanyia;
        this.companyiaeliminarpthisCompanyia = companyiaeliminarpthisCompanyia != null ? companyiaeliminarpthisCompanyia.getOid() : null;
    }

    private void checkCompanyiaeliminarpthisCompanyia() throws NotNullArgumentException {
        if(this.getCompanyiaeliminarpthisCompanyiaInstance() == null || this.getCompanyiaeliminarpthisCompanyiaInstance().isNull()) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_ELIMINAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_ELIMINAR_COMPANYIAELIMINARPTHISCOMPANYIA, CompanyiaConstants.SERV_ALIAS_ELIMINAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_ELIMINAR_COMPANYIAELIMINARPTHISCOMPANYIA);
        }
    }
}
