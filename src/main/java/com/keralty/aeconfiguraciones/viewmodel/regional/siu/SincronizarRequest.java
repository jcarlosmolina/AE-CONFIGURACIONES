package com.keralty.aeconfiguraciones.viewmodel.regional.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.RegionalConstants;
import com.keralty.aeconfiguraciones.persistence.oid.RegionalOid;
import com.keralty.aeconfiguraciones.persistence.Regional;

public class SincronizarRequest {

    @JsonProperty("p_thisregional_oid")
    private RegionalOid regionalsincronizarpthisRegional;
    
    @JsonIgnore
    private Regional regionalsincronizarpthisRegionalInstance;

    @JsonProperty("p_descripcion")
    private String regionalsincronizarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkRegionalsincronizarpthisRegional();
        checkRegionalsincronizarpDescripcion();
    }

    @JsonIgnore
    public RegionalOid getRegionalsincronizarpthisRegional() {
        return this.regionalsincronizarpthisRegional;
    }
    
    public void setRegionalsincronizarpthisRegional(RegionalOid regionalsincronizarpthisRegional) {
        this.regionalsincronizarpthisRegional = regionalsincronizarpthisRegional;
    }

    @JsonIgnore
    public Regional getRegionalsincronizarpthisRegionalInstance() {
        return this.regionalsincronizarpthisRegionalInstance;
    }
    
    public void setRegionalsincronizarpthisRegionalInstance(Regional regionalsincronizarpthisRegional) {
        this.regionalsincronizarpthisRegionalInstance = regionalsincronizarpthisRegional;
        this.regionalsincronizarpthisRegional = regionalsincronizarpthisRegional != null ? regionalsincronizarpthisRegional.getOid() : null;
    }

    private void checkRegionalsincronizarpthisRegional() throws NotNullArgumentException {
        if(this.getRegionalsincronizarpthisRegionalInstance() == null || this.getRegionalsincronizarpthisRegionalInstance().isNull()) {
            throw new NotNullArgumentException(RegionalConstants.SERV_ID_SINCRONIZAR, RegionalConstants.CLASS_ID, RegionalConstants.ARG_ID_SINCRONIZAR_REGIONALSINCRONIZARPTHISREGIONAL, RegionalConstants.SERV_ALIAS_SINCRONIZAR, RegionalConstants.CLASS_ALIAS, RegionalConstants.ARG_ALIAS_SINCRONIZAR_REGIONALSINCRONIZARPTHISREGIONAL);
        }
    }

    @JsonIgnore
    public String getRegionalsincronizarpDescripcion() {
        return this.regionalsincronizarpDescripcion;
    }
    
    public void setRegionalsincronizarpDescripcion(String regionalsincronizarpDescripcion) {
        this.regionalsincronizarpDescripcion = regionalsincronizarpDescripcion;
    }

    private void checkRegionalsincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getRegionalsincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(RegionalConstants.SERV_ID_SINCRONIZAR, RegionalConstants.CLASS_ID, RegionalConstants.ARG_ID_SINCRONIZAR_REGIONALSINCRONIZARPDESCRIPCION, RegionalConstants.SERV_ALIAS_SINCRONIZAR, RegionalConstants.CLASS_ALIAS, RegionalConstants.ARG_ALIAS_SINCRONIZAR_REGIONALSINCRONIZARPDESCRIPCION);
        }
    }
}
