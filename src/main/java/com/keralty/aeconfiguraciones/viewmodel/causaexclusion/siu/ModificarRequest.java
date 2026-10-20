package com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CausaExclusionConstants;
import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
import com.keralty.aeconfiguraciones.persistence.oid.CausaExclusionOid;

public class ModificarRequest {

    @JsonProperty("p_thiscausaexclusion_oid")
    private CausaExclusionOid causaExclusionmodificarpthisCausaExclusion;
    
    @JsonIgnore
    private CausaExclusion causaExclusionmodificarpthisCausaExclusionInstance;

    @JsonProperty("p_aliascausa")
    private String causaExclusionmodificarpAliasCausa;

    @JsonProperty("p_descripcion")
    private String causaExclusionmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkCausaExclusionmodificarpthisCausaExclusion();
        checkCausaExclusionmodificarpAliasCausa();
        checkCausaExclusionmodificarpDescripcion();
    }

    @JsonIgnore
    public CausaExclusionOid getCausaExclusionmodificarpthisCausaExclusion() {
        return this.causaExclusionmodificarpthisCausaExclusion;
    }
    
    public void setCausaExclusionmodificarpthisCausaExclusion(CausaExclusionOid causaExclusionmodificarpthisCausaExclusion) {
        this.causaExclusionmodificarpthisCausaExclusion = causaExclusionmodificarpthisCausaExclusion;
    }

    @JsonIgnore
    public CausaExclusion getCausaExclusionmodificarpthisCausaExclusionInstance() {
        return this.causaExclusionmodificarpthisCausaExclusionInstance;
    }
    
    public void setCausaExclusionmodificarpthisCausaExclusionInstance(CausaExclusion causaExclusionmodificarpthisCausaExclusion) {
        this.causaExclusionmodificarpthisCausaExclusionInstance = causaExclusionmodificarpthisCausaExclusion;
        this.causaExclusionmodificarpthisCausaExclusion = causaExclusionmodificarpthisCausaExclusion != null ? causaExclusionmodificarpthisCausaExclusion.getOid() : null;
    }

    private void checkCausaExclusionmodificarpthisCausaExclusion() throws NotNullArgumentException {
        if(this.getCausaExclusionmodificarpthisCausaExclusionInstance() == null || this.getCausaExclusionmodificarpthisCausaExclusionInstance().isNull()) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_MODIFICAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_MODIFICAR_CAUSAEXCLUSIONMODIFICARPTHISCAUSAEXCLUSION, CausaExclusionConstants.SERV_ALIAS_MODIFICAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_MODIFICAR_CAUSAEXCLUSIONMODIFICARPTHISCAUSAEXCLUSION);
        }
    }

    @JsonIgnore
    public String getCausaExclusionmodificarpAliasCausa() {
        return this.causaExclusionmodificarpAliasCausa;
    }
    
    public void setCausaExclusionmodificarpAliasCausa(String causaExclusionmodificarpAliasCausa) {
        this.causaExclusionmodificarpAliasCausa = causaExclusionmodificarpAliasCausa;
    }

    private void checkCausaExclusionmodificarpAliasCausa() throws NotNullArgumentException {
        if(this.getCausaExclusionmodificarpAliasCausa() == null) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_MODIFICAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_MODIFICAR_CAUSAEXCLUSIONMODIFICARPALIASCAUSA, CausaExclusionConstants.SERV_ALIAS_MODIFICAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_MODIFICAR_CAUSAEXCLUSIONMODIFICARPALIASCAUSA);
        }
    }

    @JsonIgnore
    public String getCausaExclusionmodificarpDescripcion() {
        return this.causaExclusionmodificarpDescripcion;
    }
    
    public void setCausaExclusionmodificarpDescripcion(String causaExclusionmodificarpDescripcion) {
        this.causaExclusionmodificarpDescripcion = causaExclusionmodificarpDescripcion;
    }

    private void checkCausaExclusionmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getCausaExclusionmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_MODIFICAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_MODIFICAR_CAUSAEXCLUSIONMODIFICARPDESCRIPCION, CausaExclusionConstants.SERV_ALIAS_MODIFICAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_MODIFICAR_CAUSAEXCLUSIONMODIFICARPDESCRIPCION);
        }
    }
}
