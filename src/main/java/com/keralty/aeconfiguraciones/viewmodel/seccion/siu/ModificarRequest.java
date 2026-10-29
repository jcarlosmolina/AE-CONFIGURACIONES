package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class ModificarRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccionmodificarpthisSeccion;
    
    @JsonIgnore
    private Seccion seccionmodificarpthisSeccionInstance;

    @JsonProperty("p_nombre")
    private String seccionmodificarpNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionmodificarpthisSeccion();
        checkSeccionmodificarpNombre();
    }

    @JsonIgnore
    public SeccionOid getSeccionmodificarpthisSeccion() {
        return this.seccionmodificarpthisSeccion;
    }
    
    public void setSeccionmodificarpthisSeccion(SeccionOid seccionmodificarpthisSeccion) {
        this.seccionmodificarpthisSeccion = seccionmodificarpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccionmodificarpthisSeccionInstance() {
        return this.seccionmodificarpthisSeccionInstance;
    }
    
    public void setSeccionmodificarpthisSeccionInstance(Seccion seccionmodificarpthisSeccion) {
        this.seccionmodificarpthisSeccionInstance = seccionmodificarpthisSeccion;
        this.seccionmodificarpthisSeccion = seccionmodificarpthisSeccion != null ? seccionmodificarpthisSeccion.getOid() : null;
    }

    private void checkSeccionmodificarpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccionmodificarpthisSeccionInstance() == null || this.getSeccionmodificarpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_MODIFICAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_MODIFICAR_SECCIONMODIFICARPTHISSECCION, SeccionConstants.SERV_ALIAS_MODIFICAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_MODIFICAR_SECCIONMODIFICARPTHISSECCION);
        }
    }

    @JsonIgnore
    public String getSeccionmodificarpNombre() {
        return this.seccionmodificarpNombre;
    }
    
    public void setSeccionmodificarpNombre(String seccionmodificarpNombre) {
        this.seccionmodificarpNombre = seccionmodificarpNombre;
    }

    private void checkSeccionmodificarpNombre() throws NotNullArgumentException {
        if(this.getSeccionmodificarpNombre() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_MODIFICAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_MODIFICAR_SECCIONMODIFICARPNOMBRE, SeccionConstants.SERV_ALIAS_MODIFICAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_MODIFICAR_SECCIONMODIFICARPNOMBRE);
        }
    }
}
