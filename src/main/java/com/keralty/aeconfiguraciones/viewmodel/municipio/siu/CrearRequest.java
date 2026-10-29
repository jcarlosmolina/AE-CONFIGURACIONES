package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;

public class CrearRequest {

    @JsonProperty("p_agrdepartamento_oid")
    private DepartamentoOid municipiocrearpagrDepartamento;
    
    @JsonIgnore
    private Departamento municipiocrearpagrDepartamentoInstance;

    @JsonProperty("p_atrcodigo")
    private Long municipiocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String municipiocrearpatrDescripcion;

    @JsonProperty("p_atrcodigolegal")
    private String municipiocrearpatrCodigoLegal;

    @JsonProperty("p_atresvalidoeps")
    private Boolean municipiocrearpatrEsValidoEPS;

    @JsonProperty("p_atraplicaiva")
    private Boolean municipiocrearpatrAplicaIVA;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipiocrearpatrCodigo();
        checkMunicipiocrearpatrDescripcion();
        checkMunicipiocrearpatrCodigoLegal();
        checkMunicipiocrearpatrEsValidoEPS();
        checkMunicipiocrearpatrAplicaIVA();
    }

    @JsonIgnore
    public DepartamentoOid getMunicipiocrearpagrDepartamento() {
        return this.municipiocrearpagrDepartamento;
    }
    
    public void setMunicipiocrearpagrDepartamento(DepartamentoOid municipiocrearpagrDepartamento) {
        this.municipiocrearpagrDepartamento = municipiocrearpagrDepartamento;
    }

    @JsonIgnore
    public Departamento getMunicipiocrearpagrDepartamentoInstance() {
        return this.municipiocrearpagrDepartamentoInstance;
    }
    
    public void setMunicipiocrearpagrDepartamentoInstance(Departamento municipiocrearpagrDepartamento) {
        this.municipiocrearpagrDepartamentoInstance = municipiocrearpagrDepartamento;
        this.municipiocrearpagrDepartamento = municipiocrearpagrDepartamento != null ? municipiocrearpagrDepartamento.getOid() : null;
    }

    @JsonIgnore
    public Long getMunicipiocrearpatrCodigo() {
        return this.municipiocrearpatrCodigo;
    }
    
    public void setMunicipiocrearpatrCodigo(Long municipiocrearpatrCodigo) {
        this.municipiocrearpatrCodigo = municipiocrearpatrCodigo;
    }

    private void checkMunicipiocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getMunicipiocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CREAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CREAR_MUNICIPIOCREARPATRCODIGO, MunicipioConstants.SERV_ALIAS_CREAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CREAR_MUNICIPIOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getMunicipiocrearpatrDescripcion() {
        return this.municipiocrearpatrDescripcion;
    }
    
    public void setMunicipiocrearpatrDescripcion(String municipiocrearpatrDescripcion) {
        this.municipiocrearpatrDescripcion = municipiocrearpatrDescripcion;
    }

    private void checkMunicipiocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getMunicipiocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CREAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CREAR_MUNICIPIOCREARPATRDESCRIPCION, MunicipioConstants.SERV_ALIAS_CREAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CREAR_MUNICIPIOCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getMunicipiocrearpatrCodigoLegal() {
        return this.municipiocrearpatrCodigoLegal;
    }
    
    public void setMunicipiocrearpatrCodigoLegal(String municipiocrearpatrCodigoLegal) {
        this.municipiocrearpatrCodigoLegal = municipiocrearpatrCodigoLegal;
    }

    private void checkMunicipiocrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getMunicipiocrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CREAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CREAR_MUNICIPIOCREARPATRCODIGOLEGAL, MunicipioConstants.SERV_ALIAS_CREAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CREAR_MUNICIPIOCREARPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getMunicipiocrearpatrEsValidoEPS() {
        return this.municipiocrearpatrEsValidoEPS;
    }
    
    public void setMunicipiocrearpatrEsValidoEPS(Boolean municipiocrearpatrEsValidoEPS) {
        this.municipiocrearpatrEsValidoEPS = municipiocrearpatrEsValidoEPS;
    }

    private void checkMunicipiocrearpatrEsValidoEPS() throws NotNullArgumentException {
        if(this.getMunicipiocrearpatrEsValidoEPS() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CREAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CREAR_MUNICIPIOCREARPATRESVALIDOEPS, MunicipioConstants.SERV_ALIAS_CREAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CREAR_MUNICIPIOCREARPATRESVALIDOEPS);
        }
    }

    @JsonIgnore
    public Boolean getMunicipiocrearpatrAplicaIVA() {
        return this.municipiocrearpatrAplicaIVA;
    }
    
    public void setMunicipiocrearpatrAplicaIVA(Boolean municipiocrearpatrAplicaIVA) {
        this.municipiocrearpatrAplicaIVA = municipiocrearpatrAplicaIVA;
    }

    private void checkMunicipiocrearpatrAplicaIVA() throws NotNullArgumentException {
        if(this.getMunicipiocrearpatrAplicaIVA() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CREAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CREAR_MUNICIPIOCREARPATRAPLICAIVA, MunicipioConstants.SERV_ALIAS_CREAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CREAR_MUNICIPIOCREARPATRAPLICAIVA);
        }
    }
}
