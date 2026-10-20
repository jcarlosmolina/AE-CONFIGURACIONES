package com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoCivilConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;

public class SincronizarRequest {

    @JsonProperty("p_thisestadocivil_oid")
    private EstadoCivilOid estadoCivilsincronizarpthisEstadoCivil;
    
    @JsonIgnore
    private EstadoCivil estadoCivilsincronizarpthisEstadoCivilInstance;

    @JsonProperty("p_descripcion")
    private String estadoCivilsincronizarpDescripcion;

    @JsonProperty("p_nombrecorto")
    private String estadoCivilsincronizarpNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoCivilsincronizarpthisEstadoCivil();
        checkEstadoCivilsincronizarpDescripcion();
        checkEstadoCivilsincronizarpNombreCorto();
    }

    @JsonIgnore
    public EstadoCivilOid getEstadoCivilsincronizarpthisEstadoCivil() {
        return this.estadoCivilsincronizarpthisEstadoCivil;
    }
    
    public void setEstadoCivilsincronizarpthisEstadoCivil(EstadoCivilOid estadoCivilsincronizarpthisEstadoCivil) {
        this.estadoCivilsincronizarpthisEstadoCivil = estadoCivilsincronizarpthisEstadoCivil;
    }

    @JsonIgnore
    public EstadoCivil getEstadoCivilsincronizarpthisEstadoCivilInstance() {
        return this.estadoCivilsincronizarpthisEstadoCivilInstance;
    }
    
    public void setEstadoCivilsincronizarpthisEstadoCivilInstance(EstadoCivil estadoCivilsincronizarpthisEstadoCivil) {
        this.estadoCivilsincronizarpthisEstadoCivilInstance = estadoCivilsincronizarpthisEstadoCivil;
        this.estadoCivilsincronizarpthisEstadoCivil = estadoCivilsincronizarpthisEstadoCivil != null ? estadoCivilsincronizarpthisEstadoCivil.getOid() : null;
    }

    private void checkEstadoCivilsincronizarpthisEstadoCivil() throws NotNullArgumentException {
        if(this.getEstadoCivilsincronizarpthisEstadoCivilInstance() == null || this.getEstadoCivilsincronizarpthisEstadoCivilInstance().isNull()) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_SINCRONIZAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_SINCRONIZAR_ESTADOCIVILSINCRONIZARPTHISESTADOCIVIL, EstadoCivilConstants.SERV_ALIAS_SINCRONIZAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_SINCRONIZAR_ESTADOCIVILSINCRONIZARPTHISESTADOCIVIL);
        }
    }

    @JsonIgnore
    public String getEstadoCivilsincronizarpDescripcion() {
        return this.estadoCivilsincronizarpDescripcion;
    }
    
    public void setEstadoCivilsincronizarpDescripcion(String estadoCivilsincronizarpDescripcion) {
        this.estadoCivilsincronizarpDescripcion = estadoCivilsincronizarpDescripcion;
    }

    private void checkEstadoCivilsincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoCivilsincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_SINCRONIZAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_SINCRONIZAR_ESTADOCIVILSINCRONIZARPDESCRIPCION, EstadoCivilConstants.SERV_ALIAS_SINCRONIZAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_SINCRONIZAR_ESTADOCIVILSINCRONIZARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getEstadoCivilsincronizarpNombreCorto() {
        return this.estadoCivilsincronizarpNombreCorto;
    }
    
    public void setEstadoCivilsincronizarpNombreCorto(String estadoCivilsincronizarpNombreCorto) {
        this.estadoCivilsincronizarpNombreCorto = estadoCivilsincronizarpNombreCorto;
    }

    private void checkEstadoCivilsincronizarpNombreCorto() throws NotNullArgumentException {
        if(this.getEstadoCivilsincronizarpNombreCorto() == null) {
            throw new NotNullArgumentException(EstadoCivilConstants.SERV_ID_SINCRONIZAR, EstadoCivilConstants.CLASS_ID, EstadoCivilConstants.ARG_ID_SINCRONIZAR_ESTADOCIVILSINCRONIZARPNOMBRECORTO, EstadoCivilConstants.SERV_ALIAS_SINCRONIZAR, EstadoCivilConstants.CLASS_ALIAS, EstadoCivilConstants.ARG_ALIAS_SINCRONIZAR_ESTADOCIVILSINCRONIZARPNOMBRECORTO);
        }
    }
}
