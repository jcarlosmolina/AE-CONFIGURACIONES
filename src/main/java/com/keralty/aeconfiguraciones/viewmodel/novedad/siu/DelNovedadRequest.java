package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class DelNovedadRequest {

    @JsonProperty("p_thisnovedad_oid")
    private NovedadOid novedadDelNovedadpthisNovedad;
    
    @JsonIgnore
    private Novedad novedadDelNovedadpthisNovedadInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid novedadDelNovedadpevcDominio;
    
    @JsonIgnore
    private Dominio novedadDelNovedadpevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadDelNovedadpthisNovedad();
        checkNovedadDelNovedadpevcDominio();
    }

    @JsonIgnore
    public NovedadOid getNovedadDelNovedadpthisNovedad() {
        return this.novedadDelNovedadpthisNovedad;
    }
    
    public void setNovedadDelNovedadpthisNovedad(NovedadOid novedadDelNovedadpthisNovedad) {
        this.novedadDelNovedadpthisNovedad = novedadDelNovedadpthisNovedad;
    }

    @JsonIgnore
    public Novedad getNovedadDelNovedadpthisNovedadInstance() {
        return this.novedadDelNovedadpthisNovedadInstance;
    }
    
    public void setNovedadDelNovedadpthisNovedadInstance(Novedad novedadDelNovedadpthisNovedad) {
        this.novedadDelNovedadpthisNovedadInstance = novedadDelNovedadpthisNovedad;
        this.novedadDelNovedadpthisNovedad = novedadDelNovedadpthisNovedad != null ? novedadDelNovedadpthisNovedad.getOid() : null;
    }

    private void checkNovedadDelNovedadpthisNovedad() throws NotNullArgumentException {
        if(this.getNovedadDelNovedadpthisNovedadInstance() == null || this.getNovedadDelNovedadpthisNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_DELNOVEDAD, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_DELNOVEDAD_NOVEDADDELNOVEDADPTHISNOVEDAD, NovedadConstants.SERV_ALIAS_DELNOVEDAD, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_DELNOVEDAD_NOVEDADDELNOVEDADPTHISNOVEDAD);
        }
    }

    @JsonIgnore
    public DominioOid getNovedadDelNovedadpevcDominio() {
        return this.novedadDelNovedadpevcDominio;
    }
    
    public void setNovedadDelNovedadpevcDominio(DominioOid novedadDelNovedadpevcDominio) {
        this.novedadDelNovedadpevcDominio = novedadDelNovedadpevcDominio;
    }

    @JsonIgnore
    public Dominio getNovedadDelNovedadpevcDominioInstance() {
        return this.novedadDelNovedadpevcDominioInstance;
    }
    
    public void setNovedadDelNovedadpevcDominioInstance(Dominio novedadDelNovedadpevcDominio) {
        this.novedadDelNovedadpevcDominioInstance = novedadDelNovedadpevcDominio;
        this.novedadDelNovedadpevcDominio = novedadDelNovedadpevcDominio != null ? novedadDelNovedadpevcDominio.getOid() : null;
    }

    private void checkNovedadDelNovedadpevcDominio() throws NotNullArgumentException {
        if(this.getNovedadDelNovedadpevcDominioInstance() == null || this.getNovedadDelNovedadpevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_DELNOVEDAD, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_DELNOVEDAD_NOVEDADDELNOVEDADPEVCDOMINIO, NovedadConstants.SERV_ALIAS_DELNOVEDAD, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_DELNOVEDAD_NOVEDADDELNOVEDADPEVCDOMINIO);
        }
    }
}
