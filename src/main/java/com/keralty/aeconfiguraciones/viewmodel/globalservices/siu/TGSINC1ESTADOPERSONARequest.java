package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1ESTADOPERSONARequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1ESTADOPERSONAptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1ESTADOPERSONAptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1ESTADOPERSONAptpatrCodigo();
        checkTGSINC1ESTADOPERSONAptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1ESTADOPERSONAptpatrCodigo() {
        return this.tGSINC1ESTADOPERSONAptpatrCodigo;
    }
    
    public void setTGSINC1ESTADOPERSONAptpatrCodigo(Long tGSINC1ESTADOPERSONAptpatrCodigo) {
        this.tGSINC1ESTADOPERSONAptpatrCodigo = tGSINC1ESTADOPERSONAptpatrCodigo;
    }

    private void checkTGSINC1ESTADOPERSONAptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOPERSONAptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOPERSONA, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOPERSONA_TGSINC1ESTADOPERSONAPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOPERSONA, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOPERSONA_TGSINC1ESTADOPERSONAPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1ESTADOPERSONAptpatrDescripcion() {
        return this.tGSINC1ESTADOPERSONAptpatrDescripcion;
    }
    
    public void setTGSINC1ESTADOPERSONAptpatrDescripcion(String tGSINC1ESTADOPERSONAptpatrDescripcion) {
        this.tGSINC1ESTADOPERSONAptpatrDescripcion = tGSINC1ESTADOPERSONAptpatrDescripcion;
    }

    private void checkTGSINC1ESTADOPERSONAptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOPERSONAptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOPERSONA, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOPERSONA_TGSINC1ESTADOPERSONAPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOPERSONA, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOPERSONA_TGSINC1ESTADOPERSONAPTPATRDESCRIPCION);
        }
    }
}
