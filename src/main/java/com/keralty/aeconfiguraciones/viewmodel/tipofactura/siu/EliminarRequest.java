package com.keralty.aeconfiguraciones.viewmodel.tipofactura.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TipoFacturaConstants;
import com.keralty.aeconfiguraciones.persistence.TipoFactura;

public class EliminarRequest {

    @JsonProperty("p_thistipofactura_json")
    private String tipoFacturaeliminarpthisTipoFactura;
    
    @JsonIgnore
    private TipoFactura tipoFacturaeliminarpthisTipoFacturaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTipoFacturaeliminarpthisTipoFactura();
    }

    @JsonIgnore
    public String getTipoFacturaeliminarpthisTipoFactura() {
        return this.tipoFacturaeliminarpthisTipoFactura;
    }
    
    public void setTipoFacturaeliminarpthisTipoFactura(String tipoFacturaeliminarpthisTipoFactura) {
        this.tipoFacturaeliminarpthisTipoFactura = tipoFacturaeliminarpthisTipoFactura;
    }

    @JsonIgnore
    public TipoFactura getTipoFacturaeliminarpthisTipoFacturaInstance() {
        return this.tipoFacturaeliminarpthisTipoFacturaInstance;
    }
    
    public void setTipoFacturaeliminarpthisTipoFacturaInstance(TipoFactura tipoFacturaeliminarpthisTipoFactura) {
        this.tipoFacturaeliminarpthisTipoFacturaInstance = tipoFacturaeliminarpthisTipoFactura;
        this.tipoFacturaeliminarpthisTipoFactura = tipoFacturaeliminarpthisTipoFactura != null ? tipoFacturaeliminarpthisTipoFactura.getOid().toJson() : "";
    }

    private void checkTipoFacturaeliminarpthisTipoFactura() throws NotNullArgumentException {
        if(this.getTipoFacturaeliminarpthisTipoFacturaInstance() == null || this.getTipoFacturaeliminarpthisTipoFacturaInstance().isNull()) {
            throw new NotNullArgumentException(TipoFacturaConstants.SERV_ID_ELIMINAR, TipoFacturaConstants.CLASS_ID, TipoFacturaConstants.ARG_ID_ELIMINAR_TIPOFACTURAELIMINARPTHISTIPOFACTURA, TipoFacturaConstants.SERV_ALIAS_ELIMINAR, TipoFacturaConstants.CLASS_ALIAS, TipoFacturaConstants.ARG_ALIAS_ELIMINAR_TIPOFACTURAELIMINARPTHISTIPOFACTURA);
        }
    }
}
