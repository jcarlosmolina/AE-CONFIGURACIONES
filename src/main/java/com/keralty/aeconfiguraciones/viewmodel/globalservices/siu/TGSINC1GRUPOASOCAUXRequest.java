package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TGSINC1GRUPOASOCAUXRequest {

    @JsonProperty("pt_p_agrproducto_oid")
    private ProductosOid tGSINC1GRUPOASOCAUXptpagrProducto;
    
    @JsonIgnore
    private Productos tGSINC1GRUPOASOCAUXptpagrProductoInstance;

    @JsonProperty("pt_p_atrcodigo")
    private String tGSINC1GRUPOASOCAUXptpatrCodigo;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1GRUPOASOCAUXptpatrCodigoLegal;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1GRUPOASOCAUXptpatrDescripcion;

    @JsonProperty("pt_p_atrfinivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCAUXptpatrFIniVigencia;

    @JsonProperty("pt_p_atrffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCAUXptpatrFFinVigencia;

    @JsonProperty("pt_p_atrestado")
    private Long tGSINC1GRUPOASOCAUXptpatrEstado;

    @JsonProperty("pt_codtarifacore")
    private String tGSINC1GRUPOASOCAUXptCodTarifaCORE;

    @JsonProperty("pt_p_atrcodigoga")
    private Long tGSINC1GRUPOASOCAUXptpatrCodigoGA;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1GRUPOASOCAUXptpagrProducto();
        checkTGSINC1GRUPOASOCAUXptpatrCodigo();
        checkTGSINC1GRUPOASOCAUXptpatrCodigoLegal();
        checkTGSINC1GRUPOASOCAUXptpatrDescripcion();
        checkTGSINC1GRUPOASOCAUXptpatrFIniVigencia();
        checkTGSINC1GRUPOASOCAUXptpatrFFinVigencia();
        checkTGSINC1GRUPOASOCAUXptpatrEstado();
        checkTGSINC1GRUPOASOCAUXptpatrCodigoGA();
    }

    @JsonIgnore
    public ProductosOid getTGSINC1GRUPOASOCAUXptpagrProducto() {
        return this.tGSINC1GRUPOASOCAUXptpagrProducto;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpagrProducto(ProductosOid tGSINC1GRUPOASOCAUXptpagrProducto) {
        this.tGSINC1GRUPOASOCAUXptpagrProducto = tGSINC1GRUPOASOCAUXptpagrProducto;
    }

    @JsonIgnore
    public Productos getTGSINC1GRUPOASOCAUXptpagrProductoInstance() {
        return this.tGSINC1GRUPOASOCAUXptpagrProductoInstance;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpagrProductoInstance(Productos tGSINC1GRUPOASOCAUXptpagrProducto) {
        this.tGSINC1GRUPOASOCAUXptpagrProductoInstance = tGSINC1GRUPOASOCAUXptpagrProducto;
        this.tGSINC1GRUPOASOCAUXptpagrProducto = tGSINC1GRUPOASOCAUXptpagrProducto != null ? tGSINC1GRUPOASOCAUXptpagrProducto.getOid() : null;
    }

    private void checkTGSINC1GRUPOASOCAUXptpagrProducto() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpagrProductoInstance() == null || this.getTGSINC1GRUPOASOCAUXptpagrProductoInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPAGRPRODUCTO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPAGRPRODUCTO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUXptpatrCodigo() {
        return this.tGSINC1GRUPOASOCAUXptpatrCodigo;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpatrCodigo(String tGSINC1GRUPOASOCAUXptpatrCodigo) {
        this.tGSINC1GRUPOASOCAUXptpatrCodigo = tGSINC1GRUPOASOCAUXptpatrCodigo;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUXptpatrCodigoLegal() {
        return this.tGSINC1GRUPOASOCAUXptpatrCodigoLegal;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpatrCodigoLegal(String tGSINC1GRUPOASOCAUXptpatrCodigoLegal) {
        this.tGSINC1GRUPOASOCAUXptpatrCodigoLegal = tGSINC1GRUPOASOCAUXptpatrCodigoLegal;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUXptpatrDescripcion() {
        return this.tGSINC1GRUPOASOCAUXptpatrDescripcion;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpatrDescripcion(String tGSINC1GRUPOASOCAUXptpatrDescripcion) {
        this.tGSINC1GRUPOASOCAUXptpatrDescripcion = tGSINC1GRUPOASOCAUXptpatrDescripcion;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCAUXptpatrFIniVigencia() {
        return this.tGSINC1GRUPOASOCAUXptpatrFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCAUXptpatrFIniVigencia(Date tGSINC1GRUPOASOCAUXptpatrFIniVigencia) {
        this.tGSINC1GRUPOASOCAUXptpatrFIniVigencia = tGSINC1GRUPOASOCAUXptpatrFIniVigencia;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrFIniVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrFIniVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRFINIVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCAUXptpatrFFinVigencia() {
        return this.tGSINC1GRUPOASOCAUXptpatrFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCAUXptpatrFFinVigencia(Date tGSINC1GRUPOASOCAUXptpatrFFinVigencia) {
        this.tGSINC1GRUPOASOCAUXptpatrFFinVigencia = tGSINC1GRUPOASOCAUXptpatrFFinVigencia;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrFFinVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrFFinVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRFFINVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCAUXptpatrEstado() {
        return this.tGSINC1GRUPOASOCAUXptpatrEstado;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpatrEstado(Long tGSINC1GRUPOASOCAUXptpatrEstado) {
        this.tGSINC1GRUPOASOCAUXptpatrEstado = tGSINC1GRUPOASOCAUXptpatrEstado;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrEstado() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrEstado() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRESTADO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRESTADO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCAUXptCodTarifaCORE() {
        return this.tGSINC1GRUPOASOCAUXptCodTarifaCORE;
    }
    
    public void setTGSINC1GRUPOASOCAUXptCodTarifaCORE(String tGSINC1GRUPOASOCAUXptCodTarifaCORE) {
        this.tGSINC1GRUPOASOCAUXptCodTarifaCORE = tGSINC1GRUPOASOCAUXptCodTarifaCORE;
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCAUXptpatrCodigoGA() {
        return this.tGSINC1GRUPOASOCAUXptpatrCodigoGA;
    }
    
    public void setTGSINC1GRUPOASOCAUXptpatrCodigoGA(Long tGSINC1GRUPOASOCAUXptpatrCodigoGA) {
        this.tGSINC1GRUPOASOCAUXptpatrCodigoGA = tGSINC1GRUPOASOCAUXptpatrCodigoGA;
    }

    private void checkTGSINC1GRUPOASOCAUXptpatrCodigoGA() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCAUXptpatrCodigoGA() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGOGA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOCAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOCAUX_TGSINC1GRUPOASOCAUXPTPATRCODIGOGA);
        }
    }
}
