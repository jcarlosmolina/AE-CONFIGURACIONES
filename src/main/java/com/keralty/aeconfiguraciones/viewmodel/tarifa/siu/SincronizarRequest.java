package com.keralty.aeconfiguraciones.viewmodel.tarifa.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class SincronizarRequest {

    @JsonProperty("p_thistarifa_oid")
    private TarifaOid tarifasincronizarpthisTarifa;
    
    @JsonIgnore
    private Tarifa tarifasincronizarpthisTarifaInstance;

    @JsonProperty("p_tipocontrato")
    private Long tarifasincronizarpTipoContrato;

    public void checkArguments() throws NotNullArgumentException {
        checkTarifasincronizarpthisTarifa();
        checkTarifasincronizarpTipoContrato();
    }

    @JsonIgnore
    public TarifaOid getTarifasincronizarpthisTarifa() {
        return this.tarifasincronizarpthisTarifa;
    }
    
    public void setTarifasincronizarpthisTarifa(TarifaOid tarifasincronizarpthisTarifa) {
        this.tarifasincronizarpthisTarifa = tarifasincronizarpthisTarifa;
    }

    @JsonIgnore
    public Tarifa getTarifasincronizarpthisTarifaInstance() {
        return this.tarifasincronizarpthisTarifaInstance;
    }
    
    public void setTarifasincronizarpthisTarifaInstance(Tarifa tarifasincronizarpthisTarifa) {
        this.tarifasincronizarpthisTarifaInstance = tarifasincronizarpthisTarifa;
        this.tarifasincronizarpthisTarifa = tarifasincronizarpthisTarifa != null ? tarifasincronizarpthisTarifa.getOid() : null;
    }

    private void checkTarifasincronizarpthisTarifa() throws NotNullArgumentException {
        if(this.getTarifasincronizarpthisTarifaInstance() == null || this.getTarifasincronizarpthisTarifaInstance().isNull()) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_SINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_SINCRONIZAR_TARIFASINCRONIZARPTHISTARIFA, TarifaConstants.SERV_ALIAS_SINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_SINCRONIZAR_TARIFASINCRONIZARPTHISTARIFA);
        }
    }

    @JsonIgnore
    public Long getTarifasincronizarpTipoContrato() {
        return this.tarifasincronizarpTipoContrato;
    }
    
    public void setTarifasincronizarpTipoContrato(Long tarifasincronizarpTipoContrato) {
        this.tarifasincronizarpTipoContrato = tarifasincronizarpTipoContrato;
    }

    private void checkTarifasincronizarpTipoContrato() throws NotNullArgumentException {
        if(this.getTarifasincronizarpTipoContrato() == null) {
            throw new NotNullArgumentException(TarifaConstants.SERV_ID_SINCRONIZAR, TarifaConstants.CLASS_ID, TarifaConstants.ARG_ID_SINCRONIZAR_TARIFASINCRONIZARPTIPOCONTRATO, TarifaConstants.SERV_ALIAS_SINCRONIZAR, TarifaConstants.CLASS_ALIAS, TarifaConstants.ARG_ALIAS_SINCRONIZAR_TARIFASINCRONIZARPTIPOCONTRATO);
        }
    }
}
