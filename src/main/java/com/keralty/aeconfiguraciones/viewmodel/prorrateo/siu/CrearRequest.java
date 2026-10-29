package com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;

public class CrearRequest {

    @JsonProperty("p_agrdominio_oid")
    private DominioOid prorrateocrearpagrDominio;
    
    @JsonIgnore
    private Dominio prorrateocrearpagrDominioInstance;

    @JsonProperty("p_atrdia")
    private Long prorrateocrearpatrDia;

    @JsonProperty("p_atrporcentaje")
    private Double prorrateocrearpatrPorcentaje;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkProrrateocrearpagrDominio();
        checkProrrateocrearpatrDia();
        checkProrrateocrearpatrPorcentaje();
    }

    @JsonIgnore
    public DominioOid getProrrateocrearpagrDominio() {
        return this.prorrateocrearpagrDominio;
    }
    
    public void setProrrateocrearpagrDominio(DominioOid prorrateocrearpagrDominio) {
        this.prorrateocrearpagrDominio = prorrateocrearpagrDominio;
    }

    @JsonIgnore
    public Dominio getProrrateocrearpagrDominioInstance() {
        return this.prorrateocrearpagrDominioInstance;
    }
    
    public void setProrrateocrearpagrDominioInstance(Dominio prorrateocrearpagrDominio) {
        this.prorrateocrearpagrDominioInstance = prorrateocrearpagrDominio;
        this.prorrateocrearpagrDominio = prorrateocrearpagrDominio != null ? prorrateocrearpagrDominio.getOid() : null;
    }

    private void checkProrrateocrearpagrDominio() throws NotNullArgumentException {
        if(this.getProrrateocrearpagrDominioInstance() == null || this.getProrrateocrearpagrDominioInstance().isNull()) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_CREAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_CREAR_PRORRATEOCREARPAGRDOMINIO, ProrrateoConstants.SERV_ALIAS_CREAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_CREAR_PRORRATEOCREARPAGRDOMINIO);
        }
    }

    @JsonIgnore
    public Long getProrrateocrearpatrDia() {
        return this.prorrateocrearpatrDia;
    }
    
    public void setProrrateocrearpatrDia(Long prorrateocrearpatrDia) {
        this.prorrateocrearpatrDia = prorrateocrearpatrDia;
    }

    private void checkProrrateocrearpatrDia() throws NotNullArgumentException {
        if(this.getProrrateocrearpatrDia() == null) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_CREAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_CREAR_PRORRATEOCREARPATRDIA, ProrrateoConstants.SERV_ALIAS_CREAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_CREAR_PRORRATEOCREARPATRDIA);
        }
    }

    @JsonIgnore
    public Double getProrrateocrearpatrPorcentaje() {
        return this.prorrateocrearpatrPorcentaje;
    }
    
    public void setProrrateocrearpatrPorcentaje(Double prorrateocrearpatrPorcentaje) {
        this.prorrateocrearpatrPorcentaje = prorrateocrearpatrPorcentaje;
    }

    private void checkProrrateocrearpatrPorcentaje() throws NotNullArgumentException {
        if(this.getProrrateocrearpatrPorcentaje() == null) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_CREAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_CREAR_PRORRATEOCREARPATRPORCENTAJE, ProrrateoConstants.SERV_ALIAS_CREAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_CREAR_PRORRATEOCREARPATRPORCENTAJE);
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
