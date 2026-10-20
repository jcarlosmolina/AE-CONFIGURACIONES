package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CuestionarioMedConstants;

public class CrearRequest {

    @JsonProperty("p_atrnombre")
    private String cuestionarioMedcrearpatrNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkCuestionarioMedcrearpatrNombre();
    }

    @JsonIgnore
    public String getCuestionarioMedcrearpatrNombre() {
        return this.cuestionarioMedcrearpatrNombre;
    }
    
    public void setCuestionarioMedcrearpatrNombre(String cuestionarioMedcrearpatrNombre) {
        this.cuestionarioMedcrearpatrNombre = cuestionarioMedcrearpatrNombre;
    }

    private void checkCuestionarioMedcrearpatrNombre() throws NotNullArgumentException {
        if(this.getCuestionarioMedcrearpatrNombre() == null) {
            throw new NotNullArgumentException(CuestionarioMedConstants.SERV_ID_CREAR, CuestionarioMedConstants.CLASS_ID, CuestionarioMedConstants.ARG_ID_CREAR_CUESTIONARIOMEDCREARPATRNOMBRE, CuestionarioMedConstants.SERV_ALIAS_CREAR, CuestionarioMedConstants.CLASS_ALIAS, CuestionarioMedConstants.ARG_ALIAS_CREAR_CUESTIONARIOMEDCREARPATRNOMBRE);
        }
    }
}
