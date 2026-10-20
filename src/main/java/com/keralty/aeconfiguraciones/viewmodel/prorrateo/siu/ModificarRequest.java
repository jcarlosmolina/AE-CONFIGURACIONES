package com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ProrrateoOid;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;

public class ModificarRequest {

    @JsonProperty("p_thisprorrateo_oid")
    private ProrrateoOid prorrateomodificarpthisProrrateo;
    
    @JsonIgnore
    private Prorrateo prorrateomodificarpthisProrrateoInstance;

    @JsonProperty("p_dia")
    private Long prorrateomodificarpDia;

    @JsonProperty("p_porcentaje")
    private Double prorrateomodificarpPorcentaje;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkProrrateomodificarpthisProrrateo();
        checkProrrateomodificarpDia();
        checkProrrateomodificarpPorcentaje();
    }

    @JsonIgnore
    public ProrrateoOid getProrrateomodificarpthisProrrateo() {
        return this.prorrateomodificarpthisProrrateo;
    }
    
    public void setProrrateomodificarpthisProrrateo(ProrrateoOid prorrateomodificarpthisProrrateo) {
        this.prorrateomodificarpthisProrrateo = prorrateomodificarpthisProrrateo;
    }

    @JsonIgnore
    public Prorrateo getProrrateomodificarpthisProrrateoInstance() {
        return this.prorrateomodificarpthisProrrateoInstance;
    }
    
    public void setProrrateomodificarpthisProrrateoInstance(Prorrateo prorrateomodificarpthisProrrateo) {
        this.prorrateomodificarpthisProrrateoInstance = prorrateomodificarpthisProrrateo;
        this.prorrateomodificarpthisProrrateo = prorrateomodificarpthisProrrateo != null ? prorrateomodificarpthisProrrateo.getOid() : null;
    }

    private void checkProrrateomodificarpthisProrrateo() throws NotNullArgumentException {
        if(this.getProrrateomodificarpthisProrrateoInstance() == null || this.getProrrateomodificarpthisProrrateoInstance().isNull()) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_MODIFICAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_MODIFICAR_PRORRATEOMODIFICARPTHISPRORRATEO, ProrrateoConstants.SERV_ALIAS_MODIFICAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_MODIFICAR_PRORRATEOMODIFICARPTHISPRORRATEO);
        }
    }

    @JsonIgnore
    public Long getProrrateomodificarpDia() {
        return this.prorrateomodificarpDia;
    }
    
    public void setProrrateomodificarpDia(Long prorrateomodificarpDia) {
        this.prorrateomodificarpDia = prorrateomodificarpDia;
    }

    private void checkProrrateomodificarpDia() throws NotNullArgumentException {
        if(this.getProrrateomodificarpDia() == null) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_MODIFICAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_MODIFICAR_PRORRATEOMODIFICARPDIA, ProrrateoConstants.SERV_ALIAS_MODIFICAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_MODIFICAR_PRORRATEOMODIFICARPDIA);
        }
    }

    @JsonIgnore
    public Double getProrrateomodificarpPorcentaje() {
        return this.prorrateomodificarpPorcentaje;
    }
    
    public void setProrrateomodificarpPorcentaje(Double prorrateomodificarpPorcentaje) {
        this.prorrateomodificarpPorcentaje = prorrateomodificarpPorcentaje;
    }

    private void checkProrrateomodificarpPorcentaje() throws NotNullArgumentException {
        if(this.getProrrateomodificarpPorcentaje() == null) {
            throw new NotNullArgumentException(ProrrateoConstants.SERV_ID_MODIFICAR, ProrrateoConstants.CLASS_ID, ProrrateoConstants.ARG_ID_MODIFICAR_PRORRATEOMODIFICARPPORCENTAJE, ProrrateoConstants.SERV_ALIAS_MODIFICAR, ProrrateoConstants.CLASS_ALIAS, ProrrateoConstants.ARG_ALIAS_MODIFICAR_PRORRATEOMODIFICARPPORCENTAJE);
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
