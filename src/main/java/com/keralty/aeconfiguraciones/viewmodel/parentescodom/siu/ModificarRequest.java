package com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;

public class ModificarRequest {

    @JsonProperty("p_thisparentescodom_oid")
    private ParentescoDomOid parentescoDommodificarpthisParentescoDom;
    
    @JsonIgnore
    private ParentescoDom parentescoDommodificarpthisParentescoDomInstance;

    @JsonProperty("p_edadminimaa")
    private Long parentescoDommodificarpEdadMinimaA;

    @JsonProperty("p_edadmaximaa")
    private Long parentescoDommodificarpEdadMaximaA;

    @JsonProperty("p_edadminimad")
    private Long parentescoDommodificarpEdadMinimaD;

    @JsonProperty("p_edadmaximap")
    private Long parentescoDommodificarpEdadMaximaP;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescoDommodificarpthisParentescoDom();
    }

    @JsonIgnore
    public ParentescoDomOid getParentescoDommodificarpthisParentescoDom() {
        return this.parentescoDommodificarpthisParentescoDom;
    }
    
    public void setParentescoDommodificarpthisParentescoDom(ParentescoDomOid parentescoDommodificarpthisParentescoDom) {
        this.parentescoDommodificarpthisParentescoDom = parentescoDommodificarpthisParentescoDom;
    }

    @JsonIgnore
    public ParentescoDom getParentescoDommodificarpthisParentescoDomInstance() {
        return this.parentescoDommodificarpthisParentescoDomInstance;
    }
    
    public void setParentescoDommodificarpthisParentescoDomInstance(ParentescoDom parentescoDommodificarpthisParentescoDom) {
        this.parentescoDommodificarpthisParentescoDomInstance = parentescoDommodificarpthisParentescoDom;
        this.parentescoDommodificarpthisParentescoDom = parentescoDommodificarpthisParentescoDom != null ? parentescoDommodificarpthisParentescoDom.getOid() : null;
    }

    private void checkParentescoDommodificarpthisParentescoDom() throws NotNullArgumentException {
        if(this.getParentescoDommodificarpthisParentescoDomInstance() == null || this.getParentescoDommodificarpthisParentescoDomInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoDomConstants.SERV_ID_MODIFICAR, ParentescoDomConstants.CLASS_ID, ParentescoDomConstants.ARG_ID_MODIFICAR_PARENTESCODOMMODIFICARPTHISPARENTESCODOM, ParentescoDomConstants.SERV_ALIAS_MODIFICAR, ParentescoDomConstants.CLASS_ALIAS, ParentescoDomConstants.ARG_ALIAS_MODIFICAR_PARENTESCODOMMODIFICARPTHISPARENTESCODOM);
        }
    }

    @JsonIgnore
    public Long getParentescoDommodificarpEdadMinimaA() {
        return this.parentescoDommodificarpEdadMinimaA;
    }
    
    public void setParentescoDommodificarpEdadMinimaA(Long parentescoDommodificarpEdadMinimaA) {
        this.parentescoDommodificarpEdadMinimaA = parentescoDommodificarpEdadMinimaA;
    }

    @JsonIgnore
    public Long getParentescoDommodificarpEdadMaximaA() {
        return this.parentescoDommodificarpEdadMaximaA;
    }
    
    public void setParentescoDommodificarpEdadMaximaA(Long parentescoDommodificarpEdadMaximaA) {
        this.parentescoDommodificarpEdadMaximaA = parentescoDommodificarpEdadMaximaA;
    }

    @JsonIgnore
    public Long getParentescoDommodificarpEdadMinimaD() {
        return this.parentescoDommodificarpEdadMinimaD;
    }
    
    public void setParentescoDommodificarpEdadMinimaD(Long parentescoDommodificarpEdadMinimaD) {
        this.parentescoDommodificarpEdadMinimaD = parentescoDommodificarpEdadMinimaD;
    }

    @JsonIgnore
    public Long getParentescoDommodificarpEdadMaximaP() {
        return this.parentescoDommodificarpEdadMaximaP;
    }
    
    public void setParentescoDommodificarpEdadMaximaP(Long parentescoDommodificarpEdadMaximaP) {
        this.parentescoDommodificarpEdadMaximaP = parentescoDommodificarpEdadMaximaP;
    }
}
