package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class CambSeccionPreguntaRequest {

    @JsonProperty("p_thisseccion_oid")
    private SeccionOid seccionCambSeccionPreguntapthisSeccion;
    
    @JsonIgnore
    private Seccion seccionCambSeccionPreguntapthisSeccionInstance;

    @JsonProperty("p_evcpregunta_oid")
    private PreguntaOid seccionCambSeccionPreguntapevcPregunta;
    
    @JsonIgnore
    private Pregunta seccionCambSeccionPreguntapevcPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionCambSeccionPreguntapthisSeccion();
        checkSeccionCambSeccionPreguntapevcPregunta();
    }

    @JsonIgnore
    public SeccionOid getSeccionCambSeccionPreguntapthisSeccion() {
        return this.seccionCambSeccionPreguntapthisSeccion;
    }
    
    public void setSeccionCambSeccionPreguntapthisSeccion(SeccionOid seccionCambSeccionPreguntapthisSeccion) {
        this.seccionCambSeccionPreguntapthisSeccion = seccionCambSeccionPreguntapthisSeccion;
    }

    @JsonIgnore
    public Seccion getSeccionCambSeccionPreguntapthisSeccionInstance() {
        return this.seccionCambSeccionPreguntapthisSeccionInstance;
    }
    
    public void setSeccionCambSeccionPreguntapthisSeccionInstance(Seccion seccionCambSeccionPreguntapthisSeccion) {
        this.seccionCambSeccionPreguntapthisSeccionInstance = seccionCambSeccionPreguntapthisSeccion;
        this.seccionCambSeccionPreguntapthisSeccion = seccionCambSeccionPreguntapthisSeccion != null ? seccionCambSeccionPreguntapthisSeccion.getOid() : null;
    }

    private void checkSeccionCambSeccionPreguntapthisSeccion() throws NotNullArgumentException {
        if(this.getSeccionCambSeccionPreguntapthisSeccionInstance() == null || this.getSeccionCambSeccionPreguntapthisSeccionInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CAMBSECCIONPREGUNTA, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CAMBSECCIONPREGUNTA_SECCIONCAMBSECCIONPREGUNTAPTHISSECCION, SeccionConstants.SERV_ALIAS_CAMBSECCIONPREGUNTA, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CAMBSECCIONPREGUNTA_SECCIONCAMBSECCIONPREGUNTAPTHISSECCION);
        }
    }

    @JsonIgnore
    public PreguntaOid getSeccionCambSeccionPreguntapevcPregunta() {
        return this.seccionCambSeccionPreguntapevcPregunta;
    }
    
    public void setSeccionCambSeccionPreguntapevcPregunta(PreguntaOid seccionCambSeccionPreguntapevcPregunta) {
        this.seccionCambSeccionPreguntapevcPregunta = seccionCambSeccionPreguntapevcPregunta;
    }

    @JsonIgnore
    public Pregunta getSeccionCambSeccionPreguntapevcPreguntaInstance() {
        return this.seccionCambSeccionPreguntapevcPreguntaInstance;
    }
    
    public void setSeccionCambSeccionPreguntapevcPreguntaInstance(Pregunta seccionCambSeccionPreguntapevcPregunta) {
        this.seccionCambSeccionPreguntapevcPreguntaInstance = seccionCambSeccionPreguntapevcPregunta;
        this.seccionCambSeccionPreguntapevcPregunta = seccionCambSeccionPreguntapevcPregunta != null ? seccionCambSeccionPreguntapevcPregunta.getOid() : null;
    }

    private void checkSeccionCambSeccionPreguntapevcPregunta() throws NotNullArgumentException {
        if(this.getSeccionCambSeccionPreguntapevcPreguntaInstance() == null || this.getSeccionCambSeccionPreguntapevcPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CAMBSECCIONPREGUNTA, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CAMBSECCIONPREGUNTA_SECCIONCAMBSECCIONPREGUNTAPEVCPREGUNTA, SeccionConstants.SERV_ALIAS_CAMBSECCIONPREGUNTA, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CAMBSECCIONPREGUNTA_SECCIONCAMBSECCIONPREGUNTAPEVCPREGUNTA);
        }
    }
}
