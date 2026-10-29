package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class DelMunicipioDepartamentoRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioDelMunicipioDepartamentopthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioDelMunicipioDepartamentopthisMunicipioInstance;

    @JsonProperty("p_evcdepartamento_oid")
    private DepartamentoOid municipioDelMunicipioDepartamentopevcDepartamento;
    
    @JsonIgnore
    private Departamento municipioDelMunicipioDepartamentopevcDepartamentoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioDelMunicipioDepartamentopthisMunicipio();
        checkMunicipioDelMunicipioDepartamentopevcDepartamento();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioDelMunicipioDepartamentopthisMunicipio() {
        return this.municipioDelMunicipioDepartamentopthisMunicipio;
    }
    
    public void setMunicipioDelMunicipioDepartamentopthisMunicipio(MunicipioOid municipioDelMunicipioDepartamentopthisMunicipio) {
        this.municipioDelMunicipioDepartamentopthisMunicipio = municipioDelMunicipioDepartamentopthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioDelMunicipioDepartamentopthisMunicipioInstance() {
        return this.municipioDelMunicipioDepartamentopthisMunicipioInstance;
    }
    
    public void setMunicipioDelMunicipioDepartamentopthisMunicipioInstance(Municipio municipioDelMunicipioDepartamentopthisMunicipio) {
        this.municipioDelMunicipioDepartamentopthisMunicipioInstance = municipioDelMunicipioDepartamentopthisMunicipio;
        this.municipioDelMunicipioDepartamentopthisMunicipio = municipioDelMunicipioDepartamentopthisMunicipio != null ? municipioDelMunicipioDepartamentopthisMunicipio.getOid() : null;
    }

    private void checkMunicipioDelMunicipioDepartamentopthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioDelMunicipioDepartamentopthisMunicipioInstance() == null || this.getMunicipioDelMunicipioDepartamentopthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_DELMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_DELMUNICIPIODEPARTAMENTO_MUNICIPIODELMUNICIPIODEPARTAMENTOPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_DELMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_DELMUNICIPIODEPARTAMENTO_MUNICIPIODELMUNICIPIODEPARTAMENTOPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public DepartamentoOid getMunicipioDelMunicipioDepartamentopevcDepartamento() {
        return this.municipioDelMunicipioDepartamentopevcDepartamento;
    }
    
    public void setMunicipioDelMunicipioDepartamentopevcDepartamento(DepartamentoOid municipioDelMunicipioDepartamentopevcDepartamento) {
        this.municipioDelMunicipioDepartamentopevcDepartamento = municipioDelMunicipioDepartamentopevcDepartamento;
    }

    @JsonIgnore
    public Departamento getMunicipioDelMunicipioDepartamentopevcDepartamentoInstance() {
        return this.municipioDelMunicipioDepartamentopevcDepartamentoInstance;
    }
    
    public void setMunicipioDelMunicipioDepartamentopevcDepartamentoInstance(Departamento municipioDelMunicipioDepartamentopevcDepartamento) {
        this.municipioDelMunicipioDepartamentopevcDepartamentoInstance = municipioDelMunicipioDepartamentopevcDepartamento;
        this.municipioDelMunicipioDepartamentopevcDepartamento = municipioDelMunicipioDepartamentopevcDepartamento != null ? municipioDelMunicipioDepartamentopevcDepartamento.getOid() : null;
    }

    private void checkMunicipioDelMunicipioDepartamentopevcDepartamento() throws NotNullArgumentException {
        if(this.getMunicipioDelMunicipioDepartamentopevcDepartamentoInstance() == null || this.getMunicipioDelMunicipioDepartamentopevcDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_DELMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_DELMUNICIPIODEPARTAMENTO_MUNICIPIODELMUNICIPIODEPARTAMENTOPEVCDEPARTAMENTO, MunicipioConstants.SERV_ALIAS_DELMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_DELMUNICIPIODEPARTAMENTO_MUNICIPIODELMUNICIPIODEPARTAMENTOPEVCDEPARTAMENTO);
        }
    }
}
