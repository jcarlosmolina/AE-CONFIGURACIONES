package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;

public class CambiarOrdenRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntacambiarOrdenpthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntacambiarOrdenpthisPreguntaInstance;

    @JsonProperty("p_nuevoorden")
    private Long preguntacambiarOrdenpNuevoOrden;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntacambiarOrdenpthisPregunta();
        checkPreguntacambiarOrdenpNuevoOrden();
    }

    @JsonIgnore
    public PreguntaOid getPreguntacambiarOrdenpthisPregunta() {
        return this.preguntacambiarOrdenpthisPregunta;
    }
    
    public void setPreguntacambiarOrdenpthisPregunta(PreguntaOid preguntacambiarOrdenpthisPregunta) {
        this.preguntacambiarOrdenpthisPregunta = preguntacambiarOrdenpthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntacambiarOrdenpthisPreguntaInstance() {
        return this.preguntacambiarOrdenpthisPreguntaInstance;
    }
    
    public void setPreguntacambiarOrdenpthisPreguntaInstance(Pregunta preguntacambiarOrdenpthisPregunta) {
        this.preguntacambiarOrdenpthisPreguntaInstance = preguntacambiarOrdenpthisPregunta;
        this.preguntacambiarOrdenpthisPregunta = preguntacambiarOrdenpthisPregunta != null ? preguntacambiarOrdenpthisPregunta.getOid() : null;
    }

    private void checkPreguntacambiarOrdenpthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntacambiarOrdenpthisPreguntaInstance() == null || this.getPreguntacambiarOrdenpthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CAMBIARORDEN, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CAMBIARORDEN_PREGUNTACAMBIARORDENPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_CAMBIARORDEN, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CAMBIARORDEN_PREGUNTACAMBIARORDENPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public Long getPreguntacambiarOrdenpNuevoOrden() {
        return this.preguntacambiarOrdenpNuevoOrden;
    }
    
    public void setPreguntacambiarOrdenpNuevoOrden(Long preguntacambiarOrdenpNuevoOrden) {
        this.preguntacambiarOrdenpNuevoOrden = preguntacambiarOrdenpNuevoOrden;
    }

    private void checkPreguntacambiarOrdenpNuevoOrden() throws NotNullArgumentException {
        if(this.getPreguntacambiarOrdenpNuevoOrden() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CAMBIARORDEN, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CAMBIARORDEN_PREGUNTACAMBIARORDENPNUEVOORDEN, PreguntaConstants.SERV_ALIAS_CAMBIARORDEN, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CAMBIARORDEN_PREGUNTACAMBIARORDENPNUEVOORDEN);
        }
    }
}
