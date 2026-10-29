package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1TIPOENTIDADRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1TIPOENTIDADptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1TIPOENTIDADptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1TIPOENTIDADptpatrCodigo();
        checkTGSINC1TIPOENTIDADptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1TIPOENTIDADptpatrCodigo() {
        return this.tGSINC1TIPOENTIDADptpatrCodigo;
    }
    
    public void setTGSINC1TIPOENTIDADptpatrCodigo(Long tGSINC1TIPOENTIDADptpatrCodigo) {
        this.tGSINC1TIPOENTIDADptpatrCodigo = tGSINC1TIPOENTIDADptpatrCodigo;
    }

    private void checkTGSINC1TIPOENTIDADptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOENTIDADptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOENTIDAD, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOENTIDAD_TGSINC1TIPOENTIDADPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOENTIDAD, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOENTIDAD_TGSINC1TIPOENTIDADPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1TIPOENTIDADptpatrDescripcion() {
        return this.tGSINC1TIPOENTIDADptpatrDescripcion;
    }
    
    public void setTGSINC1TIPOENTIDADptpatrDescripcion(String tGSINC1TIPOENTIDADptpatrDescripcion) {
        this.tGSINC1TIPOENTIDADptpatrDescripcion = tGSINC1TIPOENTIDADptpatrDescripcion;
    }

    private void checkTGSINC1TIPOENTIDADptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOENTIDADptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOENTIDAD, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOENTIDAD_TGSINC1TIPOENTIDADPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOENTIDAD, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOENTIDAD_TGSINC1TIPOENTIDADPTPATRDESCRIPCION);
        }
    }
}
