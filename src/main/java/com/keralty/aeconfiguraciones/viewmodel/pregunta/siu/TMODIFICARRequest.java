package com.keralty.aeconfiguraciones.viewmodel.pregunta.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PreguntaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreguntaOid;
import com.keralty.aeconfiguraciones.persistence.Pregunta;
import com.keralty.aeconfiguraciones.persistence.Seccion;

public class TMODIFICARRequest {

    @JsonProperty("p_thispregunta_oid")
    private PreguntaOid preguntaTMODIFICARpthisPregunta;
    
    @JsonIgnore
    private Pregunta preguntaTMODIFICARpthisPreguntaInstance;

    @JsonProperty("pt_p_descripcion")
    private String preguntaTMODIFICARptpDescripcion;

    @JsonProperty("pt_p_valorsnobliga")
    private Boolean preguntaTMODIFICARptpValorSNObliga;

    @JsonProperty("pt_p_obsoblig")
    private Boolean preguntaTMODIFICARptpObsOblig;

    @JsonProperty("pt_p_esnumero")
    private Boolean preguntaTMODIFICARptpEsNumero;

    @JsonProperty("pt_p_valormax")
    private Double preguntaTMODIFICARptpValorMax;

    @JsonProperty("pt_p_valormin")
    private Double preguntaTMODIFICARptpValorMin;

    @JsonProperty("pt_p_solohombres")
    private Boolean preguntaTMODIFICARptpSoloHombres;

    @JsonProperty("pt_p_solomujeres")
    private Boolean preguntaTMODIFICARptpSoloMujeres;

    @JsonProperty("pt_p_respmultiple")
    private Boolean preguntaTMODIFICARptpRespMultiple;

    @JsonProperty("pt_p_nuevoorden")
    private Long preguntaTMODIFICARptpNuevoOrden;

    @JsonProperty("pt_p_evcseccion_json")
    private String preguntaTMODIFICARptpevcSeccion;
    
    @JsonIgnore
    private Seccion preguntaTMODIFICARptpevcSeccionInstance;

    @JsonProperty("pt_p_seenviaalcore")
    private Boolean preguntaTMODIFICARptpSeEnviaAlCORE;

    @JsonProperty("pt_p_condicionedad")
    private Long preguntaTMODIFICARptpCondicionEdad;

    @JsonProperty("pt_p_condicionedadanyos")
    private Long preguntaTMODIFICARptpCondicionEdadAnyos;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkPreguntaTMODIFICARpthisPregunta();
        checkPreguntaTMODIFICARptpDescripcion();
        checkPreguntaTMODIFICARptpValorSNObliga();
        checkPreguntaTMODIFICARptpObsOblig();
        checkPreguntaTMODIFICARptpEsNumero();
        checkPreguntaTMODIFICARptpSoloHombres();
        checkPreguntaTMODIFICARptpSoloMujeres();
        checkPreguntaTMODIFICARptpRespMultiple();
        checkPreguntaTMODIFICARptpNuevoOrden();
        checkPreguntaTMODIFICARptpevcSeccion();
        checkPreguntaTMODIFICARptpSeEnviaAlCORE();
        checkPreguntaTMODIFICARptpCondicionEdad();
    }

    @JsonIgnore
    public PreguntaOid getPreguntaTMODIFICARpthisPregunta() {
        return this.preguntaTMODIFICARpthisPregunta;
    }
    
    public void setPreguntaTMODIFICARpthisPregunta(PreguntaOid preguntaTMODIFICARpthisPregunta) {
        this.preguntaTMODIFICARpthisPregunta = preguntaTMODIFICARpthisPregunta;
    }

    @JsonIgnore
    public Pregunta getPreguntaTMODIFICARpthisPreguntaInstance() {
        return this.preguntaTMODIFICARpthisPreguntaInstance;
    }
    
    public void setPreguntaTMODIFICARpthisPreguntaInstance(Pregunta preguntaTMODIFICARpthisPregunta) {
        this.preguntaTMODIFICARpthisPreguntaInstance = preguntaTMODIFICARpthisPregunta;
        this.preguntaTMODIFICARpthisPregunta = preguntaTMODIFICARpthisPregunta != null ? preguntaTMODIFICARpthisPregunta.getOid() : null;
    }

    private void checkPreguntaTMODIFICARpthisPregunta() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARpthisPreguntaInstance() == null || this.getPreguntaTMODIFICARpthisPreguntaInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTHISPREGUNTA, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTHISPREGUNTA);
        }
    }

    @JsonIgnore
    public String getPreguntaTMODIFICARptpDescripcion() {
        return this.preguntaTMODIFICARptpDescripcion;
    }
    
    public void setPreguntaTMODIFICARptpDescripcion(String preguntaTMODIFICARptpDescripcion) {
        this.preguntaTMODIFICARptpDescripcion = preguntaTMODIFICARptpDescripcion;
    }

    private void checkPreguntaTMODIFICARptpDescripcion() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpDescripcion() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPDESCRIPCION, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpValorSNObliga() {
        return this.preguntaTMODIFICARptpValorSNObliga;
    }
    
    public void setPreguntaTMODIFICARptpValorSNObliga(Boolean preguntaTMODIFICARptpValorSNObliga) {
        this.preguntaTMODIFICARptpValorSNObliga = preguntaTMODIFICARptpValorSNObliga;
    }

    private void checkPreguntaTMODIFICARptpValorSNObliga() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpValorSNObliga() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPVALORSNOBLIGA, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPVALORSNOBLIGA);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpObsOblig() {
        return this.preguntaTMODIFICARptpObsOblig;
    }
    
    public void setPreguntaTMODIFICARptpObsOblig(Boolean preguntaTMODIFICARptpObsOblig) {
        this.preguntaTMODIFICARptpObsOblig = preguntaTMODIFICARptpObsOblig;
    }

    private void checkPreguntaTMODIFICARptpObsOblig() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpObsOblig() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPOBSOBLIG, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPOBSOBLIG);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpEsNumero() {
        return this.preguntaTMODIFICARptpEsNumero;
    }
    
    public void setPreguntaTMODIFICARptpEsNumero(Boolean preguntaTMODIFICARptpEsNumero) {
        this.preguntaTMODIFICARptpEsNumero = preguntaTMODIFICARptpEsNumero;
    }

    private void checkPreguntaTMODIFICARptpEsNumero() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpEsNumero() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPESNUMERO, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPESNUMERO);
        }
    }

    @JsonIgnore
    public Double getPreguntaTMODIFICARptpValorMax() {
        return this.preguntaTMODIFICARptpValorMax;
    }
    
    public void setPreguntaTMODIFICARptpValorMax(Double preguntaTMODIFICARptpValorMax) {
        this.preguntaTMODIFICARptpValorMax = preguntaTMODIFICARptpValorMax;
    }

    @JsonIgnore
    public Double getPreguntaTMODIFICARptpValorMin() {
        return this.preguntaTMODIFICARptpValorMin;
    }
    
    public void setPreguntaTMODIFICARptpValorMin(Double preguntaTMODIFICARptpValorMin) {
        this.preguntaTMODIFICARptpValorMin = preguntaTMODIFICARptpValorMin;
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpSoloHombres() {
        return this.preguntaTMODIFICARptpSoloHombres;
    }
    
    public void setPreguntaTMODIFICARptpSoloHombres(Boolean preguntaTMODIFICARptpSoloHombres) {
        this.preguntaTMODIFICARptpSoloHombres = preguntaTMODIFICARptpSoloHombres;
    }

    private void checkPreguntaTMODIFICARptpSoloHombres() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpSoloHombres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPSOLOHOMBRES, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPSOLOHOMBRES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpSoloMujeres() {
        return this.preguntaTMODIFICARptpSoloMujeres;
    }
    
    public void setPreguntaTMODIFICARptpSoloMujeres(Boolean preguntaTMODIFICARptpSoloMujeres) {
        this.preguntaTMODIFICARptpSoloMujeres = preguntaTMODIFICARptpSoloMujeres;
    }

    private void checkPreguntaTMODIFICARptpSoloMujeres() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpSoloMujeres() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPSOLOMUJERES, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPSOLOMUJERES);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpRespMultiple() {
        return this.preguntaTMODIFICARptpRespMultiple;
    }
    
    public void setPreguntaTMODIFICARptpRespMultiple(Boolean preguntaTMODIFICARptpRespMultiple) {
        this.preguntaTMODIFICARptpRespMultiple = preguntaTMODIFICARptpRespMultiple;
    }

    private void checkPreguntaTMODIFICARptpRespMultiple() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpRespMultiple() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPRESPMULTIPLE, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPRESPMULTIPLE);
        }
    }

    @JsonIgnore
    public Long getPreguntaTMODIFICARptpNuevoOrden() {
        return this.preguntaTMODIFICARptpNuevoOrden;
    }
    
    public void setPreguntaTMODIFICARptpNuevoOrden(Long preguntaTMODIFICARptpNuevoOrden) {
        this.preguntaTMODIFICARptpNuevoOrden = preguntaTMODIFICARptpNuevoOrden;
    }

    private void checkPreguntaTMODIFICARptpNuevoOrden() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpNuevoOrden() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPNUEVOORDEN, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPNUEVOORDEN);
        }
    }

    @JsonIgnore
    public String getPreguntaTMODIFICARptpevcSeccion() {
        return this.preguntaTMODIFICARptpevcSeccion;
    }
    
    public void setPreguntaTMODIFICARptpevcSeccion(String preguntaTMODIFICARptpevcSeccion) {
        this.preguntaTMODIFICARptpevcSeccion = preguntaTMODIFICARptpevcSeccion;
    }

    @JsonIgnore
    public Seccion getPreguntaTMODIFICARptpevcSeccionInstance() {
        return this.preguntaTMODIFICARptpevcSeccionInstance;
    }
    
    public void setPreguntaTMODIFICARptpevcSeccionInstance(Seccion preguntaTMODIFICARptpevcSeccion) {
        this.preguntaTMODIFICARptpevcSeccionInstance = preguntaTMODIFICARptpevcSeccion;
        this.preguntaTMODIFICARptpevcSeccion = preguntaTMODIFICARptpevcSeccion != null ? preguntaTMODIFICARptpevcSeccion.getOid().toJson() : "";
    }

    private void checkPreguntaTMODIFICARptpevcSeccion() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpevcSeccionInstance() == null || this.getPreguntaTMODIFICARptpevcSeccionInstance().isNull()) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPEVCSECCION, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPEVCSECCION);
        }
    }

    @JsonIgnore
    public Boolean getPreguntaTMODIFICARptpSeEnviaAlCORE() {
        return this.preguntaTMODIFICARptpSeEnviaAlCORE;
    }
    
    public void setPreguntaTMODIFICARptpSeEnviaAlCORE(Boolean preguntaTMODIFICARptpSeEnviaAlCORE) {
        this.preguntaTMODIFICARptpSeEnviaAlCORE = preguntaTMODIFICARptpSeEnviaAlCORE;
    }

    private void checkPreguntaTMODIFICARptpSeEnviaAlCORE() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpSeEnviaAlCORE() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPSEENVIAALCORE, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPSEENVIAALCORE);
        }
    }

    @JsonIgnore
    public Long getPreguntaTMODIFICARptpCondicionEdad() {
        return this.preguntaTMODIFICARptpCondicionEdad;
    }
    
    public void setPreguntaTMODIFICARptpCondicionEdad(Long preguntaTMODIFICARptpCondicionEdad) {
        this.preguntaTMODIFICARptpCondicionEdad = preguntaTMODIFICARptpCondicionEdad;
    }

    private void checkPreguntaTMODIFICARptpCondicionEdad() throws NotNullArgumentException {
        if(this.getPreguntaTMODIFICARptpCondicionEdad() == null) {
            throw new NotNullArgumentException(PreguntaConstants.SERV_ID_TMODIFICAR, PreguntaConstants.CLASS_ID, PreguntaConstants.ARG_ID_TMODIFICAR_PREGUNTATMODIFICARPTPCONDICIONEDAD, PreguntaConstants.SERV_ALIAS_TMODIFICAR, PreguntaConstants.CLASS_ALIAS, PreguntaConstants.ARG_ALIAS_TMODIFICAR_PREGUNTATMODIFICARPTPCONDICIONEDAD);
        }
    }

    @JsonIgnore
    public Long getPreguntaTMODIFICARptpCondicionEdadAnyos() {
        return this.preguntaTMODIFICARptpCondicionEdadAnyos;
    }
    
    public void setPreguntaTMODIFICARptpCondicionEdadAnyos(Long preguntaTMODIFICARptpCondicionEdadAnyos) {
        this.preguntaTMODIFICARptpCondicionEdadAnyos = preguntaTMODIFICARptpCondicionEdadAnyos;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
