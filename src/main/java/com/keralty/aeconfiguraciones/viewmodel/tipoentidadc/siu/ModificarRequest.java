package com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoEntidadCOid;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;

public class ModificarRequest {

    @JsonProperty("p_thistipoentidadc_oid")
    private TipoEntidadCOid tipoEntidadCmodificarpthisTipoEntidadC;
    
    @JsonIgnore
    private TipoEntidadC tipoEntidadCmodificarpthisTipoEntidadCInstance;

    @JsonProperty("p_descripcion")
    private String tipoEntidadCmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoEntidadCmodificarpthisTipoEntidadC();
        checkTipoEntidadCmodificarpDescripcion();
    }

    @JsonIgnore
    public TipoEntidadCOid getTipoEntidadCmodificarpthisTipoEntidadC() {
        return this.tipoEntidadCmodificarpthisTipoEntidadC;
    }
    
    public void setTipoEntidadCmodificarpthisTipoEntidadC(TipoEntidadCOid tipoEntidadCmodificarpthisTipoEntidadC) {
        this.tipoEntidadCmodificarpthisTipoEntidadC = tipoEntidadCmodificarpthisTipoEntidadC;
    }

    @JsonIgnore
    public TipoEntidadC getTipoEntidadCmodificarpthisTipoEntidadCInstance() {
        return this.tipoEntidadCmodificarpthisTipoEntidadCInstance;
    }
    
    public void setTipoEntidadCmodificarpthisTipoEntidadCInstance(TipoEntidadC tipoEntidadCmodificarpthisTipoEntidadC) {
        this.tipoEntidadCmodificarpthisTipoEntidadCInstance = tipoEntidadCmodificarpthisTipoEntidadC;
        this.tipoEntidadCmodificarpthisTipoEntidadC = tipoEntidadCmodificarpthisTipoEntidadC != null ? tipoEntidadCmodificarpthisTipoEntidadC.getOid() : null;
    }

    private void checkTipoEntidadCmodificarpthisTipoEntidadC() throws NotNullArgumentException {
        if(this.getTipoEntidadCmodificarpthisTipoEntidadCInstance() == null || this.getTipoEntidadCmodificarpthisTipoEntidadCInstance().isNull()) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_MODIFICAR, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_MODIFICAR_TIPOENTIDADCMODIFICARPTHISTIPOENTIDADC, TipoEntidadCConstants.SERV_ALIAS_MODIFICAR, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_MODIFICAR_TIPOENTIDADCMODIFICARPTHISTIPOENTIDADC);
        }
    }

    @JsonIgnore
    public String getTipoEntidadCmodificarpDescripcion() {
        return this.tipoEntidadCmodificarpDescripcion;
    }
    
    public void setTipoEntidadCmodificarpDescripcion(String tipoEntidadCmodificarpDescripcion) {
        this.tipoEntidadCmodificarpDescripcion = tipoEntidadCmodificarpDescripcion;
    }

    private void checkTipoEntidadCmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoEntidadCmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_MODIFICAR, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_MODIFICAR_TIPOENTIDADCMODIFICARPDESCRIPCION, TipoEntidadCConstants.SERV_ALIAS_MODIFICAR, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_MODIFICAR_TIPOENTIDADCMODIFICARPDESCRIPCION);
        }
    }
}
