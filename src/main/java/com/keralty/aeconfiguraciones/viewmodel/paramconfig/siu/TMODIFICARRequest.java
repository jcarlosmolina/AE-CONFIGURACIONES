package com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamConfigConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamConfigOid;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;

public class TMODIFICARRequest {

    @JsonProperty("p_thisparamconfig_oid")
    private ParamConfigOid paramConfigTMODIFICARpthisParamConfig;
    
    @JsonIgnore
    private ParamConfig paramConfigTMODIFICARpthisParamConfigInstance;

    @JsonProperty("pt_p_valor")
    private String paramConfigTMODIFICARptpValor;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkParamConfigTMODIFICARpthisParamConfig();
        checkParamConfigTMODIFICARptpValor();
    }

    @JsonIgnore
    public ParamConfigOid getParamConfigTMODIFICARpthisParamConfig() {
        return this.paramConfigTMODIFICARpthisParamConfig;
    }
    
    public void setParamConfigTMODIFICARpthisParamConfig(ParamConfigOid paramConfigTMODIFICARpthisParamConfig) {
        this.paramConfigTMODIFICARpthisParamConfig = paramConfigTMODIFICARpthisParamConfig;
    }

    @JsonIgnore
    public ParamConfig getParamConfigTMODIFICARpthisParamConfigInstance() {
        return this.paramConfigTMODIFICARpthisParamConfigInstance;
    }
    
    public void setParamConfigTMODIFICARpthisParamConfigInstance(ParamConfig paramConfigTMODIFICARpthisParamConfig) {
        this.paramConfigTMODIFICARpthisParamConfigInstance = paramConfigTMODIFICARpthisParamConfig;
        this.paramConfigTMODIFICARpthisParamConfig = paramConfigTMODIFICARpthisParamConfig != null ? paramConfigTMODIFICARpthisParamConfig.getOid() : null;
    }

    private void checkParamConfigTMODIFICARpthisParamConfig() throws NotNullArgumentException {
        if(this.getParamConfigTMODIFICARpthisParamConfigInstance() == null || this.getParamConfigTMODIFICARpthisParamConfigInstance().isNull()) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_TMODIFICAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_TMODIFICAR_PARAMCONFIGTMODIFICARPTHISPARAMCONFIG, ParamConfigConstants.SERV_ALIAS_TMODIFICAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_TMODIFICAR_PARAMCONFIGTMODIFICARPTHISPARAMCONFIG);
        }
    }

    @JsonIgnore
    public String getParamConfigTMODIFICARptpValor() {
        return this.paramConfigTMODIFICARptpValor;
    }
    
    public void setParamConfigTMODIFICARptpValor(String paramConfigTMODIFICARptpValor) {
        this.paramConfigTMODIFICARptpValor = paramConfigTMODIFICARptpValor;
    }

    private void checkParamConfigTMODIFICARptpValor() throws NotNullArgumentException {
        if(this.getParamConfigTMODIFICARptpValor() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_TMODIFICAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_TMODIFICAR_PARAMCONFIGTMODIFICARPTPVALOR, ParamConfigConstants.SERV_ALIAS_TMODIFICAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_TMODIFICAR_PARAMCONFIGTMODIFICARPTPVALOR);
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
