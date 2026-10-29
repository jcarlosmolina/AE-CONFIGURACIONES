package com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasAE;
public class DSLogLlamadasAEIIU {

    @JsonProperty("id")
    private Long dSLogLlamadasAEIIUID;

    @JsonProperty("tiposervicio")
    private String dSLogLlamadasAEIIUTipoServicio;

    @JsonProperty("solicitud")
    private String dSLogLlamadasAEIIUSolicitud;

    @JsonProperty("servicio")
    private String dSLogLlamadasAEIIUServicio;

    @JsonProperty("capacidad")
    private String dSLogLlamadasAEIIUCapacidad;

    @JsonProperty("fechahora")
    private Timestamp dSLogLlamadasAEIIUFechaHora;

    @JsonProperty("textoerror")
    private String dSLogLlamadasAEIIUTextoError;

    @JsonProperty("tiempoejecucion")
    private Double dSLogLlamadasAEIIUTiempoEjecucion;

    @JsonProperty("datosrecibidos")
    private String dSLogLlamadasAEIIUDatosRecibidos;

    @JsonProperty("datosenviados")
    private String dSLogLlamadasAEIIUDatosEnviados;

    public DSLogLlamadasAEIIU() {
    
    }

    public DSLogLlamadasAEIIU(LogLlamadasAE instance) {
        if (instance != null) {
            this.dSLogLlamadasAEIIUID = instance.getLogLlamadasAEID();
            this.dSLogLlamadasAEIIUTipoServicio = instance.getLogLlamadasAETipoServicio();
            this.dSLogLlamadasAEIIUSolicitud = instance.getLogLlamadasAESolicitud();
            this.dSLogLlamadasAEIIUServicio = instance.getLogLlamadasAEServicio();
            this.dSLogLlamadasAEIIUCapacidad = instance.getLogLlamadasAECapacidad();
            this.dSLogLlamadasAEIIUFechaHora = instance.getLogLlamadasAEFechaHora();
            this.dSLogLlamadasAEIIUTextoError = instance.getLogLlamadasAETextoError();
            this.dSLogLlamadasAEIIUTiempoEjecucion = instance.getLogLlamadasAETiempoEjecucion();
            this.dSLogLlamadasAEIIUDatosRecibidos = instance.getLogLlamadasAEDatosRecibidos();
            this.dSLogLlamadasAEIIUDatosEnviados = instance.getLogLlamadasAEDatosEnviados();
        }
    }

    @JsonIgnore
    public Long getDSLogLlamadasAEIIUID() {
        return this.dSLogLlamadasAEIIUID;
    }
    
    public void setDSLogLlamadasAEIIUID(Long dSLogLlamadasAEIIUID) {
        this.dSLogLlamadasAEIIUID = dSLogLlamadasAEIIUID;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUTipoServicio() {
        return this.dSLogLlamadasAEIIUTipoServicio;
    }
    
    public void setDSLogLlamadasAEIIUTipoServicio(String dSLogLlamadasAEIIUTipoServicio) {
        this.dSLogLlamadasAEIIUTipoServicio = dSLogLlamadasAEIIUTipoServicio;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUSolicitud() {
        return this.dSLogLlamadasAEIIUSolicitud;
    }
    
    public void setDSLogLlamadasAEIIUSolicitud(String dSLogLlamadasAEIIUSolicitud) {
        this.dSLogLlamadasAEIIUSolicitud = dSLogLlamadasAEIIUSolicitud;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUServicio() {
        return this.dSLogLlamadasAEIIUServicio;
    }
    
    public void setDSLogLlamadasAEIIUServicio(String dSLogLlamadasAEIIUServicio) {
        this.dSLogLlamadasAEIIUServicio = dSLogLlamadasAEIIUServicio;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUCapacidad() {
        return this.dSLogLlamadasAEIIUCapacidad;
    }
    
    public void setDSLogLlamadasAEIIUCapacidad(String dSLogLlamadasAEIIUCapacidad) {
        this.dSLogLlamadasAEIIUCapacidad = dSLogLlamadasAEIIUCapacidad;
    }

    @JsonIgnore
    public Timestamp getDSLogLlamadasAEIIUFechaHora() {
        return this.dSLogLlamadasAEIIUFechaHora;
    }
    
    public void setDSLogLlamadasAEIIUFechaHora(Timestamp dSLogLlamadasAEIIUFechaHora) {
        this.dSLogLlamadasAEIIUFechaHora = dSLogLlamadasAEIIUFechaHora;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUTextoError() {
        return this.dSLogLlamadasAEIIUTextoError;
    }
    
    public void setDSLogLlamadasAEIIUTextoError(String dSLogLlamadasAEIIUTextoError) {
        this.dSLogLlamadasAEIIUTextoError = dSLogLlamadasAEIIUTextoError;
    }

    @JsonIgnore
    public Double getDSLogLlamadasAEIIUTiempoEjecucion() {
        return this.dSLogLlamadasAEIIUTiempoEjecucion;
    }
    
    public void setDSLogLlamadasAEIIUTiempoEjecucion(Double dSLogLlamadasAEIIUTiempoEjecucion) {
        this.dSLogLlamadasAEIIUTiempoEjecucion = dSLogLlamadasAEIIUTiempoEjecucion;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUDatosRecibidos() {
        return this.dSLogLlamadasAEIIUDatosRecibidos;
    }
    
    public void setDSLogLlamadasAEIIUDatosRecibidos(String dSLogLlamadasAEIIUDatosRecibidos) {
        this.dSLogLlamadasAEIIUDatosRecibidos = dSLogLlamadasAEIIUDatosRecibidos;
    }

    @JsonIgnore
    public String getDSLogLlamadasAEIIUDatosEnviados() {
        return this.dSLogLlamadasAEIIUDatosEnviados;
    }
    
    public void setDSLogLlamadasAEIIUDatosEnviados(String dSLogLlamadasAEIIUDatosEnviados) {
        this.dSLogLlamadasAEIIUDatosEnviados = dSLogLlamadasAEIIUDatosEnviados;
    }
}
