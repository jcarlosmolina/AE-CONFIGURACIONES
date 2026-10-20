package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_agrproductot_oid")
    private ProductosOid grupoAsociadocrearpagrProductoT;
    
    @JsonIgnore
    private Productos grupoAsociadocrearpagrProductoTInstance;

    @JsonProperty("p_agrplant_oid")
    private PlanesOid grupoAsociadocrearpagrPlanT;
    
    @JsonIgnore
    private Planes grupoAsociadocrearpagrPlanTInstance;

    @JsonProperty("p_atrcodigolegal")
    private String grupoAsociadocrearpatrCodigoLegal;

    @JsonProperty("p_atrdescripcion")
    private String grupoAsociadocrearpatrDescripcion;

    @JsonProperty("p_atrfinivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadocrearpatrFIniVigencia;

    @JsonProperty("p_atrffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadocrearpatrFFinVigencia;

    @JsonProperty("p_atrestado")
    private Long grupoAsociadocrearpatrEstado;

    @JsonProperty("p_atrdisminucioncarencia")
    private Long grupoAsociadocrearpatrDisminucionCarencia;

    @JsonProperty("p_atrcodtarifacore")
    private String grupoAsociadocrearpatrCodTarifaCORE;

    @JsonProperty("p_atrcodigo")
    private Long grupoAsociadocrearpatrCodigo;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadocrearpagrProductoT();
        checkGrupoAsociadocrearpagrPlanT();
        checkGrupoAsociadocrearpatrCodigoLegal();
        checkGrupoAsociadocrearpatrDescripcion();
        checkGrupoAsociadocrearpatrFIniVigencia();
        checkGrupoAsociadocrearpatrFFinVigencia();
        checkGrupoAsociadocrearpatrEstado();
        checkGrupoAsociadocrearpatrDisminucionCarencia();
        checkGrupoAsociadocrearpatrCodigo();
    }

    @JsonIgnore
    public ProductosOid getGrupoAsociadocrearpagrProductoT() {
        return this.grupoAsociadocrearpagrProductoT;
    }
    
    public void setGrupoAsociadocrearpagrProductoT(ProductosOid grupoAsociadocrearpagrProductoT) {
        this.grupoAsociadocrearpagrProductoT = grupoAsociadocrearpagrProductoT;
    }

    @JsonIgnore
    public Productos getGrupoAsociadocrearpagrProductoTInstance() {
        return this.grupoAsociadocrearpagrProductoTInstance;
    }
    
    public void setGrupoAsociadocrearpagrProductoTInstance(Productos grupoAsociadocrearpagrProductoT) {
        this.grupoAsociadocrearpagrProductoTInstance = grupoAsociadocrearpagrProductoT;
        this.grupoAsociadocrearpagrProductoT = grupoAsociadocrearpagrProductoT != null ? grupoAsociadocrearpagrProductoT.getOid() : null;
    }

    private void checkGrupoAsociadocrearpagrProductoT() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpagrProductoTInstance() == null || this.getGrupoAsociadocrearpagrProductoTInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPAGRPRODUCTOT, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPAGRPRODUCTOT);
        }
    }

    @JsonIgnore
    public PlanesOid getGrupoAsociadocrearpagrPlanT() {
        return this.grupoAsociadocrearpagrPlanT;
    }
    
    public void setGrupoAsociadocrearpagrPlanT(PlanesOid grupoAsociadocrearpagrPlanT) {
        this.grupoAsociadocrearpagrPlanT = grupoAsociadocrearpagrPlanT;
    }

    @JsonIgnore
    public Planes getGrupoAsociadocrearpagrPlanTInstance() {
        return this.grupoAsociadocrearpagrPlanTInstance;
    }
    
    public void setGrupoAsociadocrearpagrPlanTInstance(Planes grupoAsociadocrearpagrPlanT) {
        this.grupoAsociadocrearpagrPlanTInstance = grupoAsociadocrearpagrPlanT;
        this.grupoAsociadocrearpagrPlanT = grupoAsociadocrearpagrPlanT != null ? grupoAsociadocrearpagrPlanT.getOid() : null;
    }

    private void checkGrupoAsociadocrearpagrPlanT() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpagrPlanTInstance() == null || this.getGrupoAsociadocrearpagrPlanTInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPAGRPLANT, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPAGRPLANT);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadocrearpatrCodigoLegal() {
        return this.grupoAsociadocrearpatrCodigoLegal;
    }
    
    public void setGrupoAsociadocrearpatrCodigoLegal(String grupoAsociadocrearpatrCodigoLegal) {
        this.grupoAsociadocrearpatrCodigoLegal = grupoAsociadocrearpatrCodigoLegal;
    }

    private void checkGrupoAsociadocrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRCODIGOLEGAL, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadocrearpatrDescripcion() {
        return this.grupoAsociadocrearpatrDescripcion;
    }
    
    public void setGrupoAsociadocrearpatrDescripcion(String grupoAsociadocrearpatrDescripcion) {
        this.grupoAsociadocrearpatrDescripcion = grupoAsociadocrearpatrDescripcion;
    }

    private void checkGrupoAsociadocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRDESCRIPCION, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadocrearpatrFIniVigencia() {
        return this.grupoAsociadocrearpatrFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadocrearpatrFIniVigencia(Date grupoAsociadocrearpatrFIniVigencia) {
        this.grupoAsociadocrearpatrFIniVigencia = grupoAsociadocrearpatrFIniVigencia;
    }

    private void checkGrupoAsociadocrearpatrFIniVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrFIniVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRFINIVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadocrearpatrFFinVigencia() {
        return this.grupoAsociadocrearpatrFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadocrearpatrFFinVigencia(Date grupoAsociadocrearpatrFFinVigencia) {
        this.grupoAsociadocrearpatrFFinVigencia = grupoAsociadocrearpatrFFinVigencia;
    }

    private void checkGrupoAsociadocrearpatrFFinVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrFFinVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRFFINVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getGrupoAsociadocrearpatrEstado() {
        return this.grupoAsociadocrearpatrEstado;
    }
    
    public void setGrupoAsociadocrearpatrEstado(Long grupoAsociadocrearpatrEstado) {
        this.grupoAsociadocrearpatrEstado = grupoAsociadocrearpatrEstado;
    }

    private void checkGrupoAsociadocrearpatrEstado() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrEstado() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRESTADO, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRESTADO);
        }
    }

    @JsonIgnore
    public Long getGrupoAsociadocrearpatrDisminucionCarencia() {
        return this.grupoAsociadocrearpatrDisminucionCarencia;
    }
    
    public void setGrupoAsociadocrearpatrDisminucionCarencia(Long grupoAsociadocrearpatrDisminucionCarencia) {
        this.grupoAsociadocrearpatrDisminucionCarencia = grupoAsociadocrearpatrDisminucionCarencia;
    }

    private void checkGrupoAsociadocrearpatrDisminucionCarencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrDisminucionCarencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRDISMINUCIONCARENCIA, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRDISMINUCIONCARENCIA);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadocrearpatrCodTarifaCORE() {
        return this.grupoAsociadocrearpatrCodTarifaCORE;
    }
    
    public void setGrupoAsociadocrearpatrCodTarifaCORE(String grupoAsociadocrearpatrCodTarifaCORE) {
        this.grupoAsociadocrearpatrCodTarifaCORE = grupoAsociadocrearpatrCodTarifaCORE;
    }

    @JsonIgnore
    public Long getGrupoAsociadocrearpatrCodigo() {
        return this.grupoAsociadocrearpatrCodigo;
    }
    
    public void setGrupoAsociadocrearpatrCodigo(Long grupoAsociadocrearpatrCodigo) {
        this.grupoAsociadocrearpatrCodigo = grupoAsociadocrearpatrCodigo;
    }

    private void checkGrupoAsociadocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getGrupoAsociadocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CREAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CREAR_GRUPOASOCIADOCREARPATRCODIGO, GrupoAsociadoConstants.SERV_ALIAS_CREAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CREAR_GRUPOASOCIADOCREARPATRCODIGO);
        }
    }
}
