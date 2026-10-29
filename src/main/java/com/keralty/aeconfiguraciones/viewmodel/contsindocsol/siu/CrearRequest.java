package com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContSinDocSolConstants;

public class CrearRequest {

    @JsonProperty("p_atrnumcontrato")
    private String contSinDocSolcrearpatrNumContrato;

    public void checkArguments() throws NotNullArgumentException {
        checkContSinDocSolcrearpatrNumContrato();
    }

    @JsonIgnore
    public String getContSinDocSolcrearpatrNumContrato() {
        return this.contSinDocSolcrearpatrNumContrato;
    }
    
    public void setContSinDocSolcrearpatrNumContrato(String contSinDocSolcrearpatrNumContrato) {
        this.contSinDocSolcrearpatrNumContrato = contSinDocSolcrearpatrNumContrato;
    }

    private void checkContSinDocSolcrearpatrNumContrato() throws NotNullArgumentException {
        if(this.getContSinDocSolcrearpatrNumContrato() == null) {
            throw new NotNullArgumentException(ContSinDocSolConstants.SERV_ID_CREAR, ContSinDocSolConstants.CLASS_ID, ContSinDocSolConstants.ARG_ID_CREAR_CONTSINDOCSOLCREARPATRNUMCONTRATO, ContSinDocSolConstants.SERV_ALIAS_CREAR, ContSinDocSolConstants.CLASS_ALIAS, ContSinDocSolConstants.ARG_ALIAS_CREAR_CONTSINDOCSOLCREARPATRNUMCONTRATO);
        }
    }
}
