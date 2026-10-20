package com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.CausaExclusionConstants;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class CrearRequest {

    @JsonProperty("p_agrnovedad_oid")
    private NovedadOid causaExclusioncrearpagrNovedad;
    
    @JsonIgnore
    private Novedad causaExclusioncrearpagrNovedadInstance;

    @JsonProperty("p_atraliascausa")
    private String causaExclusioncrearpatrAliasCausa;

    @JsonProperty("p_atrdescripcion")
    private String causaExclusioncrearpatrDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkCausaExclusioncrearpagrNovedad();
        checkCausaExclusioncrearpatrAliasCausa();
        checkCausaExclusioncrearpatrDescripcion();
    }

    @JsonIgnore
    public NovedadOid getCausaExclusioncrearpagrNovedad() {
        return this.causaExclusioncrearpagrNovedad;
    }
    
    public void setCausaExclusioncrearpagrNovedad(NovedadOid causaExclusioncrearpagrNovedad) {
        this.causaExclusioncrearpagrNovedad = causaExclusioncrearpagrNovedad;
    }

    @JsonIgnore
    public Novedad getCausaExclusioncrearpagrNovedadInstance() {
        return this.causaExclusioncrearpagrNovedadInstance;
    }
    
    public void setCausaExclusioncrearpagrNovedadInstance(Novedad causaExclusioncrearpagrNovedad) {
        this.causaExclusioncrearpagrNovedadInstance = causaExclusioncrearpagrNovedad;
        this.causaExclusioncrearpagrNovedad = causaExclusioncrearpagrNovedad != null ? causaExclusioncrearpagrNovedad.getOid() : null;
    }

    private void checkCausaExclusioncrearpagrNovedad() throws NotNullArgumentException {
        if(this.getCausaExclusioncrearpagrNovedadInstance() == null || this.getCausaExclusioncrearpagrNovedadInstance().isNull()) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_CREAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_CREAR_CAUSAEXCLUSIONCREARPAGRNOVEDAD, CausaExclusionConstants.SERV_ALIAS_CREAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_CREAR_CAUSAEXCLUSIONCREARPAGRNOVEDAD);
        }
    }

    @JsonIgnore
    public String getCausaExclusioncrearpatrAliasCausa() {
        return this.causaExclusioncrearpatrAliasCausa;
    }
    
    public void setCausaExclusioncrearpatrAliasCausa(String causaExclusioncrearpatrAliasCausa) {
        this.causaExclusioncrearpatrAliasCausa = causaExclusioncrearpatrAliasCausa;
    }

    private void checkCausaExclusioncrearpatrAliasCausa() throws NotNullArgumentException {
        if(this.getCausaExclusioncrearpatrAliasCausa() == null) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_CREAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_CREAR_CAUSAEXCLUSIONCREARPATRALIASCAUSA, CausaExclusionConstants.SERV_ALIAS_CREAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_CREAR_CAUSAEXCLUSIONCREARPATRALIASCAUSA);
        }
    }

    @JsonIgnore
    public String getCausaExclusioncrearpatrDescripcion() {
        return this.causaExclusioncrearpatrDescripcion;
    }
    
    public void setCausaExclusioncrearpatrDescripcion(String causaExclusioncrearpatrDescripcion) {
        this.causaExclusioncrearpatrDescripcion = causaExclusioncrearpatrDescripcion;
    }

    private void checkCausaExclusioncrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getCausaExclusioncrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(CausaExclusionConstants.SERV_ID_CREAR, CausaExclusionConstants.CLASS_ID, CausaExclusionConstants.ARG_ID_CREAR_CAUSAEXCLUSIONCREARPATRDESCRIPCION, CausaExclusionConstants.SERV_ALIAS_CREAR, CausaExclusionConstants.CLASS_ALIAS, CausaExclusionConstants.ARG_ALIAS_CREAR_CAUSAEXCLUSIONCREARPATRDESCRIPCION);
        }
    }
}
