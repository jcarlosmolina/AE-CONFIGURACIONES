package com.keralty.aeconfiguraciones.viewmodel.parentesco.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long parentescocrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String parentescocrearpatrDescripcion;

    @JsonProperty("p_atrestitular")
    private Boolean parentescocrearpatrEsTitular;

    @JsonProperty("p_atresconyuge")
    private Boolean parentescocrearpatrEsConyuge;

    @JsonProperty("p_atrespadre")
    private Boolean parentescocrearpatrEsPadre;

    @JsonProperty("p_atreshijo")
    private Boolean parentescocrearpatrEsHijo;

    @JsonProperty("p_atrnombrecorto")
    private String parentescocrearpatrNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescocrearpatrCodigo();
        checkParentescocrearpatrDescripcion();
        checkParentescocrearpatrEsTitular();
        checkParentescocrearpatrEsConyuge();
        checkParentescocrearpatrEsPadre();
        checkParentescocrearpatrEsHijo();
        checkParentescocrearpatrNombreCorto();
    }

    @JsonIgnore
    public Long getParentescocrearpatrCodigo() {
        return this.parentescocrearpatrCodigo;
    }
    
    public void setParentescocrearpatrCodigo(Long parentescocrearpatrCodigo) {
        this.parentescocrearpatrCodigo = parentescocrearpatrCodigo;
    }

    private void checkParentescocrearpatrCodigo() throws NotNullArgumentException {
        if(this.getParentescocrearpatrCodigo() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRCODIGO, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getParentescocrearpatrDescripcion() {
        return this.parentescocrearpatrDescripcion;
    }
    
    public void setParentescocrearpatrDescripcion(String parentescocrearpatrDescripcion) {
        this.parentescocrearpatrDescripcion = parentescocrearpatrDescripcion;
    }

    private void checkParentescocrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getParentescocrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRDESCRIPCION, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getParentescocrearpatrEsTitular() {
        return this.parentescocrearpatrEsTitular;
    }
    
    public void setParentescocrearpatrEsTitular(Boolean parentescocrearpatrEsTitular) {
        this.parentescocrearpatrEsTitular = parentescocrearpatrEsTitular;
    }

    private void checkParentescocrearpatrEsTitular() throws NotNullArgumentException {
        if(this.getParentescocrearpatrEsTitular() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRESTITULAR, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRESTITULAR);
        }
    }

    @JsonIgnore
    public Boolean getParentescocrearpatrEsConyuge() {
        return this.parentescocrearpatrEsConyuge;
    }
    
    public void setParentescocrearpatrEsConyuge(Boolean parentescocrearpatrEsConyuge) {
        this.parentescocrearpatrEsConyuge = parentescocrearpatrEsConyuge;
    }

    private void checkParentescocrearpatrEsConyuge() throws NotNullArgumentException {
        if(this.getParentescocrearpatrEsConyuge() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRESCONYUGE, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRESCONYUGE);
        }
    }

    @JsonIgnore
    public Boolean getParentescocrearpatrEsPadre() {
        return this.parentescocrearpatrEsPadre;
    }
    
    public void setParentescocrearpatrEsPadre(Boolean parentescocrearpatrEsPadre) {
        this.parentescocrearpatrEsPadre = parentescocrearpatrEsPadre;
    }

    private void checkParentescocrearpatrEsPadre() throws NotNullArgumentException {
        if(this.getParentescocrearpatrEsPadre() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRESPADRE, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRESPADRE);
        }
    }

    @JsonIgnore
    public Boolean getParentescocrearpatrEsHijo() {
        return this.parentescocrearpatrEsHijo;
    }
    
    public void setParentescocrearpatrEsHijo(Boolean parentescocrearpatrEsHijo) {
        this.parentescocrearpatrEsHijo = parentescocrearpatrEsHijo;
    }

    private void checkParentescocrearpatrEsHijo() throws NotNullArgumentException {
        if(this.getParentescocrearpatrEsHijo() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRESHIJO, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRESHIJO);
        }
    }

    @JsonIgnore
    public String getParentescocrearpatrNombreCorto() {
        return this.parentescocrearpatrNombreCorto;
    }
    
    public void setParentescocrearpatrNombreCorto(String parentescocrearpatrNombreCorto) {
        this.parentescocrearpatrNombreCorto = parentescocrearpatrNombreCorto;
    }

    private void checkParentescocrearpatrNombreCorto() throws NotNullArgumentException {
        if(this.getParentescocrearpatrNombreCorto() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_CREAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_CREAR_PARENTESCOCREARPATRNOMBRECORTO, ParentescoConstants.SERV_ALIAS_CREAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_CREAR_PARENTESCOCREARPATRNOMBRECORTO);
        }
    }
}
