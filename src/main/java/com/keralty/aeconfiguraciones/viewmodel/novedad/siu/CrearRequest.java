package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TipoNovedadOid;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;

public class CrearRequest {

    @JsonProperty("p_agrtiponovedad_oid")
    private TipoNovedadOid novedadcrearpagrTipoNovedad;
    
    @JsonIgnore
    private TipoNovedad novedadcrearpagrTipoNovedadInstance;

    @JsonProperty("p_atraliasnovedad")
    private String novedadcrearpatrAliasNovedad;

    @JsonProperty("p_atrnombre")
    private String novedadcrearpatrNombre;

    @JsonProperty("p_atrdescripcion")
    private String novedadcrearpatrDescripcion;

    @JsonProperty("p_atrcodigolegal")
    private String novedadcrearpatrCodigoLegal;

    @JsonProperty("p_atrreqdocpersona")
    private Boolean novedadcrearpatrReqDocPersona;

    @JsonProperty("p_atrreqdocsolicitud")
    private Boolean novedadcrearpatrReqDocSolicitud;

    @JsonProperty("p_atrvariasfamilias")
    private Boolean novedadcrearpatrVariasFamilias;

    @JsonProperty("p_atrminvalormeses")
    private Long novedadcrearpatrMinValorMeses;

    @JsonProperty("p_atrmaxvalormeses")
    private Long novedadcrearpatrMaxValorMeses;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadcrearpagrTipoNovedad();
        checkNovedadcrearpatrAliasNovedad();
        checkNovedadcrearpatrNombre();
        checkNovedadcrearpatrDescripcion();
        checkNovedadcrearpatrCodigoLegal();
        checkNovedadcrearpatrReqDocPersona();
        checkNovedadcrearpatrReqDocSolicitud();
        checkNovedadcrearpatrVariasFamilias();
    }

    @JsonIgnore
    public TipoNovedadOid getNovedadcrearpagrTipoNovedad() {
        return this.novedadcrearpagrTipoNovedad;
    }
    
    public void setNovedadcrearpagrTipoNovedad(TipoNovedadOid novedadcrearpagrTipoNovedad) {
        this.novedadcrearpagrTipoNovedad = novedadcrearpagrTipoNovedad;
    }

    @JsonIgnore
    public TipoNovedad getNovedadcrearpagrTipoNovedadInstance() {
        return this.novedadcrearpagrTipoNovedadInstance;
    }
    
    public void setNovedadcrearpagrTipoNovedadInstance(TipoNovedad novedadcrearpagrTipoNovedad) {
        this.novedadcrearpagrTipoNovedadInstance = novedadcrearpagrTipoNovedad;
        this.novedadcrearpagrTipoNovedad = novedadcrearpagrTipoNovedad != null ? novedadcrearpagrTipoNovedad.getOid() : null;
    }

    private void checkNovedadcrearpagrTipoNovedad() throws NotNullArgumentException {
        if(this.getNovedadcrearpagrTipoNovedadInstance() == null || this.getNovedadcrearpagrTipoNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPAGRTIPONOVEDAD, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPAGRTIPONOVEDAD);
        }
    }

    @JsonIgnore
    public String getNovedadcrearpatrAliasNovedad() {
        return this.novedadcrearpatrAliasNovedad;
    }
    
    public void setNovedadcrearpatrAliasNovedad(String novedadcrearpatrAliasNovedad) {
        this.novedadcrearpatrAliasNovedad = novedadcrearpatrAliasNovedad;
    }

    private void checkNovedadcrearpatrAliasNovedad() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrAliasNovedad() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRALIASNOVEDAD, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRALIASNOVEDAD);
        }
    }

    @JsonIgnore
    public String getNovedadcrearpatrNombre() {
        return this.novedadcrearpatrNombre;
    }
    
    public void setNovedadcrearpatrNombre(String novedadcrearpatrNombre) {
        this.novedadcrearpatrNombre = novedadcrearpatrNombre;
    }

    private void checkNovedadcrearpatrNombre() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrNombre() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRNOMBRE, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getNovedadcrearpatrDescripcion() {
        return this.novedadcrearpatrDescripcion;
    }
    
    public void setNovedadcrearpatrDescripcion(String novedadcrearpatrDescripcion) {
        this.novedadcrearpatrDescripcion = novedadcrearpatrDescripcion;
    }

    private void checkNovedadcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRDESCRIPCION, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getNovedadcrearpatrCodigoLegal() {
        return this.novedadcrearpatrCodigoLegal;
    }
    
    public void setNovedadcrearpatrCodigoLegal(String novedadcrearpatrCodigoLegal) {
        this.novedadcrearpatrCodigoLegal = novedadcrearpatrCodigoLegal;
    }

    private void checkNovedadcrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRCODIGOLEGAL, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getNovedadcrearpatrReqDocPersona() {
        return this.novedadcrearpatrReqDocPersona;
    }
    
    public void setNovedadcrearpatrReqDocPersona(Boolean novedadcrearpatrReqDocPersona) {
        this.novedadcrearpatrReqDocPersona = novedadcrearpatrReqDocPersona;
    }

    private void checkNovedadcrearpatrReqDocPersona() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrReqDocPersona() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRREQDOCPERSONA, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRREQDOCPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getNovedadcrearpatrReqDocSolicitud() {
        return this.novedadcrearpatrReqDocSolicitud;
    }
    
    public void setNovedadcrearpatrReqDocSolicitud(Boolean novedadcrearpatrReqDocSolicitud) {
        this.novedadcrearpatrReqDocSolicitud = novedadcrearpatrReqDocSolicitud;
    }

    private void checkNovedadcrearpatrReqDocSolicitud() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrReqDocSolicitud() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRREQDOCSOLICITUD, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRREQDOCSOLICITUD);
        }
    }

    @JsonIgnore
    public Boolean getNovedadcrearpatrVariasFamilias() {
        return this.novedadcrearpatrVariasFamilias;
    }
    
    public void setNovedadcrearpatrVariasFamilias(Boolean novedadcrearpatrVariasFamilias) {
        this.novedadcrearpatrVariasFamilias = novedadcrearpatrVariasFamilias;
    }

    private void checkNovedadcrearpatrVariasFamilias() throws NotNullArgumentException {
        if(this.getNovedadcrearpatrVariasFamilias() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_CREAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_CREAR_NOVEDADCREARPATRVARIASFAMILIAS, NovedadConstants.SERV_ALIAS_CREAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_CREAR_NOVEDADCREARPATRVARIASFAMILIAS);
        }
    }

    @JsonIgnore
    public Long getNovedadcrearpatrMinValorMeses() {
        return this.novedadcrearpatrMinValorMeses;
    }
    
    public void setNovedadcrearpatrMinValorMeses(Long novedadcrearpatrMinValorMeses) {
        this.novedadcrearpatrMinValorMeses = novedadcrearpatrMinValorMeses;
    }

    @JsonIgnore
    public Long getNovedadcrearpatrMaxValorMeses() {
        return this.novedadcrearpatrMaxValorMeses;
    }
    
    public void setNovedadcrearpatrMaxValorMeses(Long novedadcrearpatrMaxValorMeses) {
        this.novedadcrearpatrMaxValorMeses = novedadcrearpatrMaxValorMeses;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
