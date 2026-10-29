package com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoDireccionOid;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;

public class EliminarRequest {

    @JsonProperty("p_thistipodireccion_oid")
    private TipoDireccionOid tipoDireccioneliminarpthisTipoDireccion;
    
    @JsonIgnore
    private TipoDireccion tipoDireccioneliminarpthisTipoDireccionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoDireccioneliminarpthisTipoDireccion();
    }

    @JsonIgnore
    public TipoDireccionOid getTipoDireccioneliminarpthisTipoDireccion() {
        return this.tipoDireccioneliminarpthisTipoDireccion;
    }
    
    public void setTipoDireccioneliminarpthisTipoDireccion(TipoDireccionOid tipoDireccioneliminarpthisTipoDireccion) {
        this.tipoDireccioneliminarpthisTipoDireccion = tipoDireccioneliminarpthisTipoDireccion;
    }

    @JsonIgnore
    public TipoDireccion getTipoDireccioneliminarpthisTipoDireccionInstance() {
        return this.tipoDireccioneliminarpthisTipoDireccionInstance;
    }
    
    public void setTipoDireccioneliminarpthisTipoDireccionInstance(TipoDireccion tipoDireccioneliminarpthisTipoDireccion) {
        this.tipoDireccioneliminarpthisTipoDireccionInstance = tipoDireccioneliminarpthisTipoDireccion;
        this.tipoDireccioneliminarpthisTipoDireccion = tipoDireccioneliminarpthisTipoDireccion != null ? tipoDireccioneliminarpthisTipoDireccion.getOid() : null;
    }

    private void checkTipoDireccioneliminarpthisTipoDireccion() throws NotNullArgumentException {
        if(this.getTipoDireccioneliminarpthisTipoDireccionInstance() == null || this.getTipoDireccioneliminarpthisTipoDireccionInstance().isNull()) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_ELIMINAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_ELIMINAR_TIPODIRECCIONELIMINARPTHISTIPODIRECCION, TipoDireccionConstants.SERV_ALIAS_ELIMINAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_ELIMINAR_TIPODIRECCIONELIMINARPTHISTIPODIRECCION);
        }
    }
}
