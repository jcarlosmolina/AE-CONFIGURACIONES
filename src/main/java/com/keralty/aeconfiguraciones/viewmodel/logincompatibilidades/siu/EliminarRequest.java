package com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogIncompatibilidadesConstants;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.oid.LogIncompatibilidadesOid;

public class EliminarRequest {

    @JsonProperty("p_thislogincompatibilidades_oid")
    private LogIncompatibilidadesOid logIncompatibilidadeseliminarpthisLogIncompatibilidades;
    
    @JsonIgnore
    private LogIncompatibilidades logIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogIncompatibilidadeseliminarpthisLogIncompatibilidades();
    }

    @JsonIgnore
    public LogIncompatibilidadesOid getLogIncompatibilidadeseliminarpthisLogIncompatibilidades() {
        return this.logIncompatibilidadeseliminarpthisLogIncompatibilidades;
    }
    
    public void setLogIncompatibilidadeseliminarpthisLogIncompatibilidades(LogIncompatibilidadesOid logIncompatibilidadeseliminarpthisLogIncompatibilidades) {
        this.logIncompatibilidadeseliminarpthisLogIncompatibilidades = logIncompatibilidadeseliminarpthisLogIncompatibilidades;
    }

    @JsonIgnore
    public LogIncompatibilidades getLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance() {
        return this.logIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance;
    }
    
    public void setLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance(LogIncompatibilidades logIncompatibilidadeseliminarpthisLogIncompatibilidades) {
        this.logIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance = logIncompatibilidadeseliminarpthisLogIncompatibilidades;
        this.logIncompatibilidadeseliminarpthisLogIncompatibilidades = logIncompatibilidadeseliminarpthisLogIncompatibilidades != null ? logIncompatibilidadeseliminarpthisLogIncompatibilidades.getOid() : null;
    }

    private void checkLogIncompatibilidadeseliminarpthisLogIncompatibilidades() throws NotNullArgumentException {
        if(this.getLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance() == null || this.getLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance().isNull()) {
            throw new NotNullArgumentException(LogIncompatibilidadesConstants.SERV_ID_ELIMINAR, LogIncompatibilidadesConstants.CLASS_ID, LogIncompatibilidadesConstants.ARG_ID_ELIMINAR_LOGINCOMPATIBILIDADESELIMINARPTHISLOGINCOMPATIBILIDADES, LogIncompatibilidadesConstants.SERV_ALIAS_ELIMINAR, LogIncompatibilidadesConstants.CLASS_ALIAS, LogIncompatibilidadesConstants.ARG_ALIAS_ELIMINAR_LOGINCOMPATIBILIDADESELIMINARPTHISLOGINCOMPATIBILIDADES);
        }
    }
}
