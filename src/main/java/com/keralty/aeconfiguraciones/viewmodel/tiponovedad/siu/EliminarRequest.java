package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;

public class EliminarRequest {

    @JsonProperty("p_thistiponovedad_json")
    private String tipoNovedadeliminarpthisTipoNovedad;
    
    @JsonIgnore
    private TipoNovedad tipoNovedadeliminarpthisTipoNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoNovedadeliminarpthisTipoNovedad();
    }

    @JsonIgnore
    public String getTipoNovedadeliminarpthisTipoNovedad() {
        return this.tipoNovedadeliminarpthisTipoNovedad;
    }
    
    public void setTipoNovedadeliminarpthisTipoNovedad(String tipoNovedadeliminarpthisTipoNovedad) {
        this.tipoNovedadeliminarpthisTipoNovedad = tipoNovedadeliminarpthisTipoNovedad;
    }

    @JsonIgnore
    public TipoNovedad getTipoNovedadeliminarpthisTipoNovedadInstance() {
        return this.tipoNovedadeliminarpthisTipoNovedadInstance;
    }
    
    public void setTipoNovedadeliminarpthisTipoNovedadInstance(TipoNovedad tipoNovedadeliminarpthisTipoNovedad) {
        this.tipoNovedadeliminarpthisTipoNovedadInstance = tipoNovedadeliminarpthisTipoNovedad;
        this.tipoNovedadeliminarpthisTipoNovedad = tipoNovedadeliminarpthisTipoNovedad != null ? tipoNovedadeliminarpthisTipoNovedad.getOid().toJson() : "";
    }

    private void checkTipoNovedadeliminarpthisTipoNovedad() throws NotNullArgumentException {
        if(this.getTipoNovedadeliminarpthisTipoNovedadInstance() == null || this.getTipoNovedadeliminarpthisTipoNovedadInstance().isNull()) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_ELIMINAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_ELIMINAR_TIPONOVEDADELIMINARPTHISTIPONOVEDAD, TipoNovedadConstants.SERV_ALIAS_ELIMINAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_ELIMINAR_TIPONOVEDADELIMINARPTHISTIPONOVEDAD);
        }
    }
}
