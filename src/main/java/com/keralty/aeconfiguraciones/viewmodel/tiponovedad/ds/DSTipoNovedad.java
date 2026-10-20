package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
public class DSTipoNovedad {

    @JsonProperty("nombre")
    private String dSTipoNovedadNombre;

    @JsonProperty("aliastipo")
    private String dSTipoNovedadAliasTipo;

    @JsonProperty("descripcion")
    private String dSTipoNovedadDescripcion;

    @JsonProperty("codigolegal")
    private String dSTipoNovedadCodigoLegal;

    public DSTipoNovedad() {
    
    }

    public DSTipoNovedad(TipoNovedad instance) {
        if (instance != null) {
            this.dSTipoNovedadNombre = instance.getTipoNovedadNombre();
            this.dSTipoNovedadAliasTipo = instance.getTipoNovedadAliasTipo();
            this.dSTipoNovedadDescripcion = instance.getTipoNovedadDescripcion();
            this.dSTipoNovedadCodigoLegal = instance.getTipoNovedadCodigoLegal();
        }
    }

    @JsonIgnore
    public String getDSTipoNovedadNombre() {
        return this.dSTipoNovedadNombre;
    }
    
    public void setDSTipoNovedadNombre(String dSTipoNovedadNombre) {
        this.dSTipoNovedadNombre = dSTipoNovedadNombre;
    }

    @JsonIgnore
    public String getDSTipoNovedadAliasTipo() {
        return this.dSTipoNovedadAliasTipo;
    }
    
    public void setDSTipoNovedadAliasTipo(String dSTipoNovedadAliasTipo) {
        this.dSTipoNovedadAliasTipo = dSTipoNovedadAliasTipo;
    }

    @JsonIgnore
    public String getDSTipoNovedadDescripcion() {
        return this.dSTipoNovedadDescripcion;
    }
    
    public void setDSTipoNovedadDescripcion(String dSTipoNovedadDescripcion) {
        this.dSTipoNovedadDescripcion = dSTipoNovedadDescripcion;
    }

    @JsonIgnore
    public String getDSTipoNovedadCodigoLegal() {
        return this.dSTipoNovedadCodigoLegal;
    }
    
    public void setDSTipoNovedadCodigoLegal(String dSTipoNovedadCodigoLegal) {
        this.dSTipoNovedadCodigoLegal = dSTipoNovedadCodigoLegal;
    }
}
