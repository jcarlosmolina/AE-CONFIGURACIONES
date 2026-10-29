package com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PeriodicidadConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;

public class ModificarRequest {

    @JsonProperty("p_thisperiodicidad_oid")
    private PeriodicidadOid periodicidadmodificarpthisPeriodicidad;
    
    @JsonIgnore
    private Periodicidad periodicidadmodificarpthisPeriodicidadInstance;

    @JsonProperty("p_esmensual")
    private Boolean periodicidadmodificarpEsMensual;

    public void checkArguments() throws NotNullArgumentException {
        checkPeriodicidadmodificarpthisPeriodicidad();
        checkPeriodicidadmodificarpEsMensual();
    }

    @JsonIgnore
    public PeriodicidadOid getPeriodicidadmodificarpthisPeriodicidad() {
        return this.periodicidadmodificarpthisPeriodicidad;
    }
    
    public void setPeriodicidadmodificarpthisPeriodicidad(PeriodicidadOid periodicidadmodificarpthisPeriodicidad) {
        this.periodicidadmodificarpthisPeriodicidad = periodicidadmodificarpthisPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getPeriodicidadmodificarpthisPeriodicidadInstance() {
        return this.periodicidadmodificarpthisPeriodicidadInstance;
    }
    
    public void setPeriodicidadmodificarpthisPeriodicidadInstance(Periodicidad periodicidadmodificarpthisPeriodicidad) {
        this.periodicidadmodificarpthisPeriodicidadInstance = periodicidadmodificarpthisPeriodicidad;
        this.periodicidadmodificarpthisPeriodicidad = periodicidadmodificarpthisPeriodicidad != null ? periodicidadmodificarpthisPeriodicidad.getOid() : null;
    }

    private void checkPeriodicidadmodificarpthisPeriodicidad() throws NotNullArgumentException {
        if(this.getPeriodicidadmodificarpthisPeriodicidadInstance() == null || this.getPeriodicidadmodificarpthisPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_MODIFICAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_MODIFICAR_PERIODICIDADMODIFICARPTHISPERIODICIDAD, PeriodicidadConstants.SERV_ALIAS_MODIFICAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_MODIFICAR_PERIODICIDADMODIFICARPTHISPERIODICIDAD);
        }
    }

    @JsonIgnore
    public Boolean getPeriodicidadmodificarpEsMensual() {
        return this.periodicidadmodificarpEsMensual;
    }
    
    public void setPeriodicidadmodificarpEsMensual(Boolean periodicidadmodificarpEsMensual) {
        this.periodicidadmodificarpEsMensual = periodicidadmodificarpEsMensual;
    }

    private void checkPeriodicidadmodificarpEsMensual() throws NotNullArgumentException {
        if(this.getPeriodicidadmodificarpEsMensual() == null) {
            throw new NotNullArgumentException(PeriodicidadConstants.SERV_ID_MODIFICAR, PeriodicidadConstants.CLASS_ID, PeriodicidadConstants.ARG_ID_MODIFICAR_PERIODICIDADMODIFICARPESMENSUAL, PeriodicidadConstants.SERV_ALIAS_MODIFICAR, PeriodicidadConstants.CLASS_ALIAS, PeriodicidadConstants.ARG_ALIAS_MODIFICAR_PERIODICIDADMODIFICARPESMENSUAL);
        }
    }
}
