package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGSINC1MUNICIPIORequest {

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1MUNICIPIOptpatrCodigo;

    @JsonProperty("pt_p_atrdescripcion")
    private String tGSINC1MUNICIPIOptpatrDescripcion;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1MUNICIPIOptpatrCodigoLegal;

    @JsonProperty("pt_p_atresvalidoeps")
    private Boolean tGSINC1MUNICIPIOptpatrEsValidoEPS;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1MUNICIPIOptpatrCodigo();
        checkTGSINC1MUNICIPIOptpatrDescripcion();
        checkTGSINC1MUNICIPIOptpatrCodigoLegal();
        checkTGSINC1MUNICIPIOptpatrEsValidoEPS();
    }

    @JsonIgnore
    public Long getTGSINC1MUNICIPIOptpatrCodigo() {
        return this.tGSINC1MUNICIPIOptpatrCodigo;
    }
    
    public void setTGSINC1MUNICIPIOptpatrCodigo(Long tGSINC1MUNICIPIOptpatrCodigo) {
        this.tGSINC1MUNICIPIOptpatrCodigo = tGSINC1MUNICIPIOptpatrCodigo;
    }

    private void checkTGSINC1MUNICIPIOptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1MUNICIPIOptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1MUNICIPIOptpatrDescripcion() {
        return this.tGSINC1MUNICIPIOptpatrDescripcion;
    }
    
    public void setTGSINC1MUNICIPIOptpatrDescripcion(String tGSINC1MUNICIPIOptpatrDescripcion) {
        this.tGSINC1MUNICIPIOptpatrDescripcion = tGSINC1MUNICIPIOptpatrDescripcion;
    }

    private void checkTGSINC1MUNICIPIOptpatrDescripcion() throws NotNullArgumentException {
        if(this.getTGSINC1MUNICIPIOptpatrDescripcion() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRDESCRIPCION, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTGSINC1MUNICIPIOptpatrCodigoLegal() {
        return this.tGSINC1MUNICIPIOptpatrCodigoLegal;
    }
    
    public void setTGSINC1MUNICIPIOptpatrCodigoLegal(String tGSINC1MUNICIPIOptpatrCodigoLegal) {
        this.tGSINC1MUNICIPIOptpatrCodigoLegal = tGSINC1MUNICIPIOptpatrCodigoLegal;
    }

    private void checkTGSINC1MUNICIPIOptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1MUNICIPIOptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getTGSINC1MUNICIPIOptpatrEsValidoEPS() {
        return this.tGSINC1MUNICIPIOptpatrEsValidoEPS;
    }
    
    public void setTGSINC1MUNICIPIOptpatrEsValidoEPS(Boolean tGSINC1MUNICIPIOptpatrEsValidoEPS) {
        this.tGSINC1MUNICIPIOptpatrEsValidoEPS = tGSINC1MUNICIPIOptpatrEsValidoEPS;
    }

    private void checkTGSINC1MUNICIPIOptpatrEsValidoEPS() throws NotNullArgumentException {
        if(this.getTGSINC1MUNICIPIOptpatrEsValidoEPS() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ID_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRESVALIDOEPS, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1MUNICIPIO, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1MUNICIPIO_TGSINC1MUNICIPIOPTPATRESVALIDOEPS);
        }
    }
}
