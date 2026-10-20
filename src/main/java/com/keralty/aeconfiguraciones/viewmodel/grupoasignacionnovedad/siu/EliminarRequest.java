package com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;

public class EliminarRequest {

    @JsonProperty("p_thisgrupoasignacionnovedad_oid")
    private GrupoAsignacionNovedadOid grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad;
    
    @JsonIgnore
    private GrupoAsignacionNovedad grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad();
    }

    @JsonIgnore
    public GrupoAsignacionNovedadOid getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad() {
        return this.grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad;
    }
    
    public void setGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad(GrupoAsignacionNovedadOid grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad = grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad;
    }

    @JsonIgnore
    public GrupoAsignacionNovedad getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance() {
        return this.grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance;
    }
    
    public void setGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance(GrupoAsignacionNovedad grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad) {
        this.grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance = grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad;
        this.grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad = grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad != null ? grupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad.getOid() : null;
    }

    private void checkGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedad() throws NotNullArgumentException {
        if(this.getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance() == null || this.getGrupoAsignacionNovedadeliminarpthisGrupoAsignacionNovedadInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsignacionNovedadConstants.SERV_ID_ELIMINAR, GrupoAsignacionNovedadConstants.CLASS_ID, GrupoAsignacionNovedadConstants.ARG_ID_ELIMINAR_GRUPOASIGNACIONNOVEDADELIMINARPTHISGRUPOASIGNACIONNOVEDAD, GrupoAsignacionNovedadConstants.SERV_ALIAS_ELIMINAR, GrupoAsignacionNovedadConstants.CLASS_ALIAS, GrupoAsignacionNovedadConstants.ARG_ALIAS_ELIMINAR_GRUPOASIGNACIONNOVEDADELIMINARPTHISGRUPOASIGNACIONNOVEDAD);
        }
    }
}
