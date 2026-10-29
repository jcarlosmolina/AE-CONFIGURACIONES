package com.keralty.aeconfiguraciones.viewmodel.configuracionans.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
public class DSConfiguracionANSDetalle {

    @JsonProperty("dominio_productot_descripcion")
    private String dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion;

    @JsonProperty("dominio_plant_nombre")
    private String dSConfiguracionANSDetalleDominioDotPlanTDotNombre;

    @JsonProperty("tramite")
    private Long dSConfiguracionANSDetalleTramite;

    @JsonProperty("novedad_nombre")
    private String dSConfiguracionANSDetalleNovedadDotNombre;

    @JsonProperty("causal_nombre")
    private String dSConfiguracionANSDetalleCausalDotNombre;

    @JsonProperty("habillunes")
    private Boolean dSConfiguracionANSDetalleHabilLunes;

    @JsonProperty("habilmartes")
    private Boolean dSConfiguracionANSDetalleHabilMartes;

    @JsonProperty("habilmiercoles")
    private Boolean dSConfiguracionANSDetalleHabilMiercoles;

    @JsonProperty("habiljueves")
    private Boolean dSConfiguracionANSDetalleHabilJueves;

    @JsonProperty("habilviernes")
    private Boolean dSConfiguracionANSDetalleHabilViernes;

    @JsonProperty("habilsabado")
    private Boolean dSConfiguracionANSDetalleHabilSabado;

    @JsonProperty("habildomingo")
    private Boolean dSConfiguracionANSDetalleHabilDomingo;

    @JsonProperty("ansafiliaciones")
    private Long dSConfiguracionANSDetalleANSAfiliaciones;

    @JsonProperty("ansareamedica")
    private Long dSConfiguracionANSDetalleANSAreaMedica;

    public DSConfiguracionANSDetalle() {
    
    }

    public DSConfiguracionANSDetalle(ConfiguracionANS instance) {
        if (instance != null) {
            try {
                this.dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion = instance.getDominio().getProductoT().getProductosDescripcion();
                this.dSConfiguracionANSDetalleDominioDotPlanTDotNombre = instance.getDominio().getPlanT().getPlanesNombre();
                this.dSConfiguracionANSDetalleTramite = instance.getConfiguracionANSTramite();
                this.dSConfiguracionANSDetalleNovedadDotNombre = instance.getNovedad().getNovedadNombre();
                this.dSConfiguracionANSDetalleCausalDotNombre = instance.getCausal().getCausalNombre();
                this.dSConfiguracionANSDetalleHabilLunes = instance.getConfiguracionANSHabilLunes();
                this.dSConfiguracionANSDetalleHabilMartes = instance.getConfiguracionANSHabilMartes();
                this.dSConfiguracionANSDetalleHabilMiercoles = instance.getConfiguracionANSHabilMiercoles();
                this.dSConfiguracionANSDetalleHabilJueves = instance.getConfiguracionANSHabilJueves();
                this.dSConfiguracionANSDetalleHabilViernes = instance.getConfiguracionANSHabilViernes();
                this.dSConfiguracionANSDetalleHabilSabado = instance.getConfiguracionANSHabilSabado();
                this.dSConfiguracionANSDetalleHabilDomingo = instance.getConfiguracionANSHabilDomingo();
                this.dSConfiguracionANSDetalleANSAfiliaciones = instance.getConfiguracionANSANSAfiliaciones();
                this.dSConfiguracionANSDetalleANSAreaMedica = instance.getConfiguracionANSANSAreaMedica();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public String getDSConfiguracionANSDetalleDominioDotProductoTDotDescripcion() {
        return this.dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion;
    }
    
    public void setDSConfiguracionANSDetalleDominioDotProductoTDotDescripcion(String dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion) {
        this.dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion = dSConfiguracionANSDetalleDominioDotProductoTDotDescripcion;
    }

    @JsonIgnore
    public String getDSConfiguracionANSDetalleDominioDotPlanTDotNombre() {
        return this.dSConfiguracionANSDetalleDominioDotPlanTDotNombre;
    }
    
    public void setDSConfiguracionANSDetalleDominioDotPlanTDotNombre(String dSConfiguracionANSDetalleDominioDotPlanTDotNombre) {
        this.dSConfiguracionANSDetalleDominioDotPlanTDotNombre = dSConfiguracionANSDetalleDominioDotPlanTDotNombre;
    }

    @JsonIgnore
    public Long getDSConfiguracionANSDetalleTramite() {
        return this.dSConfiguracionANSDetalleTramite;
    }
    
    public void setDSConfiguracionANSDetalleTramite(Long dSConfiguracionANSDetalleTramite) {
        this.dSConfiguracionANSDetalleTramite = dSConfiguracionANSDetalleTramite;
    }

    @JsonIgnore
    public String getDSConfiguracionANSDetalleNovedadDotNombre() {
        return this.dSConfiguracionANSDetalleNovedadDotNombre;
    }
    
    public void setDSConfiguracionANSDetalleNovedadDotNombre(String dSConfiguracionANSDetalleNovedadDotNombre) {
        this.dSConfiguracionANSDetalleNovedadDotNombre = dSConfiguracionANSDetalleNovedadDotNombre;
    }

    @JsonIgnore
    public String getDSConfiguracionANSDetalleCausalDotNombre() {
        return this.dSConfiguracionANSDetalleCausalDotNombre;
    }
    
    public void setDSConfiguracionANSDetalleCausalDotNombre(String dSConfiguracionANSDetalleCausalDotNombre) {
        this.dSConfiguracionANSDetalleCausalDotNombre = dSConfiguracionANSDetalleCausalDotNombre;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilLunes() {
        return this.dSConfiguracionANSDetalleHabilLunes;
    }
    
    public void setDSConfiguracionANSDetalleHabilLunes(Boolean dSConfiguracionANSDetalleHabilLunes) {
        this.dSConfiguracionANSDetalleHabilLunes = dSConfiguracionANSDetalleHabilLunes;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilMartes() {
        return this.dSConfiguracionANSDetalleHabilMartes;
    }
    
    public void setDSConfiguracionANSDetalleHabilMartes(Boolean dSConfiguracionANSDetalleHabilMartes) {
        this.dSConfiguracionANSDetalleHabilMartes = dSConfiguracionANSDetalleHabilMartes;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilMiercoles() {
        return this.dSConfiguracionANSDetalleHabilMiercoles;
    }
    
    public void setDSConfiguracionANSDetalleHabilMiercoles(Boolean dSConfiguracionANSDetalleHabilMiercoles) {
        this.dSConfiguracionANSDetalleHabilMiercoles = dSConfiguracionANSDetalleHabilMiercoles;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilJueves() {
        return this.dSConfiguracionANSDetalleHabilJueves;
    }
    
    public void setDSConfiguracionANSDetalleHabilJueves(Boolean dSConfiguracionANSDetalleHabilJueves) {
        this.dSConfiguracionANSDetalleHabilJueves = dSConfiguracionANSDetalleHabilJueves;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilViernes() {
        return this.dSConfiguracionANSDetalleHabilViernes;
    }
    
    public void setDSConfiguracionANSDetalleHabilViernes(Boolean dSConfiguracionANSDetalleHabilViernes) {
        this.dSConfiguracionANSDetalleHabilViernes = dSConfiguracionANSDetalleHabilViernes;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilSabado() {
        return this.dSConfiguracionANSDetalleHabilSabado;
    }
    
    public void setDSConfiguracionANSDetalleHabilSabado(Boolean dSConfiguracionANSDetalleHabilSabado) {
        this.dSConfiguracionANSDetalleHabilSabado = dSConfiguracionANSDetalleHabilSabado;
    }

    @JsonIgnore
    public Boolean getDSConfiguracionANSDetalleHabilDomingo() {
        return this.dSConfiguracionANSDetalleHabilDomingo;
    }
    
    public void setDSConfiguracionANSDetalleHabilDomingo(Boolean dSConfiguracionANSDetalleHabilDomingo) {
        this.dSConfiguracionANSDetalleHabilDomingo = dSConfiguracionANSDetalleHabilDomingo;
    }

    @JsonIgnore
    public Long getDSConfiguracionANSDetalleANSAfiliaciones() {
        return this.dSConfiguracionANSDetalleANSAfiliaciones;
    }
    
    public void setDSConfiguracionANSDetalleANSAfiliaciones(Long dSConfiguracionANSDetalleANSAfiliaciones) {
        this.dSConfiguracionANSDetalleANSAfiliaciones = dSConfiguracionANSDetalleANSAfiliaciones;
    }

    @JsonIgnore
    public Long getDSConfiguracionANSDetalleANSAreaMedica() {
        return this.dSConfiguracionANSDetalleANSAreaMedica;
    }
    
    public void setDSConfiguracionANSDetalleANSAreaMedica(Long dSConfiguracionANSDetalleANSAreaMedica) {
        this.dSConfiguracionANSDetalleANSAreaMedica = dSConfiguracionANSDetalleANSAreaMedica;
    }
}
