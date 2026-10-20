package com.keralty.aeconfiguraciones.viewmodel.globalservices.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GlobalServiceConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Productos;

public class TGSINC1PLANAUXRequest {

    @JsonProperty("pt_p_agrproducto_oid")
    private ProductosOid tGSINC1PLANAUXptpagrProducto;
    
    @JsonIgnore
    private Productos tGSINC1PLANAUXptpagrProductoInstance;

    @JsonProperty("pt_p_atrcodigo")
    private Long tGSINC1PLANAUXptpatrCodigo;

    @JsonProperty("pt_p_atrnombre")
    private String tGSINC1PLANAUXptpatrNombre;

    @JsonProperty("pt_p_atrcodigolegal")
    private String tGSINC1PLANAUXptpatrCodigoLegal;

    public void checkArguments() throws NotNullArgumentException {
        checkTGSINC1PLANAUXptpagrProducto();
        checkTGSINC1PLANAUXptpatrCodigo();
        checkTGSINC1PLANAUXptpatrNombre();
        checkTGSINC1PLANAUXptpatrCodigoLegal();
    }

    @JsonIgnore
    public ProductosOid getTGSINC1PLANAUXptpagrProducto() {
        return this.tGSINC1PLANAUXptpagrProducto;
    }
    
    public void setTGSINC1PLANAUXptpagrProducto(ProductosOid tGSINC1PLANAUXptpagrProducto) {
        this.tGSINC1PLANAUXptpagrProducto = tGSINC1PLANAUXptpagrProducto;
    }

    @JsonIgnore
    public Productos getTGSINC1PLANAUXptpagrProductoInstance() {
        return this.tGSINC1PLANAUXptpagrProductoInstance;
    }
    
    public void setTGSINC1PLANAUXptpagrProductoInstance(Productos tGSINC1PLANAUXptpagrProducto) {
        this.tGSINC1PLANAUXptpagrProductoInstance = tGSINC1PLANAUXptpagrProducto;
        this.tGSINC1PLANAUXptpagrProducto = tGSINC1PLANAUXptpagrProducto != null ? tGSINC1PLANAUXptpagrProducto.getOid() : null;
    }

    private void checkTGSINC1PLANAUXptpagrProducto() throws NotNullArgumentException {
        if(this.getTGSINC1PLANAUXptpagrProductoInstance() == null || this.getTGSINC1PLANAUXptpagrProductoInstance().isNull()) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLANAUX_TGSINC1PLANAUXPTPAGRPRODUCTO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLANAUX_TGSINC1PLANAUXPTPAGRPRODUCTO);
        }
    }

    @JsonIgnore
    public Long getTGSINC1PLANAUXptpatrCodigo() {
        return this.tGSINC1PLANAUXptpatrCodigo;
    }
    
    public void setTGSINC1PLANAUXptpatrCodigo(Long tGSINC1PLANAUXptpatrCodigo) {
        this.tGSINC1PLANAUXptpatrCodigo = tGSINC1PLANAUXptpatrCodigo;
    }

    private void checkTGSINC1PLANAUXptpatrCodigo() throws NotNullArgumentException {
        if(this.getTGSINC1PLANAUXptpatrCodigo() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRCODIGO, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getTGSINC1PLANAUXptpatrNombre() {
        return this.tGSINC1PLANAUXptpatrNombre;
    }
    
    public void setTGSINC1PLANAUXptpatrNombre(String tGSINC1PLANAUXptpatrNombre) {
        this.tGSINC1PLANAUXptpatrNombre = tGSINC1PLANAUXptpatrNombre;
    }

    private void checkTGSINC1PLANAUXptpatrNombre() throws NotNullArgumentException {
        if(this.getTGSINC1PLANAUXptpatrNombre() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRNOMBRE, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRNOMBRE);
        }
    }

    @JsonIgnore
    public String getTGSINC1PLANAUXptpatrCodigoLegal() {
        return this.tGSINC1PLANAUXptpatrCodigoLegal;
    }
    
    public void setTGSINC1PLANAUXptpatrCodigoLegal(String tGSINC1PLANAUXptpatrCodigoLegal) {
        this.tGSINC1PLANAUXptpatrCodigoLegal = tGSINC1PLANAUXptpatrCodigoLegal;
    }

    private void checkTGSINC1PLANAUXptpatrCodigoLegal() throws NotNullArgumentException {
        if(this.getTGSINC1PLANAUXptpatrCodigoLegal() == null) {
            throw new NotNullArgumentException(GlobalServiceConstants.G_SERV_ID_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ID_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRCODIGOLEGAL, GlobalServiceConstants.G_SERV_ALIAS_TGSINC1PLANAUX, GlobalServiceConstants.GS_ARG_ALIAS_TGSINC1PLANAUX_TGSINC1PLANAUXPTPATRCODIGOLEGAL);
        }
    }
}
