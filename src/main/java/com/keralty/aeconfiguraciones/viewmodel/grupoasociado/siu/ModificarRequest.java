package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;

public class ModificarRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadomodificarpthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadomodificarpthisGrupoAsociadoInstance;

    @JsonProperty("p_disminucioncarencia")
    private Long grupoAsociadomodificarpDisminucionCarencia;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadomodificarpthisGrupoAsociado();
        checkGrupoAsociadomodificarpDisminucionCarencia();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadomodificarpthisGrupoAsociado() {
        return this.grupoAsociadomodificarpthisGrupoAsociado;
    }
    
    public void setGrupoAsociadomodificarpthisGrupoAsociado(GrupoAsociadoOid grupoAsociadomodificarpthisGrupoAsociado) {
        this.grupoAsociadomodificarpthisGrupoAsociado = grupoAsociadomodificarpthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadomodificarpthisGrupoAsociadoInstance() {
        return this.grupoAsociadomodificarpthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadomodificarpthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadomodificarpthisGrupoAsociado) {
        this.grupoAsociadomodificarpthisGrupoAsociadoInstance = grupoAsociadomodificarpthisGrupoAsociado;
        this.grupoAsociadomodificarpthisGrupoAsociado = grupoAsociadomodificarpthisGrupoAsociado != null ? grupoAsociadomodificarpthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadomodificarpthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadomodificarpthisGrupoAsociadoInstance() == null || this.getGrupoAsociadomodificarpthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_MODIFICAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_MODIFICAR_GRUPOASOCIADOMODIFICARPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_MODIFICAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_MODIFICAR_GRUPOASOCIADOMODIFICARPTHISGRUPOASOCIADO);
        }
    }

    @JsonIgnore
    public Long getGrupoAsociadomodificarpDisminucionCarencia() {
        return this.grupoAsociadomodificarpDisminucionCarencia;
    }
    
    public void setGrupoAsociadomodificarpDisminucionCarencia(Long grupoAsociadomodificarpDisminucionCarencia) {
        this.grupoAsociadomodificarpDisminucionCarencia = grupoAsociadomodificarpDisminucionCarencia;
    }

    private void checkGrupoAsociadomodificarpDisminucionCarencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadomodificarpDisminucionCarencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_MODIFICAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_MODIFICAR_GRUPOASOCIADOMODIFICARPDISMINUCIONCARENCIA, GrupoAsociadoConstants.SERV_ALIAS_MODIFICAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_MODIFICAR_GRUPOASOCIADOMODIFICARPDISMINUCIONCARENCIA);
        }
    }
}
