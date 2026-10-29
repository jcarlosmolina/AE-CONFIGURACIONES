package com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.oid.LogContratosExentosFIngresoOid;

public class EliminarRequest {

    @JsonProperty("p_thislogcontratosexentosfingr_oid")
    private LogContratosExentosFIngresoOid logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr;
    
    @JsonIgnore
    private LogContratosExentosFIngreso logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr();
    }

    @JsonIgnore
    public LogContratosExentosFIngresoOid getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr() {
        return this.logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr;
    }
    
    public void setLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr(LogContratosExentosFIngresoOid logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr) {
        this.logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr = logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr;
    }

    @JsonIgnore
    public LogContratosExentosFIngreso getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance() {
        return this.logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance;
    }
    
    public void setLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance(LogContratosExentosFIngreso logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr) {
        this.logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance = logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr;
        this.logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr = logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr != null ? logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr.getOid() : null;
    }

    private void checkLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr() throws NotNullArgumentException {
        if(this.getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance() == null || this.getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance().isNull()) {
            throw new NotNullArgumentException(LogContratosExentosFIngresoConstants.SERV_ID_ELIMINAR, LogContratosExentosFIngresoConstants.CLASS_ID, LogContratosExentosFIngresoConstants.ARG_ID_ELIMINAR_LOGCONTRATOSEXENTOSFINGRESOELIMINARPTHISLOGCONTRATOSEXENTOSFINGR, LogContratosExentosFIngresoConstants.SERV_ALIAS_ELIMINAR, LogContratosExentosFIngresoConstants.CLASS_ALIAS, LogContratosExentosFIngresoConstants.ARG_ALIAS_ELIMINAR_LOGCONTRATOSEXENTOSFINGRESOELIMINARPTHISLOGCONTRATOSEXENTOSFINGR);
        }
    }
}
