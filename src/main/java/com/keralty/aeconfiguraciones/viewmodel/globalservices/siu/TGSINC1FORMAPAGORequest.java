package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1FORMAPAGORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1FORMAPAGOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1FORMAPAGOptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1FORMAPAGOptpatrCodigo();
        checkTGSINC1FORMAPAGOptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1FORMAPAGOptpatrCodigo() {
        return this.tGSINC1FORMAPAGOptpatrCodigo;
    }
    
    public void setTGSINC1FORMAPAGOptpatrCodigo(Long tGSINC1FORMAPAGOptpatrCodigo) {
        this.tGSINC1FORMAPAGOptpatrCodigo = tGSINC1FORMAPAGOptpatrCodigo;
    }

    private void checkTGSINC1FORMAPAGOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1FORMAPAGOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1FORMAPAGO, GlobalServiceConstants.GS_ARG_ID_TGSINC1FORMAPAGO_TGSINC1FORMAPAGOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1FORMAPAGO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1FORMAPAGO_TGSINC1FORMAPAGOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1FORMAPAGOptpatrDescripcion() {
        return this.tGSINC1FORMAPAGOptpatrDescripcion;
    }
    
    public void setTGSINC1FORMAPAGOptpatrDescripcion(String tGSINC1FORMAPAGOptpatrDescripcion) {
        this.tGSINC1FORMAPAGOptpatrDescripcion = tGSINC1FORMAPAGOptpatrDescripcion;
    }

    private void checkTGSINC1FORMAPAGOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1FORMAPAGOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1FORMAPAGO, GlobalServiceConstants.GS_ARG_ID_TGSINC1FORMAPAGO_TGSINC1FORMAPAGOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1FORMAPAGO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1FORMAPAGO_TGSINC1FORMAPAGOPTPATRDESCRIPCION);
        }
    }
}
