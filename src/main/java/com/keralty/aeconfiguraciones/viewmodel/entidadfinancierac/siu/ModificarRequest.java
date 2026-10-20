package com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EntidadFinancieraCConstants;
import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.persistence.oid.EntidadFinancieraCOid;

public class ModificarRequest {

    @JsonProperty("p_thisentidadfinancierac_oid")
    private EntidadFinancieraCOid entidadFinancieraCmodificarpthisEntidadFinancieraC;
    
    @JsonIgnore
    private EntidadFinancieraC entidadFinancieraCmodificarpthisEntidadFinancieraCInstance;

    @JsonProperty("p_descripcion")
    private String entidadFinancieraCmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkEntidadFinancieraCmodificarpthisEntidadFinancieraC();
        checkEntidadFinancieraCmodificarpDescripcion();
    }

    @JsonIgnore
    public EntidadFinancieraCOid getEntidadFinancieraCmodificarpthisEntidadFinancieraC() {
        return this.entidadFinancieraCmodificarpthisEntidadFinancieraC;
    }
    
    public void setEntidadFinancieraCmodificarpthisEntidadFinancieraC(EntidadFinancieraCOid entidadFinancieraCmodificarpthisEntidadFinancieraC) {
        this.entidadFinancieraCmodificarpthisEntidadFinancieraC = entidadFinancieraCmodificarpthisEntidadFinancieraC;
    }

    @JsonIgnore
    public EntidadFinancieraC getEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance() {
        return this.entidadFinancieraCmodificarpthisEntidadFinancieraCInstance;
    }
    
    public void setEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance(EntidadFinancieraC entidadFinancieraCmodificarpthisEntidadFinancieraC) {
        this.entidadFinancieraCmodificarpthisEntidadFinancieraCInstance = entidadFinancieraCmodificarpthisEntidadFinancieraC;
        this.entidadFinancieraCmodificarpthisEntidadFinancieraC = entidadFinancieraCmodificarpthisEntidadFinancieraC != null ? entidadFinancieraCmodificarpthisEntidadFinancieraC.getOid() : null;
    }

    private void checkEntidadFinancieraCmodificarpthisEntidadFinancieraC() throws NotNullArgumentException {
        if(this.getEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance() == null || this.getEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance().isNull()) {
            throw new NotNullArgumentException(EntidadFinancieraCConstants.SERV_ID_MODIFICAR, EntidadFinancieraCConstants.CLASS_ID, EntidadFinancieraCConstants.ARG_ID_MODIFICAR_ENTIDADFINANCIERACMODIFICARPTHISENTIDADFINANCIERAC, EntidadFinancieraCConstants.SERV_ALIAS_MODIFICAR, EntidadFinancieraCConstants.CLASS_ALIAS, EntidadFinancieraCConstants.ARG_ALIAS_MODIFICAR_ENTIDADFINANCIERACMODIFICARPTHISENTIDADFINANCIERAC);
        }
    }

    @JsonIgnore
    public String getEntidadFinancieraCmodificarpDescripcion() {
        return this.entidadFinancieraCmodificarpDescripcion;
    }
    
    public void setEntidadFinancieraCmodificarpDescripcion(String entidadFinancieraCmodificarpDescripcion) {
        this.entidadFinancieraCmodificarpDescripcion = entidadFinancieraCmodificarpDescripcion;
    }

    private void checkEntidadFinancieraCmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getEntidadFinancieraCmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(EntidadFinancieraCConstants.SERV_ID_MODIFICAR, EntidadFinancieraCConstants.CLASS_ID, EntidadFinancieraCConstants.ARG_ID_MODIFICAR_ENTIDADFINANCIERACMODIFICARPDESCRIPCION, EntidadFinancieraCConstants.SERV_ALIAS_MODIFICAR, EntidadFinancieraCConstants.CLASS_ALIAS, EntidadFinancieraCConstants.ARG_ALIAS_MODIFICAR_ENTIDADFINANCIERACMODIFICARPDESCRIPCION);
        }
    }
}
