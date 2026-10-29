package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;

public class TGCREARCONTEXENTORequest {

    @JsonProperty("pt_p_atrnumcontrato")
    private String tGCREARCONTEXENTOptpatrNumContrato;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTGCREARCONTEXENTOptpatrNumContrato();
    }

    @JsonIgnore
    public String getTGCREARCONTEXENTOptpatrNumContrato() {
        return this.tGCREARCONTEXENTOptpatrNumContrato;
    }
    
    public void setTGCREARCONTEXENTOptpatrNumContrato(String tGCREARCONTEXENTOptpatrNumContrato) {
        this.tGCREARCONTEXENTOptpatrNumContrato = tGCREARCONTEXENTOptpatrNumContrato;
    }

    private void checkTGCREARCONTEXENTOptpatrNumContrato() throws NotNullArgumentException {
        if(this.getTGCREARCONTEXENTOptpatrNumContrato() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGCREARCONTEXENTO, GlobalServiceConstants.GS_ARG_ID_TGCREARCONTEXENTO_TGCREARCONTEXENTOPTPATRNUMCONTRATO, GlobalServiceConstants.G_SERV_ALIAS_TGCREARCONTEXENTO, GlobalServiceConstants.GS_ARG_ALIAS_TGCREARCONTEXENTO_TGCREARCONTEXENTOPTPATRNUMCONTRATO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
