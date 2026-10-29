package com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import java.util.HashMap;
import java.util.Map;

public class EvGenerarResponse extends ServiceResponse {

    @JsonProperty("oafichero")
    private byte[] reportesProcAlmevGeneraroaFichero;

    @JsonProperty("oanombrefichero")
    private String reportesProcAlmevGeneraroaNombreFichero;

    public EvGenerarResponse() {
        super();
    }

    @JsonIgnore
    public byte[] getReportesProcAlmevGeneraroaFichero() {
        return this.reportesProcAlmevGeneraroaFichero;
    }
    
    public void setReportesProcAlmevGeneraroaFichero(byte[] reportesProcAlmevGeneraroaFichero) {
        this.reportesProcAlmevGeneraroaFichero = reportesProcAlmevGeneraroaFichero;
    }

    @JsonIgnore
    public String getReportesProcAlmevGeneraroaNombreFichero() {
        return this.reportesProcAlmevGeneraroaNombreFichero;
    }
    
    public void setReportesProcAlmevGeneraroaNombreFichero(String reportesProcAlmevGeneraroaNombreFichero) {
        this.reportesProcAlmevGeneraroaNombreFichero = reportesProcAlmevGeneraroaNombreFichero;
    }

    @Override
    public Map<String, Object> buildOutArgs() {
        Map<String, Object> outArgs = new HashMap<>();
        outArgs.put("reportesProcAlmevGeneraroaFichero", reportesProcAlmevGeneraroaFichero);
        outArgs.put("reportesProcAlmevGeneraroaNombreFichero", reportesProcAlmevGeneraroaNombreFichero);
        return outArgs;
    }
}
