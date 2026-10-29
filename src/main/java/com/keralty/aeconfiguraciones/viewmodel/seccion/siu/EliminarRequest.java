package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class EliminarRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccioneliminarpthisSeccion;
    
    @JsonIgnore
    private Seccion seccioneliminarpthisSeccionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccioneliminarpthisSeccion();
    }

    @JsonIgnore
    public SeccionOid getSeccioneliminarpthisSeccion() {
        return this.seccioneliminarpthisSeccion;
    }
    
    public void setSeccioneliminarpthisSeccion(SeccionOid seccioneliminarpthisSeccion) {
        this.seccioneliminarpthisSeccion = seccioneliminarpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccioneliminarpthisSeccionInstance() {
        return this.seccioneliminarpthisSeccionInstance;
    }
    
    public void setSeccioneliminarpthisSeccionInstance(Seccion seccioneliminarpthisSeccion) {
        this.seccioneliminarpthisSeccionInstance = seccioneliminarpthisSeccion;
        this.seccioneliminarpthisSeccion = seccioneliminarpthisSeccion != null ? seccioneliminarpthisSeccion.getOid() : null;
    }

    private void checkSeccioneliminarpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccioneliminarpthisSeccionInstance() == null || this.getSeccioneliminarpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_ELIMINAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_ELIMINAR_SECCIONELIMINARPTHISSECCION, SeccionConstants.SERV_ALIAS_ELIMINAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_ELIMINAR_SECCIONELIMINARPTHISSECCION);
        }
    }
}
