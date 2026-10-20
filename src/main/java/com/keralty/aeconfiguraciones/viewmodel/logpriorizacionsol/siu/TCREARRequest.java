package com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class TCREARRequest {

    @JsonProperty("pt_p_agrpriorizacionsolicitude_oid")
    private PriorizacionSolicitudesOid logPriorizacionSolTCREARptpagrPriorizacionSolicitude;
    
    @JsonIgnore
    private PriorizacionSolicitudes logPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance;

    @JsonProperty("pt_p_atrestadosolicitud")
    private Long logPriorizacionSolTCREARptpatrEstadoSolicitud;

    @JsonProperty("pt_p_atrestadodoc")
    private Long logPriorizacionSolTCREARptpatrEstadoDoc;

    @JsonProperty("pt_p_atrprioridad")
    private Long logPriorizacionSolTCREARptpatrPrioridad;

    public void checkArguments() throws NotNullArgumentException {
        checkLogPriorizacionSolTCREARptpatrEstadoSolicitud();
        checkLogPriorizacionSolTCREARptpatrEstadoDoc();
        checkLogPriorizacionSolTCREARptpatrPrioridad();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getLogPriorizacionSolTCREARptpagrPriorizacionSolicitude() {
        return this.logPriorizacionSolTCREARptpagrPriorizacionSolicitude;
    }
    
    public void setLogPriorizacionSolTCREARptpagrPriorizacionSolicitude(PriorizacionSolicitudesOid logPriorizacionSolTCREARptpagrPriorizacionSolicitude) {
        this.logPriorizacionSolTCREARptpagrPriorizacionSolicitude = logPriorizacionSolTCREARptpagrPriorizacionSolicitude;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance() {
        return this.logPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance;
    }
    
    public void setLogPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance(PriorizacionSolicitudes logPriorizacionSolTCREARptpagrPriorizacionSolicitude) {
        this.logPriorizacionSolTCREARptpagrPriorizacionSolicitudeInstance = logPriorizacionSolTCREARptpagrPriorizacionSolicitude;
        this.logPriorizacionSolTCREARptpagrPriorizacionSolicitude = logPriorizacionSolTCREARptpagrPriorizacionSolicitude != null ? logPriorizacionSolTCREARptpagrPriorizacionSolicitude.getOid() : null;
    }

    @JsonIgnore
    public Long getLogPriorizacionSolTCREARptpatrEstadoSolicitud() {
        return this.logPriorizacionSolTCREARptpatrEstadoSolicitud;
    }
    
    public void setLogPriorizacionSolTCREARptpatrEstadoSolicitud(Long logPriorizacionSolTCREARptpatrEstadoSolicitud) {
        this.logPriorizacionSolTCREARptpatrEstadoSolicitud = logPriorizacionSolTCREARptpatrEstadoSolicitud;
    }

    private void checkLogPriorizacionSolTCREARptpatrEstadoSolicitud() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolTCREARptpatrEstadoSolicitud() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_TCREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRESTADOSOLICITUD, LogPriorizacionSolConstants.SERV_ALIAS_TCREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getLogPriorizacionSolTCREARptpatrEstadoDoc() {
        return this.logPriorizacionSolTCREARptpatrEstadoDoc;
    }
    
    public void setLogPriorizacionSolTCREARptpatrEstadoDoc(Long logPriorizacionSolTCREARptpatrEstadoDoc) {
        this.logPriorizacionSolTCREARptpatrEstadoDoc = logPriorizacionSolTCREARptpatrEstadoDoc;
    }

    private void checkLogPriorizacionSolTCREARptpatrEstadoDoc() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolTCREARptpatrEstadoDoc() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_TCREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRESTADODOC, LogPriorizacionSolConstants.SERV_ALIAS_TCREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRESTADODOC);
        }
    }

    @JsonIgnore
    public Long getLogPriorizacionSolTCREARptpatrPrioridad() {
        return this.logPriorizacionSolTCREARptpatrPrioridad;
    }
    
    public void setLogPriorizacionSolTCREARptpatrPrioridad(Long logPriorizacionSolTCREARptpatrPrioridad) {
        this.logPriorizacionSolTCREARptpatrPrioridad = logPriorizacionSolTCREARptpatrPrioridad;
    }

    private void checkLogPriorizacionSolTCREARptpatrPrioridad() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolTCREARptpatrPrioridad() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_TCREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRPRIORIDAD, LogPriorizacionSolConstants.SERV_ALIAS_TCREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_TCREAR_LOGPRIORIZACIONSOLTCREARPTPATRPRIORIDAD);
        }
    }
}
