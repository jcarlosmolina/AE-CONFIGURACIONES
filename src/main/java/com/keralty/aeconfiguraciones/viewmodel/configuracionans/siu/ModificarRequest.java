package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class ModificarRequest {

    @JsonProperty("p_thisconfiguracionans_oid")
    private ConfiguracionANSOid configuracionANSmodificarpthisConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS configuracionANSmodificarpthisConfiguracionANSInstance;

    @JsonProperty("p_ansareamedica")
    private Long configuracionANSmodificarpANSAreaMedica;

    @JsonProperty("p_habillunes")
    private Boolean configuracionANSmodificarpHabilLunes;

    @JsonProperty("p_habilmartes")
    private Boolean configuracionANSmodificarpHabilMartes;

    @JsonProperty("p_habilmiercoles")
    private Boolean configuracionANSmodificarpHabilMiercoles;

    @JsonProperty("p_habiljueves")
    private Boolean configuracionANSmodificarpHabilJueves;

    @JsonProperty("p_habilviernes")
    private Boolean configuracionANSmodificarpHabilViernes;

    @JsonProperty("p_habilsabado")
    private Boolean configuracionANSmodificarpHabilSabado;

    @JsonProperty("p_habildomingo")
    private Boolean configuracionANSmodificarpHabilDomingo;

    @JsonProperty("p_ansafiliaciones")
    private Long configuracionANSmodificarpANSAfiliaciones;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANSmodificarpthisConfiguracionANS();
        checkConfiguracionANSmodificarpANSAreaMedica();
        checkConfiguracionANSmodificarpHabilLunes();
        checkConfiguracionANSmodificarpHabilMartes();
        checkConfiguracionANSmodificarpHabilMiercoles();
        checkConfiguracionANSmodificarpHabilJueves();
        checkConfiguracionANSmodificarpHabilViernes();
        checkConfiguracionANSmodificarpHabilSabado();
        checkConfiguracionANSmodificarpHabilDomingo();
        checkConfiguracionANSmodificarpANSAfiliaciones();
    }

    @JsonIgnore
    public ConfiguracionANSOid getConfiguracionANSmodificarpthisConfiguracionANS() {
        return this.configuracionANSmodificarpthisConfiguracionANS;
    }
    
    public void setConfiguracionANSmodificarpthisConfiguracionANS(ConfiguracionANSOid configuracionANSmodificarpthisConfiguracionANS) {
        this.configuracionANSmodificarpthisConfiguracionANS = configuracionANSmodificarpthisConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getConfiguracionANSmodificarpthisConfiguracionANSInstance() {
        return this.configuracionANSmodificarpthisConfiguracionANSInstance;
    }
    
    public void setConfiguracionANSmodificarpthisConfiguracionANSInstance(ConfiguracionANS configuracionANSmodificarpthisConfiguracionANS) {
        this.configuracionANSmodificarpthisConfiguracionANSInstance = configuracionANSmodificarpthisConfiguracionANS;
        this.configuracionANSmodificarpthisConfiguracionANS = configuracionANSmodificarpthisConfiguracionANS != null ? configuracionANSmodificarpthisConfiguracionANS.getOid() : null;
    }

    private void checkConfiguracionANSmodificarpthisConfiguracionANS() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpthisConfiguracionANSInstance() == null || this.getConfiguracionANSmodificarpthisConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPTHISCONFIGURACIONANS, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPTHISCONFIGURACIONANS);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSmodificarpANSAreaMedica() {
        return this.configuracionANSmodificarpANSAreaMedica;
    }
    
    public void setConfiguracionANSmodificarpANSAreaMedica(Long configuracionANSmodificarpANSAreaMedica) {
        this.configuracionANSmodificarpANSAreaMedica = configuracionANSmodificarpANSAreaMedica;
    }

    private void checkConfiguracionANSmodificarpANSAreaMedica() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpANSAreaMedica() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPANSAREAMEDICA, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilLunes() {
        return this.configuracionANSmodificarpHabilLunes;
    }
    
    public void setConfiguracionANSmodificarpHabilLunes(Boolean configuracionANSmodificarpHabilLunes) {
        this.configuracionANSmodificarpHabilLunes = configuracionANSmodificarpHabilLunes;
    }

    private void checkConfiguracionANSmodificarpHabilLunes() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilLunes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILLUNES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilMartes() {
        return this.configuracionANSmodificarpHabilMartes;
    }
    
    public void setConfiguracionANSmodificarpHabilMartes(Boolean configuracionANSmodificarpHabilMartes) {
        this.configuracionANSmodificarpHabilMartes = configuracionANSmodificarpHabilMartes;
    }

    private void checkConfiguracionANSmodificarpHabilMartes() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilMartes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILMARTES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilMiercoles() {
        return this.configuracionANSmodificarpHabilMiercoles;
    }
    
    public void setConfiguracionANSmodificarpHabilMiercoles(Boolean configuracionANSmodificarpHabilMiercoles) {
        this.configuracionANSmodificarpHabilMiercoles = configuracionANSmodificarpHabilMiercoles;
    }

    private void checkConfiguracionANSmodificarpHabilMiercoles() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilMiercoles() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILMIERCOLES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilJueves() {
        return this.configuracionANSmodificarpHabilJueves;
    }
    
    public void setConfiguracionANSmodificarpHabilJueves(Boolean configuracionANSmodificarpHabilJueves) {
        this.configuracionANSmodificarpHabilJueves = configuracionANSmodificarpHabilJueves;
    }

    private void checkConfiguracionANSmodificarpHabilJueves() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilJueves() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILJUEVES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilViernes() {
        return this.configuracionANSmodificarpHabilViernes;
    }
    
    public void setConfiguracionANSmodificarpHabilViernes(Boolean configuracionANSmodificarpHabilViernes) {
        this.configuracionANSmodificarpHabilViernes = configuracionANSmodificarpHabilViernes;
    }

    private void checkConfiguracionANSmodificarpHabilViernes() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilViernes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILVIERNES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilSabado() {
        return this.configuracionANSmodificarpHabilSabado;
    }
    
    public void setConfiguracionANSmodificarpHabilSabado(Boolean configuracionANSmodificarpHabilSabado) {
        this.configuracionANSmodificarpHabilSabado = configuracionANSmodificarpHabilSabado;
    }

    private void checkConfiguracionANSmodificarpHabilSabado() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilSabado() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILSABADO, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSmodificarpHabilDomingo() {
        return this.configuracionANSmodificarpHabilDomingo;
    }
    
    public void setConfiguracionANSmodificarpHabilDomingo(Boolean configuracionANSmodificarpHabilDomingo) {
        this.configuracionANSmodificarpHabilDomingo = configuracionANSmodificarpHabilDomingo;
    }

    private void checkConfiguracionANSmodificarpHabilDomingo() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpHabilDomingo() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILDOMINGO, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPHABILDOMINGO);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSmodificarpANSAfiliaciones() {
        return this.configuracionANSmodificarpANSAfiliaciones;
    }
    
    public void setConfiguracionANSmodificarpANSAfiliaciones(Long configuracionANSmodificarpANSAfiliaciones) {
        this.configuracionANSmodificarpANSAfiliaciones = configuracionANSmodificarpANSAfiliaciones;
    }

    private void checkConfiguracionANSmodificarpANSAfiliaciones() throws NotNullArgumentException {
        if(this.getConfiguracionANSmodificarpANSAfiliaciones() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_MODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_MODIFICAR_CONFIGURACIONANSMODIFICARPANSAFILIACIONES, ConfiguracionANSConstants.SERV_ALIAS_MODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_MODIFICAR_CONFIGURACIONANSMODIFICARPANSAFILIACIONES);
        }
    }
}
