package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class InsMunicipioDepartamentoRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioInsMunicipioDepartamentopthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioInsMunicipioDepartamentopthisMunicipioInstance;

    @JsonProperty("p_evcdepartamento_oid")
    private DepartamentoOid municipioInsMunicipioDepartamentopevcDepartamento;
    
    @JsonIgnore
    private Departamento municipioInsMunicipioDepartamentopevcDepartamentoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioInsMunicipioDepartamentopthisMunicipio();
        checkMunicipioInsMunicipioDepartamentopevcDepartamento();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioInsMunicipioDepartamentopthisMunicipio() {
        return this.municipioInsMunicipioDepartamentopthisMunicipio;
    }
    
    public void setMunicipioInsMunicipioDepartamentopthisMunicipio(MunicipioOid municipioInsMunicipioDepartamentopthisMunicipio) {
        this.municipioInsMunicipioDepartamentopthisMunicipio = municipioInsMunicipioDepartamentopthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioInsMunicipioDepartamentopthisMunicipioInstance() {
        return this.municipioInsMunicipioDepartamentopthisMunicipioInstance;
    }
    
    public void setMunicipioInsMunicipioDepartamentopthisMunicipioInstance(Municipio municipioInsMunicipioDepartamentopthisMunicipio) {
        this.municipioInsMunicipioDepartamentopthisMunicipioInstance = municipioInsMunicipioDepartamentopthisMunicipio;
        this.municipioInsMunicipioDepartamentopthisMunicipio = municipioInsMunicipioDepartamentopthisMunicipio != null ? municipioInsMunicipioDepartamentopthisMunicipio.getOid() : null;
    }

    private void checkMunicipioInsMunicipioDepartamentopthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioInsMunicipioDepartamentopthisMunicipioInstance() == null || this.getMunicipioInsMunicipioDepartamentopthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_INSMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_INSMUNICIPIODEPARTAMENTO_MUNICIPIOINSMUNICIPIODEPARTAMENTOPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_INSMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_INSMUNICIPIODEPARTAMENTO_MUNICIPIOINSMUNICIPIODEPARTAMENTOPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public DepartamentoOid getMunicipioInsMunicipioDepartamentopevcDepartamento() {
        return this.municipioInsMunicipioDepartamentopevcDepartamento;
    }
    
    public void setMunicipioInsMunicipioDepartamentopevcDepartamento(DepartamentoOid municipioInsMunicipioDepartamentopevcDepartamento) {
        this.municipioInsMunicipioDepartamentopevcDepartamento = municipioInsMunicipioDepartamentopevcDepartamento;
    }

    @JsonIgnore
    public Departamento getMunicipioInsMunicipioDepartamentopevcDepartamentoInstance() {
        return this.municipioInsMunicipioDepartamentopevcDepartamentoInstance;
    }
    
    public void setMunicipioInsMunicipioDepartamentopevcDepartamentoInstance(Departamento municipioInsMunicipioDepartamentopevcDepartamento) {
        this.municipioInsMunicipioDepartamentopevcDepartamentoInstance = municipioInsMunicipioDepartamentopevcDepartamento;
        this.municipioInsMunicipioDepartamentopevcDepartamento = municipioInsMunicipioDepartamentopevcDepartamento != null ? municipioInsMunicipioDepartamentopevcDepartamento.getOid() : null;
    }

    private void checkMunicipioInsMunicipioDepartamentopevcDepartamento() throws NotNullArgumentException {
        if(this.getMunicipioInsMunicipioDepartamentopevcDepartamentoInstance() == null || this.getMunicipioInsMunicipioDepartamentopevcDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_INSMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_INSMUNICIPIODEPARTAMENTO_MUNICIPIOINSMUNICIPIODEPARTAMENTOPEVCDEPARTAMENTO, MunicipioConstants.SERV_ALIAS_INSMUNICIPIODEPARTAMENTO, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_INSMUNICIPIODEPARTAMENTO_MUNICIPIOINSMUNICIPIODEPARTAMENTOPEVCDEPARTAMENTO);
        }
    }
}
