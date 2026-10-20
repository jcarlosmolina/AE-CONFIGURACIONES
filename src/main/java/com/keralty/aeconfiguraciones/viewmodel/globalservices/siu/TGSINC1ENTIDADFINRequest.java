package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1ENTIDADFINRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1ENTIDADFINptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1ENTIDADFINptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1ENTIDADFINptpatrCodigo();
        checkTGSINC1ENTIDADFINptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1ENTIDADFINptpatrCodigo() {
        return this.tGSINC1ENTIDADFINptpatrCodigo;
    }
    
    public void setTGSINC1ENTIDADFINptpatrCodigo(Long tGSINC1ENTIDADFINptpatrCodigo) {
        this.tGSINC1ENTIDADFINptpatrCodigo = tGSINC1ENTIDADFINptpatrCodigo;
    }

    private void checkTGSINC1ENTIDADFINptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1ENTIDADFINptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ENTIDADFIN, GlobalServiceConstants.GS_ARG_ID_TGSINC1ENTIDADFIN_TGSINC1ENTIDADFINPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ENTIDADFIN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ENTIDADFIN_TGSINC1ENTIDADFINPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1ENTIDADFINptpatrDescripcion() {
        return this.tGSINC1ENTIDADFINptpatrDescripcion;
    }
    
    public void setTGSINC1ENTIDADFINptpatrDescripcion(String tGSINC1ENTIDADFINptpatrDescripcion) {
        this.tGSINC1ENTIDADFINptpatrDescripcion = tGSINC1ENTIDADFINptpatrDescripcion;
    }

    private void checkTGSINC1ENTIDADFINptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1ENTIDADFINptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ENTIDADFIN, GlobalServiceConstants.GS_ARG_ID_TGSINC1ENTIDADFIN_TGSINC1ENTIDADFINPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ENTIDADFIN, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ENTIDADFIN_TGSINC1ENTIDADFINPTPATRDESCRIPCION);
        }
    }
}
