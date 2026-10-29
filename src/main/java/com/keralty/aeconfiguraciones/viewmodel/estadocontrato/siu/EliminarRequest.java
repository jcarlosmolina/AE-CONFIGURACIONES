package com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoContratoConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoContratoOid;

public class EliminarRequest {

    @JsonProperty("p_thisestadocontrato_oid")
    private EstadoContratoOid estadoContratoeliminarpthisEstadoContrato;
    
    @JsonIgnore
    private EstadoContrato estadoContratoeliminarpthisEstadoContratoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoContratoeliminarpthisEstadoContrato();
    }

    @JsonIgnore
    public EstadoContratoOid getEstadoContratoeliminarpthisEstadoContrato() {
        return this.estadoContratoeliminarpthisEstadoContrato;
    }
    
    public void setEstadoContratoeliminarpthisEstadoContrato(EstadoContratoOid estadoContratoeliminarpthisEstadoContrato) {
        this.estadoContratoeliminarpthisEstadoContrato = estadoContratoeliminarpthisEstadoContrato;
    }

    @JsonIgnore
    public EstadoContrato getEstadoContratoeliminarpthisEstadoContratoInstance() {
        return this.estadoContratoeliminarpthisEstadoContratoInstance;
    }
    
    public void setEstadoContratoeliminarpthisEstadoContratoInstance(EstadoContrato estadoContratoeliminarpthisEstadoContrato) {
        this.estadoContratoeliminarpthisEstadoContratoInstance = estadoContratoeliminarpthisEstadoContrato;
        this.estadoContratoeliminarpthisEstadoContrato = estadoContratoeliminarpthisEstadoContrato != null ? estadoContratoeliminarpthisEstadoContrato.getOid() : null;
    }

    private void checkEstadoContratoeliminarpthisEstadoContrato() throws NotNullArgumentException {
        if(this.getEstadoContratoeliminarpthisEstadoContratoInstance() == null || this.getEstadoContratoeliminarpthisEstadoContratoInstance().isNull()) {
            throw new NotNullArgumentException(EstadoContratoConstants.SERV_ID_ELIMINAR, EstadoContratoConstants.CLASS_ID, EstadoContratoConstants.ARG_ID_ELIMINAR_ESTADOCONTRATOELIMINARPTHISESTADOCONTRATO, EstadoContratoConstants.SERV_ALIAS_ELIMINAR, EstadoContratoConstants.CLASS_ALIAS, EstadoContratoConstants.ARG_ALIAS_ELIMINAR_ESTADOCONTRATOELIMINARPTHISESTADOCONTRATO);
        }
    }
}
