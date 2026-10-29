package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.Planes;

public class EliminarRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planeseliminarpthisPlan;
    
    @JsonIgnore
    private Planes planeseliminarpthisPlanInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkPlaneseliminarpthisPlan();
    }

    @JsonIgnore
    public PlanesOid getPlaneseliminarpthisPlan() {
        return this.planeseliminarpthisPlan;
    }
    
    public void setPlaneseliminarpthisPlan(PlanesOid planeseliminarpthisPlan) {
        this.planeseliminarpthisPlan = planeseliminarpthisPlan;
    }

    @JsonIgnore
    public Planes getPlaneseliminarpthisPlanInstance() {
        return this.planeseliminarpthisPlanInstance;
    }
    
    public void setPlaneseliminarpthisPlanInstance(Planes planeseliminarpthisPlan) {
        this.planeseliminarpthisPlanInstance = planeseliminarpthisPlan;
        this.planeseliminarpthisPlan = planeseliminarpthisPlan != null ? planeseliminarpthisPlan.getOid() : null;
    }

    private void checkPlaneseliminarpthisPlan() throws NotNullArgumentException {
        if(this.getPlaneseliminarpthisPlanInstance() == null || this.getPlaneseliminarpthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_ELIMINAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_ELIMINAR_PLANESELIMINARPTHISPLAN, PlanesConstants.SERV_ALIAS_ELIMINAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_ELIMINAR_PLANESELIMINARPTHISPLAN);
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
