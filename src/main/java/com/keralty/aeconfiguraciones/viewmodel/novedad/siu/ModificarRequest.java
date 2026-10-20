package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class ModificarRequest {

    @JsonProperty("p_thisnovedad_oid")
    private NovedadOid novedadmodificarpthisNovedad;
    
    @JsonIgnore
    private Novedad novedadmodificarpthisNovedadInstance;

    @JsonProperty("p_descripcion")
    private String novedadmodificarpDescripcion;

    @JsonProperty("p_codigolegal")
    private String novedadmodificarpCodigoLegal;

    @JsonProperty("p_reqdocpersona")
    private Boolean novedadmodificarpReqDocPersona;

    @JsonProperty("p_reqdocsolicitud")
    private Boolean novedadmodificarpReqDocSolicitud;

    @JsonProperty("p_variasfamilias")
    private Boolean novedadmodificarpVariasFamilias;

    @JsonProperty("p_nombre")
    private String novedadmodificarpNombre;

    @JsonProperty("p_minvalormeses")
    private Long novedadmodificarpMinValorMeses;

    @JsonProperty("p_maxvalormeses")
    private Long novedadmodificarpMaxValorMeses;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadmodificarpthisNovedad();
        checkNovedadmodificarpDescripcion();
        checkNovedadmodificarpCodigoLegal();
        checkNovedadmodificarpReqDocPersona();
        checkNovedadmodificarpReqDocSolicitud();
        checkNovedadmodificarpVariasFamilias();
        checkNovedadmodificarpNombre();
    }

    @JsonIgnore
    public NovedadOid getNovedadmodificarpthisNovedad() {
        return this.novedadmodificarpthisNovedad;
    }
    
    public void setNovedadmodificarpthisNovedad(NovedadOid novedadmodificarpthisNovedad) {
        this.novedadmodificarpthisNovedad = novedadmodificarpthisNovedad;
    }

    @JsonIgnore
    public Novedad getNovedadmodificarpthisNovedadInstance() {
        return this.novedadmodificarpthisNovedadInstance;
    }
    
    public void setNovedadmodificarpthisNovedadInstance(Novedad novedadmodificarpthisNovedad) {
        this.novedadmodificarpthisNovedadInstance = novedadmodificarpthisNovedad;
        this.novedadmodificarpthisNovedad = novedadmodificarpthisNovedad != null ? novedadmodificarpthisNovedad.getOid() : null;
    }

    private void checkNovedadmodificarpthisNovedad() throws NotNullArgumentException {
        if(this.getNovedadmodificarpthisNovedadInstance() == null || this.getNovedadmodificarpthisNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPTHISNOVEDAD, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPTHISNOVEDAD);
        }
    }

    @JsonIgnore
    public String getNovedadmodificarpDescripcion() {
        return this.novedadmodificarpDescripcion;
    }
    
    public void setNovedadmodificarpDescripcion(String novedadmodificarpDescripcion) {
        this.novedadmodificarpDescripcion = novedadmodificarpDescripcion;
    }

    private void checkNovedadmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getNovedadmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPDESCRIPCION, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getNovedadmodificarpCodigoLegal() {
        return this.novedadmodificarpCodigoLegal;
    }
    
    public void setNovedadmodificarpCodigoLegal(String novedadmodificarpCodigoLegal) {
        this.novedadmodificarpCodigoLegal = novedadmodificarpCodigoLegal;
    }

    private void checkNovedadmodificarpCodigoLegal() throws NotNullArgumentException {
        if(this.getNovedadmodificarpCodigoLegal() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPCODIGOLEGAL, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getNovedadmodificarpReqDocPersona() {
        return this.novedadmodificarpReqDocPersona;
    }
    
    public void setNovedadmodificarpReqDocPersona(Boolean novedadmodificarpReqDocPersona) {
        this.novedadmodificarpReqDocPersona = novedadmodificarpReqDocPersona;
    }

    private void checkNovedadmodificarpReqDocPersona() throws NotNullArgumentException {
        if(this.getNovedadmodificarpReqDocPersona() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPREQDOCPERSONA, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPREQDOCPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getNovedadmodificarpReqDocSolicitud() {
        return this.novedadmodificarpReqDocSolicitud;
    }
    
    public void setNovedadmodificarpReqDocSolicitud(Boolean novedadmodificarpReqDocSolicitud) {
        this.novedadmodificarpReqDocSolicitud = novedadmodificarpReqDocSolicitud;
    }

    private void checkNovedadmodificarpReqDocSolicitud() throws NotNullArgumentException {
        if(this.getNovedadmodificarpReqDocSolicitud() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPREQDOCSOLICITUD, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPREQDOCSOLICITUD);
        }
    }

    @JsonIgnore
    public Boolean getNovedadmodificarpVariasFamilias() {
        return this.novedadmodificarpVariasFamilias;
    }
    
    public void setNovedadmodificarpVariasFamilias(Boolean novedadmodificarpVariasFamilias) {
        this.novedadmodificarpVariasFamilias = novedadmodificarpVariasFamilias;
    }

    private void checkNovedadmodificarpVariasFamilias() throws NotNullArgumentException {
        if(this.getNovedadmodificarpVariasFamilias() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPVARIASFAMILIAS, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPVARIASFAMILIAS);
        }
    }

    @JsonIgnore
    public String getNovedadmodificarpNombre() {
        return this.novedadmodificarpNombre;
    }
    
    public void setNovedadmodificarpNombre(String novedadmodificarpNombre) {
        this.novedadmodificarpNombre = novedadmodificarpNombre;
    }

    private void checkNovedadmodificarpNombre() throws NotNullArgumentException {
        if(this.getNovedadmodificarpNombre() == null) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_MODIFICAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_MODIFICAR_NOVEDADMODIFICARPNOMBRE, NovedadConstants.SERV_ALIAS_MODIFICAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_MODIFICAR_NOVEDADMODIFICARPNOMBRE);
        }
    }

    @JsonIgnore
    public Long getNovedadmodificarpMinValorMeses() {
        return this.novedadmodificarpMinValorMeses;
    }
    
    public void setNovedadmodificarpMinValorMeses(Long novedadmodificarpMinValorMeses) {
        this.novedadmodificarpMinValorMeses = novedadmodificarpMinValorMeses;
    }

    @JsonIgnore
    public Long getNovedadmodificarpMaxValorMeses() {
        return this.novedadmodificarpMaxValorMeses;
    }
    
    public void setNovedadmodificarpMaxValorMeses(Long novedadmodificarpMaxValorMeses) {
        this.novedadmodificarpMaxValorMeses = novedadmodificarpMaxValorMeses;
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
