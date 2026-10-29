package com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;

public class EliminarRequest {

    @JsonProperty("p_thisestadobeneficiario_oid")
    private EstadoBeneficiarioOid estadoBeneficiarioeliminarpthisEstadoBeneficiario;
    
    @JsonIgnore
    private EstadoBeneficiario estadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkEstadoBeneficiarioeliminarpthisEstadoBeneficiario();
    }

    @JsonIgnore
    public EstadoBeneficiarioOid getEstadoBeneficiarioeliminarpthisEstadoBeneficiario() {
        return this.estadoBeneficiarioeliminarpthisEstadoBeneficiario;
    }
    
    public void setEstadoBeneficiarioeliminarpthisEstadoBeneficiario(EstadoBeneficiarioOid estadoBeneficiarioeliminarpthisEstadoBeneficiario) {
        this.estadoBeneficiarioeliminarpthisEstadoBeneficiario = estadoBeneficiarioeliminarpthisEstadoBeneficiario;
    }

    @JsonIgnore
    public EstadoBeneficiario getEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance() {
        return this.estadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance;
    }
    
    public void setEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance(EstadoBeneficiario estadoBeneficiarioeliminarpthisEstadoBeneficiario) {
        this.estadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance = estadoBeneficiarioeliminarpthisEstadoBeneficiario;
        this.estadoBeneficiarioeliminarpthisEstadoBeneficiario = estadoBeneficiarioeliminarpthisEstadoBeneficiario != null ? estadoBeneficiarioeliminarpthisEstadoBeneficiario.getOid() : null;
    }

    private void checkEstadoBeneficiarioeliminarpthisEstadoBeneficiario() throws NotNullArgumentException {
        if(this.getEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance() == null || this.getEstadoBeneficiarioeliminarpthisEstadoBeneficiarioInstance().isNull()) {
            throw new NotNullArgumentException(EstadoBeneficiarioConstants.SERV_ID_ELIMINAR, EstadoBeneficiarioConstants.CLASS_ID, EstadoBeneficiarioConstants.ARG_ID_ELIMINAR_ESTADOBENEFICIARIOELIMINARPTHISESTADOBENEFICIARIO, EstadoBeneficiarioConstants.SERV_ALIAS_ELIMINAR, EstadoBeneficiarioConstants.CLASS_ALIAS, EstadoBeneficiarioConstants.ARG_ALIAS_ELIMINAR_ESTADOBENEFICIARIOELIMINARPTHISESTADOBENEFICIARIO);
        }
    }

    @JsonIgnore
    public String getPreconditionId() {
        return this.preconditionId;
    }
    
    public void setPreconditionId(String preconditionId) {
        this.preconditionId = preconditionId;
    }
}
