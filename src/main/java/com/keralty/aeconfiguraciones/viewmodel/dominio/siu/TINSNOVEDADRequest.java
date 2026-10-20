package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;

public class TINSNOVEDADRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTINSNOVEDADpthisDominio;
    
    @JsonIgnore
    private Dominio dominioTINSNOVEDADpthisDominioInstance;

    @JsonProperty("pt_p_evcnovedad_json")
    private String dominioTINSNOVEDADptpevcNovedad;
    
    @JsonIgnore
    private Novedad dominioTINSNOVEDADptpevcNovedadInstance;

    @JsonProperty("pttipo_json")
    private String dominioTINSNOVEDADptTipo;
    
    @JsonIgnore
    private TipoNovedad dominioTINSNOVEDADptTipoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTINSNOVEDADpthisDominio();
        checkDominioTINSNOVEDADptpevcNovedad();
        checkDominioTINSNOVEDADptTipo();
    }

    @JsonIgnore
    public DominioOid getDominioTINSNOVEDADpthisDominio() {
        return this.dominioTINSNOVEDADpthisDominio;
    }
    
    public void setDominioTINSNOVEDADpthisDominio(DominioOid dominioTINSNOVEDADpthisDominio) {
        this.dominioTINSNOVEDADpthisDominio = dominioTINSNOVEDADpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTINSNOVEDADpthisDominioInstance() {
        return this.dominioTINSNOVEDADpthisDominioInstance;
    }
    
    public void setDominioTINSNOVEDADpthisDominioInstance(Dominio dominioTINSNOVEDADpthisDominio) {
        this.dominioTINSNOVEDADpthisDominioInstance = dominioTINSNOVEDADpthisDominio;
        this.dominioTINSNOVEDADpthisDominio = dominioTINSNOVEDADpthisDominio != null ? dominioTINSNOVEDADpthisDominio.getOid() : null;
    }

    private void checkDominioTINSNOVEDADpthisDominio() throws NotNullArgumentException {
        if(this.getDominioTINSNOVEDADpthisDominioInstance() == null || this.getDominioTINSNOVEDADpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSNOVEDAD_DOMINIOTINSNOVEDADPTHISDOMINIO, DominioConstants.SERV_ALIAS_TINSNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSNOVEDAD_DOMINIOTINSNOVEDADPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public String getDominioTINSNOVEDADptpevcNovedad() {
        return this.dominioTINSNOVEDADptpevcNovedad;
    }
    
    public void setDominioTINSNOVEDADptpevcNovedad(String dominioTINSNOVEDADptpevcNovedad) {
        this.dominioTINSNOVEDADptpevcNovedad = dominioTINSNOVEDADptpevcNovedad;
    }

    @JsonIgnore
    public Novedad getDominioTINSNOVEDADptpevcNovedadInstance() {
        return this.dominioTINSNOVEDADptpevcNovedadInstance;
    }
    
    public void setDominioTINSNOVEDADptpevcNovedadInstance(Novedad dominioTINSNOVEDADptpevcNovedad) {
        this.dominioTINSNOVEDADptpevcNovedadInstance = dominioTINSNOVEDADptpevcNovedad;
        this.dominioTINSNOVEDADptpevcNovedad = dominioTINSNOVEDADptpevcNovedad != null ? dominioTINSNOVEDADptpevcNovedad.getOid().toJson() : "";
    }

    private void checkDominioTINSNOVEDADptpevcNovedad() throws NotNullArgumentException {
        if(this.getDominioTINSNOVEDADptpevcNovedadInstance() == null || this.getDominioTINSNOVEDADptpevcNovedadInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSNOVEDAD_DOMINIOTINSNOVEDADPTPEVCNOVEDAD, DominioConstants.SERV_ALIAS_TINSNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSNOVEDAD_DOMINIOTINSNOVEDADPTPEVCNOVEDAD);
        }
    }

    @JsonIgnore
    public String getDominioTINSNOVEDADptTipo() {
        return this.dominioTINSNOVEDADptTipo;
    }
    
    public void setDominioTINSNOVEDADptTipo(String dominioTINSNOVEDADptTipo) {
        this.dominioTINSNOVEDADptTipo = dominioTINSNOVEDADptTipo;
    }

    @JsonIgnore
    public TipoNovedad getDominioTINSNOVEDADptTipoInstance() {
        return this.dominioTINSNOVEDADptTipoInstance;
    }
    
    public void setDominioTINSNOVEDADptTipoInstance(TipoNovedad dominioTINSNOVEDADptTipo) {
        this.dominioTINSNOVEDADptTipoInstance = dominioTINSNOVEDADptTipo;
        this.dominioTINSNOVEDADptTipo = dominioTINSNOVEDADptTipo != null ? dominioTINSNOVEDADptTipo.getOid().toJson() : "";
    }

    private void checkDominioTINSNOVEDADptTipo() throws NotNullArgumentException {
        if(this.getDominioTINSNOVEDADptTipoInstance() == null || this.getDominioTINSNOVEDADptTipoInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO, DominioConstants.SERV_ALIAS_TINSNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSNOVEDAD_DOMINIOTINSNOVEDADPTTIPO);
        }
    }
}
