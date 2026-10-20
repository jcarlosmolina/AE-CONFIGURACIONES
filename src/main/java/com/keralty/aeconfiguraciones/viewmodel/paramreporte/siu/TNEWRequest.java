package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class TNEWRequest {

    @JsonProperty("pt_p_agrreporte_oid")
    private ReportesProcAlmOid paramReporteTNEWptpagrReporte;
    
    @JsonIgnore
    private ReportesProcAlm paramReporteTNEWptpagrReporteInstance;

    @JsonProperty("pt_p_atresnumerico")
    private Boolean paramReporteTNEWptpatrEsNumerico;

    @JsonProperty("pt_p_atrdescripcion")
    private String paramReporteTNEWptpatrDescripcion;

    @JsonProperty("pt_p_atrtitulo")
    private String paramReporteTNEWptpatrTitulo;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReporteTNEWptpagrReporte();
        checkParamReporteTNEWptpatrEsNumerico();
        checkParamReporteTNEWptpatrTitulo();
    }

    @JsonIgnore
    public ReportesProcAlmOid getParamReporteTNEWptpagrReporte() {
        return this.paramReporteTNEWptpagrReporte;
    }
    
    public void setParamReporteTNEWptpagrReporte(ReportesProcAlmOid paramReporteTNEWptpagrReporte) {
        this.paramReporteTNEWptpagrReporte = paramReporteTNEWptpagrReporte;
    }

    @JsonIgnore
    public ReportesProcAlm getParamReporteTNEWptpagrReporteInstance() {
        return this.paramReporteTNEWptpagrReporteInstance;
    }
    
    public void setParamReporteTNEWptpagrReporteInstance(ReportesProcAlm paramReporteTNEWptpagrReporte) {
        this.paramReporteTNEWptpagrReporteInstance = paramReporteTNEWptpagrReporte;
        this.paramReporteTNEWptpagrReporte = paramReporteTNEWptpagrReporte != null ? paramReporteTNEWptpagrReporte.getOid() : null;
    }

    private void checkParamReporteTNEWptpagrReporte() throws NotNullArgumentException {
        if(this.getParamReporteTNEWptpagrReporteInstance() == null || this.getParamReporteTNEWptpagrReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TNEW, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TNEW_PARAMREPORTETNEWPTPAGRREPORTE, ParamReporteConstants.SERV_ALIAS_TNEW, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TNEW_PARAMREPORTETNEWPTPAGRREPORTE);
        }
    }

    @JsonIgnore
    public Boolean getParamReporteTNEWptpatrEsNumerico() {
        return this.paramReporteTNEWptpatrEsNumerico;
    }
    
    public void setParamReporteTNEWptpatrEsNumerico(Boolean paramReporteTNEWptpatrEsNumerico) {
        this.paramReporteTNEWptpatrEsNumerico = paramReporteTNEWptpatrEsNumerico;
    }

    private void checkParamReporteTNEWptpatrEsNumerico() throws NotNullArgumentException {
        if(this.getParamReporteTNEWptpatrEsNumerico() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TNEW, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TNEW_PARAMREPORTETNEWPTPATRESNUMERICO, ParamReporteConstants.SERV_ALIAS_TNEW, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TNEW_PARAMREPORTETNEWPTPATRESNUMERICO);
        }
    }

    @JsonIgnore
    public String getParamReporteTNEWptpatrDescripcion() {
        return this.paramReporteTNEWptpatrDescripcion;
    }
    
    public void setParamReporteTNEWptpatrDescripcion(String paramReporteTNEWptpatrDescripcion) {
        this.paramReporteTNEWptpatrDescripcion = paramReporteTNEWptpatrDescripcion;
    }

    @JsonIgnore
    public String getParamReporteTNEWptpatrTitulo() {
        return this.paramReporteTNEWptpatrTitulo;
    }
    
    public void setParamReporteTNEWptpatrTitulo(String paramReporteTNEWptpatrTitulo) {
        this.paramReporteTNEWptpatrTitulo = paramReporteTNEWptpatrTitulo;
    }

    private void checkParamReporteTNEWptpatrTitulo() throws NotNullArgumentException {
        if(this.getParamReporteTNEWptpatrTitulo() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TNEW, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TNEW_PARAMREPORTETNEWPTPATRTITULO, ParamReporteConstants.SERV_ALIAS_TNEW, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TNEW_PARAMREPORTETNEWPTPATRTITULO);
        }
    }
}
