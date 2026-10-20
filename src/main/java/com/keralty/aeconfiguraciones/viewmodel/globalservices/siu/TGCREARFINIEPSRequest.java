package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGCREARFINIEPSRequest {

    @JsonProperty("pt_p_atrtramite")
    private Long tGCREARFINIEPSptpatrTramite;

    @JsonProperty("pt_p_atrtipocontrato")
    private Long tGCREARFINIEPSptpatrTipoContrato;

    @JsonProperty("pt_p_atrdesde")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGCREARFINIEPSptpatrDesde;

    @JsonProperty("pt_p_atrhasta")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGCREARFINIEPSptpatrHasta;

    @JsonProperty("pt_p_atrfechainicioaplicar")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGCREARFINIEPSptpatrFechaInicioAplicar;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGCREARFINIEPSptpatrTramite();
        checkTGCREARFINIEPSptpatrTipoContrato();
        checkTGCREARFINIEPSptpatrDesde();
        checkTGCREARFINIEPSptpatrHasta();
        checkTGCREARFINIEPSptpatrFechaInicioAplicar();
    }

    @JsonIgnore
    public Long getTGCREARFINIEPSptpatrTramite() {
        return this.tGCREARFINIEPSptpatrTramite;
    }
    
    public void setTGCREARFINIEPSptpatrTramite(Long tGCREARFINIEPSptpatrTramite) {
        this.tGCREARFINIEPSptpatrTramite = tGCREARFINIEPSptpatrTramite;
    }

    private void checkTGCREARFINIEPSptpatrTramite() throws NotNullArgumentException {
        if(this.getTGCREARFINIEPSptpatrTramite() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGCREARFINIEPS_TGCREARFINIEPSPTPATRTRAMITE, GlobalServiceConstants.G_SERV_ALIAS_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARFINIEPS_TGCREARFINIEPSPTPATRTRAMITE);
        }
    }

    @JsonIgnore
    public Long getTGCREARFINIEPSptpatrTipoContrato() {
        return this.tGCREARFINIEPSptpatrTipoContrato;
    }
    
    public void setTGCREARFINIEPSptpatrTipoContrato(Long tGCREARFINIEPSptpatrTipoContrato) {
        this.tGCREARFINIEPSptpatrTipoContrato = tGCREARFINIEPSptpatrTipoContrato;
    }

    private void checkTGCREARFINIEPSptpatrTipoContrato() throws NotNullArgumentException {
        if(this.getTGCREARFINIEPSptpatrTipoContrato() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGCREARFINIEPS_TGCREARFINIEPSPTPATRTIPOCONTRATO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARFINIEPS_TGCREARFINIEPSPTPATRTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public Date getTGCREARFINIEPSptpatrDesde() {
        return this.tGCREARFINIEPSptpatrDesde;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGCREARFINIEPSptpatrDesde(Date tGCREARFINIEPSptpatrDesde) {
        this.tGCREARFINIEPSptpatrDesde = tGCREARFINIEPSptpatrDesde;
    }

    private void checkTGCREARFINIEPSptpatrDesde() throws NotNullArgumentException {
        if(this.getTGCREARFINIEPSptpatrDesde() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGCREARFINIEPS_TGCREARFINIEPSPTPATRDESDE, GlobalServiceConstants.G_SERV_ALIAS_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARFINIEPS_TGCREARFINIEPSPTPATRDESDE);
        }
    }

    @JsonIgnore
    public Date getTGCREARFINIEPSptpatrHasta() {
        return this.tGCREARFINIEPSptpatrHasta;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGCREARFINIEPSptpatrHasta(Date tGCREARFINIEPSptpatrHasta) {
        this.tGCREARFINIEPSptpatrHasta = tGCREARFINIEPSptpatrHasta;
    }

    private void checkTGCREARFINIEPSptpatrHasta() throws NotNullArgumentException {
        if(this.getTGCREARFINIEPSptpatrHasta() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGCREARFINIEPS_TGCREARFINIEPSPTPATRHASTA, GlobalServiceConstants.G_SERV_ALIAS_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARFINIEPS_TGCREARFINIEPSPTPATRHASTA);
        }
    }

    @JsonIgnore
    public Date getTGCREARFINIEPSptpatrFechaInicioAplicar() {
        return this.tGCREARFINIEPSptpatrFechaInicioAplicar;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGCREARFINIEPSptpatrFechaInicioAplicar(Date tGCREARFINIEPSptpatrFechaInicioAplicar) {
        this.tGCREARFINIEPSptpatrFechaInicioAplicar = tGCREARFINIEPSptpatrFechaInicioAplicar;
    }

    private void checkTGCREARFINIEPSptpatrFechaInicioAplicar() throws NotNullArgumentException {
        if(this.getTGCREARFINIEPSptpatrFechaInicioAplicar() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ID_TGCREARFINIEPS_TGCREARFINIEPSPTPATRFECHAINICIOAPLICAR, GlobalServiceConstants.G_SERV_ALIAS_TGCREARFINIEPS, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARFINIEPS_TGCREARFINIEPSPTPATRFECHAINICIOAPLICAR);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
