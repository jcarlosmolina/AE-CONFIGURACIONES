package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoAreaMedicaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioInsGrupoAreaMedicapthisDominio;
    
    @JsonIgnore
    private Dominio dominioInsGrupoAreaMedicapthisDominioInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid dominioInsGrupoAreaMedicapevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion dominioInsGrupoAreaMedicapevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioInsGrupoAreaMedicapthisDominio();
        checkDominioInsGrupoAreaMedicapevcGrupoAsignacion();
    }

    @JsonIgnore
    public DominioOid getDominioInsGrupoAreaMedicapthisDominio() {
        return this.dominioInsGrupoAreaMedicapthisDominio;
    }
    
    public void setDominioInsGrupoAreaMedicapthisDominio(DominioOid dominioInsGrupoAreaMedicapthisDominio) {
        this.dominioInsGrupoAreaMedicapthisDominio = dominioInsGrupoAreaMedicapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsGrupoAreaMedicapthisDominioInstance() {
        return this.dominioInsGrupoAreaMedicapthisDominioInstance;
    }
    
    public void setDominioInsGrupoAreaMedicapthisDominioInstance(Dominio dominioInsGrupoAreaMedicapthisDominio) {
        this.dominioInsGrupoAreaMedicapthisDominioInstance = dominioInsGrupoAreaMedicapthisDominio;
        this.dominioInsGrupoAreaMedicapthisDominio = dominioInsGrupoAreaMedicapthisDominio != null ? dominioInsGrupoAreaMedicapthisDominio.getOid() : null;
    }

    private void checkDominioInsGrupoAreaMedicapthisDominio() throws NotNullArgumentException {
        if(this.getDominioInsGrupoAreaMedicapthisDominioInstance() == null || this.getDominioInsGrupoAreaMedicapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSGRUPOAREAMEDICA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSGRUPOAREAMEDICA_DOMINIOINSGRUPOAREAMEDICAPTHISDOMINIO, DominioConstants.SERV_ALIAS_INSGRUPOAREAMEDICA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSGRUPOAREAMEDICA_DOMINIOINSGRUPOAREAMEDICAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioInsGrupoAreaMedicapevcGrupoAsignacion() {
        return this.dominioInsGrupoAreaMedicapevcGrupoAsignacion;
    }
    
    public void setDominioInsGrupoAreaMedicapevcGrupoAsignacion(GrupoAsignacionOid dominioInsGrupoAreaMedicapevcGrupoAsignacion) {
        this.dominioInsGrupoAreaMedicapevcGrupoAsignacion = dominioInsGrupoAreaMedicapevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance() {
        return this.dominioInsGrupoAreaMedicapevcGrupoAsignacionInstance;
    }
    
    public void setDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance(GrupoAsignacion dominioInsGrupoAreaMedicapevcGrupoAsignacion) {
        this.dominioInsGrupoAreaMedicapevcGrupoAsignacionInstance = dominioInsGrupoAreaMedicapevcGrupoAsignacion;
        this.dominioInsGrupoAreaMedicapevcGrupoAsignacion = dominioInsGrupoAreaMedicapevcGrupoAsignacion != null ? dominioInsGrupoAreaMedicapevcGrupoAsignacion.getOid() : null;
    }

    private void checkDominioInsGrupoAreaMedicapevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance() == null || this.getDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSGRUPOAREAMEDICA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSGRUPOAREAMEDICA_DOMINIOINSGRUPOAREAMEDICAPEVCGRUPOASIGNACION, DominioConstants.SERV_ALIAS_INSGRUPOAREAMEDICA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSGRUPOAREAMEDICA_DOMINIOINSGRUPOAREAMEDICAPEVCGRUPOASIGNACION);
        }
    }
}
