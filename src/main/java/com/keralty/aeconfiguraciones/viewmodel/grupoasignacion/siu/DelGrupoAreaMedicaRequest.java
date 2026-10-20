package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class DelGrupoAreaMedicaRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid grupoAsignacionDelGrupoAreaMedicapevcDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionDelGrupoAreaMedicapevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion();
        checkGrupoAsignacionDelGrupoAreaMedicapevcDominio();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion() {
        return this.grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion = grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance() {
        return this.grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance = grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion;
        this.grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion = grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion != null ? grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOAREAMEDICA_GRUPOASIGNACIONDELGRUPOAREAMEDICAPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOAREAMEDICA_GRUPOASIGNACIONDELGRUPOAREAMEDICAPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionDelGrupoAreaMedicapevcDominio() {
        return this.grupoAsignacionDelGrupoAreaMedicapevcDominio;
    }
    
    public void setGrupoAsignacionDelGrupoAreaMedicapevcDominio(DominioOid grupoAsignacionDelGrupoAreaMedicapevcDominio) {
        this.grupoAsignacionDelGrupoAreaMedicapevcDominio = grupoAsignacionDelGrupoAreaMedicapevcDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance() {
        return this.grupoAsignacionDelGrupoAreaMedicapevcDominioInstance;
    }
    
    public void setGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance(Dominio grupoAsignacionDelGrupoAreaMedicapevcDominio) {
        this.grupoAsignacionDelGrupoAreaMedicapevcDominioInstance = grupoAsignacionDelGrupoAreaMedicapevcDominio;
        this.grupoAsignacionDelGrupoAreaMedicapevcDominio = grupoAsignacionDelGrupoAreaMedicapevcDominio != null ? grupoAsignacionDelGrupoAreaMedicapevcDominio.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoAreaMedicapevcDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance() == null || this.getGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOAREAMEDICA_GRUPOASIGNACIONDELGRUPOAREAMEDICAPEVCDOMINIO, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOAREAMEDICA_GRUPOASIGNACIONDELGRUPOAREAMEDICAPEVCDOMINIO);
        }
    }
}
