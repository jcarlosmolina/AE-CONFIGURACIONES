package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class InsNovedadRequest {

    @JsonProperty("p_thisnovedad_oid")
    private NovedadOid novedadInsNovedadpthisNovedad;
    
    @JsonIgnore
    private Novedad novedadInsNovedadpthisNovedadInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid novedadInsNovedadpevcDominio;
    
    @JsonIgnore
    private Dominio novedadInsNovedadpevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadInsNovedadpthisNovedad();
        checkNovedadInsNovedadpevcDominio();
    }

    @JsonIgnore
    public NovedadOid getNovedadInsNovedadpthisNovedad() {
        return this.novedadInsNovedadpthisNovedad;
    }
    
    public void setNovedadInsNovedadpthisNovedad(NovedadOid novedadInsNovedadpthisNovedad) {
        this.novedadInsNovedadpthisNovedad = novedadInsNovedadpthisNovedad;
    }

    @JsonIgnore
    public Novedad getNovedadInsNovedadpthisNovedadInstance() {
        return this.novedadInsNovedadpthisNovedadInstance;
    }
    
    public void setNovedadInsNovedadpthisNovedadInstance(Novedad novedadInsNovedadpthisNovedad) {
        this.novedadInsNovedadpthisNovedadInstance = novedadInsNovedadpthisNovedad;
        this.novedadInsNovedadpthisNovedad = novedadInsNovedadpthisNovedad != null ? novedadInsNovedadpthisNovedad.getOid() : null;
    }

    private void checkNovedadInsNovedadpthisNovedad() throws NotNullArgumentException {
        if(this.getNovedadInsNovedadpthisNovedadInstance() == null || this.getNovedadInsNovedadpthisNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_INSNOVEDAD, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_INSNOVEDAD_NOVEDADINSNOVEDADPTHISNOVEDAD, NovedadConstants.SERV_ALIAS_INSNOVEDAD, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_INSNOVEDAD_NOVEDADINSNOVEDADPTHISNOVEDAD);
        }
    }

    @JsonIgnore
    public DominioOid getNovedadInsNovedadpevcDominio() {
        return this.novedadInsNovedadpevcDominio;
    }
    
    public void setNovedadInsNovedadpevcDominio(DominioOid novedadInsNovedadpevcDominio) {
        this.novedadInsNovedadpevcDominio = novedadInsNovedadpevcDominio;
    }

    @JsonIgnore
    public Dominio getNovedadInsNovedadpevcDominioInstance() {
        return this.novedadInsNovedadpevcDominioInstance;
    }
    
    public void setNovedadInsNovedadpevcDominioInstance(Dominio novedadInsNovedadpevcDominio) {
        this.novedadInsNovedadpevcDominioInstance = novedadInsNovedadpevcDominio;
        this.novedadInsNovedadpevcDominio = novedadInsNovedadpevcDominio != null ? novedadInsNovedadpevcDominio.getOid() : null;
    }

    private void checkNovedadInsNovedadpevcDominio() throws NotNullArgumentException {
        if(this.getNovedadInsNovedadpevcDominioInstance() == null || this.getNovedadInsNovedadpevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_INSNOVEDAD, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_INSNOVEDAD_NOVEDADINSNOVEDADPEVCDOMINIO, NovedadConstants.SERV_ALIAS_INSNOVEDAD, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_INSNOVEDAD_NOVEDADINSNOVEDADPEVCDOMINIO);
        }
    }
}
