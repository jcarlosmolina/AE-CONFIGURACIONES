package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class InsValorPreguntaRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaInsValorPreguntapthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaInsValorPreguntapthisPreguntaInstance;

    @JsonProperty("p_evcvalor_oid")
    private ValorOid preguntaInsValorPreguntapevcValor;
    
    @JsonIgnore
    private Valor preguntaInsValorPreguntapevcValorInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaInsValorPreguntapthisPregunta();
        checkPreguntaInsValorPreguntapevcValor();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaInsValorPreguntapthisPregunta() {
        return this.preguntaInsValorPreguntapthisPregunta;
    }
    
    public void setPreguntaInsValorPreguntapthisPregunta(PreguntaOid preguntaInsValorPreguntapthisPregunta) {
        this.preguntaInsValorPreguntapthisPregunta = preguntaInsValorPreguntapthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaInsValorPreguntapthisPreguntaInstance() {
        return this.preguntaInsValorPreguntapthisPreguntaInstance;
    }
    
    public void setPreguntaInsValorPreguntapthisPreguntaInstance(Pregunta preguntaInsValorPreguntapthisPregunta) {
        this.preguntaInsValorPreguntapthisPreguntaInstance = preguntaInsValorPreguntapthisPregunta;
        this.preguntaInsValorPreguntapthisPregunta = preguntaInsValorPreguntapthisPregunta != null ? preguntaInsValorPreguntapthisPregunta.getOid() : null;
    }

    private void checkPreguntaInsValorPreguntapthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaInsValorPreguntapthisPreguntaInstance() == null || this.getPreguntaInsValorPreguntapthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_INSVALORPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_INSVALORPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public ValorOid getPreguntaInsValorPreguntapevcValor() {
        return this.preguntaInsValorPreguntapevcValor;
    }
    
    public void setPreguntaInsValorPreguntapevcValor(ValorOid preguntaInsValorPreguntapevcValor) {
        this.preguntaInsValorPreguntapevcValor = preguntaInsValorPreguntapevcValor;
    }

    @JsonIgnore
    public Valor getPreguntaInsValorPreguntapevcValorInstance() {
        return this.preguntaInsValorPreguntapevcValorInstance;
    }
    
    public void setPreguntaInsValorPreguntapevcValorInstance(Valor preguntaInsValorPreguntapevcValor) {
        this.preguntaInsValorPreguntapevcValorInstance = preguntaInsValorPreguntapevcValor;
        this.preguntaInsValorPreguntapevcValor = preguntaInsValorPreguntapevcValor != null ? preguntaInsValorPreguntapevcValor.getOid() : null;
    }

    private void checkPreguntaInsValorPreguntapevcValor() throws NotNullArgumentException {
        if(this.getPreguntaInsValorPreguntapevcValorInstance() == null || this.getPreguntaInsValorPreguntapevcValorInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_INSVALORPREGUNTA, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPEVCVALOR, PreguntaConstants.SERV_ALIAS_INSVALORPREGUNTA, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_INSVALORPREGUNTA_PREGUNTAINSVALORPREGUNTAPEVCVALOR);
        }
    }
}
