package com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.ds;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
public class DSLogContSinDocSol {

    @JsonProperty("fecha")
    private Timestamp dSLogContSinDocSolFecha;

    @JsonProperty("numcontrato")
    private String dSLogContSinDocSolNumContrato;

    @JsonProperty("usuario")
    private String dSLogContSinDocSolUsuario;

    public DSLogContSinDocSol() {
    
    }

    public DSLogContSinDocSol(LogContSinDocSol instance) {
        if (instance != null) {
            this.dSLogContSinDocSolFecha = instance.getLogContSinDocSolFecha();
            this.dSLogContSinDocSolNumContrato = instance.getLogContSinDocSolNumContrato();
            this.dSLogContSinDocSolUsuario = instance.getLogContSinDocSolUsuario();
        }
    }

    @JsonIgnore
    public Timestamp getDSLogContSinDocSolFecha() {
        return this.dSLogContSinDocSolFecha;
    }
    
    public void setDSLogContSinDocSolFecha(Timestamp dSLogContSinDocSolFecha) {
        this.dSLogContSinDocSolFecha = dSLogContSinDocSolFecha;
    }

    @JsonIgnore
    public String getDSLogContSinDocSolNumContrato() {
        return this.dSLogContSinDocSolNumContrato;
    }
    
    public void setDSLogContSinDocSolNumContrato(String dSLogContSinDocSolNumContrato) {
        this.dSLogContSinDocSolNumContrato = dSLogContSinDocSolNumContrato;
    }

    @JsonIgnore
    public String getDSLogContSinDocSolUsuario() {
        return this.dSLogContSinDocSolUsuario;
    }
    
    public void setDSLogContSinDocSolUsuario(String dSLogContSinDocSolUsuario) {
        this.dSLogContSinDocSolUsuario = dSLogContSinDocSolUsuario;
    }
}
