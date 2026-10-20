package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class EvGenerarRequest {

    @JsonProperty("p_thisreportesprocalm_oid")
    private ReportesProcAlmOid reportesProcAlmevGenerarpthisReportesProcAlm;
    
    @JsonIgnore
    private ReportesProcAlm reportesProcAlmevGenerarpthisReportesProcAlmInstance;

    @JsonProperty("pvalorargumentos")
    private String reportesProcAlmevGenerarpValorArgumentos;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesProcAlmevGenerarpthisReportesProcAlm();
    }

    @JsonIgnore
    public ReportesProcAlmOid getReportesProcAlmevGenerarpthisReportesProcAlm() {
        return this.reportesProcAlmevGenerarpthisReportesProcAlm;
    }
    
    public void setReportesProcAlmevGenerarpthisReportesProcAlm(ReportesProcAlmOid reportesProcAlmevGenerarpthisReportesProcAlm) {
        this.reportesProcAlmevGenerarpthisReportesProcAlm = reportesProcAlmevGenerarpthisReportesProcAlm;
    }

    @JsonIgnore
    public ReportesProcAlm getReportesProcAlmevGenerarpthisReportesProcAlmInstance() {
        return this.reportesProcAlmevGenerarpthisReportesProcAlmInstance;
    }
    
    public void setReportesProcAlmevGenerarpthisReportesProcAlmInstance(ReportesProcAlm reportesProcAlmevGenerarpthisReportesProcAlm) {
        this.reportesProcAlmevGenerarpthisReportesProcAlmInstance = reportesProcAlmevGenerarpthisReportesProcAlm;
        this.reportesProcAlmevGenerarpthisReportesProcAlm = reportesProcAlmevGenerarpthisReportesProcAlm != null ? reportesProcAlmevGenerarpthisReportesProcAlm.getOid() : null;
    }

    private void checkReportesProcAlmevGenerarpthisReportesProcAlm() throws NotNullArgumentException {
        if(this.getReportesProcAlmevGenerarpthisReportesProcAlmInstance() == null || this.getReportesProcAlmevGenerarpthisReportesProcAlmInstance().isNull()) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_EVGENERAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_EVGENERAR_REPORTESPROCALMEVGENERARPTHISREPORTESPROCALM, ReportesProcAlmConstants.SERV_ALIAS_EVGENERAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_EVGENERAR_REPORTESPROCALMEVGENERARPTHISREPORTESPROCALM);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmevGenerarpValorArgumentos() {
        return this.reportesProcAlmevGenerarpValorArgumentos;
    }
    
    public void setReportesProcAlmevGenerarpValorArgumentos(String reportesProcAlmevGenerarpValorArgumentos) {
        this.reportesProcAlmevGenerarpValorArgumentos = reportesProcAlmevGenerarpValorArgumentos;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
