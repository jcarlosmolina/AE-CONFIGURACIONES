package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class CambPlanTarifaRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planesCambPlanTarifapthisPlan;
    
    @JsonIgnore
    private Planes planesCambPlanTarifapthisPlanInstance;

    @JsonProperty("p_evctarifa_oid")
    private TarifaOid planesCambPlanTarifapevcTarifa;
    
    @JsonIgnore
    private Tarifa planesCambPlanTarifapevcTarifaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanesCambPlanTarifapthisPlan();
        checkPlanesCambPlanTarifapevcTarifa();
    }

    @JsonIgnore
    public PlanesOid getPlanesCambPlanTarifapthisPlan() {
        return this.planesCambPlanTarifapthisPlan;
    }
    
    public void setPlanesCambPlanTarifapthisPlan(PlanesOid planesCambPlanTarifapthisPlan) {
        this.planesCambPlanTarifapthisPlan = planesCambPlanTarifapthisPlan;
    }

    @JsonIgnore
    public Planes getPlanesCambPlanTarifapthisPlanInstance() {
        return this.planesCambPlanTarifapthisPlanInstance;
    }
    
    public void setPlanesCambPlanTarifapthisPlanInstance(Planes planesCambPlanTarifapthisPlan) {
        this.planesCambPlanTarifapthisPlanInstance = planesCambPlanTarifapthisPlan;
        this.planesCambPlanTarifapthisPlan = planesCambPlanTarifapthisPlan != null ? planesCambPlanTarifapthisPlan.getOid() : null;
    }

    private void checkPlanesCambPlanTarifapthisPlan() throws NotNullArgumentException {
        if(this.getPlanesCambPlanTarifapthisPlanInstance() == null || this.getPlanesCambPlanTarifapthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANTARIFA, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANTARIFA_PLANESCAMBPLANTARIFAPTHISPLAN, PlanesConstants.SERV_ALIAS_CAMBPLANTARIFA, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANTARIFA_PLANESCAMBPLANTARIFAPTHISPLAN);
        }
    }

    @JsonIgnore
    public TarifaOid getPlanesCambPlanTarifapevcTarifa() {
        return this.planesCambPlanTarifapevcTarifa;
    }
    
    public void setPlanesCambPlanTarifapevcTarifa(TarifaOid planesCambPlanTarifapevcTarifa) {
        this.planesCambPlanTarifapevcTarifa = planesCambPlanTarifapevcTarifa;
    }

    @JsonIgnore
    public Tarifa getPlanesCambPlanTarifapevcTarifaInstance() {
        return this.planesCambPlanTarifapevcTarifaInstance;
    }
    
    public void setPlanesCambPlanTarifapevcTarifaInstance(Tarifa planesCambPlanTarifapevcTarifa) {
        this.planesCambPlanTarifapevcTarifaInstance = planesCambPlanTarifapevcTarifa;
        this.planesCambPlanTarifapevcTarifa = planesCambPlanTarifapevcTarifa != null ? planesCambPlanTarifapevcTarifa.getOid() : null;
    }

    private void checkPlanesCambPlanTarifapevcTarifa() throws NotNullArgumentException {
        if(this.getPlanesCambPlanTarifapevcTarifaInstance() == null || this.getPlanesCambPlanTarifapevcTarifaInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CAMBPLANTARIFA, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CAMBPLANTARIFA_PLANESCAMBPLANTARIFAPEVCTARIFA, PlanesConstants.SERV_ALIAS_CAMBPLANTARIFA, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CAMBPLANTARIFA_PLANESCAMBPLANTARIFAPEVCTARIFA);
        }
    }
}
