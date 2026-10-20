package com.keralty.aeconfiguraciones.viewmodel.tiponovedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
public class DSTipoNovedadNull {

    @JsonProperty("codigolegal")
    private String dSTipoNovedadNullCodigoLegal;

    public DSTipoNovedadNull() {
    
    }

    public DSTipoNovedadNull(TipoNovedad instance) {
        if (instance != null) {
            this.dSTipoNovedadNullCodigoLegal = instance.getTipoNovedadCodigoLegal();
        }
    }

    @JsonIgnore
    public String getDSTipoNovedadNullCodigoLegal() {
        return this.dSTipoNovedadNullCodigoLegal;
    }
    
    public void setDSTipoNovedadNullCodigoLegal(String dSTipoNovedadNullCodigoLegal) {
        this.dSTipoNovedadNullCodigoLegal = dSTipoNovedadNullCodigoLegal;
    }
}
