package com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;

public class ModificarRequest {

    @JsonProperty("p_thistiposucursalc_oid")
    private TipoSucursalCOid tipoSucursalCmodificarpthisTipoSucursalC;
    
    @JsonIgnore
    private TipoSucursalC tipoSucursalCmodificarpthisTipoSucursalCInstance;

    @JsonProperty("p_descripcion")
    private String tipoSucursalCmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoSucursalCmodificarpthisTipoSucursalC();
        checkTipoSucursalCmodificarpDescripcion();
    }

    @JsonIgnore
    public TipoSucursalCOid getTipoSucursalCmodificarpthisTipoSucursalC() {
        return this.tipoSucursalCmodificarpthisTipoSucursalC;
    }
    
    public void setTipoSucursalCmodificarpthisTipoSucursalC(TipoSucursalCOid tipoSucursalCmodificarpthisTipoSucursalC) {
        this.tipoSucursalCmodificarpthisTipoSucursalC = tipoSucursalCmodificarpthisTipoSucursalC;
    }

    @JsonIgnore
    public TipoSucursalC getTipoSucursalCmodificarpthisTipoSucursalCInstance() {
        return this.tipoSucursalCmodificarpthisTipoSucursalCInstance;
    }
    
    public void setTipoSucursalCmodificarpthisTipoSucursalCInstance(TipoSucursalC tipoSucursalCmodificarpthisTipoSucursalC) {
        this.tipoSucursalCmodificarpthisTipoSucursalCInstance = tipoSucursalCmodificarpthisTipoSucursalC;
        this.tipoSucursalCmodificarpthisTipoSucursalC = tipoSucursalCmodificarpthisTipoSucursalC != null ? tipoSucursalCmodificarpthisTipoSucursalC.getOid() : null;
    }

    private void checkTipoSucursalCmodificarpthisTipoSucursalC() throws NotNullArgumentException {
        if(this.getTipoSucursalCmodificarpthisTipoSucursalCInstance() == null || this.getTipoSucursalCmodificarpthisTipoSucursalCInstance().isNull()) {
            throw new NotNullArgumentException(TipoSucursalCConstants.SERV_ID_MODIFICAR, TipoSucursalCConstants.CLASS_ID, TipoSucursalCConstants.ARG_ID_MODIFICAR_TIPOSUCURSALCMODIFICARPTHISTIPOSUCURSALC, TipoSucursalCConstants.SERV_ALIAS_MODIFICAR, TipoSucursalCConstants.CLASS_ALIAS, TipoSucursalCConstants.ARG_ALIAS_MODIFICAR_TIPOSUCURSALCMODIFICARPTHISTIPOSUCURSALC);
        }
    }

    @JsonIgnore
    public String getTipoSucursalCmodificarpDescripcion() {
        return this.tipoSucursalCmodificarpDescripcion;
    }
    
    public void setTipoSucursalCmodificarpDescripcion(String tipoSucursalCmodificarpDescripcion) {
        this.tipoSucursalCmodificarpDescripcion = tipoSucursalCmodificarpDescripcion;
    }

    private void checkTipoSucursalCmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoSucursalCmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoSucursalCConstants.SERV_ID_MODIFICAR, TipoSucursalCConstants.CLASS_ID, TipoSucursalCConstants.ARG_ID_MODIFICAR_TIPOSUCURSALCMODIFICARPDESCRIPCION, TipoSucursalCConstants.SERV_ALIAS_MODIFICAR, TipoSucursalCConstants.CLASS_ALIAS, TipoSucursalCConstants.ARG_ALIAS_MODIFICAR_TIPOSUCURSALCMODIFICARPDESCRIPCION);
        }
    }
}
