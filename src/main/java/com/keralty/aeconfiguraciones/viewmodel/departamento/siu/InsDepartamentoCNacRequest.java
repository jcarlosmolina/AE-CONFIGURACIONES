package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;

public class InsDepartamentoCNacRequest {

    @JsonProperty("p_thisdepartamento_oid")
    private DepartamentoOid departamentoInsDepartamentoCNacpthisDepartamento;
    
    @JsonIgnore
    private Departamento departamentoInsDepartamentoCNacpthisDepartamentoInstance;

    @JsonProperty("p_evcpersonac_oid")
    private PersonaCOid departamentoInsDepartamentoCNacpevcPersonaC;
    
    @JsonIgnore
    private PersonaC departamentoInsDepartamentoCNacpevcPersonaCInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentoInsDepartamentoCNacpthisDepartamento();
        checkDepartamentoInsDepartamentoCNacpevcPersonaC();
    }

    @JsonIgnore
    public DepartamentoOid getDepartamentoInsDepartamentoCNacpthisDepartamento() {
        return this.departamentoInsDepartamentoCNacpthisDepartamento;
    }
    
    public void setDepartamentoInsDepartamentoCNacpthisDepartamento(DepartamentoOid departamentoInsDepartamentoCNacpthisDepartamento) {
        this.departamentoInsDepartamentoCNacpthisDepartamento = departamentoInsDepartamentoCNacpthisDepartamento;
    }

    @JsonIgnore
    public Departamento getDepartamentoInsDepartamentoCNacpthisDepartamentoInstance() {
        return this.departamentoInsDepartamentoCNacpthisDepartamentoInstance;
    }
    
    public void setDepartamentoInsDepartamentoCNacpthisDepartamentoInstance(Departamento departamentoInsDepartamentoCNacpthisDepartamento) {
        this.departamentoInsDepartamentoCNacpthisDepartamentoInstance = departamentoInsDepartamentoCNacpthisDepartamento;
        this.departamentoInsDepartamentoCNacpthisDepartamento = departamentoInsDepartamentoCNacpthisDepartamento != null ? departamentoInsDepartamentoCNacpthisDepartamento.getOid() : null;
    }

    private void checkDepartamentoInsDepartamentoCNacpthisDepartamento() throws NotNullArgumentException {
        if(this.getDepartamentoInsDepartamentoCNacpthisDepartamentoInstance() == null || this.getDepartamentoInsDepartamentoCNacpthisDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_INSDEPARTAMENTOCNAC, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_INSDEPARTAMENTOCNAC_DEPARTAMENTOINSDEPARTAMENTOCNACPTHISDEPARTAMENTO, DepartamentoConstants.SERV_ALIAS_INSDEPARTAMENTOCNAC, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_INSDEPARTAMENTOCNAC_DEPARTAMENTOINSDEPARTAMENTOCNACPTHISDEPARTAMENTO);
        }
    }

    @JsonIgnore
    public PersonaCOid getDepartamentoInsDepartamentoCNacpevcPersonaC() {
        return this.departamentoInsDepartamentoCNacpevcPersonaC;
    }
    
    public void setDepartamentoInsDepartamentoCNacpevcPersonaC(PersonaCOid departamentoInsDepartamentoCNacpevcPersonaC) {
        this.departamentoInsDepartamentoCNacpevcPersonaC = departamentoInsDepartamentoCNacpevcPersonaC;
    }

    @JsonIgnore
    public PersonaC getDepartamentoInsDepartamentoCNacpevcPersonaCInstance() {
        return this.departamentoInsDepartamentoCNacpevcPersonaCInstance;
    }
    
    public void setDepartamentoInsDepartamentoCNacpevcPersonaCInstance(PersonaC departamentoInsDepartamentoCNacpevcPersonaC) {
        this.departamentoInsDepartamentoCNacpevcPersonaCInstance = departamentoInsDepartamentoCNacpevcPersonaC;
        this.departamentoInsDepartamentoCNacpevcPersonaC = departamentoInsDepartamentoCNacpevcPersonaC != null ? departamentoInsDepartamentoCNacpevcPersonaC.getOid() : null;
    }

    private void checkDepartamentoInsDepartamentoCNacpevcPersonaC() throws NotNullArgumentException {
        if(this.getDepartamentoInsDepartamentoCNacpevcPersonaCInstance() == null || this.getDepartamentoInsDepartamentoCNacpevcPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_INSDEPARTAMENTOCNAC, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_INSDEPARTAMENTOCNAC_DEPARTAMENTOINSDEPARTAMENTOCNACPEVCPERSONAC, DepartamentoConstants.SERV_ALIAS_INSDEPARTAMENTOCNAC, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_INSDEPARTAMENTOCNAC_DEPARTAMENTOINSDEPARTAMENTOCNACPEVCPERSONAC);
        }
    }
}
