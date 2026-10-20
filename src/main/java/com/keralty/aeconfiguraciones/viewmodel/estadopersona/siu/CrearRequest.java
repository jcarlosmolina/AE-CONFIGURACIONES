package com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoPersonaConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long estadoPersonacrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String estadoPersonacrearpatrDescripcion;

    @JsonProperty("p_atrunbeneficiariodistino")
    private Boolean estadoPersonacrearpatrUnBeneficiarioDistino;

    @JsonProperty("p_atrprohibidobeneficiarios")
    private Boolean estadoPersonacrearpatrProhibidoBeneficiarios;

    @JsonProperty("p_atrprohibidocontratante")
    private Boolean estadoPersonacrearpatrProhibidoContratante;

    @JsonProperty("p_atrprohibidotitular")
    private Boolean estadoPersonacrearpatrProhibidoTitular;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoPersonacrearpatrCodigo();
        checkEstadoPersonacrearpatrDescripcion();
        checkEstadoPersonacrearpatrUnBeneficiarioDistino();
        checkEstadoPersonacrearpatrProhibidoBeneficiarios();
        checkEstadoPersonacrearpatrProhibidoContratante();
        checkEstadoPersonacrearpatrProhibidoTitular();
    }

    @JsonIgnore
    public Long getEstadoPersonacrearpatrCodigo() {
        return this.estadoPersonacrearpatrCodigo;
    }
    
    public void setEstadoPersonacrearpatrCodigo(Long estadoPersonacrearpatrCodigo) {
        this.estadoPersonacrearpatrCodigo = estadoPersonacrearpatrCodigo;
    }

    private void checkEstadoPersonacrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRCODIGO, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEstadoPersonacrearpatrDescripcion() {
        return this.estadoPersonacrearpatrDescripcion;
    }
    
    public void setEstadoPersonacrearpatrDescripcion(String estadoPersonacrearpatrDescripcion) {
        this.estadoPersonacrearpatrDescripcion = estadoPersonacrearpatrDescripcion;
    }

    private void checkEstadoPersonacrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRDESCRIPCION, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonacrearpatrUnBeneficiarioDistino() {
        return this.estadoPersonacrearpatrUnBeneficiarioDistino;
    }
    
    public void setEstadoPersonacrearpatrUnBeneficiarioDistino(Boolean estadoPersonacrearpatrUnBeneficiarioDistino) {
        this.estadoPersonacrearpatrUnBeneficiarioDistino = estadoPersonacrearpatrUnBeneficiarioDistino;
    }

    private void checkEstadoPersonacrearpatrUnBeneficiarioDistino() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrUnBeneficiarioDistino() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRUNBENEFICIARIODISTINO, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRUNBENEFICIARIODISTINO);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonacrearpatrProhibidoBeneficiarios() {
        return this.estadoPersonacrearpatrProhibidoBeneficiarios;
    }
    
    public void setEstadoPersonacrearpatrProhibidoBeneficiarios(Boolean estadoPersonacrearpatrProhibidoBeneficiarios) {
        this.estadoPersonacrearpatrProhibidoBeneficiarios = estadoPersonacrearpatrProhibidoBeneficiarios;
    }

    private void checkEstadoPersonacrearpatrProhibidoBeneficiarios() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrProhibidoBeneficiarios() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRPROHIBIDOBENEFICIARIOS, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRPROHIBIDOBENEFICIARIOS);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonacrearpatrProhibidoContratante() {
        return this.estadoPersonacrearpatrProhibidoContratante;
    }
    
    public void setEstadoPersonacrearpatrProhibidoContratante(Boolean estadoPersonacrearpatrProhibidoContratante) {
        this.estadoPersonacrearpatrProhibidoContratante = estadoPersonacrearpatrProhibidoContratante;
    }

    private void checkEstadoPersonacrearpatrProhibidoContratante() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrProhibidoContratante() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRPROHIBIDOCONTRATANTE, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRPROHIBIDOCONTRATANTE);
        }
    }

    @JsonIgnore
    public Boolean getEstadoPersonacrearpatrProhibidoTitular() {
        return this.estadoPersonacrearpatrProhibidoTitular;
    }
    
    public void setEstadoPersonacrearpatrProhibidoTitular(Boolean estadoPersonacrearpatrProhibidoTitular) {
        this.estadoPersonacrearpatrProhibidoTitular = estadoPersonacrearpatrProhibidoTitular;
    }

    private void checkEstadoPersonacrearpatrProhibidoTitular() throws NotNullArgumentException {
        if(this.getEstadoPersonacrearpatrProhibidoTitular() == null) {
            throw new NotNullArgumentException(EstadoPersonaConstants.SERV_ID_CREAR, EstadoPersonaConstants.CLASS_ID, EstadoPersonaConstants.ARG_ID_CREAR_ESTADOPERSONACREARPATRPROHIBIDOTITULAR, EstadoPersonaConstants.SERV_ALIAS_CREAR, EstadoPersonaConstants.CLASS_ALIAS, EstadoPersonaConstants.ARG_ALIAS_CREAR_ESTADOPERSONACREARPATRPROHIBIDOTITULAR);
        }
    }
}
