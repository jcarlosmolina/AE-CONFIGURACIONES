package com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MotDevAreaMedicaConstants;

public class CrearRequest {

    @JsonProperty("p_atrdescripcion")
    private String motDevAreaMedicacrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkMotDevAreaMedicacrearpatrDescripcion();
    }

    @JsonIgnore
    public String getMotDevAreaMedicacrearpatrDescripcion() {
        return this.motDevAreaMedicacrearpatrDescripcion;
    }
    
    public void setMotDevAreaMedicacrearpatrDescripcion(String motDevAreaMedicacrearpatrDescripcion) {
        this.motDevAreaMedicacrearpatrDescripcion = motDevAreaMedicacrearpatrDescripcion;
    }

    private void checkMotDevAreaMedicacrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getMotDevAreaMedicacrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(MotDevAreaMedicaConstants.SERV_ID_CREAR, MotDevAreaMedicaConstants.CLASS_ID, MotDevAreaMedicaConstants.ARG_ID_CREAR_MOTDEVAREAMEDICACREARPATRDESCRIPCION, MotDevAreaMedicaConstants.SERV_ALIAS_CREAR, MotDevAreaMedicaConstants.CLASS_ALIAS, MotDevAreaMedicaConstants.ARG_ALIAS_CREAR_MOTDEVAREAMEDICACREARPATRDESCRIPCION);
        }
    }
}
