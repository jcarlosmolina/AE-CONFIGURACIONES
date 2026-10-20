package com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaOid;
import com.keralty.aeconfiguraciones.persistence.Persona;

public class InsEstadoPersonaRequest {

    @JsonProperty("p_thisestadopersona_oid")
    private EstadoPersonaOid estadoPersonaInsEstadoPersonapthisEstadoPersona;
    
    @JsonIgnore
    private EstadoPersona estadoPersonaInsEstadoPersonapthisEstadoPersonaInstance;

    @JsonProperty("p_evcpersona_oid")
    private PersonaOid estadoPersonaInsEstadoPersonapevcPersona;
    
    @JsonIgnore
    private Persona estadoPersonaInsEstadoPersonapevcPersonaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoPersonaInsEstadoPersonapthisEstadoPersona();
        checkEstadoPersonaInsEstadoPersonapevcPersona();
    }

    @JsonIgnore
    public EstadoPersonaOid getEstadoPersonaInsEstadoPersonapthisEstadoPersona() {
        return this.estadoPersonaInsEstadoPersonapthisEstadoPersona;
    }
    
    public void setEstadoPersonaInsEstadoPersonapthisEstadoPersona(EstadoPersonaOid estadoPersonaInsEstadoPersonapthisEstadoPersona) {
        this.estadoPersonaInsEstadoPersonapthisEstadoPersona = estadoPersonaInsEstadoPersonapthisEstadoPersona;
    }

    @JsonIgnore
    public EstadoPersona getEstadoPersonaInsEstadoPersonapthisEstadoPersonaInstance() {
        return this.estadoPersonaInsEstadoPersonapthisEstadoPersonaInstance;
    }
    
    public void setEstadoPersonaInsEstadoPersonapthisEstadoPersonaInstance(EstadoPersona estadoPersonaInsEstadoPersonapthisEstadoPersona) {
        this.estadoPersonaInsEstadoPersonapthisEstadoPersonaInstance = estadoPersonaInsEstadoPersonapthisEstadoPersona;
        this.estadoPersonaInsEstadoPersonapthisEstadoPersona = estadoPersonaInsEstadoPersonapthisEstadoPersona != null ? estadoPersonaInsEstadoPersonapthisEstadoPersona.getOid() : null;
    }

    private void checkEstadoPersonaInsEstadoPersonapthisEstadoPersona() throws NotNullArgumentException {
        if(this.getEstadoPersonaInsEstadoPersonapthisEstadoPersonaInstance() == null || this.getEstadoPersonaInsEstadoPersonapthisEstadoPersonaInstance().isNull()) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_INSESTADOPERSONA, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_INSESTADOPERSONA_ESTADOPERSONAINSESTADOPERSONAPTHISESTADOPERSONA, EstadoPersonaConstants.SERV_ALIAS_INSESTADOPERSONA, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_INSESTADOPERSONA_ESTADOPERSONAINSESTADOPERSONAPTHISESTADOPERSONA);
        }
    }

    @JsonIgnore
    public PersonaOid getEstadoPersonaInsEstadoPersonapevcPersona() {
        return this.estadoPersonaInsEstadoPersonapevcPersona;
    }
    
    public void setEstadoPersonaInsEstadoPersonapevcPersona(PersonaOid estadoPersonaInsEstadoPersonapevcPersona) {
        this.estadoPersonaInsEstadoPersonapevcPersona = estadoPersonaInsEstadoPersonapevcPersona;
    }

    @JsonIgnore
    public Persona getEstadoPersonaInsEstadoPersonapevcPersonaInstance() {
        return this.estadoPersonaInsEstadoPersonapevcPersonaInstance;
    }
    
    public void setEstadoPersonaInsEstadoPersonapevcPersonaInstance(Persona estadoPersonaInsEstadoPersonapevcPersona) {
        this.estadoPersonaInsEstadoPersonapevcPersonaInstance = estadoPersonaInsEstadoPersonapevcPersona;
        this.estadoPersonaInsEstadoPersonapevcPersona = estadoPersonaInsEstadoPersonapevcPersona != null ? estadoPersonaInsEstadoPersonapevcPersona.getOid() : null;
    }

    private void checkEstadoPersonaInsEstadoPersonapevcPersona() throws NotNullArgumentException {
        if(this.getEstadoPersonaInsEstadoPersonapevcPersonaInstance() == null || this.getEstadoPersonaInsEstadoPersonapevcPersonaInstance().isNull()) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_INSESTADOPERSONA, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_INSESTADOPERSONA_ESTADOPERSONAINSESTADOPERSONAPEVCPERSONA, EstadoPersonaConstants.SERV_ALIAS_INSESTADOPERSONA, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_INSESTADOPERSONA_ESTADOPERSONAINSESTADOPERSONAPEVCPERSONA);
        }
    }
}
