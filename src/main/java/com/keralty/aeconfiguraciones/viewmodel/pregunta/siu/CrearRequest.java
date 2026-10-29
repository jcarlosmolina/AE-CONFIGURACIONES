package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionOid;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class CrearRequest {

    @JsonProperty("p_agrseccion_oid")
    private SeccionOid preguntacrearpagrSeccion;
    
    @JsonIgnore
    private Seccion preguntacrearpagrSeccionInstance;

    @JsonProperty("p_atrnumero")
    private Long preguntacrearpatrNumero;

    @JsonProperty("p_atrnumorden")
    private Long preguntacrearpatrNumOrden;

    @JsonProperty("p_atrdescripcion")
    private String preguntacrearpatrDescripcion;

    @JsonProperty("p_atrvalorsnobliga")
    private Boolean preguntacrearpatrValorSNObliga;

    @JsonProperty("p_atrobsoblig")
    private Boolean preguntacrearpatrObsOblig;

    @JsonProperty("p_atresnumero")
    private Boolean preguntacrearpatrEsNumero;

    @JsonProperty("p_atrvalormax")
    private Double preguntacrearpatrValorMax;

    @JsonProperty("p_atrvalormin")
    private Double preguntacrearpatrValorMin;

    @JsonProperty("p_atrsolohombres")
    private Boolean preguntacrearpatrSoloHombres;

    @JsonProperty("p_atrsolomujeres")
    private Boolean preguntacrearpatrSoloMujeres;

    @JsonProperty("p_atrrespmultiple")
    private Boolean preguntacrearpatrRespMultiple;

    @JsonProperty("p_atrseenviaalcore")
    private Boolean preguntacrearpatrSeEnviaAlCORE;

    @JsonProperty("p_atrcondicionedad")
    private Long preguntacrearpatrCondicionEdad;

    @JsonProperty("p_atrcondicionedadanyos")
    private Long preguntacrearpatrCondicionEdadAnyos;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntacrearpagrSeccion();
        checkPreguntacrearpatrNumero();
        checkPreguntacrearpatrNumOrden();
        checkPreguntacrearpatrDescripcion();
        checkPreguntacrearpatrValorSNObliga();
        checkPreguntacrearpatrObsOblig();
        checkPreguntacrearpatrEsNumero();
        checkPreguntacrearpatrSoloHombres();
        checkPreguntacrearpatrSoloMujeres();
        checkPreguntacrearpatrRespMultiple();
        checkPreguntacrearpatrSeEnviaAlCORE();
        checkPreguntacrearpatrCondicionEdad();
    }

    @JsonIgnore
    public SeccionOid getPreguntacrearpagrSeccion() {
        return this.preguntacrearpagrSeccion;
    }
    
    public void setPreguntacrearpagrSeccion(SeccionOid preguntacrearpagrSeccion) {
        this.preguntacrearpagrSeccion = preguntacrearpagrSeccion;
    }

    @JsonIgnore
    public Seccion getPreguntacrearpagrSeccionInstance() {
        return this.preguntacrearpagrSeccionInstance;
    }
    
    public void setPreguntacrearpagrSeccionInstance(Seccion preguntacrearpagrSeccion) {
        this.preguntacrearpagrSeccionInstance = preguntacrearpagrSeccion;
        this.preguntacrearpagrSeccion = preguntacrearpagrSeccion != null ? preguntacrearpagrSeccion.getOid() : null;
    }

    private void checkPreguntacrearpagrSeccion() throws NotNullArgumentException {
        if(this.getPreguntacrearpagrSeccionInstance() == null || this.getPreguntacrearpagrSeccionInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPAGRSECCION, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPAGRSECCION);
        }
    }

    @JsonIgnore
    public Long getPreguntacrearpatrNumero() {
        return this.preguntacrearpatrNumero;
    }
    
    public void setPreguntacrearpatrNumero(Long preguntacrearpatrNumero) {
        this.preguntacrearpatrNumero = preguntacrearpatrNumero;
    }

    private void checkPreguntacrearpatrNumero() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRNUMERO, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRNUMERO);
        }
    }

    @JsonIgnore
    public Long getPreguntacrearpatrNumOrden() {
        return this.preguntacrearpatrNumOrden;
    }
    
    public void setPreguntacrearpatrNumOrden(Long preguntacrearpatrNumOrden) {
        this.preguntacrearpatrNumOrden = preguntacrearpatrNumOrden;
    }

    private void checkPreguntacrearpatrNumOrden() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrNumOrden() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRNUMORDEN, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRNUMORDEN);
        }
    }

    @JsonIgnore
    public String getPreguntacrearpatrDescripcion() {
        return this.preguntacrearpatrDescripcion;
    }
    
    public void setPreguntacrearpatrDescripcion(String preguntacrearpatrDescripcion) {
        this.preguntacrearpatrDescripcion = preguntacrearpatrDescripcion;
    }

    private void checkPreguntacrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRDESCRIPCION, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrValorSNObliga() {
        return this.preguntacrearpatrValorSNObliga;
    }
    
    public void setPreguntacrearpatrValorSNObliga(Boolean preguntacrearpatrValorSNObliga) {
        this.preguntacrearpatrValorSNObliga = preguntacrearpatrValorSNObliga;
    }

    private void checkPreguntacrearpatrValorSNObliga() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrValorSNObliga() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRVALORSNOBLIGA, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRVALORSNOBLIGA);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrObsOblig() {
        return this.preguntacrearpatrObsOblig;
    }
    
    public void setPreguntacrearpatrObsOblig(Boolean preguntacrearpatrObsOblig) {
        this.preguntacrearpatrObsOblig = preguntacrearpatrObsOblig;
    }

    private void checkPreguntacrearpatrObsOblig() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrObsOblig() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATROBSOBLIG, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATROBSOBLIG);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrEsNumero() {
        return this.preguntacrearpatrEsNumero;
    }
    
    public void setPreguntacrearpatrEsNumero(Boolean preguntacrearpatrEsNumero) {
        this.preguntacrearpatrEsNumero = preguntacrearpatrEsNumero;
    }

    private void checkPreguntacrearpatrEsNumero() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrEsNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRESNUMERO, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRESNUMERO);
        }
    }

    @JsonIgnore
    public Double getPreguntacrearpatrValorMax() {
        return this.preguntacrearpatrValorMax;
    }
    
    public void setPreguntacrearpatrValorMax(Double preguntacrearpatrValorMax) {
        this.preguntacrearpatrValorMax = preguntacrearpatrValorMax;
    }

    @JsonIgnore
    public Double getPreguntacrearpatrValorMin() {
        return this.preguntacrearpatrValorMin;
    }
    
    public void setPreguntacrearpatrValorMin(Double preguntacrearpatrValorMin) {
        this.preguntacrearpatrValorMin = preguntacrearpatrValorMin;
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrSoloHombres() {
        return this.preguntacrearpatrSoloHombres;
    }
    
    public void setPreguntacrearpatrSoloHombres(Boolean preguntacrearpatrSoloHombres) {
        this.preguntacrearpatrSoloHombres = preguntacrearpatrSoloHombres;
    }

    private void checkPreguntacrearpatrSoloHombres() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrSoloHombres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRSOLOHOMBRES, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRSOLOHOMBRES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrSoloMujeres() {
        return this.preguntacrearpatrSoloMujeres;
    }
    
    public void setPreguntacrearpatrSoloMujeres(Boolean preguntacrearpatrSoloMujeres) {
        this.preguntacrearpatrSoloMujeres = preguntacrearpatrSoloMujeres;
    }

    private void checkPreguntacrearpatrSoloMujeres() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrSoloMujeres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRSOLOMUJERES, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRSOLOMUJERES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrRespMultiple() {
        return this.preguntacrearpatrRespMultiple;
    }
    
    public void setPreguntacrearpatrRespMultiple(Boolean preguntacrearpatrRespMultiple) {
        this.preguntacrearpatrRespMultiple = preguntacrearpatrRespMultiple;
    }

    private void checkPreguntacrearpatrRespMultiple() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrRespMultiple() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRRESPMULTIPLE, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRRESPMULTIPLE);
        }
    }

    @JsonIgnore
    public Boolean getPreguntacrearpatrSeEnviaAlCORE() {
        return this.preguntacrearpatrSeEnviaAlCORE;
    }
    
    public void setPreguntacrearpatrSeEnviaAlCORE(Boolean preguntacrearpatrSeEnviaAlCORE) {
        this.preguntacrearpatrSeEnviaAlCORE = preguntacrearpatrSeEnviaAlCORE;
    }

    private void checkPreguntacrearpatrSeEnviaAlCORE() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrSeEnviaAlCORE() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRSEENVIAALCORE, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRSEENVIAALCORE);
        }
    }

    @JsonIgnore
    public Long getPreguntacrearpatrCondicionEdad() {
        return this.preguntacrearpatrCondicionEdad;
    }
    
    public void setPreguntacrearpatrCondicionEdad(Long preguntacrearpatrCondicionEdad) {
        this.preguntacrearpatrCondicionEdad = preguntacrearpatrCondicionEdad;
    }

    private void checkPreguntacrearpatrCondicionEdad() throws NotNullArgumentException {
        if(this.getPreguntacrearpatrCondicionEdad() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_CREAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_CREAR_PREGUNTACREARPATRCONDICIONEDAD, PreguntaConstants.SERV_ALIAS_CREAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_CREAR_PREGUNTACREARPATRCONDICIONEDAD);
        }
    }

    @JsonIgnore
    public Long getPreguntacrearpatrCondicionEdadAnyos() {
        return this.preguntacrearpatrCondicionEdadAnyos;
    }
    
    public void setPreguntacrearpatrCondicionEdadAnyos(Long preguntacrearpatrCondicionEdadAnyos) {
        this.preguntacrearpatrCondicionEdadAnyos = preguntacrearpatrCondicionEdadAnyos;
    }
}
