package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1REGIONALRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1REGIONALptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1REGIONALptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1REGIONALptpatrCodigo();
        checkTGSINC1REGIONALptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1REGIONALptpatrCodigo() {
        return this.tGSINC1REGIONALptpatrCodigo;
    }
    
    public void setTGSINC1REGIONALptpatrCodigo(Long tGSINC1REGIONALptpatrCodigo) {
        this.tGSINC1REGIONALptpatrCodigo = tGSINC1REGIONALptpatrCodigo;
    }

    private void checkTGSINC1REGIONALptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1REGIONALptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1REGIONAL, GlobalServiceConstants.GS_ARG_ID_TGSINC1REGIONAL_TGSINC1REGIONALPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1REGIONAL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1REGIONAL_TGSINC1REGIONALPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1REGIONALptpatrDescripcion() {
        return this.tGSINC1REGIONALptpatrDescripcion;
    }
    
    public void setTGSINC1REGIONALptpatrDescripcion(String tGSINC1REGIONALptpatrDescripcion) {
        this.tGSINC1REGIONALptpatrDescripcion = tGSINC1REGIONALptpatrDescripcion;
    }

    private void checkTGSINC1REGIONALptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1REGIONALptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1REGIONAL, GlobalServiceConstants.GS_ARG_ID_TGSINC1REGIONAL_TGSINC1REGIONALPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1REGIONAL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1REGIONAL_TGSINC1REGIONALPTPATRDESCRIPCION);
        }
    }
}
