package com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.LogConfiguracionANSOid;

public class EliminarRequest {

    @JsonProperty("p_thislogconfiguracionans_oid")
    private LogConfiguracionANSOid logConfiguracionANSeliminarpthisLogConfiguracionANS;
    
    @JsonIgnore
    private LogConfiguracionANS logConfiguracionANSeliminarpthisLogConfiguracionANSInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogConfiguracionANSeliminarpthisLogConfiguracionANS();
    }

    @JsonIgnore
    public LogConfiguracionANSOid getLogConfiguracionANSeliminarpthisLogConfiguracionANS() {
        return this.logConfiguracionANSeliminarpthisLogConfiguracionANS;
    }
    
    public void setLogConfiguracionANSeliminarpthisLogConfiguracionANS(LogConfiguracionANSOid logConfiguracionANSeliminarpthisLogConfiguracionANS) {
        this.logConfiguracionANSeliminarpthisLogConfiguracionANS = logConfiguracionANSeliminarpthisLogConfiguracionANS;
    }

    @JsonIgnore
    public LogConfiguracionANS getLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance() {
        return this.logConfiguracionANSeliminarpthisLogConfiguracionANSInstance;
    }
    
    public void setLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance(LogConfiguracionANS logConfiguracionANSeliminarpthisLogConfiguracionANS) {
        this.logConfiguracionANSeliminarpthisLogConfiguracionANSInstance = logConfiguracionANSeliminarpthisLogConfiguracionANS;
        this.logConfiguracionANSeliminarpthisLogConfiguracionANS = logConfiguracionANSeliminarpthisLogConfiguracionANS != null ? logConfiguracionANSeliminarpthisLogConfiguracionANS.getOid() : null;
    }

    private void checkLogConfiguracionANSeliminarpthisLogConfiguracionANS() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance() == null || this.getLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_ELIMINAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_ELIMINAR_LOGCONFIGURACIONANSELIMINARPTHISLOGCONFIGURACIONANS, LogConfiguracionANSConstants.SERV_ALIAS_ELIMINAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_ELIMINAR_LOGCONFIGURACIONANSELIMINARPTHISLOGCONFIGURACIONANS);
        }
    }
}
