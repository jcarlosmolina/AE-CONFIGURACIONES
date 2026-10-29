package com.keralty.aeconfiguraciones.viewmodel.novedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Novedad;
public class DSNovedadDMDDominio {

    @JsonProperty("tiponovedad_nombre")
    private String dSNovedadDMDDominioTipoNovedadDotNombre;

    @JsonProperty("nombre")
    private String dSNovedadDMDDominioNombre;

    @JsonProperty("aliasnovedad")
    private String dSNovedadDMDDominioAliasNovedad;

    @JsonProperty("descripcion")
    private String dSNovedadDMDDominioDescripcion;

    @JsonProperty("reqdocpersona")
    private Boolean dSNovedadDMDDominioReqDocPersona;

    @JsonProperty("reqdocsolicitud")
    private Boolean dSNovedadDMDDominioReqDocSolicitud;

    @JsonProperty("variasfamilias")
    private Boolean dSNovedadDMDDominioVariasFamilias;

    public DSNovedadDMDDominio() {
    
    }

    public DSNovedadDMDDominio(Novedad instance) {
        if (instance != null) {
            try {
                this.dSNovedadDMDDominioTipoNovedadDotNombre = instance.getTipoNovedad().getTipoNovedadNombre();
                this.dSNovedadDMDDominioNombre = instance.getNovedadNombre();
                this.dSNovedadDMDDominioAliasNovedad = instance.getNovedadAliasNovedad();
                this.dSNovedadDMDDominioDescripcion = instance.getNovedadDescripcion();
                this.dSNovedadDMDDominioReqDocPersona = instance.getNovedadReqDocPersona();
                this.dSNovedadDMDDominioReqDocSolicitud = instance.getNovedadReqDocSolicitud();
                this.dSNovedadDMDDominioVariasFamilias = instance.getNovedadVariasFamilias();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public String getDSNovedadDMDDominioTipoNovedadDotNombre() {
        return this.dSNovedadDMDDominioTipoNovedadDotNombre;
    }
    
    public void setDSNovedadDMDDominioTipoNovedadDotNombre(String dSNovedadDMDDominioTipoNovedadDotNombre) {
        this.dSNovedadDMDDominioTipoNovedadDotNombre = dSNovedadDMDDominioTipoNovedadDotNombre;
    }

    @JsonIgnore
    public String getDSNovedadDMDDominioNombre() {
        return this.dSNovedadDMDDominioNombre;
    }
    
    public void setDSNovedadDMDDominioNombre(String dSNovedadDMDDominioNombre) {
        this.dSNovedadDMDDominioNombre = dSNovedadDMDDominioNombre;
    }

    @JsonIgnore
    public String getDSNovedadDMDDominioAliasNovedad() {
        return this.dSNovedadDMDDominioAliasNovedad;
    }
    
    public void setDSNovedadDMDDominioAliasNovedad(String dSNovedadDMDDominioAliasNovedad) {
        this.dSNovedadDMDDominioAliasNovedad = dSNovedadDMDDominioAliasNovedad;
    }

    @JsonIgnore
    public String getDSNovedadDMDDominioDescripcion() {
        return this.dSNovedadDMDDominioDescripcion;
    }
    
    public void setDSNovedadDMDDominioDescripcion(String dSNovedadDMDDominioDescripcion) {
        this.dSNovedadDMDDominioDescripcion = dSNovedadDMDDominioDescripcion;
    }

    @JsonIgnore
    public Boolean getDSNovedadDMDDominioReqDocPersona() {
        return this.dSNovedadDMDDominioReqDocPersona;
    }
    
    public void setDSNovedadDMDDominioReqDocPersona(Boolean dSNovedadDMDDominioReqDocPersona) {
        this.dSNovedadDMDDominioReqDocPersona = dSNovedadDMDDominioReqDocPersona;
    }

    @JsonIgnore
    public Boolean getDSNovedadDMDDominioReqDocSolicitud() {
        return this.dSNovedadDMDDominioReqDocSolicitud;
    }
    
    public void setDSNovedadDMDDominioReqDocSolicitud(Boolean dSNovedadDMDDominioReqDocSolicitud) {
        this.dSNovedadDMDDominioReqDocSolicitud = dSNovedadDMDDominioReqDocSolicitud;
    }

    @JsonIgnore
    public Boolean getDSNovedadDMDDominioVariasFamilias() {
        return this.dSNovedadDMDDominioVariasFamilias;
    }
    
    public void setDSNovedadDMDDominioVariasFamilias(Boolean dSNovedadDMDDominioVariasFamilias) {
        this.dSNovedadDMDDominioVariasFamilias = dSNovedadDMDDominioVariasFamilias;
    }
}
