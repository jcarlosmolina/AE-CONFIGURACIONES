package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class TMODIFICARRequest {

    @JsonProperty("p_thisconfiguracionans_oid")
    private ConfiguracionANSOid configuracionANSTMODIFICARpthisConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS configuracionANSTMODIFICARpthisConfiguracionANSInstance;

    @JsonProperty("pt_p_ansareamedica")
    private Long configuracionANSTMODIFICARptpANSAreaMedica;

    @JsonProperty("pt_p_habillunes")
    private Boolean configuracionANSTMODIFICARptpHabilLunes;

    @JsonProperty("pt_p_habilmartes")
    private Boolean configuracionANSTMODIFICARptpHabilMartes;

    @JsonProperty("pt_p_habilmiercoles")
    private Boolean configuracionANSTMODIFICARptpHabilMiercoles;

    @JsonProperty("pt_p_habiljueves")
    private Boolean configuracionANSTMODIFICARptpHabilJueves;

    @JsonProperty("pt_p_habilviernes")
    private Boolean configuracionANSTMODIFICARptpHabilViernes;

    @JsonProperty("pt_p_habilsabado")
    private Boolean configuracionANSTMODIFICARptpHabilSabado;

    @JsonProperty("pt_p_habildomingo")
    private Boolean configuracionANSTMODIFICARptpHabilDomingo;

    @JsonProperty("pt_p_ansafiliaciones")
    private Long configuracionANSTMODIFICARptpANSAfiliaciones;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANSTMODIFICARpthisConfiguracionANS();
        checkConfiguracionANSTMODIFICARptpANSAreaMedica();
        checkConfiguracionANSTMODIFICARptpHabilLunes();
        checkConfiguracionANSTMODIFICARptpHabilMartes();
        checkConfiguracionANSTMODIFICARptpHabilMiercoles();
        checkConfiguracionANSTMODIFICARptpHabilJueves();
        checkConfiguracionANSTMODIFICARptpHabilViernes();
        checkConfiguracionANSTMODIFICARptpHabilSabado();
        checkConfiguracionANSTMODIFICARptpHabilDomingo();
        checkConfiguracionANSTMODIFICARptpANSAfiliaciones();
    }

    @JsonIgnore
    public ConfiguracionANSOid getConfiguracionANSTMODIFICARpthisConfiguracionANS() {
        return this.configuracionANSTMODIFICARpthisConfiguracionANS;
    }
    
    public void setConfiguracionANSTMODIFICARpthisConfiguracionANS(ConfiguracionANSOid configuracionANSTMODIFICARpthisConfiguracionANS) {
        this.configuracionANSTMODIFICARpthisConfiguracionANS = configuracionANSTMODIFICARpthisConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getConfiguracionANSTMODIFICARpthisConfiguracionANSInstance() {
        return this.configuracionANSTMODIFICARpthisConfiguracionANSInstance;
    }
    
    public void setConfiguracionANSTMODIFICARpthisConfiguracionANSInstance(ConfiguracionANS configuracionANSTMODIFICARpthisConfiguracionANS) {
        this.configuracionANSTMODIFICARpthisConfiguracionANSInstance = configuracionANSTMODIFICARpthisConfiguracionANS;
        this.configuracionANSTMODIFICARpthisConfiguracionANS = configuracionANSTMODIFICARpthisConfiguracionANS != null ? configuracionANSTMODIFICARpthisConfiguracionANS.getOid() : null;
    }

    private void checkConfiguracionANSTMODIFICARpthisConfiguracionANS() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARpthisConfiguracionANSInstance() == null || this.getConfiguracionANSTMODIFICARpthisConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTHISCONFIGURACIONANS, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTHISCONFIGURACIONANS);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSTMODIFICARptpANSAreaMedica() {
        return this.configuracionANSTMODIFICARptpANSAreaMedica;
    }
    
    public void setConfiguracionANSTMODIFICARptpANSAreaMedica(Long configuracionANSTMODIFICARptpANSAreaMedica) {
        this.configuracionANSTMODIFICARptpANSAreaMedica = configuracionANSTMODIFICARptpANSAreaMedica;
    }

    private void checkConfiguracionANSTMODIFICARptpANSAreaMedica() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpANSAreaMedica() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPANSAREAMEDICA, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilLunes() {
        return this.configuracionANSTMODIFICARptpHabilLunes;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilLunes(Boolean configuracionANSTMODIFICARptpHabilLunes) {
        this.configuracionANSTMODIFICARptpHabilLunes = configuracionANSTMODIFICARptpHabilLunes;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilLunes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilLunes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILLUNES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilMartes() {
        return this.configuracionANSTMODIFICARptpHabilMartes;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilMartes(Boolean configuracionANSTMODIFICARptpHabilMartes) {
        this.configuracionANSTMODIFICARptpHabilMartes = configuracionANSTMODIFICARptpHabilMartes;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilMartes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilMartes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILMARTES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilMiercoles() {
        return this.configuracionANSTMODIFICARptpHabilMiercoles;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilMiercoles(Boolean configuracionANSTMODIFICARptpHabilMiercoles) {
        this.configuracionANSTMODIFICARptpHabilMiercoles = configuracionANSTMODIFICARptpHabilMiercoles;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilMiercoles() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilMiercoles() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILMIERCOLES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilJueves() {
        return this.configuracionANSTMODIFICARptpHabilJueves;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilJueves(Boolean configuracionANSTMODIFICARptpHabilJueves) {
        this.configuracionANSTMODIFICARptpHabilJueves = configuracionANSTMODIFICARptpHabilJueves;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilJueves() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilJueves() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILJUEVES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilViernes() {
        return this.configuracionANSTMODIFICARptpHabilViernes;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilViernes(Boolean configuracionANSTMODIFICARptpHabilViernes) {
        this.configuracionANSTMODIFICARptpHabilViernes = configuracionANSTMODIFICARptpHabilViernes;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilViernes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilViernes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILVIERNES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilSabado() {
        return this.configuracionANSTMODIFICARptpHabilSabado;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilSabado(Boolean configuracionANSTMODIFICARptpHabilSabado) {
        this.configuracionANSTMODIFICARptpHabilSabado = configuracionANSTMODIFICARptpHabilSabado;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilSabado() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilSabado() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILSABADO, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTMODIFICARptpHabilDomingo() {
        return this.configuracionANSTMODIFICARptpHabilDomingo;
    }
    
    public void setConfiguracionANSTMODIFICARptpHabilDomingo(Boolean configuracionANSTMODIFICARptpHabilDomingo) {
        this.configuracionANSTMODIFICARptpHabilDomingo = configuracionANSTMODIFICARptpHabilDomingo;
    }

    private void checkConfiguracionANSTMODIFICARptpHabilDomingo() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpHabilDomingo() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILDOMINGO, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPHABILDOMINGO);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSTMODIFICARptpANSAfiliaciones() {
        return this.configuracionANSTMODIFICARptpANSAfiliaciones;
    }
    
    public void setConfiguracionANSTMODIFICARptpANSAfiliaciones(Long configuracionANSTMODIFICARptpANSAfiliaciones) {
        this.configuracionANSTMODIFICARptpANSAfiliaciones = configuracionANSTMODIFICARptpANSAfiliaciones;
    }

    private void checkConfiguracionANSTMODIFICARptpANSAfiliaciones() throws NotNullArgumentException {
        if(this.getConfiguracionANSTMODIFICARptpANSAfiliaciones() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TMODIFICAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPANSAFILIACIONES, ConfiguracionANSConstants.SERV_ALIAS_TMODIFICAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TMODIFICAR_CONFIGURACIONANSTMODIFICARPTPANSAFILIACIONES);
        }
    }
}
