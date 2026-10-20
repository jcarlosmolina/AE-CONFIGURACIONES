package com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_atrtramite")
    private Long fechaIniEPScrearpatrTramite;

    @JsonProperty("p_atrtipocontrato")
    private Long fechaIniEPScrearpatrTipoContrato;

    @JsonProperty("p_atrdesde")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPScrearpatrDesde;

    @JsonProperty("p_atrhasta")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPScrearpatrHasta;

    @JsonProperty("p_atrfechainicioaplicar")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPScrearpatrFechaInicioAplicar;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkFechaIniEPScrearpatrTramite();
        checkFechaIniEPScrearpatrTipoContrato();
        checkFechaIniEPScrearpatrDesde();
        checkFechaIniEPScrearpatrHasta();
        checkFechaIniEPScrearpatrFechaInicioAplicar();
    }

    @JsonIgnore
    public Long getFechaIniEPScrearpatrTramite() {
        return this.fechaIniEPScrearpatrTramite;
    }
    
    public void setFechaIniEPScrearpatrTramite(Long fechaIniEPScrearpatrTramite) {
        this.fechaIniEPScrearpatrTramite = fechaIniEPScrearpatrTramite;
    }

    private void checkFechaIniEPScrearpatrTramite() throws NotNullArgumentException {
        if(this.getFechaIniEPScrearpatrTramite() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_CREAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_CREAR_FECHAINIEPSCREARPATRTRAMITE, FechaIniEPSConstants.SERV_ALIAS_CREAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_CREAR_FECHAINIEPSCREARPATRTRAMITE);
        }
    }

    @JsonIgnore
    public Long getFechaIniEPScrearpatrTipoContrato() {
        return this.fechaIniEPScrearpatrTipoContrato;
    }
    
    public void setFechaIniEPScrearpatrTipoContrato(Long fechaIniEPScrearpatrTipoContrato) {
        this.fechaIniEPScrearpatrTipoContrato = fechaIniEPScrearpatrTipoContrato;
    }

    private void checkFechaIniEPScrearpatrTipoContrato() throws NotNullArgumentException {
        if(this.getFechaIniEPScrearpatrTipoContrato() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_CREAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_CREAR_FECHAINIEPSCREARPATRTIPOCONTRATO, FechaIniEPSConstants.SERV_ALIAS_CREAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_CREAR_FECHAINIEPSCREARPATRTIPOCONTRATO);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPScrearpatrDesde() {
        return this.fechaIniEPScrearpatrDesde;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPScrearpatrDesde(Date fechaIniEPScrearpatrDesde) {
        this.fechaIniEPScrearpatrDesde = fechaIniEPScrearpatrDesde;
    }

    private void checkFechaIniEPScrearpatrDesde() throws NotNullArgumentException {
        if(this.getFechaIniEPScrearpatrDesde() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_CREAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_CREAR_FECHAINIEPSCREARPATRDESDE, FechaIniEPSConstants.SERV_ALIAS_CREAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_CREAR_FECHAINIEPSCREARPATRDESDE);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPScrearpatrHasta() {
        return this.fechaIniEPScrearpatrHasta;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPScrearpatrHasta(Date fechaIniEPScrearpatrHasta) {
        this.fechaIniEPScrearpatrHasta = fechaIniEPScrearpatrHasta;
    }

    private void checkFechaIniEPScrearpatrHasta() throws NotNullArgumentException {
        if(this.getFechaIniEPScrearpatrHasta() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_CREAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_CREAR_FECHAINIEPSCREARPATRHASTA, FechaIniEPSConstants.SERV_ALIAS_CREAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_CREAR_FECHAINIEPSCREARPATRHASTA);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPScrearpatrFechaInicioAplicar() {
        return this.fechaIniEPScrearpatrFechaInicioAplicar;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPScrearpatrFechaInicioAplicar(Date fechaIniEPScrearpatrFechaInicioAplicar) {
        this.fechaIniEPScrearpatrFechaInicioAplicar = fechaIniEPScrearpatrFechaInicioAplicar;
    }

    private void checkFechaIniEPScrearpatrFechaInicioAplicar() throws NotNullArgumentException {
        if(this.getFechaIniEPScrearpatrFechaInicioAplicar() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_CREAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_CREAR_FECHAINIEPSCREARPATRFECHAINICIOAPLICAR, FechaIniEPSConstants.SERV_ALIAS_CREAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_CREAR_FECHAINIEPSCREARPATRFECHAINICIOAPLICAR);
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
