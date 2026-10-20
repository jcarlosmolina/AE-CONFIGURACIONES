package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class InsMunicipioDepartamentoRequest {

    @JsonProperty("p_thisdepartamento_oid")
    private DepartamentoOid departamentoInsMunicipioDepartamentopthisDepartamento;
    
    @JsonIgnore
    private Departamento departamentoInsMunicipioDepartamentopthisDepartamentoInstance;

    @JsonProperty("p_evcmunicipio_oid")
    private MunicipioOid departamentoInsMunicipioDepartamentopevcMunicipio;
    
    @JsonIgnore
    private Municipio departamentoInsMunicipioDepartamentopevcMunicipioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentoInsMunicipioDepartamentopthisDepartamento();
        checkDepartamentoInsMunicipioDepartamentopevcMunicipio();
    }

    @JsonIgnore
    public DepartamentoOid getDepartamentoInsMunicipioDepartamentopthisDepartamento() {
        return this.departamentoInsMunicipioDepartamentopthisDepartamento;
    }
    
    public void setDepartamentoInsMunicipioDepartamentopthisDepartamento(DepartamentoOid departamentoInsMunicipioDepartamentopthisDepartamento) {
        this.departamentoInsMunicipioDepartamentopthisDepartamento = departamentoInsMunicipioDepartamentopthisDepartamento;
    }

    @JsonIgnore
    public Departamento getDepartamentoInsMunicipioDepartamentopthisDepartamentoInstance() {
        return this.departamentoInsMunicipioDepartamentopthisDepartamentoInstance;
    }
    
    public void setDepartamentoInsMunicipioDepartamentopthisDepartamentoInstance(Departamento departamentoInsMunicipioDepartamentopthisDepartamento) {
        this.departamentoInsMunicipioDepartamentopthisDepartamentoInstance = departamentoInsMunicipioDepartamentopthisDepartamento;
        this.departamentoInsMunicipioDepartamentopthisDepartamento = departamentoInsMunicipioDepartamentopthisDepartamento != null ? departamentoInsMunicipioDepartamentopthisDepartamento.getOid() : null;
    }

    private void checkDepartamentoInsMunicipioDepartamentopthisDepartamento() throws NotNullArgumentException {
        if(this.getDepartamentoInsMunicipioDepartamentopthisDepartamentoInstance() == null || this.getDepartamentoInsMunicipioDepartamentopthisDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_INSMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_INSMUNICIPIODEPARTAMENTO_DEPARTAMENTOINSMUNICIPIODEPARTAMENTOPTHISDEPARTAMENTO, DepartamentoConstants.SERV_ALIAS_INSMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_INSMUNICIPIODEPARTAMENTO_DEPARTAMENTOINSMUNICIPIODEPARTAMENTOPTHISDEPARTAMENTO);
        }
    }

    @JsonIgnore
    public MunicipioOid getDepartamentoInsMunicipioDepartamentopevcMunicipio() {
        return this.departamentoInsMunicipioDepartamentopevcMunicipio;
    }
    
    public void setDepartamentoInsMunicipioDepartamentopevcMunicipio(MunicipioOid departamentoInsMunicipioDepartamentopevcMunicipio) {
        this.departamentoInsMunicipioDepartamentopevcMunicipio = departamentoInsMunicipioDepartamentopevcMunicipio;
    }

    @JsonIgnore
    public Municipio getDepartamentoInsMunicipioDepartamentopevcMunicipioInstance() {
        return this.departamentoInsMunicipioDepartamentopevcMunicipioInstance;
    }
    
    public void setDepartamentoInsMunicipioDepartamentopevcMunicipioInstance(Municipio departamentoInsMunicipioDepartamentopevcMunicipio) {
        this.departamentoInsMunicipioDepartamentopevcMunicipioInstance = departamentoInsMunicipioDepartamentopevcMunicipio;
        this.departamentoInsMunicipioDepartamentopevcMunicipio = departamentoInsMunicipioDepartamentopevcMunicipio != null ? departamentoInsMunicipioDepartamentopevcMunicipio.getOid() : null;
    }

    private void checkDepartamentoInsMunicipioDepartamentopevcMunicipio() throws NotNullArgumentException {
        if(this.getDepartamentoInsMunicipioDepartamentopevcMunicipioInstance() == null || this.getDepartamentoInsMunicipioDepartamentopevcMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_INSMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_INSMUNICIPIODEPARTAMENTO_DEPARTAMENTOINSMUNICIPIODEPARTAMENTOPEVCMUNICIPIO, DepartamentoConstants.SERV_ALIAS_INSMUNICIPIODEPARTAMENTO, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_INSMUNICIPIODEPARTAMENTO_DEPARTAMENTOINSMUNICIPIODEPARTAMENTOPEVCMUNICIPIO);
        }
    }
}
