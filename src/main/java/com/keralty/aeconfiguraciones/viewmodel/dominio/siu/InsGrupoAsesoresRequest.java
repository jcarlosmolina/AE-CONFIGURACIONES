package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoAsesoresRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioInsGrupoAsesorespthisDominio;
    
    @JsonIgnore
    private Dominio dominioInsGrupoAsesorespthisDominioInstance;

    @JsonProperty("p_evcgrupoasignacion_oid")
    private GrupoAsignacionOid dominioInsGrupoAsesorespevcGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion dominioInsGrupoAsesorespevcGrupoAsignacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioInsGrupoAsesorespthisDominio();
        checkDominioInsGrupoAsesorespevcGrupoAsignacion();
    }

    @JsonIgnore
    public DominioOid getDominioInsGrupoAsesorespthisDominio() {
        return this.dominioInsGrupoAsesorespthisDominio;
    }
    
    public void setDominioInsGrupoAsesorespthisDominio(DominioOid dominioInsGrupoAsesorespthisDominio) {
        this.dominioInsGrupoAsesorespthisDominio = dominioInsGrupoAsesorespthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioInsGrupoAsesorespthisDominioInstance() {
        return this.dominioInsGrupoAsesorespthisDominioInstance;
    }
    
    public void setDominioInsGrupoAsesorespthisDominioInstance(Dominio dominioInsGrupoAsesorespthisDominio) {
        this.dominioInsGrupoAsesorespthisDominioInstance = dominioInsGrupoAsesorespthisDominio;
        this.dominioInsGrupoAsesorespthisDominio = dominioInsGrupoAsesorespthisDominio != null ? dominioInsGrupoAsesorespthisDominio.getOid() : null;
    }

    private void checkDominioInsGrupoAsesorespthisDominio() throws NotNullArgumentException {
        if(this.getDominioInsGrupoAsesorespthisDominioInstance() == null || this.getDominioInsGrupoAsesorespthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSGRUPOASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSGRUPOASESORES_DOMINIOINSGRUPOASESORESPTHISDOMINIO, DominioConstants.SERV_ALIAS_INSGRUPOASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSGRUPOASESORES_DOMINIOINSGRUPOASESORESPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getDominioInsGrupoAsesorespevcGrupoAsignacion() {
        return this.dominioInsGrupoAsesorespevcGrupoAsignacion;
    }
    
    public void setDominioInsGrupoAsesorespevcGrupoAsignacion(GrupoAsignacionOid dominioInsGrupoAsesorespevcGrupoAsignacion) {
        this.dominioInsGrupoAsesorespevcGrupoAsignacion = dominioInsGrupoAsesorespevcGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getDominioInsGrupoAsesorespevcGrupoAsignacionInstance() {
        return this.dominioInsGrupoAsesorespevcGrupoAsignacionInstance;
    }
    
    public void setDominioInsGrupoAsesorespevcGrupoAsignacionInstance(GrupoAsignacion dominioInsGrupoAsesorespevcGrupoAsignacion) {
        this.dominioInsGrupoAsesorespevcGrupoAsignacionInstance = dominioInsGrupoAsesorespevcGrupoAsignacion;
        this.dominioInsGrupoAsesorespevcGrupoAsignacion = dominioInsGrupoAsesorespevcGrupoAsignacion != null ? dominioInsGrupoAsesorespevcGrupoAsignacion.getOid() : null;
    }

    private void checkDominioInsGrupoAsesorespevcGrupoAsignacion() throws NotNullArgumentException {
        if(this.getDominioInsGrupoAsesorespevcGrupoAsignacionInstance() == null || this.getDominioInsGrupoAsesorespevcGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_INSGRUPOASESORES, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_INSGRUPOASESORES_DOMINIOINSGRUPOASESORESPEVCGRUPOASIGNACION, DominioConstants.SERV_ALIAS_INSGRUPOASESORES, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_INSGRUPOASESORES_DOMINIOINSGRUPOASESORESPEVCGRUPOASIGNACION);
        }
    }
}
