package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;

public class EliminarRequest {

    @JsonProperty("p_thisdepartamento_oid")
    private DepartamentoOid departamentoeliminarpthisDepartamento;
    
    @JsonIgnore
    private Departamento departamentoeliminarpthisDepartamentoInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentoeliminarpthisDepartamento();
    }

    @JsonIgnore
    public DepartamentoOid getDepartamentoeliminarpthisDepartamento() {
        return this.departamentoeliminarpthisDepartamento;
    }
    
    public void setDepartamentoeliminarpthisDepartamento(DepartamentoOid departamentoeliminarpthisDepartamento) {
        this.departamentoeliminarpthisDepartamento = departamentoeliminarpthisDepartamento;
    }

    @JsonIgnore
    public Departamento getDepartamentoeliminarpthisDepartamentoInstance() {
        return this.departamentoeliminarpthisDepartamentoInstance;
    }
    
    public void setDepartamentoeliminarpthisDepartamentoInstance(Departamento departamentoeliminarpthisDepartamento) {
        this.departamentoeliminarpthisDepartamentoInstance = departamentoeliminarpthisDepartamento;
        this.departamentoeliminarpthisDepartamento = departamentoeliminarpthisDepartamento != null ? departamentoeliminarpthisDepartamento.getOid() : null;
    }

    private void checkDepartamentoeliminarpthisDepartamento() throws NotNullArgumentException {
        if(this.getDepartamentoeliminarpthisDepartamentoInstance() == null || this.getDepartamentoeliminarpthisDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_ELIMINAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_ELIMINAR_DEPARTAMENTOELIMINARPTHISDEPARTAMENTO, DepartamentoConstants.SERV_ALIAS_ELIMINAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_ELIMINAR_DEPARTAMENTOELIMINARPTHISDEPARTAMENTO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
