package com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CuestionarioMedConstants;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioMedOid;

public class EliminarRequest {

    @JsonProperty("p_thiscuestionariomed_oid")
    private CuestionarioMedOid cuestionarioMedeliminarpthisCuestionarioMed;
    
    @JsonIgnore
    private CuestionarioMed cuestionarioMedeliminarpthisCuestionarioMedInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkCuestionarioMedeliminarpthisCuestionarioMed();
    }

    @JsonIgnore
    public CuestionarioMedOid getCuestionarioMedeliminarpthisCuestionarioMed() {
        return this.cuestionarioMedeliminarpthisCuestionarioMed;
    }
    
    public void setCuestionarioMedeliminarpthisCuestionarioMed(CuestionarioMedOid cuestionarioMedeliminarpthisCuestionarioMed) {
        this.cuestionarioMedeliminarpthisCuestionarioMed = cuestionarioMedeliminarpthisCuestionarioMed;
    }

    @JsonIgnore
    public CuestionarioMed getCuestionarioMedeliminarpthisCuestionarioMedInstance() {
        return this.cuestionarioMedeliminarpthisCuestionarioMedInstance;
    }
    
    public void setCuestionarioMedeliminarpthisCuestionarioMedInstance(CuestionarioMed cuestionarioMedeliminarpthisCuestionarioMed) {
        this.cuestionarioMedeliminarpthisCuestionarioMedInstance = cuestionarioMedeliminarpthisCuestionarioMed;
        this.cuestionarioMedeliminarpthisCuestionarioMed = cuestionarioMedeliminarpthisCuestionarioMed != null ? cuestionarioMedeliminarpthisCuestionarioMed.getOid() : null;
    }

    private void checkCuestionarioMedeliminarpthisCuestionarioMed() throws NotNullArgumentException {
        if(this.getCuestionarioMedeliminarpthisCuestionarioMedInstance() == null || this.getCuestionarioMedeliminarpthisCuestionarioMedInstance().isNull()) {
            throw new NotNullArgumentException(CuestionarioMedConstants.SERV_ID_ELIMINAR, CuestionarioMedConstants.CLASS_ID, CuestionarioMedConstants.ARG_ID_ELIMINAR_CUESTIONARIOMEDELIMINARPTHISCUESTIONARIOMED, CuestionarioMedConstants.SERV_ALIAS_ELIMINAR, CuestionarioMedConstants.CLASS_ALIAS, CuestionarioMedConstants.ARG_ALIAS_ELIMINAR_CUESTIONARIOMEDELIMINARPTHISCUESTIONARIOMED);
        }
    }
}
