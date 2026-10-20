package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class DelPriorizacionSolicitudesRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance;

    @JsonProperty("p_evclogpriorizacionsol_oid")
    private LogPriorizacionSolOid priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol;
    
    @JsonIgnore
    private LogPriorizacionSol priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes();
        checkPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes = priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance = priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes;
        this.priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes = priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes != null ? priorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudesDelPriorizacionSolicitudespthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_DELPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_DELPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESDELPRIORIZACIONSOLICITUDESPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_DELPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_DELPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESDELPRIORIZACIONSOLICITUDESPTHISPRIORIZACIONSOLICITUDES);
        }
    }

    @JsonIgnore
    public LogPriorizacionSolOid getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol() {
        return this.priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol;
    }
    
    public void setPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol(LogPriorizacionSolOid priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol) {
        this.priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol = priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol;
    }

    @JsonIgnore
    public LogPriorizacionSol getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance() {
        return this.priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance;
    }
    
    public void setPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance(LogPriorizacionSol priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol) {
        this.priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance = priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol;
        this.priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol = priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol != null ? priorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol.getOid() : null;
    }

    private void checkPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSol() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance() == null || this.getPriorizacionSolicitudesDelPriorizacionSolicitudespevcLogPriorizacionSolInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_DELPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_DELPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESDELPRIORIZACIONSOLICITUDESPEVCLOGPRIORIZACIONSOL, PriorizacionSolicitudesConstants.SERV_ALIAS_DELPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_DELPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESDELPRIORIZACIONSOLICITUDESPEVCLOGPRIORIZACIONSOL);
        }
    }
}
