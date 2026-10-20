package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;

public class ModificarRequest {

    @JsonProperty("p_thistiponovedad_json")
    private String tipoNovedadmodificarpthisTipoNovedad;
    
    @JsonIgnore
    private TipoNovedad tipoNovedadmodificarpthisTipoNovedadInstance;

    @JsonProperty("p_aliastipo")
    private String tipoNovedadmodificarpAliasTipo;

    @JsonProperty("p_nombre")
    private String tipoNovedadmodificarpNombre;

    @JsonProperty("p_descripcion")
    private String tipoNovedadmodificarpDescripcion;

    @JsonProperty("p_codigolegal")
    private String tipoNovedadmodificarpCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoNovedadmodificarpthisTipoNovedad();
        checkTipoNovedadmodificarpAliasTipo();
        checkTipoNovedadmodificarpNombre();
        checkTipoNovedadmodificarpDescripcion();
        checkTipoNovedadmodificarpCodigoLegal();
    }

    @JsonIgnore
    public String getTipoNovedadmodificarpthisTipoNovedad() {
        return this.tipoNovedadmodificarpthisTipoNovedad;
    }
    
    public void setTipoNovedadmodificarpthisTipoNovedad(String tipoNovedadmodificarpthisTipoNovedad) {
        this.tipoNovedadmodificarpthisTipoNovedad = tipoNovedadmodificarpthisTipoNovedad;
    }

    @JsonIgnore
    public TipoNovedad getTipoNovedadmodificarpthisTipoNovedadInstance() {
        return this.tipoNovedadmodificarpthisTipoNovedadInstance;
    }
    
    public void setTipoNovedadmodificarpthisTipoNovedadInstance(TipoNovedad tipoNovedadmodificarpthisTipoNovedad) {
        this.tipoNovedadmodificarpthisTipoNovedadInstance = tipoNovedadmodificarpthisTipoNovedad;
        this.tipoNovedadmodificarpthisTipoNovedad = tipoNovedadmodificarpthisTipoNovedad != null ? tipoNovedadmodificarpthisTipoNovedad.getOid().toJson() : "";
    }

    private void checkTipoNovedadmodificarpthisTipoNovedad() throws NotNullArgumentException {
        if(this.getTipoNovedadmodificarpthisTipoNovedadInstance() == null || this.getTipoNovedadmodificarpthisTipoNovedadInstance().isNull()) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_MODIFICAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_MODIFICAR_TIPONOVEDADMODIFICARPTHISTIPONOVEDAD, TipoNovedadConstants.SERV_ALIAS_MODIFICAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_MODIFICAR_TIPONOVEDADMODIFICARPTHISTIPONOVEDAD);
        }
    }

    @JsonIgnore
    public String getTipoNovedadmodificarpAliasTipo() {
        return this.tipoNovedadmodificarpAliasTipo;
    }
    
    public void setTipoNovedadmodificarpAliasTipo(String tipoNovedadmodificarpAliasTipo) {
        this.tipoNovedadmodificarpAliasTipo = tipoNovedadmodificarpAliasTipo;
    }

    private void checkTipoNovedadmodificarpAliasTipo() throws NotNullArgumentException {
        if(this.getTipoNovedadmodificarpAliasTipo() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_MODIFICAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_MODIFICAR_TIPONOVEDADMODIFICARPALIASTIPO, TipoNovedadConstants.SERV_ALIAS_MODIFICAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_MODIFICAR_TIPONOVEDADMODIFICARPALIASTIPO);
        }
    }

    @JsonIgnore
    public String getTipoNovedadmodificarpNombre() {
        return this.tipoNovedadmodificarpNombre;
    }
    
    public void setTipoNovedadmodificarpNombre(String tipoNovedadmodificarpNombre) {
        this.tipoNovedadmodificarpNombre = tipoNovedadmodificarpNombre;
    }

    private void checkTipoNovedadmodificarpNombre() throws NotNullArgumentException {
        if(this.getTipoNovedadmodificarpNombre() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_MODIFICAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_MODIFICAR_TIPONOVEDADMODIFICARPNOMBRE, TipoNovedadConstants.SERV_ALIAS_MODIFICAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_MODIFICAR_TIPONOVEDADMODIFICARPNOMBRE);
        }
    }

    @JsonIgnore
    public String getTipoNovedadmodificarpDescripcion() {
        return this.tipoNovedadmodificarpDescripcion;
    }
    
    public void setTipoNovedadmodificarpDescripcion(String tipoNovedadmodificarpDescripcion) {
        this.tipoNovedadmodificarpDescripcion = tipoNovedadmodificarpDescripcion;
    }

    private void checkTipoNovedadmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getTipoNovedadmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_MODIFICAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_MODIFICAR_TIPONOVEDADMODIFICARPDESCRIPCION, TipoNovedadConstants.SERV_ALIAS_MODIFICAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_MODIFICAR_TIPONOVEDADMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getTipoNovedadmodificarpCodigoLegal() {
        return this.tipoNovedadmodificarpCodigoLegal;
    }
    
    public void setTipoNovedadmodificarpCodigoLegal(String tipoNovedadmodificarpCodigoLegal) {
        this.tipoNovedadmodificarpCodigoLegal = tipoNovedadmodificarpCodigoLegal;
    }

    private void checkTipoNovedadmodificarpCodigoLegal() throws NotNullArgumentException {
        if(this.getTipoNovedadmodificarpCodigoLegal() == null) {
            throw new NotNullArgumentException(TipoNovedadConstants.SERV_ID_MODIFICAR, TipoNovedadConstants.CLASS_ID, TipoNovedadConstants.ARG_ID_MODIFICAR_TIPONOVEDADMODIFICARPCODIGOLEGAL, TipoNovedadConstants.SERV_ALIAS_MODIFICAR, TipoNovedadConstants.CLASS_ALIAS, TipoNovedadConstants.ARG_ALIAS_MODIFICAR_TIPONOVEDADMODIFICARPCODIGOLEGAL);
        }
    }
}
