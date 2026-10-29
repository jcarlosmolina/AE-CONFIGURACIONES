package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;

public class ModificarRequest {

    @JsonProperty("p_thisestadobeneficiario_oid")
    private EstadoBeneficiarioOid estadoBeneficiariomodificarpthisEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiariomodificarpthisEstadoBeneficiarioInstance;

    @JsonProperty("p_esactivo")
    private Boolean estadoBeneficiariomodificarpEsActivo;

    @JsonProperty("p_confirmado")
    private Boolean estadoBeneficiariomodificarpConfirmado;

    @JsonProperty("p_eserror")
    private Boolean estadoBeneficiariomodificarpEsError;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiariomodificarpthisEstadoBeneficiario();
        checkEstadoBeneficiariomodificarpEsActivo();
        checkEstadoBeneficiariomodificarpConfirmado();
        checkEstadoBeneficiariomodificarpEsError();
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getEstadoBeneficiariomodificarpthisEstadoBeneficiario() {
        return this.estadoBeneficiariomodificarpthisEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiariomodificarpthisEstadoBeneficiario(EstadoBeneficiarioOid estadoBeneficiariomodificarpthisEstadoBeneficiario) {
        this.estadoBeneficiariomodificarpthisEstadoBeneficiario = estadoBeneficiariomodificarpthisEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance() {
        return this.estadoBeneficiariomodificarpthisEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiariomodificarpthisEstadoBeneficiario) {
        this.estadoBeneficiariomodificarpthisEstadoBeneficiarioInstance = estadoBeneficiariomodificarpthisEstadoBeneficiario;
        this.estadoBeneficiariomodificarpthisEstadoBeneficiario = estadoBeneficiariomodificarpthisEstadoBeneficiario != null ? estadoBeneficiariomodificarpthisEstadoBeneficiario.getOid() : null;
    }

    private void checkEstadoBeneficiariomodificarpthisEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiariomodificarpthisEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPTHISESTADOBENEFICIARIO, EstadoBeneficiarioConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPTHISESTADOBENEFICIARIO);
        }
    }

    @JsonIgnore
    public Boolean getEstadoBeneficiariomodificarpEsActivo() {
        return this.estadoBeneficiariomodificarpEsActivo;
    }
    
    public void setEstadoBeneficiariomodificarpEsActivo(Boolean estadoBeneficiariomodificarpEsActivo) {
        this.estadoBeneficiariomodificarpEsActivo = estadoBeneficiariomodificarpEsActivo;
    }

    private void checkEstadoBeneficiariomodificarpEsActivo() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariomodificarpEsActivo() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPESACTIVO, EstadoBeneficiarioConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPESACTIVO);
        }
    }

    @JsonIgnore
    public Boolean getEstadoBeneficiariomodificarpConfirmado() {
        return this.estadoBeneficiariomodificarpConfirmado;
    }
    
    public void setEstadoBeneficiariomodificarpConfirmado(Boolean estadoBeneficiariomodificarpConfirmado) {
        this.estadoBeneficiariomodificarpConfirmado = estadoBeneficiariomodificarpConfirmado;
    }

    private void checkEstadoBeneficiariomodificarpConfirmado() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariomodificarpConfirmado() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPCONFIRMADO, EstadoBeneficiarioConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPCONFIRMADO);
        }
    }

    @JsonIgnore
    public Boolean getEstadoBeneficiariomodificarpEsError() {
        return this.estadoBeneficiariomodificarpEsError;
    }
    
    public void setEstadoBeneficiariomodificarpEsError(Boolean estadoBeneficiariomodificarpEsError) {
        this.estadoBeneficiariomodificarpEsError = estadoBeneficiariomodificarpEsError;
    }

    private void checkEstadoBeneficiariomodificarpEsError() throws NotNullArgumentException {
        if(this.getEstadoBeneficiariomodificarpEsError() == null) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPESERROR, EstadoBeneficiarioConstants.SERV_ALIAS_MODIFICAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_MODIFICAR_ESTADOBENEFICIARIOMODIFICARPESERROR);
        }
    }
}
