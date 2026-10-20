package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.LogPriorizacionSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogPriorizacionSolOid;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class InsPriorizacionSolicitudesRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance;

    @JsonProperty("p_evclogpriorizacionsol_oid")
    private LogPriorizacionSolOid priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol;
    
    @JsonIgnore
    private LogPriorizacionSol priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes();
        checkPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes = priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance = priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes;
        this.priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes = priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes != null ? priorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudesInsPriorizacionSolicitudespthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_INSPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_INSPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESINSPRIORIZACIONSOLICITUDESPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_INSPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_INSPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESINSPRIORIZACIONSOLICITUDESPTHISPRIORIZACIONSOLICITUDES);
        }
    }

    @JsonIgnore
    public LogPriorizacionSolOid getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol() {
        return this.priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol;
    }
    
    public void setPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol(LogPriorizacionSolOid priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol) {
        this.priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol = priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol;
    }

    @JsonIgnore
    public LogPriorizacionSol getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance() {
        return this.priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance;
    }
    
    public void setPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance(LogPriorizacionSol priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol) {
        this.priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance = priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol;
        this.priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol = priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol != null ? priorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol.getOid() : null;
    }

    private void checkPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSol() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance() == null || this.getPriorizacionSolicitudesInsPriorizacionSolicitudespevcLogPriorizacionSolInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_INSPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_INSPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESINSPRIORIZACIONSOLICITUDESPEVCLOGPRIORIZACIONSOL, PriorizacionSolicitudesConstants.SERV_ALIAS_INSPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_INSPRIORIZACIONSOLICITUDES_PRIORIZACIONSOLICITUDESINSPRIORIZACIONSOLICITUDESPEVCLOGPRIORIZACIONSOL);
        }
    }
}
