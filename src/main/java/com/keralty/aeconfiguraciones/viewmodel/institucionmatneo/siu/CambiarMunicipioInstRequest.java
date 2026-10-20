package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class CambiarMunicipioInstRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance;

    @JsonProperty("p_evcmunicipio_oid")
    private MunicipioOid institucionMatNeoCambiarMunicipioInstpevcMunicipio;
    
    @JsonIgnore
    private Municipio institucionMatNeoCambiarMunicipioInstpevcMunicipioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo();
        checkInstitucionMatNeoCambiarMunicipioInstpevcMunicipio();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo() {
        return this.institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo = institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance() {
        return this.institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance = institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo;
        this.institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo = institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo != null ? institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARMUNICIPIOINST, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARMUNICIPIOINST_INSTITUCIONMATNEOCAMBIARMUNICIPIOINSTPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARMUNICIPIOINST, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARMUNICIPIOINST_INSTITUCIONMATNEOCAMBIARMUNICIPIOINSTPTHISINSTITUCIONMATNEO);
        }
    }

    @JsonIgnore
    public MunicipioOid getInstitucionMatNeoCambiarMunicipioInstpevcMunicipio() {
        return this.institucionMatNeoCambiarMunicipioInstpevcMunicipio;
    }
    
    public void setInstitucionMatNeoCambiarMunicipioInstpevcMunicipio(MunicipioOid institucionMatNeoCambiarMunicipioInstpevcMunicipio) {
        this.institucionMatNeoCambiarMunicipioInstpevcMunicipio = institucionMatNeoCambiarMunicipioInstpevcMunicipio;
    }

    @JsonIgnore
    public Municipio getInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance() {
        return this.institucionMatNeoCambiarMunicipioInstpevcMunicipioInstance;
    }
    
    public void setInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance(Municipio institucionMatNeoCambiarMunicipioInstpevcMunicipio) {
        this.institucionMatNeoCambiarMunicipioInstpevcMunicipioInstance = institucionMatNeoCambiarMunicipioInstpevcMunicipio;
        this.institucionMatNeoCambiarMunicipioInstpevcMunicipio = institucionMatNeoCambiarMunicipioInstpevcMunicipio != null ? institucionMatNeoCambiarMunicipioInstpevcMunicipio.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarMunicipioInstpevcMunicipio() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance() == null || this.getInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARMUNICIPIOINST, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARMUNICIPIOINST_INSTITUCIONMATNEOCAMBIARMUNICIPIOINSTPEVCMUNICIPIO, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARMUNICIPIOINST, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARMUNICIPIOINST_INSTITUCIONMATNEOCAMBIARMUNICIPIOINSTPEVCMUNICIPIO);
        }
    }
}
