package com.keralty.aeconfiguraciones.viewmodel.personac.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PersonaCConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;

public class DelEstadoPersonaCRequest {

    @JsonProperty("p_thispersonac_oid")
    private PersonaCOid personaCDelEstadoPersonaCpthisPersonaC;
    
    @JsonIgnore
    private PersonaC personaCDelEstadoPersonaCpthisPersonaCInstance;

    @JsonProperty("p_evcestadopersona_oid")
    private EstadoPersonaOid personaCDelEstadoPersonaCpevcEstadoPersona;
    
    @JsonIgnore
    private EstadoPersona personaCDelEstadoPersonaCpevcEstadoPersonaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPersonaCDelEstadoPersonaCpthisPersonaC();
        checkPersonaCDelEstadoPersonaCpevcEstadoPersona();
    }

    @JsonIgnore
    public PersonaCOid getPersonaCDelEstadoPersonaCpthisPersonaC() {
        return this.personaCDelEstadoPersonaCpthisPersonaC;
    }
    
    public void setPersonaCDelEstadoPersonaCpthisPersonaC(PersonaCOid personaCDelEstadoPersonaCpthisPersonaC) {
        this.personaCDelEstadoPersonaCpthisPersonaC = personaCDelEstadoPersonaCpthisPersonaC;
    }

    @JsonIgnore
    public PersonaC getPersonaCDelEstadoPersonaCpthisPersonaCInstance() {
        return this.personaCDelEstadoPersonaCpthisPersonaCInstance;
    }
    
    public void setPersonaCDelEstadoPersonaCpthisPersonaCInstance(PersonaC personaCDelEstadoPersonaCpthisPersonaC) {
        this.personaCDelEstadoPersonaCpthisPersonaCInstance = personaCDelEstadoPersonaCpthisPersonaC;
        this.personaCDelEstadoPersonaCpthisPersonaC = personaCDelEstadoPersonaCpthisPersonaC != null ? personaCDelEstadoPersonaCpthisPersonaC.getOid() : null;
    }

    private void checkPersonaCDelEstadoPersonaCpthisPersonaC() throws NotNullArgumentException {
        if(this.getPersonaCDelEstadoPersonaCpthisPersonaCInstance() == null || this.getPersonaCDelEstadoPersonaCpthisPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_DELESTADOPERSONAC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_DELESTADOPERSONAC_PERSONACDELESTADOPERSONACPTHISPERSONAC, PersonaCConstants.SERV_ALIAS_DELESTADOPERSONAC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_DELESTADOPERSONAC_PERSONACDELESTADOPERSONACPTHISPERSONAC);
        }
    }

    @JsonIgnore
    public EstadoPersonaOid getPersonaCDelEstadoPersonaCpevcEstadoPersona() {
        return this.personaCDelEstadoPersonaCpevcEstadoPersona;
    }
    
    public void setPersonaCDelEstadoPersonaCpevcEstadoPersona(EstadoPersonaOid personaCDelEstadoPersonaCpevcEstadoPersona) {
        this.personaCDelEstadoPersonaCpevcEstadoPersona = personaCDelEstadoPersonaCpevcEstadoPersona;
    }

    @JsonIgnore
    public EstadoPersona getPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance() {
        return this.personaCDelEstadoPersonaCpevcEstadoPersonaInstance;
    }
    
    public void setPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance(EstadoPersona personaCDelEstadoPersonaCpevcEstadoPersona) {
        this.personaCDelEstadoPersonaCpevcEstadoPersonaInstance = personaCDelEstadoPersonaCpevcEstadoPersona;
        this.personaCDelEstadoPersonaCpevcEstadoPersona = personaCDelEstadoPersonaCpevcEstadoPersona != null ? personaCDelEstadoPersonaCpevcEstadoPersona.getOid() : null;
    }

    private void checkPersonaCDelEstadoPersonaCpevcEstadoPersona() throws NotNullArgumentException {
        if(this.getPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance() == null || this.getPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_DELESTADOPERSONAC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_DELESTADOPERSONAC_PERSONACDELESTADOPERSONACPEVCESTADOPERSONA, PersonaCConstants.SERV_ALIAS_DELESTADOPERSONAC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_DELESTADOPERSONAC_PERSONACDELESTADOPERSONACPEVCESTADOPERSONA);
        }
    }
}
