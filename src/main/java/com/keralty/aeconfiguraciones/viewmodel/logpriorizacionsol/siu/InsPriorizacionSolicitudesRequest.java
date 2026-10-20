package com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class InsPriorizacionSolicitudesRequest {

    @JsonProperty("p_thislogpriorizacionsol_oid")
    private LogPriorizacionSolOid logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol;
    
    @JsonIgnore
    private LogPriorizacionSol logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance;

    @JsonProperty("p_evcpriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol();
        checkLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes();
    }

    @JsonIgnore
    public LogPriorizacionSolOid getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol() {
        return this.logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol;
    }
    
    public void setLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol(LogPriorizacionSolOid logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol) {
        this.logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol = logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol;
    }

    @JsonIgnore
    public LogPriorizacionSol getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance() {
        return this.logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance;
    }
    
    public void setLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance(LogPriorizacionSol logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol) {
        this.logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance = logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol;
        this.logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol = logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol != null ? logPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol.getOid() : null;
    }

    private void checkLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSol() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance() == null || this.getLogPriorizacionSolInsPriorizacionSolicitudespthisLogPriorizacionSolInstance().isNull()) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_INSPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_INSPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLINSPRIORIZACIONSOLICITUDESPTHISLOGPRIORIZACIONSOL, LogPriorizacionSolConstants.SERV_ALIAS_INSPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_INSPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLINSPRIORIZACIONSOLICITUDESPTHISLOGPRIORIZACIONSOL);
        }
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes() {
        return this.logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes;
    }
    
    public void setLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes(PriorizacionSolicitudesOid logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes) {
        this.logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes = logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance() {
        return this.logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance;
    }
    
    public void setLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance(PriorizacionSolicitudes logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes) {
        this.logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance = logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes;
        this.logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes = logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes != null ? logPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes.getOid() : null;
    }

    private void checkLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance() == null || this.getLogPriorizacionSolInsPriorizacionSolicitudespevcPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_INSPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_INSPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLINSPRIORIZACIONSOLICITUDESPEVCPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.SERV_ALIAS_INSPRIORIZACIONSOLICITUDES, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_INSPRIORIZACIONSOLICITUDES_LOGPRIORIZACIONSOLINSPRIORIZACIONSOLICITUDESPEVCPRIORIZACIONSOLICITUDES);
        }
    }
}
