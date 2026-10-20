package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class CrearRequest {

    @JsonProperty("p_agrreporte_oid")
    private ReportesProcAlmOid paramReportecrearpagrReporte;
    
    @JsonIgnore
    private ReportesProcAlm paramReportecrearpagrReporteInstance;

    @JsonProperty("p_atresnumerico")
    private Boolean paramReportecrearpatrEsNumerico;

    @JsonProperty("p_atrorden")
    private Long paramReportecrearpatrOrden;

    @JsonProperty("p_atrdescripcion")
    private String paramReportecrearpatrDescripcion;

    @JsonProperty("p_atrtitulo")
    private String paramReportecrearpatrTitulo;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReportecrearpagrReporte();
        checkParamReportecrearpatrEsNumerico();
        checkParamReportecrearpatrTitulo();
    }

    @JsonIgnore
    public ReportesProcAlmOid getParamReportecrearpagrReporte() {
        return this.paramReportecrearpagrReporte;
    }
    
    public void setParamReportecrearpagrReporte(ReportesProcAlmOid paramReportecrearpagrReporte) {
        this.paramReportecrearpagrReporte = paramReportecrearpagrReporte;
    }

    @JsonIgnore
    public ReportesProcAlm getParamReportecrearpagrReporteInstance() {
        return this.paramReportecrearpagrReporteInstance;
    }
    
    public void setParamReportecrearpagrReporteInstance(ReportesProcAlm paramReportecrearpagrReporte) {
        this.paramReportecrearpagrReporteInstance = paramReportecrearpagrReporte;
        this.paramReportecrearpagrReporte = paramReportecrearpagrReporte != null ? paramReportecrearpagrReporte.getOid() : null;
    }

    private void checkParamReportecrearpagrReporte() throws NotNullArgumentException {
        if(this.getParamReportecrearpagrReporteInstance() == null || this.getParamReportecrearpagrReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_CREAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_CREAR_PARAMREPORTECREARPAGRREPORTE, ParamReporteConstants.SERV_ALIAS_CREAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_CREAR_PARAMREPORTECREARPAGRREPORTE);
        }
    }

    @JsonIgnore
    public Boolean getParamReportecrearpatrEsNumerico() {
        return this.paramReportecrearpatrEsNumerico;
    }
    
    public void setParamReportecrearpatrEsNumerico(Boolean paramReportecrearpatrEsNumerico) {
        this.paramReportecrearpatrEsNumerico = paramReportecrearpatrEsNumerico;
    }

    private void checkParamReportecrearpatrEsNumerico() throws NotNullArgumentException {
        if(this.getParamReportecrearpatrEsNumerico() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_CREAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_CREAR_PARAMREPORTECREARPATRESNUMERICO, ParamReporteConstants.SERV_ALIAS_CREAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_CREAR_PARAMREPORTECREARPATRESNUMERICO);
        }
    }

    @JsonIgnore
    public Long getParamReportecrearpatrOrden() {
        return this.paramReportecrearpatrOrden;
    }
    
    public void setParamReportecrearpatrOrden(Long paramReportecrearpatrOrden) {
        this.paramReportecrearpatrOrden = paramReportecrearpatrOrden;
    }

    @JsonIgnore
    public String getParamReportecrearpatrDescripcion() {
        return this.paramReportecrearpatrDescripcion;
    }
    
    public void setParamReportecrearpatrDescripcion(String paramReportecrearpatrDescripcion) {
        this.paramReportecrearpatrDescripcion = paramReportecrearpatrDescripcion;
    }

    @JsonIgnore
    public String getParamReportecrearpatrTitulo() {
        return this.paramReportecrearpatrTitulo;
    }
    
    public void setParamReportecrearpatrTitulo(String paramReportecrearpatrTitulo) {
        this.paramReportecrearpatrTitulo = paramReportecrearpatrTitulo;
    }

    private void checkParamReportecrearpatrTitulo() throws NotNullArgumentException {
        if(this.getParamReportecrearpatrTitulo() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_CREAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_CREAR_PARAMREPORTECREARPATRTITULO, ParamReporteConstants.SERV_ALIAS_CREAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_CREAR_PARAMREPORTECREARPATRTITULO);
        }
    }
}
