package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;

public class EliminarRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaeliminarpthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaeliminarpthisPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaeliminarpthisPregunta();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaeliminarpthisPregunta() {
        return this.preguntaeliminarpthisPregunta;
    }
    
    public void setPreguntaeliminarpthisPregunta(PreguntaOid preguntaeliminarpthisPregunta) {
        this.preguntaeliminarpthisPregunta = preguntaeliminarpthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaeliminarpthisPreguntaInstance() {
        return this.preguntaeliminarpthisPreguntaInstance;
    }
    
    public void setPreguntaeliminarpthisPreguntaInstance(Pregunta preguntaeliminarpthisPregunta) {
        this.preguntaeliminarpthisPreguntaInstance = preguntaeliminarpthisPregunta;
        this.preguntaeliminarpthisPregunta = preguntaeliminarpthisPregunta != null ? preguntaeliminarpthisPregunta.getOid() : null;
    }

    private void checkPreguntaeliminarpthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaeliminarpthisPreguntaInstance() == null || this.getPreguntaeliminarpthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_ELIMINAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_ELIMINAR_PREGUNTAELIMINARPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_ELIMINAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_ELIMINAR_PREGUNTAELIMINARPTHISPREGUNTA);
        }
    }
}
