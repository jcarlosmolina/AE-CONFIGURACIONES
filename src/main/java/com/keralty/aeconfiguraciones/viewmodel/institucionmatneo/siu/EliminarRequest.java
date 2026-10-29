package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;

public class EliminarRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeoeliminarpthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeoeliminarpthisInstitucionMatNeoInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeoeliminarpthisInstitucionMatNeo();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeoeliminarpthisInstitucionMatNeo() {
        return this.institucionMatNeoeliminarpthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeoeliminarpthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeoeliminarpthisInstitucionMatNeo) {
        this.institucionMatNeoeliminarpthisInstitucionMatNeo = institucionMatNeoeliminarpthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance() {
        return this.institucionMatNeoeliminarpthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeoeliminarpthisInstitucionMatNeo) {
        this.institucionMatNeoeliminarpthisInstitucionMatNeoInstance = institucionMatNeoeliminarpthisInstitucionMatNeo;
        this.institucionMatNeoeliminarpthisInstitucionMatNeo = institucionMatNeoeliminarpthisInstitucionMatNeo != null ? institucionMatNeoeliminarpthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeoeliminarpthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_ELIMINAR, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_ELIMINAR_INSTITUCIONMATNEOELIMINARPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_ELIMINAR, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_ELIMINAR_INSTITUCIONMATNEOELIMINARPTHISINSTITUCIONMATNEO);
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
