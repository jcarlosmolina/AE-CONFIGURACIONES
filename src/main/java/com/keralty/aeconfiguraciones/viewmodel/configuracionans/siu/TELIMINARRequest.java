package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class TELIMINARRequest {

    @JsonProperty("p_thisconfiguracionans_oid")
    private ConfiguracionANSOid configuracionANSTELIMINARpthisConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS configuracionANSTELIMINARpthisConfiguracionANSInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANSTELIMINARpthisConfiguracionANS();
    }

    @JsonIgnore
    public ConfiguracionANSOid getConfiguracionANSTELIMINARpthisConfiguracionANS() {
        return this.configuracionANSTELIMINARpthisConfiguracionANS;
    }
    
    public void setConfiguracionANSTELIMINARpthisConfiguracionANS(ConfiguracionANSOid configuracionANSTELIMINARpthisConfiguracionANS) {
        this.configuracionANSTELIMINARpthisConfiguracionANS = configuracionANSTELIMINARpthisConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getConfiguracionANSTELIMINARpthisConfiguracionANSInstance() {
        return this.configuracionANSTELIMINARpthisConfiguracionANSInstance;
    }
    
    public void setConfiguracionANSTELIMINARpthisConfiguracionANSInstance(ConfiguracionANS configuracionANSTELIMINARpthisConfiguracionANS) {
        this.configuracionANSTELIMINARpthisConfiguracionANSInstance = configuracionANSTELIMINARpthisConfiguracionANS;
        this.configuracionANSTELIMINARpthisConfiguracionANS = configuracionANSTELIMINARpthisConfiguracionANS != null ? configuracionANSTELIMINARpthisConfiguracionANS.getOid() : null;
    }

    private void checkConfiguracionANSTELIMINARpthisConfiguracionANS() throws NotNullArgumentException {
        if(this.getConfiguracionANSTELIMINARpthisConfiguracionANSInstance() == null || this.getConfiguracionANSTELIMINARpthisConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TELIMINAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TELIMINAR_CONFIGURACIONANSTELIMINARPTHISCONFIGURACIONANS, ConfiguracionANSConstants.SERV_ALIAS_TELIMINAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TELIMINAR_CONFIGURACIONANSTELIMINARPTHISCONFIGURACIONANS);
        }
    }
}
