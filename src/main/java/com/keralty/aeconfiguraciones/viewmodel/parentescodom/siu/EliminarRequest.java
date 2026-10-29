package com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoDomConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;

public class EliminarRequest {

    @JsonProperty("p_thisparentescodom_oid")
    private ParentescoDomOid parentescoDomeliminarpthisParentescoDom;
    
    @JsonIgnore
    private ParentescoDom parentescoDomeliminarpthisParentescoDomInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescoDomeliminarpthisParentescoDom();
    }

    @JsonIgnore
    public ParentescoDomOid getParentescoDomeliminarpthisParentescoDom() {
        return this.parentescoDomeliminarpthisParentescoDom;
    }
    
    public void setParentescoDomeliminarpthisParentescoDom(ParentescoDomOid parentescoDomeliminarpthisParentescoDom) {
        this.parentescoDomeliminarpthisParentescoDom = parentescoDomeliminarpthisParentescoDom;
    }

    @JsonIgnore
    public ParentescoDom getParentescoDomeliminarpthisParentescoDomInstance() {
        return this.parentescoDomeliminarpthisParentescoDomInstance;
    }
    
    public void setParentescoDomeliminarpthisParentescoDomInstance(ParentescoDom parentescoDomeliminarpthisParentescoDom) {
        this.parentescoDomeliminarpthisParentescoDomInstance = parentescoDomeliminarpthisParentescoDom;
        this.parentescoDomeliminarpthisParentescoDom = parentescoDomeliminarpthisParentescoDom != null ? parentescoDomeliminarpthisParentescoDom.getOid() : null;
    }

    private void checkParentescoDomeliminarpthisParentescoDom() throws NotNullArgumentException {
        if(this.getParentescoDomeliminarpthisParentescoDomInstance() == null || this.getParentescoDomeliminarpthisParentescoDomInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoDomConstants.SERV_ID_ELIMINAR, ParentescoDomConstants.CLASS_ID, ParentescoDomConstants.ARG_ID_ELIMINAR_PARENTESCODOMELIMINARPTHISPARENTESCODOM, ParentescoDomConstants.SERV_ALIAS_ELIMINAR, ParentescoDomConstants.CLASS_ALIAS, ParentescoDomConstants.ARG_ALIAS_ELIMINAR_PARENTESCODOMELIMINARPTHISPARENTESCODOM);
        }
    }
}
