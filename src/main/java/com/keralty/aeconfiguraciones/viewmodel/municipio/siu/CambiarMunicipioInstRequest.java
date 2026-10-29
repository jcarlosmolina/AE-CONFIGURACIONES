package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class CambiarMunicipioInstRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioCambiarMunicipioInstpthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioCambiarMunicipioInstpthisMunicipioInstance;

    @JsonProperty("p_evcinstitucionmatneo_oid")
    private InstitucionMatNeoOid municipioCambiarMunicipioInstpevcInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo municipioCambiarMunicipioInstpevcInstitucionMatNeoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioCambiarMunicipioInstpthisMunicipio();
        checkMunicipioCambiarMunicipioInstpevcInstitucionMatNeo();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioCambiarMunicipioInstpthisMunicipio() {
        return this.municipioCambiarMunicipioInstpthisMunicipio;
    }
    
    public void setMunicipioCambiarMunicipioInstpthisMunicipio(MunicipioOid municipioCambiarMunicipioInstpthisMunicipio) {
        this.municipioCambiarMunicipioInstpthisMunicipio = municipioCambiarMunicipioInstpthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioCambiarMunicipioInstpthisMunicipioInstance() {
        return this.municipioCambiarMunicipioInstpthisMunicipioInstance;
    }
    
    public void setMunicipioCambiarMunicipioInstpthisMunicipioInstance(Municipio municipioCambiarMunicipioInstpthisMunicipio) {
        this.municipioCambiarMunicipioInstpthisMunicipioInstance = municipioCambiarMunicipioInstpthisMunicipio;
        this.municipioCambiarMunicipioInstpthisMunicipio = municipioCambiarMunicipioInstpthisMunicipio != null ? municipioCambiarMunicipioInstpthisMunicipio.getOid() : null;
    }

    private void checkMunicipioCambiarMunicipioInstpthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioCambiarMunicipioInstpthisMunicipioInstance() == null || this.getMunicipioCambiarMunicipioInstpthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CAMBIARMUNICIPIOINST, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CAMBIARMUNICIPIOINST_MUNICIPIOCAMBIARMUNICIPIOINSTPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_CAMBIARMUNICIPIOINST, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CAMBIARMUNICIPIOINST_MUNICIPIOCAMBIARMUNICIPIOINSTPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public InstitucionMatNeoOid getMunicipioCambiarMunicipioInstpevcInstitucionMatNeo() {
        return this.municipioCambiarMunicipioInstpevcInstitucionMatNeo;
    }
    
    public void setMunicipioCambiarMunicipioInstpevcInstitucionMatNeo(InstitucionMatNeoOid municipioCambiarMunicipioInstpevcInstitucionMatNeo) {
        this.municipioCambiarMunicipioInstpevcInstitucionMatNeo = municipioCambiarMunicipioInstpevcInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getMunicipioCambiarMunicipioInstpevcInstitucionMatNeoInstance() {
        return this.municipioCambiarMunicipioInstpevcInstitucionMatNeoInstance;
    }
    
    public void setMunicipioCambiarMunicipioInstpevcInstitucionMatNeoInstance(InstitucionMatNeo municipioCambiarMunicipioInstpevcInstitucionMatNeo) {
        this.municipioCambiarMunicipioInstpevcInstitucionMatNeoInstance = municipioCambiarMunicipioInstpevcInstitucionMatNeo;
        this.municipioCambiarMunicipioInstpevcInstitucionMatNeo = municipioCambiarMunicipioInstpevcInstitucionMatNeo != null ? municipioCambiarMunicipioInstpevcInstitucionMatNeo.getOid() : null;
    }

    private void checkMunicipioCambiarMunicipioInstpevcInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getMunicipioCambiarMunicipioInstpevcInstitucionMatNeoInstance() == null || this.getMunicipioCambiarMunicipioInstpevcInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_CAMBIARMUNICIPIOINST, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_CAMBIARMUNICIPIOINST_MUNICIPIOCAMBIARMUNICIPIOINSTPEVCINSTITUCIONMATNEO, MunicipioConstants.SERV_ALIAS_CAMBIARMUNICIPIOINST, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_CAMBIARMUNICIPIOINST_MUNICIPIOCAMBIARMUNICIPIOINSTPEVCINSTITUCIONMATNEO);
        }
    }
}
