package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class TMODIFICARRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance;

    @JsonProperty("pt_p_prioridad")
    private Long priorizacionSolicitudesTMODIFICARptpPrioridad;

    @JsonProperty("ptestadosolicitud")
    private Long priorizacionSolicitudesTMODIFICARptEstadoSolicitud;

    @JsonProperty("ptestadodoc")
    private Long priorizacionSolicitudesTMODIFICARptEstadoDoc;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes();
        checkPriorizacionSolicitudesTMODIFICARptpPrioridad();
        checkPriorizacionSolicitudesTMODIFICARptEstadoSolicitud();
        checkPriorizacionSolicitudesTMODIFICARptEstadoDoc();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes = priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance = priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes;
        this.priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes = priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes != null ? priorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudesTMODIFICARpthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTHISPRIORIZACIONSOLICITUDES);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTMODIFICARptpPrioridad() {
        return this.priorizacionSolicitudesTMODIFICARptpPrioridad;
    }
    
    public void setPriorizacionSolicitudesTMODIFICARptpPrioridad(Long priorizacionSolicitudesTMODIFICARptpPrioridad) {
        this.priorizacionSolicitudesTMODIFICARptpPrioridad = priorizacionSolicitudesTMODIFICARptpPrioridad;
    }

    private void checkPriorizacionSolicitudesTMODIFICARptpPrioridad() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTMODIFICARptpPrioridad() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTPPRIORIDAD, PriorizacionSolicitudesConstants.SERV_ALIAS_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTPPRIORIDAD);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTMODIFICARptEstadoSolicitud() {
        return this.priorizacionSolicitudesTMODIFICARptEstadoSolicitud;
    }
    
    public void setPriorizacionSolicitudesTMODIFICARptEstadoSolicitud(Long priorizacionSolicitudesTMODIFICARptEstadoSolicitud) {
        this.priorizacionSolicitudesTMODIFICARptEstadoSolicitud = priorizacionSolicitudesTMODIFICARptEstadoSolicitud;
    }

    private void checkPriorizacionSolicitudesTMODIFICARptEstadoSolicitud() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTMODIFICARptEstadoSolicitud() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTESTADOSOLICITUD, PriorizacionSolicitudesConstants.SERV_ALIAS_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTMODIFICARptEstadoDoc() {
        return this.priorizacionSolicitudesTMODIFICARptEstadoDoc;
    }
    
    public void setPriorizacionSolicitudesTMODIFICARptEstadoDoc(Long priorizacionSolicitudesTMODIFICARptEstadoDoc) {
        this.priorizacionSolicitudesTMODIFICARptEstadoDoc = priorizacionSolicitudesTMODIFICARptEstadoDoc;
    }

    private void checkPriorizacionSolicitudesTMODIFICARptEstadoDoc() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTMODIFICARptEstadoDoc() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTESTADODOC, PriorizacionSolicitudesConstants.SERV_ALIAS_TMODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TMODIFICAR_PRIORIZACIONSOLICITUDESTMODIFICARPTESTADODOC);
        }
    }
}
