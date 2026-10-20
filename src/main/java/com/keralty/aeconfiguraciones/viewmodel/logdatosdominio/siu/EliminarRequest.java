package com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogDatosDominioConstants;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.oid.LogDatosDominioOid;

public class EliminarRequest {

    @JsonProperty("p_thislogdatosdominio_oid")
    private LogDatosDominioOid logDatosDominioeliminarpthisLogDatosDominio;
    
    @JsonIgnore
    private LogDatosDominio logDatosDominioeliminarpthisLogDatosDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogDatosDominioeliminarpthisLogDatosDominio();
    }

    @JsonIgnore
    public LogDatosDominioOid getLogDatosDominioeliminarpthisLogDatosDominio() {
        return this.logDatosDominioeliminarpthisLogDatosDominio;
    }
    
    public void setLogDatosDominioeliminarpthisLogDatosDominio(LogDatosDominioOid logDatosDominioeliminarpthisLogDatosDominio) {
        this.logDatosDominioeliminarpthisLogDatosDominio = logDatosDominioeliminarpthisLogDatosDominio;
    }

    @JsonIgnore
    public LogDatosDominio getLogDatosDominioeliminarpthisLogDatosDominioInstance() {
        return this.logDatosDominioeliminarpthisLogDatosDominioInstance;
    }
    
    public void setLogDatosDominioeliminarpthisLogDatosDominioInstance(LogDatosDominio logDatosDominioeliminarpthisLogDatosDominio) {
        this.logDatosDominioeliminarpthisLogDatosDominioInstance = logDatosDominioeliminarpthisLogDatosDominio;
        this.logDatosDominioeliminarpthisLogDatosDominio = logDatosDominioeliminarpthisLogDatosDominio != null ? logDatosDominioeliminarpthisLogDatosDominio.getOid() : null;
    }

    private void checkLogDatosDominioeliminarpthisLogDatosDominio() throws NotNullArgumentException {
        if(this.getLogDatosDominioeliminarpthisLogDatosDominioInstance() == null || this.getLogDatosDominioeliminarpthisLogDatosDominioInstance().isNull()) {
            throw new NotNullArgumentException(LogDatosDominioConstants.SERV_ID_ELIMINAR, LogDatosDominioConstants.CLASS_ID, LogDatosDominioConstants.ARG_ID_ELIMINAR_LOGDATOSDOMINIOELIMINARPTHISLOGDATOSDOMINIO, LogDatosDominioConstants.SERV_ALIAS_ELIMINAR, LogDatosDominioConstants.CLASS_ALIAS, LogDatosDominioConstants.ARG_ALIAS_ELIMINAR_LOGDATOSDOMINIOELIMINARPTHISLOGDATOSDOMINIO);
        }
    }
}
