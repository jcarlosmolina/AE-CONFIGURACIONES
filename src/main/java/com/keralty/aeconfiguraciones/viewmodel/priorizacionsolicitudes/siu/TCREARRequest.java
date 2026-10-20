package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;

public class TCREARRequest {

    @JsonProperty("pt_p_atrestadosolicitud")
    private Long priorizacionSolicitudesTCREARptpatrEstadoSolicitud;

    @JsonProperty("pt_p_atrestadodoc")
    private Long priorizacionSolicitudesTCREARptpatrEstadoDoc;

    @JsonProperty("pt_p_atrprioridad")
    private Long priorizacionSolicitudesTCREARptpatrPrioridad;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesTCREARptpatrEstadoSolicitud();
        checkPriorizacionSolicitudesTCREARptpatrEstadoDoc();
        checkPriorizacionSolicitudesTCREARptpatrPrioridad();
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTCREARptpatrEstadoSolicitud() {
        return this.priorizacionSolicitudesTCREARptpatrEstadoSolicitud;
    }
    
    public void setPriorizacionSolicitudesTCREARptpatrEstadoSolicitud(Long priorizacionSolicitudesTCREARptpatrEstadoSolicitud) {
        this.priorizacionSolicitudesTCREARptpatrEstadoSolicitud = priorizacionSolicitudesTCREARptpatrEstadoSolicitud;
    }

    private void checkPriorizacionSolicitudesTCREARptpatrEstadoSolicitud() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTCREARptpatrEstadoSolicitud() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TCREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRESTADOSOLICITUD, PriorizacionSolicitudesConstants.SERV_ALIAS_TCREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTCREARptpatrEstadoDoc() {
        return this.priorizacionSolicitudesTCREARptpatrEstadoDoc;
    }
    
    public void setPriorizacionSolicitudesTCREARptpatrEstadoDoc(Long priorizacionSolicitudesTCREARptpatrEstadoDoc) {
        this.priorizacionSolicitudesTCREARptpatrEstadoDoc = priorizacionSolicitudesTCREARptpatrEstadoDoc;
    }

    private void checkPriorizacionSolicitudesTCREARptpatrEstadoDoc() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTCREARptpatrEstadoDoc() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TCREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRESTADODOC, PriorizacionSolicitudesConstants.SERV_ALIAS_TCREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRESTADODOC);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTCREARptpatrPrioridad() {
        return this.priorizacionSolicitudesTCREARptpatrPrioridad;
    }
    
    public void setPriorizacionSolicitudesTCREARptpatrPrioridad(Long priorizacionSolicitudesTCREARptpatrPrioridad) {
        this.priorizacionSolicitudesTCREARptpatrPrioridad = priorizacionSolicitudesTCREARptpatrPrioridad;
    }

    private void checkPriorizacionSolicitudesTCREARptpatrPrioridad() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTCREARptpatrPrioridad() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TCREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRPRIORIDAD, PriorizacionSolicitudesConstants.SERV_ALIAS_TCREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TCREAR_PRIORIZACIONSOLICITUDESTCREARPTPATRPRIORIDAD);
        }
    }
}
