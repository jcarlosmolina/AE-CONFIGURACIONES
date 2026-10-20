package com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoCivilConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;

public class EliminarRequest {

    @JsonProperty("p_thisestadocivil_oid")
    private EstadoCivilOid estadoCivileliminarpthisEstadoCivil;
    
    @JsonIgnore
    private EstadoCivil estadoCivileliminarpthisEstadoCivilInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoCivileliminarpthisEstadoCivil();
    }

    @JsonIgnore
    public EstadoCivilOid getEstadoCivileliminarpthisEstadoCivil() {
        return this.estadoCivileliminarpthisEstadoCivil;
    }
    
    public void setEstadoCivileliminarpthisEstadoCivil(EstadoCivilOid estadoCivileliminarpthisEstadoCivil) {
        this.estadoCivileliminarpthisEstadoCivil = estadoCivileliminarpthisEstadoCivil;
    }

    @JsonIgnore
    public EstadoCivil getEstadoCivileliminarpthisEstadoCivilInstance() {
        return this.estadoCivileliminarpthisEstadoCivilInstance;
    }
    
    public void setEstadoCivileliminarpthisEstadoCivilInstance(EstadoCivil estadoCivileliminarpthisEstadoCivil) {
        this.estadoCivileliminarpthisEstadoCivilInstance = estadoCivileliminarpthisEstadoCivil;
        this.estadoCivileliminarpthisEstadoCivil = estadoCivileliminarpthisEstadoCivil != null ? estadoCivileliminarpthisEstadoCivil.getOid() : null;
    }

    private void checkEstadoCivileliminarpthisEstadoCivil() throws NotNullArgumentException {
        if(this.getEstadoCivileliminarpthisEstadoCivilInstance() == null || this.getEstadoCivileliminarpthisEstadoCivilInstance().isNull()) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_ELIMINAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_ELIMINAR_ESTADOCIVILELIMINARPTHISESTADOCIVIL, EstadoCivilConstants.SERV_ALIAS_ELIMINAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_ELIMINAR_ESTADOCIVILELIMINARPTHISESTADOCIVIL);
        }
    }
}
