package com.keralty.aeconfiguraciones.viewmodel.causal.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;

public class ModificarRequest {

    @JsonProperty("p_thiscausal_oid")
    private CausalOid causalmodificarpthisCausal;
    
    @JsonIgnore
    private Causal causalmodificarpthisCausalInstance;

    @JsonProperty("p_aliascausal")
    private String causalmodificarpAliasCausal;

    @JsonProperty("p_nombre")
    private String causalmodificarpNombre;

    @JsonProperty("p_descripcion")
    private String causalmodificarpDescripcion;

    @JsonProperty("p_codigolegal")
    private String causalmodificarpCodigoLegal;

    @JsonProperty("p_reqdocpersona")
    private Boolean causalmodificarpReqDocPersona;

    @JsonProperty("p_reqdocsolicitud")
    private Boolean causalmodificarpReqDocSolicitud;

    public void checkArguments() throws NotNullArgumentException {
        checkCausalmodificarpthisCausal();
        checkCausalmodificarpAliasCausal();
        checkCausalmodificarpNombre();
        checkCausalmodificarpDescripcion();
        checkCausalmodificarpCodigoLegal();
        checkCausalmodificarpReqDocPersona();
        checkCausalmodificarpReqDocSolicitud();
    }

    @JsonIgnore
    public CausalOid getCausalmodificarpthisCausal() {
        return this.causalmodificarpthisCausal;
    }
    
    public void setCausalmodificarpthisCausal(CausalOid causalmodificarpthisCausal) {
        this.causalmodificarpthisCausal = causalmodificarpthisCausal;
    }

    @JsonIgnore
    public Causal getCausalmodificarpthisCausalInstance() {
        return this.causalmodificarpthisCausalInstance;
    }
    
    public void setCausalmodificarpthisCausalInstance(Causal causalmodificarpthisCausal) {
        this.causalmodificarpthisCausalInstance = causalmodificarpthisCausal;
        this.causalmodificarpthisCausal = causalmodificarpthisCausal != null ? causalmodificarpthisCausal.getOid() : null;
    }

    private void checkCausalmodificarpthisCausal() throws NotNullArgumentException {
        if(this.getCausalmodificarpthisCausalInstance() == null || this.getCausalmodificarpthisCausalInstance().isNull()) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPTHISCAUSAL, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPTHISCAUSAL);
        }
    }

    @JsonIgnore
    public String getCausalmodificarpAliasCausal() {
        return this.causalmodificarpAliasCausal;
    }
    
    public void setCausalmodificarpAliasCausal(String causalmodificarpAliasCausal) {
        this.causalmodificarpAliasCausal = causalmodificarpAliasCausal;
    }

    private void checkCausalmodificarpAliasCausal() throws NotNullArgumentException {
        if(this.getCausalmodificarpAliasCausal() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPALIASCAUSAL, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPALIASCAUSAL);
        }
    }

    @JsonIgnore
    public String getCausalmodificarpNombre() {
        return this.causalmodificarpNombre;
    }
    
    public void setCausalmodificarpNombre(String causalmodificarpNombre) {
        this.causalmodificarpNombre = causalmodificarpNombre;
    }

    private void checkCausalmodificarpNombre() throws NotNullArgumentException {
        if(this.getCausalmodificarpNombre() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPNOMBRE, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPNOMBRE);
        }
    }

    @JsonIgnore
    public String getCausalmodificarpDescripcion() {
        return this.causalmodificarpDescripcion;
    }
    
    public void setCausalmodificarpDescripcion(String causalmodificarpDescripcion) {
        this.causalmodificarpDescripcion = causalmodificarpDescripcion;
    }

    private void checkCausalmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getCausalmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPDESCRIPCION, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getCausalmodificarpCodigoLegal() {
        return this.causalmodificarpCodigoLegal;
    }
    
    public void setCausalmodificarpCodigoLegal(String causalmodificarpCodigoLegal) {
        this.causalmodificarpCodigoLegal = causalmodificarpCodigoLegal;
    }

    private void checkCausalmodificarpCodigoLegal() throws NotNullArgumentException {
        if(this.getCausalmodificarpCodigoLegal() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPCODIGOLEGAL, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getCausalmodificarpReqDocPersona() {
        return this.causalmodificarpReqDocPersona;
    }
    
    public void setCausalmodificarpReqDocPersona(Boolean causalmodificarpReqDocPersona) {
        this.causalmodificarpReqDocPersona = causalmodificarpReqDocPersona;
    }

    private void checkCausalmodificarpReqDocPersona() throws NotNullArgumentException {
        if(this.getCausalmodificarpReqDocPersona() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPREQDOCPERSONA, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPREQDOCPERSONA);
        }
    }

    @JsonIgnore
    public Boolean getCausalmodificarpReqDocSolicitud() {
        return this.causalmodificarpReqDocSolicitud;
    }
    
    public void setCausalmodificarpReqDocSolicitud(Boolean causalmodificarpReqDocSolicitud) {
        this.causalmodificarpReqDocSolicitud = causalmodificarpReqDocSolicitud;
    }

    private void checkCausalmodificarpReqDocSolicitud() throws NotNullArgumentException {
        if(this.getCausalmodificarpReqDocSolicitud() == null) {
            throw new NotNullArgumentException(CausalConstants.SERV_ID_MODIFICAR, CausalConstants.CLASS_ID, CausalConstants.ARG_ID_MODIFICAR_CAUSALMODIFICARPREQDOCSOLICITUD, CausalConstants.SERV_ALIAS_MODIFICAR, CausalConstants.CLASS_ALIAS, CausalConstants.ARG_ALIAS_MODIFICAR_CAUSALMODIFICARPREQDOCSOLICITUD);
        }
    }
}
