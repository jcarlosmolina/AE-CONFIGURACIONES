package com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoDireccionOid;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;

public class ModificarRequest {

    @JsonProperty("p_thistipodireccion_oid")
    private TipoDireccionOid tipoDireccionmodificarpthisTipoDireccion;
    
    @JsonIgnore
    private TipoDireccion tipoDireccionmodificarpthisTipoDireccionInstance;

    @JsonProperty("p_esresidencia")
    private Boolean tipoDireccionmodificarpEsResidencia;

    @JsonProperty("p_escorrespondencia")
    private Boolean tipoDireccionmodificarpEsCorrespondencia;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoDireccionmodificarpthisTipoDireccion();
        checkTipoDireccionmodificarpEsResidencia();
        checkTipoDireccionmodificarpEsCorrespondencia();
    }

    @JsonIgnore
    public TipoDireccionOid getTipoDireccionmodificarpthisTipoDireccion() {
        return this.tipoDireccionmodificarpthisTipoDireccion;
    }
    
    public void setTipoDireccionmodificarpthisTipoDireccion(TipoDireccionOid tipoDireccionmodificarpthisTipoDireccion) {
        this.tipoDireccionmodificarpthisTipoDireccion = tipoDireccionmodificarpthisTipoDireccion;
    }

    @JsonIgnore
    public TipoDireccion getTipoDireccionmodificarpthisTipoDireccionInstance() {
        return this.tipoDireccionmodificarpthisTipoDireccionInstance;
    }
    
    public void setTipoDireccionmodificarpthisTipoDireccionInstance(TipoDireccion tipoDireccionmodificarpthisTipoDireccion) {
        this.tipoDireccionmodificarpthisTipoDireccionInstance = tipoDireccionmodificarpthisTipoDireccion;
        this.tipoDireccionmodificarpthisTipoDireccion = tipoDireccionmodificarpthisTipoDireccion != null ? tipoDireccionmodificarpthisTipoDireccion.getOid() : null;
    }

    private void checkTipoDireccionmodificarpthisTipoDireccion() throws NotNullArgumentException {
        if(this.getTipoDireccionmodificarpthisTipoDireccionInstance() == null || this.getTipoDireccionmodificarpthisTipoDireccionInstance().isNull()) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_MODIFICAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_MODIFICAR_TIPODIRECCIONMODIFICARPTHISTIPODIRECCION, TipoDireccionConstants.SERV_ALIAS_MODIFICAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_MODIFICAR_TIPODIRECCIONMODIFICARPTHISTIPODIRECCION);
        }
    }

    @JsonIgnore
    public Boolean getTipoDireccionmodificarpEsResidencia() {
        return this.tipoDireccionmodificarpEsResidencia;
    }
    
    public void setTipoDireccionmodificarpEsResidencia(Boolean tipoDireccionmodificarpEsResidencia) {
        this.tipoDireccionmodificarpEsResidencia = tipoDireccionmodificarpEsResidencia;
    }

    private void checkTipoDireccionmodificarpEsResidencia() throws NotNullArgumentException {
        if(this.getTipoDireccionmodificarpEsResidencia() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_MODIFICAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_MODIFICAR_TIPODIRECCIONMODIFICARPESRESIDENCIA, TipoDireccionConstants.SERV_ALIAS_MODIFICAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_MODIFICAR_TIPODIRECCIONMODIFICARPESRESIDENCIA);
        }
    }

    @JsonIgnore
    public Boolean getTipoDireccionmodificarpEsCorrespondencia() {
        return this.tipoDireccionmodificarpEsCorrespondencia;
    }
    
    public void setTipoDireccionmodificarpEsCorrespondencia(Boolean tipoDireccionmodificarpEsCorrespondencia) {
        this.tipoDireccionmodificarpEsCorrespondencia = tipoDireccionmodificarpEsCorrespondencia;
    }

    private void checkTipoDireccionmodificarpEsCorrespondencia() throws NotNullArgumentException {
        if(this.getTipoDireccionmodificarpEsCorrespondencia() == null) {
            throw new NotNullArgumentException(TipoDireccionConstants.SERV_ID_MODIFICAR, TipoDireccionConstants.CLASS_ID, TipoDireccionConstants.ARG_ID_MODIFICAR_TIPODIRECCIONMODIFICARPESCORRESPONDENCIA, TipoDireccionConstants.SERV_ALIAS_MODIFICAR, TipoDireccionConstants.CLASS_ALIAS, TipoDireccionConstants.ARG_ALIAS_MODIFICAR_TIPODIRECCIONMODIFICARPESCORRESPONDENCIA);
        }
    }
}
