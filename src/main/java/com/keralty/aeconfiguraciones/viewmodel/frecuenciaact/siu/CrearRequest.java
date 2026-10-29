package com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FrecuenciaActConstants;

public class CrearRequest {

    @JsonProperty("p_atrparametrica")
    private Long frecuenciaActcrearpatrParametrica;

    @JsonProperty("p_atrfrecuencia")
    private Long frecuenciaActcrearpatrFrecuencia;

    public void checkArguments() throws NotNullArgumentException {
        checkFrecuenciaActcrearpatrParametrica();
        checkFrecuenciaActcrearpatrFrecuencia();
    }

    @JsonIgnore
    public Long getFrecuenciaActcrearpatrParametrica() {
        return this.frecuenciaActcrearpatrParametrica;
    }
    
    public void setFrecuenciaActcrearpatrParametrica(Long frecuenciaActcrearpatrParametrica) {
        this.frecuenciaActcrearpatrParametrica = frecuenciaActcrearpatrParametrica;
    }

    private void checkFrecuenciaActcrearpatrParametrica() throws NotNullArgumentException {
        if(this.getFrecuenciaActcrearpatrParametrica() == null) {
            throw new NotNullArgumentException(FrecuenciaActConstants.SERV_ID_CREAR, FrecuenciaActConstants.CLASS_ID, FrecuenciaActConstants.ARG_ID_CREAR_FRECUENCIAACTCREARPATRPARAMETRICA, FrecuenciaActConstants.SERV_ALIAS_CREAR, FrecuenciaActConstants.CLASS_ALIAS, FrecuenciaActConstants.ARG_ALIAS_CREAR_FRECUENCIAACTCREARPATRPARAMETRICA);
        }
    }

    @JsonIgnore
    public Long getFrecuenciaActcrearpatrFrecuencia() {
        return this.frecuenciaActcrearpatrFrecuencia;
    }
    
    public void setFrecuenciaActcrearpatrFrecuencia(Long frecuenciaActcrearpatrFrecuencia) {
        this.frecuenciaActcrearpatrFrecuencia = frecuenciaActcrearpatrFrecuencia;
    }

    private void checkFrecuenciaActcrearpatrFrecuencia() throws NotNullArgumentException {
        if(this.getFrecuenciaActcrearpatrFrecuencia() == null) {
            throw new NotNullArgumentException(FrecuenciaActConstants.SERV_ID_CREAR, FrecuenciaActConstants.CLASS_ID, FrecuenciaActConstants.ARG_ID_CREAR_FRECUENCIAACTCREARPATRFRECUENCIA, FrecuenciaActConstants.SERV_ALIAS_CREAR, FrecuenciaActConstants.CLASS_ALIAS, FrecuenciaActConstants.ARG_ALIAS_CREAR_FRECUENCIAACTCREARPATRFRECUENCIA);
        }
    }
}
