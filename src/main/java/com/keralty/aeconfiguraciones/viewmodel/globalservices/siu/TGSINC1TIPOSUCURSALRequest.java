package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1TIPOSUCURSALRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1TIPOSUCURSALptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1TIPOSUCURSALptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1TIPOSUCURSALptpatrCodigo();
        checkTGSINC1TIPOSUCURSALptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1TIPOSUCURSALptpatrCodigo() {
        return this.tGSINC1TIPOSUCURSALptpatrCodigo;
    }
    
    public void setTGSINC1TIPOSUCURSALptpatrCodigo(Long tGSINC1TIPOSUCURSALptpatrCodigo) {
        this.tGSINC1TIPOSUCURSALptpatrCodigo = tGSINC1TIPOSUCURSALptpatrCodigo;
    }

    private void checkTGSINC1TIPOSUCURSALptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOSUCURSALptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOSUCURSAL, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOSUCURSAL_TGSINC1TIPOSUCURSALPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOSUCURSAL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOSUCURSAL_TGSINC1TIPOSUCURSALPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1TIPOSUCURSALptpatrDescripcion() {
        return this.tGSINC1TIPOSUCURSALptpatrDescripcion;
    }
    
    public void setTGSINC1TIPOSUCURSALptpatrDescripcion(String tGSINC1TIPOSUCURSALptpatrDescripcion) {
        this.tGSINC1TIPOSUCURSALptpatrDescripcion = tGSINC1TIPOSUCURSALptpatrDescripcion;
    }

    private void checkTGSINC1TIPOSUCURSALptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1TIPOSUCURSALptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1TIPOSUCURSAL, GlobalServiceConstants.GS_ARG_ID_TGSINC1TIPOSUCURSAL_TGSINC1TIPOSUCURSALPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1TIPOSUCURSAL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1TIPOSUCURSAL_TGSINC1TIPOSUCURSALPTPATRDESCRIPCION);
        }
    }
}
