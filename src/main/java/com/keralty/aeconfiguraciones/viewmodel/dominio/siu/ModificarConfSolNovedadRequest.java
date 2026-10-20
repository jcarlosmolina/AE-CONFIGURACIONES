package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class ModificarConfSolNovedadRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominiomodificarConfSolNovedadpthisDominio;
    
    @JsonIgnore
    private Dominio dominiomodificarConfSolNovedadpthisDominioInstance;

    @JsonProperty("p_novexcepcionreqdoc")
    private Boolean dominiomodificarConfSolNovedadpNovExcepcionReqDoc;

    @JsonProperty("p_solnovcoloroscuro")
    private String dominiomodificarConfSolNovedadpSolNovColorOscuro;

    @JsonProperty("p_solnovcolorclaro")
    private String dominiomodificarConfSolNovedadpSolNovColorClaro;

    @JsonProperty("p_solnovlogo")
    private String dominiomodificarConfSolNovedadpSolNovLogo;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiomodificarConfSolNovedadpthisDominio();
        checkDominiomodificarConfSolNovedadpNovExcepcionReqDoc();
    }

    @JsonIgnore
    public DominioOid getDominiomodificarConfSolNovedadpthisDominio() {
        return this.dominiomodificarConfSolNovedadpthisDominio;
    }
    
    public void setDominiomodificarConfSolNovedadpthisDominio(DominioOid dominiomodificarConfSolNovedadpthisDominio) {
        this.dominiomodificarConfSolNovedadpthisDominio = dominiomodificarConfSolNovedadpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominiomodificarConfSolNovedadpthisDominioInstance() {
        return this.dominiomodificarConfSolNovedadpthisDominioInstance;
    }
    
    public void setDominiomodificarConfSolNovedadpthisDominioInstance(Dominio dominiomodificarConfSolNovedadpthisDominio) {
        this.dominiomodificarConfSolNovedadpthisDominioInstance = dominiomodificarConfSolNovedadpthisDominio;
        this.dominiomodificarConfSolNovedadpthisDominio = dominiomodificarConfSolNovedadpthisDominio != null ? dominiomodificarConfSolNovedadpthisDominio.getOid() : null;
    }

    private void checkDominiomodificarConfSolNovedadpthisDominio() throws NotNullArgumentException {
        if(this.getDominiomodificarConfSolNovedadpthisDominioInstance() == null || this.getDominiomodificarConfSolNovedadpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICARCONFSOLNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICARCONFSOLNOVEDAD_DOMINIOMODIFICARCONFSOLNOVEDADPTHISDOMINIO, DominioConstants.SERV_ALIAS_MODIFICARCONFSOLNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICARCONFSOLNOVEDAD_DOMINIOMODIFICARCONFSOLNOVEDADPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getDominiomodificarConfSolNovedadpNovExcepcionReqDoc() {
        return this.dominiomodificarConfSolNovedadpNovExcepcionReqDoc;
    }
    
    public void setDominiomodificarConfSolNovedadpNovExcepcionReqDoc(Boolean dominiomodificarConfSolNovedadpNovExcepcionReqDoc) {
        this.dominiomodificarConfSolNovedadpNovExcepcionReqDoc = dominiomodificarConfSolNovedadpNovExcepcionReqDoc;
    }

    private void checkDominiomodificarConfSolNovedadpNovExcepcionReqDoc() throws NotNullArgumentException {
        if(this.getDominiomodificarConfSolNovedadpNovExcepcionReqDoc() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_MODIFICARCONFSOLNOVEDAD, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_MODIFICARCONFSOLNOVEDAD_DOMINIOMODIFICARCONFSOLNOVEDADPNOVEXCEPCIONREQDOC, DominioConstants.SERV_ALIAS_MODIFICARCONFSOLNOVEDAD, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_MODIFICARCONFSOLNOVEDAD_DOMINIOMODIFICARCONFSOLNOVEDADPNOVEXCEPCIONREQDOC);
        }
    }

    @JsonIgnore
    public String getDominiomodificarConfSolNovedadpSolNovColorOscuro() {
        return this.dominiomodificarConfSolNovedadpSolNovColorOscuro;
    }
    
    public void setDominiomodificarConfSolNovedadpSolNovColorOscuro(String dominiomodificarConfSolNovedadpSolNovColorOscuro) {
        this.dominiomodificarConfSolNovedadpSolNovColorOscuro = dominiomodificarConfSolNovedadpSolNovColorOscuro;
    }

    @JsonIgnore
    public String getDominiomodificarConfSolNovedadpSolNovColorClaro() {
        return this.dominiomodificarConfSolNovedadpSolNovColorClaro;
    }
    
    public void setDominiomodificarConfSolNovedadpSolNovColorClaro(String dominiomodificarConfSolNovedadpSolNovColorClaro) {
        this.dominiomodificarConfSolNovedadpSolNovColorClaro = dominiomodificarConfSolNovedadpSolNovColorClaro;
    }

    @JsonIgnore
    public String getDominiomodificarConfSolNovedadpSolNovLogo() {
        return this.dominiomodificarConfSolNovedadpSolNovLogo;
    }
    
    public void setDominiomodificarConfSolNovedadpSolNovLogo(String dominiomodificarConfSolNovedadpSolNovLogo) {
        this.dominiomodificarConfSolNovedadpSolNovLogo = dominiomodificarConfSolNovedadpSolNovLogo;
    }
}
