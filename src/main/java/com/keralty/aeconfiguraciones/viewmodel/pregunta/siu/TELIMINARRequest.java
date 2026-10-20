package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;

public class TELIMINARRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaTELIMINARpthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaTELIMINARpthisPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaTELIMINARpthisPregunta();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaTELIMINARpthisPregunta() {
        return this.preguntaTELIMINARpthisPregunta;
    }
    
    public void setPreguntaTELIMINARpthisPregunta(PreguntaOid preguntaTELIMINARpthisPregunta) {
        this.preguntaTELIMINARpthisPregunta = preguntaTELIMINARpthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaTELIMINARpthisPreguntaInstance() {
        return this.preguntaTELIMINARpthisPreguntaInstance;
    }
    
    public void setPreguntaTELIMINARpthisPreguntaInstance(Pregunta preguntaTELIMINARpthisPregunta) {
        this.preguntaTELIMINARpthisPreguntaInstance = preguntaTELIMINARpthisPregunta;
        this.preguntaTELIMINARpthisPregunta = preguntaTELIMINARpthisPregunta != null ? preguntaTELIMINARpthisPregunta.getOid() : null;
    }

    private void checkPreguntaTELIMINARpthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaTELIMINARpthisPreguntaInstance() == null || this.getPreguntaTELIMINARpthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TELIMINAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TELIMINAR_PREGUNTATELIMINARPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_TELIMINAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TELIMINAR_PREGUNTATELIMINARPTHISPREGUNTA);
        }
    }
}
