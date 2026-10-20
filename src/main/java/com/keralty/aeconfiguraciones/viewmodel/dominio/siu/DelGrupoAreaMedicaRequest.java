package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class DelGrupoAreaMedicaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioDelGrupoAreaMedicapthisDominio;
    
    @JsonIgnore
    private Dominio dominioDelGrupoAreaMedicapthisDominioInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid dominioDelGrupoAreaMedicapevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion dominioDelGrupoAreaMedicapevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioDelGrupoAreaMedicapthisDominio();
        checkDominioDelGrupoAreaMedicapevcGrupoAsignacion();
    }

    @JsonIgnore
    public DominioOid getDominioDelGrupoAreaMedicapthisDominio() {
        return this.dominioDelGrupoAreaMedicapthisDominio;
    }
    
    public void setDominioDelGrupoAreaMedicapthisDominio(DominioOid dominioDelGrupoAreaMedicapthisDominio) {
        this.dominioDelGrupoAreaMedicapthisDominio = dominioDelGrupoAreaMedicapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioDelGrupoAreaMedicapthisDominioInstance() {
        return this.dominioDelGrupoAreaMedicapthisDominioInstance;
    }
    
    public void setDominioDelGrupoAreaMedicapthisDominioInstance(Dominio dominioDelGrupoAreaMedicapthisDominio) {
        this.dominioDelGrupoAreaMedicapthisDominioInstance = dominioDelGrupoAreaMedicapthisDominio;
        this.dominioDelGrupoAreaMedicapthisDominio = dominioDelGrupoAreaMedicapthisDominio != null ? dominioDelGrupoAreaMedicapthisDominio.getOid() : null;
    }

    private void checkDominioDelGrupoAreaMedicapthisDominio() throws NotNullArgumentException {
        if(this.getDominioDelGrupoAreaMedicapthisDominioInstance() == null || this.getDominioDelGrupoAreaMedicapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELGRUPOAREAMEDICA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELGRUPOAREAMEDICA_DOMINIODELGRUPOAREAMEDICAPTHISDOMINIO, DominioConstants.SERV_ALIAS_DELGRUPOAREAMEDICA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELGRUPOAREAMEDICA_DOMINIODELGRUPOAREAMEDICAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioDelGrupoAreaMedicapevcGrupoAsignacion() {
        return this.dominioDelGrupoAreaMedicapevcGrupoAsignacion;
    }
    
    public void setDominioDelGrupoAreaMedicapevcGrupoAsignacion(GrupoAsignacionOid dominioDelGrupoAreaMedicapevcGrupoAsignacion) {
        this.dominioDelGrupoAreaMedicapevcGrupoAsignacion = dominioDelGrupoAreaMedicapevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance() {
        return this.dominioDelGrupoAreaMedicapevcGrupoAsignacionInstance;
    }
    
    public void setDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance(GrupoAsignacion dominioDelGrupoAreaMedicapevcGrupoAsignacion) {
        this.dominioDelGrupoAreaMedicapevcGrupoAsignacionInstance = dominioDelGrupoAreaMedicapevcGrupoAsignacion;
        this.dominioDelGrupoAreaMedicapevcGrupoAsignacion = dominioDelGrupoAreaMedicapevcGrupoAsignacion != null ? dominioDelGrupoAreaMedicapevcGrupoAsignacion.getOid() : null;
    }

    private void checkDominioDelGrupoAreaMedicapevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance() == null || this.getDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_DELGRUPOAREAMEDICA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_DELGRUPOAREAMEDICA_DOMINIODELGRUPOAREAMEDICAPEVCGRUPOASIGNACION, DominioConstants.SERV_ALIAS_DELGRUPOAREAMEDICA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_DELGRUPOAREAMEDICA_DOMINIODELGRUPOAREAMEDICAPEVCGRUPOASIGNACION);
        }
    }
}
