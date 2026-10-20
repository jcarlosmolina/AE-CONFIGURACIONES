package com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesDominioConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesDominioOid;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;

public class ModificarRequest {

    @JsonProperty("p_thisreportesdominio_oid")
    private ReportesDominioOid reportesDominiomodificarpthisReportesDominio;
    
    @JsonIgnore
    private ReportesDominio reportesDominiomodificarpthisReportesDominioInstance;

    @JsonProperty("p_enviarauto")
    private Boolean reportesDominiomodificarpEnviarAuto;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesDominiomodificarpthisReportesDominio();
        checkReportesDominiomodificarpEnviarAuto();
    }

    @JsonIgnore
    public ReportesDominioOid getReportesDominiomodificarpthisReportesDominio() {
        return this.reportesDominiomodificarpthisReportesDominio;
    }
    
    public void setReportesDominiomodificarpthisReportesDominio(ReportesDominioOid reportesDominiomodificarpthisReportesDominio) {
        this.reportesDominiomodificarpthisReportesDominio = reportesDominiomodificarpthisReportesDominio;
    }

    @JsonIgnore
    public ReportesDominio getReportesDominiomodificarpthisReportesDominioInstance() {
        return this.reportesDominiomodificarpthisReportesDominioInstance;
    }
    
    public void setReportesDominiomodificarpthisReportesDominioInstance(ReportesDominio reportesDominiomodificarpthisReportesDominio) {
        this.reportesDominiomodificarpthisReportesDominioInstance = reportesDominiomodificarpthisReportesDominio;
        this.reportesDominiomodificarpthisReportesDominio = reportesDominiomodificarpthisReportesDominio != null ? reportesDominiomodificarpthisReportesDominio.getOid() : null;
    }

    private void checkReportesDominiomodificarpthisReportesDominio() throws NotNullArgumentException {
        if(this.getReportesDominiomodificarpthisReportesDominioInstance() == null || this.getReportesDominiomodificarpthisReportesDominioInstance().isNull()) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_MODIFICAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_MODIFICAR_REPORTESDOMINIOMODIFICARPTHISREPORTESDOMINIO, ReportesDominioConstants.SERV_ALIAS_MODIFICAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_MODIFICAR_REPORTESDOMINIOMODIFICARPTHISREPORTESDOMINIO);
        }
    }

    @JsonIgnore
    public Boolean getReportesDominiomodificarpEnviarAuto() {
        return this.reportesDominiomodificarpEnviarAuto;
    }
    
    public void setReportesDominiomodificarpEnviarAuto(Boolean reportesDominiomodificarpEnviarAuto) {
        this.reportesDominiomodificarpEnviarAuto = reportesDominiomodificarpEnviarAuto;
    }

    private void checkReportesDominiomodificarpEnviarAuto() throws NotNullArgumentException {
        if(this.getReportesDominiomodificarpEnviarAuto() == null) {
            throw new NotNullArgumentException(ReportesDominioConstants.SERV_ID_MODIFICAR, ReportesDominioConstants.CLASS_ID, ReportesDominioConstants.ARG_ID_MODIFICAR_REPORTESDOMINIOMODIFICARPENVIARAUTO, ReportesDominioConstants.SERV_ALIAS_MODIFICAR, ReportesDominioConstants.CLASS_ALIAS, ReportesDominioConstants.ARG_ALIAS_MODIFICAR_REPORTESDOMINIOMODIFICARPENVIARAUTO);
        }
    }
}
