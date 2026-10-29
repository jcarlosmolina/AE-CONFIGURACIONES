package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1PARENTESCORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1PARENTESCOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1PARENTESCOptpatrDescripcion;

    @JsonProperty("pt_p_atrnombrecorto")
    private String tGSINC1PARENTESCOptpatrNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1PARENTESCOptpatrCodigo();
        checkTGSINC1PARENTESCOptpatrDescripcion();
        checkTGSINC1PARENTESCOptpatrNombreCorto();
    }

    @JsonIgnore
    public Long getTGSINC1PARENTESCOptpatrCodigo() {
        return this.tGSINC1PARENTESCOptpatrCodigo;
    }
    
    public void setTGSINC1PARENTESCOptpatrCodigo(Long tGSINC1PARENTESCOptpatrCodigo) {
        this.tGSINC1PARENTESCOptpatrCodigo = tGSINC1PARENTESCOptpatrCodigo;
    }

    private void checkTGSINC1PARENTESCOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1PARENTESCOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ID_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1PARENTESCOptpatrDescripcion() {
        return this.tGSINC1PARENTESCOptpatrDescripcion;
    }
    
    public void setTGSINC1PARENTESCOptpatrDescripcion(String tGSINC1PARENTESCOptpatrDescripcion) {
        this.tGSINC1PARENTESCOptpatrDescripcion = tGSINC1PARENTESCOptpatrDescripcion;
    }

    private void checkTGSINC1PARENTESCOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1PARENTESCOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ID_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTGSINC1PARENTESCOptpatrNombreCorto() {
        return this.tGSINC1PARENTESCOptpatrNombreCorto;
    }
    
    public void setTGSINC1PARENTESCOptpatrNombreCorto(String tGSINC1PARENTESCOptpatrNombreCorto) {
        this.tGSINC1PARENTESCOptpatrNombreCorto = tGSINC1PARENTESCOptpatrNombreCorto;
    }

    private void checkTGSINC1PARENTESCOptpatrNombreCorto() throws NotNullArgumentException {
        if(this.getTGSINC1PARENTESCOptpatrNombreCorto() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ID_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRNOMBRECORTO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PARENTESCO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PARENTESCO_TGSINC1PARENTESCOPTPATRNOMBRECORTO);
        }
    }
}
