package com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoDireccionOid;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;

public class SincronizarRequest {

    @JsonProperty("p_thistipodireccion_oid")
    private TipoDireccionOid tipoDireccionsincronizarpthisTipoDireccion;
    
    @JsonIgnore
    private TipoDireccion tipoDireccionsincronizarpthisTipoDireccionInstance;

    @JsonProperty("p_descripcion")
    private String tipoDireccionsincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoDireccionsincronizarpthisTipoDireccion();
        checkTipoDireccionsincronizarpDescripcion();
    }

    @JsonIgnore
    public TipoDireccionOid getTipoDireccionsincronizarpthisTipoDireccion() {
        return this.tipoDireccionsincronizarpthisTipoDireccion;
    }
    
    public void setTipoDireccionsincronizarpthisTipoDireccion(TipoDireccionOid tipoDireccionsincronizarpthisTipoDireccion) {
        this.tipoDireccionsincronizarpthisTipoDireccion = tipoDireccionsincronizarpthisTipoDireccion;
    }

    @JsonIgnore
    public TipoDireccion getTipoDireccionsincronizarpthisTipoDireccionInstance() {
        return this.tipoDireccionsincronizarpthisTipoDireccionInstance;
    }
    
    public void setTipoDireccionsincronizarpthisTipoDireccionInstance(TipoDireccion tipoDireccionsincronizarpthisTipoDireccion) {
        this.tipoDireccionsincronizarpthisTipoDireccionInstance = tipoDireccionsincronizarpthisTipoDireccion;
        this.tipoDireccionsincronizarpthisTipoDireccion = tipoDireccionsincronizarpthisTipoDireccion != null ? tipoDireccionsincronizarpthisTipoDireccion.getOid() : null;
    }

    private void checkTipoDireccionsincronizarpthisTipoDireccion() throws NotNullArgumentException {
        if(this.getTipoDireccionsincronizarpthisTipoDireccionInstance() == null || this.getTipoDireccionsincronizarpthisTipoDireccionInstance().isNull()) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_SINCRONIZAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_SINCRONIZAR_TIPODIRECCIONSINCRONIZARPTHISTIPODIRECCION, TipoDireccionConstants.SERV_ALIAS_SINCRONIZAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_SINCRONIZAR_TIPODIRECCIONSINCRONIZARPTHISTIPODIRECCION);
        }
    }

    @JsonIgnore
    public String getTipoDireccionsincronizarpDescripcion() {
        return this.tipoDireccionsincronizarpDescripcion;
    }
    
    public void setTipoDireccionsincronizarpDescripcion(String tipoDireccionsincronizarpDescripcion) {
        this.tipoDireccionsincronizarpDescripcion = tipoDireccionsincronizarpDescripcion;
    }

    private void checkTipoDireccionsincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoDireccionsincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_SINCRONIZAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_SINCRONIZAR_TIPODIRECCIONSINCRONIZARPDESCRIPCION, TipoDireccionConstants.SERV_ALIAS_SINCRONIZAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_SINCRONIZAR_TIPODIRECCIONSINCRONIZARPDESCRIPCION);
        }
    }
}
