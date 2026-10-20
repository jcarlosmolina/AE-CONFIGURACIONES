package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;

public class TCREARRequest {

    @JsonProperty("pt_p_agrcuestionario_json")
    private String seccionTCREARptpagrCuestionario;
    
    @JsonIgnore
    private CuestionarioMed seccionTCREARptpagrCuestionarioInstance;

    @JsonProperty("pt_p_atrnombre")
    private String seccionTCREARptpatrNombre;

    @JsonProperty("pt_p_atrnumorden")
    private Long seccionTCREARptpatrNumOrden;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccionTCREARptpagrCuestionario();
        checkSeccionTCREARptpatrNombre();
        checkSeccionTCREARptpatrNumOrden();
    }

    @JsonIgnore
    public String getSeccionTCREARptpagrCuestionario() {
        return this.seccionTCREARptpagrCuestionario;
    }
    
    public void setSeccionTCREARptpagrCuestionario(String seccionTCREARptpagrCuestionario) {
        this.seccionTCREARptpagrCuestionario = seccionTCREARptpagrCuestionario;
    }

    @JsonIgnore
    public CuestionarioMed getSeccionTCREARptpagrCuestionarioInstance() {
        return this.seccionTCREARptpagrCuestionarioInstance;
    }
    
    public void setSeccionTCREARptpagrCuestionarioInstance(CuestionarioMed seccionTCREARptpagrCuestionario) {
        this.seccionTCREARptpagrCuestionarioInstance = seccionTCREARptpagrCuestionario;
        this.seccionTCREARptpagrCuestionario = seccionTCREARptpagrCuestionario != null ? seccionTCREARptpagrCuestionario.getOid().toJson() : "";
    }

    private void checkSeccionTCREARptpagrCuestionario() throws NotNullArgumentException {
        if(this.getSeccionTCREARptpagrCuestionarioInstance() == null || this.getSeccionTCREARptpagrCuestionarioInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCREAR_SECCIONTCREARPTPAGRCUESTIONARIO, SeccionConstants.SERV_ALIAS_TCREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCREAR_SECCIONTCREARPTPAGRCUESTIONARIO);
        }
    }

    @JsonIgnore
    public String getSeccionTCREARptpatrNombre() {
        return this.seccionTCREARptpatrNombre;
    }
    
    public void setSeccionTCREARptpatrNombre(String seccionTCREARptpatrNombre) {
        this.seccionTCREARptpatrNombre = seccionTCREARptpatrNombre;
    }

    private void checkSeccionTCREARptpatrNombre() throws NotNullArgumentException {
        if(this.getSeccionTCREARptpatrNombre() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCREAR_SECCIONTCREARPTPATRNOMBRE, SeccionConstants.SERV_ALIAS_TCREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCREAR_SECCIONTCREARPTPATRNOMBRE);
        }
    }

    @JsonIgnore
    public Long getSeccionTCREARptpatrNumOrden() {
        return this.seccionTCREARptpatrNumOrden;
    }
    
    public void setSeccionTCREARptpatrNumOrden(Long seccionTCREARptpatrNumOrden) {
        this.seccionTCREARptpatrNumOrden = seccionTCREARptpatrNumOrden;
    }

    private void checkSeccionTCREARptpatrNumOrden() throws NotNullArgumentException {
        if(this.getSeccionTCREARptpatrNumOrden() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_TCREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_TCREAR_SECCIONTCREARPTPATRNUMORDEN, SeccionConstants.SERV_ALIAS_TCREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_TCREAR_SECCIONTCREARPTPATRNUMORDEN);
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
