package com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoCivilConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;

public class DelEstadoCivilCRequest {

    @JsonProperty("p_thisestadocivil_oid")
    private EstadoCivilOid estadoCivilDelEstadoCivilCpthisEstadoCivil;
    
    @JsonIgnore
    private EstadoCivil estadoCivilDelEstadoCivilCpthisEstadoCivilInstance;

    @JsonProperty("p_evcpersonac_oid")
    private PersonaCOid estadoCivilDelEstadoCivilCpevcPersonaC;
    
    @JsonIgnore
    private PersonaC estadoCivilDelEstadoCivilCpevcPersonaCInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoCivilDelEstadoCivilCpthisEstadoCivil();
        checkEstadoCivilDelEstadoCivilCpevcPersonaC();
    }

    @JsonIgnore
    public EstadoCivilOid getEstadoCivilDelEstadoCivilCpthisEstadoCivil() {
        return this.estadoCivilDelEstadoCivilCpthisEstadoCivil;
    }
    
    public void setEstadoCivilDelEstadoCivilCpthisEstadoCivil(EstadoCivilOid estadoCivilDelEstadoCivilCpthisEstadoCivil) {
        this.estadoCivilDelEstadoCivilCpthisEstadoCivil = estadoCivilDelEstadoCivilCpthisEstadoCivil;
    }

    @JsonIgnore
    public EstadoCivil getEstadoCivilDelEstadoCivilCpthisEstadoCivilInstance() {
        return this.estadoCivilDelEstadoCivilCpthisEstadoCivilInstance;
    }
    
    public void setEstadoCivilDelEstadoCivilCpthisEstadoCivilInstance(EstadoCivil estadoCivilDelEstadoCivilCpthisEstadoCivil) {
        this.estadoCivilDelEstadoCivilCpthisEstadoCivilInstance = estadoCivilDelEstadoCivilCpthisEstadoCivil;
        this.estadoCivilDelEstadoCivilCpthisEstadoCivil = estadoCivilDelEstadoCivilCpthisEstadoCivil != null ? estadoCivilDelEstadoCivilCpthisEstadoCivil.getOid() : null;
    }

    private void checkEstadoCivilDelEstadoCivilCpthisEstadoCivil() throws NotNullArgumentException {
        if(this.getEstadoCivilDelEstadoCivilCpthisEstadoCivilInstance() == null || this.getEstadoCivilDelEstadoCivilCpthisEstadoCivilInstance().isNull()) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_DELESTADOCIVILC, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_DELESTADOCIVILC_ESTADOCIVILDELESTADOCIVILCPTHISESTADOCIVIL, EstadoCivilConstants.SERV_ALIAS_DELESTADOCIVILC, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_DELESTADOCIVILC_ESTADOCIVILDELESTADOCIVILCPTHISESTADOCIVIL);
        }
    }

    @JsonIgnore
    public PersonaCOid getEstadoCivilDelEstadoCivilCpevcPersonaC() {
        return this.estadoCivilDelEstadoCivilCpevcPersonaC;
    }
    
    public void setEstadoCivilDelEstadoCivilCpevcPersonaC(PersonaCOid estadoCivilDelEstadoCivilCpevcPersonaC) {
        this.estadoCivilDelEstadoCivilCpevcPersonaC = estadoCivilDelEstadoCivilCpevcPersonaC;
    }

    @JsonIgnore
    public PersonaC getEstadoCivilDelEstadoCivilCpevcPersonaCInstance() {
        return this.estadoCivilDelEstadoCivilCpevcPersonaCInstance;
    }
    
    public void setEstadoCivilDelEstadoCivilCpevcPersonaCInstance(PersonaC estadoCivilDelEstadoCivilCpevcPersonaC) {
        this.estadoCivilDelEstadoCivilCpevcPersonaCInstance = estadoCivilDelEstadoCivilCpevcPersonaC;
        this.estadoCivilDelEstadoCivilCpevcPersonaC = estadoCivilDelEstadoCivilCpevcPersonaC != null ? estadoCivilDelEstadoCivilCpevcPersonaC.getOid() : null;
    }

    private void checkEstadoCivilDelEstadoCivilCpevcPersonaC() throws NotNullArgumentException {
        if(this.getEstadoCivilDelEstadoCivilCpevcPersonaCInstance() == null || this.getEstadoCivilDelEstadoCivilCpevcPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_DELESTADOCIVILC, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_DELESTADOCIVILC_ESTADOCIVILDELESTADOCIVILCPEVCPERSONAC, EstadoCivilConstants.SERV_ALIAS_DELESTADOCIVILC, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_DELESTADOCIVILC_ESTADOCIVILDELESTADOCIVILCPEVCPERSONAC);
        }
    }
}
