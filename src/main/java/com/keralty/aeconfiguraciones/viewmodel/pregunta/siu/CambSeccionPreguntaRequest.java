package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class CambSeccionPreguntaRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaCambSeccionPreguntapthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaCambSeccionPreguntapthisPreguntaInstance;

    @JsonProperty("p_evcseccion_oid")
    private SeccionOid preguntaCambSeccionPreguntapevcSeccion;
    
    @JsonIgnore
    private Seccion preguntaCambSeccionPreguntapevcSeccionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaCambSeccionPreguntapthisPregunta();
        checkPreguntaCambSeccionPreguntapevcSeccion();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaCambSeccionPreguntapthisPregunta() {
        return this.preguntaCambSeccionPreguntapthisPregunta;
    }
    
    public void setPreguntaCambSeccionPreguntapthisPregunta(PreguntaOid preguntaCambSeccionPreguntapthisPregunta) {
        this.preguntaCambSeccionPreguntapthisPregunta = preguntaCambSeccionPreguntapthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaCambSeccionPreguntapthisPreguntaInstance() {
        return this.preguntaCambSeccionPreguntapthisPreguntaInstance;
    }
    
    public void setPreguntaCambSeccionPreguntapthisPreguntaInstance(Pregunta preguntaCambSeccionPreguntapthisPregunta) {
        this.preguntaCambSeccionPreguntapthisPreguntaInstance = preguntaCambSeccionPreguntapthisPregunta;
        this.preguntaCambSeccionPreguntapthisPregunta = preguntaCambSeccionPreguntapthisPregunta != null ? preguntaCambSeccionPreguntapthisPregunta.getOid() : null;
    }

    private void checkPreguntaCambSeccionPreguntapthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaCambSeccionPreguntapthisPreguntaInstance() == null || this.getPreguntaCambSeccionPreguntapthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CAMBSECCIONPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CAMBSECCIONPREGUNTA_PREGUNTACAMBSECCIONPREGUNTAPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_CAMBSECCIONPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CAMBSECCIONPREGUNTA_PREGUNTACAMBSECCIONPREGUNTAPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public SeccionOid getPreguntaCambSeccionPreguntapevcSeccion() {
        return this.preguntaCambSeccionPreguntapevcSeccion;
    }
    
    public void setPreguntaCambSeccionPreguntapevcSeccion(SeccionOid preguntaCambSeccionPreguntapevcSeccion) {
        this.preguntaCambSeccionPreguntapevcSeccion = preguntaCambSeccionPreguntapevcSeccion;
    }

    @JsonIgnore
    public Seccion getPreguntaCambSeccionPreguntapevcSeccionInstance() {
        return this.preguntaCambSeccionPreguntapevcSeccionInstance;
    }
    
    public void setPreguntaCambSeccionPreguntapevcSeccionInstance(Seccion preguntaCambSeccionPreguntapevcSeccion) {
        this.preguntaCambSeccionPreguntapevcSeccionInstance = preguntaCambSeccionPreguntapevcSeccion;
        this.preguntaCambSeccionPreguntapevcSeccion = preguntaCambSeccionPreguntapevcSeccion != null ? preguntaCambSeccionPreguntapevcSeccion.getOid() : null;
    }

    private void checkPreguntaCambSeccionPreguntapevcSeccion() throws NotNullArgumentException {
        if(this.getPreguntaCambSeccionPreguntapevcSeccionInstance() == null || this.getPreguntaCambSeccionPreguntapevcSeccionInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CAMBSECCIONPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CAMBSECCIONPREGUNTA_PREGUNTACAMBSECCIONPREGUNTAPEVCSECCION, PreguntaConstants.SERV_ALIAS_CAMBSECCIONPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CAMBSECCIONPREGUNTA_PREGUNTACAMBSECCIONPREGUNTAPEVCSECCION);
        }
    }
}
