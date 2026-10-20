package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class TELIMINARRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccionTELIMINARpthisSeccion;
    
    @JsonIgnore
    private Seccion seccionTELIMINARpthisSeccionInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionTELIMINARpthisSeccion();
    }

    @JsonIgnore
    public SeccionOid getSeccionTELIMINARpthisSeccion() {
        return this.seccionTELIMINARpthisSeccion;
    }
    
    public void setSeccionTELIMINARpthisSeccion(SeccionOid seccionTELIMINARpthisSeccion) {
        this.seccionTELIMINARpthisSeccion = seccionTELIMINARpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccionTELIMINARpthisSeccionInstance() {
        return this.seccionTELIMINARpthisSeccionInstance;
    }
    
    public void setSeccionTELIMINARpthisSeccionInstance(Seccion seccionTELIMINARpthisSeccion) {
        this.seccionTELIMINARpthisSeccionInstance = seccionTELIMINARpthisSeccion;
        this.seccionTELIMINARpthisSeccion = seccionTELIMINARpthisSeccion != null ? seccionTELIMINARpthisSeccion.getOid() : null;
    }

    private void checkSeccionTELIMINARpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccionTELIMINARpthisSeccionInstance() == null || this.getSeccionTELIMINARpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TELIMINAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TELIMINAR_SECCIONTELIMINARPTHISSECCION, SeccionConstants.SERV_ALIAS_TELIMINAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TELIMINAR_SECCIONTELIMINARPTHISSECCION);
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
