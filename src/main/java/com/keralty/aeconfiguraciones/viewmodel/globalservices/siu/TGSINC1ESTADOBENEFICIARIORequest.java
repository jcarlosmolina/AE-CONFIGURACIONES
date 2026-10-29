package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1ESTADOBENEFICIARIORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1ESTADOBENEFICIARIOptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1ESTADOBENEFICIARIOptpatrCodigo();
        checkTGSINC1ESTADOBENEFICIARIOptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1ESTADOBENEFICIARIOptpatrCodigo() {
        return this.tGSINC1ESTADOBENEFICIARIOptpatrCodigo;
    }
    
    public void setTGSINC1ESTADOBENEFICIARIOptpatrCodigo(Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) {
        this.tGSINC1ESTADOBENEFICIARIOptpatrCodigo = tGSINC1ESTADOBENEFICIARIOptpatrCodigo;
    }

    private void checkTGSINC1ESTADOBENEFICIARIOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOBENEFICIARIOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOBENEFICIARIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOBENEFICIARIO_TGSINC1ESTADOBENEFICIARIOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOBENEFICIARIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOBENEFICIARIO_TGSINC1ESTADOBENEFICIARIOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1ESTADOBENEFICIARIOptpatrDescripcion() {
        return this.tGSINC1ESTADOBENEFICIARIOptpatrDescripcion;
    }
    
    public void setTGSINC1ESTADOBENEFICIARIOptpatrDescripcion(String tGSINC1ESTADOBENEFICIARIOptpatrDescripcion) {
        this.tGSINC1ESTADOBENEFICIARIOptpatrDescripcion = tGSINC1ESTADOBENEFICIARIOptpatrDescripcion;
    }

    private void checkTGSINC1ESTADOBENEFICIARIOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOBENEFICIARIOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOBENEFICIARIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOBENEFICIARIO_TGSINC1ESTADOBENEFICIARIOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOBENEFICIARIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOBENEFICIARIO_TGSINC1ESTADOBENEFICIARIOPTPATRDESCRIPCION);
        }
    }
}
