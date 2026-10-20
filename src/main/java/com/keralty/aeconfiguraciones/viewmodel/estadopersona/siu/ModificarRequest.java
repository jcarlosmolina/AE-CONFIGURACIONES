package com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;

public class ModificarRequest {

    @JsonProperty("p_thisestadopersona_oid")
    private EstadoPersonaOid estadoPersonamodificarpthisEstadoPersona;
    
    @JsonIgnore
    private EstadoPersona estadoPersonamodificarpthisEstadoPersonaInstance;

    @JsonProperty("p_unbeneficiariodistino")
    private Boolean estadoPersonamodificarpUnBeneficiarioDistino;

    @JsonProperty("p_prohibidobeneficiarios")
    private Boolean estadoPersonamodificarpProhibidoBeneficiarios;

    @JsonProperty("p_prohibidocontratante")
    private Boolean estadoPersonamodificarpProhibidoContratante;

    @JsonProperty("p_prohibidotitular")
    private Boolean estadoPersonamodificarpProhibidoTitular;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoPersonamodificarpthisEstadoPersona();
        checkEstadoPersonamodificarpUnBeneficiarioDistino();
        checkEstadoPersonamodificarpProhibidoBeneficiarios();
        checkEstadoPersonamodificarpProhibidoContratante();
        checkEstadoPersonamodificarpProhibidoTitular();
    }

    @JsonIgnore
    public EstadoPersonaOid getEstadoPersonamodificarpthisEstadoPersona() {
        return this.estadoPersonamodificarpthisEstadoPersona;
    }
    
    public void setEstadoPersonamodificarpthisEstadoPersona(EstadoPersonaOid estadoPersonamodificarpthisEstadoPersona) {
        this.estadoPersonamodificarpthisEstadoPersona = estadoPersonamodificarpthisEstadoPersona;
    }

    @JsonIgnore
    public EstadoPersona getEstadoPersonamodificarpthisEstadoPersonaInstance() {
        return this.estadoPersonamodificarpthisEstadoPersonaInstance;
    }
    
    public void setEstadoPersonamodificarpthisEstadoPersonaInstance(EstadoPersona estadoPersonamodificarpthisEstadoPersona) {
        this.estadoPersonamodificarpthisEstadoPersonaInstance = estadoPersonamodificarpthisEstadoPersona;
        this.estadoPersonamodificarpthisEstadoPersona = estadoPersonamodificarpthisEstadoPersona != null ? estadoPersonamodificarpthisEstadoPersona.getOid() : null;
    }

    private void checkEstadoPersonamodificarpthisEstadoPersona() throws NotNullArgumentException {
        if(this.getEstadoPersonamodificarpthisEstadoPersonaInstance() == null || this.getEstadoPersonamodificarpthisEstadoPersonaInstance().isNull()) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_MODIFICAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_MODIFICAR_ESTADOPERSONAMODIFICARPTHISESTADOPERSONA, EstadoPersonaConstants.SERV_ALIAS_MODIFICAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_MODIFICAR_ESTADOPERSONAMODIFICARPTHISESTADOPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonamodificarpUnBeneficiarioDistino() {
        return this.estadoPersonamodificarpUnBeneficiarioDistino;
    }
    
    public void setEstadoPersonamodificarpUnBeneficiarioDistino(Boolean estadoPersonamodificarpUnBeneficiarioDistino) {
        this.estadoPersonamodificarpUnBeneficiarioDistino = estadoPersonamodificarpUnBeneficiarioDistino;
    }

    private void checkEstadoPersonamodificarpUnBeneficiarioDistino() throws NotNullArgumentException {
        if(this.getEstadoPersonamodificarpUnBeneficiarioDistino() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_MODIFICAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_MODIFICAR_ESTADOPERSONAMODIFICARPUNBENEFICIARIODISTINO, EstadoPersonaConstants.SERV_ALIAS_MODIFICAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_MODIFICAR_ESTADOPERSONAMODIFICARPUNBENEFICIARIODISTINO);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonamodificarpProhibidoBeneficiarios() {
        return this.estadoPersonamodificarpProhibidoBeneficiarios;
    }
    
    public void setEstadoPersonamodificarpProhibidoBeneficiarios(Boolean estadoPersonamodificarpProhibidoBeneficiarios) {
        this.estadoPersonamodificarpProhibidoBeneficiarios = estadoPersonamodificarpProhibidoBeneficiarios;
    }

    private void checkEstadoPersonamodificarpProhibidoBeneficiarios() throws NotNullArgumentException {
        if(this.getEstadoPersonamodificarpProhibidoBeneficiarios() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_MODIFICAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOBENEFICIARIOS, EstadoPersonaConstants.SERV_ALIAS_MODIFICAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOBENEFICIARIOS);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonamodificarpProhibidoContratante() {
        return this.estadoPersonamodificarpProhibidoContratante;
    }
    
    public void setEstadoPersonamodificarpProhibidoContratante(Boolean estadoPersonamodificarpProhibidoContratante) {
        this.estadoPersonamodificarpProhibidoContratante = estadoPersonamodificarpProhibidoContratante;
    }

    private void checkEstadoPersonamodificarpProhibidoContratante() throws NotNullArgumentException {
        if(this.getEstadoPersonamodificarpProhibidoContratante() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_MODIFICAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOCONTRATANTE, EstadoPersonaConstants.SERV_ALIAS_MODIFICAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOCONTRATANTE);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonamodificarpProhibidoTitular() {
        return this.estadoPersonamodificarpProhibidoTitular;
    }
    
    public void setEstadoPersonamodificarpProhibidoTitular(Boolean estadoPersonamodificarpProhibidoTitular) {
        this.estadoPersonamodificarpProhibidoTitular = estadoPersonamodificarpProhibidoTitular;
    }

    private void checkEstadoPersonamodificarpProhibidoTitular() throws NotNullArgumentException {
        if(this.getEstadoPersonamodificarpProhibidoTitular() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_MODIFICAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOTITULAR, EstadoPersonaConstants.SERV_ALIAS_MODIFICAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_MODIFICAR_ESTADOPERSONAMODIFICARPPROHIBIDOTITULAR);
        }
    }
}
