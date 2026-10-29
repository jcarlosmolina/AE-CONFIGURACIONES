package com.keralty.aeconfiguraciones.viewmodel.priorizacionsolicitudes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PriorizacionSolicitudesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PriorizacionSolicitudesOid;
import com.keralty.aeconfiguraciones.persistence.PriorizacionSolicitudes;

public class ModificarRequest {

    @JsonProperty("p_thispriorizacionsolicitudes_oid")
    private PriorizacionSolicitudesOid priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes;
    
    @JsonIgnore
    private PriorizacionSolicitudes priorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance;

    @JsonProperty("p_prioridad")
    private Long priorizacionSolicitudesmodificarpPrioridad;

    public void checkArguments() throws NotNullArgumentException {
        checkPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudes();
        checkPriorizacionSolicitudesmodificarpPrioridad();
    }

    @JsonIgnore
    public PriorizacionSolicitudesOid getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudes() {
        return this.priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes;
    }
    
    public void setPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudes(PriorizacionSolicitudesOid priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes = priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes;
    }

    @JsonIgnore
    public PriorizacionSolicitudes getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance() {
        return this.priorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance;
    }
    
    public void setPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance(PriorizacionSolicitudes priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes) {
        this.priorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance = priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes;
        this.priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes = priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes != null ? priorizacionSolicitudesmodificarpthisPriorizacionSolicitudes.getOid() : null;
    }

    private void checkPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudes() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance() == null || this.getPriorizacionSolicitudesmodificarpthisPriorizacionSolicitudesInstance().isNull()) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_MODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_MODIFICAR_PRIORIZACIONSOLICITUDESMODIFICARPTHISPRIORIZACIONSOLICITUDES, PriorizacionSolicitudesConstants.SERV_ALIAS_MODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_MODIFICAR_PRIORIZACIONSOLICITUDESMODIFICARPTHISPRIORIZACIONSOLICITUDES);
        }
    }

    @JsonIgnore
    public Long getPriorizacionSolicitudesmodificarpPrioridad() {
        return this.priorizacionSolicitudesmodificarpPrioridad;
    }
    
    public void setPriorizacionSolicitudesmodificarpPrioridad(Long priorizacionSolicitudesmodificarpPrioridad) {
        this.priorizacionSolicitudesmodificarpPrioridad = priorizacionSolicitudesmodificarpPrioridad;
    }

    private void checkPriorizacionSolicitudesmodificarpPrioridad() throws NotNullArgumentException {
        if(this.getPriorizacionSolicitudesmodificarpPrioridad() == null) {
            throw new NotNullArgumentException(PriorizacionSolicitudesConstants.SERV_ID_MODIFICAR, PriorizacionSolicitudesConstants.CLASS_ID, PriorizacionSolicitudesConstants.ARG_ID_MODIFICAR_PRIORIZACIONSOLICITUDESMODIFICARPPRIORIDAD, PriorizacionSolicitudesConstants.SERV_ALIAS_MODIFICAR, PriorizacionSolicitudesConstants.CLASS_ALIAS, PriorizacionSolicitudesConstants.ARG_ALIAS_MODIFICAR_PRIORIZACIONSOLICITUDESMODIFICARPPRIORIDAD);
        }
    }
}
