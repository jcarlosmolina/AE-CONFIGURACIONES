package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneDeserializer;
import com.keralty.aeconfiguraciones.viewmodel.SqlDateWithoutTimezoneSerializer;
import java.sql.Date;

public class TSINCRONIZARRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadoTSINCRONIZARpthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance;

    @JsonProperty("pt_p_descripcion")
    private String grupoAsociadoTSINCRONIZARptpDescripcion;

    @JsonProperty("pt_p_finivigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadoTSINCRONIZARptpFIniVigencia;

    @JsonProperty("pt_p_ffinvigencia")
    @JsonSerialize(using = SqlDateWithoutTimezoneSerializer.class)
    private Date grupoAsociadoTSINCRONIZARptpFFinVigencia;

    @JsonProperty("pt_p_estado")
    private Long grupoAsociadoTSINCRONIZARptpEstado;

    @JsonProperty("pt_p_evcplan_oid")
    private PlanesOid grupoAsociadoTSINCRONIZARptpevcPlan;
    
    @JsonIgnore
    private Planes grupoAsociadoTSINCRONIZARptpevcPlanInstance;

    @JsonProperty("pt_p_evcproducto_oid")
    private ProductosOid grupoAsociadoTSINCRONIZARptpevcProducto;
    
    @JsonIgnore
    private Productos grupoAsociadoTSINCRONIZARptpevcProductoInstance;

    @JsonProperty("pt_codtarifacore")
    private String grupoAsociadoTSINCRONIZARptCodTarifaCORE;

    @JsonProperty("pt_p_codigolegal")
    private String grupoAsociadoTSINCRONIZARptpCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadoTSINCRONIZARpthisGrupoAsociado();
        checkGrupoAsociadoTSINCRONIZARptpDescripcion();
        checkGrupoAsociadoTSINCRONIZARptpFIniVigencia();
        checkGrupoAsociadoTSINCRONIZARptpFFinVigencia();
        checkGrupoAsociadoTSINCRONIZARptpEstado();
        checkGrupoAsociadoTSINCRONIZARptpevcPlan();
        checkGrupoAsociadoTSINCRONIZARptpevcProducto();
        checkGrupoAsociadoTSINCRONIZARptpCodigoLegal();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadoTSINCRONIZARpthisGrupoAsociado() {
        return this.grupoAsociadoTSINCRONIZARpthisGrupoAsociado;
    }
    
    public void setGrupoAsociadoTSINCRONIZARpthisGrupoAsociado(GrupoAsociadoOid grupoAsociadoTSINCRONIZARpthisGrupoAsociado) {
        this.grupoAsociadoTSINCRONIZARpthisGrupoAsociado = grupoAsociadoTSINCRONIZARpthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance() {
        return this.grupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadoTSINCRONIZARpthisGrupoAsociado) {
        this.grupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance = grupoAsociadoTSINCRONIZARpthisGrupoAsociado;
        this.grupoAsociadoTSINCRONIZARpthisGrupoAsociado = grupoAsociadoTSINCRONIZARpthisGrupoAsociado != null ? grupoAsociadoTSINCRONIZARpthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadoTSINCRONIZARpthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance() == null || this.getGrupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTHISGRUPOASOCIADO);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadoTSINCRONIZARptpDescripcion() {
        return this.grupoAsociadoTSINCRONIZARptpDescripcion;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpDescripcion(String grupoAsociadoTSINCRONIZARptpDescripcion) {
        this.grupoAsociadoTSINCRONIZARptpDescripcion = grupoAsociadoTSINCRONIZARptpDescripcion;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpDescripcion() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpDescripcion() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPDESCRIPCION, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPDESCRIPCION);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadoTSINCRONIZARptpFIniVigencia() {
        return this.grupoAsociadoTSINCRONIZARptpFIniVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadoTSINCRONIZARptpFIniVigencia(Date grupoAsociadoTSINCRONIZARptpFIniVigencia) {
        this.grupoAsociadoTSINCRONIZARptpFIniVigencia = grupoAsociadoTSINCRONIZARptpFIniVigencia;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpFIniVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpFIniVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPFINIVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPFINIVIGENCIA);
        }
    }

    @JsonIgnore
    public Date getGrupoAsociadoTSINCRONIZARptpFFinVigencia() {
        return this.grupoAsociadoTSINCRONIZARptpFFinVigencia;
    }
    
    @JsonDeserialize(using = SqlDateWithoutTimezoneDeserializer.class)
    public void setGrupoAsociadoTSINCRONIZARptpFFinVigencia(Date grupoAsociadoTSINCRONIZARptpFFinVigencia) {
        this.grupoAsociadoTSINCRONIZARptpFFinVigencia = grupoAsociadoTSINCRONIZARptpFFinVigencia;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpFFinVigencia() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpFFinVigencia() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPFFINVIGENCIA, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPFFINVIGENCIA);
        }
    }

    @JsonIgnore
    public Long getGrupoAsociadoTSINCRONIZARptpEstado() {
        return this.grupoAsociadoTSINCRONIZARptpEstado;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpEstado(Long grupoAsociadoTSINCRONIZARptpEstado) {
        this.grupoAsociadoTSINCRONIZARptpEstado = grupoAsociadoTSINCRONIZARptpEstado;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpEstado() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpEstado() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPESTADO, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPESTADO);
        }
    }

    @JsonIgnore
    public PlanesOid getGrupoAsociadoTSINCRONIZARptpevcPlan() {
        return this.grupoAsociadoTSINCRONIZARptpevcPlan;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpevcPlan(PlanesOid grupoAsociadoTSINCRONIZARptpevcPlan) {
        this.grupoAsociadoTSINCRONIZARptpevcPlan = grupoAsociadoTSINCRONIZARptpevcPlan;
    }

    @JsonIgnore
    public Planes getGrupoAsociadoTSINCRONIZARptpevcPlanInstance() {
        return this.grupoAsociadoTSINCRONIZARptpevcPlanInstance;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpevcPlanInstance(Planes grupoAsociadoTSINCRONIZARptpevcPlan) {
        this.grupoAsociadoTSINCRONIZARptpevcPlanInstance = grupoAsociadoTSINCRONIZARptpevcPlan;
        this.grupoAsociadoTSINCRONIZARptpevcPlan = grupoAsociadoTSINCRONIZARptpevcPlan != null ? grupoAsociadoTSINCRONIZARptpevcPlan.getOid() : null;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpevcPlan() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpevcPlanInstance() == null || this.getGrupoAsociadoTSINCRONIZARptpevcPlanInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPEVCPLAN, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPEVCPLAN);
        }
    }

    @JsonIgnore
    public ProductosOid getGrupoAsociadoTSINCRONIZARptpevcProducto() {
        return this.grupoAsociadoTSINCRONIZARptpevcProducto;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpevcProducto(ProductosOid grupoAsociadoTSINCRONIZARptpevcProducto) {
        this.grupoAsociadoTSINCRONIZARptpevcProducto = grupoAsociadoTSINCRONIZARptpevcProducto;
    }

    @JsonIgnore
    public Productos getGrupoAsociadoTSINCRONIZARptpevcProductoInstance() {
        return this.grupoAsociadoTSINCRONIZARptpevcProductoInstance;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpevcProductoInstance(Productos grupoAsociadoTSINCRONIZARptpevcProducto) {
        this.grupoAsociadoTSINCRONIZARptpevcProductoInstance = grupoAsociadoTSINCRONIZARptpevcProducto;
        this.grupoAsociadoTSINCRONIZARptpevcProducto = grupoAsociadoTSINCRONIZARptpevcProducto != null ? grupoAsociadoTSINCRONIZARptpevcProducto.getOid() : null;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpevcProducto() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpevcProductoInstance() == null || this.getGrupoAsociadoTSINCRONIZARptpevcProductoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPEVCPRODUCTO, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPEVCPRODUCTO);
        }
    }

    @JsonIgnore
    public String getGrupoAsociadoTSINCRONIZARptCodTarifaCORE() {
        return this.grupoAsociadoTSINCRONIZARptCodTarifaCORE;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptCodTarifaCORE(String grupoAsociadoTSINCRONIZARptCodTarifaCORE) {
        this.grupoAsociadoTSINCRONIZARptCodTarifaCORE = grupoAsociadoTSINCRONIZARptCodTarifaCORE;
    }

    @JsonIgnore
    public String getGrupoAsociadoTSINCRONIZARptpCodigoLegal() {
        return this.grupoAsociadoTSINCRONIZARptpCodigoLegal;
    }
    
    public void setGrupoAsociadoTSINCRONIZARptpCodigoLegal(String grupoAsociadoTSINCRONIZARptpCodigoLegal) {
        this.grupoAsociadoTSINCRONIZARptpCodigoLegal = grupoAsociadoTSINCRONIZARptpCodigoLegal;
    }

    private void checkGrupoAsociadoTSINCRONIZARptpCodigoLegal() throws NotNullArgumentException {
        if(this.getGrupoAsociadoTSINCRONIZARptpCodigoLegal() == null) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPCODIGOLEGAL, GrupoAsociadoConstants.SERV_ALIAS_TSINCRONIZAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_TSINCRONIZAR_GRUPOASOCIADOTSINCRONIZARPTPCODIGOLEGAL);
        }
    }
}
