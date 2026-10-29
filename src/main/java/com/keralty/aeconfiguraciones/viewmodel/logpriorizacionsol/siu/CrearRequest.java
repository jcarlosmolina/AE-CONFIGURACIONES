package com.keralty.aeconfiguraciones.viewmodel.logpriorizacionsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogPriorizacionSolConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class CrearRequest {

    @JsonProperty("p_agrprisolicitud_oid")
    private PriorizacionSolicitudesOid logPriorizacionSolcrearpagrPriSolicitud;
    
    @JsonIgnore
    private PriorizacionSolicitudes logPriorizacionSolcrearpagrPriSolicitudInstance;

    @JsonProperty("p_atrestadosolicitud")
    private Long logPriorizacionSolcrearpatrEstadoSolicitud;

    @JsonProperty("p_atrestadodoc")
    private Long logPriorizacionSolcrearpatrEstadoDoc;

    @JsonProperty("p_atrprioridad")
    private Long logPriorizacionSolcrearpatrPrioridad;

    @JsonProperty("p_atrloginfuncionario")
    private String logPriorizacionSolcrearpatrLoginFuncionario;

    public void checkArguments() throws NotNullArgumentException {
        checkLogPriorizacionSolcrearpatrEstadoSolicitud();
        checkLogPriorizacionSolcrearpatrEstadoDoc();
        checkLogPriorizacionSolcrearpatrPrioridad();
        checkLogPriorizacionSolcrearpatrLoginFuncionario();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getLogPriorizacionSolcrearpagrPriSolicitud() {
        return this.logPriorizacionSolcrearpagrPriSolicitud;
    }
    
    public void setLogPriorizacionSolcrearpagrPriSolicitud(PriorizacionSolicitudesOid logPriorizacionSolcrearpagrPriSolicitud) {
        this.logPriorizacionSolcrearpagrPriSolicitud = logPriorizacionSolcrearpagrPriSolicitud;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getLogPriorizacionSolcrearpagrPriSolicitudInstance() {
        return this.logPriorizacionSolcrearpagrPriSolicitudInstance;
    }
    
    public void setLogPriorizacionSolcrearpagrPriSolicitudInstance(PriorizacionSolicitudes logPriorizacionSolcrearpagrPriSolicitud) {
        this.logPriorizacionSolcrearpagrPriSolicitudInstance = logPriorizacionSolcrearpagrPriSolicitud;
        this.logPriorizacionSolcrearpagrPriSolicitud = logPriorizacionSolcrearpagrPriSolicitud != null ? logPriorizacionSolcrearpagrPriSolicitud.getOid() : null;
    }

    @JsonIgnore
    public Long getLogPriorizacionSolcrearpatrEstadoSolicitud() {
        return this.logPriorizacionSolcrearpatrEstadoSolicitud;
    }
    
    public void setLogPriorizacionSolcrearpatrEstadoSolicitud(Long logPriorizacionSolcrearpatrEstadoSolicitud) {
        this.logPriorizacionSolcrearpatrEstadoSolicitud = logPriorizacionSolcrearpatrEstadoSolicitud;
    }

    private void checkLogPriorizacionSolcrearpatrEstadoSolicitud() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolcrearpatrEstadoSolicitud() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_CREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_CREAR_LOGPRIORIZACIONSOLCREARPATRESTADOSOLICITUD, LogPriorizacionSolConstants.SERV_ALIAS_CREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_CREAR_LOGPRIORIZACIONSOLCREARPATRESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getLogPriorizacionSolcrearpatrEstadoDoc() {
        return this.logPriorizacionSolcrearpatrEstadoDoc;
    }
    
    public void setLogPriorizacionSolcrearpatrEstadoDoc(Long logPriorizacionSolcrearpatrEstadoDoc) {
        this.logPriorizacionSolcrearpatrEstadoDoc = logPriorizacionSolcrearpatrEstadoDoc;
    }

    private void checkLogPriorizacionSolcrearpatrEstadoDoc() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolcrearpatrEstadoDoc() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_CREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_CREAR_LOGPRIORIZACIONSOLCREARPATRESTADODOC, LogPriorizacionSolConstants.SERV_ALIAS_CREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_CREAR_LOGPRIORIZACIONSOLCREARPATRESTADODOC);
        }
    }

    @JsonIgnore
    public Long getLogPriorizacionSolcrearpatrPrioridad() {
        return this.logPriorizacionSolcrearpatrPrioridad;
    }
    
    public void setLogPriorizacionSolcrearpatrPrioridad(Long logPriorizacionSolcrearpatrPrioridad) {
        this.logPriorizacionSolcrearpatrPrioridad = logPriorizacionSolcrearpatrPrioridad;
    }

    private void checkLogPriorizacionSolcrearpatrPrioridad() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolcrearpatrPrioridad() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_CREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_CREAR_LOGPRIORIZACIONSOLCREARPATRPRIORIDAD, LogPriorizacionSolConstants.SERV_ALIAS_CREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_CREAR_LOGPRIORIZACIONSOLCREARPATRPRIORIDAD);
        }
    }

    @JsonIgnore
    public String getLogPriorizacionSolcrearpatrLoginFuncionario() {
        return this.logPriorizacionSolcrearpatrLoginFuncionario;
    }
    
    public void setLogPriorizacionSolcrearpatrLoginFuncionario(String logPriorizacionSolcrearpatrLoginFuncionario) {
        this.logPriorizacionSolcrearpatrLoginFuncionario = logPriorizacionSolcrearpatrLoginFuncionario;
    }

    private void checkLogPriorizacionSolcrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogPriorizacionSolcrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogPriorizacionSolConstants.SERV_ID_CREAR, LogPriorizacionSolConstants.CLASS_ID, LogPriorizacionSolConstants.ARG_ID_CREAR_LOGPRIORIZACIONSOLCREARPATRLOGINFUNCIONARIO, LogPriorizacionSolConstants.SERV_ALIAS_CREAR, LogPriorizacionSolConstants.CLASS_ALIAS, LogPriorizacionSolConstants.ARG_ALIAS_CREAR_LOGPRIORIZACIONSOLCREARPATRLOGINFUNCIONARIO);
        }
    }
}
