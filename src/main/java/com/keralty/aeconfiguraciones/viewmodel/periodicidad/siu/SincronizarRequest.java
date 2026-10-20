package com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;

public class SincronizarRequest {

    @JsonProperty("p_thisperiodicidad_oid")
    private PeriodicidadOid periodicidadsincronizarpthisPeriodicidad;
    
    @JsonIgnore
    private Periodicidad periodicidadsincronizarpthisPeriodicidadInstance;

    @JsonProperty("p_descripcion")
    private String periodicidadsincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkPeriodicidadsincronizarpthisPeriodicidad();
        checkPeriodicidadsincronizarpDescripcion();
    }

    @JsonIgnore
    public PeriodicidadOid getPeriodicidadsincronizarpthisPeriodicidad() {
        return this.periodicidadsincronizarpthisPeriodicidad;
    }
    
    public void setPeriodicidadsincronizarpthisPeriodicidad(PeriodicidadOid periodicidadsincronizarpthisPeriodicidad) {
        this.periodicidadsincronizarpthisPeriodicidad = periodicidadsincronizarpthisPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getPeriodicidadsincronizarpthisPeriodicidadInstance() {
        return this.periodicidadsincronizarpthisPeriodicidadInstance;
    }
    
    public void setPeriodicidadsincronizarpthisPeriodicidadInstance(Periodicidad periodicidadsincronizarpthisPeriodicidad) {
        this.periodicidadsincronizarpthisPeriodicidadInstance = periodicidadsincronizarpthisPeriodicidad;
        this.periodicidadsincronizarpthisPeriodicidad = periodicidadsincronizarpthisPeriodicidad != null ? periodicidadsincronizarpthisPeriodicidad.getOid() : null;
    }

    private void checkPeriodicidadsincronizarpthisPeriodicidad() throws NotNullArgumentException {
        if(this.getPeriodicidadsincronizarpthisPeriodicidadInstance() == null || this.getPeriodicidadsincronizarpthisPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_SINCRONIZAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_SINCRONIZAR_PERIODICIDADSINCRONIZARPTHISPERIODICIDAD, PeriodicidadConstants.SERV_ALIAS_SINCRONIZAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_SINCRONIZAR_PERIODICIDADSINCRONIZARPTHISPERIODICIDAD);
        }
    }

    @JsonIgnore
    public String getPeriodicidadsincronizarpDescripcion() {
        return this.periodicidadsincronizarpDescripcion;
    }
    
    public void setPeriodicidadsincronizarpDescripcion(String periodicidadsincronizarpDescripcion) {
        this.periodicidadsincronizarpDescripcion = periodicidadsincronizarpDescripcion;
    }

    private void checkPeriodicidadsincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getPeriodicidadsincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_SINCRONIZAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_SINCRONIZAR_PERIODICIDADSINCRONIZARPDESCRIPCION, PeriodicidadConstants.SERV_ALIAS_SINCRONIZAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_SINCRONIZAR_PERIODICIDADSINCRONIZARPDESCRIPCION);
        }
    }
}
