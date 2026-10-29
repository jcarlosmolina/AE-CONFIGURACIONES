package com.keralty.aeconfiguraciones.viewmodel.eps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EpsConstants;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;

public class ModificarRequest {

    @JsonProperty("p_thiseps_oid")
    private EpsOid epsmodificarpthisEPS;
    
    @JsonIgnore
    private Eps epsmodificarpthisEPSInstance;

    @JsonProperty("p_epssanitas")
    private Boolean epsmodificarpEPSSanitas;

    public void checkArguments() throws NotNullArgumentException {
        checkEpsmodificarpthisEPS();
        checkEpsmodificarpEPSSanitas();
    }

    @JsonIgnore
    public EpsOid getEpsmodificarpthisEPS() {
        return this.epsmodificarpthisEPS;
    }
    
    public void setEpsmodificarpthisEPS(EpsOid epsmodificarpthisEPS) {
        this.epsmodificarpthisEPS = epsmodificarpthisEPS;
    }

    @JsonIgnore
    public Eps getEpsmodificarpthisEPSInstance() {
        return this.epsmodificarpthisEPSInstance;
    }
    
    public void setEpsmodificarpthisEPSInstance(Eps epsmodificarpthisEPS) {
        this.epsmodificarpthisEPSInstance = epsmodificarpthisEPS;
        this.epsmodificarpthisEPS = epsmodificarpthisEPS != null ? epsmodificarpthisEPS.getOid() : null;
    }

    private void checkEpsmodificarpthisEPS() throws NotNullArgumentException {
        if(this.getEpsmodificarpthisEPSInstance() == null || this.getEpsmodificarpthisEPSInstance().isNull()) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_MODIFICAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_MODIFICAR_EPSMODIFICARPTHISEPS, EpsConstants.SERV_ALIAS_MODIFICAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_MODIFICAR_EPSMODIFICARPTHISEPS);
        }
    }

    @JsonIgnore
    public Boolean getEpsmodificarpEPSSanitas() {
        return this.epsmodificarpEPSSanitas;
    }
    
    public void setEpsmodificarpEPSSanitas(Boolean epsmodificarpEPSSanitas) {
        this.epsmodificarpEPSSanitas = epsmodificarpEPSSanitas;
    }

    private void checkEpsmodificarpEPSSanitas() throws NotNullArgumentException {
        if(this.getEpsmodificarpEPSSanitas() == null) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_MODIFICAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_MODIFICAR_EPSMODIFICARPEPSSANITAS, EpsConstants.SERV_ALIAS_MODIFICAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_MODIFICAR_EPSMODIFICARPEPSSANITAS);
        }
    }
}
