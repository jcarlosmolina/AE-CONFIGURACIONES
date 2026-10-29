package com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.LogConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrconfiguracionans_oid")
    private ConfiguracionANSOid logConfiguracionANSTCREARptpagrConfiguracionANS;
    
    @JsonIgnore
    private ConfiguracionANS logConfiguracionANSTCREARptpagrConfiguracionANSInstance;

    @JsonProperty("pt_p_atransafiliaciones")
    private Long logConfiguracionANSTCREARptpatrANSAfiliaciones;

    @JsonProperty("pt_p_atransareamedica")
    private Long logConfiguracionANSTCREARptpatrANSAreaMedica;

    @JsonProperty("pt_p_atrhabillunes")
    private Boolean logConfiguracionANSTCREARptpatrHabilLunes;

    @JsonProperty("pt_p_atrhabilmartes")
    private Boolean logConfiguracionANSTCREARptpatrHabilMartes;

    @JsonProperty("pt_p_atrhabilmiercoles")
    private Boolean logConfiguracionANSTCREARptpatrHabilMiercoles;

    @JsonProperty("pt_p_atrhabiljueves")
    private Boolean logConfiguracionANSTCREARptpatrHabilJueves;

    @JsonProperty("pt_p_atrhabilviernes")
    private Boolean logConfiguracionANSTCREARptpatrHabilViernes;

    @JsonProperty("pt_p_atrhabilsabado")
    private Boolean logConfiguracionANSTCREARptpatrHabilSabado;

    @JsonProperty("pt_p_atrhabildomingo")
    private Boolean logConfiguracionANSTCREARptpatrHabilDomingo;

    public void checkArguments() throws NotNullArgumentException {
        checkLogConfiguracionANSTCREARptpagrConfiguracionANS();
        checkLogConfiguracionANSTCREARptpatrANSAfiliaciones();
        checkLogConfiguracionANSTCREARptpatrANSAreaMedica();
        checkLogConfiguracionANSTCREARptpatrHabilLunes();
        checkLogConfiguracionANSTCREARptpatrHabilMartes();
        checkLogConfiguracionANSTCREARptpatrHabilMiercoles();
        checkLogConfiguracionANSTCREARptpatrHabilJueves();
        checkLogConfiguracionANSTCREARptpatrHabilViernes();
        checkLogConfiguracionANSTCREARptpatrHabilSabado();
        checkLogConfiguracionANSTCREARptpatrHabilDomingo();
    }

    @JsonIgnore
    public ConfiguracionANSOid getLogConfiguracionANSTCREARptpagrConfiguracionANS() {
        return this.logConfiguracionANSTCREARptpagrConfiguracionANS;
    }
    
    public void setLogConfiguracionANSTCREARptpagrConfiguracionANS(ConfiguracionANSOid logConfiguracionANSTCREARptpagrConfiguracionANS) {
        this.logConfiguracionANSTCREARptpagrConfiguracionANS = logConfiguracionANSTCREARptpagrConfiguracionANS;
    }

    @JsonIgnore
    public ConfiguracionANS getLogConfiguracionANSTCREARptpagrConfiguracionANSInstance() {
        return this.logConfiguracionANSTCREARptpagrConfiguracionANSInstance;
    }
    
    public void setLogConfiguracionANSTCREARptpagrConfiguracionANSInstance(ConfiguracionANS logConfiguracionANSTCREARptpagrConfiguracionANS) {
        this.logConfiguracionANSTCREARptpagrConfiguracionANSInstance = logConfiguracionANSTCREARptpagrConfiguracionANS;
        this.logConfiguracionANSTCREARptpagrConfiguracionANS = logConfiguracionANSTCREARptpagrConfiguracionANS != null ? logConfiguracionANSTCREARptpagrConfiguracionANS.getOid() : null;
    }

    private void checkLogConfiguracionANSTCREARptpagrConfiguracionANS() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpagrConfiguracionANSInstance() == null || this.getLogConfiguracionANSTCREARptpagrConfiguracionANSInstance().isNull()) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPAGRCONFIGURACIONANS, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPAGRCONFIGURACIONANS);
        }
    }

    @JsonIgnore
    public Long getLogConfiguracionANSTCREARptpatrANSAfiliaciones() {
        return this.logConfiguracionANSTCREARptpatrANSAfiliaciones;
    }
    
    public void setLogConfiguracionANSTCREARptpatrANSAfiliaciones(Long logConfiguracionANSTCREARptpatrANSAfiliaciones) {
        this.logConfiguracionANSTCREARptpatrANSAfiliaciones = logConfiguracionANSTCREARptpatrANSAfiliaciones;
    }

    private void checkLogConfiguracionANSTCREARptpatrANSAfiliaciones() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrANSAfiliaciones() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRANSAFILIACIONES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRANSAFILIACIONES);
        }
    }

    @JsonIgnore
    public Long getLogConfiguracionANSTCREARptpatrANSAreaMedica() {
        return this.logConfiguracionANSTCREARptpatrANSAreaMedica;
    }
    
    public void setLogConfiguracionANSTCREARptpatrANSAreaMedica(Long logConfiguracionANSTCREARptpatrANSAreaMedica) {
        this.logConfiguracionANSTCREARptpatrANSAreaMedica = logConfiguracionANSTCREARptpatrANSAreaMedica;
    }

    private void checkLogConfiguracionANSTCREARptpatrANSAreaMedica() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrANSAreaMedica() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRANSAREAMEDICA, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilLunes() {
        return this.logConfiguracionANSTCREARptpatrHabilLunes;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilLunes(Boolean logConfiguracionANSTCREARptpatrHabilLunes) {
        this.logConfiguracionANSTCREARptpatrHabilLunes = logConfiguracionANSTCREARptpatrHabilLunes;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilLunes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilLunes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILLUNES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilMartes() {
        return this.logConfiguracionANSTCREARptpatrHabilMartes;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilMartes(Boolean logConfiguracionANSTCREARptpatrHabilMartes) {
        this.logConfiguracionANSTCREARptpatrHabilMartes = logConfiguracionANSTCREARptpatrHabilMartes;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilMartes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilMartes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILMARTES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilMiercoles() {
        return this.logConfiguracionANSTCREARptpatrHabilMiercoles;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilMiercoles(Boolean logConfiguracionANSTCREARptpatrHabilMiercoles) {
        this.logConfiguracionANSTCREARptpatrHabilMiercoles = logConfiguracionANSTCREARptpatrHabilMiercoles;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilMiercoles() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilMiercoles() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILMIERCOLES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilJueves() {
        return this.logConfiguracionANSTCREARptpatrHabilJueves;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilJueves(Boolean logConfiguracionANSTCREARptpatrHabilJueves) {
        this.logConfiguracionANSTCREARptpatrHabilJueves = logConfiguracionANSTCREARptpatrHabilJueves;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilJueves() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilJueves() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILJUEVES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilViernes() {
        return this.logConfiguracionANSTCREARptpatrHabilViernes;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilViernes(Boolean logConfiguracionANSTCREARptpatrHabilViernes) {
        this.logConfiguracionANSTCREARptpatrHabilViernes = logConfiguracionANSTCREARptpatrHabilViernes;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilViernes() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilViernes() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILVIERNES, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilSabado() {
        return this.logConfiguracionANSTCREARptpatrHabilSabado;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilSabado(Boolean logConfiguracionANSTCREARptpatrHabilSabado) {
        this.logConfiguracionANSTCREARptpatrHabilSabado = logConfiguracionANSTCREARptpatrHabilSabado;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilSabado() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilSabado() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILSABADO, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getLogConfiguracionANSTCREARptpatrHabilDomingo() {
        return this.logConfiguracionANSTCREARptpatrHabilDomingo;
    }
    
    public void setLogConfiguracionANSTCREARptpatrHabilDomingo(Boolean logConfiguracionANSTCREARptpatrHabilDomingo) {
        this.logConfiguracionANSTCREARptpatrHabilDomingo = logConfiguracionANSTCREARptpatrHabilDomingo;
    }

    private void checkLogConfiguracionANSTCREARptpatrHabilDomingo() throws NotNullArgumentException {
        if(this.getLogConfiguracionANSTCREARptpatrHabilDomingo() == null) {
            throw new NotNullArgumentException(LogConfiguracionANSConstants.SERV_ID_TCREAR, LogConfiguracionANSConstants.CLASS_ID, LogConfiguracionANSConstants.ARG_ID_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILDOMINGO, LogConfiguracionANSConstants.SERV_ALIAS_TCREAR, LogConfiguracionANSConstants.CLASS_ALIAS, LogConfiguracionANSConstants.ARG_ALIAS_TCREAR_LOGCONFIGURACIONANSTCREARPTPATRHABILDOMINGO);
        }
    }
}
