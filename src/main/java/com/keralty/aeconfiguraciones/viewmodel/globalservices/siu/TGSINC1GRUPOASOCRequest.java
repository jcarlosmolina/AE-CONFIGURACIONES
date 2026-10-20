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

public class TGSINC1GRUPOASOCRequest {

    @JsonProperty("pt_p_atrnumero")
    private String tGSINC1GRUPOASOCptpatrNumero;

    @JsonProperty("pt_p_atrcodigo")
    private String tGSINC1GRUPOASOCptpatrCodigo;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1GRUPOASOCptpatrCodigoLegal;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1GRUPOASOCptpatrDescripcion;

    @JsonProperty("pt_p_atrfinivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCptpatrFIniVigencia;

    @JsonProperty("pt_p_atrffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date tGSINC1GRUPOASOCptpatrFFinVigencia;

    @JsonProperty("pt_p_atrestado")
    private Long tGSINC1GRUPOASOCptpatrEstado;

    @JsonProperty("pt_codtarifacore")
    private String tGSINC1GRUPOASOCptCodTarifaCORE;

    @JsonProperty("pt_p_atrcodigoga")
    private Long tGSINC1GRUPOASOCptpatrCodigoGA;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1GRUPOASOCptpatrNumero();
        checkTGSINC1GRUPOASOCptpatrCodigo();
        checkTGSINC1GRUPOASOCptpatrCodigoLegal();
        checkTGSINC1GRUPOASOCptpatrDescripcion();
        checkTGSINC1GRUPOASOCptpatrFIniVigencia();
        checkTGSINC1GRUPOASOCptpatrFFinVigencia();
        checkTGSINC1GRUPOASOCptpatrEstado();
        checkTGSINC1GRUPOASOCptpatrCodigoGA();
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCptpatrNumero() {
        return this.tGSINC1GRUPOASOCptpatrNumero;
    }
    
    public void setTGSINC1GRUPOASOCptpatrNumero(String tGSINC1GRUPOASOCptpatrNumero) {
        this.tGSINC1GRUPOASOCptpatrNumero = tGSINC1GRUPOASOCptpatrNumero;
    }

    private void checkTGSINC1GRUPOASOCptpatrNumero() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrNumero() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRNUMERO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRNUMERO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCptpatrCodigo() {
        return this.tGSINC1GRUPOASOCptpatrCodigo;
    }
    
    public void setTGSINC1GRUPOASOCptpatrCodigo(String tGSINC1GRUPOASOCptpatrCodigo) {
        this.tGSINC1GRUPOASOCptpatrCodigo = tGSINC1GRUPOASOCptpatrCodigo;
    }

    private void checkTGSINC1GRUPOASOCptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCptpatrCodigoLegal() {
        return this.tGSINC1GRUPOASOCptpatrCodigoLegal;
    }
    
    public void setTGSINC1GRUPOASOCptpatrCodigoLegal(String tGSINC1GRUPOASOCptpatrCodigoLegal) {
        this.tGSINC1GRUPOASOCptpatrCodigoLegal = tGSINC1GRUPOASOCptpatrCodigoLegal;
    }

    private void checkTGSINC1GRUPOASOCptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCptpatrDescripcion() {
        return this.tGSINC1GRUPOASOCptpatrDescripcion;
    }
    
    public void setTGSINC1GRUPOASOCptpatrDescripcion(String tGSINC1GRUPOASOCptpatrDescripcion) {
        this.tGSINC1GRUPOASOCptpatrDescripcion = tGSINC1GRUPOASOCptpatrDescripcion;
    }

    private void checkTGSINC1GRUPOASOCptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCptpatrFIniVigencia() {
        return this.tGSINC1GRUPOASOCptpatrFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCptpatrFIniVigencia(Date tGSINC1GRUPOASOCptpatrFIniVigencia) {
        this.tGSINC1GRUPOASOCptpatrFIniVigencia = tGSINC1GRUPOASOCptpatrFIniVigencia;
    }

    private void checkTGSINC1GRUPOASOCptpatrFIniVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrFIniVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRFINIVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getTGSINC1GRUPOASOCptpatrFFinVigencia() {
        return this.tGSINC1GRUPOASOCptpatrFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setTGSINC1GRUPOASOCptpatrFFinVigencia(Date tGSINC1GRUPOASOCptpatrFFinVigencia) {
        this.tGSINC1GRUPOASOCptpatrFFinVigencia = tGSINC1GRUPOASOCptpatrFFinVigencia;
    }

    private void checkTGSINC1GRUPOASOCptpatrFFinVigencia() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrFFinVigencia() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRFFINVIGENCIA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCptpatrEstado() {
        return this.tGSINC1GRUPOASOCptpatrEstado;
    }
    
    public void setTGSINC1GRUPOASOCptpatrEstado(Long tGSINC1GRUPOASOCptpatrEstado) {
        this.tGSINC1GRUPOASOCptpatrEstado = tGSINC1GRUPOASOCptpatrEstado;
    }

    private void checkTGSINC1GRUPOASOCptpatrEstado() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrEstado() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRESTADO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRESTADO);
        }
    }

    @JsonIgnore
    public String getTGSINC1GRUPOASOCptCodTarifaCORE() {
        return this.tGSINC1GRUPOASOCptCodTarifaCORE;
    }
    
    public void setTGSINC1GRUPOASOCptCodTarifaCORE(String tGSINC1GRUPOASOCptCodTarifaCORE) {
        this.tGSINC1GRUPOASOCptCodTarifaCORE = tGSINC1GRUPOASOCptCodTarifaCORE;
    }

    @JsonIgnore
    public Long getTGSINC1GRUPOASOCptpatrCodigoGA() {
        return this.tGSINC1GRUPOASOCptpatrCodigoGA;
    }
    
    public void setTGSINC1GRUPOASOCptpatrCodigoGA(Long tGSINC1GRUPOASOCptpatrCodigoGA) {
        this.tGSINC1GRUPOASOCptpatrCodigoGA = tGSINC1GRUPOASOCptpatrCodigoGA;
    }

    private void checkTGSINC1GRUPOASOCptpatrCodigoGA() throws NotNullArgumentException {
        if(this.getTGSINC1GRUPOASOCptpatrCodigoGA() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ID_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGOGA, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1GRUPOASOC, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1GRUPOASOC_TGSINC1GRUPOASOCPTPATRCODIGOGA);
        }
    }
}
