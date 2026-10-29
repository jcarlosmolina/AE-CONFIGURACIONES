package com.keralty.aeconfiguraciones.viewmodel.logllamadasbh.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogLlamadasBHConstants;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasBH;
import com.keralty.aeconfiguraciones.persistence.oid.LogLlamadasBHOid;

public class DeleteinstanceRequest {

    @JsonProperty("p_thislogllamadasbh_oid")
    private LogLlamadasBHOid logLlamadasBHdeleteinstancepthisLogLlamadasBH;
    
    @JsonIgnore
    private LogLlamadasBH logLlamadasBHdeleteinstancepthisLogLlamadasBHInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogLlamadasBHdeleteinstancepthisLogLlamadasBH();
    }

    @JsonIgnore
    public LogLlamadasBHOid getLogLlamadasBHdeleteinstancepthisLogLlamadasBH() {
        return this.logLlamadasBHdeleteinstancepthisLogLlamadasBH;
    }
    
    public void setLogLlamadasBHdeleteinstancepthisLogLlamadasBH(LogLlamadasBHOid logLlamadasBHdeleteinstancepthisLogLlamadasBH) {
        this.logLlamadasBHdeleteinstancepthisLogLlamadasBH = logLlamadasBHdeleteinstancepthisLogLlamadasBH;
    }

    @JsonIgnore
    public LogLlamadasBH getLogLlamadasBHdeleteinstancepthisLogLlamadasBHInstance() {
        return this.logLlamadasBHdeleteinstancepthisLogLlamadasBHInstance;
    }
    
    public void setLogLlamadasBHdeleteinstancepthisLogLlamadasBHInstance(LogLlamadasBH logLlamadasBHdeleteinstancepthisLogLlamadasBH) {
        this.logLlamadasBHdeleteinstancepthisLogLlamadasBHInstance = logLlamadasBHdeleteinstancepthisLogLlamadasBH;
        this.logLlamadasBHdeleteinstancepthisLogLlamadasBH = logLlamadasBHdeleteinstancepthisLogLlamadasBH != null ? logLlamadasBHdeleteinstancepthisLogLlamadasBH.getOid() : null;
    }

    private void checkLogLlamadasBHdeleteinstancepthisLogLlamadasBH() throws NotNullArgumentException {
        if(this.getLogLlamadasBHdeleteinstancepthisLogLlamadasBHInstance() == null || this.getLogLlamadasBHdeleteinstancepthisLogLlamadasBHInstance().isNull()) {
            throw new NotNullArgumentException(LogLlamadasBHConstants.SERV_ID_DELETEINSTANCE, LogLlamadasBHConstants.CLASS_ID, LogLlamadasBHConstants.ARG_ID_DELETEINSTANCE_LOGLLAMADASBHDELETEINSTANCEPTHISLOGLLAMADASBH, LogLlamadasBHConstants.SERV_ALIAS_DELETEINSTANCE, LogLlamadasBHConstants.CLASS_ALIAS, LogLlamadasBHConstants.ARG_ALIAS_DELETEINSTANCE_LOGLLAMADASBHDELETEINSTANCEPTHISLOGLLAMADASBH);
        }
    }
}
