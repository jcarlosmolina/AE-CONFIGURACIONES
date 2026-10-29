package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class SincronizarRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadosincronizarpthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadosincronizarpthisGrupoAsociadoInstance;

    @JsonProperty("p_descripcion")
    private String grupoAsociadosincronizarpDescripcion;

    @JsonProperty("p_finivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadosincronizarpFIniVigencia;

    @JsonProperty("p_ffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadosincronizarpFFinVigencia;

    @JsonProperty("p_estado")
    private Long grupoAsociadosincronizarpEstado;

    @JsonProperty("p_codtarifacore")
    private String grupoAsociadosincronizarpCodTarifaCORE;

    @JsonProperty("p_codigolegal")
    private String grupoAsociadosincronizarpCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadosincronizarpthisGrupoAsociado();
        checkGrupoAsociadosincronizarpDescripcion();
        checkGrupoAsociadosincronizarpFIniVigencia();
        checkGrupoAsociadosincronizarpFFinVigencia();
        checkGrupoAsociadosincronizarpEstado();
        checkGrupoAsociadosincronizarpCodigoLegal();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadosincronizarpthisGrupoAsociado() {
        return this.grupoAsociadosincronizarpthisGrupoAsociado;
    }
    
    public void setGrupoAsociadosincronizarpthisGrupoAsociado(GrupoAsociadoOid grupoAsociadosincronizarpthisGrupoAsociado) {
        this.grupoAsociadosincronizarpthisGrupoAsociado = grupoAsociadosincronizarpthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadosincronizarpthisGrupoAsociadoInstance() {
        return this.grupoAsociadosincronizarpthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadosincronizarpthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadosincronizarpthisGrupoAsociado) {
        this.grupoAsociadosincronizarpthisGrupoAsociadoInstance = grupoAsociadosincronizarpthisGrupoAsociado;
        this.grupoAsociadosincronizarpthisGrupoAsociado = grupoAsociadosincronizarpthisGrupoAsociado != null ? grupoAsociadosincronizarpthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadosincronizarpthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpthisGrupoAsociadoInstance() == null || this.getGrupoAsociadosincronizarpthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPTHISGRUPOASOCIADO);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadosincronizarpDescripcion() {
        return this.grupoAsociadosincronizarpDescripcion;
    }
    
    public void setGrupoAsociadosincronizarpDescripcion(String grupoAsociadosincronizarpDescripcion) {
        this.grupoAsociadosincronizarpDescripcion = grupoAsociadosincronizarpDescripcion;
    }

    private void checkGrupoAsociadosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPDESCRIPCION, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadosincronizarpFIniVigencia() {
        return this.grupoAsociadosincronizarpFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadosincronizarpFIniVigencia(Date grupoAsociadosincronizarpFIniVigencia) {
        this.grupoAsociadosincronizarpFIniVigencia = grupoAsociadosincronizarpFIniVigencia;
    }

    private void checkGrupoAsociadosincronizarpFIniVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpFIniVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPFINIVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadosincronizarpFFinVigencia() {
        return this.grupoAsociadosincronizarpFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadosincronizarpFFinVigencia(Date grupoAsociadosincronizarpFFinVigencia) {
        this.grupoAsociadosincronizarpFFinVigencia = grupoAsociadosincronizarpFFinVigencia;
    }

    private void checkGrupoAsociadosincronizarpFFinVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpFFinVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPFFINVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getGrupoAsociadosincronizarpEstado() {
        return this.grupoAsociadosincronizarpEstado;
    }
    
    public void setGrupoAsociadosincronizarpEstado(Long grupoAsociadosincronizarpEstado) {
        this.grupoAsociadosincronizarpEstado = grupoAsociadosincronizarpEstado;
    }

    private void checkGrupoAsociadosincronizarpEstado() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpEstado() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPESTADO, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPESTADO);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadosincronizarpCodTarifaCORE() {
        return this.grupoAsociadosincronizarpCodTarifaCORE;
    }
    
    public void setGrupoAsociadosincronizarpCodTarifaCORE(String grupoAsociadosincronizarpCodTarifaCORE) {
        this.grupoAsociadosincronizarpCodTarifaCORE = grupoAsociadosincronizarpCodTarifaCORE;
    }

    @JsonIgnore
    public String getGrupoAsociadosincronizarpCodigoLegal() {
        return this.grupoAsociadosincronizarpCodigoLegal;
    }
    
    public void setGrupoAsociadosincronizarpCodigoLegal(String grupoAsociadosincronizarpCodigoLegal) {
        this.grupoAsociadosincronizarpCodigoLegal = grupoAsociadosincronizarpCodigoLegal;
    }

    private void checkGrupoAsociadosincronizarpCodigoLegal() throws NotNullArgumentException {
        if(this.getGrupoAsociadosincronizarpCodigoLegal() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPCODIGOLEGAL, GrupoAsociadoConstants.SERV_ALIAS_SINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_SINCRONIZAR_GRUPOASOCIADOSINCRONIZARPCODIGOLEGAL);
        }
    }
}
