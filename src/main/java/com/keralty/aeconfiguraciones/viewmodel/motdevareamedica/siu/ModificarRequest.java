package com.keralty.aeconfiguraciones.viewmodel.motdevareamedica.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MotDevAreaMedicaConstants;
import com.keralty.aeconfiguraciones.persistence.MotDevAreaMedica;
import com.keralty.aeconfiguraciones.persistence.oid.MotDevAreaMedicaOid;

public class ModificarRequest {

    @JsonProperty("p_thismotdevareamedica_oid")
    private MotDevAreaMedicaOid motDevAreaMedicamodificarpthisMotDevAreaMedica;
    
    @JsonIgnore
    private MotDevAreaMedica motDevAreaMedicamodificarpthisMotDevAreaMedicaInstance;

    @JsonProperty("p_descripcion")
    private String motDevAreaMedicamodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkMotDevAreaMedicamodificarpthisMotDevAreaMedica();
        checkMotDevAreaMedicamodificarpDescripcion();
    }

    @JsonIgnore
    public MotDevAreaMedicaOid getMotDevAreaMedicamodificarpthisMotDevAreaMedica() {
        return this.motDevAreaMedicamodificarpthisMotDevAreaMedica;
    }
    
    public void setMotDevAreaMedicamodificarpthisMotDevAreaMedica(MotDevAreaMedicaOid motDevAreaMedicamodificarpthisMotDevAreaMedica) {
        this.motDevAreaMedicamodificarpthisMotDevAreaMedica = motDevAreaMedicamodificarpthisMotDevAreaMedica;
    }

    @JsonIgnore
    public MotDevAreaMedica getMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance() {
        return this.motDevAreaMedicamodificarpthisMotDevAreaMedicaInstance;
    }
    
    public void setMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance(MotDevAreaMedica motDevAreaMedicamodificarpthisMotDevAreaMedica) {
        this.motDevAreaMedicamodificarpthisMotDevAreaMedicaInstance = motDevAreaMedicamodificarpthisMotDevAreaMedica;
        this.motDevAreaMedicamodificarpthisMotDevAreaMedica = motDevAreaMedicamodificarpthisMotDevAreaMedica != null ? motDevAreaMedicamodificarpthisMotDevAreaMedica.getOid() : null;
    }

    private void checkMotDevAreaMedicamodificarpthisMotDevAreaMedica() throws NotNullArgumentException {
        if(this.getMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance() == null || this.getMotDevAreaMedicamodificarpthisMotDevAreaMedicaInstance().isNull()) {
            throw new NotNullArgumentException(MotDevAreaMedicaConstants.SERV_ID_MODIFICAR, MotDevAreaMedicaConstants.CLASS_ID, MotDevAreaMedicaConstants.ARG_ID_MODIFICAR_MOTDEVAREAMEDICAMODIFICARPTHISMOTDEVAREAMEDICA, MotDevAreaMedicaConstants.SERV_ALIAS_MODIFICAR, MotDevAreaMedicaConstants.CLASS_ALIAS, MotDevAreaMedicaConstants.ARG_ALIAS_MODIFICAR_MOTDEVAREAMEDICAMODIFICARPTHISMOTDEVAREAMEDICA);
        }
    }

    @JsonIgnore
    public String getMotDevAreaMedicamodificarpDescripcion() {
        return this.motDevAreaMedicamodificarpDescripcion;
    }
    
    public void setMotDevAreaMedicamodificarpDescripcion(String motDevAreaMedicamodificarpDescripcion) {
        this.motDevAreaMedicamodificarpDescripcion = motDevAreaMedicamodificarpDescripcion;
    }

    private void checkMotDevAreaMedicamodificarpDescripcion() throws NotNullArgumentException {
        if(this.getMotDevAreaMedicamodificarpDescripcion() == null) {
            throw new NotNullArgumentException(MotDevAreaMedicaConstants.SERV_ID_MODIFICAR, MotDevAreaMedicaConstants.CLASS_ID, MotDevAreaMedicaConstants.ARG_ID_MODIFICAR_MOTDEVAREAMEDICAMODIFICARPDESCRIPCION, MotDevAreaMedicaConstants.SERV_ALIAS_MODIFICAR, MotDevAreaMedicaConstants.CLASS_ALIAS, MotDevAreaMedicaConstants.ARG_ALIAS_MODIFICAR_MOTDEVAREAMEDICAMODIFICARPDESCRIPCION);
        }
    }
}
