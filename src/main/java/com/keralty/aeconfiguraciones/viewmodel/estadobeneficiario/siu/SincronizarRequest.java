package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;

public class SincronizarRequest {

    @JsonProperty("p_thisestadobeneficiario_oid")
    private EstadoBeneficiarioOid estadoBeneficiariosincronizarpthisEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance;

    @JsonProperty("p_descripcion")
    private String estadoBeneficiariosincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiariosincronizarpthisEstadoBeneficiario();
        checkEstadoBeneficiariosincronizarpDescripcion();
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getEstadoBeneficiariosincronizarpthisEstadoBeneficiario() {
        return this.estadoBeneficiariosincronizarpthisEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiariosincronizarpthisEstadoBeneficiario(EstadoBeneficiarioOid estadoBeneficiariosincronizarpthisEstadoBeneficiario) {
        this.estadoBeneficiariosincronizarpthisEstadoBeneficiario = estadoBeneficiariosincronizarpthisEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance() {
        return this.estadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiariosincronizarpthisEstadoBeneficiario) {
        this.estadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance = estadoBeneficiariosincronizarpthisEstadoBeneficiario;
        this.estadoBeneficiariosincronizarpthisEstadoBeneficiario = estadoBeneficiariosincronizarpthisEstadoBeneficiario != null ? estadoBeneficiariosincronizarpthisEstadoBeneficiario.getOid() : null;
    }

    private void checkEstadoBeneficiariosincronizarpthisEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_SINCRONIZAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_SINCRONIZAR_ESTADOBENEFICIARIOSINCRONIZARPTHISESTADOBENEFICIARIO, EstadoBeneficiarioConstants.SERV_ALIAS_SINCRONIZAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_SINCRONIZAR_ESTADOBENEFICIARIOSINCRONIZARPTHISESTADOBENEFICIARIO);
        }
    }

    @JsonIgnore
    public String getEstadoBeneficiariosincronizarpDescripcion() {
        return this.estadoBeneficiariosincronizarpDescripcion;
    }
    
    public void setEstadoBeneficiariosincronizarpDescripcion(String estadoBeneficiariosincronizarpDescripcion) {
        this.estadoBeneficiariosincronizarpDescripcion = estadoBeneficiariosincronizarpDescripcion;
    }

    private void checkEstadoBeneficiariosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_SINCRONIZAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_SINCRONIZAR_ESTADOBENEFICIARIOSINCRONIZARPDESCRIPCION, EstadoBeneficiarioConstants.SERV_ALIAS_SINCRONIZAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_SINCRONIZAR_ESTADOBENEFICIARIOSINCRONIZARPDESCRIPCION);
        }
    }
}
