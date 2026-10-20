package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.oid.FechaIniEPSOid;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGMODFINIEPSRequest {

    @JsonProperty("pt_p_thisfechainieps_oid")
    private FechaIniEPSOid tGMODFINIEPSptpthisFechaIniEPS;
    
    @JsonIgnore
    private FechaIniEPS tGMODFINIEPSptpthisFechaIniEPSInstance;

    @JsonProperty("pt_p_desde")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGMODFINIEPSptpDesde;

    @JsonProperty("pt_p_hasta")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGMODFINIEPSptpHasta;

    @JsonProperty("pt_p_fechainicioaplicar")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGMODFINIEPSptpFechaInicioAplicar;

    @JsonProperty("pttratime")
    private Long tGMODFINIEPSptTratime;

    @JsonProperty("pttipocontrato")
    private Long tGMODFINIEPSptTipoContrato;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGMODFINIEPSptpthisFechaIniEPS();
        checkTGMODFINIEPSptpDesde();
        checkTGMODFINIEPSptpHasta();
        checkTGMODFINIEPSptpFechaInicioAplicar();
    }

    @JsonIgnore
    public FechaIniEPSOid getTGMODFINIEPSptpthisFechaIniEPS() {
        return this.tGMODFINIEPSptpthisFechaIniEPS;
    }
    
    public void setTGMODFINIEPSptpthisFechaIniEPS(FechaIniEPSOid tGMODFINIEPSptpthisFechaIniEPS) {
        this.tGMODFINIEPSptpthisFechaIniEPS = tGMODFINIEPSptpthisFechaIniEPS;
    }

    @JsonIgnore
    public FechaIniEPS getTGMODFINIEPSptpthisFechaIniEPSInstance() {
        return this.tGMODFINIEPSptpthisFechaIniEPSInstance;
    }
    
    public void setTGMODFINIEPSptpthisFechaIniEPSInstance(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS) {
        this.tGMODFINIEPSptpthisFechaIniEPSInstance = tGMODFINIEPSptpthisFechaIniEPS;
        this.tGMODFINIEPSptpthisFechaIniEPS = tGMODFINIEPSptpthisFechaIniEPS != null ? tGMODFINIEPSptpthisFechaIniEPS.getOid() : null;
    }

    private void checkTGMODFINIEPSptpthisFechaIniEPS() throws NotNullArgumentException {
        if(this.getTGMODFINIEPSptpthisFechaIniEPSInstance() == null || this.getTGMODFINIEPSptpthisFechaIniEPSInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGMODFINIEPS_TGMODFINIEPSPTPTHISFECHAINIEPS, GlobalServiceConstants.G_SERV_ALIAS_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGMODFINIEPS_TGMODFINIEPSPTPTHISFECHAINIEPS);
        }
    }

    @JsonIgnore
    public Date getTGMODFINIEPSptpDesde() {
        return this.tGMODFINIEPSptpDesde;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGMODFINIEPSptpDesde(Date tGMODFINIEPSptpDesde) {
        this.tGMODFINIEPSptpDesde = tGMODFINIEPSptpDesde;
    }

    private void checkTGMODFINIEPSptpDesde() throws NotNullArgumentException {
        if(this.getTGMODFINIEPSptpDesde() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGMODFINIEPS_TGMODFINIEPSPTPDESDE, GlobalServiceConstants.G_SERV_ALIAS_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGMODFINIEPS_TGMODFINIEPSPTPDESDE);
        }
    }

    @JsonIgnore
    public Date getTGMODFINIEPSptpHasta() {
        return this.tGMODFINIEPSptpHasta;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGMODFINIEPSptpHasta(Date tGMODFINIEPSptpHasta) {
        this.tGMODFINIEPSptpHasta = tGMODFINIEPSptpHasta;
    }

    private void checkTGMODFINIEPSptpHasta() throws NotNullArgumentException {
        if(this.getTGMODFINIEPSptpHasta() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGMODFINIEPS_TGMODFINIEPSPTPHASTA, GlobalServiceConstants.G_SERV_ALIAS_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGMODFINIEPS_TGMODFINIEPSPTPHASTA);
        }
    }

    @JsonIgnore
    public Date getTGMODFINIEPSptpFechaInicioAplicar() {
        return this.tGMODFINIEPSptpFechaInicioAplicar;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGMODFINIEPSptpFechaInicioAplicar(Date tGMODFINIEPSptpFechaInicioAplicar) {
        this.tGMODFINIEPSptpFechaInicioAplicar = tGMODFINIEPSptpFechaInicioAplicar;
    }

    private void checkTGMODFINIEPSptpFechaInicioAplicar() throws NotNullArgumentException {
        if(this.getTGMODFINIEPSptpFechaInicioAplicar() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGMODFINIEPS_TGMODFINIEPSPTPFECHAINICIOAPLICAR, GlobalServiceConstants.G_SERV_ALIAS_TGMODFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGMODFINIEPS_TGMODFINIEPSPTPFECHAINICIOAPLICAR);
        }
    }

    @JsonIgnore
    public Long getTGMODFINIEPSptTratime() {
        return this.tGMODFINIEPSptTratime;
    }
    
    public void setTGMODFINIEPSptTratime(Long tGMODFINIEPSptTratime) {
        this.tGMODFINIEPSptTratime = tGMODFINIEPSptTratime;
    }

    @JsonIgnore
    public Long getTGMODFINIEPSptTipoContrato() {
        return this.tGMODFINIEPSptTipoContrato;
    }
    
    public void setTGMODFINIEPSptTipoContrato(Long tGMODFINIEPSptTipoContrato) {
        this.tGMODFINIEPSptTipoContrato = tGMODFINIEPSptTipoContrato;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
