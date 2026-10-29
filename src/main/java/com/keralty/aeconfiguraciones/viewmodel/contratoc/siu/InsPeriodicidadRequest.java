package com.keralty.aeconfiguraciones.viewmodel.contratoc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ContratoCConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;

public class InsPeriodicidadRequest {

    @JsonProperty("p_thiscontratoc_oid")
    private ContratoCOid contratoCInsPeriodicidadpthisContratoC;
    
    @JsonIgnore
    private ContratoC contratoCInsPeriodicidadpthisContratoCInstance;

    @JsonProperty("p_evcperiodicidad_oid")
    private PeriodicidadOid contratoCInsPeriodicidadpevcPeriodicidad;
    
    @JsonIgnore
    private Periodicidad contratoCInsPeriodicidadpevcPeriodicidadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkContratoCInsPeriodicidadpthisContratoC();
        checkContratoCInsPeriodicidadpevcPeriodicidad();
    }

    @JsonIgnore
    public ContratoCOid getContratoCInsPeriodicidadpthisContratoC() {
        return this.contratoCInsPeriodicidadpthisContratoC;
    }
    
    public void setContratoCInsPeriodicidadpthisContratoC(ContratoCOid contratoCInsPeriodicidadpthisContratoC) {
        this.contratoCInsPeriodicidadpthisContratoC = contratoCInsPeriodicidadpthisContratoC;
    }

    @JsonIgnore
    public ContratoC getContratoCInsPeriodicidadpthisContratoCInstance() {
        return this.contratoCInsPeriodicidadpthisContratoCInstance;
    }
    
    public void setContratoCInsPeriodicidadpthisContratoCInstance(ContratoC contratoCInsPeriodicidadpthisContratoC) {
        this.contratoCInsPeriodicidadpthisContratoCInstance = contratoCInsPeriodicidadpthisContratoC;
        this.contratoCInsPeriodicidadpthisContratoC = contratoCInsPeriodicidadpthisContratoC != null ? contratoCInsPeriodicidadpthisContratoC.getOid() : null;
    }

    private void checkContratoCInsPeriodicidadpthisContratoC() throws NotNullArgumentException {
        if(this.getContratoCInsPeriodicidadpthisContratoCInstance() == null || this.getContratoCInsPeriodicidadpthisContratoCInstance().isNull()) {
            throw new NotNullArgumentException(ContratoCConstants.SERV_ID_INSPERIODICIDAD, ContratoCConstants.CLASS_ID, ContratoCConstants.ARG_ID_INSPERIODICIDAD_CONTRATOCINSPERIODICIDADPTHISCONTRATOC, ContratoCConstants.SERV_ALIAS_INSPERIODICIDAD, ContratoCConstants.CLASS_ALIAS, ContratoCConstants.ARG_ALIAS_INSPERIODICIDAD_CONTRATOCINSPERIODICIDADPTHISCONTRATOC);
        }
    }

    @JsonIgnore
    public PeriodicidadOid getContratoCInsPeriodicidadpevcPeriodicidad() {
        return this.contratoCInsPeriodicidadpevcPeriodicidad;
    }
    
    public void setContratoCInsPeriodicidadpevcPeriodicidad(PeriodicidadOid contratoCInsPeriodicidadpevcPeriodicidad) {
        this.contratoCInsPeriodicidadpevcPeriodicidad = contratoCInsPeriodicidadpevcPeriodicidad;
    }

    @JsonIgnore
    public Periodicidad getContratoCInsPeriodicidadpevcPeriodicidadInstance() {
        return this.contratoCInsPeriodicidadpevcPeriodicidadInstance;
    }
    
    public void setContratoCInsPeriodicidadpevcPeriodicidadInstance(Periodicidad contratoCInsPeriodicidadpevcPeriodicidad) {
        this.contratoCInsPeriodicidadpevcPeriodicidadInstance = contratoCInsPeriodicidadpevcPeriodicidad;
        this.contratoCInsPeriodicidadpevcPeriodicidad = contratoCInsPeriodicidadpevcPeriodicidad != null ? contratoCInsPeriodicidadpevcPeriodicidad.getOid() : null;
    }

    private void checkContratoCInsPeriodicidadpevcPeriodicidad() throws NotNullArgumentException {
        if(this.getContratoCInsPeriodicidadpevcPeriodicidadInstance() == null || this.getContratoCInsPeriodicidadpevcPeriodicidadInstance().isNull()) {
            throw new NotNullArgumentException(ContratoCConstants.SERV_ID_INSPERIODICIDAD, ContratoCConstants.CLASS_ID, ContratoCConstants.ARG_ID_INSPERIODICIDAD_CONTRATOCINSPERIODICIDADPEVCPERIODICIDAD, ContratoCConstants.SERV_ALIAS_INSPERIODICIDAD, ContratoCConstants.CLASS_ALIAS, ContratoCConstants.ARG_ALIAS_INSPERIODICIDAD_CONTRATOCINSPERIODICIDADPEVCPERIODICIDAD);
        }
    }
}
