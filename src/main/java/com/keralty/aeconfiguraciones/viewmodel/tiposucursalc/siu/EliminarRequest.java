package com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoSucursalCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;

public class EliminarRequest {

    @JsonProperty("p_thistiposucursalc_oid")
    private TipoSucursalCOid tipoSucursalCeliminarpthisTipoSucursalC;
    
    @JsonIgnore
    private TipoSucursalC tipoSucursalCeliminarpthisTipoSucursalCInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoSucursalCeliminarpthisTipoSucursalC();
    }

    @JsonIgnore
    public TipoSucursalCOid getTipoSucursalCeliminarpthisTipoSucursalC() {
        return this.tipoSucursalCeliminarpthisTipoSucursalC;
    }
    
    public void setTipoSucursalCeliminarpthisTipoSucursalC(TipoSucursalCOid tipoSucursalCeliminarpthisTipoSucursalC) {
        this.tipoSucursalCeliminarpthisTipoSucursalC = tipoSucursalCeliminarpthisTipoSucursalC;
    }

    @JsonIgnore
    public TipoSucursalC getTipoSucursalCeliminarpthisTipoSucursalCInstance() {
        return this.tipoSucursalCeliminarpthisTipoSucursalCInstance;
    }
    
    public void setTipoSucursalCeliminarpthisTipoSucursalCInstance(TipoSucursalC tipoSucursalCeliminarpthisTipoSucursalC) {
        this.tipoSucursalCeliminarpthisTipoSucursalCInstance = tipoSucursalCeliminarpthisTipoSucursalC;
        this.tipoSucursalCeliminarpthisTipoSucursalC = tipoSucursalCeliminarpthisTipoSucursalC != null ? tipoSucursalCeliminarpthisTipoSucursalC.getOid() : null;
    }

    private void checkTipoSucursalCeliminarpthisTipoSucursalC() throws NotNullArgumentException {
        if(this.getTipoSucursalCeliminarpthisTipoSucursalCInstance() == null || this.getTipoSucursalCeliminarpthisTipoSucursalCInstance().isNull()) {
            throw new NotNullArgumentException(TipoSucursalCConstants.SERV_ID_ELIMINAR, TipoSucursalCConstants.CLASS_ID, TipoSucursalCConstants.ARG_ID_ELIMINAR_TIPOSUCURSALCELIMINARPTHISTIPOSUCURSALC, TipoSucursalCConstants.SERV_ALIAS_ELIMINAR, TipoSucursalCConstants.CLASS_ALIAS, TipoSucursalCConstants.ARG_ALIAS_ELIMINAR_TIPOSUCURSALCELIMINARPTHISTIPOSUCURSALC);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
