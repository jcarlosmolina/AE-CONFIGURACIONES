package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1ESTADOCONTRATORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1ESTADOCONTRATOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1ESTADOCONTRATOptpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1ESTADOCONTRATOptpatrCodigo();
        checkTGSINC1ESTADOCONTRATOptpatrDescripcion();
    }

    @JsonIgnore
    public Long getTGSINC1ESTADOCONTRATOptpatrCodigo() {
        return this.tGSINC1ESTADOCONTRATOptpatrCodigo;
    }
    
    public void setTGSINC1ESTADOCONTRATOptpatrCodigo(Long tGSINC1ESTADOCONTRATOptpatrCodigo) {
        this.tGSINC1ESTADOCONTRATOptpatrCodigo = tGSINC1ESTADOCONTRATOptpatrCodigo;
    }

    private void checkTGSINC1ESTADOCONTRATOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOCONTRATOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOCONTRATO, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOCONTRATO_TGSINC1ESTADOCONTRATOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOCONTRATO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOCONTRATO_TGSINC1ESTADOCONTRATOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1ESTADOCONTRATOptpatrDescripcion() {
        return this.tGSINC1ESTADOCONTRATOptpatrDescripcion;
    }
    
    public void setTGSINC1ESTADOCONTRATOptpatrDescripcion(String tGSINC1ESTADOCONTRATOptpatrDescripcion) {
        this.tGSINC1ESTADOCONTRATOptpatrDescripcion = tGSINC1ESTADOCONTRATOptpatrDescripcion;
    }

    private void checkTGSINC1ESTADOCONTRATOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOCONTRATOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOCONTRATO, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOCONTRATO_TGSINC1ESTADOCONTRATOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOCONTRATO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOCONTRATO_TGSINC1ESTADOCONTRATOPTPATRDESCRIPCION);
        }
    }
}
