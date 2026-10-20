package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;
public class DSPriorizacionSolicitudes {

    @JsonProperty("estadosolicitud")
    private Long dSPriorizacionSolicitudesEstadoSolicitud;

    @JsonProperty("estadodoc")
    private Long dSPriorizacionSolicitudesEstadoDoc;

    @JsonProperty("prioridad")
    private Long dSPriorizacionSolicitudesPrioridad;

    public DSPriorizacionSolicitudes() {
    
    }

    public DSPriorizacionSolicitudes(PriorizacionSolicitudes instance) {
        if (instance != null) {
            this.dSPriorizacionSolicitudesEstadoSolicitud = instance.getPriorizacionSolicitudesEstadoSolicitud();
            this.dSPriorizacionSolicitudesEstadoDoc = instance.getPriorizacionSolicitudesEstadoDoc();
            this.dSPriorizacionSolicitudesPrioridad = instance.getPriorizacionSolicitudesPrioridad();
        }
    }

    @JsonIgnore
    public Long getDSPriorizacionSolicitudesEstadoSolicitud() {
        return this.dSPriorizacionSolicitudesEstadoSolicitud;
    }
    
    public void setDSPriorizacionSolicitudesEstadoSolicitud(Long dSPriorizacionSolicitudesEstadoSolicitud) {
        this.dSPriorizacionSolicitudesEstadoSolicitud = dSPriorizacionSolicitudesEstadoSolicitud;
    }

    @JsonIgnore
    public Long getDSPriorizacionSolicitudesEstadoDoc() {
        return this.dSPriorizacionSolicitudesEstadoDoc;
    }
    
    public void setDSPriorizacionSolicitudesEstadoDoc(Long dSPriorizacionSolicitudesEstadoDoc) {
        this.dSPriorizacionSolicitudesEstadoDoc = dSPriorizacionSolicitudesEstadoDoc;
    }

    @JsonIgnore
    public Long getDSPriorizacionSolicitudesPrioridad() {
        return this.dSPriorizacionSolicitudesPrioridad;
    }
    
    public void setDSPriorizacionSolicitudesPrioridad(Long dSPriorizacionSolicitudesPrioridad) {
        this.dSPriorizacionSolicitudesPrioridad = dSPriorizacionSolicitudesPrioridad;
    }
}
