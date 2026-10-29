package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;

public class ModificarRequest {

    @JsonProperty("p_thisreportesprocalm_oid")
    private ReportesProcAlmOid reportesProcAlmmodificarpthisReportesProcAlm;
    
    @JsonIgnore
    private ReportesProcAlm reportesProcAlmmodificarpthisReportesProcAlmInstance;

    @JsonProperty("p_descripcion")
    private String reportesProcAlmmodificarpDescripcion;

    @JsonProperty("p_nombreproc")
    private String reportesProcAlmmodificarpNombreProc;

    @JsonProperty("p_nombrefichero")
    private String reportesProcAlmmodificarpNombreFichero;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesProcAlmmodificarpthisReportesProcAlm();
        checkReportesProcAlmmodificarpDescripcion();
        checkReportesProcAlmmodificarpNombreProc();
        checkReportesProcAlmmodificarpNombreFichero();
    }

    @JsonIgnore
    public ReportesProcAlmOid getReportesProcAlmmodificarpthisReportesProcAlm() {
        return this.reportesProcAlmmodificarpthisReportesProcAlm;
    }
    
    public void setReportesProcAlmmodificarpthisReportesProcAlm(ReportesProcAlmOid reportesProcAlmmodificarpthisReportesProcAlm) {
        this.reportesProcAlmmodificarpthisReportesProcAlm = reportesProcAlmmodificarpthisReportesProcAlm;
    }

    @JsonIgnore
    public ReportesProcAlm getReportesProcAlmmodificarpthisReportesProcAlmInstance() {
        return this.reportesProcAlmmodificarpthisReportesProcAlmInstance;
    }
    
    public void setReportesProcAlmmodificarpthisReportesProcAlmInstance(ReportesProcAlm reportesProcAlmmodificarpthisReportesProcAlm) {
        this.reportesProcAlmmodificarpthisReportesProcAlmInstance = reportesProcAlmmodificarpthisReportesProcAlm;
        this.reportesProcAlmmodificarpthisReportesProcAlm = reportesProcAlmmodificarpthisReportesProcAlm != null ? reportesProcAlmmodificarpthisReportesProcAlm.getOid() : null;
    }

    private void checkReportesProcAlmmodificarpthisReportesProcAlm() throws NotNullArgumentException {
        if(this.getReportesProcAlmmodificarpthisReportesProcAlmInstance() == null || this.getReportesProcAlmmodificarpthisReportesProcAlmInstance().isNull()) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_MODIFICAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_MODIFICAR_REPORTESPROCALMMODIFICARPTHISREPORTESPROCALM, ReportesProcAlmConstants.SERV_ALIAS_MODIFICAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_MODIFICAR_REPORTESPROCALMMODIFICARPTHISREPORTESPROCALM);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmmodificarpDescripcion() {
        return this.reportesProcAlmmodificarpDescripcion;
    }
    
    public void setReportesProcAlmmodificarpDescripcion(String reportesProcAlmmodificarpDescripcion) {
        this.reportesProcAlmmodificarpDescripcion = reportesProcAlmmodificarpDescripcion;
    }

    private void checkReportesProcAlmmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getReportesProcAlmmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_MODIFICAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_MODIFICAR_REPORTESPROCALMMODIFICARPDESCRIPCION, ReportesProcAlmConstants.SERV_ALIAS_MODIFICAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_MODIFICAR_REPORTESPROCALMMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmmodificarpNombreProc() {
        return this.reportesProcAlmmodificarpNombreProc;
    }
    
    public void setReportesProcAlmmodificarpNombreProc(String reportesProcAlmmodificarpNombreProc) {
        this.reportesProcAlmmodificarpNombreProc = reportesProcAlmmodificarpNombreProc;
    }

    private void checkReportesProcAlmmodificarpNombreProc() throws NotNullArgumentException {
        if(this.getReportesProcAlmmodificarpNombreProc() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_MODIFICAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_MODIFICAR_REPORTESPROCALMMODIFICARPNOMBREPROC, ReportesProcAlmConstants.SERV_ALIAS_MODIFICAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_MODIFICAR_REPORTESPROCALMMODIFICARPNOMBREPROC);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmmodificarpNombreFichero() {
        return this.reportesProcAlmmodificarpNombreFichero;
    }
    
    public void setReportesProcAlmmodificarpNombreFichero(String reportesProcAlmmodificarpNombreFichero) {
        this.reportesProcAlmmodificarpNombreFichero = reportesProcAlmmodificarpNombreFichero;
    }

    private void checkReportesProcAlmmodificarpNombreFichero() throws NotNullArgumentException {
        if(this.getReportesProcAlmmodificarpNombreFichero() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_MODIFICAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_MODIFICAR_REPORTESPROCALMMODIFICARPNOMBREFICHERO, ReportesProcAlmConstants.SERV_ALIAS_MODIFICAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_MODIFICAR_REPORTESPROCALMMODIFICARPNOMBREFICHERO);
        }
    }
}
