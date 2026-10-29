package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGSINC1GRUPOASOCAUX2Request {

    @JsonProperty("pt_p_agrproducto_oid")
    private ProductosOid tGSINC1GRUPOASOCAUX2ptpagrProducto;
    
    @JsonIgnore
    private Productos tGSINC1GRUPOASOCAUX2ptpagrProductoInstance;

    @JsonProperty("pt_p_agrplan_oid")
    private PlanesOid tGSINC1GRUPOASOCAUX2ptpagrPlan;
    
    @JsonIgnore
    private Planes tGSINC1GRUPOASOCAUX2ptpagrPlanInstance;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1GRUPOASOCAUX2ptpatrDescripcion;

    @JsonProperty("pt_p_atrfinivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia;

    @JsonProperty("pt_p_atrffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia;

    @JsonProperty("pt_p_atrestado")
    private Long tGSINC1GRUPOASOCAUX2ptpatrEstado;

    @JsonProperty("pt_codtarifacore")
    private String tGSINC1GRUPOASOCAUX2ptCodTarifaCORE;

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1GRUPOASOCAUX2ptpatrCodigo;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1GRUPOASOCAUX2ptpagrProducto();
        checkTGSINC1GRUPOASOCAUX2ptpagrPlan();
        checkTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal();
        checkTGSINC1GRUPOASOCAUX2ptpatrDescripcion();
        checkTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia();
        checkTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia();
        checkTGSINC1GRUPOASOCAUX2ptpatrEstado();
        checkTGSINC1GRUPOASOCAUX2ptpatrCodigo();
    }

    @JsonIgnore
    public ProductosOid getTGSINC1GRUPOASOCAUX2ptpagrProducto() {
        return this.tGSINC1GRUPOASOCAUX2ptpagrProducto;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpagrProducto(ProductosOid tGSINC1GRUPOASOCAUX2ptpagrProducto) {
        this.tGSINC1GRUPOASOCAUX2ptpagrProducto = tGSINC1GRUPOASOCAUX2ptpagrProducto;
    }

    @JsonIgnore
    public Productos getTGSINC1GRUPOASOCAUX2ptpagrProductoInstance() {
        return this.tGSINC1GRUPOASOCAUX2ptpagrProductoInstance;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpagrProductoInstance(Productos tGSINC1GRUPOASOCAUX2ptpagrProducto) {
        this.tGSINC1GRUPOASOCAUX2ptpagrProductoInstance = tGSINC1GRUPOASOCAUX2ptpagrProducto;
        this.tGSINC1GRUPOASOCAUX2ptpagrProducto = tGSINC1GRUPOASOCAUX2ptpagrProducto != null ? tGSINC1GRUPOASOCAUX2ptpagrProducto.getOid() : null;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpagrProducto() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpagrProductoInstance() == null || this.getTGSINC1GRUPOASOCAUX2ptpagrProductoInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPAGRPRODUCTO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPAGRPRODUCTO);
        }
    }

    @JsonIgnore
    public PlanesOid getTGSINC1GRUPOASOCAUX2ptpagrPlan() {
        return this.tGSINC1GRUPOASOCAUX2ptpagrPlan;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpagrPlan(PlanesOid tGSINC1GRUPOASOCAUX2ptpagrPlan) {
        this.tGSINC1GRUPOASOCAUX2ptpagrPlan = tGSINC1GRUPOASOCAUX2ptpagrPlan;
    }

    @JsonIgnore
    public Planes getTGSINC1GRUPOASOCAUX2ptpagrPlanInstance() {
        return this.tGSINC1GRUPOASOCAUX2ptpagrPlanInstance;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpagrPlanInstance(Planes tGSINC1GRUPOASOCAUX2ptpagrPlan) {
        this.tGSINC1GRUPOASOCAUX2ptpagrPlanInstance = tGSINC1GRUPOASOCAUX2ptpagrPlan;
        this.tGSINC1GRUPOASOCAUX2ptpagrPlan = tGSINC1GRUPOASOCAUX2ptpagrPlan != null ? tGSINC1GRUPOASOCAUX2ptpagrPlan.getOid() : null;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpagrPlan() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpagrPlanInstance() == null || this.getTGSINC1GRUPOASOCAUX2ptpagrPlanInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPAGRPLAN, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPAGRPLAN);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal(String tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal) {
        this.tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal = tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUX2ptpatrDescripcion() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrDescripcion;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpatrDescripcion(String tGSINC1GRUPOASOCAUX2ptpatrDescripcion) {
        this.tGSINC1GRUPOASOCAUX2ptpatrDescripcion = tGSINC1GRUPOASOCAUX2ptpatrDescripcion;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia(Date tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia) {
        this.tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia = tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRFINIVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia(Date tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia) {
        this.tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia = tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRFFINVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCAUX2ptpatrEstado() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrEstado;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpatrEstado(Long tGSINC1GRUPOASOCAUX2ptpatrEstado) {
        this.tGSINC1GRUPOASOCAUX2ptpatrEstado = tGSINC1GRUPOASOCAUX2ptpatrEstado;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrEstado() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrEstado() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRESTADO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRESTADO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUX2ptCodTarifaCORE() {
        return this.tGSINC1GRUPOASOCAUX2ptCodTarifaCORE;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptCodTarifaCORE(String tGSINC1GRUPOASOCAUX2ptCodTarifaCORE) {
        this.tGSINC1GRUPOASOCAUX2ptCodTarifaCORE = tGSINC1GRUPOASOCAUX2ptCodTarifaCORE;
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCAUX2ptpatrCodigo() {
        return this.tGSINC1GRUPOASOCAUX2ptpatrCodigo;
    }
    
    public void setTGSINC1GRUPOASOCAUX2ptpatrCodigo(Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) {
        this.tGSINC1GRUPOASOCAUX2ptpatrCodigo = tGSINC1GRUPOASOCAUX2ptpatrCodigo;
    }

    private void checkTGSINC1GRUPOASOCAUX2ptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUX2ptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX2, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX2_TGSINC1GRUPOASOCAUX2PTPATRCODIGO);
        }
    }
}
