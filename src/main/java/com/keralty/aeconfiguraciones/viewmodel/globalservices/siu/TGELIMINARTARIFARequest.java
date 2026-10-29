package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class TGELIMINARTARIFARequest {

    @JsonProperty("ptarifa_oid")
    private TarifaOid tGELIMINARTARIFApTarifa;
    
    @JsonIgnore
    private Tarifa tGELIMINARTARIFApTarifaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTGELIMINARTARIFApTarifa();
    }

    @JsonIgnore
    public TarifaOid getTGELIMINARTARIFApTarifa() {
        return this.tGELIMINARTARIFApTarifa;
    }
    
    public void setTGELIMINARTARIFApTarifa(TarifaOid tGELIMINARTARIFApTarifa) {
        this.tGELIMINARTARIFApTarifa = tGELIMINARTARIFApTarifa;
    }

    @JsonIgnore
    public Tarifa getTGELIMINARTARIFApTarifaInstance() {
        return this.tGELIMINARTARIFApTarifaInstance;
    }
    
    public void setTGELIMINARTARIFApTarifaInstance(Tarifa tGELIMINARTARIFApTarifa) {
        this.tGELIMINARTARIFApTarifaInstance = tGELIMINARTARIFApTarifa;
        this.tGELIMINARTARIFApTarifa = tGELIMINARTARIFApTarifa != null ? tGELIMINARTARIFApTarifa.getOid() : null;
    }

    private void checkTGELIMINARTARIFApTarifa() throws NotNullArgumentException {
        if(this.getTGELIMINARTARIFApTarifaInstance() == null || this.getTGELIMINARTARIFApTarifaInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGELIMINARTARIFA, GlobalServiceConstants.GS_ARG_ID_TGELIMINARTARIFA_TGELIMINARTARIFAPTARIFA, GlobalServiceConstants.G_SERV_ALIAS_TGELIMINARTARIFA, GlobalServiceConstants.GS_ARG_ALIAS_TGELIMINARTARIFA_TGELIMINARTARIFAPTARIFA);
        }
    }
}
