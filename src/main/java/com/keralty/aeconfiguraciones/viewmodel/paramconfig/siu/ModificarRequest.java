package com.keralty.aeconfiguraciones.viewmodel.paramconfig.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParamConfigConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParamConfigOid;
import com.keralty.aeconfiguraciones.persistence.ParamConfig;

public class ModificarRequest {

    @JsonProperty("p_thisparamconfig_oid")
    private ParamConfigOid paramConfigmodificarpthisParamConfig;
    
    @JsonIgnore
    private ParamConfig paramConfigmodificarpthisParamConfigInstance;

    @JsonProperty("p_valor")
    private String paramConfigmodificarpValor;

    public void checkArguments() throws NotNullArgumentException {
        checkParamConfigmodificarpthisParamConfig();
        checkParamConfigmodificarpValor();
    }

    @JsonIgnore
    public ParamConfigOid getParamConfigmodificarpthisParamConfig() {
        return this.paramConfigmodificarpthisParamConfig;
    }
    
    public void setParamConfigmodificarpthisParamConfig(ParamConfigOid paramConfigmodificarpthisParamConfig) {
        this.paramConfigmodificarpthisParamConfig = paramConfigmodificarpthisParamConfig;
    }

    @JsonIgnore
    public ParamConfig getParamConfigmodificarpthisParamConfigInstance() {
        return this.paramConfigmodificarpthisParamConfigInstance;
    }
    
    public void setParamConfigmodificarpthisParamConfigInstance(ParamConfig paramConfigmodificarpthisParamConfig) {
        this.paramConfigmodificarpthisParamConfigInstance = paramConfigmodificarpthisParamConfig;
        this.paramConfigmodificarpthisParamConfig = paramConfigmodificarpthisParamConfig != null ? paramConfigmodificarpthisParamConfig.getOid() : null;
    }

    private void checkParamConfigmodificarpthisParamConfig() throws NotNullArgumentException {
        if(this.getParamConfigmodificarpthisParamConfigInstance() == null || this.getParamConfigmodificarpthisParamConfigInstance().isNull()) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_MODIFICAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_MODIFICAR_PARAMCONFIGMODIFICARPTHISPARAMCONFIG, ParamConfigConstants.SERV_ALIAS_MODIFICAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_MODIFICAR_PARAMCONFIGMODIFICARPTHISPARAMCONFIG);
        }
    }

    @JsonIgnore
    public String getParamConfigmodificarpValor() {
        return this.paramConfigmodificarpValor;
    }
    
    public void setParamConfigmodificarpValor(String paramConfigmodificarpValor) {
        this.paramConfigmodificarpValor = paramConfigmodificarpValor;
    }

    private void checkParamConfigmodificarpValor() throws NotNullArgumentException {
        if(this.getParamConfigmodificarpValor() == null) {
            throw new NotNullArgumentException(ParamConfigConstants.SERV_ID_MODIFICAR, ParamConfigConstants.CLASS_ID, ParamConfigConstants.ARG_ID_MODIFICAR_PARAMCONFIGMODIFICARPVALOR, ParamConfigConstants.SERV_ALIAS_MODIFICAR, ParamConfigConstants.CLASS_ALIAS, ParamConfigConstants.ARG_ALIAS_MODIFICAR_PARAMCONFIGMODIFICARPVALOR);
        }
    }
}
