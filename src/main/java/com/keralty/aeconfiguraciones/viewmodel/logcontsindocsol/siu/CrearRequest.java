package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContSinDocSolConstants;

public class CrearRequest {

    @JsonProperty("p_atrnumcontrato")
    private String logContSinDocSolcrearpatrNumContrato;

    @JsonProperty("p_atrusuario")
    private String logContSinDocSolcrearpatrUsuario;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContSinDocSolcrearpatrNumContrato();
        checkLogContSinDocSolcrearpatrUsuario();
    }

    @JsonIgnore
    public String getLogContSinDocSolcrearpatrNumContrato() {
        return this.logContSinDocSolcrearpatrNumContrato;
    }
    
    public void setLogContSinDocSolcrearpatrNumContrato(String logContSinDocSolcrearpatrNumContrato) {
        this.logContSinDocSolcrearpatrNumContrato = logContSinDocSolcrearpatrNumContrato;
    }

    private void checkLogContSinDocSolcrearpatrNumContrato() throws NotNullArgumentException {
        if(this.getLogContSinDocSolcrearpatrNumContrato() == null) {
            throw new NotNullArgumentException(LogContSinDocSolConstants.SERV_ID_CREAR, LogContSinDocSolConstants.CLASS_ID, LogContSinDocSolConstants.ARG_ID_CREAR_LOGCONTSINDOCSOLCREARPATRNUMCONTRATO, LogContSinDocSolConstants.SERV_ALIAS_CREAR, LogContSinDocSolConstants.CLASS_ALIAS, LogContSinDocSolConstants.ARG_ALIAS_CREAR_LOGCONTSINDOCSOLCREARPATRNUMCONTRATO);
        }
    }

    @JsonIgnore
    public String getLogContSinDocSolcrearpatrUsuario() {
        return this.logContSinDocSolcrearpatrUsuario;
    }
    
    public void setLogContSinDocSolcrearpatrUsuario(String logContSinDocSolcrearpatrUsuario) {
        this.logContSinDocSolcrearpatrUsuario = logContSinDocSolcrearpatrUsuario;
    }

    private void checkLogContSinDocSolcrearpatrUsuario() throws NotNullArgumentException {
        if(this.getLogContSinDocSolcrearpatrUsuario() == null) {
            throw new NotNullArgumentException(LogContSinDocSolConstants.SERV_ID_CREAR, LogContSinDocSolConstants.CLASS_ID, LogContSinDocSolConstants.ARG_ID_CREAR_LOGCONTSINDOCSOLCREARPATRUSUARIO, LogContSinDocSolConstants.SERV_ALIAS_CREAR, LogContSinDocSolConstants.CLASS_ALIAS, LogContSinDocSolConstants.ARG_ALIAS_CREAR_LOGCONTSINDOCSOLCREARPATRUSUARIO);
        }
    }
}
