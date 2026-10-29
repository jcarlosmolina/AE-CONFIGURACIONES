package com.keralty.aeconfiguraciones.viewmodel.novedad.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.persistence.Novedad;
public class DSNovedad {

    @JsonProperty("tiponovedad_nombre")
    private String dSNovedadTipoNovedadDotNombre;

    @JsonProperty("nombre")
    private String dSNovedadNombre;

    @JsonProperty("aliasnovedad")
    private String dSNovedadAliasNovedad;

    @JsonProperty("descripcion")
    private String dSNovedadDescripcion;

    @JsonProperty("codigolegal")
    private String dSNovedadCodigoLegal;

    @JsonProperty("reqdocpersona")
    private Boolean dSNovedadReqDocPersona;

    @JsonProperty("reqdocsolicitud")
    private Boolean dSNovedadReqDocSolicitud;

    @JsonProperty("variasfamilias")
    private Boolean dSNovedadVariasFamilias;

    @JsonProperty("minvalormeses")
    private Long dSNovedadMinValorMeses;

    @JsonProperty("maxvalormeses")
    private Long dSNovedadMaxValorMeses;

    public DSNovedad() {
    
    }

    public DSNovedad(Novedad instance) {
        if (instance != null) {
            try {
                this.dSNovedadTipoNovedadDotNombre = instance.getTipoNovedad().getTipoNovedadNombre();
                this.dSNovedadNombre = instance.getNovedadNombre();
                this.dSNovedadAliasNovedad = instance.getNovedadAliasNovedad();
                this.dSNovedadDescripcion = instance.getNovedadDescripcion();
                this.dSNovedadCodigoLegal = instance.getNovedadCodigoLegal();
                this.dSNovedadReqDocPersona = instance.getNovedadReqDocPersona();
                this.dSNovedadReqDocSolicitud = instance.getNovedadReqDocSolicitud();
                this.dSNovedadVariasFamilias = instance.getNovedadVariasFamilias();
                this.dSNovedadMinValorMeses = instance.getNovedadMinValorMeses();
                this.dSNovedadMaxValorMeses = instance.getNovedadMaxValorMeses();
            } catch (Exception e) {
                org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            }
        }
    }

    @JsonIgnore
    public String getDSNovedadTipoNovedadDotNombre() {
        return this.dSNovedadTipoNovedadDotNombre;
    }
    
    public void setDSNovedadTipoNovedadDotNombre(String dSNovedadTipoNovedadDotNombre) {
        this.dSNovedadTipoNovedadDotNombre = dSNovedadTipoNovedadDotNombre;
    }

    @JsonIgnore
    public String getDSNovedadNombre() {
        return this.dSNovedadNombre;
    }
    
    public void setDSNovedadNombre(String dSNovedadNombre) {
        this.dSNovedadNombre = dSNovedadNombre;
    }

    @JsonIgnore
    public String getDSNovedadAliasNovedad() {
        return this.dSNovedadAliasNovedad;
    }
    
    public void setDSNovedadAliasNovedad(String dSNovedadAliasNovedad) {
        this.dSNovedadAliasNovedad = dSNovedadAliasNovedad;
    }

    @JsonIgnore
    public String getDSNovedadDescripcion() {
        return this.dSNovedadDescripcion;
    }
    
    public void setDSNovedadDescripcion(String dSNovedadDescripcion) {
        this.dSNovedadDescripcion = dSNovedadDescripcion;
    }

    @JsonIgnore
    public String getDSNovedadCodigoLegal() {
        return this.dSNovedadCodigoLegal;
    }
    
    public void setDSNovedadCodigoLegal(String dSNovedadCodigoLegal) {
        this.dSNovedadCodigoLegal = dSNovedadCodigoLegal;
    }

    @JsonIgnore
    public Boolean getDSNovedadReqDocPersona() {
        return this.dSNovedadReqDocPersona;
    }
    
    public void setDSNovedadReqDocPersona(Boolean dSNovedadReqDocPersona) {
        this.dSNovedadReqDocPersona = dSNovedadReqDocPersona;
    }

    @JsonIgnore
    public Boolean getDSNovedadReqDocSolicitud() {
        return this.dSNovedadReqDocSolicitud;
    }
    
    public void setDSNovedadReqDocSolicitud(Boolean dSNovedadReqDocSolicitud) {
        this.dSNovedadReqDocSolicitud = dSNovedadReqDocSolicitud;
    }

    @JsonIgnore
    public Boolean getDSNovedadVariasFamilias() {
        return this.dSNovedadVariasFamilias;
    }
    
    public void setDSNovedadVariasFamilias(Boolean dSNovedadVariasFamilias) {
        this.dSNovedadVariasFamilias = dSNovedadVariasFamilias;
    }

    @JsonIgnore
    public Long getDSNovedadMinValorMeses() {
        return this.dSNovedadMinValorMeses;
    }
    
    public void setDSNovedadMinValorMeses(Long dSNovedadMinValorMeses) {
        this.dSNovedadMinValorMeses = dSNovedadMinValorMeses;
    }

    @JsonIgnore
    public Long getDSNovedadMaxValorMeses() {
        return this.dSNovedadMaxValorMeses;
    }
    
    public void setDSNovedadMaxValorMeses(Long dSNovedadMaxValorMeses) {
        this.dSNovedadMaxValorMeses = dSNovedadMaxValorMeses;
    }
}
