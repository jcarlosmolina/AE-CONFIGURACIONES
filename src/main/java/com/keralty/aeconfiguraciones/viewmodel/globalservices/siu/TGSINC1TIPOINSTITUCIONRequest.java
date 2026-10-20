package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1TIPOINSTITUCIONRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1TIPOINSTITUCIONptpatrCodigo;

    @JsonProperty("pt_p_atrnombre")
    private String tGSINC1TIPOINSTITUCIONptpatrNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1TIPOINSTITUCIONptpatrCodigo();
        checkTGSINC1TIPOINSTITUCIONptpatrNombre();
    }

    @JsonIgnore
    public Long getTGSINC1TIPOINSTITUCIONptpatrCodigo() {
        return this.tGSINC1TIPOINSTITUCIONptpatrCodigo;
    }
    
    public void setTGSINC1TIPOINSTITUCIONptpatrCodigo(Long tGSINC1TIPOINSTITUCIONptpatrCodigo) {
        this.tGSINC1TIPOINSTITUCIONptpatrCodigo = tGSINC1TIPOINSTITUCIONptpatrCodigo;
    }

    private void checkTGSINC1TIPOINSTITUCIONptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOINSTITUCIONptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOINSTITUCION, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOINSTITUCION_TGSINC1TIPOINSTITUCIONPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOINSTITUCION, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOINSTITUCION_TGSINC1TIPOINSTITUCIONPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1TIPOINSTITUCIONptpatrNombre() {
        return this.tGSINC1TIPOINSTITUCIONptpatrNombre;
    }
    
    public void setTGSINC1TIPOINSTITUCIONptpatrNombre(String tGSINC1TIPOINSTITUCIONptpatrNombre) {
        this.tGSINC1TIPOINSTITUCIONptpatrNombre = tGSINC1TIPOINSTITUCIONptpatrNombre;
    }

    private void checkTGSINC1TIPOINSTITUCIONptpatrNombre() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOINSTITUCIONptpatrNombre() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOINSTITUCION, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOINSTITUCION_TGSINC1TIPOINSTITUCIONPTPATRNOMBRE, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOINSTITUCION, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOINSTITUCION_TGSINC1TIPOINSTITUCIONPTPATRNOMBRE);
        }
    }
}
