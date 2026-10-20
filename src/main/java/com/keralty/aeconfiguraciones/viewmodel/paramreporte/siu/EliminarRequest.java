package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class EliminarRequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReporteeliminarpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReporteeliminarpthisParamReporteInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReporteeliminarpthisParamReporte();
    }

    @JsonIgnore
    public ParamReporteOid getParamReporteeliminarpthisParamReporte() {
        return this.paramReporteeliminarpthisParamReporte;
    }
    
    public void setParamReporteeliminarpthisParamReporte(ParamReporteOid paramReporteeliminarpthisParamReporte) {
        this.paramReporteeliminarpthisParamReporte = paramReporteeliminarpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReporteeliminarpthisParamReporteInstance() {
        return this.paramReporteeliminarpthisParamReporteInstance;
    }
    
    public void setParamReporteeliminarpthisParamReporteInstance(ParamReporte paramReporteeliminarpthisParamReporte) {
        this.paramReporteeliminarpthisParamReporteInstance = paramReporteeliminarpthisParamReporte;
        this.paramReporteeliminarpthisParamReporte = paramReporteeliminarpthisParamReporte != null ? paramReporteeliminarpthisParamReporte.getOid() : null;
    }

    private void checkParamReporteeliminarpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReporteeliminarpthisParamReporteInstance() == null || this.getParamReporteeliminarpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_ELIMINAR, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_ELIMINAR_PARAMREPORTEELIMINARPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_ELIMINAR, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_ELIMINAR_PARAMREPORTEELIMINARPTHISPARAMREPORTE);
        }
    }
}
