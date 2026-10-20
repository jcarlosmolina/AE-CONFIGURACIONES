package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.filter;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.keralty.aeconfiguraciones.global.LogContSinDocSolConstants;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;

public class FLogContSinDocSol {

    @JsonProperty("vfdesde")
    private Date vfDesde;

    @JsonProperty("vfhasta")
    private Date vfHasta;

    @JsonProperty("vfnumcont")
    private String vfNumCont;

    public FLogContSinDocSol() {
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
    public String getVfNumCont() {
        return this.vfNumCont;
    }
    
    public void setVfNumCont(String vfNumCont) {
        this.vfNumCont = vfNumCont;
    }

    public FilterRequest buildFilterRequest() {
        FilterRequest filterRequest = new FilterRequest();
        filterRequest.setName(LogContSinDocSolConstants.FILTER_NAME_FLOGCONTSINDOCSOL);
        filterRequest.addFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFDESDE, vfDesde);
        filterRequest.addFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFHASTA, vfHasta);
        filterRequest.addFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFNUMCONT, vfNumCont);
        return filterRequest;
    }
}
