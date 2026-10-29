package com.keralty.aeconfiguraciones.viewmodel.novedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;

public class EliminarRequest {

    @JsonProperty("p_thisnovedad_oid")
    private NovedadOid novedadeliminarpthisNovedad;
    
    @JsonIgnore
    private Novedad novedadeliminarpthisNovedadInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkNovedadeliminarpthisNovedad();
    }

    @JsonIgnore
    public NovedadOid getNovedadeliminarpthisNovedad() {
        return this.novedadeliminarpthisNovedad;
    }
    
    public void setNovedadeliminarpthisNovedad(NovedadOid novedadeliminarpthisNovedad) {
        this.novedadeliminarpthisNovedad = novedadeliminarpthisNovedad;
    }

    @JsonIgnore
    public Novedad getNovedadeliminarpthisNovedadInstance() {
        return this.novedadeliminarpthisNovedadInstance;
    }
    
    public void setNovedadeliminarpthisNovedadInstance(Novedad novedadeliminarpthisNovedad) {
        this.novedadeliminarpthisNovedadInstance = novedadeliminarpthisNovedad;
        this.novedadeliminarpthisNovedad = novedadeliminarpthisNovedad != null ? novedadeliminarpthisNovedad.getOid() : null;
    }

    private void checkNovedadeliminarpthisNovedad() throws NotNullArgumentException {
        if(this.getNovedadeliminarpthisNovedadInstance() == null || this.getNovedadeliminarpthisNovedadInstance().isNull()) {
            throw new NotNullArgumentException(NovedadConstants.SERV_ID_ELIMINAR, NovedadConstants.CLASS_ID, NovedadConstants.ARG_ID_ELIMINAR_NOVEDADELIMINARPTHISNOVEDAD, NovedadConstants.SERV_ALIAS_ELIMINAR, NovedadConstants.CLASS_ALIAS, NovedadConstants.ARG_ALIAS_ELIMINAR_NOVEDADELIMINARPTHISNOVEDAD);
        }
    }
}
