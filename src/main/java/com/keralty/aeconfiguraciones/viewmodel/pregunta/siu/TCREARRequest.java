package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class TCREARRequest {

    @JsonProperty("pt_p_agrseccion_json")
    private String preguntaTCREARptpagrSeccion;
    
    @JsonIgnore
    private Seccion preguntaTCREARptpagrSeccionInstance;

    @JsonProperty("pt_p_atrnumero")
    private Long preguntaTCREARptpatrNumero;

    @JsonProperty("pt_p_atrnumorden")
    private Long preguntaTCREARptpatrNumOrden;

    @JsonProperty("pt_p_atrdescripcion")
    private String preguntaTCREARptpatrDescripcion;

    @JsonProperty("pt_p_atrvalorsnobliga")
    private Boolean preguntaTCREARptpatrValorSNObliga;

    @JsonProperty("pt_p_atrobsoblig")
    private Boolean preguntaTCREARptpatrObsOblig;

    @JsonProperty("pt_p_atresnumero")
    private Boolean preguntaTCREARptpatrEsNumero;

    @JsonProperty("pt_p_atrvalormax")
    private Double preguntaTCREARptpatrValorMax;

    @JsonProperty("pt_p_atrvalormin")
    private Double preguntaTCREARptpatrValorMin;

    @JsonProperty("pt_p_atrsolohombres")
    private Boolean preguntaTCREARptpatrSoloHombres;

    @JsonProperty("pt_p_atrsolomujeres")
    private Boolean preguntaTCREARptpatrSoloMujeres;

    @JsonProperty("pt_p_atrrespmultiple")
    private Boolean preguntaTCREARptpatrRespMultiple;

    @JsonProperty("pt_p_atrseenviaalcore")
    private Boolean preguntaTCREARptpatrSeEnviaAlCORE;

    @JsonProperty("pt_p_atrcondicionedad")
    private Long preguntaTCREARptpatrCondicionEdad;

    @JsonProperty("pt_p_atrcondicionedadanyos")
    private Long preguntaTCREARptpatrCondicionEdadAnyos;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaTCREARptpagrSeccion();
        checkPreguntaTCREARptpatrNumero();
        checkPreguntaTCREARptpatrNumOrden();
        checkPreguntaTCREARptpatrDescripcion();
        checkPreguntaTCREARptpatrValorSNObliga();
        checkPreguntaTCREARptpatrObsOblig();
        checkPreguntaTCREARptpatrEsNumero();
        checkPreguntaTCREARptpatrSoloHombres();
        checkPreguntaTCREARptpatrSoloMujeres();
        checkPreguntaTCREARptpatrRespMultiple();
        checkPreguntaTCREARptpatrSeEnviaAlCORE();
        checkPreguntaTCREARptpatrCondicionEdad();
    }

    @JsonIgnore
    public String getPreguntaTCREARptpagrSeccion() {
        return this.preguntaTCREARptpagrSeccion;
    }
    
    public void setPreguntaTCREARptpagrSeccion(String preguntaTCREARptpagrSeccion) {
        this.preguntaTCREARptpagrSeccion = preguntaTCREARptpagrSeccion;
    }

    @JsonIgnore
    public Seccion getPreguntaTCREARptpagrSeccionInstance() {
        return this.preguntaTCREARptpagrSeccionInstance;
    }
    
    public void setPreguntaTCREARptpagrSeccionInstance(Seccion preguntaTCREARptpagrSeccion) {
        this.preguntaTCREARptpagrSeccionInstance = preguntaTCREARptpagrSeccion;
        this.preguntaTCREARptpagrSeccion = preguntaTCREARptpagrSeccion != null ? preguntaTCREARptpagrSeccion.getOid().toJson() : "";
    }

    private void checkPreguntaTCREARptpagrSeccion() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpagrSeccionInstance() == null || this.getPreguntaTCREARptpagrSeccionInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPAGRSECCION, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPAGRSECCION);
        }
    }

    @JsonIgnore
    public Long getPreguntaTCREARptpatrNumero() {
        return this.preguntaTCREARptpatrNumero;
    }
    
    public void setPreguntaTCREARptpatrNumero(Long preguntaTCREARptpatrNumero) {
        this.preguntaTCREARptpatrNumero = preguntaTCREARptpatrNumero;
    }

    private void checkPreguntaTCREARptpatrNumero() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRNUMERO, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRNUMERO);
        }
    }

    @JsonIgnore
    public Long getPreguntaTCREARptpatrNumOrden() {
        return this.preguntaTCREARptpatrNumOrden;
    }
    
    public void setPreguntaTCREARptpatrNumOrden(Long preguntaTCREARptpatrNumOrden) {
        this.preguntaTCREARptpatrNumOrden = preguntaTCREARptpatrNumOrden;
    }

    private void checkPreguntaTCREARptpatrNumOrden() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrNumOrden() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRNUMORDEN, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRNUMORDEN);
        }
    }

    @JsonIgnore
    public String getPreguntaTCREARptpatrDescripcion() {
        return this.preguntaTCREARptpatrDescripcion;
    }
    
    public void setPreguntaTCREARptpatrDescripcion(String preguntaTCREARptpatrDescripcion) {
        this.preguntaTCREARptpatrDescripcion = preguntaTCREARptpatrDescripcion;
    }

    private void checkPreguntaTCREARptpatrDescripcion() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrDescripcion() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRDESCRIPCION, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrValorSNObliga() {
        return this.preguntaTCREARptpatrValorSNObliga;
    }
    
    public void setPreguntaTCREARptpatrValorSNObliga(Boolean preguntaTCREARptpatrValorSNObliga) {
        this.preguntaTCREARptpatrValorSNObliga = preguntaTCREARptpatrValorSNObliga;
    }

    private void checkPreguntaTCREARptpatrValorSNObliga() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrValorSNObliga() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRVALORSNOBLIGA, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRVALORSNOBLIGA);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrObsOblig() {
        return this.preguntaTCREARptpatrObsOblig;
    }
    
    public void setPreguntaTCREARptpatrObsOblig(Boolean preguntaTCREARptpatrObsOblig) {
        this.preguntaTCREARptpatrObsOblig = preguntaTCREARptpatrObsOblig;
    }

    private void checkPreguntaTCREARptpatrObsOblig() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrObsOblig() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATROBSOBLIG, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATROBSOBLIG);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrEsNumero() {
        return this.preguntaTCREARptpatrEsNumero;
    }
    
    public void setPreguntaTCREARptpatrEsNumero(Boolean preguntaTCREARptpatrEsNumero) {
        this.preguntaTCREARptpatrEsNumero = preguntaTCREARptpatrEsNumero;
    }

    private void checkPreguntaTCREARptpatrEsNumero() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrEsNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRESNUMERO, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRESNUMERO);
        }
    }

    @JsonIgnore
    public Double getPreguntaTCREARptpatrValorMax() {
        return this.preguntaTCREARptpatrValorMax;
    }
    
    public void setPreguntaTCREARptpatrValorMax(Double preguntaTCREARptpatrValorMax) {
        this.preguntaTCREARptpatrValorMax = preguntaTCREARptpatrValorMax;
    }

    @JsonIgnore
    public Double getPreguntaTCREARptpatrValorMin() {
        return this.preguntaTCREARptpatrValorMin;
    }
    
    public void setPreguntaTCREARptpatrValorMin(Double preguntaTCREARptpatrValorMin) {
        this.preguntaTCREARptpatrValorMin = preguntaTCREARptpatrValorMin;
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrSoloHombres() {
        return this.preguntaTCREARptpatrSoloHombres;
    }
    
    public void setPreguntaTCREARptpatrSoloHombres(Boolean preguntaTCREARptpatrSoloHombres) {
        this.preguntaTCREARptpatrSoloHombres = preguntaTCREARptpatrSoloHombres;
    }

    private void checkPreguntaTCREARptpatrSoloHombres() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrSoloHombres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRSOLOHOMBRES, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRSOLOHOMBRES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrSoloMujeres() {
        return this.preguntaTCREARptpatrSoloMujeres;
    }
    
    public void setPreguntaTCREARptpatrSoloMujeres(Boolean preguntaTCREARptpatrSoloMujeres) {
        this.preguntaTCREARptpatrSoloMujeres = preguntaTCREARptpatrSoloMujeres;
    }

    private void checkPreguntaTCREARptpatrSoloMujeres() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrSoloMujeres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRSOLOMUJERES, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRSOLOMUJERES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrRespMultiple() {
        return this.preguntaTCREARptpatrRespMultiple;
    }
    
    public void setPreguntaTCREARptpatrRespMultiple(Boolean preguntaTCREARptpatrRespMultiple) {
        this.preguntaTCREARptpatrRespMultiple = preguntaTCREARptpatrRespMultiple;
    }

    private void checkPreguntaTCREARptpatrRespMultiple() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrRespMultiple() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRRESPMULTIPLE, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRRESPMULTIPLE);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTCREARptpatrSeEnviaAlCORE() {
        return this.preguntaTCREARptpatrSeEnviaAlCORE;
    }
    
    public void setPreguntaTCREARptpatrSeEnviaAlCORE(Boolean preguntaTCREARptpatrSeEnviaAlCORE) {
        this.preguntaTCREARptpatrSeEnviaAlCORE = preguntaTCREARptpatrSeEnviaAlCORE;
    }

    private void checkPreguntaTCREARptpatrSeEnviaAlCORE() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrSeEnviaAlCORE() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRSEENVIAALCORE, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRSEENVIAALCORE);
        }
    }

    @JsonIgnore
    public Long getPreguntaTCREARptpatrCondicionEdad() {
        return this.preguntaTCREARptpatrCondicionEdad;
    }
    
    public void setPreguntaTCREARptpatrCondicionEdad(Long preguntaTCREARptpatrCondicionEdad) {
        this.preguntaTCREARptpatrCondicionEdad = preguntaTCREARptpatrCondicionEdad;
    }

    private void checkPreguntaTCREARptpatrCondicionEdad() throws NotNullArgumentException {
        if(this.getPreguntaTCREARptpatrCondicionEdad() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TCREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TCREAR_PREGUNTATCREARPTPATRCONDICIONEDAD, PreguntaConstants.SERV_ALIAS_TCREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TCREAR_PREGUNTATCREARPTPATRCONDICIONEDAD);
        }
    }

    @JsonIgnore
    public Long getPreguntaTCREARptpatrCondicionEdadAnyos() {
        return this.preguntaTCREARptpatrCondicionEdadAnyos;
    }
    
    public void setPreguntaTCREARptpatrCondicionEdadAnyos(Long preguntaTCREARptpatrCondicionEdadAnyos) {
        this.preguntaTCREARptpatrCondicionEdadAnyos = preguntaTCREARptpatrCondicionEdadAnyos;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
