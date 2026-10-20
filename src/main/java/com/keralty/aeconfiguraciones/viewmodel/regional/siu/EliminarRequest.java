package com.keralty.aeconfiguraciones.viewmodel.regional.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.RegionalConstants;
import com.keralty.aeconfiguraciones.persistence.oid.RegionalOid;
import com.keralty.aeconfiguraciones.persistence.Regional;

public class EliminarRequest {

    @JsonProperty("p_thisregional_oid")
    private RegionalOid regionaleliminarpthisRegional;
    
    @JsonIgnore
    private Regional regionaleliminarpthisRegionalInstance;

    @JsonProperty("preconditionId")
    private String preconditionId;

    public void checkArguments() throws NotNullArgumentException {
        checkRegionaleliminarpthisRegional();
    }

    @JsonIgnore
    public RegionalOid getRegionaleliminarpthisRegional() {
        return this.regionaleliminarpthisRegional;
    }
    
    public void setRegionaleliminarpthisRegional(RegionalOid regionaleliminarpthisRegional) {
        this.regionaleliminarpthisRegional = regionaleliminarpthisRegional;
    }

    @JsonIgnore
    public Regional getRegionaleliminarpthisRegionalInstance() {
        return this.regionaleliminarpthisRegionalInstance;
    }
    
    public void setRegionaleliminarpthisRegionalInstance(Regional regionaleliminarpthisRegional) {
        this.regionaleliminarpthisRegionalInstance = regionaleliminarpthisRegional;
        this.regionaleliminarpthisRegional = regionaleliminarpthisRegional != null ? regionaleliminarpthisRegional.getOid() : null;
    }

    private void checkRegionaleliminarpthisRegional() throws NotNullArgumentException {
        if(this.getRegionaleliminarpthisRegionalInstance() == null || this.getRegionaleliminarpthisRegionalInstance().isNull()) {
            throw new NotNullArgumentException(RegionalConstants.SERV_ID_ELIMINAR, RegionalConstants.CLASS_ID, RegionalConstants.ARG_ID_ELIMINAR_REGIONALELIMINARPTHISREGIONAL, RegionalConstants.SERV_ALIAS_ELIMINAR, RegionalConstants.CLASS_ALIAS, RegionalConstants.ARG_ALIAS_ELIMINAR_REGIONALELIMINARPTHISREGIONAL);
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
