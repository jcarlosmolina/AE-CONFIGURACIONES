package com.keralty.aeconfiguraciones.viewmodel.parentesco.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;

public class ModificarRequest {

    @JsonProperty("p_thisparentesco_oid")
    private ParentescoOid parentescomodificarpthisParentesco;
    
    @JsonIgnore
    private Parentesco parentescomodificarpthisParentescoInstance;

    @JsonProperty("p_estitular")
    private Boolean parentescomodificarpEsTitular;

    @JsonProperty("p_esconyuge")
    private Boolean parentescomodificarpEsConyuge;

    @JsonProperty("p_espadre")
    private Boolean parentescomodificarpEsPadre;

    @JsonProperty("p_eshijo")
    private Boolean parentescomodificarpEsHijo;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescomodificarpthisParentesco();
        checkParentescomodificarpEsTitular();
        checkParentescomodificarpEsConyuge();
        checkParentescomodificarpEsPadre();
        checkParentescomodificarpEsHijo();
    }

    @JsonIgnore
    public ParentescoOid getParentescomodificarpthisParentesco() {
        return this.parentescomodificarpthisParentesco;
    }
    
    public void setParentescomodificarpthisParentesco(ParentescoOid parentescomodificarpthisParentesco) {
        this.parentescomodificarpthisParentesco = parentescomodificarpthisParentesco;
    }

    @JsonIgnore
    public Parentesco getParentescomodificarpthisParentescoInstance() {
        return this.parentescomodificarpthisParentescoInstance;
    }
    
    public void setParentescomodificarpthisParentescoInstance(Parentesco parentescomodificarpthisParentesco) {
        this.parentescomodificarpthisParentescoInstance = parentescomodificarpthisParentesco;
        this.parentescomodificarpthisParentesco = parentescomodificarpthisParentesco != null ? parentescomodificarpthisParentesco.getOid() : null;
    }

    private void checkParentescomodificarpthisParentesco() throws NotNullArgumentException {
        if(this.getParentescomodificarpthisParentescoInstance() == null || this.getParentescomodificarpthisParentescoInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_MODIFICAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_MODIFICAR_PARENTESCOMODIFICARPTHISPARENTESCO, ParentescoConstants.SERV_ALIAS_MODIFICAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_MODIFICAR_PARENTESCOMODIFICARPTHISPARENTESCO);
        }
    }

    @JsonIgnore
    public Boolean getParentescomodificarpEsTitular() {
        return this.parentescomodificarpEsTitular;
    }
    
    public void setParentescomodificarpEsTitular(Boolean parentescomodificarpEsTitular) {
        this.parentescomodificarpEsTitular = parentescomodificarpEsTitular;
    }

    private void checkParentescomodificarpEsTitular() throws NotNullArgumentException {
        if(this.getParentescomodificarpEsTitular() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_MODIFICAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_MODIFICAR_PARENTESCOMODIFICARPESTITULAR, ParentescoConstants.SERV_ALIAS_MODIFICAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_MODIFICAR_PARENTESCOMODIFICARPESTITULAR);
        }
    }

    @JsonIgnore
    public Boolean getParentescomodificarpEsConyuge() {
        return this.parentescomodificarpEsConyuge;
    }
    
    public void setParentescomodificarpEsConyuge(Boolean parentescomodificarpEsConyuge) {
        this.parentescomodificarpEsConyuge = parentescomodificarpEsConyuge;
    }

    private void checkParentescomodificarpEsConyuge() throws NotNullArgumentException {
        if(this.getParentescomodificarpEsConyuge() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_MODIFICAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_MODIFICAR_PARENTESCOMODIFICARPESCONYUGE, ParentescoConstants.SERV_ALIAS_MODIFICAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_MODIFICAR_PARENTESCOMODIFICARPESCONYUGE);
        }
    }

    @JsonIgnore
    public Boolean getParentescomodificarpEsPadre() {
        return this.parentescomodificarpEsPadre;
    }
    
    public void setParentescomodificarpEsPadre(Boolean parentescomodificarpEsPadre) {
        this.parentescomodificarpEsPadre = parentescomodificarpEsPadre;
    }

    private void checkParentescomodificarpEsPadre() throws NotNullArgumentException {
        if(this.getParentescomodificarpEsPadre() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_MODIFICAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_MODIFICAR_PARENTESCOMODIFICARPESPADRE, ParentescoConstants.SERV_ALIAS_MODIFICAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_MODIFICAR_PARENTESCOMODIFICARPESPADRE);
        }
    }

    @JsonIgnore
    public Boolean getParentescomodificarpEsHijo() {
        return this.parentescomodificarpEsHijo;
    }
    
    public void setParentescomodificarpEsHijo(Boolean parentescomodificarpEsHijo) {
        this.parentescomodificarpEsHijo = parentescomodificarpEsHijo;
    }

    private void checkParentescomodificarpEsHijo() throws NotNullArgumentException {
        if(this.getParentescomodificarpEsHijo() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_MODIFICAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_MODIFICAR_PARENTESCOMODIFICARPESHIJO, ParentescoConstants.SERV_ALIAS_MODIFICAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_MODIFICAR_PARENTESCOMODIFICARPESHIJO);
        }
    }
}
