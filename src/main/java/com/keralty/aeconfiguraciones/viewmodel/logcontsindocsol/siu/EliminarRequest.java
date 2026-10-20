package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogContSinDocSolConstants;
import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogContSinDocSolOid;

public class EliminarRequest {

    @JsonProperty("p_thislogcontsindocsol_oid")
    private LogContSinDocSolOid logContSinDocSoleliminarpthisLogContSinDocSol;
    
    @JsonIgnore
    private LogContSinDocSol logContSinDocSoleliminarpthisLogContSinDocSolInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkLogContSinDocSoleliminarpthisLogContSinDocSol();
    }

    @JsonIgnore
    public LogContSinDocSolOid getLogContSinDocSoleliminarpthisLogContSinDocSol() {
        return this.logContSinDocSoleliminarpthisLogContSinDocSol;
    }
    
    public void setLogContSinDocSoleliminarpthisLogContSinDocSol(LogContSinDocSolOid logContSinDocSoleliminarpthisLogContSinDocSol) {
        this.logContSinDocSoleliminarpthisLogContSinDocSol = logContSinDocSoleliminarpthisLogContSinDocSol;
    }

    @JsonIgnore
    public LogContSinDocSol getLogContSinDocSoleliminarpthisLogContSinDocSolInstance() {
        return this.logContSinDocSoleliminarpthisLogContSinDocSolInstance;
    }
    
    public void setLogContSinDocSoleliminarpthisLogContSinDocSolInstance(LogContSinDocSol logContSinDocSoleliminarpthisLogContSinDocSol) {
        this.logContSinDocSoleliminarpthisLogContSinDocSolInstance = logContSinDocSoleliminarpthisLogContSinDocSol;
        this.logContSinDocSoleliminarpthisLogContSinDocSol = logContSinDocSoleliminarpthisLogContSinDocSol != null ? logContSinDocSoleliminarpthisLogContSinDocSol.getOid() : null;
    }

    private void checkLogContSinDocSoleliminarpthisLogContSinDocSol() throws NotNullArgumentException {
        if(this.getLogContSinDocSoleliminarpthisLogContSinDocSolInstance() == null || this.getLogContSinDocSoleliminarpthisLogContSinDocSolInstance().isNull()) {
            throw new NotNullArgumentException(LogContSinDocSolConstants.SERV_ID_ELIMINAR, LogContSinDocSolConstants.CLASS_ID, LogContSinDocSolConstants.ARG_ID_ELIMINAR_LOGCONTSINDOCSOLELIMINARPTHISLOGCONTSINDOCSOL, LogContSinDocSolConstants.SERV_ALIAS_ELIMINAR, LogContSinDocSolConstants.CLASS_ALIAS, LogContSinDocSolConstants.ARG_ALIAS_ELIMINAR_LOGCONTSINDOCSOLELIMINARPTHISLOGCONTSINDOCSOL);
        }
    }
}
