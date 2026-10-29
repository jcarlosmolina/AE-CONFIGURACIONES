package com.keralty.aeconfiguraciones.viewmodel.personac.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PersonaCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;

public class CambTipoIdentificacionCRequest {

    @JsonProperty("p_thispersonac_oid")
    private PersonaCOid personaCCambTipoIdentificacionCpthisPersonaC;
    
    @JsonIgnore
    private PersonaC personaCCambTipoIdentificacionCpthisPersonaCInstance;

    @JsonProperty("p_evctipoidentificacion_oid")
    private TipoIdentificacionOid personaCCambTipoIdentificacionCpevcTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion personaCCambTipoIdentificacionCpevcTipoIdentificacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPersonaCCambTipoIdentificacionCpthisPersonaC();
        checkPersonaCCambTipoIdentificacionCpevcTipoIdentificacion();
    }

    @JsonIgnore
    public PersonaCOid getPersonaCCambTipoIdentificacionCpthisPersonaC() {
        return this.personaCCambTipoIdentificacionCpthisPersonaC;
    }
    
    public void setPersonaCCambTipoIdentificacionCpthisPersonaC(PersonaCOid personaCCambTipoIdentificacionCpthisPersonaC) {
        this.personaCCambTipoIdentificacionCpthisPersonaC = personaCCambTipoIdentificacionCpthisPersonaC;
    }

    @JsonIgnore
    public PersonaC getPersonaCCambTipoIdentificacionCpthisPersonaCInstance() {
        return this.personaCCambTipoIdentificacionCpthisPersonaCInstance;
    }
    
    public void setPersonaCCambTipoIdentificacionCpthisPersonaCInstance(PersonaC personaCCambTipoIdentificacionCpthisPersonaC) {
        this.personaCCambTipoIdentificacionCpthisPersonaCInstance = personaCCambTipoIdentificacionCpthisPersonaC;
        this.personaCCambTipoIdentificacionCpthisPersonaC = personaCCambTipoIdentificacionCpthisPersonaC != null ? personaCCambTipoIdentificacionCpthisPersonaC.getOid() : null;
    }

    private void checkPersonaCCambTipoIdentificacionCpthisPersonaC() throws NotNullArgumentException {
        if(this.getPersonaCCambTipoIdentificacionCpthisPersonaCInstance() == null || this.getPersonaCCambTipoIdentificacionCpthisPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_CAMBTIPOIDENTIFICACIONC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_CAMBTIPOIDENTIFICACIONC_PERSONACCAMBTIPOIDENTIFICACIONCPTHISPERSONAC, PersonaCConstants.SERV_ALIAS_CAMBTIPOIDENTIFICACIONC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_CAMBTIPOIDENTIFICACIONC_PERSONACCAMBTIPOIDENTIFICACIONCPTHISPERSONAC);
        }
    }

    @JsonIgnore
    public TipoIdentificacionOid getPersonaCCambTipoIdentificacionCpevcTipoIdentificacion() {
        return this.personaCCambTipoIdentificacionCpevcTipoIdentificacion;
    }
    
    public void setPersonaCCambTipoIdentificacionCpevcTipoIdentificacion(TipoIdentificacionOid personaCCambTipoIdentificacionCpevcTipoIdentificacion) {
        this.personaCCambTipoIdentificacionCpevcTipoIdentificacion = personaCCambTipoIdentificacionCpevcTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance() {
        return this.personaCCambTipoIdentificacionCpevcTipoIdentificacionInstance;
    }
    
    public void setPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance(TipoIdentificacion personaCCambTipoIdentificacionCpevcTipoIdentificacion) {
        this.personaCCambTipoIdentificacionCpevcTipoIdentificacionInstance = personaCCambTipoIdentificacionCpevcTipoIdentificacion;
        this.personaCCambTipoIdentificacionCpevcTipoIdentificacion = personaCCambTipoIdentificacionCpevcTipoIdentificacion != null ? personaCCambTipoIdentificacionCpevcTipoIdentificacion.getOid() : null;
    }

    private void checkPersonaCCambTipoIdentificacionCpevcTipoIdentificacion() throws NotNullArgumentException {
        if(this.getPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance() == null || this.getPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_CAMBTIPOIDENTIFICACIONC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_CAMBTIPOIDENTIFICACIONC_PERSONACCAMBTIPOIDENTIFICACIONCPEVCTIPOIDENTIFICACION, PersonaCConstants.SERV_ALIAS_CAMBTIPOIDENTIFICACIONC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_CAMBTIPOIDENTIFICACIONC_PERSONACCAMBTIPOIDENTIFICACIONCPEVCTIPOIDENTIFICACION);
        }
    }
}
