package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class TELIMINARRequest {

    @JsonProperty("p_thistarifa_oid")
    private TarifaOid tarifaTELIMINARpthisTarifa;
    
    @JsonIgnore
    private Tarifa tarifaTELIMINARpthisTarifaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifaTELIMINARpthisTarifa();
    }

    @JsonIgnore
    public TarifaOid getTarifaTELIMINARpthisTarifa() {
        return this.tarifaTELIMINARpthisTarifa;
    }
    
    public void setTarifaTELIMINARpthisTarifa(TarifaOid tarifaTELIMINARpthisTarifa) {
        this.tarifaTELIMINARpthisTarifa = tarifaTELIMINARpthisTarifa;
    }

    @JsonIgnore
    public Tarifa getTarifaTELIMINARpthisTarifaInstance() {
        return this.tarifaTELIMINARpthisTarifaInstance;
    }
    
    public void setTarifaTELIMINARpthisTarifaInstance(Tarifa tarifaTELIMINARpthisTarifa) {
        this.tarifaTELIMINARpthisTarifaInstance = tarifaTELIMINARpthisTarifa;
        this.tarifaTELIMINARpthisTarifa = tarifaTELIMINARpthisTarifa != null ? tarifaTELIMINARpthisTarifa.getOid() : null;
    }

    private void checkTarifaTELIMINARpthisTarifa() throws NotNullArgumentException {
        if(this.getTarifaTELIMINARpthisTarifaInstance() == null || this.getTarifaTELIMINARpthisTarifaInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_TELIMINAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_TELIMINAR_TARIFATELIMINARPTHISTARIFA, TarifaConstants.SERV_ALIAS_TELIMINAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_TELIMINAR_TARIFATELIMINARPTHISTARIFA);
        }
    }
}
