package com.keralty.aeconfiguraciones.viewmodel.causal.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class CrearRequest {

    @JsonProperty("p_agrnovedad_oid")
    private NovedadOid causalcrearpagrNovedad;
    
    @JsonIgnore
    private Novedad causalcrearpagrNovedadInstance;

    @JsonProperty("p_atraliascausal")
    private String causalcrearpatrAliasCausal;

    @JsonProperty("p_atrnombre")
    private String causalcrearpatrNombre;

    @JsonProperty("p_atrdescripcion")
    private String causalcrearpatrDescripcion;

    @JsonProperty("p_atrcodigolegal")
    private String causalcrearpatrCodigoLegal;

    @JsonProperty("p_atrreqdocpersona")
    private Boolean causalcrearpatrReqDocPersona;

    @JsonProperty("p_atrreqdocsolicitud")
    private Boolean causalcrearpatrReqDocSolicitud;

    public void checkArguments() throws NotNullArgumentException {
        checkCausalcrearpagrNovedad();
        checkCausalcrearpatrAliasCausal();
        checkCausalcrearpatrNombre();
        checkCausalcrearpatrDescripcion();
        checkCausalcrearpatrCodigoLegal();
        checkCausalcrearpatrReqDocPersona();
        checkCausalcrearpatrReqDocSolicitud();
    }

    @JsonIgnore
    public NovedadOid getCausalcrearpagrNovedad() {
        return this.causalcrearpagrNovedad;
    }
    
    public void setCausalcrearpagrNovedad(NovedadOid causalcrearpagrNovedad) {
        this.causalcrearpagrNovedad = causalcrearpagrNovedad;
    }

    @JsonIgnore
    public Novedad getCausalcrearpagrNovedadInstance() {
        return this.causalcrearpagrNovedadInstance;
    }
    
    public void setCausalcrearpagrNovedadInstance(Novedad causalcrearpagrNovedad) {
        this.causalcrearpagrNovedadInstance = causalcrearpagrNovedad;
        this.causalcrearpagrNovedad = causalcrearpagrNovedad != null ? causalcrearpagrNovedad.getOid() : null;
    }

    private void checkCausalcrearpagrNovedad() throws NotNullArgumentException {
        if(this.getCausalcrearpagrNovedadInstance() == null || this.getCausalcrearpagrNovedadInstance().isNull()) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPAGRNOVEDAD, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPAGRNOVEDAD);
        }
    }

    @JsonIgnore
    public String getCausalcrearpatrAliasCausal() {
        return this.causalcrearpatrAliasCausal;
    }
    
    public void setCausalcrearpatrAliasCausal(String causalcrearpatrAliasCausal) {
        this.causalcrearpatrAliasCausal = causalcrearpatrAliasCausal;
    }

    private void checkCausalcrearpatrAliasCausal() throws NotNullArgumentException {
        if(this.getCausalcrearpatrAliasCausal() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRALIASCAUSAL, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRALIASCAUSAL);
        }
    }

    @JsonIgnore
    public String getCausalcrearpatrNombre() {
        return this.causalcrearpatrNombre;
    }
    
    public void setCausalcrearpatrNombre(String causalcrearpatrNombre) {
        this.causalcrearpatrNombre = causalcrearpatrNombre;
    }

    private void checkCausalcrearpatrNombre() throws NotNullArgumentException {
        if(this.getCausalcrearpatrNombre() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRNOMBRE, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getCausalcrearpatrDescripcion() {
        return this.causalcrearpatrDescripcion;
    }
    
    public void setCausalcrearpatrDescripcion(String causalcrearpatrDescripcion) {
        this.causalcrearpatrDescripcion = causalcrearpatrDescripcion;
    }

    private void checkCausalcrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getCausalcrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRDESCRIPCION, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getCausalcrearpatrCodigoLegal() {
        return this.causalcrearpatrCodigoLegal;
    }
    
    public void setCausalcrearpatrCodigoLegal(String causalcrearpatrCodigoLegal) {
        this.causalcrearpatrCodigoLegal = causalcrearpatrCodigoLegal;
    }

    private void checkCausalcrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getCausalcrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRCODIGOLEGAL, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getCausalcrearpatrReqDocPersona() {
        return this.causalcrearpatrReqDocPersona;
    }
    
    public void setCausalcrearpatrReqDocPersona(Boolean causalcrearpatrReqDocPersona) {
        this.causalcrearpatrReqDocPersona = causalcrearpatrReqDocPersona;
    }

    private void checkCausalcrearpatrReqDocPersona() throws NotNullArgumentException {
        if(this.getCausalcrearpatrReqDocPersona() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRREQDOCPERSONA, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRREQDOCPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getCausalcrearpatrReqDocSolicitud() {
        return this.causalcrearpatrReqDocSolicitud;
    }
    
    public void setCausalcrearpatrReqDocSolicitud(Boolean causalcrearpatrReqDocSolicitud) {
        this.causalcrearpatrReqDocSolicitud = causalcrearpatrReqDocSolicitud;
    }

    private void checkCausalcrearpatrReqDocSolicitud() throws NotNullArgumentException {
        if(this.getCausalcrearpatrReqDocSolicitud() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_CREAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_CREAR_CAUSALCREARPATRREQDOCSOLICITUD, CausalConstants.SERV_ALIAS_CREAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_CREAR_CAUSALCREARPATRREQDOCSOLICITUD);
        }
    }
}
