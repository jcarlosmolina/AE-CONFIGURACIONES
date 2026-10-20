package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class DelNovedadRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelNovedadpthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelNovedadpthisDominioInstance;

    @JsonProperty("p_evcnovedad_oid")
    private NovedadOid dominioDelNovedadpevcNovedad;
    
    @JsonIgnore
    private Novedad dominioDelNovedadpevcNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelNovedadpthisDominio();
        checkDominioDelNovedadpevcNovedad();
    }

    @JsonIgnore
    public DominioOid getDominioDelNovedadpthisDominio() {
        return this.dominioDelNovedadpthisDominio;
    }
    
    public void setDominioDelNovedadpthisDominio(DominioOid dominioDelNovedadpthisDominio) {
        this.dominioDelNovedadpthisDominio = dominioDelNovedadpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelNovedadpthisDominioInstance() {
        return this.dominioDelNovedadpthisDominioInstance;
    }
    
    public void setDominioDelNovedadpthisDominioInstance(Dominio dominioDelNovedadpthisDominio) {
        this.dominioDelNovedadpthisDominioInstance = dominioDelNovedadpthisDominio;
        this.dominioDelNovedadpthisDominio = dominioDelNovedadpthisDominio != null ? dominioDelNovedadpthisDominio.getOid() : null;
    }

    private void checkDominioDelNovedadpthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelNovedadpthisDominioInstance() == null || this.getDominioDelNovedadpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELNOVEDAD_DOMINIODELNOVEDADPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELNOVEDAD_DOMINIODELNOVEDADPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public NovedadOid getDominioDelNovedadpevcNovedad() {
        return this.dominioDelNovedadpevcNovedad;
    }
    
    public void setDominioDelNovedadpevcNovedad(NovedadOid dominioDelNovedadpevcNovedad) {
        this.dominioDelNovedadpevcNovedad = dominioDelNovedadpevcNovedad;
    }

    @JsonIgnore
    public Novedad getDominioDelNovedadpevcNovedadInstance() {
        return this.dominioDelNovedadpevcNovedadInstance;
    }
    
    public void setDominioDelNovedadpevcNovedadInstance(Novedad dominioDelNovedadpevcNovedad) {
        this.dominioDelNovedadpevcNovedadInstance = dominioDelNovedadpevcNovedad;
        this.dominioDelNovedadpevcNovedad = dominioDelNovedadpevcNovedad != null ? dominioDelNovedadpevcNovedad.getOid() : null;
    }

    private void checkDominioDelNovedadpevcNovedad() throws NotNullArgumentException {
        if(this.getDominioDelNovedadpevcNovedadInstance() == null || this.getDominioDelNovedadpevcNovedadInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELNOVEDAD_DOMINIODELNOVEDADPEVCNOVEDAD, DominioConstants.SERV_ALIAS_DELNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELNOVEDAD_DOMINIODELNOVEDADPEVCNOVEDAD);
        }
    }
}
