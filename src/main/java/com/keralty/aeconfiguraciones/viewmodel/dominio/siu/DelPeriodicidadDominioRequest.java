package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;

public class DelPeriodicidadDominioRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelPeriodicidadDominiopthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelPeriodicidadDominiopthisDominioInstance;

    @JsonProperty("p_evcperiodicidad_json")
    private String dominioDelPeriodicidadDominiopevcPeriodicidad;
    
    @JsonIgnore
    private Periodicidad dominioDelPeriodicidadDominiopevcPeriodicidadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelPeriodicidadDominiopthisDominio();
        checkDominioDelPeriodicidadDominiopevcPeriodicidad();
    }

    @JsonIgnore
    public DominioOid getDominioDelPeriodicidadDominiopthisDominio() {
        return this.dominioDelPeriodicidadDominiopthisDominio;
    }
    
    public void setDominioDelPeriodicidadDominiopthisDominio(DominioOid dominioDelPeriodicidadDominiopthisDominio) {
        this.dominioDelPeriodicidadDominiopthisDominio = dominioDelPeriodicidadDominiopthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelPeriodicidadDominiopthisDominioInstance() {
        return this.dominioDelPeriodicidadDominiopthisDominioInstance;
    }
    
    public void setDominioDelPeriodicidadDominiopthisDominioInstance(Dominio dominioDelPeriodicidadDominiopthisDominio) {
        this.dominioDelPeriodicidadDominiopthisDominioInstance = dominioDelPeriodicidadDominiopthisDominio;
        this.dominioDelPeriodicidadDominiopthisDominio = dominioDelPeriodicidadDominiopthisDominio != null ? dominioDelPeriodicidadDominiopthisDominio.getOid() : null;
    }

    private void checkDominioDelPeriodicidadDominiopthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelPeriodicidadDominiopthisDominioInstance() == null || this.getDominioDelPeriodicidadDominiopthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELPERIODICIDADDOMINIO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELPERIODICIDADDOMINIO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public String getDominioDelPeriodicidadDominiopevcPeriodicidad() {
        return this.dominioDelPeriodicidadDominiopevcPeriodicidad;
    }
    
    public void setDominioDelPeriodicidadDominiopevcPeriodicidad(String dominioDelPeriodicidadDominiopevcPeriodicidad) {
        this.dominioDelPeriodicidadDominiopevcPeriodicidad = dominioDelPeriodicidadDominiopevcPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getDominioDelPeriodicidadDominiopevcPeriodicidadInstance() {
        return this.dominioDelPeriodicidadDominiopevcPeriodicidadInstance;
    }
    
    public void setDominioDelPeriodicidadDominiopevcPeriodicidadInstance(Periodicidad dominioDelPeriodicidadDominiopevcPeriodicidad) {
        this.dominioDelPeriodicidadDominiopevcPeriodicidadInstance = dominioDelPeriodicidadDominiopevcPeriodicidad;
        this.dominioDelPeriodicidadDominiopevcPeriodicidad = dominioDelPeriodicidadDominiopevcPeriodicidad != null ? dominioDelPeriodicidadDominiopevcPeriodicidad.getOid().toJson() : "";
    }

    private void checkDominioDelPeriodicidadDominiopevcPeriodicidad() throws NotNullArgumentException {
        if(this.getDominioDelPeriodicidadDominiopevcPeriodicidadInstance() == null || this.getDominioDelPeriodicidadDominiopevcPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELPERIODICIDADDOMINIO, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPEVCPERIODICIDAD, DominioConstants.SERV_ALIAS_DELPERIODICIDADDOMINIO, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELPERIODICIDADDOMINIO_DOMINIODELPERIODICIDADDOMINIOPEVCPERIODICIDAD);
        }
    }
}
