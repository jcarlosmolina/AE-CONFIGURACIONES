package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class TMODIFICARRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccionTMODIFICARpthisSeccion;
    
    @JsonIgnore
    private Seccion seccionTMODIFICARpthisSeccionInstance;

    @JsonProperty("pt_p_nombre")
    private String seccionTMODIFICARptpNombre;

    @JsonProperty("pt_p_numorden")
    private Long seccionTMODIFICARptpNumOrden;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionTMODIFICARpthisSeccion();
        checkSeccionTMODIFICARptpNombre();
        checkSeccionTMODIFICARptpNumOrden();
    }

    @JsonIgnore
    public SeccionOid getSeccionTMODIFICARpthisSeccion() {
        return this.seccionTMODIFICARpthisSeccion;
    }
    
    public void setSeccionTMODIFICARpthisSeccion(SeccionOid seccionTMODIFICARpthisSeccion) {
        this.seccionTMODIFICARpthisSeccion = seccionTMODIFICARpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccionTMODIFICARpthisSeccionInstance() {
        return this.seccionTMODIFICARpthisSeccionInstance;
    }
    
    public void setSeccionTMODIFICARpthisSeccionInstance(Seccion seccionTMODIFICARpthisSeccion) {
        this.seccionTMODIFICARpthisSeccionInstance = seccionTMODIFICARpthisSeccion;
        this.seccionTMODIFICARpthisSeccion = seccionTMODIFICARpthisSeccion != null ? seccionTMODIFICARpthisSeccion.getOid() : null;
    }

    private void checkSeccionTMODIFICARpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccionTMODIFICARpthisSeccionInstance() == null || this.getSeccionTMODIFICARpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TMODIFICAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TMODIFICAR_SECCIONTMODIFICARPTHISSECCION, SeccionConstants.SERV_ALIAS_TMODIFICAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TMODIFICAR_SECCIONTMODIFICARPTHISSECCION);
        }
    }

    @JsonIgnore
    public String getSeccionTMODIFICARptpNombre() {
        return this.seccionTMODIFICARptpNombre;
    }
    
    public void setSeccionTMODIFICARptpNombre(String seccionTMODIFICARptpNombre) {
        this.seccionTMODIFICARptpNombre = seccionTMODIFICARptpNombre;
    }

    private void checkSeccionTMODIFICARptpNombre() throws NotNullArgumentException {
        if(this.getSeccionTMODIFICARptpNombre() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TMODIFICAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TMODIFICAR_SECCIONTMODIFICARPTPNOMBRE, SeccionConstants.SERV_ALIAS_TMODIFICAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TMODIFICAR_SECCIONTMODIFICARPTPNOMBRE);
        }
    }

    @JsonIgnore
    public Long getSeccionTMODIFICARptpNumOrden() {
        return this.seccionTMODIFICARptpNumOrden;
    }
    
    public void setSeccionTMODIFICARptpNumOrden(Long seccionTMODIFICARptpNumOrden) {
        this.seccionTMODIFICARptpNumOrden = seccionTMODIFICARptpNumOrden;
    }

    private void checkSeccionTMODIFICARptpNumOrden() throws NotNullArgumentException {
        if(this.getSeccionTMODIFICARptpNumOrden() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TMODIFICAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TMODIFICAR_SECCIONTMODIFICARPTPNUMORDEN, SeccionConstants.SERV_ALIAS_TMODIFICAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TMODIFICAR_SECCIONTMODIFICARPTPNUMORDEN);
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
