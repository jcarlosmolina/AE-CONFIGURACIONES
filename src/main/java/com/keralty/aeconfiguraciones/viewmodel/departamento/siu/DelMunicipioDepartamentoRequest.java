package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class DelMunicipioDepartamentoRequest {

    @JsonProperty("p_thisdepartamento_oid")
    private DepartamentoOid departamentoDelMunicipioDepartamentopthisDepartamento;
    
    @JsonIgnore
    private Departamento departamentoDelMunicipioDepartamentopthisDepartamentoInstance;

    @JsonProperty("p_evcmunicipio_oid")
    private MunicipioOid departamentoDelMunicipioDepartamentopevcMunicipio;
    
    @JsonIgnore
    private Municipio departamentoDelMunicipioDepartamentopevcMunicipioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentoDelMunicipioDepartamentopthisDepartamento();
        checkDepartamentoDelMunicipioDepartamentopevcMunicipio();
    }

    @JsonIgnore
    public DepartamentoOid getDepartamentoDelMunicipioDepartamentopthisDepartamento() {
        return this.departamentoDelMunicipioDepartamentopthisDepartamento;
    }
    
    public void setDepartamentoDelMunicipioDepartamentopthisDepartamento(DepartamentoOid departamentoDelMunicipioDepartamentopthisDepartamento) {
        this.departamentoDelMunicipioDepartamentopthisDepartamento = departamentoDelMunicipioDepartamentopthisDepartamento;
    }

    @JsonIgnore
    public Departamento getDepartamentoDelMunicipioDepartamentopthisDepartamentoInstance() {
        return this.departamentoDelMunicipioDepartamentopthisDepartamentoInstance;
    }
    
    public void setDepartamentoDelMunicipioDepartamentopthisDepartamentoInstance(Departamento departamentoDelMunicipioDepartamentopthisDepartamento) {
        this.departamentoDelMunicipioDepartamentopthisDepartamentoInstance = departamentoDelMunicipioDepartamentopthisDepartamento;
        this.departamentoDelMunicipioDepartamentopthisDepartamento = departamentoDelMunicipioDepartamentopthisDepartamento != null ? departamentoDelMunicipioDepartamentopthisDepartamento.getOid() : null;
    }

    private void checkDepartamentoDelMunicipioDepartamentopthisDepartamento() throws NotNullArgumentException {
        if(this.getDepartamentoDelMunicipioDepartamentopthisDepartamentoInstance() == null || this.getDepartamentoDelMunicipioDepartamentopthisDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_DELMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_DELMUNICIPIODEPARTAMENTO_DEPARTAMENTODELMUNICIPIODEPARTAMENTOPTHISDEPARTAMENTO, DepartamentoConstants.SERV_ALIAS_DELMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_DELMUNICIPIODEPARTAMENTO_DEPARTAMENTODELMUNICIPIODEPARTAMENTOPTHISDEPARTAMENTO);
        }
    }

    @JsonIgnore
    public MunicipioOid getDepartamentoDelMunicipioDepartamentopevcMunicipio() {
        return this.departamentoDelMunicipioDepartamentopevcMunicipio;
    }
    
    public void setDepartamentoDelMunicipioDepartamentopevcMunicipio(MunicipioOid departamentoDelMunicipioDepartamentopevcMunicipio) {
        this.departamentoDelMunicipioDepartamentopevcMunicipio = departamentoDelMunicipioDepartamentopevcMunicipio;
    }

    @JsonIgnore
    public Municipio getDepartamentoDelMunicipioDepartamentopevcMunicipioInstance() {
        return this.departamentoDelMunicipioDepartamentopevcMunicipioInstance;
    }
    
    public void setDepartamentoDelMunicipioDepartamentopevcMunicipioInstance(Municipio departamentoDelMunicipioDepartamentopevcMunicipio) {
        this.departamentoDelMunicipioDepartamentopevcMunicipioInstance = departamentoDelMunicipioDepartamentopevcMunicipio;
        this.departamentoDelMunicipioDepartamentopevcMunicipio = departamentoDelMunicipioDepartamentopevcMunicipio != null ? departamentoDelMunicipioDepartamentopevcMunicipio.getOid() : null;
    }

    private void checkDepartamentoDelMunicipioDepartamentopevcMunicipio() throws NotNullArgumentException {
        if(this.getDepartamentoDelMunicipioDepartamentopevcMunicipioInstance() == null || this.getDepartamentoDelMunicipioDepartamentopevcMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_DELMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_DELMUNICIPIODEPARTAMENTO_DEPARTAMENTODELMUNICIPIODEPARTAMENTOPEVCMUNICIPIO, DepartamentoConstants.SERV_ALIAS_DELMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_DELMUNICIPIODEPARTAMENTO_DEPARTAMENTODELMUNICIPIODEPARTAMENTOPEVCMUNICIPIO);
        }
    }
}
