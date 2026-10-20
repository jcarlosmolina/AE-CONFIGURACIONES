package com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoContratoConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoContratoOid;

public class SincronizarRequest {

    @JsonProperty("p_thisestadocontrato_oid")
    private EstadoContratoOid estadoContratosincronizarpthisEstadoContrato;
    
    @JsonIgnore
    private EstadoContrato estadoContratosincronizarpthisEstadoContratoInstance;

    @JsonProperty("p_descripcion")
    private String estadoContratosincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoContratosincronizarpthisEstadoContrato();
        checkEstadoContratosincronizarpDescripcion();
    }

    @JsonIgnore
    public EstadoContratoOid getEstadoContratosincronizarpthisEstadoContrato() {
        return this.estadoContratosincronizarpthisEstadoContrato;
    }
    
    public void setEstadoContratosincronizarpthisEstadoContrato(EstadoContratoOid estadoContratosincronizarpthisEstadoContrato) {
        this.estadoContratosincronizarpthisEstadoContrato = estadoContratosincronizarpthisEstadoContrato;
    }

    @JsonIgnore
    public EstadoContrato getEstadoContratosincronizarpthisEstadoContratoInstance() {
        return this.estadoContratosincronizarpthisEstadoContratoInstance;
    }
    
    public void setEstadoContratosincronizarpthisEstadoContratoInstance(EstadoContrato estadoContratosincronizarpthisEstadoContrato) {
        this.estadoContratosincronizarpthisEstadoContratoInstance = estadoContratosincronizarpthisEstadoContrato;
        this.estadoContratosincronizarpthisEstadoContrato = estadoContratosincronizarpthisEstadoContrato != null ? estadoContratosincronizarpthisEstadoContrato.getOid() : null;
    }

    private void checkEstadoContratosincronizarpthisEstadoContrato() throws NotNullArgumentException {
        if(this.getEstadoContratosincronizarpthisEstadoContratoInstance() == null || this.getEstadoContratosincronizarpthisEstadoContratoInstance().isNull()) {
            throw new NotNullArgumentException(EstadoContratoConstants.SERV_ID_SINCRONIZAR, EstadoContratoConstants.CLASS_ID, EstadoContratoConstants.ARG_ID_SINCRONIZAR_ESTADOCONTRATOSINCRONIZARPTHISESTADOCONTRATO, EstadoContratoConstants.SERV_ALIAS_SINCRONIZAR, EstadoContratoConstants.CLASS_ALIAS, EstadoContratoConstants.ARG_ALIAS_SINCRONIZAR_ESTADOCONTRATOSINCRONIZARPTHISESTADOCONTRATO);
        }
    }

    @JsonIgnore
    public String getEstadoContratosincronizarpDescripcion() {
        return this.estadoContratosincronizarpDescripcion;
    }
    
    public void setEstadoContratosincronizarpDescripcion(String estadoContratosincronizarpDescripcion) {
        this.estadoContratosincronizarpDescripcion = estadoContratosincronizarpDescripcion;
    }

    private void checkEstadoContratosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoContratosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoContratoConstants.SERV_ID_SINCRONIZAR, EstadoContratoConstants.CLASS_ID, EstadoContratoConstants.ARG_ID_SINCRONIZAR_ESTADOCONTRATOSINCRONIZARPDESCRIPCION, EstadoContratoConstants.SERV_ALIAS_SINCRONIZAR, EstadoContratoConstants.CLASS_ALIAS, EstadoContratoConstants.ARG_ALIAS_SINCRONIZAR_ESTADOCONTRATOSINCRONIZARPDESCRIPCION);
        }
    }
}
