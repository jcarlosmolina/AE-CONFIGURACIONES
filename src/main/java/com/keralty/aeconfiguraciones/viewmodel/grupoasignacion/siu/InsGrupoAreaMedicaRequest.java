package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoAreaMedicaRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid grupoAsignacionInsGrupoAreaMedicapevcDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionInsGrupoAreaMedicapevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion();
        checkGrupoAsignacionInsGrupoAreaMedicapevcDominio();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion() {
        return this.grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion = grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance() {
        return this.grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance = grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion;
        this.grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion = grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion != null ? grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOAREAMEDICA_GRUPOASIGNACIONINSGRUPOAREAMEDICAPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOAREAMEDICA_GRUPOASIGNACIONINSGRUPOAREAMEDICAPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionInsGrupoAreaMedicapevcDominio() {
        return this.grupoAsignacionInsGrupoAreaMedicapevcDominio;
    }
    
    public void setGrupoAsignacionInsGrupoAreaMedicapevcDominio(DominioOid grupoAsignacionInsGrupoAreaMedicapevcDominio) {
        this.grupoAsignacionInsGrupoAreaMedicapevcDominio = grupoAsignacionInsGrupoAreaMedicapevcDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance() {
        return this.grupoAsignacionInsGrupoAreaMedicapevcDominioInstance;
    }
    
    public void setGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance(Dominio grupoAsignacionInsGrupoAreaMedicapevcDominio) {
        this.grupoAsignacionInsGrupoAreaMedicapevcDominioInstance = grupoAsignacionInsGrupoAreaMedicapevcDominio;
        this.grupoAsignacionInsGrupoAreaMedicapevcDominio = grupoAsignacionInsGrupoAreaMedicapevcDominio != null ? grupoAsignacionInsGrupoAreaMedicapevcDominio.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoAreaMedicapevcDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance() == null || this.getGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOAREAMEDICA_GRUPOASIGNACIONINSGRUPOAREAMEDICAPEVCDOMINIO, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOAREAMEDICA, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOAREAMEDICA_GRUPOASIGNACIONINSGRUPOAREAMEDICAPEVCDOMINIO);
        }
    }
}
