package com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FrecuenciaActConstants;
import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.persistence.oid.FrecuenciaActOid;

public class ModificarRequest {

    @JsonProperty("p_thisfrecuenciaact_oid")
    private FrecuenciaActOid frecuenciaActmodificarpthisFrecuenciaAct;
    
    @JsonIgnore
    private FrecuenciaAct frecuenciaActmodificarpthisFrecuenciaActInstance;

    @JsonProperty("p_frecuencia")
    private Long frecuenciaActmodificarpFrecuencia;

    public void checkArguments() throws NotNullArgumentException {
        checkFrecuenciaActmodificarpthisFrecuenciaAct();
        checkFrecuenciaActmodificarpFrecuencia();
    }

    @JsonIgnore
    public FrecuenciaActOid getFrecuenciaActmodificarpthisFrecuenciaAct() {
        return this.frecuenciaActmodificarpthisFrecuenciaAct;
    }
    
    public void setFrecuenciaActmodificarpthisFrecuenciaAct(FrecuenciaActOid frecuenciaActmodificarpthisFrecuenciaAct) {
        this.frecuenciaActmodificarpthisFrecuenciaAct = frecuenciaActmodificarpthisFrecuenciaAct;
    }

    @JsonIgnore
    public FrecuenciaAct getFrecuenciaActmodificarpthisFrecuenciaActInstance() {
        return this.frecuenciaActmodificarpthisFrecuenciaActInstance;
    }
    
    public void setFrecuenciaActmodificarpthisFrecuenciaActInstance(FrecuenciaAct frecuenciaActmodificarpthisFrecuenciaAct) {
        this.frecuenciaActmodificarpthisFrecuenciaActInstance = frecuenciaActmodificarpthisFrecuenciaAct;
        this.frecuenciaActmodificarpthisFrecuenciaAct = frecuenciaActmodificarpthisFrecuenciaAct != null ? frecuenciaActmodificarpthisFrecuenciaAct.getOid() : null;
    }

    private void checkFrecuenciaActmodificarpthisFrecuenciaAct() throws NotNullArgumentException {
        if(this.getFrecuenciaActmodificarpthisFrecuenciaActInstance() == null || this.getFrecuenciaActmodificarpthisFrecuenciaActInstance().isNull()) {
            throw new NotNullArgumentException(FrecuenciaActConstants.SERV_ID_MODIFICAR, FrecuenciaActConstants.CLASS_ID, FrecuenciaActConstants.ARG_ID_MODIFICAR_FRECUENCIAACTMODIFICARPTHISFRECUENCIAACT, FrecuenciaActConstants.SERV_ALIAS_MODIFICAR, FrecuenciaActConstants.CLASS_ALIAS, FrecuenciaActConstants.ARG_ALIAS_MODIFICAR_FRECUENCIAACTMODIFICARPTHISFRECUENCIAACT);
        }
    }

    @JsonIgnore
    public Long getFrecuenciaActmodificarpFrecuencia() {
        return this.frecuenciaActmodificarpFrecuencia;
    }
    
    public void setFrecuenciaActmodificarpFrecuencia(Long frecuenciaActmodificarpFrecuencia) {
        this.frecuenciaActmodificarpFrecuencia = frecuenciaActmodificarpFrecuencia;
    }

    private void checkFrecuenciaActmodificarpFrecuencia() throws NotNullArgumentException {
        if(this.getFrecuenciaActmodificarpFrecuencia() == null) {
            throw new NotNullArgumentException(FrecuenciaActConstants.SERV_ID_MODIFICAR, FrecuenciaActConstants.CLASS_ID, FrecuenciaActConstants.ARG_ID_MODIFICAR_FRECUENCIAACTMODIFICARPFRECUENCIA, FrecuenciaActConstants.SERV_ALIAS_MODIFICAR, FrecuenciaActConstants.CLASS_ALIAS, FrecuenciaActConstants.ARG_ALIAS_MODIFICAR_FRECUENCIAACTMODIFICARPFRECUENCIA);
        }
    }
}
