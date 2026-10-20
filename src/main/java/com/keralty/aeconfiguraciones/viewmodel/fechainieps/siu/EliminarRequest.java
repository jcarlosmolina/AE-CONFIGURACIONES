package com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.oid.FechaIniEPSOid;

public class EliminarRequest {

    @JsonProperty("p_thisfechainieps_oid")
    private FechaIniEPSOid fechaIniEPSeliminarpthisFechaIniEPS;
    
    @JsonIgnore
    private FechaIniEPS fechaIniEPSeliminarpthisFechaIniEPSInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkFechaIniEPSeliminarpthisFechaIniEPS();
    }

    @JsonIgnore
    public FechaIniEPSOid getFechaIniEPSeliminarpthisFechaIniEPS() {
        return this.fechaIniEPSeliminarpthisFechaIniEPS;
    }
    
    public void setFechaIniEPSeliminarpthisFechaIniEPS(FechaIniEPSOid fechaIniEPSeliminarpthisFechaIniEPS) {
        this.fechaIniEPSeliminarpthisFechaIniEPS = fechaIniEPSeliminarpthisFechaIniEPS;
    }

    @JsonIgnore
    public FechaIniEPS getFechaIniEPSeliminarpthisFechaIniEPSInstance() {
        return this.fechaIniEPSeliminarpthisFechaIniEPSInstance;
    }
    
    public void setFechaIniEPSeliminarpthisFechaIniEPSInstance(FechaIniEPS fechaIniEPSeliminarpthisFechaIniEPS) {
        this.fechaIniEPSeliminarpthisFechaIniEPSInstance = fechaIniEPSeliminarpthisFechaIniEPS;
        this.fechaIniEPSeliminarpthisFechaIniEPS = fechaIniEPSeliminarpthisFechaIniEPS != null ? fechaIniEPSeliminarpthisFechaIniEPS.getOid() : null;
    }

    private void checkFechaIniEPSeliminarpthisFechaIniEPS() throws NotNullArgumentException {
        if(this.getFechaIniEPSeliminarpthisFechaIniEPSInstance() == null || this.getFechaIniEPSeliminarpthisFechaIniEPSInstance().isNull()) {
            throw new NotNullArgumentException(FechaIniEPSConstants.SERV_ID_ELIMINAR, FechaIniEPSConstants.CLASS_ID, FechaIniEPSConstants.ARG_ID_ELIMINAR_FECHAINIEPSELIMINARPTHISFECHAINIEPS, FechaIniEPSConstants.SERV_ALIAS_ELIMINAR, FechaIniEPSConstants.CLASS_ALIAS, FechaIniEPSConstants.ARG_ALIAS_ELIMINAR_FECHAINIEPSELIMINARPTHISFECHAINIEPS);
        }
    }
}
