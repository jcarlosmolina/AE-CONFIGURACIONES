package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class TSUBIRRequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReporteTSUBIRpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReporteTSUBIRpthisParamReporteInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReporteTSUBIRpthisParamReporte();
    }

    @JsonIgnore
    public ParamReporteOid getParamReporteTSUBIRpthisParamReporte() {
        return this.paramReporteTSUBIRpthisParamReporte;
    }
    
    public void setParamReporteTSUBIRpthisParamReporte(ParamReporteOid paramReporteTSUBIRpthisParamReporte) {
        this.paramReporteTSUBIRpthisParamReporte = paramReporteTSUBIRpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReporteTSUBIRpthisParamReporteInstance() {
        return this.paramReporteTSUBIRpthisParamReporteInstance;
    }
    
    public void setParamReporteTSUBIRpthisParamReporteInstance(ParamReporte paramReporteTSUBIRpthisParamReporte) {
        this.paramReporteTSUBIRpthisParamReporteInstance = paramReporteTSUBIRpthisParamReporte;
        this.paramReporteTSUBIRpthisParamReporte = paramReporteTSUBIRpthisParamReporte != null ? paramReporteTSUBIRpthisParamReporte.getOid() : null;
    }

    private void checkParamReporteTSUBIRpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReporteTSUBIRpthisParamReporteInstance() == null || this.getParamReporteTSUBIRpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_TSUBIR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_TSUBIR_PARAMREPORTETSUBIRPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_TSUBIR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_TSUBIR_PARAMREPORTETSUBIRPTHISPARAMREPORTE);
        }
    }
}
