package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1TIPODIRECCIONRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1TIPODIRECCIONptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1TIPODIRECCIONptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1TIPODIRECCIONptpatrCodigo();
        checkTGSINC1TIPODIRECCIONptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1TIPODIRECCIONptpatrCodigo() {
        return this.tGSINC1TIPODIRECCIONptpatrCodigo;
    }
    
    public void setTGSINC1TIPODIRECCIONptpatrCodigo(Long tGSINC1TIPODIRECCIONptpatrCodigo) {
        this.tGSINC1TIPODIRECCIONptpatrCodigo = tGSINC1TIPODIRECCIONptpatrCodigo;
    }

    private void checkTGSINC1TIPODIRECCIONptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1TIPODIRECCIONptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPODIRECCION, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPODIRECCION_TGSINC1TIPODIRECCIONPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPODIRECCION, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPODIRECCION_TGSINC1TIPODIRECCIONPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1TIPODIRECCIONptpatrDescripcion() {
        return this.tGSINC1TIPODIRECCIONptpatrDescripcion;
    }
    
    public void setTGSINC1TIPODIRECCIONptpatrDescripcion(String tGSINC1TIPODIRECCIONptpatrDescripcion) {
        this.tGSINC1TIPODIRECCIONptpatrDescripcion = tGSINC1TIPODIRECCIONptpatrDescripcion;
    }

    private void checkTGSINC1TIPODIRECCIONptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1TIPODIRECCIONptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPODIRECCION, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPODIRECCION_TGSINC1TIPODIRECCIONPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPODIRECCION, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPODIRECCION_TGSINC1TIPODIRECCIONPTPATRDESCRIPCION);
        }
    }
}
