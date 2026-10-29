package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1PAISRequest {

    @JsonProperty("pt_p_atrcodigo")
    private String tGSINC1PAISptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1PAISptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1PAISptpatrCodigo();
    }

    @JsonIgnore
    public String getTGSINC1PAISptpatrCodigo() {
        return this.tGSINC1PAISptpatrCodigo;
    }
    
    public void setTGSINC1PAISptpatrCodigo(String tGSINC1PAISptpatrCodigo) {
        this.tGSINC1PAISptpatrCodigo = tGSINC1PAISptpatrCodigo;
    }

    private void checkTGSINC1PAISptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1PAISptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PAIS, GlobalServiceConstants.GS_ARG_ID_TGSINC1PAIS_TGSINC1PAISPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PAIS, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PAIS_TGSINC1PAISPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1PAISptpatrDescripcion() {
        return this.tGSINC1PAISptpatrDescripcion;
    }
    
    public void setTGSINC1PAISptpatrDescripcion(String tGSINC1PAISptpatrDescripcion) {
        this.tGSINC1PAISptpatrDescripcion = tGSINC1PAISptpatrDescripcion;
    }
}
