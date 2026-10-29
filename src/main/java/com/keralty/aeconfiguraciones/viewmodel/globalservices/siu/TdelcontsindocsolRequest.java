package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.ContSinDocSolOid;

public class TdelcontsindocsolRequest {

    @JsonProperty("pt_p_thiscontsindocsol_oid")
    private ContSinDocSolOid tdelcontsindocsolptpthisContSinDocSol;
    
    @JsonIgnore
    private ContSinDocSol tdelcontsindocsolptpthisContSinDocSolInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTdelcontsindocsolptpthisContSinDocSol();
    }

    @JsonIgnore
    public ContSinDocSolOid getTdelcontsindocsolptpthisContSinDocSol() {
        return this.tdelcontsindocsolptpthisContSinDocSol;
    }
    
    public void setTdelcontsindocsolptpthisContSinDocSol(ContSinDocSolOid tdelcontsindocsolptpthisContSinDocSol) {
        this.tdelcontsindocsolptpthisContSinDocSol = tdelcontsindocsolptpthisContSinDocSol;
    }

    @JsonIgnore
    public ContSinDocSol getTdelcontsindocsolptpthisContSinDocSolInstance() {
        return this.tdelcontsindocsolptpthisContSinDocSolInstance;
    }
    
    public void setTdelcontsindocsolptpthisContSinDocSolInstance(ContSinDocSol tdelcontsindocsolptpthisContSinDocSol) {
        this.tdelcontsindocsolptpthisContSinDocSolInstance = tdelcontsindocsolptpthisContSinDocSol;
        this.tdelcontsindocsolptpthisContSinDocSol = tdelcontsindocsolptpthisContSinDocSol != null ? tdelcontsindocsolptpthisContSinDocSol.getOid() : null;
    }

    private void checkTdelcontsindocsolptpthisContSinDocSol() throws NotNullArgumentException {
        if(this.getTdelcontsindocsolptpthisContSinDocSolInstance() == null || this.getTdelcontsindocsolptpthisContSinDocSolInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TDELCONTSINDOCSOL, GlobalServiceConstants.GS_ARG_ID_TDELCONTSINDOCSOL_TDELCONTSINDOCSOLPTPTHISCONTSINDOCSOL, GlobalServiceConstants.G_SERV_ALIAS_TDELCONTSINDOCSOL, GlobalServiceConstants.GS_ARG_ALIAS_TDELCONTSINDOCSOL_TDELCONTSINDOCSOLPTPTHISCONTSINDOCSOL);
        }
    }
}
