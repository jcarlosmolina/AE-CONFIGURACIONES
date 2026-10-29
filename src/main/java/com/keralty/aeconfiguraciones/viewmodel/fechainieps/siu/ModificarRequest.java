package com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.oid.FechaIniEPSOid;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class ModificarRequest {

    @JsonProperty("p_thisfechainieps_oid")
    private FechaIniEPSOid fechaIniEPSmodificarpthisFechaIniEPS;
    
    @JsonIgnore
    private FechaIniEPS fechaIniEPSmodificarpthisFechaIniEPSInstance;

    @JsonProperty("p_desde")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPSmodificarpDesde;

    @JsonProperty("p_hasta")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPSmodificarpHasta;

    @JsonProperty("p_fechainicioaplicar")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date fechaIniEPSmodificarpFechaInicioAplicar;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkFechaIniEPSmodificarpthisFechaIniEPS();
        checkFechaIniEPSmodificarpDesde();
        checkFechaIniEPSmodificarpHasta();
        checkFechaIniEPSmodificarpFechaInicioAplicar();
    }

    @JsonIgnore
    public FechaIniEPSOid getFechaIniEPSmodificarpthisFechaIniEPS() {
        return this.fechaIniEPSmodificarpthisFechaIniEPS;
    }
    
    public void setFechaIniEPSmodificarpthisFechaIniEPS(FechaIniEPSOid fechaIniEPSmodificarpthisFechaIniEPS) {
        this.fechaIniEPSmodificarpthisFechaIniEPS = fechaIniEPSmodificarpthisFechaIniEPS;
    }

    @JsonIgnore
    public FechaIniEPS getFechaIniEPSmodificarpthisFechaIniEPSInstance() {
        return this.fechaIniEPSmodificarpthisFechaIniEPSInstance;
    }
    
    public void setFechaIniEPSmodificarpthisFechaIniEPSInstance(FechaIniEPS fechaIniEPSmodificarpthisFechaIniEPS) {
        this.fechaIniEPSmodificarpthisFechaIniEPSInstance = fechaIniEPSmodificarpthisFechaIniEPS;
        this.fechaIniEPSmodificarpthisFechaIniEPS = fechaIniEPSmodificarpthisFechaIniEPS != null ? fechaIniEPSmodificarpthisFechaIniEPS.getOid() : null;
    }

    private void checkFechaIniEPSmodificarpthisFechaIniEPS() throws NotNullArgumentException {
        if(this.getFechaIniEPSmodificarpthisFechaIniEPSInstance() == null || this.getFechaIniEPSmodificarpthisFechaIniEPSInstance().isNull()) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_MODIFICAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_MODIFICAR_FECHAINIEPSMODIFICARPTHISFECHAINIEPS, FechaIniEPSConstants.SERV_ALIAS_MODIFICAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_MODIFICAR_FECHAINIEPSMODIFICARPTHISFECHAINIEPS);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPSmodificarpDesde() {
        return this.fechaIniEPSmodificarpDesde;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPSmodificarpDesde(Date fechaIniEPSmodificarpDesde) {
        this.fechaIniEPSmodificarpDesde = fechaIniEPSmodificarpDesde;
    }

    private void checkFechaIniEPSmodificarpDesde() throws NotNullArgumentException {
        if(this.getFechaIniEPSmodificarpDesde() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_MODIFICAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_MODIFICAR_FECHAINIEPSMODIFICARPDESDE, FechaIniEPSConstants.SERV_ALIAS_MODIFICAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_MODIFICAR_FECHAINIEPSMODIFICARPDESDE);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPSmodificarpHasta() {
        return this.fechaIniEPSmodificarpHasta;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPSmodificarpHasta(Date fechaIniEPSmodificarpHasta) {
        this.fechaIniEPSmodificarpHasta = fechaIniEPSmodificarpHasta;
    }

    private void checkFechaIniEPSmodificarpHasta() throws NotNullArgumentException {
        if(this.getFechaIniEPSmodificarpHasta() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_MODIFICAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_MODIFICAR_FECHAINIEPSMODIFICARPHASTA, FechaIniEPSConstants.SERV_ALIAS_MODIFICAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_MODIFICAR_FECHAINIEPSMODIFICARPHASTA);
        }
    }

    @JsonIgnore
    public Date getFechaIniEPSmodificarpFechaInicioAplicar() {
        return this.fechaIniEPSmodificarpFechaInicioAplicar;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setFechaIniEPSmodificarpFechaInicioAplicar(Date fechaIniEPSmodificarpFechaInicioAplicar) {
        this.fechaIniEPSmodificarpFechaInicioAplicar = fechaIniEPSmodificarpFechaInicioAplicar;
    }

    private void checkFechaIniEPSmodificarpFechaInicioAplicar() throws NotNullArgumentException {
        if(this.getFechaIniEPSmodificarpFechaInicioAplicar() == null) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_MODIFICAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_MODIFICAR_FECHAINIEPSMODIFICARPFECHAINICIOAPLICAR, FechaIniEPSConstants.SERV_ALIAS_MODIFICAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_MODIFICAR_FECHAINIEPSMODIFICARPFECHAINICIOAPLICAR);
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
