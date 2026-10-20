package com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoInstitucionCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;

public class ModificarRequest {

    @JsonProperty("p_thistipoinstitucionc_oid")
    private TipoInstitucionCOid tipoInstitucionCmodificarpthisTipoInstitucionC;
    
    @JsonIgnore
    private TipoInstitucionC tipoInstitucionCmodificarpthisTipoInstitucionCInstance;

    @JsonProperty("p_nombre")
    private String tipoInstitucionCmodificarpNombre;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoInstitucionCmodificarpthisTipoInstitucionC();
        checkTipoInstitucionCmodificarpNombre();
    }

    @JsonIgnore
    public TipoInstitucionCOid getTipoInstitucionCmodificarpthisTipoInstitucionC() {
        return this.tipoInstitucionCmodificarpthisTipoInstitucionC;
    }
    
    public void setTipoInstitucionCmodificarpthisTipoInstitucionC(TipoInstitucionCOid tipoInstitucionCmodificarpthisTipoInstitucionC) {
        this.tipoInstitucionCmodificarpthisTipoInstitucionC = tipoInstitucionCmodificarpthisTipoInstitucionC;
    }

    @JsonIgnore
    public TipoInstitucionC getTipoInstitucionCmodificarpthisTipoInstitucionCInstance() {
        return this.tipoInstitucionCmodificarpthisTipoInstitucionCInstance;
    }
    
    public void setTipoInstitucionCmodificarpthisTipoInstitucionCInstance(TipoInstitucionC tipoInstitucionCmodificarpthisTipoInstitucionC) {
        this.tipoInstitucionCmodificarpthisTipoInstitucionCInstance = tipoInstitucionCmodificarpthisTipoInstitucionC;
        this.tipoInstitucionCmodificarpthisTipoInstitucionC = tipoInstitucionCmodificarpthisTipoInstitucionC != null ? tipoInstitucionCmodificarpthisTipoInstitucionC.getOid() : null;
    }

    private void checkTipoInstitucionCmodificarpthisTipoInstitucionC() throws NotNullArgumentException {
        if(this.getTipoInstitucionCmodificarpthisTipoInstitucionCInstance() == null || this.getTipoInstitucionCmodificarpthisTipoInstitucionCInstance().isNull()) {
            throw new NotNullArgumentException(TipoInstitucionCConstants.SERV_ID_MODIFICAR, TipoInstitucionCConstants.CLASS_ID, TipoInstitucionCConstants.ARG_ID_MODIFICAR_TIPOINSTITUCIONCMODIFICARPTHISTIPOINSTITUCIONC, TipoInstitucionCConstants.SERV_ALIAS_MODIFICAR, TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ARG_ALIAS_MODIFICAR_TIPOINSTITUCIONCMODIFICARPTHISTIPOINSTITUCIONC);
        }
    }

    @JsonIgnore
    public String getTipoInstitucionCmodificarpNombre() {
        return this.tipoInstitucionCmodificarpNombre;
    }
    
    public void setTipoInstitucionCmodificarpNombre(String tipoInstitucionCmodificarpNombre) {
        this.tipoInstitucionCmodificarpNombre = tipoInstitucionCmodificarpNombre;
    }

    private void checkTipoInstitucionCmodificarpNombre() throws NotNullArgumentException {
        if(this.getTipoInstitucionCmodificarpNombre() == null) {
            throw new NotNullArgumentException(TipoInstitucionCConstants.SERV_ID_MODIFICAR, TipoInstitucionCConstants.CLASS_ID, TipoInstitucionCConstants.ARG_ID_MODIFICAR_TIPOINSTITUCIONCMODIFICARPNOMBRE, TipoInstitucionCConstants.SERV_ALIAS_MODIFICAR, TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ARG_ALIAS_MODIFICAR_TIPOINSTITUCIONCMODIFICARPNOMBRE);
        }
    }
}
