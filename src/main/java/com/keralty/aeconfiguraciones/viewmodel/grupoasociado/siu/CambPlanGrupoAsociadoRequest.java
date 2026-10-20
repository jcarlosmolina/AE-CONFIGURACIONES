package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.Planes;

public class CambPlanGrupoAsociadoRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance;

    @JsonProperty("p_evcplanes_oid")
    private PlanesOid grupoAsociadoCambPlanGrupoAsociadopevcPlanes;
    
    @JsonIgnore
    private Planes grupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado();
        checkGrupoAsociadoCambPlanGrupoAsociadopevcPlanes();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado() {
        return this.grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado;
    }
    
    public void setGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado(GrupoAsociadoOid grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado) {
        this.grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado = grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance() {
        return this.grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado) {
        this.grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance = grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado;
        this.grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado = grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado != null ? grupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance() == null || this.getGrupoAsociadoCambPlanGrupoAsociadopthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CAMBPLANGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CAMBPLANGRUPOASOCIADO_GRUPOASOCIADOCAMBPLANGRUPOASOCIADOPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_CAMBPLANGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CAMBPLANGRUPOASOCIADO_GRUPOASOCIADOCAMBPLANGRUPOASOCIADOPTHISGRUPOASOCIADO);
        }
    }

    @JsonIgnore
    public PlanesOid getGrupoAsociadoCambPlanGrupoAsociadopevcPlanes() {
        return this.grupoAsociadoCambPlanGrupoAsociadopevcPlanes;
    }
    
    public void setGrupoAsociadoCambPlanGrupoAsociadopevcPlanes(PlanesOid grupoAsociadoCambPlanGrupoAsociadopevcPlanes) {
        this.grupoAsociadoCambPlanGrupoAsociadopevcPlanes = grupoAsociadoCambPlanGrupoAsociadopevcPlanes;
    }

    @JsonIgnore
    public Planes getGrupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance() {
        return this.grupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance;
    }
    
    public void setGrupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance(Planes grupoAsociadoCambPlanGrupoAsociadopevcPlanes) {
        this.grupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance = grupoAsociadoCambPlanGrupoAsociadopevcPlanes;
        this.grupoAsociadoCambPlanGrupoAsociadopevcPlanes = grupoAsociadoCambPlanGrupoAsociadopevcPlanes != null ? grupoAsociadoCambPlanGrupoAsociadopevcPlanes.getOid() : null;
    }

    private void checkGrupoAsociadoCambPlanGrupoAsociadopevcPlanes() throws NotNullArgumentException {
        if(this.getGrupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance() == null || this.getGrupoAsociadoCambPlanGrupoAsociadopevcPlanesInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_CAMBPLANGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_CAMBPLANGRUPOASOCIADO_GRUPOASOCIADOCAMBPLANGRUPOASOCIADOPEVCPLANES, GrupoAsociadoConstants.SERV_ALIAS_CAMBPLANGRUPOASOCIADO, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_CAMBPLANGRUPOASOCIADO_GRUPOASOCIADOCAMBPLANGRUPOASOCIADOPEVCPLANES);
        }
    }
}
