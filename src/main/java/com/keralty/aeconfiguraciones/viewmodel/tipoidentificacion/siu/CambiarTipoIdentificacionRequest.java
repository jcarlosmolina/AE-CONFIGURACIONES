package com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;

public class CambiarTipoIdentificacionRequest {

    @JsonProperty("p_thistipoidentificacion_oid")
    private TipoIdentificacionOid tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance;

    @JsonProperty("p_evcinstitucionmatneo_oid")
    private InstitucionMatNeoOid tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion();
        checkTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo();
    }

    @JsonIgnore
    public TipoIdentificacionOid getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion() {
        return this.tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion;
    }
    
    public void setTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion(TipoIdentificacionOid tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion) {
        this.tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion = tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance() {
        return this.tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance;
    }
    
    public void setTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance(TipoIdentificacion tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion) {
        this.tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance = tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion;
        this.tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion = tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion != null ? tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion.getOid() : null;
    }

    private void checkTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion() throws NotNullArgumentException {
        if(this.getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance() == null || this.getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_CAMBIARTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_CAMBIARTIPOIDENTIFICACION_TIPOIDENTIFICACIONCAMBIARTIPOIDENTIFICACIONPTHISTIPOIDENTIFICACION, TipoIdentificacionConstants.SERV_ALIAS_CAMBIARTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_CAMBIARTIPOIDENTIFICACION_TIPOIDENTIFICACIONCAMBIARTIPOIDENTIFICACIONPTHISTIPOIDENTIFICACION);
        }
    }

    @JsonIgnore
    public InstitucionMatNeoOid getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo() {
        return this.tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo;
    }
    
    public void setTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo(InstitucionMatNeoOid tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo) {
        this.tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo = tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance() {
        return this.tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance;
    }
    
    public void setTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance(InstitucionMatNeo tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo) {
        this.tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance = tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo;
        this.tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo = tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo != null ? tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo.getOid() : null;
    }

    private void checkTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance() == null || this.getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_CAMBIARTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_CAMBIARTIPOIDENTIFICACION_TIPOIDENTIFICACIONCAMBIARTIPOIDENTIFICACIONPEVCINSTITUCIONMATNEO, TipoIdentificacionConstants.SERV_ALIAS_CAMBIARTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_CAMBIARTIPOIDENTIFICACION_TIPOIDENTIFICACIONCAMBIARTIPOIDENTIFICACIONPEVCINSTITUCIONMATNEO);
        }
    }
}
