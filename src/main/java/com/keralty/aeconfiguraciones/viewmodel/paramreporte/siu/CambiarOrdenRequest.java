package com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamReporteConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;

public class CambiarOrdenRequest {

    @JsonProperty("p_thisparamreporte_oid")
    private ParamReporteOid paramReportecambiarOrdenpthisParamReporte;
    
    @JsonIgnore
    private ParamReporte paramReportecambiarOrdenpthisParamReporteInstance;

    @JsonProperty("nuevoorden")
    private Long paramReportecambiarOrdennuevoOrden;

    public void checkArguments() throws NotNullArgumentException {
        checkParamReportecambiarOrdenpthisParamReporte();
        checkParamReportecambiarOrdennuevoOrden();
    }

    @JsonIgnore
    public ParamReporteOid getParamReportecambiarOrdenpthisParamReporte() {
        return this.paramReportecambiarOrdenpthisParamReporte;
    }
    
    public void setParamReportecambiarOrdenpthisParamReporte(ParamReporteOid paramReportecambiarOrdenpthisParamReporte) {
        this.paramReportecambiarOrdenpthisParamReporte = paramReportecambiarOrdenpthisParamReporte;
    }

    @JsonIgnore
    public ParamReporte getParamReportecambiarOrdenpthisParamReporteInstance() {
        return this.paramReportecambiarOrdenpthisParamReporteInstance;
    }
    
    public void setParamReportecambiarOrdenpthisParamReporteInstance(ParamReporte paramReportecambiarOrdenpthisParamReporte) {
        this.paramReportecambiarOrdenpthisParamReporteInstance = paramReportecambiarOrdenpthisParamReporte;
        this.paramReportecambiarOrdenpthisParamReporte = paramReportecambiarOrdenpthisParamReporte != null ? paramReportecambiarOrdenpthisParamReporte.getOid() : null;
    }

    private void checkParamReportecambiarOrdenpthisParamReporte() throws NotNullArgumentException {
        if(this.getParamReportecambiarOrdenpthisParamReporteInstance() == null || this.getParamReportecambiarOrdenpthisParamReporteInstance().isNull()) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_CAMBIARORDEN, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_CAMBIARORDEN_PARAMREPORTECAMBIARORDENPTHISPARAMREPORTE, ParamReporteConstants.SERV_ALIAS_CAMBIARORDEN, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_CAMBIARORDEN_PARAMREPORTECAMBIARORDENPTHISPARAMREPORTE);
        }
    }

    @JsonIgnore
    public Long getParamReportecambiarOrdennuevoOrden() {
        return this.paramReportecambiarOrdennuevoOrden;
    }
    
    public void setParamReportecambiarOrdennuevoOrden(Long paramReportecambiarOrdennuevoOrden) {
        this.paramReportecambiarOrdennuevoOrden = paramReportecambiarOrdennuevoOrden;
    }

    private void checkParamReportecambiarOrdennuevoOrden() throws NotNullArgumentException {
        if(this.getParamReportecambiarOrdennuevoOrden() == null) {
            throw new NotNullArgumentException(ParamReporteConstants.SERV_ID_CAMBIARORDEN, ParamReporteConstants.CLASS_ID, ParamReporteConstants.ARG_ID_CAMBIARORDEN_PARAMREPORTECAMBIARORDENNUEVOORDEN, ParamReporteConstants.SERV_ALIAS_CAMBIARORDEN, ParamReporteConstants.CLASS_ALIAS, ParamReporteConstants.ARG_ALIAS_CAMBIARORDEN_PARAMREPORTECAMBIARORDENNUEVOORDEN);
        }
    }
}
