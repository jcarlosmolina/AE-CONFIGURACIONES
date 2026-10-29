package com.keralty.aeconfiguraciones.viewmodel.seccion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.SeccionConstants;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioMedOid;

public class CrearRequest {

    @JsonProperty("p_agrcuestionario_oid")
    private CuestionarioMedOid seccioncrearpagrCuestionario;
    
    @JsonIgnore
    private CuestionarioMed seccioncrearpagrCuestionarioInstance;

    @JsonProperty("p_atrnombre")
    private String seccioncrearpatrNombre;

    @JsonProperty("p_atrnumorden")
    private Long seccioncrearpatrNumOrden;

    public void checkArguments() throws NotNullArgumentException {
        checkSeccioncrearpagrCuestionario();
        checkSeccioncrearpatrNombre();
        checkSeccioncrearpatrNumOrden();
    }

    @JsonIgnore
    public CuestionarioMedOid getSeccioncrearpagrCuestionario() {
        return this.seccioncrearpagrCuestionario;
    }
    
    public void setSeccioncrearpagrCuestionario(CuestionarioMedOid seccioncrearpagrCuestionario) {
        this.seccioncrearpagrCuestionario = seccioncrearpagrCuestionario;
    }

    @JsonIgnore
    public CuestionarioMed getSeccioncrearpagrCuestionarioInstance() {
        return this.seccioncrearpagrCuestionarioInstance;
    }
    
    public void setSeccioncrearpagrCuestionarioInstance(CuestionarioMed seccioncrearpagrCuestionario) {
        this.seccioncrearpagrCuestionarioInstance = seccioncrearpagrCuestionario;
        this.seccioncrearpagrCuestionario = seccioncrearpagrCuestionario != null ? seccioncrearpagrCuestionario.getOid() : null;
    }

    private void checkSeccioncrearpagrCuestionario() throws NotNullArgumentException {
        if(this.getSeccioncrearpagrCuestionarioInstance() == null || this.getSeccioncrearpagrCuestionarioInstance().isNull()) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CREAR_SECCIONCREARPAGRCUESTIONARIO, SeccionConstants.SERV_ALIAS_CREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CREAR_SECCIONCREARPAGRCUESTIONARIO);
        }
    }

    @JsonIgnore
    public String getSeccioncrearpatrNombre() {
        return this.seccioncrearpatrNombre;
    }
    
    public void setSeccioncrearpatrNombre(String seccioncrearpatrNombre) {
        this.seccioncrearpatrNombre = seccioncrearpatrNombre;
    }

    private void checkSeccioncrearpatrNombre() throws NotNullArgumentException {
        if(this.getSeccioncrearpatrNombre() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CREAR_SECCIONCREARPATRNOMBRE, SeccionConstants.SERV_ALIAS_CREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CREAR_SECCIONCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public Long getSeccioncrearpatrNumOrden() {
        return this.seccioncrearpatrNumOrden;
    }
    
    public void setSeccioncrearpatrNumOrden(Long seccioncrearpatrNumOrden) {
        this.seccioncrearpatrNumOrden = seccioncrearpatrNumOrden;
    }

    private void checkSeccioncrearpatrNumOrden() throws NotNullArgumentException {
        if(this.getSeccioncrearpatrNumOrden() == null) {
            throw new NotNullArgumentException(SeccionConstants.SERV_ID_CREAR, SeccionConstants.CLASS_ID, SeccionConstants.ARG_ID_CREAR_SECCIONCREARPATRNUMORDEN, SeccionConstants.SERV_ALIAS_CREAR, SeccionConstants.CLASS_ALIAS, SeccionConstants.ARG_ALIAS_CREAR_SECCIONCREARPATRNUMORDEN);
        }
    }
}
