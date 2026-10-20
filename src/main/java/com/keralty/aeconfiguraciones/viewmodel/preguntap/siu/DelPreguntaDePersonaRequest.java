package com.keralty.aeconfiguraciones.viewmodel.preguntap.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaPConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaPOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.PreguntaP;

public class DelPreguntaDePersonaRequest {

    @JsonProperty("p_thispreguntap_oid")
    private PreguntaPOid preguntaPDelPreguntaDePersonapthisPreguntaP;
    
    @JsonIgnore
    private PreguntaP preguntaPDelPreguntaDePersonapthisPreguntaPInstance;

    @JsonProperty("p_evcpregunta_oid")
    private PreguntaOid preguntaPDelPreguntaDePersonapevcPregunta;
    
    @JsonIgnore
    private Pregunta preguntaPDelPreguntaDePersonapevcPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaPDelPreguntaDePersonapthisPreguntaP();
        checkPreguntaPDelPreguntaDePersonapevcPregunta();
    }

    @JsonIgnore
    public PreguntaPOid getPreguntaPDelPreguntaDePersonapthisPreguntaP() {
        return this.preguntaPDelPreguntaDePersonapthisPreguntaP;
    }
    
    public void setPreguntaPDelPreguntaDePersonapthisPreguntaP(PreguntaPOid preguntaPDelPreguntaDePersonapthisPreguntaP) {
        this.preguntaPDelPreguntaDePersonapthisPreguntaP = preguntaPDelPreguntaDePersonapthisPreguntaP;
    }

    @JsonIgnore
    public PreguntaP getPreguntaPDelPreguntaDePersonapthisPreguntaPInstance() {
        return this.preguntaPDelPreguntaDePersonapthisPreguntaPInstance;
    }
    
    public void setPreguntaPDelPreguntaDePersonapthisPreguntaPInstance(PreguntaP preguntaPDelPreguntaDePersonapthisPreguntaP) {
        this.preguntaPDelPreguntaDePersonapthisPreguntaPInstance = preguntaPDelPreguntaDePersonapthisPreguntaP;
        this.preguntaPDelPreguntaDePersonapthisPreguntaP = preguntaPDelPreguntaDePersonapthisPreguntaP != null ? preguntaPDelPreguntaDePersonapthisPreguntaP.getOid() : null;
    }

    private void checkPreguntaPDelPreguntaDePersonapthisPreguntaP() throws NotNullArgumentException {
        if(this.getPreguntaPDelPreguntaDePersonapthisPreguntaPInstance() == null || this.getPreguntaPDelPreguntaDePersonapthisPreguntaPInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaPConstants.SERV_ID_DELPREGUNTADEPERSONA, PreguntaPConstants.CLASS_ID, PreguntaPConstants.ARG_ID_DELPREGUNTADEPERSONA_PREGUNTAPDELPREGUNTADEPERSONAPTHISPREGUNTAP, PreguntaPConstants.SERV_ALIAS_DELPREGUNTADEPERSONA, PreguntaPConstants.CLASS_ALIAS, PreguntaPConstants.ARG_ALIAS_DELPREGUNTADEPERSONA_PREGUNTAPDELPREGUNTADEPERSONAPTHISPREGUNTAP);
        }
    }

    @JsonIgnore
    public PreguntaOid getPreguntaPDelPreguntaDePersonapevcPregunta() {
        return this.preguntaPDelPreguntaDePersonapevcPregunta;
    }
    
    public void setPreguntaPDelPreguntaDePersonapevcPregunta(PreguntaOid preguntaPDelPreguntaDePersonapevcPregunta) {
        this.preguntaPDelPreguntaDePersonapevcPregunta = preguntaPDelPreguntaDePersonapevcPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaPDelPreguntaDePersonapevcPreguntaInstance() {
        return this.preguntaPDelPreguntaDePersonapevcPreguntaInstance;
    }
    
    public void setPreguntaPDelPreguntaDePersonapevcPreguntaInstance(Pregunta preguntaPDelPreguntaDePersonapevcPregunta) {
        this.preguntaPDelPreguntaDePersonapevcPreguntaInstance = preguntaPDelPreguntaDePersonapevcPregunta;
        this.preguntaPDelPreguntaDePersonapevcPregunta = preguntaPDelPreguntaDePersonapevcPregunta != null ? preguntaPDelPreguntaDePersonapevcPregunta.getOid() : null;
    }

    private void checkPreguntaPDelPreguntaDePersonapevcPregunta() throws NotNullArgumentException {
        if(this.getPreguntaPDelPreguntaDePersonapevcPreguntaInstance() == null || this.getPreguntaPDelPreguntaDePersonapevcPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaPConstants.SERV_ID_DELPREGUNTADEPERSONA, PreguntaPConstants.CLASS_ID, PreguntaPConstants.ARG_ID_DELPREGUNTADEPERSONA_PREGUNTAPDELPREGUNTADEPERSONAPEVCPREGUNTA, PreguntaPConstants.SERV_ALIAS_DELPREGUNTADEPERSONA, PreguntaPConstants.CLASS_ALIAS, PreguntaPConstants.ARG_ALIAS_DELPREGUNTADEPERSONA_PREGUNTAPDELPREGUNTADEPERSONAPEVCPREGUNTA);
        }
    }
}
