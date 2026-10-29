package com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;

public class SincronizarRequest {

    @JsonProperty("p_thisestadopersona_oid")
    private EstadoPersonaOid estadoPersonasincronizarpthisEstadoPersona;
    
    @JsonIgnore
    private EstadoPersona estadoPersonasincronizarpthisEstadoPersonaInstance;

    @JsonProperty("p_descripcion")
    private String estadoPersonasincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoPersonasincronizarpthisEstadoPersona();
        checkEstadoPersonasincronizarpDescripcion();
    }

    @JsonIgnore
    public EstadoPersonaOid getEstadoPersonasincronizarpthisEstadoPersona() {
        return this.estadoPersonasincronizarpthisEstadoPersona;
    }
    
    public void setEstadoPersonasincronizarpthisEstadoPersona(EstadoPersonaOid estadoPersonasincronizarpthisEstadoPersona) {
        this.estadoPersonasincronizarpthisEstadoPersona = estadoPersonasincronizarpthisEstadoPersona;
    }

    @JsonIgnore
    public EstadoPersona getEstadoPersonasincronizarpthisEstadoPersonaInstance() {
        return this.estadoPersonasincronizarpthisEstadoPersonaInstance;
    }
    
    public void setEstadoPersonasincronizarpthisEstadoPersonaInstance(EstadoPersona estadoPersonasincronizarpthisEstadoPersona) {
        this.estadoPersonasincronizarpthisEstadoPersonaInstance = estadoPersonasincronizarpthisEstadoPersona;
        this.estadoPersonasincronizarpthisEstadoPersona = estadoPersonasincronizarpthisEstadoPersona != null ? estadoPersonasincronizarpthisEstadoPersona.getOid() : null;
    }

    private void checkEstadoPersonasincronizarpthisEstadoPersona() throws NotNullArgumentException {
        if(this.getEstadoPersonasincronizarpthisEstadoPersonaInstance() == null || this.getEstadoPersonasincronizarpthisEstadoPersonaInstance().isNull()) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_SINCRONIZAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_SINCRONIZAR_ESTADOPERSONASINCRONIZARPTHISESTADOPERSONA, EstadoPersonaConstants.SERV_ALIAS_SINCRONIZAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_SINCRONIZAR_ESTADOPERSONASINCRONIZARPTHISESTADOPERSONA);
        }
    }

    @JsonIgnore
    public String getEstadoPersonasincronizarpDescripcion() {
        return this.estadoPersonasincronizarpDescripcion;
    }
    
    public void setEstadoPersonasincronizarpDescripcion(String estadoPersonasincronizarpDescripcion) {
        this.estadoPersonasincronizarpDescripcion = estadoPersonasincronizarpDescripcion;
    }

    private void checkEstadoPersonasincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoPersonasincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_SINCRONIZAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_SINCRONIZAR_ESTADOPERSONASINCRONIZARPDESCRIPCION, EstadoPersonaConstants.SERV_ALIAS_SINCRONIZAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_SINCRONIZAR_ESTADOPERSONASINCRONIZARPDESCRIPCION);
        }
    }
}
