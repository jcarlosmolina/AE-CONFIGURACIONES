package com.keralty.aeconfiguraciones.viewmodel.configuracionans.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class TCREARRequest {

    @JsonProperty("pt_p_agrdominio_oid")
    private DominioOid configuracionANSTCREARptpagrDominio;
    
    @JsonIgnore
    private Dominio configuracionANSTCREARptpagrDominioInstance;

    @JsonProperty("pt_p_agrnovedad_json")
    private String configuracionANSTCREARptpagrNovedad;
    
    @JsonIgnore
    private Novedad configuracionANSTCREARptpagrNovedadInstance;

    @JsonProperty("pt_p_agrcausal_json")
    private String configuracionANSTCREARptpagrCausal;
    
    @JsonIgnore
    private Causal configuracionANSTCREARptpagrCausalInstance;

    @JsonProperty("pt_p_atrtramite")
    private Long configuracionANSTCREARptpatrTramite;

    @JsonProperty("pt_p_atransareamedica")
    private Long configuracionANSTCREARptpatrANSAreaMedica;

    @JsonProperty("pt_p_atransafiliaciones")
    private Long configuracionANSTCREARptpatrANSAfiliaciones;

    @JsonProperty("pt_p_atrhabillunes")
    private Boolean configuracionANSTCREARptpatrHabilLunes;

    @JsonProperty("pt_p_atrhabilmartes")
    private Boolean configuracionANSTCREARptpatrHabilMartes;

    @JsonProperty("pt_p_atrhabilmiercoles")
    private Boolean configuracionANSTCREARptpatrHabilMiercoles;

    @JsonProperty("pt_p_atrhabiljueves")
    private Boolean configuracionANSTCREARptpatrHabilJueves;

    @JsonProperty("pt_p_atrhabilviernes")
    private Boolean configuracionANSTCREARptpatrHabilViernes;

    @JsonProperty("pt_p_atrhabilsabado")
    private Boolean configuracionANSTCREARptpatrHabilSabado;

    @JsonProperty("pt_p_atrhabildomingo")
    private Boolean configuracionANSTCREARptpatrHabilDomingo;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkConfiguracionANSTCREARptpagrDominio();
        checkConfiguracionANSTCREARptpatrTramite();
        checkConfiguracionANSTCREARptpatrANSAreaMedica();
        checkConfiguracionANSTCREARptpatrANSAfiliaciones();
        checkConfiguracionANSTCREARptpatrHabilLunes();
        checkConfiguracionANSTCREARptpatrHabilMartes();
        checkConfiguracionANSTCREARptpatrHabilMiercoles();
        checkConfiguracionANSTCREARptpatrHabilJueves();
        checkConfiguracionANSTCREARptpatrHabilViernes();
        checkConfiguracionANSTCREARptpatrHabilSabado();
        checkConfiguracionANSTCREARptpatrHabilDomingo();
    }

    @JsonIgnore
    public DominioOid getConfiguracionANSTCREARptpagrDominio() {
        return this.configuracionANSTCREARptpagrDominio;
    }
    
    public void setConfiguracionANSTCREARptpagrDominio(DominioOid configuracionANSTCREARptpagrDominio) {
        this.configuracionANSTCREARptpagrDominio = configuracionANSTCREARptpagrDominio;
    }

    @JsonIgnore
    public Dominio getConfiguracionANSTCREARptpagrDominioInstance() {
        return this.configuracionANSTCREARptpagrDominioInstance;
    }
    
    public void setConfiguracionANSTCREARptpagrDominioInstance(Dominio configuracionANSTCREARptpagrDominio) {
        this.configuracionANSTCREARptpagrDominioInstance = configuracionANSTCREARptpagrDominio;
        this.configuracionANSTCREARptpagrDominio = configuracionANSTCREARptpagrDominio != null ? configuracionANSTCREARptpagrDominio.getOid() : null;
    }

    private void checkConfiguracionANSTCREARptpagrDominio() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpagrDominioInstance() == null || this.getConfiguracionANSTCREARptpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public String getConfiguracionANSTCREARptpagrNovedad() {
        return this.configuracionANSTCREARptpagrNovedad;
    }
    
    public void setConfiguracionANSTCREARptpagrNovedad(String configuracionANSTCREARptpagrNovedad) {
        this.configuracionANSTCREARptpagrNovedad = configuracionANSTCREARptpagrNovedad;
    }

    @JsonIgnore
    public Novedad getConfiguracionANSTCREARptpagrNovedadInstance() {
        return this.configuracionANSTCREARptpagrNovedadInstance;
    }
    
    public void setConfiguracionANSTCREARptpagrNovedadInstance(Novedad configuracionANSTCREARptpagrNovedad) {
        this.configuracionANSTCREARptpagrNovedadInstance = configuracionANSTCREARptpagrNovedad;
        this.configuracionANSTCREARptpagrNovedad = configuracionANSTCREARptpagrNovedad != null ? configuracionANSTCREARptpagrNovedad.getOid().toJson() : "";
    }

    @JsonIgnore
    public String getConfiguracionANSTCREARptpagrCausal() {
        return this.configuracionANSTCREARptpagrCausal;
    }
    
    public void setConfiguracionANSTCREARptpagrCausal(String configuracionANSTCREARptpagrCausal) {
        this.configuracionANSTCREARptpagrCausal = configuracionANSTCREARptpagrCausal;
    }

    @JsonIgnore
    public Causal getConfiguracionANSTCREARptpagrCausalInstance() {
        return this.configuracionANSTCREARptpagrCausalInstance;
    }
    
    public void setConfiguracionANSTCREARptpagrCausalInstance(Causal configuracionANSTCREARptpagrCausal) {
        this.configuracionANSTCREARptpagrCausalInstance = configuracionANSTCREARptpagrCausal;
        this.configuracionANSTCREARptpagrCausal = configuracionANSTCREARptpagrCausal != null ? configuracionANSTCREARptpagrCausal.getOid().toJson() : "";
    }

    @JsonIgnore
    public Long getConfiguracionANSTCREARptpatrTramite() {
        return this.configuracionANSTCREARptpatrTramite;
    }
    
    public void setConfiguracionANSTCREARptpatrTramite(Long configuracionANSTCREARptpatrTramite) {
        this.configuracionANSTCREARptpatrTramite = configuracionANSTCREARptpatrTramite;
    }

    private void checkConfiguracionANSTCREARptpatrTramite() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrTramite() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRTRAMITE, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRTRAMITE);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSTCREARptpatrANSAreaMedica() {
        return this.configuracionANSTCREARptpatrANSAreaMedica;
    }
    
    public void setConfiguracionANSTCREARptpatrANSAreaMedica(Long configuracionANSTCREARptpatrANSAreaMedica) {
        this.configuracionANSTCREARptpatrANSAreaMedica = configuracionANSTCREARptpatrANSAreaMedica;
    }

    private void checkConfiguracionANSTCREARptpatrANSAreaMedica() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrANSAreaMedica() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRANSAREAMEDICA, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRANSAREAMEDICA);
        }
    }

    @JsonIgnore
    public Long getConfiguracionANSTCREARptpatrANSAfiliaciones() {
        return this.configuracionANSTCREARptpatrANSAfiliaciones;
    }
    
    public void setConfiguracionANSTCREARptpatrANSAfiliaciones(Long configuracionANSTCREARptpatrANSAfiliaciones) {
        this.configuracionANSTCREARptpatrANSAfiliaciones = configuracionANSTCREARptpatrANSAfiliaciones;
    }

    private void checkConfiguracionANSTCREARptpatrANSAfiliaciones() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrANSAfiliaciones() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRANSAFILIACIONES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRANSAFILIACIONES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilLunes() {
        return this.configuracionANSTCREARptpatrHabilLunes;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilLunes(Boolean configuracionANSTCREARptpatrHabilLunes) {
        this.configuracionANSTCREARptpatrHabilLunes = configuracionANSTCREARptpatrHabilLunes;
    }

    private void checkConfiguracionANSTCREARptpatrHabilLunes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilLunes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILLUNES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILLUNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilMartes() {
        return this.configuracionANSTCREARptpatrHabilMartes;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilMartes(Boolean configuracionANSTCREARptpatrHabilMartes) {
        this.configuracionANSTCREARptpatrHabilMartes = configuracionANSTCREARptpatrHabilMartes;
    }

    private void checkConfiguracionANSTCREARptpatrHabilMartes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilMartes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILMARTES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILMARTES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilMiercoles() {
        return this.configuracionANSTCREARptpatrHabilMiercoles;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilMiercoles(Boolean configuracionANSTCREARptpatrHabilMiercoles) {
        this.configuracionANSTCREARptpatrHabilMiercoles = configuracionANSTCREARptpatrHabilMiercoles;
    }

    private void checkConfiguracionANSTCREARptpatrHabilMiercoles() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilMiercoles() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILMIERCOLES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILMIERCOLES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilJueves() {
        return this.configuracionANSTCREARptpatrHabilJueves;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilJueves(Boolean configuracionANSTCREARptpatrHabilJueves) {
        this.configuracionANSTCREARptpatrHabilJueves = configuracionANSTCREARptpatrHabilJueves;
    }

    private void checkConfiguracionANSTCREARptpatrHabilJueves() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilJueves() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILJUEVES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILJUEVES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilViernes() {
        return this.configuracionANSTCREARptpatrHabilViernes;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilViernes(Boolean configuracionANSTCREARptpatrHabilViernes) {
        this.configuracionANSTCREARptpatrHabilViernes = configuracionANSTCREARptpatrHabilViernes;
    }

    private void checkConfiguracionANSTCREARptpatrHabilViernes() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilViernes() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILVIERNES, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILVIERNES);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilSabado() {
        return this.configuracionANSTCREARptpatrHabilSabado;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilSabado(Boolean configuracionANSTCREARptpatrHabilSabado) {
        this.configuracionANSTCREARptpatrHabilSabado = configuracionANSTCREARptpatrHabilSabado;
    }

    private void checkConfiguracionANSTCREARptpatrHabilSabado() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilSabado() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILSABADO, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILSABADO);
        }
    }

    @JsonIgnore
    public Boolean getConfiguracionANSTCREARptpatrHabilDomingo() {
        return this.configuracionANSTCREARptpatrHabilDomingo;
    }
    
    public void setConfiguracionANSTCREARptpatrHabilDomingo(Boolean configuracionANSTCREARptpatrHabilDomingo) {
        this.configuracionANSTCREARptpatrHabilDomingo = configuracionANSTCREARptpatrHabilDomingo;
    }

    private void checkConfiguracionANSTCREARptpatrHabilDomingo() throws NotNullArgumentException {
        if(this.getConfiguracionANSTCREARptpatrHabilDomingo() == null) {
            throw new NotNullArgumentException(ConfiguracionANSConstants.SERV_ID_TCREAR, ConfiguracionANSConstants.CLASS_ID, ConfiguracionANSConstants.ARG_ID_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILDOMINGO, ConfiguracionANSConstants.SERV_ALIAS_TCREAR, ConfiguracionANSConstants.CLASS_ALIAS, ConfiguracionANSConstants.ARG_ALIAS_TCREAR_CONFIGURACIONANSTCREARPTPATRHABILDOMINGO);
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
