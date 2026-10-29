package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaPOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.PreguntaP;

public class InsPreguntaAPersonaRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaInsPreguntaAPersonapthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaInsPreguntaAPersonapthisPreguntaInstance;

    @JsonProperty("p_evcpreguntap_oid")
    private PreguntaPOid preguntaInsPreguntaAPersonapevcPreguntaP;
    
    @JsonIgnore
    private PreguntaP preguntaInsPreguntaAPersonapevcPreguntaPInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaInsPreguntaAPersonapthisPregunta();
        checkPreguntaInsPreguntaAPersonapevcPreguntaP();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaInsPreguntaAPersonapthisPregunta() {
        return this.preguntaInsPreguntaAPersonapthisPregunta;
    }
    
    public void setPreguntaInsPreguntaAPersonapthisPregunta(PreguntaOid preguntaInsPreguntaAPersonapthisPregunta) {
        this.preguntaInsPreguntaAPersonapthisPregunta = preguntaInsPreguntaAPersonapthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaInsPreguntaAPersonapthisPreguntaInstance() {
        return this.preguntaInsPreguntaAPersonapthisPreguntaInstance;
    }
    
    public void setPreguntaInsPreguntaAPersonapthisPreguntaInstance(Pregunta preguntaInsPreguntaAPersonapthisPregunta) {
        this.preguntaInsPreguntaAPersonapthisPreguntaInstance = preguntaInsPreguntaAPersonapthisPregunta;
        this.preguntaInsPreguntaAPersonapthisPregunta = preguntaInsPreguntaAPersonapthisPregunta != null ? preguntaInsPreguntaAPersonapthisPregunta.getOid() : null;
    }

    private void checkPreguntaInsPreguntaAPersonapthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaInsPreguntaAPersonapthisPreguntaInstance() == null || this.getPreguntaInsPreguntaAPersonapthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_INSPREGUNTAAPERSONA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_INSPREGUNTAAPERSONA_PREGUNTAINSPREGUNTAAPERSONAPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_INSPREGUNTAAPERSONA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_INSPREGUNTAAPERSONA_PREGUNTAINSPREGUNTAAPERSONAPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public PreguntaPOid getPreguntaInsPreguntaAPersonapevcPreguntaP() {
        return this.preguntaInsPreguntaAPersonapevcPreguntaP;
    }
    
    public void setPreguntaInsPreguntaAPersonapevcPreguntaP(PreguntaPOid preguntaInsPreguntaAPersonapevcPreguntaP) {
        this.preguntaInsPreguntaAPersonapevcPreguntaP = preguntaInsPreguntaAPersonapevcPreguntaP;
    }

    @JsonIgnore
    public PreguntaP getPreguntaInsPreguntaAPersonapevcPreguntaPInstance() {
        return this.preguntaInsPreguntaAPersonapevcPreguntaPInstance;
    }
    
    public void setPreguntaInsPreguntaAPersonapevcPreguntaPInstance(PreguntaP preguntaInsPreguntaAPersonapevcPreguntaP) {
        this.preguntaInsPreguntaAPersonapevcPreguntaPInstance = preguntaInsPreguntaAPersonapevcPreguntaP;
        this.preguntaInsPreguntaAPersonapevcPreguntaP = preguntaInsPreguntaAPersonapevcPreguntaP != null ? preguntaInsPreguntaAPersonapevcPreguntaP.getOid() : null;
    }

    private void checkPreguntaInsPreguntaAPersonapevcPreguntaP() throws NotNullArgumentException {
        if(this.getPreguntaInsPreguntaAPersonapevcPreguntaPInstance() == null || this.getPreguntaInsPreguntaAPersonapevcPreguntaPInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_INSPREGUNTAAPERSONA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_INSPREGUNTAAPERSONA_PREGUNTAINSPREGUNTAAPERSONAPEVCPREGUNTAP, PreguntaConstants.SERV_ALIAS_INSPREGUNTAAPERSONA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_INSPREGUNTAAPERSONA_PREGUNTAINSPREGUNTAAPERSONAPEVCPREGUNTAP);
        }
    }
}
