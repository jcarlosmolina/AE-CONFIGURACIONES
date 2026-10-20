package com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FrecuenciaActConstants;
import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.persistence.oid.FrecuenciaActOid;

public class EliminarRequest {

    @JsonProperty("p_thisfrecuenciaact_oid")
    private FrecuenciaActOid frecuenciaActeliminarpthisFrecuenciaAct;
    
    @JsonIgnore
    private FrecuenciaAct frecuenciaActeliminarpthisFrecuenciaActInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFrecuenciaActeliminarpthisFrecuenciaAct();
    }

    @JsonIgnore
    public FrecuenciaActOid getFrecuenciaActeliminarpthisFrecuenciaAct() {
        return this.frecuenciaActeliminarpthisFrecuenciaAct;
    }
    
    public void setFrecuenciaActeliminarpthisFrecuenciaAct(FrecuenciaActOid frecuenciaActeliminarpthisFrecuenciaAct) {
        this.frecuenciaActeliminarpthisFrecuenciaAct = frecuenciaActeliminarpthisFrecuenciaAct;
    }

    @JsonIgnore
    public FrecuenciaAct getFrecuenciaActeliminarpthisFrecuenciaActInstance() {
        return this.frecuenciaActeliminarpthisFrecuenciaActInstance;
    }
    
    public void setFrecuenciaActeliminarpthisFrecuenciaActInstance(FrecuenciaAct frecuenciaActeliminarpthisFrecuenciaAct) {
        this.frecuenciaActeliminarpthisFrecuenciaActInstance = frecuenciaActeliminarpthisFrecuenciaAct;
        this.frecuenciaActeliminarpthisFrecuenciaAct = frecuenciaActeliminarpthisFrecuenciaAct != null ? frecuenciaActeliminarpthisFrecuenciaAct.getOid() : null;
    }

    private void checkFrecuenciaActeliminarpthisFrecuenciaAct() throws NotNullArgumentException {
        if(this.getFrecuenciaActeliminarpthisFrecuenciaActInstance() == null || this.getFrecuenciaActeliminarpthisFrecuenciaActInstance().isNull()) {
            throw new NotNullArgumentException(FrecuenciaActConstants.SERV_ID_ELIMINAR, FrecuenciaActConstants.CLASS_ID, FrecuenciaActConstants.ARG_ID_ELIMINAR_FRECUENCIAACTELIMINARPTHISFRECUENCIAACT, FrecuenciaActConstants.SERV_ALIAS_ELIMINAR, FrecuenciaActConstants.CLASS_ALIAS, FrecuenciaActConstants.ARG_ALIAS_ELIMINAR_FRECUENCIAACTELIMINARPTHISFRECUENCIAACT);
        }
    }
}
