package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogLlamadasAEConstants;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasAE;
import com.keralty.aeconfiguraciones.persistence.oid.LogLlamadasAEOid;

public class DeleteinstanceRequest {

    @JsonProperty("p_thislogllamadasae_oid")
    private LogLlamadasAEOid logLlamadasAEdeleteinstancepthisLogLlamadasAE;
    
    @JsonIgnore
    private LogLlamadasAE logLlamadasAEdeleteinstancepthisLogLlamadasAEInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogLlamadasAEdeleteinstancepthisLogLlamadasAE();
    }

    @JsonIgnore
    public LogLlamadasAEOid getLogLlamadasAEdeleteinstancepthisLogLlamadasAE() {
        return this.logLlamadasAEdeleteinstancepthisLogLlamadasAE;
    }
    
    public void setLogLlamadasAEdeleteinstancepthisLogLlamadasAE(LogLlamadasAEOid logLlamadasAEdeleteinstancepthisLogLlamadasAE) {
        this.logLlamadasAEdeleteinstancepthisLogLlamadasAE = logLlamadasAEdeleteinstancepthisLogLlamadasAE;
    }

    @JsonIgnore
    public LogLlamadasAE getLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance() {
        return this.logLlamadasAEdeleteinstancepthisLogLlamadasAEInstance;
    }
    
    public void setLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance(LogLlamadasAE logLlamadasAEdeleteinstancepthisLogLlamadasAE) {
        this.logLlamadasAEdeleteinstancepthisLogLlamadasAEInstance = logLlamadasAEdeleteinstancepthisLogLlamadasAE;
        this.logLlamadasAEdeleteinstancepthisLogLlamadasAE = logLlamadasAEdeleteinstancepthisLogLlamadasAE != null ? logLlamadasAEdeleteinstancepthisLogLlamadasAE.getOid() : null;
    }

    private void checkLogLlamadasAEdeleteinstancepthisLogLlamadasAE() throws NotNullArgumentException {
        if(this.getLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance() == null || this.getLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance().isNull()) {
            throw new NotNullArgumentException(LogLlamadasAEConstants.SERV_ID_DELETEINSTANCE, LogLlamadasAEConstants.CLASS_ID, LogLlamadasAEConstants.ARG_ID_DELETEINSTANCE_LOGLLAMADASAEDELETEINSTANCEPTHISLOGLLAMADASAE, LogLlamadasAEConstants.SERV_ALIAS_DELETEINSTANCE, LogLlamadasAEConstants.CLASS_ALIAS, LogLlamadasAEConstants.ARG_ALIAS_DELETEINSTANCE_LOGLLAMADASAEDELETEINSTANCEPTHISLOGLLAMADASAE);
        }
    }
}
