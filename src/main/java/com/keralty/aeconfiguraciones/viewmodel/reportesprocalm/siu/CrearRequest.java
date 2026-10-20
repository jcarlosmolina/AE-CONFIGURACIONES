package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;

public class CrearRequest {

    @JsonProperty("p_atrdescripcion")
    private String reportesProcAlmcrearpatrDescripcion;

    @JsonProperty("p_atrnombreproc")
    private String reportesProcAlmcrearpatrNombreProc;

    @JsonProperty("p_atrnombrefichero")
    private String reportesProcAlmcrearpatrNombreFichero;

    public void checkArguments() throws NotNullArgumentException {
        checkReportesProcAlmcrearpatrDescripcion();
        checkReportesProcAlmcrearpatrNombreProc();
        checkReportesProcAlmcrearpatrNombreFichero();
    }

    @JsonIgnore
    public String getReportesProcAlmcrearpatrDescripcion() {
        return this.reportesProcAlmcrearpatrDescripcion;
    }
    
    public void setReportesProcAlmcrearpatrDescripcion(String reportesProcAlmcrearpatrDescripcion) {
        this.reportesProcAlmcrearpatrDescripcion = reportesProcAlmcrearpatrDescripcion;
    }

    private void checkReportesProcAlmcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getReportesProcAlmcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_CREAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_CREAR_REPORTESPROCALMCREARPATRDESCRIPCION, ReportesProcAlmConstants.SERV_ALIAS_CREAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_CREAR_REPORTESPROCALMCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmcrearpatrNombreProc() {
        return this.reportesProcAlmcrearpatrNombreProc;
    }
    
    public void setReportesProcAlmcrearpatrNombreProc(String reportesProcAlmcrearpatrNombreProc) {
        this.reportesProcAlmcrearpatrNombreProc = reportesProcAlmcrearpatrNombreProc;
    }

    private void checkReportesProcAlmcrearpatrNombreProc() throws NotNullArgumentException {
        if(this.getReportesProcAlmcrearpatrNombreProc() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_CREAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_CREAR_REPORTESPROCALMCREARPATRNOMBREPROC, ReportesProcAlmConstants.SERV_ALIAS_CREAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_CREAR_REPORTESPROCALMCREARPATRNOMBREPROC);
        }
    }

    @JsonIgnore
    public String getReportesProcAlmcrearpatrNombreFichero() {
        return this.reportesProcAlmcrearpatrNombreFichero;
    }
    
    public void setReportesProcAlmcrearpatrNombreFichero(String reportesProcAlmcrearpatrNombreFichero) {
        this.reportesProcAlmcrearpatrNombreFichero = reportesProcAlmcrearpatrNombreFichero;
    }

    private void checkReportesProcAlmcrearpatrNombreFichero() throws NotNullArgumentException {
        if(this.getReportesProcAlmcrearpatrNombreFichero() == null) {
            throw new NotNullArgumentException(ReportesProcAlmConstants.SERV_ID_CREAR, ReportesProcAlmConstants.CLASS_ID, ReportesProcAlmConstants.ARG_ID_CREAR_REPORTESPROCALMCREARPATRNOMBREFICHERO, ReportesProcAlmConstants.SERV_ALIAS_CREAR, ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ARG_ALIAS_CREAR_REPORTESPROCALMCREARPATRNOMBREFICHERO);
        }
    }
}
