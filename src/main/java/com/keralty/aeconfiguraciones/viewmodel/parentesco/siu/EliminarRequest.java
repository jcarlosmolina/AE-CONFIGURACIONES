package com.keralty.aeconfiguraciones.viewmodel.parentesco.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;

public class EliminarRequest {

    @JsonProperty("p_thisparentesco_oid")
    private ParentescoOid parentescoeliminarpthisParentesco;
    
    @JsonIgnore
    private Parentesco parentescoeliminarpthisParentescoInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescoeliminarpthisParentesco();
    }

    @JsonIgnore
    public ParentescoOid getParentescoeliminarpthisParentesco() {
        return this.parentescoeliminarpthisParentesco;
    }
    
    public void setParentescoeliminarpthisParentesco(ParentescoOid parentescoeliminarpthisParentesco) {
        this.parentescoeliminarpthisParentesco = parentescoeliminarpthisParentesco;
    }

    @JsonIgnore
    public Parentesco getParentescoeliminarpthisParentescoInstance() {
        return this.parentescoeliminarpthisParentescoInstance;
    }
    
    public void setParentescoeliminarpthisParentescoInstance(Parentesco parentescoeliminarpthisParentesco) {
        this.parentescoeliminarpthisParentescoInstance = parentescoeliminarpthisParentesco;
        this.parentescoeliminarpthisParentesco = parentescoeliminarpthisParentesco != null ? parentescoeliminarpthisParentesco.getOid() : null;
    }

    private void checkParentescoeliminarpthisParentesco() throws NotNullArgumentException {
        if(this.getParentescoeliminarpthisParentescoInstance() == null || this.getParentescoeliminarpthisParentescoInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_ELIMINAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_ELIMINAR_PARENTESCOELIMINARPTHISPARENTESCO, ParentescoConstants.SERV_ALIAS_ELIMINAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_ELIMINAR_PARENTESCOELIMINARPTHISPARENTESCO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
