package com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;

public class ModificarRequest {

    @JsonProperty("p_thistipoidentificacion_json")
    private String tipoIdentificacionmodificarpthisTipoIdentificacion;
    
    @JsonIgnore
    private TipoIdentificacion tipoIdentificacionmodificarpthisTipoIdentificacionInstance;

    @JsonProperty("p_validocontratante")
    private Boolean tipoIdentificacionmodificarpValidoContratante;

    @JsonProperty("p_validotitular")
    private Boolean tipoIdentificacionmodificarpValidoTitular;

    @JsonProperty("p_diplomatico")
    private Boolean tipoIdentificacionmodificarpDiplomatico;

    @JsonProperty("p_nit")
    private Boolean tipoIdentificacionmodificarpNIT;

    @JsonProperty("p_hijode")
    private Boolean tipoIdentificacionmodificarpHijoDe;

    @JsonProperty("p_requiereeps")
    private Boolean tipoIdentificacionmodificarpRequiereEPS;

    @JsonProperty("p_menorsinidentificar")
    private Boolean tipoIdentificacionmodificarpMenorSinIdentificar;

    @JsonProperty("p_espasaporte")
    private Boolean tipoIdentificacionmodificarpEsPasaporte;

    @JsonProperty("p_visiblevalidadorusuarios")
    private Boolean tipoIdentificacionmodificarpVisibleValidadorUsuarios;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoIdentificacionmodificarpthisTipoIdentificacion();
        checkTipoIdentificacionmodificarpValidoContratante();
        checkTipoIdentificacionmodificarpValidoTitular();
        checkTipoIdentificacionmodificarpDiplomatico();
        checkTipoIdentificacionmodificarpNIT();
        checkTipoIdentificacionmodificarpHijoDe();
        checkTipoIdentificacionmodificarpRequiereEPS();
        checkTipoIdentificacionmodificarpMenorSinIdentificar();
        checkTipoIdentificacionmodificarpEsPasaporte();
        checkTipoIdentificacionmodificarpVisibleValidadorUsuarios();
    }

    @JsonIgnore
    public String getTipoIdentificacionmodificarpthisTipoIdentificacion() {
        return this.tipoIdentificacionmodificarpthisTipoIdentificacion;
    }
    
    public void setTipoIdentificacionmodificarpthisTipoIdentificacion(String tipoIdentificacionmodificarpthisTipoIdentificacion) {
        this.tipoIdentificacionmodificarpthisTipoIdentificacion = tipoIdentificacionmodificarpthisTipoIdentificacion;
    }

    @JsonIgnore
    public TipoIdentificacion getTipoIdentificacionmodificarpthisTipoIdentificacionInstance() {
        return this.tipoIdentificacionmodificarpthisTipoIdentificacionInstance;
    }
    
    public void setTipoIdentificacionmodificarpthisTipoIdentificacionInstance(TipoIdentificacion tipoIdentificacionmodificarpthisTipoIdentificacion) {
        this.tipoIdentificacionmodificarpthisTipoIdentificacionInstance = tipoIdentificacionmodificarpthisTipoIdentificacion;
        this.tipoIdentificacionmodificarpthisTipoIdentificacion = tipoIdentificacionmodificarpthisTipoIdentificacion != null ? tipoIdentificacionmodificarpthisTipoIdentificacion.getOid().toJson() : "";
    }

    private void checkTipoIdentificacionmodificarpthisTipoIdentificacion() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpthisTipoIdentificacionInstance() == null || this.getTipoIdentificacionmodificarpthisTipoIdentificacionInstance().isNull()) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPTHISTIPOIDENTIFICACION, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPTHISTIPOIDENTIFICACION);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpValidoContratante() {
        return this.tipoIdentificacionmodificarpValidoContratante;
    }
    
    public void setTipoIdentificacionmodificarpValidoContratante(Boolean tipoIdentificacionmodificarpValidoContratante) {
        this.tipoIdentificacionmodificarpValidoContratante = tipoIdentificacionmodificarpValidoContratante;
    }

    private void checkTipoIdentificacionmodificarpValidoContratante() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpValidoContratante() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVALIDOCONTRATANTE, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVALIDOCONTRATANTE);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpValidoTitular() {
        return this.tipoIdentificacionmodificarpValidoTitular;
    }
    
    public void setTipoIdentificacionmodificarpValidoTitular(Boolean tipoIdentificacionmodificarpValidoTitular) {
        this.tipoIdentificacionmodificarpValidoTitular = tipoIdentificacionmodificarpValidoTitular;
    }

    private void checkTipoIdentificacionmodificarpValidoTitular() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpValidoTitular() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVALIDOTITULAR, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVALIDOTITULAR);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpDiplomatico() {
        return this.tipoIdentificacionmodificarpDiplomatico;
    }
    
    public void setTipoIdentificacionmodificarpDiplomatico(Boolean tipoIdentificacionmodificarpDiplomatico) {
        this.tipoIdentificacionmodificarpDiplomatico = tipoIdentificacionmodificarpDiplomatico;
    }

    private void checkTipoIdentificacionmodificarpDiplomatico() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpDiplomatico() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPDIPLOMATICO, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPDIPLOMATICO);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpNIT() {
        return this.tipoIdentificacionmodificarpNIT;
    }
    
    public void setTipoIdentificacionmodificarpNIT(Boolean tipoIdentificacionmodificarpNIT) {
        this.tipoIdentificacionmodificarpNIT = tipoIdentificacionmodificarpNIT;
    }

    private void checkTipoIdentificacionmodificarpNIT() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpNIT() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPNIT, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPNIT);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpHijoDe() {
        return this.tipoIdentificacionmodificarpHijoDe;
    }
    
    public void setTipoIdentificacionmodificarpHijoDe(Boolean tipoIdentificacionmodificarpHijoDe) {
        this.tipoIdentificacionmodificarpHijoDe = tipoIdentificacionmodificarpHijoDe;
    }

    private void checkTipoIdentificacionmodificarpHijoDe() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpHijoDe() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPHIJODE, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPHIJODE);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpRequiereEPS() {
        return this.tipoIdentificacionmodificarpRequiereEPS;
    }
    
    public void setTipoIdentificacionmodificarpRequiereEPS(Boolean tipoIdentificacionmodificarpRequiereEPS) {
        this.tipoIdentificacionmodificarpRequiereEPS = tipoIdentificacionmodificarpRequiereEPS;
    }

    private void checkTipoIdentificacionmodificarpRequiereEPS() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpRequiereEPS() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPREQUIEREEPS, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPREQUIEREEPS);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpMenorSinIdentificar() {
        return this.tipoIdentificacionmodificarpMenorSinIdentificar;
    }
    
    public void setTipoIdentificacionmodificarpMenorSinIdentificar(Boolean tipoIdentificacionmodificarpMenorSinIdentificar) {
        this.tipoIdentificacionmodificarpMenorSinIdentificar = tipoIdentificacionmodificarpMenorSinIdentificar;
    }

    private void checkTipoIdentificacionmodificarpMenorSinIdentificar() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpMenorSinIdentificar() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPMENORSINIDENTIFICAR, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPMENORSINIDENTIFICAR);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpEsPasaporte() {
        return this.tipoIdentificacionmodificarpEsPasaporte;
    }
    
    public void setTipoIdentificacionmodificarpEsPasaporte(Boolean tipoIdentificacionmodificarpEsPasaporte) {
        this.tipoIdentificacionmodificarpEsPasaporte = tipoIdentificacionmodificarpEsPasaporte;
    }

    private void checkTipoIdentificacionmodificarpEsPasaporte() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpEsPasaporte() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPESPASAPORTE, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPESPASAPORTE);
        }
    }

    @JsonIgnore
    public Boolean getTipoIdentificacionmodificarpVisibleValidadorUsuarios() {
        return this.tipoIdentificacionmodificarpVisibleValidadorUsuarios;
    }
    
    public void setTipoIdentificacionmodificarpVisibleValidadorUsuarios(Boolean tipoIdentificacionmodificarpVisibleValidadorUsuarios) {
        this.tipoIdentificacionmodificarpVisibleValidadorUsuarios = tipoIdentificacionmodificarpVisibleValidadorUsuarios;
    }

    private void checkTipoIdentificacionmodificarpVisibleValidadorUsuarios() throws NotNullArgumentException {
        if(this.getTipoIdentificacionmodificarpVisibleValidadorUsuarios() == null) {
            throw new NotNullArgumentException(TipoIdentificacionConstants.SERV_ID_MODIFICAR, TipoIdentificacionConstants.CLASS_ID, TipoIdentificacionConstants.ARG_ID_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVISIBLEVALIDADORUSUARIOS, TipoIdentificacionConstants.SERV_ALIAS_MODIFICAR, TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ARG_ALIAS_MODIFICAR_TIPOIDENTIFICACIONMODIFICARPVISIBLEVALIDADORUSUARIOS);
        }
    }
}
