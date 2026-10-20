package com.keralty.aeconfiguraciones.viewmodel.valor.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class InsValorPreguntaRequest {

    @JsonProperty("p_thisvalor_oid")
    private ValorOid valorInsValorPreguntapthisValor;
    
    @JsonIgnore
    private Valor valorInsValorPreguntapthisValorInstance;

    @JsonProperty("p_evcpregunta_oid")
    private PreguntaOid valorInsValorPreguntapevcPregunta;
    
    @JsonIgnore
    private Pregunta valorInsValorPreguntapevcPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkValorInsValorPreguntapthisValor();
        checkValorInsValorPreguntapevcPregunta();
    }

    @JsonIgnore
    public ValorOid getValorInsValorPreguntapthisValor() {
        return this.valorInsValorPreguntapthisValor;
    }
    
    public void setValorInsValorPreguntapthisValor(ValorOid valorInsValorPreguntapthisValor) {
        this.valorInsValorPreguntapthisValor = valorInsValorPreguntapthisValor;
    }

    @JsonIgnore
    public Valor getValorInsValorPreguntapthisValorInstance() {
        return this.valorInsValorPreguntapthisValorInstance;
    }
    
    public void setValorInsValorPreguntapthisValorInstance(Valor valorInsValorPreguntapthisValor) {
        this.valorInsValorPreguntapthisValorInstance = valorInsValorPreguntapthisValor;
        this.valorInsValorPreguntapthisValor = valorInsValorPreguntapthisValor != null ? valorInsValorPreguntapthisValor.getOid() : null;
    }

    private void checkValorInsValorPreguntapthisValor() throws NotNullArgumentException {
        if(this.getValorInsValorPreguntapthisValorInstance() == null || this.getValorInsValorPreguntapthisValorInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_INSVALORPREGUNTA, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_INSVALORPREGUNTA_VALORINSVALORPREGUNTAPTHISVALOR, ValorConstants.SERV_ALIAS_INSVALORPREGUNTA, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_INSVALORPREGUNTA_VALORINSVALORPREGUNTAPTHISVALOR);
        }
    }

    @JsonIgnore
    public PreguntaOid getValorInsValorPreguntapevcPregunta() {
        return this.valorInsValorPreguntapevcPregunta;
    }
    
    public void setValorInsValorPreguntapevcPregunta(PreguntaOid valorInsValorPreguntapevcPregunta) {
        this.valorInsValorPreguntapevcPregunta = valorInsValorPreguntapevcPregunta;
    }

    @JsonIgnore
    public Pregunta getValorInsValorPreguntapevcPreguntaInstance() {
        return this.valorInsValorPreguntapevcPreguntaInstance;
    }
    
    public void setValorInsValorPreguntapevcPreguntaInstance(Pregunta valorInsValorPreguntapevcPregunta) {
        this.valorInsValorPreguntapevcPreguntaInstance = valorInsValorPreguntapevcPregunta;
        this.valorInsValorPreguntapevcPregunta = valorInsValorPreguntapevcPregunta != null ? valorInsValorPreguntapevcPregunta.getOid() : null;
    }

    private void checkValorInsValorPreguntapevcPregunta() throws NotNullArgumentException {
        if(this.getValorInsValorPreguntapevcPreguntaInstance() == null || this.getValorInsValorPreguntapevcPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_INSVALORPREGUNTA, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_INSVALORPREGUNTA_VALORINSVALORPREGUNTAPEVCPREGUNTA, ValorConstants.SERV_ALIAS_INSVALORPREGUNTA, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_INSVALORPREGUNTA_VALORINSVALORPREGUNTAPEVCPREGUNTA);
        }
    }
}
