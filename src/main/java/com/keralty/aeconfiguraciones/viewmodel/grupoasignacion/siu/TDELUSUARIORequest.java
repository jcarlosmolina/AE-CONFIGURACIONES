package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.FuncionarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TDELUSUARIORequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionTDELUSUARIOpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance;

    @JsonProperty("ptfuncionario_oid")
    private FuncionarioOid grupoAsignacionTDELUSUARIOptFuncionario;
    
    @JsonIgnore
    private Funcionario grupoAsignacionTDELUSUARIOptFuncionarioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionTDELUSUARIOpthisGrupoAsignacion();
        checkGrupoAsignacionTDELUSUARIOptFuncionario();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacion() {
        return this.grupoAsignacionTDELUSUARIOpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionTDELUSUARIOpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionTDELUSUARIOpthisGrupoAsignacion) {
        this.grupoAsignacionTDELUSUARIOpthisGrupoAsignacion = grupoAsignacionTDELUSUARIOpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance() {
        return this.grupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacion) {
        this.grupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance = grupoAsignacionTDELUSUARIOpthisGrupoAsignacion;
        this.grupoAsignacionTDELUSUARIOpthisGrupoAsignacion = grupoAsignacionTDELUSUARIOpthisGrupoAsignacion != null ? grupoAsignacionTDELUSUARIOpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionTDELUSUARIOpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TDELUSUARIO, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TDELUSUARIO_GRUPOASIGNACIONTDELUSUARIOPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_TDELUSUARIO, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TDELUSUARIO_GRUPOASIGNACIONTDELUSUARIOPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public FuncionarioOid getGrupoAsignacionTDELUSUARIOptFuncionario() {
        return this.grupoAsignacionTDELUSUARIOptFuncionario;
    }
    
    public void setGrupoAsignacionTDELUSUARIOptFuncionario(FuncionarioOid grupoAsignacionTDELUSUARIOptFuncionario) {
        this.grupoAsignacionTDELUSUARIOptFuncionario = grupoAsignacionTDELUSUARIOptFuncionario;
    }

    @JsonIgnore
    public Funcionario getGrupoAsignacionTDELUSUARIOptFuncionarioInstance() {
        return this.grupoAsignacionTDELUSUARIOptFuncionarioInstance;
    }
    
    public void setGrupoAsignacionTDELUSUARIOptFuncionarioInstance(Funcionario grupoAsignacionTDELUSUARIOptFuncionario) {
        this.grupoAsignacionTDELUSUARIOptFuncionarioInstance = grupoAsignacionTDELUSUARIOptFuncionario;
        this.grupoAsignacionTDELUSUARIOptFuncionario = grupoAsignacionTDELUSUARIOptFuncionario != null ? grupoAsignacionTDELUSUARIOptFuncionario.getOid() : null;
    }

    private void checkGrupoAsignacionTDELUSUARIOptFuncionario() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTDELUSUARIOptFuncionarioInstance() == null || this.getGrupoAsignacionTDELUSUARIOptFuncionarioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TDELUSUARIO, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TDELUSUARIO_GRUPOASIGNACIONTDELUSUARIOPTFUNCIONARIO, GrupoAsignacionConstants.SERV_ALIAS_TDELUSUARIO, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TDELUSUARIO_GRUPOASIGNACIONTDELUSUARIOPTFUNCIONARIO);
        }
    }
}
