package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;

public class ModificarRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntamodificarpthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntamodificarpthisPreguntaInstance;

    @JsonProperty("p_descripcion")
    private String preguntamodificarpDescripcion;

    @JsonProperty("p_valorsnobliga")
    private Boolean preguntamodificarpValorSNObliga;

    @JsonProperty("p_obsoblig")
    private Boolean preguntamodificarpObsOblig;

    @JsonProperty("p_esnumero")
    private Boolean preguntamodificarpEsNumero;

    @JsonProperty("p_valormax")
    private Double preguntamodificarpValorMax;

    @JsonProperty("p_valormin")
    private Double preguntamodificarpValorMin;

    @JsonProperty("p_solohombres")
    private Boolean preguntamodificarpSoloHombres;

    @JsonProperty("p_solomujeres")
    private Boolean preguntamodificarpSoloMujeres;

    @JsonProperty("p_respmultiple")
    private Boolean preguntamodificarpRespMultiple;

    @JsonProperty("p_seenviaalcore")
    private Boolean preguntamodificarpSeEnviaAlCORE;

    @JsonProperty("p_condicionedad")
    private Long preguntamodificarpCondicionEdad;

    @JsonProperty("p_condicionedadanyos")
    private Long preguntamodificarpCondicionEdadAnyos;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntamodificarpthisPregunta();
        checkPreguntamodificarpDescripcion();
        checkPreguntamodificarpValorSNObliga();
        checkPreguntamodificarpObsOblig();
        checkPreguntamodificarpEsNumero();
        checkPreguntamodificarpSoloHombres();
        checkPreguntamodificarpSoloMujeres();
        checkPreguntamodificarpRespMultiple();
        checkPreguntamodificarpSeEnviaAlCORE();
        checkPreguntamodificarpCondicionEdad();
    }

    @JsonIgnore
    public PreguntaOid getPreguntamodificarpthisPregunta() {
        return this.preguntamodificarpthisPregunta;
    }
    
    public void setPreguntamodificarpthisPregunta(PreguntaOid preguntamodificarpthisPregunta) {
        this.preguntamodificarpthisPregunta = preguntamodificarpthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntamodificarpthisPreguntaInstance() {
        return this.preguntamodificarpthisPreguntaInstance;
    }
    
    public void setPreguntamodificarpthisPreguntaInstance(Pregunta preguntamodificarpthisPregunta) {
        this.preguntamodificarpthisPreguntaInstance = preguntamodificarpthisPregunta;
        this.preguntamodificarpthisPregunta = preguntamodificarpthisPregunta != null ? preguntamodificarpthisPregunta.getOid() : null;
    }

    private void checkPreguntamodificarpthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntamodificarpthisPreguntaInstance() == null || this.getPreguntamodificarpthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public String getPreguntamodificarpDescripcion() {
        return this.preguntamodificarpDescripcion;
    }
    
    public void setPreguntamodificarpDescripcion(String preguntamodificarpDescripcion) {
        this.preguntamodificarpDescripcion = preguntamodificarpDescripcion;
    }

    private void checkPreguntamodificarpDescripcion() throws NotNullArgumentException {
        if(this.getPreguntamodificarpDescripcion() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPDESCRIPCION, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpValorSNObliga() {
        return this.preguntamodificarpValorSNObliga;
    }
    
    public void setPreguntamodificarpValorSNObliga(Boolean preguntamodificarpValorSNObliga) {
        this.preguntamodificarpValorSNObliga = preguntamodificarpValorSNObliga;
    }

    private void checkPreguntamodificarpValorSNObliga() throws NotNullArgumentException {
        if(this.getPreguntamodificarpValorSNObliga() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPVALORSNOBLIGA, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPVALORSNOBLIGA);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpObsOblig() {
        return this.preguntamodificarpObsOblig;
    }
    
    public void setPreguntamodificarpObsOblig(Boolean preguntamodificarpObsOblig) {
        this.preguntamodificarpObsOblig = preguntamodificarpObsOblig;
    }

    private void checkPreguntamodificarpObsOblig() throws NotNullArgumentException {
        if(this.getPreguntamodificarpObsOblig() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPOBSOBLIG, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPOBSOBLIG);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpEsNumero() {
        return this.preguntamodificarpEsNumero;
    }
    
    public void setPreguntamodificarpEsNumero(Boolean preguntamodificarpEsNumero) {
        this.preguntamodificarpEsNumero = preguntamodificarpEsNumero;
    }

    private void checkPreguntamodificarpEsNumero() throws NotNullArgumentException {
        if(this.getPreguntamodificarpEsNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPESNUMERO, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPESNUMERO);
        }
    }

    @JsonIgnore
    public Double getPreguntamodificarpValorMax() {
        return this.preguntamodificarpValorMax;
    }
    
    public void setPreguntamodificarpValorMax(Double preguntamodificarpValorMax) {
        this.preguntamodificarpValorMax = preguntamodificarpValorMax;
    }

    @JsonIgnore
    public Double getPreguntamodificarpValorMin() {
        return this.preguntamodificarpValorMin;
    }
    
    public void setPreguntamodificarpValorMin(Double preguntamodificarpValorMin) {
        this.preguntamodificarpValorMin = preguntamodificarpValorMin;
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpSoloHombres() {
        return this.preguntamodificarpSoloHombres;
    }
    
    public void setPreguntamodificarpSoloHombres(Boolean preguntamodificarpSoloHombres) {
        this.preguntamodificarpSoloHombres = preguntamodificarpSoloHombres;
    }

    private void checkPreguntamodificarpSoloHombres() throws NotNullArgumentException {
        if(this.getPreguntamodificarpSoloHombres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPSOLOHOMBRES, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPSOLOHOMBRES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpSoloMujeres() {
        return this.preguntamodificarpSoloMujeres;
    }
    
    public void setPreguntamodificarpSoloMujeres(Boolean preguntamodificarpSoloMujeres) {
        this.preguntamodificarpSoloMujeres = preguntamodificarpSoloMujeres;
    }

    private void checkPreguntamodificarpSoloMujeres() throws NotNullArgumentException {
        if(this.getPreguntamodificarpSoloMujeres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPSOLOMUJERES, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPSOLOMUJERES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpRespMultiple() {
        return this.preguntamodificarpRespMultiple;
    }
    
    public void setPreguntamodificarpRespMultiple(Boolean preguntamodificarpRespMultiple) {
        this.preguntamodificarpRespMultiple = preguntamodificarpRespMultiple;
    }

    private void checkPreguntamodificarpRespMultiple() throws NotNullArgumentException {
        if(this.getPreguntamodificarpRespMultiple() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPRESPMULTIPLE, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPRESPMULTIPLE);
        }
    }

    @JsonIgnore
    public Boolean getPreguntamodificarpSeEnviaAlCORE() {
        return this.preguntamodificarpSeEnviaAlCORE;
    }
    
    public void setPreguntamodificarpSeEnviaAlCORE(Boolean preguntamodificarpSeEnviaAlCORE) {
        this.preguntamodificarpSeEnviaAlCORE = preguntamodificarpSeEnviaAlCORE;
    }

    private void checkPreguntamodificarpSeEnviaAlCORE() throws NotNullArgumentException {
        if(this.getPreguntamodificarpSeEnviaAlCORE() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPSEENVIAALCORE, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPSEENVIAALCORE);
        }
    }

    @JsonIgnore
    public Long getPreguntamodificarpCondicionEdad() {
        return this.preguntamodificarpCondicionEdad;
    }
    
    public void setPreguntamodificarpCondicionEdad(Long preguntamodificarpCondicionEdad) {
        this.preguntamodificarpCondicionEdad = preguntamodificarpCondicionEdad;
    }

    private void checkPreguntamodificarpCondicionEdad() throws NotNullArgumentException {
        if(this.getPreguntamodificarpCondicionEdad() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_MODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_MODIFICAR_PREGUNTAMODIFICARPCONDICIONEDAD, PreguntaConstants.SERV_ALIAS_MODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_MODIFICAR_PREGUNTAMODIFICARPCONDICIONEDAD);
        }
    }

    @JsonIgnore
    public Long getPreguntamodificarpCondicionEdadAnyos() {
        return this.preguntamodificarpCondicionEdadAnyos;
    }
    
    public void setPreguntamodificarpCondicionEdadAnyos(Long preguntamodificarpCondicionEdadAnyos) {
        this.preguntamodificarpCondicionEdadAnyos = preguntamodificarpCondicionEdadAnyos;
    }
}
