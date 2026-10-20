package com.keralty.aeconfiguraciones.viewmodel.planes.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class CrearRequest {

    @JsonProperty("p_agrproductot_oid")
    private ProductosOid planescrearpagrProductoT;
    
    @JsonIgnore
    private Productos planescrearpagrProductoTInstance;

    @JsonProperty("p_atrplan")
    private Long planescrearpatrPlan;

    @JsonProperty("p_atrnombre")
    private String planescrearpatrNombre;

    @JsonProperty("p_atresbancorep")
    private Boolean planescrearpatrEsBancoRep;

    @JsonProperty("p_atrcodigolegal")
    private String planescrearpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkPlanescrearpagrProductoT();
        checkPlanescrearpatrPlan();
        checkPlanescrearpatrNombre();
        checkPlanescrearpatrEsBancoRep();
        checkPlanescrearpatrCodigoLegal();
    }

    @JsonIgnore
    public ProductosOid getPlanescrearpagrProductoT() {
        return this.planescrearpagrProductoT;
    }
    
    public void setPlanescrearpagrProductoT(ProductosOid planescrearpagrProductoT) {
        this.planescrearpagrProductoT = planescrearpagrProductoT;
    }

    @JsonIgnore
    public Productos getPlanescrearpagrProductoTInstance() {
        return this.planescrearpagrProductoTInstance;
    }
    
    public void setPlanescrearpagrProductoTInstance(Productos planescrearpagrProductoT) {
        this.planescrearpagrProductoTInstance = planescrearpagrProductoT;
        this.planescrearpagrProductoT = planescrearpagrProductoT != null ? planescrearpagrProductoT.getOid() : null;
    }

    private void checkPlanescrearpagrProductoT() throws NotNullArgumentException {
        if(this.getPlanescrearpagrProductoTInstance() == null || this.getPlanescrearpagrProductoTInstance().isNull()) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CREAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CREAR_PLANESCREARPAGRPRODUCTOT, PlanesConstants.SERV_ALIAS_CREAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CREAR_PLANESCREARPAGRPRODUCTOT);
        }
    }

    @JsonIgnore
    public Long getPlanescrearpatrPlan() {
        return this.planescrearpatrPlan;
    }
    
    public void setPlanescrearpatrPlan(Long planescrearpatrPlan) {
        this.planescrearpatrPlan = planescrearpatrPlan;
    }

    private void checkPlanescrearpatrPlan() throws NotNullArgumentException {
        if(this.getPlanescrearpatrPlan() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CREAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CREAR_PLANESCREARPATRPLAN, PlanesConstants.SERV_ALIAS_CREAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CREAR_PLANESCREARPATRPLAN);
        }
    }

    @JsonIgnore
    public String getPlanescrearpatrNombre() {
        return this.planescrearpatrNombre;
    }
    
    public void setPlanescrearpatrNombre(String planescrearpatrNombre) {
        this.planescrearpatrNombre = planescrearpatrNombre;
    }

    private void checkPlanescrearpatrNombre() throws NotNullArgumentException {
        if(this.getPlanescrearpatrNombre() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CREAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CREAR_PLANESCREARPATRNOMBRE, PlanesConstants.SERV_ALIAS_CREAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CREAR_PLANESCREARPATRNOMBRE);
        }
    }

    @JsonIgnore
    public Boolean getPlanescrearpatrEsBancoRep() {
        return this.planescrearpatrEsBancoRep;
    }
    
    public void setPlanescrearpatrEsBancoRep(Boolean planescrearpatrEsBancoRep) {
        this.planescrearpatrEsBancoRep = planescrearpatrEsBancoRep;
    }

    private void checkPlanescrearpatrEsBancoRep() throws NotNullArgumentException {
        if(this.getPlanescrearpatrEsBancoRep() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CREAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CREAR_PLANESCREARPATRESBANCOREP, PlanesConstants.SERV_ALIAS_CREAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CREAR_PLANESCREARPATRESBANCOREP);
        }
    }

    @JsonIgnore
    public String getPlanescrearpatrCodigoLegal() {
        return this.planescrearpatrCodigoLegal;
    }
    
    public void setPlanescrearpatrCodigoLegal(String planescrearpatrCodigoLegal) {
        this.planescrearpatrCodigoLegal = planescrearpatrCodigoLegal;
    }

    private void checkPlanescrearpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getPlanescrearpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(PlanesConstants.SERV_ID_CREAR, PlanesConstants.CLASS_ID, PlanesConstants.ARG_ID_CREAR_PLANESCREARPATRCODIGOLEGAL, PlanesConstants.SERV_ALIAS_CREAR, PlanesConstants.CLASS_ALIAS, PlanesConstants.ARG_ALIAS_CREAR_PLANESCREARPATRCODIGOLEGAL);
        }
    }
}
