package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class TELIMINARRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance;

    @JsonProperty("ptestadosolicitud")
    private Long priorizacionSolicitudesTELIMINARptEstadoSolicitud;

    @JsonProperty("ptestadodoc")
    private Long priorizacionSolicitudesTELIMINARptEstadoDoc;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes();
        checkPriorizacionSolicitudesTELIMINARptEstadoSolicitud();
        checkPriorizacionSolicitudesTELIMINARptEstadoDoc();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes = priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance = priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes;
        this.priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes = priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes != null ? priorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudesTELIMINARpthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTHISPRIORIZACIONSOLICITUDES);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTELIMINARptEstadoSolicitud() {
        return this.priorizacionSolicitudesTELIMINARptEstadoSolicitud;
    }
    
    public void setPriorizacionSolicitudesTELIMINARptEstadoSolicitud(Long priorizacionSolicitudesTELIMINARptEstadoSolicitud) {
        this.priorizacionSolicitudesTELIMINARptEstadoSolicitud = priorizacionSolicitudesTELIMINARptEstadoSolicitud;
    }

    private void checkPriorizacionSolicitudesTELIMINARptEstadoSolicitud() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTELIMINARptEstadoSolicitud() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTESTADOSOLICITUD, PriorizacionSolicitudesConstants.SERV_ALIAS_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTESTADOSOLICITUD);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesTELIMINARptEstadoDoc() {
        return this.priorizacionSolicitudesTELIMINARptEstadoDoc;
    }
    
    public void setPriorizacionSolicitudesTELIMINARptEstadoDoc(Long priorizacionSolicitudesTELIMINARptEstadoDoc) {
        this.priorizacionSolicitudesTELIMINARptEstadoDoc = priorizacionSolicitudesTELIMINARptEstadoDoc;
    }

    private void checkPriorizacionSolicitudesTELIMINARptEstadoDoc() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesTELIMINARptEstadoDoc() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTESTADODOC, PriorizacionSolicitudesConstants.SERV_ALIAS_TELIMINAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_TELIMINAR_PRIORIZACIONSOLICITUDESTELIMINARPTESTADODOC);
        }
    }
}
