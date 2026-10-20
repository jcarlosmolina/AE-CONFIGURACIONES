package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class ModificarRequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReportemodificarpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReportemodificarpthisParamReporteInstance;

    @JsonProperty("p_esnumerico")
    private Boolean paramReportemodificarpEsNumerico;

    @JsonProperty("p_descripcion")
    private String paramReportemodificarpDescripcion;

    @JsonProperty("p_titulo")
    private String paramReportemodificarpTitulo;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReportemodificarpthisParamReporte();
        checkParamReportemodificarpEsNumerico();
        checkParamReportemodificarpTitulo();
    }

    @JsonIgnore
    public ParamReporteOid getParamReportemodificarpthisParamReporte() {
        return this.paramReportemodificarpthisParamReporte;
    }
    
    public void setParamReportemodificarpthisParamReporte(ParamReporteOid paramReportemodificarpthisParamReporte) {
        this.paramReportemodificarpthisParamReporte = paramReportemodificarpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReportemodificarpthisParamReporteInstance() {
        return this.paramReportemodificarpthisParamReporteInstance;
    }
    
    public void setParamReportemodificarpthisParamReporteInstance(ParamReporte paramReportemodificarpthisParamReporte) {
        this.paramReportemodificarpthisParamReporteInstance = paramReportemodificarpthisParamReporte;
        this.paramReportemodificarpthisParamReporte = paramReportemodificarpthisParamReporte != null ? paramReportemodificarpthisParamReporte.getOid() : null;
    }

    private void checkParamReportemodificarpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReportemodificarpthisParamReporteInstance() == null || this.getParamReportemodificarpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_MODIFICAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_MODIFICAR_PARAMREPORTEMODIFICARPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_MODIFICAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_MODIFICAR_PARAMREPORTEMODIFICARPTHISPARAMREPORTE);
        }
    }

    @JsonIgnore
    public Boolean getParamReportemodificarpEsNumerico() {
        return this.paramReportemodificarpEsNumerico;
    }
    
    public void setParamReportemodificarpEsNumerico(Boolean paramReportemodificarpEsNumerico) {
        this.paramReportemodificarpEsNumerico = paramReportemodificarpEsNumerico;
    }

    private void checkParamReportemodificarpEsNumerico() throws NotNullArgumentException {
        if(this.getParamReportemodificarpEsNumerico() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_MODIFICAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_MODIFICAR_PARAMREPORTEMODIFICARPESNUMERICO, ParamReporteConstants.SERV_ALIAS_MODIFICAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_MODIFICAR_PARAMREPORTEMODIFICARPESNUMERICO);
        }
    }

    @JsonIgnore
    public String getParamReportemodificarpDescripcion() {
        return this.paramReportemodificarpDescripcion;
    }
    
    public void setParamReportemodificarpDescripcion(String paramReportemodificarpDescripcion) {
        this.paramReportemodificarpDescripcion = paramReportemodificarpDescripcion;
    }

    @JsonIgnore
    public String getParamReportemodificarpTitulo() {
        return this.paramReportemodificarpTitulo;
    }
    
    public void setParamReportemodificarpTitulo(String paramReportemodificarpTitulo) {
        this.paramReportemodificarpTitulo = paramReportemodificarpTitulo;
    }

    private void checkParamReportemodificarpTitulo() throws NotNullArgumentException {
        if(this.getParamReportemodificarpTitulo() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_MODIFICAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_MODIFICAR_PARAMREPORTEMODIFICARPTITULO, ParamReporteConstants.SERV_ALIAS_MODIFICAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_MODIFICAR_PARAMREPORTEMODIFICARPTITULO);
        }
    }
}
