package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;

public class CrearRequest {

    @JsonProperty("p_atrestadosolicitud")
    private Long priorizacionSolicitudescrearpatrEstadoSolicitud;

    @JsonProperty("p_atrestadodoc")
    private Long priorizacionSolicitudescrearpatrEstadoDoc;

    @JsonProperty("p_atrprioridad")
    private Long priorizacionSolicitudescrearpatrPrioridad;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudescrearpatrEstadoSolicitud();
        checkPriorizacionSolicitudescrearpatrEstadoDoc();
        checkPriorizacionSolicitudescrearpatrPrioridad();
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudescrearpatrEstadoSolicitud() {
        return this.priorizacionSolicitudescrearpatrEstadoSolicitud;
    }
    
    public void setPriorizacionSolicitudescrearpatrEstadoSolicitud(Long priorizacionSolicitudescrearpatrEstadoSolicitud) {
        this.priorizacionSolicitudescrearpatrEstadoSolicitud = priorizacionSolicitudescrearpatrEstadoSolicitud;
    }

    private void checkPriorizacionSolicitudescrearpatrEstadoSolicitud() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudescrearpatrEstadoSolicitud() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_CREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_CREAR_PRIORIZACIONSOLICITUDESCREARPATRESTADOSOLICITUD, PriorizacionSolicitudesConstants.SERV_ALIAS_CREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_CREAR_PRIORIZACIONSOLICITUDESCREARPATRESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudescrearpatrEstadoDoc() {
        return this.priorizacionSolicitudescrearpatrEstadoDoc;
    }
    
    public void setPriorizacionSolicitudescrearpatrEstadoDoc(Long priorizacionSolicitudescrearpatrEstadoDoc) {
        this.priorizacionSolicitudescrearpatrEstadoDoc = priorizacionSolicitudescrearpatrEstadoDoc;
    }

    private void checkPriorizacionSolicitudescrearpatrEstadoDoc() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudescrearpatrEstadoDoc() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_CREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_CREAR_PRIORIZACIONSOLICITUDESCREARPATRESTADODOC, PriorizacionSolicitudesConstants.SERV_ALIAS_CREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_CREAR_PRIORIZACIONSOLICITUDESCREARPATRESTADODOC);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudescrearpatrPrioridad() {
        return this.priorizacionSolicitudescrearpatrPrioridad;
    }
    
    public void setPriorizacionSolicitudescrearpatrPrioridad(Long priorizacionSolicitudescrearpatrPrioridad) {
        this.priorizacionSolicitudescrearpatrPrioridad = priorizacionSolicitudescrearpatrPrioridad;
    }

    private void checkPriorizacionSolicitudescrearpatrPrioridad() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudescrearpatrPrioridad() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_CREAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_CREAR_PRIORIZACIONSOLICITUDESCREARPATRPRIORIDAD, PriorizacionSolicitudesConstants.SERV_ALIAS_CREAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_CREAR_PRIORIZACIONSOLICITUDESCREARPATRPRIORIDAD);
        }
    }
}
