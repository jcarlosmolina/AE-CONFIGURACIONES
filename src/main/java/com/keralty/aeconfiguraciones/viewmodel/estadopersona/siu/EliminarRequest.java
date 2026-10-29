package com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;

public class EliminarRequest {

    @JsonProperty("p_thisestadopersona_oid")
    private EstadoPersonaOid estadoPersonaeliminarpthisEstadoPersona;
    
    @JsonIgnore
    private EstadoPersona estadoPersonaeliminarpthisEstadoPersonaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoPersonaeliminarpthisEstadoPersona();
    }

    @JsonIgnore
    public EstadoPersonaOid getEstadoPersonaeliminarpthisEstadoPersona() {
        return this.estadoPersonaeliminarpthisEstadoPersona;
    }
    
    public void setEstadoPersonaeliminarpthisEstadoPersona(EstadoPersonaOid estadoPersonaeliminarpthisEstadoPersona) {
        this.estadoPersonaeliminarpthisEstadoPersona = estadoPersonaeliminarpthisEstadoPersona;
    }

    @JsonIgnore
    public EstadoPersona getEstadoPersonaeliminarpthisEstadoPersonaInstance() {
        return this.estadoPersonaeliminarpthisEstadoPersonaInstance;
    }
    
    public void setEstadoPersonaeliminarpthisEstadoPersonaInstance(EstadoPersona estadoPersonaeliminarpthisEstadoPersona) {
        this.estadoPersonaeliminarpthisEstadoPersonaInstance = estadoPersonaeliminarpthisEstadoPersona;
        this.estadoPersonaeliminarpthisEstadoPersona = estadoPersonaeliminarpthisEstadoPersona != null ? estadoPersonaeliminarpthisEstadoPersona.getOid() : null;
    }

    private void checkEstadoPersonaeliminarpthisEstadoPersona() throws NotNullArgumentException {
        if(this.getEstadoPersonaeliminarpthisEstadoPersonaInstance() == null || this.getEstadoPersonaeliminarpthisEstadoPersonaInstance().isNull()) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_ELIMINAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_ELIMINAR_ESTADOPERSONAELIMINARPTHISESTADOPERSONA, EstadoPersonaConstants.SERV_ALIAS_ELIMINAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_ELIMINAR_ESTADOPERSONAELIMINARPTHISESTADOPERSONA);
        }
    }
}
