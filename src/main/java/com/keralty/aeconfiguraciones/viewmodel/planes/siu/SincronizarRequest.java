package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.Planes;

public class SincronizarRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planessincronizarpthisPlan;
    
    @JsonIgnore
    private Planes planessincronizarpthisPlanInstance;

    @JsonProperty("p_nombre")
    private String planessincronizarpNombre;

    @JsonProperty("p_codigolegal")
    private String planessincronizarpCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanessincronizarpthisPlan();
        checkPlanessincronizarpNombre();
        checkPlanessincronizarpCodigoLegal();
    }

    @JsonIgnore
    public PlanesOid getPlanessincronizarpthisPlan() {
        return this.planessincronizarpthisPlan;
    }
    
    public void setPlanessincronizarpthisPlan(PlanesOid planessincronizarpthisPlan) {
        this.planessincronizarpthisPlan = planessincronizarpthisPlan;
    }

    @JsonIgnore
    public Planes getPlanessincronizarpthisPlanInstance() {
        return this.planessincronizarpthisPlanInstance;
    }
    
    public void setPlanessincronizarpthisPlanInstance(Planes planessincronizarpthisPlan) {
        this.planessincronizarpthisPlanInstance = planessincronizarpthisPlan;
        this.planessincronizarpthisPlan = planessincronizarpthisPlan != null ? planessincronizarpthisPlan.getOid() : null;
    }

    private void checkPlanessincronizarpthisPlan() throws NotNullArgumentException {
        if(this.getPlanessincronizarpthisPlanInstance() == null || this.getPlanessincronizarpthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_SINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_SINCRONIZAR_PLANESSINCRONIZARPTHISPLAN, PlanesConstants.SERV_ALIAS_SINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_SINCRONIZAR_PLANESSINCRONIZARPTHISPLAN);
        }
    }

    @JsonIgnore
    public String getPlanessincronizarpNombre() {
        return this.planessincronizarpNombre;
    }
    
    public void setPlanessincronizarpNombre(String planessincronizarpNombre) {
        this.planessincronizarpNombre = planessincronizarpNombre;
    }

    private void checkPlanessincronizarpNombre() throws NotNullArgumentException {
        if(this.getPlanessincronizarpNombre() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_SINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_SINCRONIZAR_PLANESSINCRONIZARPNOMBRE, PlanesConstants.SERV_ALIAS_SINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_SINCRONIZAR_PLANESSINCRONIZARPNOMBRE);
        }
    }

    @JsonIgnore
    public String getPlanessincronizarpCodigoLegal() {
        return this.planessincronizarpCodigoLegal;
    }
    
    public void setPlanessincronizarpCodigoLegal(String planessincronizarpCodigoLegal) {
        this.planessincronizarpCodigoLegal = planessincronizarpCodigoLegal;
    }

    private void checkPlanessincronizarpCodigoLegal() throws NotNullArgumentException {
        if(this.getPlanessincronizarpCodigoLegal() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_SINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_SINCRONIZAR_PLANESSINCRONIZARPCODIGOLEGAL, PlanesConstants.SERV_ALIAS_SINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_SINCRONIZAR_PLANESSINCRONIZARPCODIGOLEGAL);
        }
    }
}
