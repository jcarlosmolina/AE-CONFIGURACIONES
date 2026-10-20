package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1PERIODICIDADRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1PERIODICIDADptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1PERIODICIDADptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1PERIODICIDADptpatrCodigo();
        checkTGSINC1PERIODICIDADptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1PERIODICIDADptpatrCodigo() {
        return this.tGSINC1PERIODICIDADptpatrCodigo;
    }
    
    public void setTGSINC1PERIODICIDADptpatrCodigo(Long tGSINC1PERIODICIDADptpatrCodigo) {
        this.tGSINC1PERIODICIDADptpatrCodigo = tGSINC1PERIODICIDADptpatrCodigo;
    }

    private void checkTGSINC1PERIODICIDADptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1PERIODICIDADptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PERIODICIDAD, GlobalServiceConstants.GS_ARG_ID_TGSINC1PERIODICIDAD_TGSINC1PERIODICIDADPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PERIODICIDAD, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PERIODICIDAD_TGSINC1PERIODICIDADPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1PERIODICIDADptpatrDescripcion() {
        return this.tGSINC1PERIODICIDADptpatrDescripcion;
    }
    
    public void setTGSINC1PERIODICIDADptpatrDescripcion(String tGSINC1PERIODICIDADptpatrDescripcion) {
        this.tGSINC1PERIODICIDADptpatrDescripcion = tGSINC1PERIODICIDADptpatrDescripcion;
    }

    private void checkTGSINC1PERIODICIDADptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1PERIODICIDADptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PERIODICIDAD, GlobalServiceConstants.GS_ARG_ID_TGSINC1PERIODICIDAD_TGSINC1PERIODICIDADPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PERIODICIDAD, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PERIODICIDAD_TGSINC1PERIODICIDADPTPATRDESCRIPCION);
        }
    }
}
