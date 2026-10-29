package com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoEntidadCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoEntidadCOid;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;

public class EliminarRequest {

    @JsonProperty("p_thistipoentidad_oid")
    private TipoEntidadCOid tipoEntidadCeliminarpthisTipoEntidad;
    
    @JsonIgnore
    private TipoEntidadC tipoEntidadCeliminarpthisTipoEntidadInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoEntidadCeliminarpthisTipoEntidad();
    }

    @JsonIgnore
    public TipoEntidadCOid getTipoEntidadCeliminarpthisTipoEntidad() {
        return this.tipoEntidadCeliminarpthisTipoEntidad;
    }
    
    public void setTipoEntidadCeliminarpthisTipoEntidad(TipoEntidadCOid tipoEntidadCeliminarpthisTipoEntidad) {
        this.tipoEntidadCeliminarpthisTipoEntidad = tipoEntidadCeliminarpthisTipoEntidad;
    }

    @JsonIgnore
    public TipoEntidadC getTipoEntidadCeliminarpthisTipoEntidadInstance() {
        return this.tipoEntidadCeliminarpthisTipoEntidadInstance;
    }
    
    public void setTipoEntidadCeliminarpthisTipoEntidadInstance(TipoEntidadC tipoEntidadCeliminarpthisTipoEntidad) {
        this.tipoEntidadCeliminarpthisTipoEntidadInstance = tipoEntidadCeliminarpthisTipoEntidad;
        this.tipoEntidadCeliminarpthisTipoEntidad = tipoEntidadCeliminarpthisTipoEntidad != null ? tipoEntidadCeliminarpthisTipoEntidad.getOid() : null;
    }

    private void checkTipoEntidadCeliminarpthisTipoEntidad() throws NotNullArgumentException {
        if(this.getTipoEntidadCeliminarpthisTipoEntidadInstance() == null || this.getTipoEntidadCeliminarpthisTipoEntidadInstance().isNull()) {
            throw new NotNullArgumentException(TipoEntidadCConstants.SERV_ID_ELIMINAR, TipoEntidadCConstants.CLASS_ID, TipoEntidadCConstants.ARG_ID_ELIMINAR_TIPOENTIDADCELIMINARPTHISTIPOENTIDAD, TipoEntidadCConstants.SERV_ALIAS_ELIMINAR, TipoEntidadCConstants.CLASS_ALIAS, TipoEntidadCConstants.ARG_ALIAS_ELIMINAR_TIPOENTIDADCELIMINARPTHISTIPOENTIDAD);
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
