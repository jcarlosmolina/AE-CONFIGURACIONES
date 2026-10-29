package com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class DelPriorizacionSolicitudesRequest {

    @JsonProperty("p_thislogpriorizacionsol_oid")
    private LogPriorizacionSolOid logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol;
    
    @JsonIgnore
    private LogPriorizacionSol logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance;

    @JsonProperty("p_evcpriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol();
        checkLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes();
    }

    @JsonIgnore
    public LogPriorizacionSolOid getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol() {
        return this.logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol;
    }
    
    public void setLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol(LogPriorizacionSolOid logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol) {
        this.logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol = logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol;
    }

    @JsonIgnore
    public LogPriorizacionSol getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance() {
        return this.logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance;
    }
    
    public void setLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance(LogPriorizacionSol logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol) {
        this.logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance = logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol;
        this.logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol = logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol != null ? logPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol.getOid() : null;
    }

    private void checkLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSol() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance() == null || this.getLogPriorizacionSolDelPriorizacionSolicitudespthisLogPriorizacionSolInstance().isNull()) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_DELPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_DELPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLDELPRIORIZACIONSOLICITUDESPTHISLOGPRIORIZACIONSOL, LogPriorizacionSolConstants.SERV_ALIAS_DELPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_DELPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLDELPRIORIZACIONSOLICITUDESPTHISLOGPRIORIZACIONSOL);
        }
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes() {
        return this.logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes;
    }
    
    public void setLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes(PriorizacionSolicitudesOid logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes) {
        this.logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes = logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance() {
        return this.logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance;
    }
    
    public void setLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(PriorizacionSolicitudes logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes) {
        this.logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance = logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes;
        this.logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes = logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes != null ? logPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes.getOid() : null;
    }

    private void checkLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance() == null || this.getLogPriorizacionSolDelPriorizacionSolicitudespevcPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_DELPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_DELPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLDELPRIORIZACIONSOLICITUDESPEVCPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.SERV_ALIAS_DELPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_DELPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLDELPRIORIZACIONSOLICITUDESPEVCPRIORIZACIONSOLICITUDES);
        }
    }
}
