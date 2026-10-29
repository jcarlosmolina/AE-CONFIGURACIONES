package com.keralty.aeconfiguraciones.viewmodel.companyia.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long companyiacrearpatrCodigo;

    @JsonProperty("p_atrnombre")
    private String companyiacrearpatrNombre;

    @JsonProperty("p_atresdemp")
    private Boolean companyiacrearpatrEsDeMP;

    public void checkArguments() throws NotNullArgumentException {
        checkCompanyiacrearpatrCodigo();
        checkCompanyiacrearpatrNombre();
        checkCompanyiacrearpatrEsDeMP();
    }

    @JsonIgnore
    public Long getCompanyiacrearpatrCodigo() {
        return this.companyiacrearpatrCodigo;
    }
    
    public void setCompanyiacrearpatrCodigo(Long companyiacrearpatrCodigo) {
        this.companyiacrearpatrCodigo = companyiacrearpatrCodigo;
    }

    private void checkCompanyiacrearpatrCodigo() throws NotNullArgumentException {
        if(this.getCompanyiacrearpatrCodigo() == null) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_CREAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_CREAR_COMPANYIACREARPATRCODIGO, CompanyiaConstants.SERV_ALIAS_CREAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_CREAR_COMPANYIACREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getCompanyiacrearpatrNombre() {
        return this.companyiacrearpatrNombre;
    }
    
    public void setCompanyiacrearpatrNombre(String companyiacrearpatrNombre) {
        this.companyiacrearpatrNombre = companyiacrearpatrNombre;
    }

    private void checkCompanyiacrearpatrNombre() throws NotNullArgumentException {
        if(this.getCompanyiacrearpatrNombre() == null) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_CREAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_CREAR_COMPANYIACREARPATRNOMBRE, CompanyiaConstants.SERV_ALIAS_CREAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_CREAR_COMPANYIACREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public Boolean getCompanyiacrearpatrEsDeMP() {
        return this.companyiacrearpatrEsDeMP;
    }
    
    public void setCompanyiacrearpatrEsDeMP(Boolean companyiacrearpatrEsDeMP) {
        this.companyiacrearpatrEsDeMP = companyiacrearpatrEsDeMP;
    }

    private void checkCompanyiacrearpatrEsDeMP() throws NotNullArgumentException {
        if(this.getCompanyiacrearpatrEsDeMP() == null) {
            throw new NotNullArgumentException(CompanyiaConstants.SERV_ID_CREAR, CompanyiaConstants.CLASS_ID, CompanyiaConstants.ARG_ID_CREAR_COMPANYIACREARPATRESDEMP, CompanyiaConstants.SERV_ALIAS_CREAR, CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ARG_ALIAS_CREAR_COMPANYIACREARPATRESDEMP);
        }
    }
}
