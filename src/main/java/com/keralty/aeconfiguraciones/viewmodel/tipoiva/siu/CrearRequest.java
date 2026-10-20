package com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_agrproductot_json")
    private String tipoIVAcrearpagrProductoT;
    
    @JsonIgnore
    private Productos tipoIVAcrearpagrProductoTInstance;

    @JsonProperty("p_agrplant_json")
    private String tipoIVAcrearpagrPlanT;
    
    @JsonIgnore
    private Planes tipoIVAcrearpagrPlanTInstance;

    @JsonProperty("p_atrdescripcion")
    private String tipoIVAcrearpatrDescripcion;

    @JsonProperty("p_atrporcentaje")
    private Double tipoIVAcrearpatrPorcentaje;

    @JsonProperty("p_atrfechainicio")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tipoIVAcrearpatrFechaInicio;

    @JsonProperty("p_atrfechafin")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tipoIVAcrearpatrFechaFin;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoIVAcrearpagrProductoT();
        checkTipoIVAcrearpagrPlanT();
        checkTipoIVAcrearpatrDescripcion();
        checkTipoIVAcrearpatrPorcentaje();
        checkTipoIVAcrearpatrFechaInicio();
        checkTipoIVAcrearpatrFechaFin();
    }

    @JsonIgnore
    public String getTipoIVAcrearpagrProductoT() {
        return this.tipoIVAcrearpagrProductoT;
    }
    
    public void setTipoIVAcrearpagrProductoT(String tipoIVAcrearpagrProductoT) {
        this.tipoIVAcrearpagrProductoT = tipoIVAcrearpagrProductoT;
    }

    @JsonIgnore
    public Productos getTipoIVAcrearpagrProductoTInstance() {
        return this.tipoIVAcrearpagrProductoTInstance;
    }
    
    public void setTipoIVAcrearpagrProductoTInstance(Productos tipoIVAcrearpagrProductoT) {
        this.tipoIVAcrearpagrProductoTInstance = tipoIVAcrearpagrProductoT;
        this.tipoIVAcrearpagrProductoT = tipoIVAcrearpagrProductoT != null ? tipoIVAcrearpagrProductoT.getOid().toJson() : "";
    }

    private void checkTipoIVAcrearpagrProductoT() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpagrProductoTInstance() == null || this.getTipoIVAcrearpagrProductoTInstance().isNull()) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPAGRPRODUCTOT, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPAGRPRODUCTOT);
        }
    }

    @JsonIgnore
    public String getTipoIVAcrearpagrPlanT() {
        return this.tipoIVAcrearpagrPlanT;
    }
    
    public void setTipoIVAcrearpagrPlanT(String tipoIVAcrearpagrPlanT) {
        this.tipoIVAcrearpagrPlanT = tipoIVAcrearpagrPlanT;
    }

    @JsonIgnore
    public Planes getTipoIVAcrearpagrPlanTInstance() {
        return this.tipoIVAcrearpagrPlanTInstance;
    }
    
    public void setTipoIVAcrearpagrPlanTInstance(Planes tipoIVAcrearpagrPlanT) {
        this.tipoIVAcrearpagrPlanTInstance = tipoIVAcrearpagrPlanT;
        this.tipoIVAcrearpagrPlanT = tipoIVAcrearpagrPlanT != null ? tipoIVAcrearpagrPlanT.getOid().toJson() : "";
    }

    private void checkTipoIVAcrearpagrPlanT() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpagrPlanTInstance() == null || this.getTipoIVAcrearpagrPlanTInstance().isNull()) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPAGRPLANT, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPAGRPLANT);
        }
    }

    @JsonIgnore
    public String getTipoIVAcrearpatrDescripcion() {
        return this.tipoIVAcrearpatrDescripcion;
    }
    
    public void setTipoIVAcrearpatrDescripcion(String tipoIVAcrearpatrDescripcion) {
        this.tipoIVAcrearpatrDescripcion = tipoIVAcrearpatrDescripcion;
    }

    private void checkTipoIVAcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPATRDESCRIPCION, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Double getTipoIVAcrearpatrPorcentaje() {
        return this.tipoIVAcrearpatrPorcentaje;
    }
    
    public void setTipoIVAcrearpatrPorcentaje(Double tipoIVAcrearpatrPorcentaje) {
        this.tipoIVAcrearpatrPorcentaje = tipoIVAcrearpatrPorcentaje;
    }

    private void checkTipoIVAcrearpatrPorcentaje() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpatrPorcentaje() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPATRPORCENTAJE, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPATRPORCENTAJE);
        }
    }

    @JsonIgnore
    public Date getTipoIVAcrearpatrFechaInicio() {
        return this.tipoIVAcrearpatrFechaInicio;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTipoIVAcrearpatrFechaInicio(Date tipoIVAcrearpatrFechaInicio) {
        this.tipoIVAcrearpatrFechaInicio = tipoIVAcrearpatrFechaInicio;
    }

    private void checkTipoIVAcrearpatrFechaInicio() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpatrFechaInicio() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPATRFECHAINICIO, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPATRFECHAINICIO);
        }
    }

    @JsonIgnore
    public Date getTipoIVAcrearpatrFechaFin() {
        return this.tipoIVAcrearpatrFechaFin;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTipoIVAcrearpatrFechaFin(Date tipoIVAcrearpatrFechaFin) {
        this.tipoIVAcrearpatrFechaFin = tipoIVAcrearpatrFechaFin;
    }

    private void checkTipoIVAcrearpatrFechaFin() throws NotNullArgumentException {
        if(this.getTipoIVAcrearpatrFechaFin() == null) {
            throw new NotNullArgumentException(TipoIVAConstants.SERV_ID_CREAR, TipoIVAConstants.CLASS_ID, TipoIVAConstants.ARG_ID_CREAR_TIPOIVACREARPATRFECHAFIN, TipoIVAConstants.SERV_ALIAS_CREAR, TipoIVAConstants.CLASS_ALIAS, TipoIVAConstants.ARG_ALIAS_CREAR_TIPOIVACREARPATRFECHAFIN);
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
