package com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FLogContratoCombo {

    @JsonProperty("vfdesde")
    private Date vfDesde;

    @JsonProperty("vfhasta")
    private Date vfHasta;

    @JsonProperty("vfnum")
    private String vfNum;

    public FLogContratoCombo() {
        // Default constructor
    }

    @JsonIgnore
    public Date getVfDesde() {
        return this.vfDesde;
    }
    
    public void setVfDesde(Date vfDesde) {
        this.vfDesde = vfDesde;
    }

    @JsonIgnore
    public Date getVfHasta() {
        return this.vfHasta;
    }
    
    public void setVfHasta(Date vfHasta) {
        this.vfHasta = vfHasta;
    }

    @JsonIgnore
    public String getVfNum() {
        return this.vfNum;
    }
    
    public void setVfNum(String vfNum) {
        this.vfNum = vfNum;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(LogContratoComboConstants.FILTER_NAME_FLOGCONTRATOCOMBO);
        filterRequest.addFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFDESDE, vfDesde);
        filterRequest.addFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFHASTA, vfHasta);
        filterRequest.addFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFNUM, vfNum);
        return filterRequest;
    }
}
