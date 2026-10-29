package com.keralty.aeconfiguraciones.viewmodel.contsindocsol.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
public class DSContSinDocSol {

    @JsonProperty("numcontrato")
    private String dSContSinDocSolNumContrato;

    public DSContSinDocSol() {
    
    }

    public DSContSinDocSol(ContSinDocSol instance) {
        if (instance != null) {
            this.dSContSinDocSolNumContrato = instance.getContSinDocSolNumContrato();
        }
    }

    @JsonIgnore
    public String getDSContSinDocSolNumContrato() {
        return this.dSContSinDocSolNumContrato;
    }
    
    public void setDSContSinDocSolNumContrato(String dSContSinDocSolNumContrato) {
        this.dSContSinDocSolNumContrato = dSContSinDocSolNumContrato;
    }
}
