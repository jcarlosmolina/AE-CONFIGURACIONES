package com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionFestivosConstants;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class CrearRequest {

    @JsonProperty("p_atrdiafestivo")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date configuracionFestivoscrearpatrDiaFestivo;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionFestivoscrearpatrDiaFestivo();
    }

    @JsonIgnore
    public Date getConfiguracionFestivoscrearpatrDiaFestivo() {
        return this.configuracionFestivoscrearpatrDiaFestivo;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setConfiguracionFestivoscrearpatrDiaFestivo(Date configuracionFestivoscrearpatrDiaFestivo) {
        this.configuracionFestivoscrearpatrDiaFestivo = configuracionFestivoscrearpatrDiaFestivo;
    }

    private void checkConfiguracionFestivoscrearpatrDiaFestivo() throws NotNullArgumentException {
        if(this.getConfiguracionFestivoscrearpatrDiaFestivo() == null) {
            throw new NotNullArgumentException(ConfiguracionFestivosConstants.SERV_ID_CREAR, ConfiguracionFestivosConstants.CLASS_ID, ConfiguracionFestivosConstants.ARG_ID_CREAR_CONFIGURACIONFESTIVOSCREARPATRDIAFESTIVO, ConfiguracionFestivosConstants.SERV_ALIAS_CREAR, ConfiguracionFestivosConstants.CLASS_ALIAS, ConfiguracionFestivosConstants.ARG_ALIAS_CREAR_CONFIGURACIONFESTIVOSCREARPATRDIAFESTIVO);
        }
    }
}
