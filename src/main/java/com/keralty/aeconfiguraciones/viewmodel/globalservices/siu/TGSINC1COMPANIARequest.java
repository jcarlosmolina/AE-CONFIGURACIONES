package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1COMPANIARequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1COMPANIAptpatrCodigo;

    @JsonProperty("pt_p_atrnombre")
    private String tGSINC1COMPANIAptpatrNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1COMPANIAptpatrCodigo();
        checkTGSINC1COMPANIAptpatrNombre();
    }

    @JsonIgnore
    public Long getTGSINC1COMPANIAptpatrCodigo() {
        return this.tGSINC1COMPANIAptpatrCodigo;
    }
    
    public void setTGSINC1COMPANIAptpatrCodigo(Long tGSINC1COMPANIAptpatrCodigo) {
        this.tGSINC1COMPANIAptpatrCodigo = tGSINC1COMPANIAptpatrCodigo;
    }

    private void checkTGSINC1COMPANIAptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1COMPANIAptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1COMPANIA, GlobalServiceConstants.GS_ARG_ID_TGSINC1COMPANIA_TGSINC1COMPANIAPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1COMPANIA, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1COMPANIA_TGSINC1COMPANIAPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1COMPANIAptpatrNombre() {
        return this.tGSINC1COMPANIAptpatrNombre;
    }
    
    public void setTGSINC1COMPANIAptpatrNombre(String tGSINC1COMPANIAptpatrNombre) {
        this.tGSINC1COMPANIAptpatrNombre = tGSINC1COMPANIAptpatrNombre;
    }

    private void checkTGSINC1COMPANIAptpatrNombre() throws NotNullArgumentException {
        if(this.getTGSINC1COMPANIAptpatrNombre() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1COMPANIA, GlobalServiceConstants.GS_ARG_ID_TGSINC1COMPANIA_TGSINC1COMPANIAPTPATRNOMBRE, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1COMPANIA, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1COMPANIA_TGSINC1COMPANIAPTPATRNOMBRE);
        }
    }
}
