package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiarioae.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioAEConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiarioAE;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioAEOid;

public class EliminarRequest {

    @JsonProperty("p_thisestadobeneficiarioae_oid")
    private EstadoBeneficiarioAEOid estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE;
    
    @JsonIgnore
    private EstadoBeneficiarioAE estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE();
    }

    @JsonIgnore
    public EstadoBeneficiarioAEOid getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE() {
        return this.estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE;
    }
    
    public void setEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE(EstadoBeneficiarioAEOid estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE = estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE;
    }

    @JsonIgnore
    public EstadoBeneficiarioAE getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance() {
        return this.estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance;
    }
    
    public void setEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance(EstadoBeneficiarioAE estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE) {
        this.estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance = estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE;
        this.estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE = estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE != null ? estadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE.getOid() : null;
    }

    private void checkEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAE() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance() == null || this.getEstadoBeneficiarioAEeliminarpthisEstadoBeneficiarioAEInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioAEConstants.SERV_ID_ELIMINAR, EstadoBeneficiarioAEConstants.CLASS_ID, EstadoBeneficiarioAEConstants.ARG_ID_ELIMINAR_ESTADOBENEFICIARIOAEELIMINARPTHISESTADOBENEFICIARIOAE, EstadoBeneficiarioAEConstants.SERV_ALIAS_ELIMINAR, EstadoBeneficiarioAEConstants.CLASS_ALIAS, EstadoBeneficiarioAEConstants.ARG_ALIAS_ELIMINAR_ESTADOBENEFICIARIOAEELIMINARPTHISESTADOBENEFICIARIOAE);
        }
    }
}
