package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogLlamadasAEConstants;
import java.sql.Timestamp;

public class CreateinstanceRequest {

    @JsonProperty("p_atrfechahora")
    private Timestamp logLlamadasAEcreateinstancepatrFechaHora;

    @JsonProperty("p_atrcapacidad")
    private String logLlamadasAEcreateinstancepatrCapacidad;

    @JsonProperty("p_atrservicio")
    private String logLlamadasAEcreateinstancepatrServicio;

    @JsonProperty("p_atrsolicitud")
    private String logLlamadasAEcreateinstancepatrSolicitud;

    @JsonProperty("p_atrdatosrecibidos")
    private String logLlamadasAEcreateinstancepatrDatosRecibidos;

    @JsonProperty("p_atrdatosenviados")
    private String logLlamadasAEcreateinstancepatrDatosEnviados;

    @JsonProperty("p_atrtiposervicio")
    private String logLlamadasAEcreateinstancepatrTipoServicio;

    @JsonProperty("p_atrtiempoejecucion")
    private Double logLlamadasAEcreateinstancepatrTiempoEjecucion;

    @JsonProperty("p_atrtextoerror")
    private String logLlamadasAEcreateinstancepatrTextoError;

    public void checkArguments() throws NotNullArgumentException {
        checkLogLlamadasAEcreateinstancepatrFechaHora();
        checkLogLlamadasAEcreateinstancepatrServicio();
        checkLogLlamadasAEcreateinstancepatrTipoServicio();
    }

    @JsonIgnore
    public Timestamp getLogLlamadasAEcreateinstancepatrFechaHora() {
        return this.logLlamadasAEcreateinstancepatrFechaHora;
    }
    
    public void setLogLlamadasAEcreateinstancepatrFechaHora(Timestamp logLlamadasAEcreateinstancepatrFechaHora) {
        this.logLlamadasAEcreateinstancepatrFechaHora = logLlamadasAEcreateinstancepatrFechaHora;
    }

    private void checkLogLlamadasAEcreateinstancepatrFechaHora() throws NotNullArgumentException {
        if(this.getLogLlamadasAEcreateinstancepatrFechaHora() == null) {
            throw new NotNullArgumentException(LogLlamadasAEConstants.SERV_ID_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ID, LogLlamadasAEConstants.ARG_ID_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRFECHAHORA, LogLlamadasAEConstants.SERV_ALIAS_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ALIAS, LogLlamadasAEConstants.ARG_ALIAS_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRFECHAHORA);
        }
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrCapacidad() {
        return this.logLlamadasAEcreateinstancepatrCapacidad;
    }
    
    public void setLogLlamadasAEcreateinstancepatrCapacidad(String logLlamadasAEcreateinstancepatrCapacidad) {
        this.logLlamadasAEcreateinstancepatrCapacidad = logLlamadasAEcreateinstancepatrCapacidad;
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrServicio() {
        return this.logLlamadasAEcreateinstancepatrServicio;
    }
    
    public void setLogLlamadasAEcreateinstancepatrServicio(String logLlamadasAEcreateinstancepatrServicio) {
        this.logLlamadasAEcreateinstancepatrServicio = logLlamadasAEcreateinstancepatrServicio;
    }

    private void checkLogLlamadasAEcreateinstancepatrServicio() throws NotNullArgumentException {
        if(this.getLogLlamadasAEcreateinstancepatrServicio() == null) {
            throw new NotNullArgumentException(LogLlamadasAEConstants.SERV_ID_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ID, LogLlamadasAEConstants.ARG_ID_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRSERVICIO, LogLlamadasAEConstants.SERV_ALIAS_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ALIAS, LogLlamadasAEConstants.ARG_ALIAS_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRSERVICIO);
        }
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrSolicitud() {
        return this.logLlamadasAEcreateinstancepatrSolicitud;
    }
    
    public void setLogLlamadasAEcreateinstancepatrSolicitud(String logLlamadasAEcreateinstancepatrSolicitud) {
        this.logLlamadasAEcreateinstancepatrSolicitud = logLlamadasAEcreateinstancepatrSolicitud;
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrDatosRecibidos() {
        return this.logLlamadasAEcreateinstancepatrDatosRecibidos;
    }
    
    public void setLogLlamadasAEcreateinstancepatrDatosRecibidos(String logLlamadasAEcreateinstancepatrDatosRecibidos) {
        this.logLlamadasAEcreateinstancepatrDatosRecibidos = logLlamadasAEcreateinstancepatrDatosRecibidos;
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrDatosEnviados() {
        return this.logLlamadasAEcreateinstancepatrDatosEnviados;
    }
    
    public void setLogLlamadasAEcreateinstancepatrDatosEnviados(String logLlamadasAEcreateinstancepatrDatosEnviados) {
        this.logLlamadasAEcreateinstancepatrDatosEnviados = logLlamadasAEcreateinstancepatrDatosEnviados;
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrTipoServicio() {
        return this.logLlamadasAEcreateinstancepatrTipoServicio;
    }
    
    public void setLogLlamadasAEcreateinstancepatrTipoServicio(String logLlamadasAEcreateinstancepatrTipoServicio) {
        this.logLlamadasAEcreateinstancepatrTipoServicio = logLlamadasAEcreateinstancepatrTipoServicio;
    }

    private void checkLogLlamadasAEcreateinstancepatrTipoServicio() throws NotNullArgumentException {
        if(this.getLogLlamadasAEcreateinstancepatrTipoServicio() == null) {
            throw new NotNullArgumentException(LogLlamadasAEConstants.SERV_ID_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ID, LogLlamadasAEConstants.ARG_ID_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRTIPOSERVICIO, LogLlamadasAEConstants.SERV_ALIAS_CREATEINSTANCE, LogLlamadasAEConstants.CLASS_ALIAS, LogLlamadasAEConstants.ARG_ALIAS_CREATEINSTANCE_LOGLLAMADASAECREATEINSTANCEPATRTIPOSERVICIO);
        }
    }

    @JsonIgnore
    public Double getLogLlamadasAEcreateinstancepatrTiempoEjecucion() {
        return this.logLlamadasAEcreateinstancepatrTiempoEjecucion;
    }
    
    public void setLogLlamadasAEcreateinstancepatrTiempoEjecucion(Double logLlamadasAEcreateinstancepatrTiempoEjecucion) {
        this.logLlamadasAEcreateinstancepatrTiempoEjecucion = logLlamadasAEcreateinstancepatrTiempoEjecucion;
    }

    @JsonIgnore
    public String getLogLlamadasAEcreateinstancepatrTextoError() {
        return this.logLlamadasAEcreateinstancepatrTextoError;
    }
    
    public void setLogLlamadasAEcreateinstancepatrTextoError(String logLlamadasAEcreateinstancepatrTextoError) {
        this.logLlamadasAEcreateinstancepatrTextoError = logLlamadasAEcreateinstancepatrTextoError;
    }
}
