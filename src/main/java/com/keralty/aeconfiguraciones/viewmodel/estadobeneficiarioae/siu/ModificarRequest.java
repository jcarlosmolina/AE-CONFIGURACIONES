package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;

public class ModificarRequest {

    @JsonProperty("p_thisestadobeneficiarioae_oid")
    private EstadoBeneficiarioAEOid estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE;
    
    @JsonIgnore
    private EstadoBeneficiarioAE estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance;

    @JsonProperty("p_descripcion")
    private String estadoBeneficiarioAEmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE();
        checkEstadoBeneficiarioAEmodificarpDescripcion();
    }

    @JsonIgnore
    public EstadoBeneficiarioAEOid getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE() {
        return this.estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE;
    }
    
    public void setEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE(EstadoBeneficiarioAEOid estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE = estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE;
    }

    @JsonIgnore
    public EstadoBeneficiarioAE getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance() {
        return this.estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance;
    }
    
    public void setEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance(EstadoBeneficiarioAE estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance = estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE;
        this.estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE = estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE != null ? estadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE.getOid() : null;
    }

    private void checkEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance() == null || this.getEstadoBeneficiarioAEmodificarpthisEstadoBeneficiarioAEInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOAEMODIFICARPTHISESTADOBENEFICIARIOAE, EstadoBeneficiarioAEConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOAEMODIFICARPTHISESTADOBENEFICIARIOAE);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiarioAEmodificarpDescripcion() {
        return this.estadoBeneficiarioAEmodificarpDescripcion;
    }
    
    public void setEstadoBeneficiarioAEmodificarpDescripcion(String estadoBeneficiarioAEmodificarpDescripcion) {
        this.estadoBeneficiarioAEmodificarpDescripcion = estadoBeneficiarioAEmodificarpDescripcion;
    }

    private void checkEstadoBeneficiarioAEmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAEmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOAEMODIFICARPDESCRIPCION, EstadoBeneficiarioAEConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOAEMODIFICARPDESCRIPCION);
        }
    }
}
