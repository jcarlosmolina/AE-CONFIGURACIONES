package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class EliminarRequest {

    @JsonProperty("p_thisconfiguracionans_oid")
    private ConfiguracionANSOid configuracionANSeliminarpthisConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS configuracionANSeliminarpthisConfiguracionANSInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANSeliminarpthisConfiguracionANS();
    }

    @JsonIgnore
    public ConfiguracionANSOid getConfiguracionANSeliminarpthisConfiguracionANS() {
        return this.configuracionANSeliminarpthisConfiguracionANS;
    }
    
    public void setConfiguracionANSeliminarpthisConfiguracionANS(ConfiguracionANSOid configuracionANSeliminarpthisConfiguracionANS) {
        this.configuracionANSeliminarpthisConfiguracionANS = configuracionANSeliminarpthisConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getConfiguracionANSeliminarpthisConfiguracionANSInstance() {
        return this.configuracionANSeliminarpthisConfiguracionANSInstance;
    }
    
    public void setConfiguracionANSeliminarpthisConfiguracionANSInstance(ConfiguracionANS configuracionANSeliminarpthisConfiguracionANS) {
        this.configuracionANSeliminarpthisConfiguracionANSInstance = configuracionANSeliminarpthisConfiguracionANS;
        this.configuracionANSeliminarpthisConfiguracionANS = configuracionANSeliminarpthisConfiguracionANS != null ? configuracionANSeliminarpthisConfiguracionANS.getOid() : null;
    }

    private void checkConfiguracionANSeliminarpthisConfiguracionANS() throws NotNullArgumentException {
        if(this.getConfiguracionANSeliminarpthisConfiguracionANSInstance() == null || this.getConfiguracionANSeliminarpthisConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_ELIMINAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_ELIMINAR_CONFIGURACIONANSELIMINARPTHISCONFIGURACIONANS, ConfiguracionANSConstants.SERV_ALIAS_ELIMINAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_ELIMINAR_CONFIGURACIONANSELIMINARPTHISCONFIGURACIONANS);
        }
    }
}
