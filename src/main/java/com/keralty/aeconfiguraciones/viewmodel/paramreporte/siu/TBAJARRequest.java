package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class TBAJARRequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReporteTBAJARpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReporteTBAJARpthisParamReporteInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReporteTBAJARpthisParamReporte();
    }

    @JsonIgnore
    public ParamReporteOid getParamReporteTBAJARpthisParamReporte() {
        return this.paramReporteTBAJARpthisParamReporte;
    }
    
    public void setParamReporteTBAJARpthisParamReporte(ParamReporteOid paramReporteTBAJARpthisParamReporte) {
        this.paramReporteTBAJARpthisParamReporte = paramReporteTBAJARpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReporteTBAJARpthisParamReporteInstance() {
        return this.paramReporteTBAJARpthisParamReporteInstance;
    }
    
    public void setParamReporteTBAJARpthisParamReporteInstance(ParamReporte paramReporteTBAJARpthisParamReporte) {
        this.paramReporteTBAJARpthisParamReporteInstance = paramReporteTBAJARpthisParamReporte;
        this.paramReporteTBAJARpthisParamReporte = paramReporteTBAJARpthisParamReporte != null ? paramReporteTBAJARpthisParamReporte.getOid() : null;
    }

    private void checkParamReporteTBAJARpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReporteTBAJARpthisParamReporteInstance() == null || this.getParamReporteTBAJARpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TBAJAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TBAJAR_PARAMREPORTETBAJARPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_TBAJAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TBAJAR_PARAMREPORTETBAJARPTHISPARAMREPORTE);
        }
    }
}
