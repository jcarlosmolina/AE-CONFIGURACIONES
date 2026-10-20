package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class EliminarRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioeliminarpthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioeliminarpthisMunicipioInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioeliminarpthisMunicipio();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioeliminarpthisMunicipio() {
        return this.municipioeliminarpthisMunicipio;
    }
    
    public void setMunicipioeliminarpthisMunicipio(MunicipioOid municipioeliminarpthisMunicipio) {
        this.municipioeliminarpthisMunicipio = municipioeliminarpthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioeliminarpthisMunicipioInstance() {
        return this.municipioeliminarpthisMunicipioInstance;
    }
    
    public void setMunicipioeliminarpthisMunicipioInstance(Municipio municipioeliminarpthisMunicipio) {
        this.municipioeliminarpthisMunicipioInstance = municipioeliminarpthisMunicipio;
        this.municipioeliminarpthisMunicipio = municipioeliminarpthisMunicipio != null ? municipioeliminarpthisMunicipio.getOid() : null;
    }

    private void checkMunicipioeliminarpthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioeliminarpthisMunicipioInstance() == null || this.getMunicipioeliminarpthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_ELIMINAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_ELIMINAR_MUNICIPIOELIMINARPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_ELIMINAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_ELIMINAR_MUNICIPIOELIMINARPTHISMUNICIPIO);
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
