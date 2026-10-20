package com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;

public class CambiarTarifaRequest {

    @JsonProperty("p_thisinstitucionmatneo_oid")
    private InstitucionMatNeoOid institucionMatNeoCambiarTarifapthisInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo institucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance;

    @JsonProperty("p_evctarifa_oid")
    private TarifaOid institucionMatNeoCambiarTarifapevcTarifa;
    
    @JsonIgnore
    private Tarifa institucionMatNeoCambiarTarifapevcTarifaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkInstitucionMatNeoCambiarTarifapthisInstitucionMatNeo();
        checkInstitucionMatNeoCambiarTarifapevcTarifa();
    }

    @JsonIgnore
    public InstitucionMatNeoOid getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeo() {
        return this.institucionMatNeoCambiarTarifapthisInstitucionMatNeo;
    }
    
    public void setInstitucionMatNeoCambiarTarifapthisInstitucionMatNeo(InstitucionMatNeoOid institucionMatNeoCambiarTarifapthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarTarifapthisInstitucionMatNeo = institucionMatNeoCambiarTarifapthisInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance() {
        return this.institucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance;
    }
    
    public void setInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance(InstitucionMatNeo institucionMatNeoCambiarTarifapthisInstitucionMatNeo) {
        this.institucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance = institucionMatNeoCambiarTarifapthisInstitucionMatNeo;
        this.institucionMatNeoCambiarTarifapthisInstitucionMatNeo = institucionMatNeoCambiarTarifapthisInstitucionMatNeo != null ? institucionMatNeoCambiarTarifapthisInstitucionMatNeo.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarTarifapthisInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance() == null || this.getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARTARIFA, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARTARIFA_INSTITUCIONMATNEOCAMBIARTARIFAPTHISINSTITUCIONMATNEO, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARTARIFA, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARTARIFA_INSTITUCIONMATNEOCAMBIARTARIFAPTHISINSTITUCIONMATNEO);
        }
    }

    @JsonIgnore
    public TarifaOid getInstitucionMatNeoCambiarTarifapevcTarifa() {
        return this.institucionMatNeoCambiarTarifapevcTarifa;
    }
    
    public void setInstitucionMatNeoCambiarTarifapevcTarifa(TarifaOid institucionMatNeoCambiarTarifapevcTarifa) {
        this.institucionMatNeoCambiarTarifapevcTarifa = institucionMatNeoCambiarTarifapevcTarifa;
    }

    @JsonIgnore
    public Tarifa getInstitucionMatNeoCambiarTarifapevcTarifaInstance() {
        return this.institucionMatNeoCambiarTarifapevcTarifaInstance;
    }
    
    public void setInstitucionMatNeoCambiarTarifapevcTarifaInstance(Tarifa institucionMatNeoCambiarTarifapevcTarifa) {
        this.institucionMatNeoCambiarTarifapevcTarifaInstance = institucionMatNeoCambiarTarifapevcTarifa;
        this.institucionMatNeoCambiarTarifapevcTarifa = institucionMatNeoCambiarTarifapevcTarifa != null ? institucionMatNeoCambiarTarifapevcTarifa.getOid() : null;
    }

    private void checkInstitucionMatNeoCambiarTarifapevcTarifa() throws NotNullArgumentException {
        if(this.getInstitucionMatNeoCambiarTarifapevcTarifaInstance() == null || this.getInstitucionMatNeoCambiarTarifapevcTarifaInstance().isNull()) {
            throw new NotNullArgumentException(InstitucionMatNeoConstants.SERV_ID_CAMBIARTARIFA, InstitucionMatNeoConstants.CLASS_ID, InstitucionMatNeoConstants.ARG_ID_CAMBIARTARIFA_INSTITUCIONMATNEOCAMBIARTARIFAPEVCTARIFA, InstitucionMatNeoConstants.SERV_ALIAS_CAMBIARTARIFA, InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ARG_ALIAS_CAMBIARTARIFA_INSTITUCIONMATNEOCAMBIARTARIFAPEVCTARIFA);
        }
    }
}
