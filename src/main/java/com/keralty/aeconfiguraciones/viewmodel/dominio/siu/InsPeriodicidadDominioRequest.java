package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;

public class InsPeriodicidadDominioRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioInsPeriodicidadDominiopthisDominio;
    
    @JsonIgnore
    private Dominio dominioInsPeriodicidadDominiopthisDominioInstance;

    @JsonProperty("p_evcperiodicidad_oid")
    private PeriodicidadOid dominioInsPeriodicidadDominiopevcPeriodicidad;
    
    @JsonIgnore
    private Periodicidad dominioInsPeriodicidadDominiopevcPeriodicidadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioInsPeriodicidadDominiopthisDominio();
        checkDominioInsPeriodicidadDominiopevcPeriodicidad();
    }

    @JsonIgnore
    public DominioOid getDominioInsPeriodicidadDominiopthisDominio() {
        return this.dominioInsPeriodicidadDominiopthisDominio;
    }
    
    public void setDominioInsPeriodicidadDominiopthisDominio(DominioOid dominioInsPeriodicidadDominiopthisDominio) {
        this.dominioInsPeriodicidadDominiopthisDominio = dominioInsPeriodicidadDominiopthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsPeriodicidadDominiopthisDominioInstance() {
        return this.dominioInsPeriodicidadDominiopthisDominioInstance;
    }
    
    public void setDominioInsPeriodicidadDominiopthisDominioInstance(Dominio dominioInsPeriodicidadDominiopthisDominio) {
        this.dominioInsPeriodicidadDominiopthisDominioInstance = dominioInsPeriodicidadDominiopthisDominio;
        this.dominioInsPeriodicidadDominiopthisDominio = dominioInsPeriodicidadDominiopthisDominio != null ? dominioInsPeriodicidadDominiopthisDominio.getOid() : null;
    }

    private void checkDominioInsPeriodicidadDominiopthisDominio() throws NotNullArgumentException {
        if(this.getDominioInsPeriodicidadDominiopthisDominioInstance() == null || this.getDominioInsPeriodicidadDominiopthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSPERIODICIDADDOMINIO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPTHISDOMINIO, DominioConstants.SERV_ALIAS_INSPERIODICIDADDOMINIO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public PeriodicidadOid getDominioInsPeriodicidadDominiopevcPeriodicidad() {
        return this.dominioInsPeriodicidadDominiopevcPeriodicidad;
    }
    
    public void setDominioInsPeriodicidadDominiopevcPeriodicidad(PeriodicidadOid dominioInsPeriodicidadDominiopevcPeriodicidad) {
        this.dominioInsPeriodicidadDominiopevcPeriodicidad = dominioInsPeriodicidadDominiopevcPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getDominioInsPeriodicidadDominiopevcPeriodicidadInstance() {
        return this.dominioInsPeriodicidadDominiopevcPeriodicidadInstance;
    }
    
    public void setDominioInsPeriodicidadDominiopevcPeriodicidadInstance(Periodicidad dominioInsPeriodicidadDominiopevcPeriodicidad) {
        this.dominioInsPeriodicidadDominiopevcPeriodicidadInstance = dominioInsPeriodicidadDominiopevcPeriodicidad;
        this.dominioInsPeriodicidadDominiopevcPeriodicidad = dominioInsPeriodicidadDominiopevcPeriodicidad != null ? dominioInsPeriodicidadDominiopevcPeriodicidad.getOid() : null;
    }

    private void checkDominioInsPeriodicidadDominiopevcPeriodicidad() throws NotNullArgumentException {
        if(this.getDominioInsPeriodicidadDominiopevcPeriodicidadInstance() == null || this.getDominioInsPeriodicidadDominiopevcPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSPERIODICIDADDOMINIO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPEVCPERIODICIDAD, DominioConstants.SERV_ALIAS_INSPERIODICIDADDOMINIO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSPERIODICIDADDOMINIO_DOMINIOINSPERIODICIDADDOMINIOPEVCPERIODICIDAD);
        }
    }
}
