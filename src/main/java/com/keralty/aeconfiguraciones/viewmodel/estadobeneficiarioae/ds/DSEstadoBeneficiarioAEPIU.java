package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
public class DSEstadoBeneficiarioAEPIU {

    @JsonProperty("codigo")
    private Long dSEstadoBeneficiarioAEPIUCodigo;

    @JsonProperty("descripcion")
    private String dSEstadoBeneficiarioAEPIUDescripcion;

    @JsonProperty("estadobeneficiariocore_descripcion")
    private String dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion;

    public DSEstadoBeneficiarioAEPIU() {
    
    }

    public DSEstadoBeneficiarioAEPIU(EstadoBeneficiarioAE instance) {
        if (instance != null) {
            try {
                this.dSEstadoBeneficiarioAEPIUCodigo = instance.getEstadoBeneficiarioAECodigo();
                this.dSEstadoBeneficiarioAEPIUDescripcion = instance.getEstadoBeneficiarioAEDescripcion();
                this.dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion = instance.getEstadoBeneficiarioCORE().getEstadoBeneficiarioDescripcion();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public Long getDSEstadoBeneficiarioAEPIUCodigo() {
        return this.dSEstadoBeneficiarioAEPIUCodigo;
    }
    
    public void setDSEstadoBeneficiarioAEPIUCodigo(Long dSEstadoBeneficiarioAEPIUCodigo) {
        this.dSEstadoBeneficiarioAEPIUCodigo = dSEstadoBeneficiarioAEPIUCodigo;
    }

    @JsonIgnore
    public String getDSEstadoBeneficiarioAEPIUDescripcion() {
        return this.dSEstadoBeneficiarioAEPIUDescripcion;
    }
    
    public void setDSEstadoBeneficiarioAEPIUDescripcion(String dSEstadoBeneficiarioAEPIUDescripcion) {
        this.dSEstadoBeneficiarioAEPIUDescripcion = dSEstadoBeneficiarioAEPIUDescripcion;
    }

    @JsonIgnore
    public String getDSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion() {
        return this.dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion;
    }
    
    public void setDSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion(String dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion) {
        this.dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion = dSEstadoBeneficiarioAEPIUEstadoBeneficiarioCOREDotDescripcion;
    }
}
