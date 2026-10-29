package com.keralty.aeconfiguraciones.viewmodel.valor.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ValorConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.oid.ValorOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Valor;

public class DelValorPreguntaRequest {

    @JsonProperty("p_thisvalor_oid")
    private ValorOid valorDelValorPreguntapthisValor;
    
    @JsonIgnore
    private Valor valorDelValorPreguntapthisValorInstance;

    @JsonProperty("p_evcpregunta_oid")
    private PreguntaOid valorDelValorPreguntapevcPregunta;
    
    @JsonIgnore
    private Pregunta valorDelValorPreguntapevcPreguntaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkValorDelValorPreguntapthisValor();
        checkValorDelValorPreguntapevcPregunta();
    }

    @JsonIgnore
    public ValorOid getValorDelValorPreguntapthisValor() {
        return this.valorDelValorPreguntapthisValor;
    }
    
    public void setValorDelValorPreguntapthisValor(ValorOid valorDelValorPreguntapthisValor) {
        this.valorDelValorPreguntapthisValor = valorDelValorPreguntapthisValor;
    }

    @JsonIgnore
    public Valor getValorDelValorPreguntapthisValorInstance() {
        return this.valorDelValorPreguntapthisValorInstance;
    }
    
    public void setValorDelValorPreguntapthisValorInstance(Valor valorDelValorPreguntapthisValor) {
        this.valorDelValorPreguntapthisValorInstance = valorDelValorPreguntapthisValor;
        this.valorDelValorPreguntapthisValor = valorDelValorPreguntapthisValor != null ? valorDelValorPreguntapthisValor.getOid() : null;
    }

    private void checkValorDelValorPreguntapthisValor() throws NotNullArgumentException {
        if(this.getValorDelValorPreguntapthisValorInstance() == null || this.getValorDelValorPreguntapthisValorInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_DELVALORPREGUNTA, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_DELVALORPREGUNTA_VALORDELVALORPREGUNTAPTHISVALOR, ValorConstants.SERV_ALIAS_DELVALORPREGUNTA, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_DELVALORPREGUNTA_VALORDELVALORPREGUNTAPTHISVALOR);
        }
    }

    @JsonIgnore
    public PreguntaOid getValorDelValorPreguntapevcPregunta() {
        return this.valorDelValorPreguntapevcPregunta;
    }
    
    public void setValorDelValorPreguntapevcPregunta(PreguntaOid valorDelValorPreguntapevcPregunta) {
        this.valorDelValorPreguntapevcPregunta = valorDelValorPreguntapevcPregunta;
    }

    @JsonIgnore
    public Pregunta getValorDelValorPreguntapevcPreguntaInstance() {
        return this.valorDelValorPreguntapevcPreguntaInstance;
    }
    
    public void setValorDelValorPreguntapevcPreguntaInstance(Pregunta valorDelValorPreguntapevcPregunta) {
        this.valorDelValorPreguntapevcPreguntaInstance = valorDelValorPreguntapevcPregunta;
        this.valorDelValorPreguntapevcPregunta = valorDelValorPreguntapevcPregunta != null ? valorDelValorPreguntapevcPregunta.getOid() : null;
    }

    private void checkValorDelValorPreguntapevcPregunta() throws NotNullArgumentException {
        if(this.getValorDelValorPreguntapevcPreguntaInstance() == null || this.getValorDelValorPreguntapevcPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(ValorConstants.SERV_ID_DELVALORPREGUNTA, ValorConstants.CLASS_ID, ValorConstants.ARG_ID_DELVALORPREGUNTA_VALORDELVALORPREGUNTAPEVCPREGUNTA, ValorConstants.SERV_ALIAS_DELVALORPREGUNTA, ValorConstants.CLASS_ALIAS, ValorConstants.ARG_ALIAS_DELVALORPREGUNTA_VALORDELVALORPREGUNTAPEVCPREGUNTA);
        }
    }
}
