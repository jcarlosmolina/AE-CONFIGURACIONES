package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;

public class CambiarTipoIdentificacionRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance;

    @JsonProperty("p_evctipoidentificacion_oid")
    private TipoIdentificacionOid institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo();
        checkInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo() {
        return this.institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo = institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance() {
        return this.institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance = institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo;
        this.institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo = institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo != null ? institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARTIPOIDENTIFICACION, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARTIPOIDENTIFICACION_INSTITUCIONMATNEOCAMBIARTIPOIDENTIFICACIONPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARTIPOIDENTIFICACION, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARTIPOIDENTIFICACION_INSTITUCIONMATNEOCAMBIARTIPOIDENTIFICACIONPTHISINSTITUCIONMATNEO);
        }
    }

    @JsonIgnore
    public TipoIdentificacionOid getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion() {
        return this.institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion;
    }
    
    public void setInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion(TipoIdentificacionOid institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion) {
        this.institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion = institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance() {
        return this.institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance;
    }
    
    public void setInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance(TipoIdentificacion institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion) {
        this.institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance = institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion;
        this.institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion = institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion != null ? institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance() == null || this.getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARTIPOIDENTIFICACION, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARTIPOIDENTIFICACION_INSTITUCIONMATNEOCAMBIARTIPOIDENTIFICACIONPEVCTIPOIDENTIFICACION, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARTIPOIDENTIFICACION, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARTIPOIDENTIFICACION_INSTITUCIONMATNEOCAMBIARTIPOIDENTIFICACIONPEVCTIPOIDENTIFICACION);
        }
    }
}
