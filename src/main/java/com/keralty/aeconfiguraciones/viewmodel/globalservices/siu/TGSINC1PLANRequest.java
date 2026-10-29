package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1PLANRequest {

    @JsonProperty("pt_p_atrnumero")
    private Long tGSINC1PLANptpatrNumero;

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1PLANptpatrCodigo;

    @JsonProperty("pt_p_atrnombre")
    private String tGSINC1PLANptpatrNombre;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1PLANptpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1PLANptpatrNumero();
        checkTGSINC1PLANptpatrCodigo();
        checkTGSINC1PLANptpatrNombre();
        checkTGSINC1PLANptpatrCodigoLegal();
    }

    @JsonIgnore
    public Long getTGSINC1PLANptpatrNumero() {
        return this.tGSINC1PLANptpatrNumero;
    }
    
    public void setTGSINC1PLANptpatrNumero(Long tGSINC1PLANptpatrNumero) {
        this.tGSINC1PLANptpatrNumero = tGSINC1PLANptpatrNumero;
    }

    private void checkTGSINC1PLANptpatrNumero() throws NotNullArgumentException {
        if(this.getTGSINC1PLANptpatrNumero() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLAN_TGSINC1PLANPTPATRNUMERO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLAN_TGSINC1PLANPTPATRNUMERO);
        }
    }

    @JsonIgnore
    public Long getTGSINC1PLANptpatrCodigo() {
        return this.tGSINC1PLANptpatrCodigo;
    }
    
    public void setTGSINC1PLANptpatrCodigo(Long tGSINC1PLANptpatrCodigo) {
        this.tGSINC1PLANptpatrCodigo = tGSINC1PLANptpatrCodigo;
    }

    private void checkTGSINC1PLANptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1PLANptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLAN_TGSINC1PLANPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLAN_TGSINC1PLANPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1PLANptpatrNombre() {
        return this.tGSINC1PLANptpatrNombre;
    }
    
    public void setTGSINC1PLANptpatrNombre(String tGSINC1PLANptpatrNombre) {
        this.tGSINC1PLANptpatrNombre = tGSINC1PLANptpatrNombre;
    }

    private void checkTGSINC1PLANptpatrNombre() throws NotNullArgumentException {
        if(this.getTGSINC1PLANptpatrNombre() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLAN_TGSINC1PLANPTPATRNOMBRE, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLAN_TGSINC1PLANPTPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getTGSINC1PLANptpatrCodigoLegal() {
        return this.tGSINC1PLANptpatrCodigoLegal;
    }
    
    public void setTGSINC1PLANptpatrCodigoLegal(String tGSINC1PLANptpatrCodigoLegal) {
        this.tGSINC1PLANptpatrCodigoLegal = tGSINC1PLANptpatrCodigoLegal;
    }

    private void checkTGSINC1PLANptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1PLANptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLAN_TGSINC1PLANPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLAN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLAN_TGSINC1PLANPTPATRCODIGOLEGAL);
        }
    }
}
