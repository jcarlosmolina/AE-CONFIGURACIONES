package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class TSINCRONIZARRequest {

    @JsonProperty("p_thisplan_oid")
    private PlanesOid planesTSINCRONIZARpthisPlan;
    
    @JsonIgnore
    private Planes planesTSINCRONIZARpthisPlanInstance;

    @JsonProperty("pt_p_nombre")
    private String planesTSINCRONIZARptpNombre;

    @JsonProperty("pt_p_evcproducto_oid")
    private ProductosOid planesTSINCRONIZARptpevcProducto;
    
    @JsonIgnore
    private Productos planesTSINCRONIZARptpevcProductoInstance;

    @JsonProperty("p_codigolegal")
    private String planesTSINCRONIZARpCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanesTSINCRONIZARpthisPlan();
        checkPlanesTSINCRONIZARptpNombre();
        checkPlanesTSINCRONIZARptpevcProducto();
        checkPlanesTSINCRONIZARpCodigoLegal();
    }

    @JsonIgnore
    public PlanesOid getPlanesTSINCRONIZARpthisPlan() {
        return this.planesTSINCRONIZARpthisPlan;
    }
    
    public void setPlanesTSINCRONIZARpthisPlan(PlanesOid planesTSINCRONIZARpthisPlan) {
        this.planesTSINCRONIZARpthisPlan = planesTSINCRONIZARpthisPlan;
    }

    @JsonIgnore
    public Planes getPlanesTSINCRONIZARpthisPlanInstance() {
        return this.planesTSINCRONIZARpthisPlanInstance;
    }
    
    public void setPlanesTSINCRONIZARpthisPlanInstance(Planes planesTSINCRONIZARpthisPlan) {
        this.planesTSINCRONIZARpthisPlanInstance = planesTSINCRONIZARpthisPlan;
        this.planesTSINCRONIZARpthisPlan = planesTSINCRONIZARpthisPlan != null ? planesTSINCRONIZARpthisPlan.getOid() : null;
    }

    private void checkPlanesTSINCRONIZARpthisPlan() throws NotNullArgumentException {
        if(this.getPlanesTSINCRONIZARpthisPlanInstance() == null || this.getPlanesTSINCRONIZARpthisPlanInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_TSINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_TSINCRONIZAR_PLANESTSINCRONIZARPTHISPLAN, PlanesConstants.SERV_ALIAS_TSINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_TSINCRONIZAR_PLANESTSINCRONIZARPTHISPLAN);
        }
    }

    @JsonIgnore
    public String getPlanesTSINCRONIZARptpNombre() {
        return this.planesTSINCRONIZARptpNombre;
    }
    
    public void setPlanesTSINCRONIZARptpNombre(String planesTSINCRONIZARptpNombre) {
        this.planesTSINCRONIZARptpNombre = planesTSINCRONIZARptpNombre;
    }

    private void checkPlanesTSINCRONIZARptpNombre() throws NotNullArgumentException {
        if(this.getPlanesTSINCRONIZARptpNombre() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_TSINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_TSINCRONIZAR_PLANESTSINCRONIZARPTPNOMBRE, PlanesConstants.SERV_ALIAS_TSINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_TSINCRONIZAR_PLANESTSINCRONIZARPTPNOMBRE);
        }
    }

    @JsonIgnore
    public ProductosOid getPlanesTSINCRONIZARptpevcProducto() {
        return this.planesTSINCRONIZARptpevcProducto;
    }
    
    public void setPlanesTSINCRONIZARptpevcProducto(ProductosOid planesTSINCRONIZARptpevcProducto) {
        this.planesTSINCRONIZARptpevcProducto = planesTSINCRONIZARptpevcProducto;
    }

    @JsonIgnore
    public Productos getPlanesTSINCRONIZARptpevcProductoInstance() {
        return this.planesTSINCRONIZARptpevcProductoInstance;
    }
    
    public void setPlanesTSINCRONIZARptpevcProductoInstance(Productos planesTSINCRONIZARptpevcProducto) {
        this.planesTSINCRONIZARptpevcProductoInstance = planesTSINCRONIZARptpevcProducto;
        this.planesTSINCRONIZARptpevcProducto = planesTSINCRONIZARptpevcProducto != null ? planesTSINCRONIZARptpevcProducto.getOid() : null;
    }

    private void checkPlanesTSINCRONIZARptpevcProducto() throws NotNullArgumentException {
        if(this.getPlanesTSINCRONIZARptpevcProductoInstance() == null || this.getPlanesTSINCRONIZARptpevcProductoInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_TSINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_TSINCRONIZAR_PLANESTSINCRONIZARPTPEVCPRODUCTO, PlanesConstants.SERV_ALIAS_TSINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_TSINCRONIZAR_PLANESTSINCRONIZARPTPEVCPRODUCTO);
        }
    }

    @JsonIgnore
    public String getPlanesTSINCRONIZARpCodigoLegal() {
        return this.planesTSINCRONIZARpCodigoLegal;
    }
    
    public void setPlanesTSINCRONIZARpCodigoLegal(String planesTSINCRONIZARpCodigoLegal) {
        this.planesTSINCRONIZARpCodigoLegal = planesTSINCRONIZARpCodigoLegal;
    }

    private void checkPlanesTSINCRONIZARpCodigoLegal() throws NotNullArgumentException {
        if(this.getPlanesTSINCRONIZARpCodigoLegal() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_TSINCRONIZAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_TSINCRONIZAR_PLANESTSINCRONIZARPCODIGOLEGAL, PlanesConstants.SERV_ALIAS_TSINCRONIZAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_TSINCRONIZAR_PLANESTSINCRONIZARPCODIGOLEGAL);
        }
    }
}
