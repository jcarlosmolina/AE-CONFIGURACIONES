package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;

public class TCREARRequest {

    @JsonProperty("pt_p_agrtiponovedad_json")
    private String novedadTCREARptpagrTipoNovedad;
    
    @JsonIgnore
    private TipoNovedad novedadTCREARptpagrTipoNovedadInstance;

    @JsonProperty("pt_p_atrnombre")
    private String novedadTCREARptpatrNombre;

    @JsonProperty("pt_p_atrdescripcion")
    private String novedadTCREARptpatrDescripcion;

    @JsonProperty("pt_p_atrcodigolegal")
    private String novedadTCREARptpatrCodigoLegal;

    @JsonProperty("pt_p_atrreqdocpersona")
    private Boolean novedadTCREARptpatrReqDocPersona;

    @JsonProperty("pt_p_atrreqdocsolicitud")
    private Boolean novedadTCREARptpatrReqDocSolicitud;

    @JsonProperty("pt_p_atrvariasfamilias")
    private Boolean novedadTCREARptpatrVariasFamilias;

    @JsonProperty("ptaliasparacontrato")
    private String novedadTCREARptAliasParaContrato;

    @JsonProperty("ptaliasparatitular")
    private String novedadTCREARptAliasParaTitular;

    @JsonProperty("ptaliasparausuario")
    private String novedadTCREARptAliasParaUsuario;

    @JsonProperty("pt_p_atrminvalormeses")
    private Long novedadTCREARptpatrMinValorMeses;

    @JsonProperty("pt_p_atrmaxvalormeses")
    private Long novedadTCREARptpatrMaxValorMeses;

    @JsonProperty("ptalias")
    private String novedadTCREARptAlias;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadTCREARptpagrTipoNovedad();
        checkNovedadTCREARptpatrNombre();
        checkNovedadTCREARptpatrDescripcion();
        checkNovedadTCREARptpatrCodigoLegal();
        checkNovedadTCREARptpatrReqDocPersona();
        checkNovedadTCREARptpatrReqDocSolicitud();
        checkNovedadTCREARptpatrVariasFamilias();
        checkNovedadTCREARptAlias();
    }

    @JsonIgnore
    public String getNovedadTCREARptpagrTipoNovedad() {
        return this.novedadTCREARptpagrTipoNovedad;
    }
    
    public void setNovedadTCREARptpagrTipoNovedad(String novedadTCREARptpagrTipoNovedad) {
        this.novedadTCREARptpagrTipoNovedad = novedadTCREARptpagrTipoNovedad;
    }

    @JsonIgnore
    public TipoNovedad getNovedadTCREARptpagrTipoNovedadInstance() {
        return this.novedadTCREARptpagrTipoNovedadInstance;
    }
    
    public void setNovedadTCREARptpagrTipoNovedadInstance(TipoNovedad novedadTCREARptpagrTipoNovedad) {
        this.novedadTCREARptpagrTipoNovedadInstance = novedadTCREARptpagrTipoNovedad;
        this.novedadTCREARptpagrTipoNovedad = novedadTCREARptpagrTipoNovedad != null ? novedadTCREARptpagrTipoNovedad.getOid().toJson() : "";
    }

    private void checkNovedadTCREARptpagrTipoNovedad() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpagrTipoNovedadInstance() == null || this.getNovedadTCREARptpagrTipoNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPAGRTIPONOVEDAD, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPAGRTIPONOVEDAD);
        }
    }

    @JsonIgnore
    public String getNovedadTCREARptpatrNombre() {
        return this.novedadTCREARptpatrNombre;
    }
    
    public void setNovedadTCREARptpatrNombre(String novedadTCREARptpatrNombre) {
        this.novedadTCREARptpatrNombre = novedadTCREARptpatrNombre;
    }

    private void checkNovedadTCREARptpatrNombre() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrNombre() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRNOMBRE, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getNovedadTCREARptpatrDescripcion() {
        return this.novedadTCREARptpatrDescripcion;
    }
    
    public void setNovedadTCREARptpatrDescripcion(String novedadTCREARptpatrDescripcion) {
        this.novedadTCREARptpatrDescripcion = novedadTCREARptpatrDescripcion;
    }

    private void checkNovedadTCREARptpatrDescripcion() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrDescripcion() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRDESCRIPCION, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getNovedadTCREARptpatrCodigoLegal() {
        return this.novedadTCREARptpatrCodigoLegal;
    }
    
    public void setNovedadTCREARptpatrCodigoLegal(String novedadTCREARptpatrCodigoLegal) {
        this.novedadTCREARptpatrCodigoLegal = novedadTCREARptpatrCodigoLegal;
    }

    private void checkNovedadTCREARptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRCODIGOLEGAL, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getNovedadTCREARptpatrReqDocPersona() {
        return this.novedadTCREARptpatrReqDocPersona;
    }
    
    public void setNovedadTCREARptpatrReqDocPersona(Boolean novedadTCREARptpatrReqDocPersona) {
        this.novedadTCREARptpatrReqDocPersona = novedadTCREARptpatrReqDocPersona;
    }

    private void checkNovedadTCREARptpatrReqDocPersona() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrReqDocPersona() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRREQDOCPERSONA, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRREQDOCPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getNovedadTCREARptpatrReqDocSolicitud() {
        return this.novedadTCREARptpatrReqDocSolicitud;
    }
    
    public void setNovedadTCREARptpatrReqDocSolicitud(Boolean novedadTCREARptpatrReqDocSolicitud) {
        this.novedadTCREARptpatrReqDocSolicitud = novedadTCREARptpatrReqDocSolicitud;
    }

    private void checkNovedadTCREARptpatrReqDocSolicitud() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrReqDocSolicitud() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRREQDOCSOLICITUD, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRREQDOCSOLICITUD);
        }
    }

    @JsonIgnore
    public Boolean getNovedadTCREARptpatrVariasFamilias() {
        return this.novedadTCREARptpatrVariasFamilias;
    }
    
    public void setNovedadTCREARptpatrVariasFamilias(Boolean novedadTCREARptpatrVariasFamilias) {
        this.novedadTCREARptpatrVariasFamilias = novedadTCREARptpatrVariasFamilias;
    }

    private void checkNovedadTCREARptpatrVariasFamilias() throws NotNullArgumentException {
        if(this.getNovedadTCREARptpatrVariasFamilias() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTPATRVARIASFAMILIAS, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTPATRVARIASFAMILIAS);
        }
    }

    @JsonIgnore
    public String getNovedadTCREARptAliasParaContrato() {
        return this.novedadTCREARptAliasParaContrato;
    }
    
    public void setNovedadTCREARptAliasParaContrato(String novedadTCREARptAliasParaContrato) {
        this.novedadTCREARptAliasParaContrato = novedadTCREARptAliasParaContrato;
    }

    @JsonIgnore
    public String getNovedadTCREARptAliasParaTitular() {
        return this.novedadTCREARptAliasParaTitular;
    }
    
    public void setNovedadTCREARptAliasParaTitular(String novedadTCREARptAliasParaTitular) {
        this.novedadTCREARptAliasParaTitular = novedadTCREARptAliasParaTitular;
    }

    @JsonIgnore
    public String getNovedadTCREARptAliasParaUsuario() {
        return this.novedadTCREARptAliasParaUsuario;
    }
    
    public void setNovedadTCREARptAliasParaUsuario(String novedadTCREARptAliasParaUsuario) {
        this.novedadTCREARptAliasParaUsuario = novedadTCREARptAliasParaUsuario;
    }

    @JsonIgnore
    public Long getNovedadTCREARptpatrMinValorMeses() {
        return this.novedadTCREARptpatrMinValorMeses;
    }
    
    public void setNovedadTCREARptpatrMinValorMeses(Long novedadTCREARptpatrMinValorMeses) {
        this.novedadTCREARptpatrMinValorMeses = novedadTCREARptpatrMinValorMeses;
    }

    @JsonIgnore
    public Long getNovedadTCREARptpatrMaxValorMeses() {
        return this.novedadTCREARptpatrMaxValorMeses;
    }
    
    public void setNovedadTCREARptpatrMaxValorMeses(Long novedadTCREARptpatrMaxValorMeses) {
        this.novedadTCREARptpatrMaxValorMeses = novedadTCREARptpatrMaxValorMeses;
    }

    @JsonIgnore
    public String getNovedadTCREARptAlias() {
        return this.novedadTCREARptAlias;
    }
    
    public void setNovedadTCREARptAlias(String novedadTCREARptAlias) {
        this.novedadTCREARptAlias = novedadTCREARptAlias;
    }

    private void checkNovedadTCREARptAlias() throws NotNullArgumentException {
        if(this.getNovedadTCREARptAlias() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_TCREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_TCREAR_NOVEDADTCREARPTALIAS, NovedadConstants.SERV_ALIAS_TCREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_TCREAR_NOVEDADTCREARPTALIAS);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
