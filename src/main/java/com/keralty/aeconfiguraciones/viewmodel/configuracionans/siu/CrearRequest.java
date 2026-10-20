package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class CrearRequest {

    @JsonProperty("p_agrnovedad_oid")
    private NovedadOid configuracionANScrearpagrNovedad;
    
    @JsonIgnore
    private Novedad configuracionANScrearpagrNovedadInstance;

    @JsonProperty("p_agrcausal_oid")
    private CausalOid configuracionANScrearpagrCausal;
    
    @JsonIgnore
    private Causal configuracionANScrearpagrCausalInstance;

    @JsonProperty("p_agrdominio_oid")
    private DominioOid configuracionANScrearpagrDominio;
    
    @JsonIgnore
    private Dominio configuracionANScrearpagrDominioInstance;

    @JsonProperty("p_atrtramite")
    private Long configuracionANScrearpatrTramite;

    @JsonProperty("p_atransareamedica")
    private Long configuracionANScrearpatrANSAreaMedica;

    @JsonProperty("p_atransafiliaciones")
    private Long configuracionANScrearpatrANSAfiliaciones;

    @JsonProperty("p_atrhabillunes")
    private Boolean configuracionANScrearpatrHabilLunes;

    @JsonProperty("p_atrhabilmartes")
    private Boolean configuracionANScrearpatrHabilMartes;

    @JsonProperty("p_atrhabilmiercoles")
    private Boolean configuracionANScrearpatrHabilMiercoles;

    @JsonProperty("p_atrhabiljueves")
    private Boolean configuracionANScrearpatrHabilJueves;

    @JsonProperty("p_atrhabilviernes")
    private Boolean configuracionANScrearpatrHabilViernes;

    @JsonProperty("p_atrhabilsabado")
    private Boolean configuracionANScrearpatrHabilSabado;

    @JsonProperty("p_atrhabildomingo")
    private Boolean configuracionANScrearpatrHabilDomingo;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANScrearpagrDominio();
        checkConfiguracionANScrearpatrTramite();
        checkConfiguracionANScrearpatrANSAreaMedica();
        checkConfiguracionANScrearpatrANSAfiliaciones();
        checkConfiguracionANScrearpatrHabilLunes();
        checkConfiguracionANScrearpatrHabilMartes();
        checkConfiguracionANScrearpatrHabilMiercoles();
        checkConfiguracionANScrearpatrHabilJueves();
        checkConfiguracionANScrearpatrHabilViernes();
        checkConfiguracionANScrearpatrHabilSabado();
        checkConfiguracionANScrearpatrHabilDomingo();
    }

    @JsonIgnore
    public NovedadOid getConfiguracionANScrearpagrNovedad() {
        return this.configuracionANScrearpagrNovedad;
    }
    
    public void setConfiguracionANScrearpagrNovedad(NovedadOid configuracionANScrearpagrNovedad) {
        this.configuracionANScrearpagrNovedad = configuracionANScrearpagrNovedad;
    }

    @JsonIgnore
    public Novedad getConfiguracionANScrearpagrNovedadInstance() {
        return this.configuracionANScrearpagrNovedadInstance;
    }
    
    public void setConfiguracionANScrearpagrNovedadInstance(Novedad configuracionANScrearpagrNovedad) {
        this.configuracionANScrearpagrNovedadInstance = configuracionANScrearpagrNovedad;
        this.configuracionANScrearpagrNovedad = configuracionANScrearpagrNovedad != null ? configuracionANScrearpagrNovedad.getOid() : null;
    }

    @JsonIgnore
    public CausalOid getConfiguracionANScrearpagrCausal() {
        return this.configuracionANScrearpagrCausal;
    }
    
    public void setConfiguracionANScrearpagrCausal(CausalOid configuracionANScrearpagrCausal) {
        this.configuracionANScrearpagrCausal = configuracionANScrearpagrCausal;
    }

    @JsonIgnore
    public Causal getConfiguracionANScrearpagrCausalInstance() {
        return this.configuracionANScrearpagrCausalInstance;
    }
    
    public void setConfiguracionANScrearpagrCausalInstance(Causal configuracionANScrearpagrCausal) {
        this.configuracionANScrearpagrCausalInstance = configuracionANScrearpagrCausal;
        this.configuracionANScrearpagrCausal = configuracionANScrearpagrCausal != null ? configuracionANScrearpagrCausal.getOid() : null;
    }

    @JsonIgnore
    public DominioOid getConfiguracionANScrearpagrDominio() {
        return this.configuracionANScrearpagrDominio;
    }
    
    public void setConfiguracionANScrearpagrDominio(DominioOid configuracionANScrearpagrDominio) {
        this.configuracionANScrearpagrDominio = configuracionANScrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getConfiguracionANScrearpagrDominioInstance() {
        return this.configuracionANScrearpagrDominioInstance;
    }
    
    public void setConfiguracionANScrearpagrDominioInstance(Dominio configuracionANScrearpagrDominio) {
        this.configuracionANScrearpagrDominioInstance = configuracionANScrearpagrDominio;
        this.configuracionANScrearpagrDominio = configuracionANScrearpagrDominio != null ? configuracionANScrearpagrDominio.getOid() : null;
    }

    private void checkConfiguracionANScrearpagrDominio() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpagrDominioInstance() == null || this.getConfiguracionANScrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPAGRDOMINIO, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANScrearpatrTramite() {
        return this.configuracionANScrearpatrTramite;
    }
    
    public void setConfiguracionANScrearpatrTramite(Long configuracionANScrearpatrTramite) {
        this.configuracionANScrearpatrTramite = configuracionANScrearpatrTramite;
    }

    private void checkConfiguracionANScrearpatrTramite() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrTramite() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRTRAMITE, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRTRAMITE);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANScrearpatrANSAreaMedica() {
        return this.configuracionANScrearpatrANSAreaMedica;
    }
    
    public void setConfiguracionANScrearpatrANSAreaMedica(Long configuracionANScrearpatrANSAreaMedica) {
        this.configuracionANScrearpatrANSAreaMedica = configuracionANScrearpatrANSAreaMedica;
    }

    private void checkConfiguracionANScrearpatrANSAreaMedica() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrANSAreaMedica() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRANSAREAMEDICA, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANScrearpatrANSAfiliaciones() {
        return this.configuracionANScrearpatrANSAfiliaciones;
    }
    
    public void setConfiguracionANScrearpatrANSAfiliaciones(Long configuracionANScrearpatrANSAfiliaciones) {
        this.configuracionANScrearpatrANSAfiliaciones = configuracionANScrearpatrANSAfiliaciones;
    }

    private void checkConfiguracionANScrearpatrANSAfiliaciones() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrANSAfiliaciones() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRANSAFILIACIONES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRANSAFILIACIONES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilLunes() {
        return this.configuracionANScrearpatrHabilLunes;
    }
    
    public void setConfiguracionANScrearpatrHabilLunes(Boolean configuracionANScrearpatrHabilLunes) {
        this.configuracionANScrearpatrHabilLunes = configuracionANScrearpatrHabilLunes;
    }

    private void checkConfiguracionANScrearpatrHabilLunes() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilLunes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILLUNES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilMartes() {
        return this.configuracionANScrearpatrHabilMartes;
    }
    
    public void setConfiguracionANScrearpatrHabilMartes(Boolean configuracionANScrearpatrHabilMartes) {
        this.configuracionANScrearpatrHabilMartes = configuracionANScrearpatrHabilMartes;
    }

    private void checkConfiguracionANScrearpatrHabilMartes() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilMartes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILMARTES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilMiercoles() {
        return this.configuracionANScrearpatrHabilMiercoles;
    }
    
    public void setConfiguracionANScrearpatrHabilMiercoles(Boolean configuracionANScrearpatrHabilMiercoles) {
        this.configuracionANScrearpatrHabilMiercoles = configuracionANScrearpatrHabilMiercoles;
    }

    private void checkConfiguracionANScrearpatrHabilMiercoles() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilMiercoles() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILMIERCOLES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilJueves() {
        return this.configuracionANScrearpatrHabilJueves;
    }
    
    public void setConfiguracionANScrearpatrHabilJueves(Boolean configuracionANScrearpatrHabilJueves) {
        this.configuracionANScrearpatrHabilJueves = configuracionANScrearpatrHabilJueves;
    }

    private void checkConfiguracionANScrearpatrHabilJueves() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilJueves() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILJUEVES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilViernes() {
        return this.configuracionANScrearpatrHabilViernes;
    }
    
    public void setConfiguracionANScrearpatrHabilViernes(Boolean configuracionANScrearpatrHabilViernes) {
        this.configuracionANScrearpatrHabilViernes = configuracionANScrearpatrHabilViernes;
    }

    private void checkConfiguracionANScrearpatrHabilViernes() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilViernes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILVIERNES, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilSabado() {
        return this.configuracionANScrearpatrHabilSabado;
    }
    
    public void setConfiguracionANScrearpatrHabilSabado(Boolean configuracionANScrearpatrHabilSabado) {
        this.configuracionANScrearpatrHabilSabado = configuracionANScrearpatrHabilSabado;
    }

    private void checkConfiguracionANScrearpatrHabilSabado() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilSabado() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILSABADO, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANScrearpatrHabilDomingo() {
        return this.configuracionANScrearpatrHabilDomingo;
    }
    
    public void setConfiguracionANScrearpatrHabilDomingo(Boolean configuracionANScrearpatrHabilDomingo) {
        this.configuracionANScrearpatrHabilDomingo = configuracionANScrearpatrHabilDomingo;
    }

    private void checkConfiguracionANScrearpatrHabilDomingo() throws NotNullArgumentException {
        if(this.getConfiguracionANScrearpatrHabilDomingo() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_CREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_CREAR_CONFIGURACIONANSCREARPATRHABILDOMINGO, ConfiguracionANSConstants.SERV_ALIAS_CREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_CREAR_CONFIGURACIONANSCREARPATRHABILDOMINGO);
        }
    }
}
