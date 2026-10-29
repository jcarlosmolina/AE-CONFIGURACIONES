package com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
public class DSLogConfiguracionANS {

    @JsonProperty("fecha")
    private Timestamp dSLogConfiguracionANSFecha;

    @JsonProperty("loginfuncionario")
    private String dSLogConfiguracionANSLoginFuncionario;

    @JsonProperty("ansafiliaciones")
    private Long dSLogConfiguracionANSANSAfiliaciones;

    @JsonProperty("ansareamedica")
    private Long dSLogConfiguracionANSANSAreaMedica;

    @JsonProperty("habillunes")
    private Boolean dSLogConfiguracionANSHabilLunes;

    @JsonProperty("habilmartes")
    private Boolean dSLogConfiguracionANSHabilMartes;

    @JsonProperty("habilmiercoles")
    private Boolean dSLogConfiguracionANSHabilMiercoles;

    @JsonProperty("habiljueves")
    private Boolean dSLogConfiguracionANSHabilJueves;

    @JsonProperty("habilviernes")
    private Boolean dSLogConfiguracionANSHabilViernes;

    @JsonProperty("habilsabado")
    private Boolean dSLogConfiguracionANSHabilSabado;

    @JsonProperty("habildomingo")
    private Boolean dSLogConfiguracionANSHabilDomingo;

    public DSLogConfiguracionANS() {
    
    }

    public DSLogConfiguracionANS(LogConfiguracionANS instance) {
        if (instance != null) {
            this.dSLogConfiguracionANSFecha = instance.getLogConfiguracionANSFecha();
            this.dSLogConfiguracionANSLoginFuncionario = instance.getLogConfiguracionANSLoginFuncionario();
            this.dSLogConfiguracionANSANSAfiliaciones = instance.getLogConfiguracionANSANSAfiliaciones();
            this.dSLogConfiguracionANSANSAreaMedica = instance.getLogConfiguracionANSANSAreaMedica();
            this.dSLogConfiguracionANSHabilLunes = instance.getLogConfiguracionANSHabilLunes();
            this.dSLogConfiguracionANSHabilMartes = instance.getLogConfiguracionANSHabilMartes();
            this.dSLogConfiguracionANSHabilMiercoles = instance.getLogConfiguracionANSHabilMiercoles();
            this.dSLogConfiguracionANSHabilJueves = instance.getLogConfiguracionANSHabilJueves();
            this.dSLogConfiguracionANSHabilViernes = instance.getLogConfiguracionANSHabilViernes();
            this.dSLogConfiguracionANSHabilSabado = instance.getLogConfiguracionANSHabilSabado();
            this.dSLogConfiguracionANSHabilDomingo = instance.getLogConfiguracionANSHabilDomingo();
        }
    }

    @JsonIgnore
    public Timestamp getDSLogConfiguracionANSFecha() {
        return this.dSLogConfiguracionANSFecha;
    }
    
    public void setDSLogConfiguracionANSFecha(Timestamp dSLogConfiguracionANSFecha) {
        this.dSLogConfiguracionANSFecha = dSLogConfiguracionANSFecha;
    }

    @JsonIgnore
    public String getDSLogConfiguracionANSLoginFuncionario() {
        return this.dSLogConfiguracionANSLoginFuncionario;
    }
    
    public void setDSLogConfiguracionANSLoginFuncionario(String dSLogConfiguracionANSLoginFuncionario) {
        this.dSLogConfiguracionANSLoginFuncionario = dSLogConfiguracionANSLoginFuncionario;
    }

    @JsonIgnore
    public Long getDSLogConfiguracionANSANSAfiliaciones() {
        return this.dSLogConfiguracionANSANSAfiliaciones;
    }
    
    public void setDSLogConfiguracionANSANSAfiliaciones(Long dSLogConfiguracionANSANSAfiliaciones) {
        this.dSLogConfiguracionANSANSAfiliaciones = dSLogConfiguracionANSANSAfiliaciones;
    }

    @JsonIgnore
    public Long getDSLogConfiguracionANSANSAreaMedica() {
        return this.dSLogConfiguracionANSANSAreaMedica;
    }
    
    public void setDSLogConfiguracionANSANSAreaMedica(Long dSLogConfiguracionANSANSAreaMedica) {
        this.dSLogConfiguracionANSANSAreaMedica = dSLogConfiguracionANSANSAreaMedica;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilLunes() {
        return this.dSLogConfiguracionANSHabilLunes;
    }
    
    public void setDSLogConfiguracionANSHabilLunes(Boolean dSLogConfiguracionANSHabilLunes) {
        this.dSLogConfiguracionANSHabilLunes = dSLogConfiguracionANSHabilLunes;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilMartes() {
        return this.dSLogConfiguracionANSHabilMartes;
    }
    
    public void setDSLogConfiguracionANSHabilMartes(Boolean dSLogConfiguracionANSHabilMartes) {
        this.dSLogConfiguracionANSHabilMartes = dSLogConfiguracionANSHabilMartes;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilMiercoles() {
        return this.dSLogConfiguracionANSHabilMiercoles;
    }
    
    public void setDSLogConfiguracionANSHabilMiercoles(Boolean dSLogConfiguracionANSHabilMiercoles) {
        this.dSLogConfiguracionANSHabilMiercoles = dSLogConfiguracionANSHabilMiercoles;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilJueves() {
        return this.dSLogConfiguracionANSHabilJueves;
    }
    
    public void setDSLogConfiguracionANSHabilJueves(Boolean dSLogConfiguracionANSHabilJueves) {
        this.dSLogConfiguracionANSHabilJueves = dSLogConfiguracionANSHabilJueves;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilViernes() {
        return this.dSLogConfiguracionANSHabilViernes;
    }
    
    public void setDSLogConfiguracionANSHabilViernes(Boolean dSLogConfiguracionANSHabilViernes) {
        this.dSLogConfiguracionANSHabilViernes = dSLogConfiguracionANSHabilViernes;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilSabado() {
        return this.dSLogConfiguracionANSHabilSabado;
    }
    
    public void setDSLogConfiguracionANSHabilSabado(Boolean dSLogConfiguracionANSHabilSabado) {
        this.dSLogConfiguracionANSHabilSabado = dSLogConfiguracionANSHabilSabado;
    }

    @JsonIgnore
    public Boolean getDSLogConfiguracionANSHabilDomingo() {
        return this.dSLogConfiguracionANSHabilDomingo;
    }
    
    public void setDSLogConfiguracionANSHabilDomingo(Boolean dSLogConfiguracionANSHabilDomingo) {
        this.dSLogConfiguracionANSHabilDomingo = dSLogConfiguracionANSHabilDomingo;
    }
}
