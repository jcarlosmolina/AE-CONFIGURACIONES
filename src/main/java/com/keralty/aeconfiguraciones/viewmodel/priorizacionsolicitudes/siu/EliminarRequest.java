package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class EliminarRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudes();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes = priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance = priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes;
        this.priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes = priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes != null ? priorizacionSolicitudeseliminarpthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudeseliminarpthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_ELIMINAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_ELIMINAR_PRIORIZACIONSOLICITUDESELIMINARPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_ELIMINAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_ELIMINAR_PRIORIZACIONSOLICITUDESELIMINARPTHISPRIORIZACIONSOLICITUDES);
        }
    }
}
