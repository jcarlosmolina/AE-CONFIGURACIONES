package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class EliminarRequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioeliminarpthisDominio;
    
    @JsonIgnore
    private Dominio dominioeliminarpthisDominioInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioeliminarpthisDominio();
    }

    @JsonIgnore
    public DominioOid getDominioeliminarpthisDominio() {
        return this.dominioeliminarpthisDominio;
    }
    
    public void setDominioeliminarpthisDominio(DominioOid dominioeliminarpthisDominio) {
        this.dominioeliminarpthisDominio = dominioeliminarpthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioeliminarpthisDominioInstance() {
        return this.dominioeliminarpthisDominioInstance;
    }
    
    public void setDominioeliminarpthisDominioInstance(Dominio dominioeliminarpthisDominio) {
        this.dominioeliminarpthisDominioInstance = dominioeliminarpthisDominio;
        this.dominioeliminarpthisDominio = dominioeliminarpthisDominio != null ? dominioeliminarpthisDominio.getOid() : null;
    }

    private void checkDominioeliminarpthisDominio() throws NotNullArgumentException {
        if(this.getDominioeliminarpthisDominioInstance() == null || this.getDominioeliminarpthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_ELIMINAR, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_ELIMINAR_DOMINIOELIMINARPTHISDOMINIO, DominioConstants.SERV_ALIAS_ELIMINAR, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_ELIMINAR_DOMINIOELIMINARPTHISDOMINIO);
        }
    }
}
