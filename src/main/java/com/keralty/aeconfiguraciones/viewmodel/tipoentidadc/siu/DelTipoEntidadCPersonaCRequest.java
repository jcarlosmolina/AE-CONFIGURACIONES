package com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoEntidadCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;

public class DelTipoEntidadCPersonaCRequest {

    @JsonProperty("p_thistipoentidadc_oid")
    private TipoEntidadCOid tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC;
    
    @JsonIgnore
    private TipoEntidadC tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance;

    @JsonProperty("p_evcpersonac_oid")
    private PersonaCOid tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC;
    
    @JsonIgnore
    private PersonaC tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC();
        checkTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC();
    }

    @JsonIgnore
    public TipoEntidadCOid getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC() {
        return this.tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC;
    }
    
    public void setTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC(TipoEntidadCOid tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC) {
        this.tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC = tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC;
    }

    @JsonIgnore
    public TipoEntidadC getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance() {
        return this.tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance;
    }
    
    public void setTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance(TipoEntidadC tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC) {
        this.tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance = tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC;
        this.tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC = tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC != null ? tipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC.getOid() : null;
    }

    private void checkTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadC() throws NotNullArgumentException {
        if(this.getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance() == null || this.getTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance().isNull()) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_DELTIPOENTIDADCPERSONAC, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_DELTIPOENTIDADCPERSONAC_TIPOENTIDADCDELTIPOENTIDADCPERSONACPTHISTIPOENTIDADC, TipoEntidadCConstants.SERV_ALIAS_DELTIPOENTIDADCPERSONAC, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_DELTIPOENTIDADCPERSONAC_TIPOENTIDADCDELTIPOENTIDADCPERSONACPTHISTIPOENTIDADC);
        }
    }

    @JsonIgnore
    public PersonaCOid getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC() {
        return this.tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC;
    }
    
    public void setTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC(PersonaCOid tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC) {
        this.tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC = tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC;
    }

    @JsonIgnore
    public PersonaC getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance() {
        return this.tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance;
    }
    
    public void setTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance(PersonaC tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC) {
        this.tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance = tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC;
        this.tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC = tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC != null ? tipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC.getOid() : null;
    }

    private void checkTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaC() throws NotNullArgumentException {
        if(this.getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance() == null || this.getTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance().isNull()) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_DELTIPOENTIDADCPERSONAC, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_DELTIPOENTIDADCPERSONAC_TIPOENTIDADCDELTIPOENTIDADCPERSONACPEVCPERSONAC, TipoEntidadCConstants.SERV_ALIAS_DELTIPOENTIDADCPERSONAC, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_DELTIPOENTIDADCPERSONAC_TIPOENTIDADCDELTIPOENTIDADCPERSONACPEVCPERSONAC);
        }
    }
}
