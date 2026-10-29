package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class DelValorPreguntaRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaDelValorPreguntapthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaDelValorPreguntapthisPreguntaInstance;

    @JsonProperty("p_evcvalor_json")
    private String preguntaDelValorPreguntapevcValor;
    
    @JsonIgnore
    private Valor preguntaDelValorPreguntapevcValorInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaDelValorPreguntapthisPregunta();
        checkPreguntaDelValorPreguntapevcValor();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaDelValorPreguntapthisPregunta() {
        return this.preguntaDelValorPreguntapthisPregunta;
    }
    
    public void setPreguntaDelValorPreguntapthisPregunta(PreguntaOid preguntaDelValorPreguntapthisPregunta) {
        this.preguntaDelValorPreguntapthisPregunta = preguntaDelValorPreguntapthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaDelValorPreguntapthisPreguntaInstance() {
        return this.preguntaDelValorPreguntapthisPreguntaInstance;
    }
    
    public void setPreguntaDelValorPreguntapthisPreguntaInstance(Pregunta preguntaDelValorPreguntapthisPregunta) {
        this.preguntaDelValorPreguntapthisPreguntaInstance = preguntaDelValorPreguntapthisPregunta;
        this.preguntaDelValorPreguntapthisPregunta = preguntaDelValorPreguntapthisPregunta != null ? preguntaDelValorPreguntapthisPregunta.getOid() : null;
    }

    private void checkPreguntaDelValorPreguntapthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaDelValorPreguntapthisPreguntaInstance() == null || this.getPreguntaDelValorPreguntapthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_DELVALORPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_DELVALORPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public String getPreguntaDelValorPreguntapevcValor() {
        return this.preguntaDelValorPreguntapevcValor;
    }
    
    public void setPreguntaDelValorPreguntapevcValor(String preguntaDelValorPreguntapevcValor) {
        this.preguntaDelValorPreguntapevcValor = preguntaDelValorPreguntapevcValor;
    }

    @JsonIgnore
    public Valor getPreguntaDelValorPreguntapevcValorInstance() {
        return this.preguntaDelValorPreguntapevcValorInstance;
    }
    
    public void setPreguntaDelValorPreguntapevcValorInstance(Valor preguntaDelValorPreguntapevcValor) {
        this.preguntaDelValorPreguntapevcValorInstance = preguntaDelValorPreguntapevcValor;
        this.preguntaDelValorPreguntapevcValor = preguntaDelValorPreguntapevcValor != null ? preguntaDelValorPreguntapevcValor.getOid().toJson() : "";
    }

    private void checkPreguntaDelValorPreguntapevcValor() throws NotNullArgumentException {
        if(this.getPreguntaDelValorPreguntapevcValorInstance() == null || this.getPreguntaDelValorPreguntapevcValorInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_DELVALORPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPEVCVALOR, PreguntaConstants.SERV_ALIAS_DELVALORPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_DELVALORPREGUNTA_PREGUNTADELVALORPREGUNTAPEVCVALOR);
        }
    }
}
