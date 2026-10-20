package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class EliminarRequest {

    @JsonProperty("p_thistarifa_oid")
    private TarifaOid tarifaeliminarpthisTarifa;
    
    @JsonIgnore
    private Tarifa tarifaeliminarpthisTarifaInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifaeliminarpthisTarifa();
    }

    @JsonIgnore
    public TarifaOid getTarifaeliminarpthisTarifa() {
        return this.tarifaeliminarpthisTarifa;
    }
    
    public void setTarifaeliminarpthisTarifa(TarifaOid tarifaeliminarpthisTarifa) {
        this.tarifaeliminarpthisTarifa = tarifaeliminarpthisTarifa;
    }

    @JsonIgnore
    public Tarifa getTarifaeliminarpthisTarifaInstance() {
        return this.tarifaeliminarpthisTarifaInstance;
    }
    
    public void setTarifaeliminarpthisTarifaInstance(Tarifa tarifaeliminarpthisTarifa) {
        this.tarifaeliminarpthisTarifaInstance = tarifaeliminarpthisTarifa;
        this.tarifaeliminarpthisTarifa = tarifaeliminarpthisTarifa != null ? tarifaeliminarpthisTarifa.getOid() : null;
    }

    private void checkTarifaeliminarpthisTarifa() throws NotNullArgumentException {
        if(this.getTarifaeliminarpthisTarifaInstance() == null || this.getTarifaeliminarpthisTarifaInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_ELIMINAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_ELIMINAR_TARIFAELIMINARPTHISTARIFA, TarifaConstants.SERV_ALIAS_ELIMINAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_ELIMINAR_TARIFAELIMINARPTHISTARIFA);
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
