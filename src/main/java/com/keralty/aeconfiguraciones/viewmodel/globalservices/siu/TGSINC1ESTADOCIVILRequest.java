package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1ESTADOCIVILRequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1ESTADOCIVILptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1ESTADOCIVILptpatrDescripcion;

    @JsonProperty("pt_p_atrnombrecorto")
    private String tGSINC1ESTADOCIVILptpatrNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1ESTADOCIVILptpatrCodigo();
        checkTGSINC1ESTADOCIVILptpatrDescripcion();
        checkTGSINC1ESTADOCIVILptpatrNombreCorto();
    }

    @JsonIgnore
    public Long getTGSINC1ESTADOCIVILptpatrCodigo() {
        return this.tGSINC1ESTADOCIVILptpatrCodigo;
    }
    
    public void setTGSINC1ESTADOCIVILptpatrCodigo(Long tGSINC1ESTADOCIVILptpatrCodigo) {
        this.tGSINC1ESTADOCIVILptpatrCodigo = tGSINC1ESTADOCIVILptpatrCodigo;
    }

    private void checkTGSINC1ESTADOCIVILptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOCIVILptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1ESTADOCIVILptpatrDescripcion() {
        return this.tGSINC1ESTADOCIVILptpatrDescripcion;
    }
    
    public void setTGSINC1ESTADOCIVILptpatrDescripcion(String tGSINC1ESTADOCIVILptpatrDescripcion) {
        this.tGSINC1ESTADOCIVILptpatrDescripcion = tGSINC1ESTADOCIVILptpatrDescripcion;
    }

    private void checkTGSINC1ESTADOCIVILptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOCIVILptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTGSINC1ESTADOCIVILptpatrNombreCorto() {
        return this.tGSINC1ESTADOCIVILptpatrNombreCorto;
    }
    
    public void setTGSINC1ESTADOCIVILptpatrNombreCorto(String tGSINC1ESTADOCIVILptpatrNombreCorto) {
        this.tGSINC1ESTADOCIVILptpatrNombreCorto = tGSINC1ESTADOCIVILptpatrNombreCorto;
    }

    private void checkTGSINC1ESTADOCIVILptpatrNombreCorto() throws NotNullArgumentException {
        if(this.getTGSINC1ESTADOCIVILptpatrNombreCorto() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ID_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRNOMBRECORTO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1ESTADOCIVIL, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1ESTADOCIVIL_TGSINC1ESTADOCIVILPTPATRNOMBRECORTO);
        }
    }
}
