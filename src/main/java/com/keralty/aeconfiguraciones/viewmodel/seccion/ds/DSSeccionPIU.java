package com.keralty.aeconfiguraciones.viewmodel.seccion.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Seccion;
public class DSSeccionPIU {

    @JsonProperty("numorden")
    private Long dSSeccionPIUNumOrden;

    @JsonProperty("nombre")
    private String dSSeccionPIUNombre;

    public DSSeccionPIU() {
    
    }

    public DSSeccionPIU(Seccion instance) {
        if (instance != null) {
            this.dSSeccionPIUNumOrden = instance.getSeccionNumOrden();
            this.dSSeccionPIUNombre = instance.getSeccionNombre();
        }
    }

    @JsonIgnore
    public Long getDSSeccionPIUNumOrden() {
        return this.dSSeccionPIUNumOrden;
    }
    
    public void setDSSeccionPIUNumOrden(Long dSSeccionPIUNumOrden) {
        this.dSSeccionPIUNumOrden = dSSeccionPIUNumOrden;
    }

    @JsonIgnore
    public String getDSSeccionPIUNombre() {
        return this.dSSeccionPIUNombre;
    }
    
    public void setDSSeccionPIUNombre(String dSSeccionPIUNombre) {
        this.dSSeccionPIUNombre = dSSeccionPIUNombre;
    }
}
