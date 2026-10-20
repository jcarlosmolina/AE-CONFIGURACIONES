package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1DEPARTAMENTORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1DEPARTAMENTOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1DEPARTAMENTOptpatrDescripcion;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1DEPARTAMENTOptpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1DEPARTAMENTOptpatrCodigo();
        checkTGSINC1DEPARTAMENTOptpatrDescripcion();
        checkTGSINC1DEPARTAMENTOptpatrCodigoLegal();
    }

    @JsonIgnore
    public Long getTGSINC1DEPARTAMENTOptpatrCodigo() {
        return this.tGSINC1DEPARTAMENTOptpatrCodigo;
    }
    
    public void setTGSINC1DEPARTAMENTOptpatrCodigo(Long tGSINC1DEPARTAMENTOptpatrCodigo) {
        this.tGSINC1DEPARTAMENTOptpatrCodigo = tGSINC1DEPARTAMENTOptpatrCodigo;
    }

    private void checkTGSINC1DEPARTAMENTOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1DEPARTAMENTOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ID_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1DEPARTAMENTOptpatrDescripcion() {
        return this.tGSINC1DEPARTAMENTOptpatrDescripcion;
    }
    
    public void setTGSINC1DEPARTAMENTOptpatrDescripcion(String tGSINC1DEPARTAMENTOptpatrDescripcion) {
        this.tGSINC1DEPARTAMENTOptpatrDescripcion = tGSINC1DEPARTAMENTOptpatrDescripcion;
    }

    private void checkTGSINC1DEPARTAMENTOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1DEPARTAMENTOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ID_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTGSINC1DEPARTAMENTOptpatrCodigoLegal() {
        return this.tGSINC1DEPARTAMENTOptpatrCodigoLegal;
    }
    
    public void setTGSINC1DEPARTAMENTOptpatrCodigoLegal(String tGSINC1DEPARTAMENTOptpatrCodigoLegal) {
        this.tGSINC1DEPARTAMENTOptpatrCodigoLegal = tGSINC1DEPARTAMENTOptpatrCodigoLegal;
    }

    private void checkTGSINC1DEPARTAMENTOptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1DEPARTAMENTOptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ID_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1DEPARTAMENTO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1DEPARTAMENTO_TGSINC1DEPARTAMENTOPTPATRCODIGOLEGAL);
        }
    }
}
