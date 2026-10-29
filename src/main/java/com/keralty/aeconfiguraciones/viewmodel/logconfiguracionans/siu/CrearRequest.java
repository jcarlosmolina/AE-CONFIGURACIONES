package com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class CrearRequest {

    @JsonProperty("p_agrconfiguracionans_oid")
    private ConfiguracionANSOid logConfiguracionANScrearpagrConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS logConfiguracionANScrearpagrConfiguracionANSInstance;

    @JsonProperty("p_atrloginfuncionario")
    private String logConfiguracionANScrearpatrLoginFuncionario;

    @JsonProperty("p_atransafiliaciones")
    private Long logConfiguracionANScrearpatrANSAfiliaciones;

    @JsonProperty("p_atransareamedica")
    private Long logConfiguracionANScrearpatrANSAreaMedica;

    @JsonProperty("p_atrhabillunes")
    private Boolean logConfiguracionANScrearpatrHabilLunes;

    @JsonProperty("p_atrhabilmartes")
    private Boolean logConfiguracionANScrearpatrHabilMartes;

    @JsonProperty("p_atrhabilmiercoles")
    private Boolean logConfiguracionANScrearpatrHabilMiercoles;

    @JsonProperty("p_atrhabiljueves")
    private Boolean logConfiguracionANScrearpatrHabilJueves;

    @JsonProperty("p_atrhabilviernes")
    private Boolean logConfiguracionANScrearpatrHabilViernes;

    @JsonProperty("p_atrhabilsabado")
    private Boolean logConfiguracionANScrearpatrHabilSabado;

    @JsonProperty("p_atrhabildomingo")
    private Boolean logConfiguracionANScrearpatrHabilDomingo;

    public void checkArguments() throws NotNullArgumentException {
        checkLogConfiguracionANScrearpagrConfiguracionANS();
        checkLogConfiguracionANScrearpatrLoginFuncionario();
        checkLogConfiguracionANScrearpatrANSAfiliaciones();
        checkLogConfiguracionANScrearpatrANSAreaMedica();
        checkLogConfiguracionANScrearpatrHabilLunes();
        checkLogConfiguracionANScrearpatrHabilMartes();
        checkLogConfiguracionANScrearpatrHabilMiercoles();
        checkLogConfiguracionANScrearpatrHabilJueves();
        checkLogConfiguracionANScrearpatrHabilViernes();
        checkLogConfiguracionANScrearpatrHabilSabado();
        checkLogConfiguracionANScrearpatrHabilDomingo();
    }

    @JsonIgnore
    public ConfiguracionANSOid getLogConfiguracionANScrearpagrConfiguracionANS() {
        return this.logConfiguracionANScrearpagrConfiguracionANS;
    }
    
    public void setLogConfiguracionANScrearpagrConfiguracionANS(ConfiguracionANSOid logConfiguracionANScrearpagrConfiguracionANS) {
        this.logConfiguracionANScrearpagrConfiguracionANS = logConfiguracionANScrearpagrConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getLogConfiguracionANScrearpagrConfiguracionANSInstance() {
        return this.logConfiguracionANScrearpagrConfiguracionANSInstance;
    }
    
    public void setLogConfiguracionANScrearpagrConfiguracionANSInstance(ConfiguracionANS logConfiguracionANScrearpagrConfiguracionANS) {
        this.logConfiguracionANScrearpagrConfiguracionANSInstance = logConfiguracionANScrearpagrConfiguracionANS;
        this.logConfiguracionANScrearpagrConfiguracionANS = logConfiguracionANScrearpagrConfiguracionANS != null ? logConfiguracionANScrearpagrConfiguracionANS.getOid() : null;
    }

    private void checkLogConfiguracionANScrearpagrConfiguracionANS() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpagrConfiguracionANSInstance() == null || this.getLogConfiguracionANScrearpagrConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPAGRCONFIGURACIONANS, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPAGRCONFIGURACIONANS);
        }
    }

    @JsonIgnore
    public String getLogConfiguracionANScrearpatrLoginFuncionario() {
        return this.logConfiguracionANScrearpatrLoginFuncionario;
    }
    
    public void setLogConfiguracionANScrearpatrLoginFuncionario(String logConfiguracionANScrearpatrLoginFuncionario) {
        this.logConfiguracionANScrearpatrLoginFuncionario = logConfiguracionANScrearpatrLoginFuncionario;
    }

    private void checkLogConfiguracionANScrearpatrLoginFuncionario() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrLoginFuncionario() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRLOGINFUNCIONARIO, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRLOGINFUNCIONARIO);
        }
    }

    @JsonIgnore
    public Long getLogConfiguracionANScrearpatrANSAfiliaciones() {
        return this.logConfiguracionANScrearpatrANSAfiliaciones;
    }
    
    public void setLogConfiguracionANScrearpatrANSAfiliaciones(Long logConfiguracionANScrearpatrANSAfiliaciones) {
        this.logConfiguracionANScrearpatrANSAfiliaciones = logConfiguracionANScrearpatrANSAfiliaciones;
    }

    private void checkLogConfiguracionANScrearpatrANSAfiliaciones() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrANSAfiliaciones() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRANSAFILIACIONES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRANSAFILIACIONES);
        }
    }

    @JsonIgnore
    public Long getLogConfiguracionANScrearpatrANSAreaMedica() {
        return this.logConfiguracionANScrearpatrANSAreaMedica;
    }
    
    public void setLogConfiguracionANScrearpatrANSAreaMedica(Long logConfiguracionANScrearpatrANSAreaMedica) {
        this.logConfiguracionANScrearpatrANSAreaMedica = logConfiguracionANScrearpatrANSAreaMedica;
    }

    private void checkLogConfiguracionANScrearpatrANSAreaMedica() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrANSAreaMedica() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRANSAREAMEDICA, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilLunes() {
        return this.logConfiguracionANScrearpatrHabilLunes;
    }
    
    public void setLogConfiguracionANScrearpatrHabilLunes(Boolean logConfiguracionANScrearpatrHabilLunes) {
        this.logConfiguracionANScrearpatrHabilLunes = logConfiguracionANScrearpatrHabilLunes;
    }

    private void checkLogConfiguracionANScrearpatrHabilLunes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilLunes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILLUNES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilMartes() {
        return this.logConfiguracionANScrearpatrHabilMartes;
    }
    
    public void setLogConfiguracionANScrearpatrHabilMartes(Boolean logConfiguracionANScrearpatrHabilMartes) {
        this.logConfiguracionANScrearpatrHabilMartes = logConfiguracionANScrearpatrHabilMartes;
    }

    private void checkLogConfiguracionANScrearpatrHabilMartes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilMartes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILMARTES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilMiercoles() {
        return this.logConfiguracionANScrearpatrHabilMiercoles;
    }
    
    public void setLogConfiguracionANScrearpatrHabilMiercoles(Boolean logConfiguracionANScrearpatrHabilMiercoles) {
        this.logConfiguracionANScrearpatrHabilMiercoles = logConfiguracionANScrearpatrHabilMiercoles;
    }

    private void checkLogConfiguracionANScrearpatrHabilMiercoles() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilMiercoles() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILMIERCOLES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilJueves() {
        return this.logConfiguracionANScrearpatrHabilJueves;
    }
    
    public void setLogConfiguracionANScrearpatrHabilJueves(Boolean logConfiguracionANScrearpatrHabilJueves) {
        this.logConfiguracionANScrearpatrHabilJueves = logConfiguracionANScrearpatrHabilJueves;
    }

    private void checkLogConfiguracionANScrearpatrHabilJueves() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilJueves() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILJUEVES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilViernes() {
        return this.logConfiguracionANScrearpatrHabilViernes;
    }
    
    public void setLogConfiguracionANScrearpatrHabilViernes(Boolean logConfiguracionANScrearpatrHabilViernes) {
        this.logConfiguracionANScrearpatrHabilViernes = logConfiguracionANScrearpatrHabilViernes;
    }

    private void checkLogConfiguracionANScrearpatrHabilViernes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilViernes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILVIERNES, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilSabado() {
        return this.logConfiguracionANScrearpatrHabilSabado;
    }
    
    public void setLogConfiguracionANScrearpatrHabilSabado(Boolean logConfiguracionANScrearpatrHabilSabado) {
        this.logConfiguracionANScrearpatrHabilSabado = logConfiguracionANScrearpatrHabilSabado;
    }

    private void checkLogConfiguracionANScrearpatrHabilSabado() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilSabado() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILSABADO, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANScrearpatrHabilDomingo() {
        return this.logConfiguracionANScrearpatrHabilDomingo;
    }
    
    public void setLogConfiguracionANScrearpatrHabilDomingo(Boolean logConfiguracionANScrearpatrHabilDomingo) {
        this.logConfiguracionANScrearpatrHabilDomingo = logConfiguracionANScrearpatrHabilDomingo;
    }

    private void checkLogConfiguracionANScrearpatrHabilDomingo() throws NotNullArgumentException {
        if(this.getLogConfiguracionANScrearpatrHabilDomingo() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_CREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_CREAR_LOGCONFIGURACIONANSCREARPATRHABILDOMINGO, LogConfiguracionANSConstants.SERV_ALIAS_CREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_CREAR_LOGCONFIGURACIONANSCREARPATRHABILDOMINGO);
        }
    }
}
