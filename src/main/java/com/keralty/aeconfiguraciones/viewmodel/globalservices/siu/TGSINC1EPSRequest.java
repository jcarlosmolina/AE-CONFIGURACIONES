package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1EPSRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1EPSptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1EPSptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1EPSptpatrCodigo();
        checkTGSINC1EPSptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1EPSptpatrCodigo() {
        return this.tGSINC1EPSptpatrCodigo;
    }
    
    public void setTGSINC1EPSptpatrCodigo(Long tGSINC1EPSptpatrCodigo) {
        this.tGSINC1EPSptpatrCodigo = tGSINC1EPSptpatrCodigo;
    }

    private void checkTGSINC1EPSptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1EPSptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1EPS, GlobalServiceConstants.GS_ARG_ID_TGSINC1EPS_TGSINC1EPSPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1EPS, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1EPS_TGSINC1EPSPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1EPSptpatrDescripcion() {
        return this.tGSINC1EPSptpatrDescripcion;
    }
    
    public void setTGSINC1EPSptpatrDescripcion(String tGSINC1EPSptpatrDescripcion) {
        this.tGSINC1EPSptpatrDescripcion = tGSINC1EPSptpatrDescripcion;
    }

    private void checkTGSINC1EPSptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1EPSptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1EPS, GlobalServiceConstants.GS_ARG_ID_TGSINC1EPS_TGSINC1EPSPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1EPS, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1EPS_TGSINC1EPSPTPATRDESCRIPCION);
        }
    }
}
