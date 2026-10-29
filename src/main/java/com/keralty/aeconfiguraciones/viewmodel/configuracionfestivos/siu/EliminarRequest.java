package com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionFestivosConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionFestivos;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionFestivosOid;

public class EliminarRequest {

    @JsonProperty("p_thisconfiguracionfestivos_oid")
    private ConfiguracionFestivosOid configuracionFestivoseliminarpthisConfiguracionFestivos;
    
    @JsonIgnore
    private ConfiguracionFestivos configuracionFestivoseliminarpthisConfiguracionFestivosInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionFestivoseliminarpthisConfiguracionFestivos();
    }

    @JsonIgnore
    public ConfiguracionFestivosOid getConfiguracionFestivoseliminarpthisConfiguracionFestivos() {
        return this.configuracionFestivoseliminarpthisConfiguracionFestivos;
    }
    
    public void setConfiguracionFestivoseliminarpthisConfiguracionFestivos(ConfiguracionFestivosOid configuracionFestivoseliminarpthisConfiguracionFestivos) {
        this.configuracionFestivoseliminarpthisConfiguracionFestivos = configuracionFestivoseliminarpthisConfiguracionFestivos;
    }

    @JsonIgnore
    public ConfiguracionFestivos getConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance() {
        return this.configuracionFestivoseliminarpthisConfiguracionFestivosInstance;
    }
    
    public void setConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance(ConfiguracionFestivos configuracionFestivoseliminarpthisConfiguracionFestivos) {
        this.configuracionFestivoseliminarpthisConfiguracionFestivosInstance = configuracionFestivoseliminarpthisConfiguracionFestivos;
        this.configuracionFestivoseliminarpthisConfiguracionFestivos = configuracionFestivoseliminarpthisConfiguracionFestivos != null ? configuracionFestivoseliminarpthisConfiguracionFestivos.getOid() : null;
    }

    private void checkConfiguracionFestivoseliminarpthisConfiguracionFestivos() throws NotNullArgumentException {
        if(this.getConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance() == null || this.getConfiguracionFestivoseliminarpthisConfiguracionFestivosInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionFestivosConstants.SERV_ID_ELIMINAR, ConfiguracionFestivosConstants.CLASS_ID, ConfiguracionFestivosConstants.ARG_ID_ELIMINAR_CONFIGURACIONFESTIVOSELIMINARPTHISCONFIGURACIONFESTIVOS, ConfiguracionFestivosConstants.SERV_ALIAS_ELIMINAR, ConfiguracionFestivosConstants.CLASS_ALIAS, ConfiguracionFestivosConstants.ARG_ALIAS_ELIMINAR_CONFIGURACIONFESTIVOSELIMINARPTHISCONFIGURACIONFESTIVOS);
        }
    }
}
