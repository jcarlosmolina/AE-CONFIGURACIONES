package com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class DelGrupoAsesoresRequest {

    @JsonProperty("p_thisgrupoasignacion_oid")
    private GrupoAsignacionOid grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance;

    @JsonProperty("p_evcdominio_oid")
    private DominioOid grupoAsignacionDelGrupoAsesorespevcDominio;
    
    @JsonIgnore
    private Dominio grupoAsignacionDelGrupoAsesorespevcDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacion();
        checkGrupoAsignacionDelGrupoAsesorespevcDominio();
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacion() {
        return this.grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion;
    }
    
    public void setGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacion(GrupoAsignacionOid grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion = grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance() {
        return this.grupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance;
    }
    
    public void setGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance(GrupoAsignacion grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion) {
        this.grupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance = grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion;
        this.grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion = grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion != null ? grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacion() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance() == null || this.getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOASESORES, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOASESORES_GRUPOASIGNACIONDELGRUPOASESORESPTHISGRUPOASIGNACION, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOASESORES, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOASESORES_GRUPOASIGNACIONDELGRUPOASESORESPTHISGRUPOASIGNACION);
        }
    }

    @JsonIgnore
    public DominioOid getGrupoAsignacionDelGrupoAsesorespevcDominio() {
        return this.grupoAsignacionDelGrupoAsesorespevcDominio;
    }
    
    public void setGrupoAsignacionDelGrupoAsesorespevcDominio(DominioOid grupoAsignacionDelGrupoAsesorespevcDominio) {
        this.grupoAsignacionDelGrupoAsesorespevcDominio = grupoAsignacionDelGrupoAsesorespevcDominio;
    }

    @JsonIgnore
    public Dominio getGrupoAsignacionDelGrupoAsesorespevcDominioInstance() {
        return this.grupoAsignacionDelGrupoAsesorespevcDominioInstance;
    }
    
    public void setGrupoAsignacionDelGrupoAsesorespevcDominioInstance(Dominio grupoAsignacionDelGrupoAsesorespevcDominio) {
        this.grupoAsignacionDelGrupoAsesorespevcDominioInstance = grupoAsignacionDelGrupoAsesorespevcDominio;
        this.grupoAsignacionDelGrupoAsesorespevcDominio = grupoAsignacionDelGrupoAsesorespevcDominio != null ? grupoAsignacionDelGrupoAsesorespevcDominio.getOid() : null;
    }

    private void checkGrupoAsignacionDelGrupoAsesorespevcDominio() throws NotNullArgumentException {
        if(this.getGrupoAsignacionDelGrupoAsesorespevcDominioInstance() == null || this.getGrupoAsignacionDelGrupoAsesorespevcDominioInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionConstants.SERV_ID_DELGRUPOASESORES, GrupoAsignacionConstants.CLASS_ID, GrupoAsignacionConstants.ARG_ID_DELGRUPOASESORES_GRUPOASIGNACIONDELGRUPOASESORESPEVCDOMINIO, GrupoAsignacionConstants.SERV_ALIAS_DELGRUPOASESORES, GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ARG_ALIAS_DELGRUPOASESORES_GRUPOASIGNACIONDELGRUPOASESORESPEVCDOMINIO);
        }
    }
}
