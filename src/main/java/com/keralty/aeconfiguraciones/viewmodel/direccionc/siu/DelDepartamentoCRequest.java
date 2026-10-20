package com.keralty.aeconfiguraciones.viewmodel.direccionc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DireccionCConstants;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionCOid;

public class DelDepartamentoCRequest {

    @JsonProperty("p_thisdireccionc_oid")
    private DireccionCOid direccionCDelDepartamentoCpthisDireccionC;
    
    @JsonIgnore
    private DireccionC direccionCDelDepartamentoCpthisDireccionCInstance;

    @JsonProperty("p_evcdepartamento_oid")
    private DepartamentoOid direccionCDelDepartamentoCpevcDepartamento;
    
    @JsonIgnore
    private Departamento direccionCDelDepartamentoCpevcDepartamentoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDireccionCDelDepartamentoCpthisDireccionC();
        checkDireccionCDelDepartamentoCpevcDepartamento();
    }

    @JsonIgnore
    public DireccionCOid getDireccionCDelDepartamentoCpthisDireccionC() {
        return this.direccionCDelDepartamentoCpthisDireccionC;
    }
    
    public void setDireccionCDelDepartamentoCpthisDireccionC(DireccionCOid direccionCDelDepartamentoCpthisDireccionC) {
        this.direccionCDelDepartamentoCpthisDireccionC = direccionCDelDepartamentoCpthisDireccionC;
    }

    @JsonIgnore
    public DireccionC getDireccionCDelDepartamentoCpthisDireccionCInstance() {
        return this.direccionCDelDepartamentoCpthisDireccionCInstance;
    }
    
    public void setDireccionCDelDepartamentoCpthisDireccionCInstance(DireccionC direccionCDelDepartamentoCpthisDireccionC) {
        this.direccionCDelDepartamentoCpthisDireccionCInstance = direccionCDelDepartamentoCpthisDireccionC;
        this.direccionCDelDepartamentoCpthisDireccionC = direccionCDelDepartamentoCpthisDireccionC != null ? direccionCDelDepartamentoCpthisDireccionC.getOid() : null;
    }

    private void checkDireccionCDelDepartamentoCpthisDireccionC() throws NotNullArgumentException {
        if(this.getDireccionCDelDepartamentoCpthisDireccionCInstance() == null || this.getDireccionCDelDepartamentoCpthisDireccionCInstance().isNull()) {
            throw new NotNullArgumentException(DireccionCConstants.SERV_ID_DELDEPARTAMENTOC, DireccionCConstants.CLASS_ID, DireccionCConstants.ARG_ID_DELDEPARTAMENTOC_DIRECCIONCDELDEPARTAMENTOCPTHISDIRECCIONC, DireccionCConstants.SERV_ALIAS_DELDEPARTAMENTOC, DireccionCConstants.CLASS_ALIAS, DireccionCConstants.ARG_ALIAS_DELDEPARTAMENTOC_DIRECCIONCDELDEPARTAMENTOCPTHISDIRECCIONC);
        }
    }

    @JsonIgnore
    public DepartamentoOid getDireccionCDelDepartamentoCpevcDepartamento() {
        return this.direccionCDelDepartamentoCpevcDepartamento;
    }
    
    public void setDireccionCDelDepartamentoCpevcDepartamento(DepartamentoOid direccionCDelDepartamentoCpevcDepartamento) {
        this.direccionCDelDepartamentoCpevcDepartamento = direccionCDelDepartamentoCpevcDepartamento;
    }

    @JsonIgnore
    public Departamento getDireccionCDelDepartamentoCpevcDepartamentoInstance() {
        return this.direccionCDelDepartamentoCpevcDepartamentoInstance;
    }
    
    public void setDireccionCDelDepartamentoCpevcDepartamentoInstance(Departamento direccionCDelDepartamentoCpevcDepartamento) {
        this.direccionCDelDepartamentoCpevcDepartamentoInstance = direccionCDelDepartamentoCpevcDepartamento;
        this.direccionCDelDepartamentoCpevcDepartamento = direccionCDelDepartamentoCpevcDepartamento != null ? direccionCDelDepartamentoCpevcDepartamento.getOid() : null;
    }

    private void checkDireccionCDelDepartamentoCpevcDepartamento() throws NotNullArgumentException {
        if(this.getDireccionCDelDepartamentoCpevcDepartamentoInstance() == null || this.getDireccionCDelDepartamentoCpevcDepartamentoInstance().isNull()) {
            throw new NotNullArgumentException(DireccionCConstants.SERV_ID_DELDEPARTAMENTOC, DireccionCConstants.CLASS_ID, DireccionCConstants.ARG_ID_DELDEPARTAMENTOC_DIRECCIONCDELDEPARTAMENTOCPEVCDEPARTAMENTO, DireccionCConstants.SERV_ALIAS_DELDEPARTAMENTOC, DireccionCConstants.CLASS_ALIAS, DireccionCConstants.ARG_ALIAS_DELDEPARTAMENTOC_DIRECCIONCDELDEPARTAMENTOCPEVCDEPARTAMENTO);
        }
    }
}
