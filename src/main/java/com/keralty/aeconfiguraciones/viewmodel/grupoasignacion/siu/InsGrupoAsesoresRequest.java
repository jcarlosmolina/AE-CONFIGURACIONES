package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class InsGrupoAsesoresRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid grupoAsignacionInsGrupoAsesorespevcDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionInsGrupoAsesorespevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacion();
        checkGrupoAsignacionInsGrupoAsesorespevcDominio();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacion() {
        return this.grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion = grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance() {
        return this.grupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion) {
        this.grupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance = grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion;
        this.grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion = grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion != null ? grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOASESORES, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOASESORES_GRUPOASIGNACIONINSGRUPOASESORESPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOASESORES, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOASESORES_GRUPOASIGNACIONINSGRUPOASESORESPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionInsGrupoAsesorespevcDominio() {
        return this.grupoAsignacionInsGrupoAsesorespevcDominio;
    }
    
    public void setGrupoAsignacionInsGrupoAsesorespevcDominio(DominioOid grupoAsignacionInsGrupoAsesorespevcDominio) {
        this.grupoAsignacionInsGrupoAsesorespevcDominio = grupoAsignacionInsGrupoAsesorespevcDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionInsGrupoAsesorespevcDominioInstance() {
        return this.grupoAsignacionInsGrupoAsesorespevcDominioInstance;
    }
    
    public void setGrupoAsignacionInsGrupoAsesorespevcDominioInstance(Dominio grupoAsignacionInsGrupoAsesorespevcDominio) {
        this.grupoAsignacionInsGrupoAsesorespevcDominioInstance = grupoAsignacionInsGrupoAsesorespevcDominio;
        this.grupoAsignacionInsGrupoAsesorespevcDominio = grupoAsignacionInsGrupoAsesorespevcDominio != null ? grupoAsignacionInsGrupoAsesorespevcDominio.getOid() : null;
    }

    private void checkGrupoAsignacionInsGrupoAsesorespevcDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionInsGrupoAsesorespevcDominioInstance() == null || this.getGrupoAsignacionInsGrupoAsesorespevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_INSGRUPOASESORES, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_INSGRUPOASESORES_GRUPOASIGNACIONINSGRUPOASESORESPEVCDOMINIO, GrupoAsignacionConstants.SERV_ALIAS_INSGRUPOASESORES, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_INSGRUPOASESORES_GRUPOASIGNACIONINSGRUPOASESORESPEVCDOMINIO);
        }
    }
}
