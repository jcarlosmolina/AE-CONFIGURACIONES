package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;

public class SincronizarRequest {

    @JsonProperty("p_thisdepartamento_oid")
    private DepartamentoOid departamentosincronizarpthisDepartamento;
    
    @JsonIgnore
    private Departamento departamentosincronizarpthisDepartamentoInstance;

    @JsonProperty("p_descripcion")
    private String departamentosincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentosincronizarpthisDepartamento();
        checkDepartamentosincronizarpDescripcion();
    }

    @JsonIgnore
    public DepartamentoOid getDepartamentosincronizarpthisDepartamento() {
        return this.departamentosincronizarpthisDepartamento;
    }
    
    public void setDepartamentosincronizarpthisDepartamento(DepartamentoOid departamentosincronizarpthisDepartamento) {
        this.departamentosincronizarpthisDepartamento = departamentosincronizarpthisDepartamento;
    }

    @JsonIgnore
    public Departamento getDepartamentosincronizarpthisDepartamentoInstance() {
        return this.departamentosincronizarpthisDepartamentoInstance;
    }
    
    public void setDepartamentosincronizarpthisDepartamentoInstance(Departamento departamentosincronizarpthisDepartamento) {
        this.departamentosincronizarpthisDepartamentoInstance = departamentosincronizarpthisDepartamento;
        this.departamentosincronizarpthisDepartamento = departamentosincronizarpthisDepartamento != null ? departamentosincronizarpthisDepartamento.getOid() : null;
    }

    private void checkDepartamentosincronizarpthisDepartamento() throws NotNullArgumentException {
        if(this.getDepartamentosincronizarpthisDepartamentoInstance() == null || this.getDepartamentosincronizarpthisDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_SINCRONIZAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_SINCRONIZAR_DEPARTAMENTOSINCRONIZARPTHISDEPARTAMENTO, DepartamentoConstants.SERV_ALIAS_SINCRONIZAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_SINCRONIZAR_DEPARTAMENTOSINCRONIZARPTHISDEPARTAMENTO);
        }
    }

    @JsonIgnore
    public String getDepartamentosincronizarpDescripcion() {
        return this.departamentosincronizarpDescripcion;
    }
    
    public void setDepartamentosincronizarpDescripcion(String departamentosincronizarpDescripcion) {
        this.departamentosincronizarpDescripcion = departamentosincronizarpDescripcion;
    }

    private void checkDepartamentosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getDepartamentosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_SINCRONIZAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_SINCRONIZAR_DEPARTAMENTOSINCRONIZARPDESCRIPCION, DepartamentoConstants.SERV_ALIAS_SINCRONIZAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_SINCRONIZAR_DEPARTAMENTOSINCRONIZARPDESCRIPCION);
        }
    }
}
