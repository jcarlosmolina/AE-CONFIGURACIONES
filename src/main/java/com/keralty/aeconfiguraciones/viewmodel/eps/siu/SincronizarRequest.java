package com.keralty.aeconfiguraciones.viewmodel.eps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EpsConstants;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;

public class SincronizarRequest {

    @JsonProperty("p_thiseps_oid")
    private EpsOid epssincronizarpthisEPS;
    
    @JsonIgnore
    private Eps epssincronizarpthisEPSInstance;

    @JsonProperty("p_descripcion")
    private String epssincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEpssincronizarpthisEPS();
        checkEpssincronizarpDescripcion();
    }

    @JsonIgnore
    public EpsOid getEpssincronizarpthisEPS() {
        return this.epssincronizarpthisEPS;
    }
    
    public void setEpssincronizarpthisEPS(EpsOid epssincronizarpthisEPS) {
        this.epssincronizarpthisEPS = epssincronizarpthisEPS;
    }

    @JsonIgnore
    public Eps getEpssincronizarpthisEPSInstance() {
        return this.epssincronizarpthisEPSInstance;
    }
    
    public void setEpssincronizarpthisEPSInstance(Eps epssincronizarpthisEPS) {
        this.epssincronizarpthisEPSInstance = epssincronizarpthisEPS;
        this.epssincronizarpthisEPS = epssincronizarpthisEPS != null ? epssincronizarpthisEPS.getOid() : null;
    }

    private void checkEpssincronizarpthisEPS() throws NotNullArgumentException {
        if(this.getEpssincronizarpthisEPSInstance() == null || this.getEpssincronizarpthisEPSInstance().isNull()) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_SINCRONIZAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_SINCRONIZAR_EPSSINCRONIZARPTHISEPS, EpsConstants.SERV_ALIAS_SINCRONIZAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_SINCRONIZAR_EPSSINCRONIZARPTHISEPS);
        }
    }

    @JsonIgnore
    public String getEpssincronizarpDescripcion() {
        return this.epssincronizarpDescripcion;
    }
    
    public void setEpssincronizarpDescripcion(String epssincronizarpDescripcion) {
        this.epssincronizarpDescripcion = epssincronizarpDescripcion;
    }

    private void checkEpssincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getEpssincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_SINCRONIZAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_SINCRONIZAR_EPSSINCRONIZARPDESCRIPCION, EpsConstants.SERV_ALIAS_SINCRONIZAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_SINCRONIZAR_EPSSINCRONIZARPDESCRIPCION);
        }
    }
}
