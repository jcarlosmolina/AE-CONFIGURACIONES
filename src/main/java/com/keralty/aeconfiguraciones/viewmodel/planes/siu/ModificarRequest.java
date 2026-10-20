package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.Planes;

public class ModificarRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planesmodificarpthisPlan;
    
    @JsonIgnore
    private Planes planesmodificarpthisPlanInstance;

    @JsonProperty("p_esbancorep")
    private Boolean planesmodificarpEsBancoRep;

    @JsonProperty("p_aceptapreexistencias")
    private Boolean planesmodificarpAceptaPreexistencias;

    @JsonProperty("p_esvoluntario")
    private Boolean planesmodificarpEsVoluntario;

    @JsonProperty("p_iniciocontdiauno")
    private Boolean planesmodificarpInicioContDiaUno;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanesmodificarpthisPlan();
        checkPlanesmodificarpEsBancoRep();
        checkPlanesmodificarpAceptaPreexistencias();
        checkPlanesmodificarpEsVoluntario();
        checkPlanesmodificarpInicioContDiaUno();
    }

    @JsonIgnore
    public PlanesOid getPlanesmodificarpthisPlan() {
        return this.planesmodificarpthisPlan;
    }
    
    public void setPlanesmodificarpthisPlan(PlanesOid planesmodificarpthisPlan) {
        this.planesmodificarpthisPlan = planesmodificarpthisPlan;
    }

    @JsonIgnore
    public Planes getPlanesmodificarpthisPlanInstance() {
        return this.planesmodificarpthisPlanInstance;
    }
    
    public void setPlanesmodificarpthisPlanInstance(Planes planesmodificarpthisPlan) {
        this.planesmodificarpthisPlanInstance = planesmodificarpthisPlan;
        this.planesmodificarpthisPlan = planesmodificarpthisPlan != null ? planesmodificarpthisPlan.getOid() : null;
    }

    private void checkPlanesmodificarpthisPlan() throws NotNullArgumentException {
        if(this.getPlanesmodificarpthisPlanInstance() == null || this.getPlanesmodificarpthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_MODIFICAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_MODIFICAR_PLANESMODIFICARPTHISPLAN, PlanesConstants.SERV_ALIAS_MODIFICAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_MODIFICAR_PLANESMODIFICARPTHISPLAN);
        }
    }

    @JsonIgnore
    public Boolean getPlanesmodificarpEsBancoRep() {
        return this.planesmodificarpEsBancoRep;
    }
    
    public void setPlanesmodificarpEsBancoRep(Boolean planesmodificarpEsBancoRep) {
        this.planesmodificarpEsBancoRep = planesmodificarpEsBancoRep;
    }

    private void checkPlanesmodificarpEsBancoRep() throws NotNullArgumentException {
        if(this.getPlanesmodificarpEsBancoRep() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_MODIFICAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_MODIFICAR_PLANESMODIFICARPESBANCOREP, PlanesConstants.SERV_ALIAS_MODIFICAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_MODIFICAR_PLANESMODIFICARPESBANCOREP);
        }
    }

    @JsonIgnore
    public Boolean getPlanesmodificarpAceptaPreexistencias() {
        return this.planesmodificarpAceptaPreexistencias;
    }
    
    public void setPlanesmodificarpAceptaPreexistencias(Boolean planesmodificarpAceptaPreexistencias) {
        this.planesmodificarpAceptaPreexistencias = planesmodificarpAceptaPreexistencias;
    }

    private void checkPlanesmodificarpAceptaPreexistencias() throws NotNullArgumentException {
        if(this.getPlanesmodificarpAceptaPreexistencias() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_MODIFICAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_MODIFICAR_PLANESMODIFICARPACEPTAPREEXISTENCIAS, PlanesConstants.SERV_ALIAS_MODIFICAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_MODIFICAR_PLANESMODIFICARPACEPTAPREEXISTENCIAS);
        }
    }

    @JsonIgnore
    public Boolean getPlanesmodificarpEsVoluntario() {
        return this.planesmodificarpEsVoluntario;
    }
    
    public void setPlanesmodificarpEsVoluntario(Boolean planesmodificarpEsVoluntario) {
        this.planesmodificarpEsVoluntario = planesmodificarpEsVoluntario;
    }

    private void checkPlanesmodificarpEsVoluntario() throws NotNullArgumentException {
        if(this.getPlanesmodificarpEsVoluntario() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_MODIFICAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_MODIFICAR_PLANESMODIFICARPESVOLUNTARIO, PlanesConstants.SERV_ALIAS_MODIFICAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_MODIFICAR_PLANESMODIFICARPESVOLUNTARIO);
        }
    }

    @JsonIgnore
    public Boolean getPlanesmodificarpInicioContDiaUno() {
        return this.planesmodificarpInicioContDiaUno;
    }
    
    public void setPlanesmodificarpInicioContDiaUno(Boolean planesmodificarpInicioContDiaUno) {
        this.planesmodificarpInicioContDiaUno = planesmodificarpInicioContDiaUno;
    }

    private void checkPlanesmodificarpInicioContDiaUno() throws NotNullArgumentException {
        if(this.getPlanesmodificarpInicioContDiaUno() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_MODIFICAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_MODIFICAR_PLANESMODIFICARPINICIOCONTDIAUNO, PlanesConstants.SERV_ALIAS_MODIFICAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_MODIFICAR_PLANESMODIFICARPINICIOCONTDIAUNO);
        }
    }
}
