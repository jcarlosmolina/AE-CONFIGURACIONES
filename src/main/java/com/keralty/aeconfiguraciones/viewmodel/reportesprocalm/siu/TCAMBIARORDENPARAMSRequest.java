package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class TCAMBIARORDENPARAMSRequest {

    @JsonProperty("p_thisreportesprocalm_oid")
    private ReportesProcAlmOid reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm;
    
    @JsonIgnore
    private ReportesProcAlm reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance;

    @JsonProperty("ptordenactual")
    private Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual;

    @JsonProperty("ptnuevoorden")
    private Long reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm();
        checkReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual();
        checkReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden();
    }

    @JsonIgnore
    public ReportesProcAlmOid getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm() {
        return this.reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm;
    }
    
    public void setReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm(ReportesProcAlmOid reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm) {
        this.reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm = reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm;
    }

    @JsonIgnore
    public ReportesProcAlm getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance() {
        return this.reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance;
    }
    
    public void setReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance(ReportesProcAlm reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm) {
        this.reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance = reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm;
        this.reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm = reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm != null ? reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm.getOid() : null;
    }

    private void checkReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm() throws NotNullArgumentException {
        if(this.getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance() == null || this.getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance().isNull()) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTHISREPORTESPROCALM, ReportesProcAlmConstants.SERV_ALIAS_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTHISREPORTESPROCALM);
        }
    }

    @JsonIgnore
    public Long getReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual() {
        return this.reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual;
    }
    
    public void setReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual(Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual) {
        this.reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual = reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual;
    }

    private void checkReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual() throws NotNullArgumentException {
        if(this.getReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTORDENACTUAL, ReportesProcAlmConstants.SERV_ALIAS_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTORDENACTUAL);
        }
    }

    @JsonIgnore
    public Long getReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden() {
        return this.reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden;
    }
    
    public void setReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden(Long reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden) {
        this.reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden = reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden;
    }

    private void checkReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden() throws NotNullArgumentException {
        if(this.getReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTNUEVOORDEN, ReportesProcAlmConstants.SERV_ALIAS_TCAMBIARORDENPARAMS, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_TCAMBIARORDENPARAMS_REPORTESPROCALMTCAMBIARORDENPARAMSPTNUEVOORDEN);
        }
    }
}
