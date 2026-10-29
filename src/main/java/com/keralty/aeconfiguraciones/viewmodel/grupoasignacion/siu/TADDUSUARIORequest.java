package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.AreaMedica;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.AreaMedicaOid;
import com.keralty.aeconfiguraciones.persistence.oid.FuncionarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TADDUSUARIORequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionTADDUSUARIOpthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance;

    @JsonProperty("ptfuncionario_oid")
    private FuncionarioOid grupoAsignacionTADDUSUARIOptFuncionario;
    
    @JsonIgnore
    private Funcionario grupoAsignacionTADDUSUARIOptFuncionarioInstance;

    @JsonProperty("ptareamedica_oid")
    private AreaMedicaOid grupoAsignacionTADDUSUARIOptAreaMedica;
    
    @JsonIgnore
    private AreaMedica grupoAsignacionTADDUSUARIOptAreaMedicaInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion() {
        return this.grupoAsignacionTADDUSUARIOpthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionTADDUSUARIOpthisGrupoAsignacion) {
        this.grupoAsignacionTADDUSUARIOpthisGrupoAsignacion = grupoAsignacionTADDUSUARIOpthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance() {
        return this.grupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacion) {
        this.grupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance = grupoAsignacionTADDUSUARIOpthisGrupoAsignacion;
        this.grupoAsignacionTADDUSUARIOpthisGrupoAsignacion = grupoAsignacionTADDUSUARIOpthisGrupoAsignacion != null ? grupoAsignacionTADDUSUARIOpthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_TADDUSUARIO, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_TADDUSUARIO_GRUPOASIGNACIONTADDUSUARIOPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_TADDUSUARIO, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_TADDUSUARIO_GRUPOASIGNACIONTADDUSUARIOPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public FuncionarioOid getGrupoAsignacionTADDUSUARIOptFuncionario() {
        return this.grupoAsignacionTADDUSUARIOptFuncionario;
    }
    
    public void setGrupoAsignacionTADDUSUARIOptFuncionario(FuncionarioOid grupoAsignacionTADDUSUARIOptFuncionario) {
        this.grupoAsignacionTADDUSUARIOptFuncionario = grupoAsignacionTADDUSUARIOptFuncionario;
    }

    @JsonIgnore
    public Funcionario getGrupoAsignacionTADDUSUARIOptFuncionarioInstance() {
        return this.grupoAsignacionTADDUSUARIOptFuncionarioInstance;
    }
    
    public void setGrupoAsignacionTADDUSUARIOptFuncionarioInstance(Funcionario grupoAsignacionTADDUSUARIOptFuncionario) {
        this.grupoAsignacionTADDUSUARIOptFuncionarioInstance = grupoAsignacionTADDUSUARIOptFuncionario;
        this.grupoAsignacionTADDUSUARIOptFuncionario = grupoAsignacionTADDUSUARIOptFuncionario != null ? grupoAsignacionTADDUSUARIOptFuncionario.getOid() : null;
    }

    @JsonIgnore
    public AreaMedicaOid getGrupoAsignacionTADDUSUARIOptAreaMedica() {
        return this.grupoAsignacionTADDUSUARIOptAreaMedica;
    }
    
    public void setGrupoAsignacionTADDUSUARIOptAreaMedica(AreaMedicaOid grupoAsignacionTADDUSUARIOptAreaMedica) {
        this.grupoAsignacionTADDUSUARIOptAreaMedica = grupoAsignacionTADDUSUARIOptAreaMedica;
    }

    @JsonIgnore
    public AreaMedica getGrupoAsignacionTADDUSUARIOptAreaMedicaInstance() {
        return this.grupoAsignacionTADDUSUARIOptAreaMedicaInstance;
    }
    
    public void setGrupoAsignacionTADDUSUARIOptAreaMedicaInstance(AreaMedica grupoAsignacionTADDUSUARIOptAreaMedica) {
        this.grupoAsignacionTADDUSUARIOptAreaMedicaInstance = grupoAsignacionTADDUSUARIOptAreaMedica;
        this.grupoAsignacionTADDUSUARIOptAreaMedica = grupoAsignacionTADDUSUARIOptAreaMedica != null ? grupoAsignacionTADDUSUARIOptAreaMedica.getOid() : null;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
