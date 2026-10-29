package com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoFacturaConstants;
import com.keralty.aeconfiguraciones.persistence.TipoFactura;

public class ModificarRequest {

    @JsonProperty("p_thistipofactura_json")
    private String tipoFacturamodificarpthisTipoFactura;
    
    @JsonIgnore
    private TipoFactura tipoFacturamodificarpthisTipoFacturaInstance;

    @JsonProperty("p_descripcion")
    private String tipoFacturamodificarpDescripcion;

    @JsonProperty("p_esemail")
    private Boolean tipoFacturamodificarpEsEmail;

    @JsonProperty("p_escelular")
    private Boolean tipoFacturamodificarpEsCelular;

    @JsonProperty("p_activo")
    private Boolean tipoFacturamodificarpActivo;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoFacturamodificarpthisTipoFactura();
        checkTipoFacturamodificarpDescripcion();
        checkTipoFacturamodificarpEsEmail();
        checkTipoFacturamodificarpEsCelular();
        checkTipoFacturamodificarpActivo();
    }

    @JsonIgnore
    public String getTipoFacturamodificarpthisTipoFactura() {
        return this.tipoFacturamodificarpthisTipoFactura;
    }
    
    public void setTipoFacturamodificarpthisTipoFactura(String tipoFacturamodificarpthisTipoFactura) {
        this.tipoFacturamodificarpthisTipoFactura = tipoFacturamodificarpthisTipoFactura;
    }

    @JsonIgnore
    public TipoFactura getTipoFacturamodificarpthisTipoFacturaInstance() {
        return this.tipoFacturamodificarpthisTipoFacturaInstance;
    }
    
    public void setTipoFacturamodificarpthisTipoFacturaInstance(TipoFactura tipoFacturamodificarpthisTipoFactura) {
        this.tipoFacturamodificarpthisTipoFacturaInstance = tipoFacturamodificarpthisTipoFactura;
        this.tipoFacturamodificarpthisTipoFactura = tipoFacturamodificarpthisTipoFactura != null ? tipoFacturamodificarpthisTipoFactura.getOid().toJson() : "";
    }

    private void checkTipoFacturamodificarpthisTipoFactura() throws NotNullArgumentException {
        if(this.getTipoFacturamodificarpthisTipoFacturaInstance() == null || this.getTipoFacturamodificarpthisTipoFacturaInstance().isNull()) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_MODIFICAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_MODIFICAR_TIPOFACTURAMODIFICARPTHISTIPOFACTURA, TipoFacturaConstants.SERV_ALIAS_MODIFICAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_MODIFICAR_TIPOFACTURAMODIFICARPTHISTIPOFACTURA);
        }
    }

    @JsonIgnore
    public String getTipoFacturamodificarpDescripcion() {
        return this.tipoFacturamodificarpDescripcion;
    }
    
    public void setTipoFacturamodificarpDescripcion(String tipoFacturamodificarpDescripcion) {
        this.tipoFacturamodificarpDescripcion = tipoFacturamodificarpDescripcion;
    }

    private void checkTipoFacturamodificarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoFacturamodificarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_MODIFICAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_MODIFICAR_TIPOFACTURAMODIFICARPDESCRIPCION, TipoFacturaConstants.SERV_ALIAS_MODIFICAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_MODIFICAR_TIPOFACTURAMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getTipoFacturamodificarpEsEmail() {
        return this.tipoFacturamodificarpEsEmail;
    }
    
    public void setTipoFacturamodificarpEsEmail(Boolean tipoFacturamodificarpEsEmail) {
        this.tipoFacturamodificarpEsEmail = tipoFacturamodificarpEsEmail;
    }

    private void checkTipoFacturamodificarpEsEmail() throws NotNullArgumentException {
        if(this.getTipoFacturamodificarpEsEmail() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_MODIFICAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_MODIFICAR_TIPOFACTURAMODIFICARPESEMAIL, TipoFacturaConstants.SERV_ALIAS_MODIFICAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_MODIFICAR_TIPOFACTURAMODIFICARPESEMAIL);
        }
    }

    @JsonIgnore
    public Boolean getTipoFacturamodificarpEsCelular() {
        return this.tipoFacturamodificarpEsCelular;
    }
    
    public void setTipoFacturamodificarpEsCelular(Boolean tipoFacturamodificarpEsCelular) {
        this.tipoFacturamodificarpEsCelular = tipoFacturamodificarpEsCelular;
    }

    private void checkTipoFacturamodificarpEsCelular() throws NotNullArgumentException {
        if(this.getTipoFacturamodificarpEsCelular() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_MODIFICAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_MODIFICAR_TIPOFACTURAMODIFICARPESCELULAR, TipoFacturaConstants.SERV_ALIAS_MODIFICAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_MODIFICAR_TIPOFACTURAMODIFICARPESCELULAR);
        }
    }

    @JsonIgnore
    public Boolean getTipoFacturamodificarpActivo() {
        return this.tipoFacturamodificarpActivo;
    }
    
    public void setTipoFacturamodificarpActivo(Boolean tipoFacturamodificarpActivo) {
        this.tipoFacturamodificarpActivo = tipoFacturamodificarpActivo;
    }

    private void checkTipoFacturamodificarpActivo() throws NotNullArgumentException {
        if(this.getTipoFacturamodificarpActivo() == null) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_MODIFICAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_MODIFICAR_TIPOFACTURAMODIFICARPACTIVO, TipoFacturaConstants.SERV_ALIAS_MODIFICAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_MODIFICAR_TIPOFACTURAMODIFICARPACTIVO);
        }
    }
}
