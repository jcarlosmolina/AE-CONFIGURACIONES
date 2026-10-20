package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class CambiarOrdenRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccioncambiarOrdenpthisSeccion;
    
    @JsonIgnore
    private Seccion seccioncambiarOrdenpthisSeccionInstance;

    @JsonProperty("p_nuevoorden")
    private Long seccioncambiarOrdenpNuevoOrden;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccioncambiarOrdenpthisSeccion();
        checkSeccioncambiarOrdenpNuevoOrden();
    }

    @JsonIgnore
    public SeccionOid getSeccioncambiarOrdenpthisSeccion() {
        return this.seccioncambiarOrdenpthisSeccion;
    }
    
    public void setSeccioncambiarOrdenpthisSeccion(SeccionOid seccioncambiarOrdenpthisSeccion) {
        this.seccioncambiarOrdenpthisSeccion = seccioncambiarOrdenpthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccioncambiarOrdenpthisSeccionInstance() {
        return this.seccioncambiarOrdenpthisSeccionInstance;
    }
    
    public void setSeccioncambiarOrdenpthisSeccionInstance(Seccion seccioncambiarOrdenpthisSeccion) {
        this.seccioncambiarOrdenpthisSeccionInstance = seccioncambiarOrdenpthisSeccion;
        this.seccioncambiarOrdenpthisSeccion = seccioncambiarOrdenpthisSeccion != null ? seccioncambiarOrdenpthisSeccion.getOid() : null;
    }

    private void checkSeccioncambiarOrdenpthisSeccion() throws NotNullArgumentException {
        if(this.getSeccioncambiarOrdenpthisSeccionInstance() == null || this.getSeccioncambiarOrdenpthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CAMBIARORDEN, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CAMBIARORDEN_SECCIONCAMBIARORDENPTHISSECCION, SeccionConstants.SERV_ALIAS_CAMBIARORDEN, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CAMBIARORDEN_SECCIONCAMBIARORDENPTHISSECCION);
        }
    }

    @JsonIgnore
    public Long getSeccioncambiarOrdenpNuevoOrden() {
        return this.seccioncambiarOrdenpNuevoOrden;
    }
    
    public void setSeccioncambiarOrdenpNuevoOrden(Long seccioncambiarOrdenpNuevoOrden) {
        this.seccioncambiarOrdenpNuevoOrden = seccioncambiarOrdenpNuevoOrden;
    }

    private void checkSeccioncambiarOrdenpNuevoOrden() throws NotNullArgumentException {
        if(this.getSeccioncambiarOrdenpNuevoOrden() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CAMBIARORDEN, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CAMBIARORDEN_SECCIONCAMBIARORDENPNUEVOORDEN, SeccionConstants.SERV_ALIAS_CAMBIARORDEN, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CAMBIARORDEN_SECCIONCAMBIARORDENPNUEVOORDEN);
        }
    }
}
