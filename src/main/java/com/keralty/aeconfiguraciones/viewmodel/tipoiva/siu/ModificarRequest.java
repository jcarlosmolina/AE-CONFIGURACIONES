package com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIVAOid;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class ModificarRequest {

    @JsonProperty("p_thistipoiva_oid")
    private TipoIVAOid tipoIVAmodificarpthisTipoIVA;
    
    @JsonIgnore
    private TipoIVA tipoIVAmodificarpthisTipoIVAInstance;

    @JsonProperty("p_descripcion")
    private String tipoIVAmodificarpDescripcion;

    @JsonProperty("p_porcentaje")
    private Double tipoIVAmodificarpPorcentaje;

    @JsonProperty("p_fechainicio")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tipoIVAmodificarpFechaInicio;

    @JsonProperty("p_fechafin")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tipoIVAmodificarpFechaFin;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoIVAmodificarpthisTipoIVA();
        checkTipoIVAmodificarpDescripcion();
        checkTipoIVAmodificarpPorcentaje();
        checkTipoIVAmodificarpFechaInicio();
        checkTipoIVAmodificarpFechaFin();
    }

    @JsonIgnore
    public TipoIVAOid getTipoIVAmodificarpthisTipoIVA() {
        return this.tipoIVAmodificarpthisTipoIVA;
    }
    
    public void setTipoIVAmodificarpthisTipoIVA(TipoIVAOid tipoIVAmodificarpthisTipoIVA) {
        this.tipoIVAmodificarpthisTipoIVA = tipoIVAmodificarpthisTipoIVA;
    }

    @JsonIgnore
    public TipoIVA getTipoIVAmodificarpthisTipoIVAInstance() {
        return this.tipoIVAmodificarpthisTipoIVAInstance;
    }
    
    public void setTipoIVAmodificarpthisTipoIVAInstance(TipoIVA tipoIVAmodificarpthisTipoIVA) {
        this.tipoIVAmodificarpthisTipoIVAInstance = tipoIVAmodificarpthisTipoIVA;
        this.tipoIVAmodificarpthisTipoIVA = tipoIVAmodificarpthisTipoIVA != null ? tipoIVAmodificarpthisTipoIVA.getOid() : null;
    }

    private void checkTipoIVAmodificarpthisTipoIVA() throws NotNullArgumentException {
        if(this.getTipoIVAmodificarpthisTipoIVAInstance() == null || this.getTipoIVAmodificarpthisTipoIVAInstance().isNull()) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_MODIFICAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA, TipoIVAConstants.SERV_ALIAS_MODIFICAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_MODIFICAR_TIPOIVAMODIFICARPTHISTIPOIVA);
        }
    }

    @JsonIgnore
    public String getTipoIVAmodificarpDescripcion() {
        return this.tipoIVAmodificarpDescripcion;
    }
    
    public void setTipoIVAmodificarpDescripcion(String tipoIVAmodificarpDescripcion) {
        this.tipoIVAmodificarpDescripcion = tipoIVAmodificarpDescripcion;
    }

    private void checkTipoIVAmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoIVAmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_MODIFICAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_MODIFICAR_TIPOIVAMODIFICARPDESCRIPCION, TipoIVAConstants.SERV_ALIAS_MODIFICAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_MODIFICAR_TIPOIVAMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Double getTipoIVAmodificarpPorcentaje() {
        return this.tipoIVAmodificarpPorcentaje;
    }
    
    public void setTipoIVAmodificarpPorcentaje(Double tipoIVAmodificarpPorcentaje) {
        this.tipoIVAmodificarpPorcentaje = tipoIVAmodificarpPorcentaje;
    }

    private void checkTipoIVAmodificarpPorcentaje() throws NotNullArgumentException {
        if(this.getTipoIVAmodificarpPorcentaje() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_MODIFICAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_MODIFICAR_TIPOIVAMODIFICARPPORCENTAJE, TipoIVAConstants.SERV_ALIAS_MODIFICAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_MODIFICAR_TIPOIVAMODIFICARPPORCENTAJE);
        }
    }

    @JsonIgnore
    public Date getTipoIVAmodificarpFechaInicio() {
        return this.tipoIVAmodificarpFechaInicio;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTipoIVAmodificarpFechaInicio(Date tipoIVAmodificarpFechaInicio) {
        this.tipoIVAmodificarpFechaInicio = tipoIVAmodificarpFechaInicio;
    }

    private void checkTipoIVAmodificarpFechaInicio() throws NotNullArgumentException {
        if(this.getTipoIVAmodificarpFechaInicio() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_MODIFICAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_MODIFICAR_TIPOIVAMODIFICARPFECHAINICIO, TipoIVAConstants.SERV_ALIAS_MODIFICAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_MODIFICAR_TIPOIVAMODIFICARPFECHAINICIO);
        }
    }

    @JsonIgnore
    public Date getTipoIVAmodificarpFechaFin() {
        return this.tipoIVAmodificarpFechaFin;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTipoIVAmodificarpFechaFin(Date tipoIVAmodificarpFechaFin) {
        this.tipoIVAmodificarpFechaFin = tipoIVAmodificarpFechaFin;
    }

    private void checkTipoIVAmodificarpFechaFin() throws NotNullArgumentException {
        if(this.getTipoIVAmodificarpFechaFin() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_MODIFICAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_MODIFICAR_TIPOIVAMODIFICARPFECHAFIN, TipoIVAConstants.SERV_ALIAS_MODIFICAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_MODIFICAR_TIPOIVAMODIFICARPFECHAFIN);
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
