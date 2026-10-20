package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;

public class CrearRequest {

    @JsonProperty("p_agrestadobeneficiariocore_json")
    private String estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance;

    @JsonProperty("p_atrdescripcion")
    private String estadoBeneficiarioAEcrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE();
        checkEstadoBeneficiarioAEcrearpatrDescripcion();
    }

    @JsonIgnore
    public String getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE() {
        return this.estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE;
    }
    
    public void setEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE(String estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE) {
        this.estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE = estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance() {
        return this.estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance;
    }
    
    public void setEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance(EstadoBeneficiario estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE) {
        this.estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance = estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE;
        this.estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE = estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE != null ? estadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE.getOid().toJson() : "";
    }

    private void checkEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCORE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance() == null || this.getEstadoBeneficiarioAEcrearpagrEstadoBeneficiarioCOREInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_CREAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_CREAR_ESTADOBENEFICIARIOAECREARPAGRESTADOBENEFICIARIOCORE, EstadoBeneficiarioAEConstants.SERV_ALIAS_CREAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_CREAR_ESTADOBENEFICIARIOAECREARPAGRESTADOBENEFICIARIOCORE);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiarioAEcrearpatrDescripcion() {
        return this.estadoBeneficiarioAEcrearpatrDescripcion;
    }
    
    public void setEstadoBeneficiarioAEcrearpatrDescripcion(String estadoBeneficiarioAEcrearpatrDescripcion) {
        this.estadoBeneficiarioAEcrearpatrDescripcion = estadoBeneficiarioAEcrearpatrDescripcion;
    }

    private void checkEstadoBeneficiarioAEcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAEcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_CREAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_CREAR_ESTADOBENEFICIARIOAECREARPATRDESCRIPCION, EstadoBeneficiarioAEConstants.SERV_ALIAS_CREAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_CREAR_ESTADOBENEFICIARIOAECREARPATRDESCRIPCION);
        }
    }
}
