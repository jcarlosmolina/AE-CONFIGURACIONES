package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class CambiarDiasVigenciaRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominiocambiarDiasVigenciapthisDominio;
    
    @JsonIgnore
    private Dominio dominiocambiarDiasVigenciapthisDominioInstance;

    @JsonProperty("pinivigencia")
    private Long dominiocambiarDiasVigenciapIniVigencia;

    @JsonProperty("pfinvigencia")
    private Long dominiocambiarDiasVigenciapFinVigencia;

    @JsonProperty("pcaducidad")
    private Long dominiocambiarDiasVigenciapCaducidad;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkDominiocambiarDiasVigenciapthisDominio();
        checkDominiocambiarDiasVigenciapIniVigencia();
        checkDominiocambiarDiasVigenciapFinVigencia();
        checkDominiocambiarDiasVigenciapCaducidad();
    }

    @JsonIgnore
    public DominioOid getDominiocambiarDiasVigenciapthisDominio() {
        return this.dominiocambiarDiasVigenciapthisDominio;
    }
    
    public void setDominiocambiarDiasVigenciapthisDominio(DominioOid dominiocambiarDiasVigenciapthisDominio) {
        this.dominiocambiarDiasVigenciapthisDominio = dominiocambiarDiasVigenciapthisDominio;
    }

    @JsonIgnore
    public Dominio getDominiocambiarDiasVigenciapthisDominioInstance() {
        return this.dominiocambiarDiasVigenciapthisDominioInstance;
    }
    
    public void setDominiocambiarDiasVigenciapthisDominioInstance(Dominio dominiocambiarDiasVigenciapthisDominio) {
        this.dominiocambiarDiasVigenciapthisDominioInstance = dominiocambiarDiasVigenciapthisDominio;
        this.dominiocambiarDiasVigenciapthisDominio = dominiocambiarDiasVigenciapthisDominio != null ? dominiocambiarDiasVigenciapthisDominio.getOid() : null;
    }

    private void checkDominiocambiarDiasVigenciapthisDominio() throws NotNullArgumentException {
        if(this.getDominiocambiarDiasVigenciapthisDominioInstance() == null || this.getDominiocambiarDiasVigenciapthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPTHISDOMINIO, DominioConstants.SERV_ALIAS_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public Long getDominiocambiarDiasVigenciapIniVigencia() {
        return this.dominiocambiarDiasVigenciapIniVigencia;
    }
    
    public void setDominiocambiarDiasVigenciapIniVigencia(Long dominiocambiarDiasVigenciapIniVigencia) {
        this.dominiocambiarDiasVigenciapIniVigencia = dominiocambiarDiasVigenciapIniVigencia;
    }

    private void checkDominiocambiarDiasVigenciapIniVigencia() throws NotNullArgumentException {
        if(this.getDominiocambiarDiasVigenciapIniVigencia() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPINIVIGENCIA, DominioConstants.SERV_ALIAS_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getDominiocambiarDiasVigenciapFinVigencia() {
        return this.dominiocambiarDiasVigenciapFinVigencia;
    }
    
    public void setDominiocambiarDiasVigenciapFinVigencia(Long dominiocambiarDiasVigenciapFinVigencia) {
        this.dominiocambiarDiasVigenciapFinVigencia = dominiocambiarDiasVigenciapFinVigencia;
    }

    private void checkDominiocambiarDiasVigenciapFinVigencia() throws NotNullArgumentException {
        if(this.getDominiocambiarDiasVigenciapFinVigencia() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPFINVIGENCIA, DominioConstants.SERV_ALIAS_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getDominiocambiarDiasVigenciapCaducidad() {
        return this.dominiocambiarDiasVigenciapCaducidad;
    }
    
    public void setDominiocambiarDiasVigenciapCaducidad(Long dominiocambiarDiasVigenciapCaducidad) {
        this.dominiocambiarDiasVigenciapCaducidad = dominiocambiarDiasVigenciapCaducidad;
    }

    private void checkDominiocambiarDiasVigenciapCaducidad() throws NotNullArgumentException {
        if(this.getDominiocambiarDiasVigenciapCaducidad() == null) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPCADUCIDAD, DominioConstants.SERV_ALIAS_CAMBIARDIASVIGENCIA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_CAMBIARDIASVIGENCIA_DOMINIOCAMBIARDIASVIGENCIAPCADUCIDAD);
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
