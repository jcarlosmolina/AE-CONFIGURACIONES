package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;

public class TASGGRUPORequest {

    @JsonProperty("p_thisgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance;

    @JsonProperty("ptgrupo_oid")
    private GrupoAsignacionOid grupoAsignacionNovedadTASGGRUPOptGrupo;
    
    @JsonIgnore
    private GrupoAsignacion grupoAsignacionNovedadTASGGRUPOptGrupoInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad();
        checkGrupoAsignacionNovedadTASGGRUPOptGrupo();
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad() {
        return this.grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad = grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance = grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad;
        this.grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad = grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad != null ? grupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionNovedadTASGGRUPOpthisGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TASGGRUPO, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TASGGRUPO_GRUPOASIGNACIONNOVEDADTASGGRUPOPTHISGRUPOASIGNACIONNOVEDAD, GrupoAsignacionNovedadConstants.SERV_ALIAS_TASGGRUPO, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TASGGRUPO_GRUPOASIGNACIONNOVEDADTASGGRUPOPTHISGRUPOASIGNACIONNOVEDAD);
        }
    }

    @JsonIgnore
    public GrupoAsignacionOid getGrupoAsignacionNovedadTASGGRUPOptGrupo() {
        return this.grupoAsignacionNovedadTASGGRUPOptGrupo;
    }
    
    public void setGrupoAsignacionNovedadTASGGRUPOptGrupo(GrupoAsignacionOid grupoAsignacionNovedadTASGGRUPOptGrupo) {
        this.grupoAsignacionNovedadTASGGRUPOptGrupo = grupoAsignacionNovedadTASGGRUPOptGrupo;
    }

    @JsonIgnore
    public GrupoAsignacion getGrupoAsignacionNovedadTASGGRUPOptGrupoInstance() {
        return this.grupoAsignacionNovedadTASGGRUPOptGrupoInstance;
    }
    
    public void setGrupoAsignacionNovedadTASGGRUPOptGrupoInstance(GrupoAsignacion grupoAsignacionNovedadTASGGRUPOptGrupo) {
        this.grupoAsignacionNovedadTASGGRUPOptGrupoInstance = grupoAsignacionNovedadTASGGRUPOptGrupo;
        this.grupoAsignacionNovedadTASGGRUPOptGrupo = grupoAsignacionNovedadTASGGRUPOptGrupo != null ? grupoAsignacionNovedadTASGGRUPOptGrupo.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadTASGGRUPOptGrupo() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadTASGGRUPOptGrupoInstance() == null || this.getGrupoAsignacionNovedadTASGGRUPOptGrupoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_TASGGRUPO, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_TASGGRUPO_GRUPOASIGNACIONNOVEDADTASGGRUPOPTGRUPO, GrupoAsignacionNovedadConstants.SERV_ALIAS_TASGGRUPO, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_TASGGRUPO_GRUPOASIGNACIONNOVEDADTASGGRUPOPTGRUPO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
