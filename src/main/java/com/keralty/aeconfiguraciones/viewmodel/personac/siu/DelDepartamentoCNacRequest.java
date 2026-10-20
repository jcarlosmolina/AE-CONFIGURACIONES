package com.keralty.aeconfiguraciones.viewmodel.personac.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PersonaCConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;

public class DelDepartamentoCNacRequest {

    @JsonProperty("p_thispersonac_oid")
    private PersonaCOid personaCDelDepartamentoCNacpthisPersonaC;
    
    @JsonIgnore
    private PersonaC personaCDelDepartamentoCNacpthisPersonaCInstance;

    @JsonProperty("p_evcdepartamento_oid")
    private DepartamentoOid personaCDelDepartamentoCNacpevcDepartamento;
    
    @JsonIgnore
    private Departamento personaCDelDepartamentoCNacpevcDepartamentoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPersonaCDelDepartamentoCNacpthisPersonaC();
        checkPersonaCDelDepartamentoCNacpevcDepartamento();
    }

    @JsonIgnore
    public PersonaCOid getPersonaCDelDepartamentoCNacpthisPersonaC() {
        return this.personaCDelDepartamentoCNacpthisPersonaC;
    }
    
    public void setPersonaCDelDepartamentoCNacpthisPersonaC(PersonaCOid personaCDelDepartamentoCNacpthisPersonaC) {
        this.personaCDelDepartamentoCNacpthisPersonaC = personaCDelDepartamentoCNacpthisPersonaC;
    }

    @JsonIgnore
    public PersonaC getPersonaCDelDepartamentoCNacpthisPersonaCInstance() {
        return this.personaCDelDepartamentoCNacpthisPersonaCInstance;
    }
    
    public void setPersonaCDelDepartamentoCNacpthisPersonaCInstance(PersonaC personaCDelDepartamentoCNacpthisPersonaC) {
        this.personaCDelDepartamentoCNacpthisPersonaCInstance = personaCDelDepartamentoCNacpthisPersonaC;
        this.personaCDelDepartamentoCNacpthisPersonaC = personaCDelDepartamentoCNacpthisPersonaC != null ? personaCDelDepartamentoCNacpthisPersonaC.getOid() : null;
    }

    private void checkPersonaCDelDepartamentoCNacpthisPersonaC() throws NotNullArgumentException {
        if(this.getPersonaCDelDepartamentoCNacpthisPersonaCInstance() == null || this.getPersonaCDelDepartamentoCNacpthisPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_DELDEPARTAMENTOCNAC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_DELDEPARTAMENTOCNAC_PERSONACDELDEPARTAMENTOCNACPTHISPERSONAC, PersonaCConstants.SERV_ALIAS_DELDEPARTAMENTOCNAC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_DELDEPARTAMENTOCNAC_PERSONACDELDEPARTAMENTOCNACPTHISPERSONAC);
        }
    }

    @JsonIgnore
    public DepartamentoOid getPersonaCDelDepartamentoCNacpevcDepartamento() {
        return this.personaCDelDepartamentoCNacpevcDepartamento;
    }
    
    public void setPersonaCDelDepartamentoCNacpevcDepartamento(DepartamentoOid personaCDelDepartamentoCNacpevcDepartamento) {
        this.personaCDelDepartamentoCNacpevcDepartamento = personaCDelDepartamentoCNacpevcDepartamento;
    }

    @JsonIgnore
    public Departamento getPersonaCDelDepartamentoCNacpevcDepartamentoInstance() {
        return this.personaCDelDepartamentoCNacpevcDepartamentoInstance;
    }
    
    public void setPersonaCDelDepartamentoCNacpevcDepartamentoInstance(Departamento personaCDelDepartamentoCNacpevcDepartamento) {
        this.personaCDelDepartamentoCNacpevcDepartamentoInstance = personaCDelDepartamentoCNacpevcDepartamento;
        this.personaCDelDepartamentoCNacpevcDepartamento = personaCDelDepartamentoCNacpevcDepartamento != null ? personaCDelDepartamentoCNacpevcDepartamento.getOid() : null;
    }

    private void checkPersonaCDelDepartamentoCNacpevcDepartamento() throws NotNullArgumentException {
        if(this.getPersonaCDelDepartamentoCNacpevcDepartamentoInstance() == null || this.getPersonaCDelDepartamentoCNacpevcDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(PersonaCConstants.SERV_ID_DELDEPARTAMENTOCNAC, PersonaCConstants.CLASS_ID, PersonaCConstants.ARG_ID_DELDEPARTAMENTOCNAC_PERSONACDELDEPARTAMENTOCNACPEVCDEPARTAMENTO, PersonaCConstants.SERV_ALIAS_DELDEPARTAMENTOCNAC, PersonaCConstants.CLASS_ALIAS, PersonaCConstants.ARG_ALIAS_DELDEPARTAMENTOCNAC_PERSONACDELDEPARTAMENTOCNACPEVCDEPARTAMENTO);
        }
    }
}
