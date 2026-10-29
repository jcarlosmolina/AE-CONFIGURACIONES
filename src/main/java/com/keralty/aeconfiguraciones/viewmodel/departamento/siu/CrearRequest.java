package com.keralty.aeconfiguraciones.viewmodel.departamento.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DepartamentoConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long departamentocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String departamentocrearpatrDescripcion;

    @JsonProperty("p_atrcodigolegal")
    private String departamentocrearpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkDepartamentocrearpatrCodigo();
        checkDepartamentocrearpatrDescripcion();
        checkDepartamentocrearpatrCodigoLegal();
    }

    @JsonIgnore
    public Long getDepartamentocrearpatrCodigo() {
        return this.departamentocrearpatrCodigo;
    }
    
    public void setDepartamentocrearpatrCodigo(Long departamentocrearpatrCodigo) {
        this.departamentocrearpatrCodigo = departamentocrearpatrCodigo;
    }

    private void checkDepartamentocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getDepartamentocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_CREAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_CREAR_DEPARTAMENTOCREARPATRCODIGO, DepartamentoConstants.SERV_ALIAS_CREAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_CREAR_DEPARTAMENTOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getDepartamentocrearpatrDescripcion() {
        return this.departamentocrearpatrDescripcion;
    }
    
    public void setDepartamentocrearpatrDescripcion(String departamentocrearpatrDescripcion) {
        this.departamentocrearpatrDescripcion = departamentocrearpatrDescripcion;
    }

    private void checkDepartamentocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getDepartamentocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_CREAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_CREAR_DEPARTAMENTOCREARPATRDESCRIPCION, DepartamentoConstants.SERV_ALIAS_CREAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_CREAR_DEPARTAMENTOCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getDepartamentocrearpatrCodigoLegal() {
        return this.departamentocrearpatrCodigoLegal;
    }
    
    public void setDepartamentocrearpatrCodigoLegal(String departamentocrearpatrCodigoLegal) {
        this.departamentocrearpatrCodigoLegal = departamentocrearpatrCodigoLegal;
    }

    private void checkDepartamentocrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getDepartamentocrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(DepartamentoConstants.SERV_ID_CREAR, DepartamentoConstants.CLASS_ID, DepartamentoConstants.ARG_ID_CREAR_DEPARTAMENTOCREARPATRCODIGOLEGAL, DepartamentoConstants.SERV_ALIAS_CREAR, DepartamentoConstants.CLASS_ALIAS, DepartamentoConstants.ARG_ALIAS_CREAR_DEPARTAMENTOCREARPATRCODIGOLEGAL);
        }
    }
}
