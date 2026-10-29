package com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;

public class EliminarRequest {

    @JsonProperty("p_thistipoidentificacion_json")
    private String tipoIdentificacioneliminarpthisTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion tipoIdentificacioneliminarpthisTipoIdentificacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoIdentificacioneliminarpthisTipoIdentificacion();
    }

    @JsonIgnore
    public String getTipoIdentificacioneliminarpthisTipoIdentificacion() {
        return this.tipoIdentificacioneliminarpthisTipoIdentificacion;
    }
    
    public void setTipoIdentificacioneliminarpthisTipoIdentificacion(String tipoIdentificacioneliminarpthisTipoIdentificacion) {
        this.tipoIdentificacioneliminarpthisTipoIdentificacion = tipoIdentificacioneliminarpthisTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getTipoIdentificacioneliminarpthisTipoIdentificacionInstance() {
        return this.tipoIdentificacioneliminarpthisTipoIdentificacionInstance;
    }
    
    public void setTipoIdentificacioneliminarpthisTipoIdentificacionInstance(TipoIdentificacion tipoIdentificacioneliminarpthisTipoIdentificacion) {
        this.tipoIdentificacioneliminarpthisTipoIdentificacionInstance = tipoIdentificacioneliminarpthisTipoIdentificacion;
        this.tipoIdentificacioneliminarpthisTipoIdentificacion = tipoIdentificacioneliminarpthisTipoIdentificacion != null ? tipoIdentificacioneliminarpthisTipoIdentificacion.getOid().toJson() : "";
    }

    private void checkTipoIdentificacioneliminarpthisTipoIdentificacion() throws NotNullArgumentException {
        if(this.getTipoIdentificacioneliminarpthisTipoIdentificacionInstance() == null || this.getTipoIdentificacioneliminarpthisTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_ELIMINAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_ELIMINAR_TIPOIDENTIFICACIONELIMINARPTHISTIPOIDENTIFICACION, TipoIdentificacionConstants.SERV_ALIAS_ELIMINAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_ELIMINAR_TIPOIDENTIFICACIONELIMINARPTHISTIPOIDENTIFICACION);
        }
    }
}
