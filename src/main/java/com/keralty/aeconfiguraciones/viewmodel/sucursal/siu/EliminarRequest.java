package com.keralty.aeconfiguraciones.viewmodel.sucursal.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SucursalConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SucursalOid;
import com.keralty.aeconfiguraciones.persistence.Sucursal;

public class EliminarRequest {

    @JsonProperty("p_thissucursal_oid")
    private SucursalOid sucursaleliminarpthisSucursal;
    
    @JsonIgnore
    private Sucursal sucursaleliminarpthisSucursalInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkSucursaleliminarpthisSucursal();
    }

    @JsonIgnore
    public SucursalOid getSucursaleliminarpthisSucursal() {
        return this.sucursaleliminarpthisSucursal;
    }
    
    public void setSucursaleliminarpthisSucursal(SucursalOid sucursaleliminarpthisSucursal) {
        this.sucursaleliminarpthisSucursal = sucursaleliminarpthisSucursal;
    }

    @JsonIgnore
    public Sucursal getSucursaleliminarpthisSucursalInstance() {
        return this.sucursaleliminarpthisSucursalInstance;
    }
    
    public void setSucursaleliminarpthisSucursalInstance(Sucursal sucursaleliminarpthisSucursal) {
        this.sucursaleliminarpthisSucursalInstance = sucursaleliminarpthisSucursal;
        this.sucursaleliminarpthisSucursal = sucursaleliminarpthisSucursal != null ? sucursaleliminarpthisSucursal.getOid() : null;
    }

    private void checkSucursaleliminarpthisSucursal() throws NotNullArgumentException {
        if(this.getSucursaleliminarpthisSucursalInstance() == null || this.getSucursaleliminarpthisSucursalInstance().isNull()) {
            throw new NotNullArgumentException(SucursalConstants.SERV_ID_ELIMINAR, SucursalConstants.CLASS_ID, SucursalConstants.ARG_ID_ELIMINAR_SUCURSALELIMINARPTHISSUCURSAL, SucursalConstants.SERV_ALIAS_ELIMINAR, SucursalConstants.CLASS_ALIAS, SucursalConstants.ARG_ALIAS_ELIMINAR_SUCURSALELIMINARPTHISSUCURSAL);
        }
    }
}
