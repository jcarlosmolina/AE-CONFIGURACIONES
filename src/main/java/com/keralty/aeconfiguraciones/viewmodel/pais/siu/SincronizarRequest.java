package com.keralty.aeconfiguraciones.viewmodel.pais.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PaisConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.Pais;

public class SincronizarRequest {

    @JsonProperty("p_thispais_oid")
    private PaisOid paissincronizarpthisPais;
    
    @JsonIgnore
    private Pais paissincronizarpthisPaisInstance;

    @JsonProperty("p_descripcion")
    private String paissincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkPaissincronizarpthisPais();
    }

    @JsonIgnore
    public PaisOid getPaissincronizarpthisPais() {
        return this.paissincronizarpthisPais;
    }
    
    public void setPaissincronizarpthisPais(PaisOid paissincronizarpthisPais) {
        this.paissincronizarpthisPais = paissincronizarpthisPais;
    }

    @JsonIgnore
    public Pais getPaissincronizarpthisPaisInstance() {
        return this.paissincronizarpthisPaisInstance;
    }
    
    public void setPaissincronizarpthisPaisInstance(Pais paissincronizarpthisPais) {
        this.paissincronizarpthisPaisInstance = paissincronizarpthisPais;
        this.paissincronizarpthisPais = paissincronizarpthisPais != null ? paissincronizarpthisPais.getOid() : null;
    }

    private void checkPaissincronizarpthisPais() throws NotNullArgumentException {
        if(this.getPaissincronizarpthisPaisInstance() == null || this.getPaissincronizarpthisPaisInstance().isNull()) {
            throw new NotNullArgumentException(PaisConstants.SERV_ID_SINCRONIZAR, PaisConstants.CLASS_ID, PaisConstants.ARG_ID_SINCRONIZAR_PAISSINCRONIZARPTHISPAIS, PaisConstants.SERV_ALIAS_SINCRONIZAR, PaisConstants.CLASS_ALIAS, PaisConstants.ARG_ALIAS_SINCRONIZAR_PAISSINCRONIZARPTHISPAIS);
        }
    }

    @JsonIgnore
    public String getPaissincronizarpDescripcion() {
        return this.paissincronizarpDescripcion;
    }
    
    public void setPaissincronizarpDescripcion(String paissincronizarpDescripcion) {
        this.paissincronizarpDescripcion = paissincronizarpDescripcion;
    }
}
