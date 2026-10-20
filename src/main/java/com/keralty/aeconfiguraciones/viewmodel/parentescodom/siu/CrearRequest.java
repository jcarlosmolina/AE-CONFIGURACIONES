package com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;

public class CrearRequest {

    @JsonProperty("p_agrparentesco_json")
    private String parentescoDomcrearpagrParentesco;
    
    @JsonIgnore
    private Parentesco parentescoDomcrearpagrParentescoInstance;

    @JsonProperty("p_agrdominio_oid")
    private DominioOid parentescoDomcrearpagrDominio;
    
    @JsonIgnore
    private Dominio parentescoDomcrearpagrDominioInstance;

    @JsonProperty("p_atredadminimaa")
    private Long parentescoDomcrearpatrEdadMinimaA;

    @JsonProperty("p_atredadmaximaa")
    private Long parentescoDomcrearpatrEdadMaximaA;

    @JsonProperty("p_atredadminimad")
    private Long parentescoDomcrearpatrEdadMinimaD;

    @JsonProperty("p_atredadmaximap")
    private Long parentescoDomcrearpatrEdadMaximaP;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescoDomcrearpagrParentesco();
        checkParentescoDomcrearpagrDominio();
    }

    @JsonIgnore
    public String getParentescoDomcrearpagrParentesco() {
        return this.parentescoDomcrearpagrParentesco;
    }
    
    public void setParentescoDomcrearpagrParentesco(String parentescoDomcrearpagrParentesco) {
        this.parentescoDomcrearpagrParentesco = parentescoDomcrearpagrParentesco;
    }

    @JsonIgnore
    public Parentesco getParentescoDomcrearpagrParentescoInstance() {
        return this.parentescoDomcrearpagrParentescoInstance;
    }
    
    public void setParentescoDomcrearpagrParentescoInstance(Parentesco parentescoDomcrearpagrParentesco) {
        this.parentescoDomcrearpagrParentescoInstance = parentescoDomcrearpagrParentesco;
        this.parentescoDomcrearpagrParentesco = parentescoDomcrearpagrParentesco != null ? parentescoDomcrearpagrParentesco.getOid().toJson() : "";
    }

    private void checkParentescoDomcrearpagrParentesco() throws NotNullArgumentException {
        if(this.getParentescoDomcrearpagrParentescoInstance() == null || this.getParentescoDomcrearpagrParentescoInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoDomConstants.SERV_ID_CREAR, ParentescoDomConstants.CLASS_ID, ParentescoDomConstants.ARG_ID_CREAR_PARENTESCODOMCREARPAGRPARENTESCO, ParentescoDomConstants.SERV_ALIAS_CREAR, ParentescoDomConstants.CLASS_ALIAS, ParentescoDomConstants.ARG_ALIAS_CREAR_PARENTESCODOMCREARPAGRPARENTESCO);
        }
    }

    @JsonIgnore
    public DominioOid getParentescoDomcrearpagrDominio() {
        return this.parentescoDomcrearpagrDominio;
    }
    
    public void setParentescoDomcrearpagrDominio(DominioOid parentescoDomcrearpagrDominio) {
        this.parentescoDomcrearpagrDominio = parentescoDomcrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getParentescoDomcrearpagrDominioInstance() {
        return this.parentescoDomcrearpagrDominioInstance;
    }
    
    public void setParentescoDomcrearpagrDominioInstance(Dominio parentescoDomcrearpagrDominio) {
        this.parentescoDomcrearpagrDominioInstance = parentescoDomcrearpagrDominio;
        this.parentescoDomcrearpagrDominio = parentescoDomcrearpagrDominio != null ? parentescoDomcrearpagrDominio.getOid() : null;
    }

    private void checkParentescoDomcrearpagrDominio() throws NotNullArgumentException {
        if(this.getParentescoDomcrearpagrDominioInstance() == null || this.getParentescoDomcrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoDomConstants.SERV_ID_CREAR, ParentescoDomConstants.CLASS_ID, ParentescoDomConstants.ARG_ID_CREAR_PARENTESCODOMCREARPAGRDOMINIO, ParentescoDomConstants.SERV_ALIAS_CREAR, ParentescoDomConstants.CLASS_ALIAS, ParentescoDomConstants.ARG_ALIAS_CREAR_PARENTESCODOMCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Long getParentescoDomcrearpatrEdadMinimaA() {
        return this.parentescoDomcrearpatrEdadMinimaA;
    }
    
    public void setParentescoDomcrearpatrEdadMinimaA(Long parentescoDomcrearpatrEdadMinimaA) {
        this.parentescoDomcrearpatrEdadMinimaA = parentescoDomcrearpatrEdadMinimaA;
    }

    @JsonIgnore
    public Long getParentescoDomcrearpatrEdadMaximaA() {
        return this.parentescoDomcrearpatrEdadMaximaA;
    }
    
    public void setParentescoDomcrearpatrEdadMaximaA(Long parentescoDomcrearpatrEdadMaximaA) {
        this.parentescoDomcrearpatrEdadMaximaA = parentescoDomcrearpatrEdadMaximaA;
    }

    @JsonIgnore
    public Long getParentescoDomcrearpatrEdadMinimaD() {
        return this.parentescoDomcrearpatrEdadMinimaD;
    }
    
    public void setParentescoDomcrearpatrEdadMinimaD(Long parentescoDomcrearpatrEdadMinimaD) {
        this.parentescoDomcrearpatrEdadMinimaD = parentescoDomcrearpatrEdadMinimaD;
    }

    @JsonIgnore
    public Long getParentescoDomcrearpatrEdadMaximaP() {
        return this.parentescoDomcrearpatrEdadMaximaP;
    }
    
    public void setParentescoDomcrearpatrEdadMaximaP(Long parentescoDomcrearpatrEdadMaximaP) {
        this.parentescoDomcrearpatrEdadMaximaP = parentescoDomcrearpatrEdadMaximaP;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
