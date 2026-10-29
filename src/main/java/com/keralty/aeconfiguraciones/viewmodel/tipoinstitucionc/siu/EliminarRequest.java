package com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoInstitucionCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;

public class EliminarRequest {

    @JsonProperty("p_thistipoinstitucion_oid")
    private TipoInstitucionCOid tipoInstitucionCeliminarpthisTipoInstitucion;
    
    @JsonIgnore
    private TipoInstitucionC tipoInstitucionCeliminarpthisTipoInstitucionInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoInstitucionCeliminarpthisTipoInstitucion();
    }

    @JsonIgnore
    public TipoInstitucionCOid getTipoInstitucionCeliminarpthisTipoInstitucion() {
        return this.tipoInstitucionCeliminarpthisTipoInstitucion;
    }
    
    public void setTipoInstitucionCeliminarpthisTipoInstitucion(TipoInstitucionCOid tipoInstitucionCeliminarpthisTipoInstitucion) {
        this.tipoInstitucionCeliminarpthisTipoInstitucion = tipoInstitucionCeliminarpthisTipoInstitucion;
    }

    @JsonIgnore
    public TipoInstitucionC getTipoInstitucionCeliminarpthisTipoInstitucionInstance() {
        return this.tipoInstitucionCeliminarpthisTipoInstitucionInstance;
    }
    
    public void setTipoInstitucionCeliminarpthisTipoInstitucionInstance(TipoInstitucionC tipoInstitucionCeliminarpthisTipoInstitucion) {
        this.tipoInstitucionCeliminarpthisTipoInstitucionInstance = tipoInstitucionCeliminarpthisTipoInstitucion;
        this.tipoInstitucionCeliminarpthisTipoInstitucion = tipoInstitucionCeliminarpthisTipoInstitucion != null ? tipoInstitucionCeliminarpthisTipoInstitucion.getOid() : null;
    }

    private void checkTipoInstitucionCeliminarpthisTipoInstitucion() throws NotNullArgumentException {
        if(this.getTipoInstitucionCeliminarpthisTipoInstitucionInstance() == null || this.getTipoInstitucionCeliminarpthisTipoInstitucionInstance().isNull()) {
            throw new NotNullArgumentException(TipoInstitucionCConstants.SERV_ID_ELIMINAR, TipoInstitucionCConstants.CLASS_ID, TipoInstitucionCConstants.ARG_ID_ELIMINAR_TIPOINSTITUCIONCELIMINARPTHISTIPOINSTITUCION, TipoInstitucionCConstants.SERV_ALIAS_ELIMINAR, TipoInstitucionCConstants.CLASS_ALIAS, TipoInstitucionCConstants.ARG_ALIAS_ELIMINAR_TIPOINSTITUCIONCELIMINARPTHISTIPOINSTITUCION);
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
