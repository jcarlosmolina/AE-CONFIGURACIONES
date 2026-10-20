package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.Planes;

public class CambPlanGrupoAsociadoRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planesCambPlanGrupoAsociadopthisPlan;
    
    @JsonIgnore
    private Planes planesCambPlanGrupoAsociadopthisPlanInstance;

    @JsonProperty("p_evcgrupoasociado_oid")
    private GrupoAsociadoOid planesCambPlanGrupoAsociadopevcGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado planesCambPlanGrupoAsociadopevcGrupoAsociadoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanesCambPlanGrupoAsociadopthisPlan();
        checkPlanesCambPlanGrupoAsociadopevcGrupoAsociado();
    }

    @JsonIgnore
    public PlanesOid getPlanesCambPlanGrupoAsociadopthisPlan() {
        return this.planesCambPlanGrupoAsociadopthisPlan;
    }
    
    public void setPlanesCambPlanGrupoAsociadopthisPlan(PlanesOid planesCambPlanGrupoAsociadopthisPlan) {
        this.planesCambPlanGrupoAsociadopthisPlan = planesCambPlanGrupoAsociadopthisPlan;
    }

    @JsonIgnore
    public Planes getPlanesCambPlanGrupoAsociadopthisPlanInstance() {
        return this.planesCambPlanGrupoAsociadopthisPlanInstance;
    }
    
    public void setPlanesCambPlanGrupoAsociadopthisPlanInstance(Planes planesCambPlanGrupoAsociadopthisPlan) {
        this.planesCambPlanGrupoAsociadopthisPlanInstance = planesCambPlanGrupoAsociadopthisPlan;
        this.planesCambPlanGrupoAsociadopthisPlan = planesCambPlanGrupoAsociadopthisPlan != null ? planesCambPlanGrupoAsociadopthisPlan.getOid() : null;
    }

    private void checkPlanesCambPlanGrupoAsociadopthisPlan() throws NotNullArgumentException {
        if(this.getPlanesCambPlanGrupoAsociadopthisPlanInstance() == null || this.getPlanesCambPlanGrupoAsociadopthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANGRUPOASOCIADO, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANGRUPOASOCIADO_PLANESCAMBPLANGRUPOASOCIADOPTHISPLAN, PlanesConstants.SERV_ALIAS_CAMBPLANGRUPOASOCIADO, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANGRUPOASOCIADO_PLANESCAMBPLANGRUPOASOCIADOPTHISPLAN);
        }
    }

    @JsonIgnore
    public GrupoAsociadoOid getPlanesCambPlanGrupoAsociadopevcGrupoAsociado() {
        return this.planesCambPlanGrupoAsociadopevcGrupoAsociado;
    }
    
    public void setPlanesCambPlanGrupoAsociadopevcGrupoAsociado(GrupoAsociadoOid planesCambPlanGrupoAsociadopevcGrupoAsociado) {
        this.planesCambPlanGrupoAsociadopevcGrupoAsociado = planesCambPlanGrupoAsociadopevcGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getPlanesCambPlanGrupoAsociadopevcGrupoAsociadoInstance() {
        return this.planesCambPlanGrupoAsociadopevcGrupoAsociadoInstance;
    }
    
    public void setPlanesCambPlanGrupoAsociadopevcGrupoAsociadoInstance(GrupoAsociado planesCambPlanGrupoAsociadopevcGrupoAsociado) {
        this.planesCambPlanGrupoAsociadopevcGrupoAsociadoInstance = planesCambPlanGrupoAsociadopevcGrupoAsociado;
        this.planesCambPlanGrupoAsociadopevcGrupoAsociado = planesCambPlanGrupoAsociadopevcGrupoAsociado != null ? planesCambPlanGrupoAsociadopevcGrupoAsociado.getOid() : null;
    }

    private void checkPlanesCambPlanGrupoAsociadopevcGrupoAsociado() throws NotNullArgumentException {
        if(this.getPlanesCambPlanGrupoAsociadopevcGrupoAsociadoInstance() == null || this.getPlanesCambPlanGrupoAsociadopevcGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANGRUPOASOCIADO, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANGRUPOASOCIADO_PLANESCAMBPLANGRUPOASOCIADOPEVCGRUPOASOCIADO, PlanesConstants.SERV_ALIAS_CAMBPLANGRUPOASOCIADO, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANGRUPOASOCIADO_PLANESCAMBPLANGRUPOASOCIADOPEVCGRUPOASOCIADO);
        }
    }
}
