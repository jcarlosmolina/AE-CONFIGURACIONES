package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class TDELETERequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReporteTDELETEpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReporteTDELETEpthisParamReporteInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReporteTDELETEpthisParamReporte();
    }

    @JsonIgnore
    public ParamReporteOid getParamReporteTDELETEpthisParamReporte() {
        return this.paramReporteTDELETEpthisParamReporte;
    }
    
    public void setParamReporteTDELETEpthisParamReporte(ParamReporteOid paramReporteTDELETEpthisParamReporte) {
        this.paramReporteTDELETEpthisParamReporte = paramReporteTDELETEpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReporteTDELETEpthisParamReporteInstance() {
        return this.paramReporteTDELETEpthisParamReporteInstance;
    }
    
    public void setParamReporteTDELETEpthisParamReporteInstance(ParamReporte paramReporteTDELETEpthisParamReporte) {
        this.paramReporteTDELETEpthisParamReporteInstance = paramReporteTDELETEpthisParamReporte;
        this.paramReporteTDELETEpthisParamReporte = paramReporteTDELETEpthisParamReporte != null ? paramReporteTDELETEpthisParamReporte.getOid() : null;
    }

    private void checkParamReporteTDELETEpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReporteTDELETEpthisParamReporteInstance() == null || this.getParamReporteTDELETEpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TDELETE, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TDELETE_PARAMREPORTETDELETEPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_TDELETE, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TDELETE_PARAMREPORTETDELETEPTHISPARAMREPORTE);
        }
    }
}
