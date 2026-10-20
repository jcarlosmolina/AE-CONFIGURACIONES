package com.keralty.aeconfiguraciones.viewmodel.companyia.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;

public class ModificarRequest {

    @JsonProperty("p_thiscompanyia_oid")
    private CompanyiaOid companyiamodificarpthisCompanyia;
    
    @JsonIgnore
    private Companyia companyiamodificarpthisCompanyiaInstance;

    @JsonProperty("p_esdemp")
    private Boolean companyiamodificarpEsDeMP;

    public void checkArguments() throws NotNullArgumentException {
        checkCompanyiamodificarpthisCompanyia();
        checkCompanyiamodificarpEsDeMP();
    }

    @JsonIgnore
    public CompanyiaOid getCompanyiamodificarpthisCompanyia() {
        return this.companyiamodificarpthisCompanyia;
    }
    
    public void setCompanyiamodificarpthisCompanyia(CompanyiaOid companyiamodificarpthisCompanyia) {
        this.companyiamodificarpthisCompanyia = companyiamodificarpthisCompanyia;
    }

    @JsonIgnore
    public Companyia getCompanyiamodificarpthisCompanyiaInstance() {
        return this.companyiamodificarpthisCompanyiaInstance;
    }
    
    public void setCompanyiamodificarpthisCompanyiaInstance(Companyia companyiamodificarpthisCompanyia) {
        this.companyiamodificarpthisCompanyiaInstance = companyiamodificarpthisCompanyia;
        this.companyiamodificarpthisCompanyia = companyiamodificarpthisCompanyia != null ? companyiamodificarpthisCompanyia.getOid() : null;
    }

    private void checkCompanyiamodificarpthisCompanyia() throws NotNullArgumentException {
        if(this.getCompanyiamodificarpthisCompanyiaInstance() == null || this.getCompanyiamodificarpthisCompanyiaInstance().isNull()) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_MODIFICAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_MODIFICAR_COMPANYIAMODIFICARPTHISCOMPANYIA, CompanyiaConstants.SERV_ALIAS_MODIFICAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_MODIFICAR_COMPANYIAMODIFICARPTHISCOMPANYIA);
        }
    }

    @JsonIgnore
    public Boolean getCompanyiamodificarpEsDeMP() {
        return this.companyiamodificarpEsDeMP;
    }
    
    public void setCompanyiamodificarpEsDeMP(Boolean companyiamodificarpEsDeMP) {
        this.companyiamodificarpEsDeMP = companyiamodificarpEsDeMP;
    }

    private void checkCompanyiamodificarpEsDeMP() throws NotNullArgumentException {
        if(this.getCompanyiamodificarpEsDeMP() == null) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_MODIFICAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_MODIFICAR_COMPANYIAMODIFICARPESDEMP, CompanyiaConstants.SERV_ALIAS_MODIFICAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_MODIFICAR_COMPANYIAMODIFICARPESDEMP);
        }
    }
}
