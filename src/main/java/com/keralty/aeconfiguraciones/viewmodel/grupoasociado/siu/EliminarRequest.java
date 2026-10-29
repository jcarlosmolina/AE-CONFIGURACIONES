package com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.GrupoAsociadoConstants;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsociadoOid;

public class EliminarRequest {

    @JsonProperty("p_thisgrupoasociado_oid")
    private GrupoAsociadoOid grupoAsociadoeliminarpthisGrupoAsociado;
    
    @JsonIgnore
    private GrupoAsociado grupoAsociadoeliminarpthisGrupoAsociadoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkGrupoAsociadoeliminarpthisGrupoAsociado();
    }

    @JsonIgnore
    public GrupoAsociadoOid getGrupoAsociadoeliminarpthisGrupoAsociado() {
        return this.grupoAsociadoeliminarpthisGrupoAsociado;
    }
    
    public void setGrupoAsociadoeliminarpthisGrupoAsociado(GrupoAsociadoOid grupoAsociadoeliminarpthisGrupoAsociado) {
        this.grupoAsociadoeliminarpthisGrupoAsociado = grupoAsociadoeliminarpthisGrupoAsociado;
    }

    @JsonIgnore
    public GrupoAsociado getGrupoAsociadoeliminarpthisGrupoAsociadoInstance() {
        return this.grupoAsociadoeliminarpthisGrupoAsociadoInstance;
    }
    
    public void setGrupoAsociadoeliminarpthisGrupoAsociadoInstance(GrupoAsociado grupoAsociadoeliminarpthisGrupoAsociado) {
        this.grupoAsociadoeliminarpthisGrupoAsociadoInstance = grupoAsociadoeliminarpthisGrupoAsociado;
        this.grupoAsociadoeliminarpthisGrupoAsociado = grupoAsociadoeliminarpthisGrupoAsociado != null ? grupoAsociadoeliminarpthisGrupoAsociado.getOid() : null;
    }

    private void checkGrupoAsociadoeliminarpthisGrupoAsociado() throws NotNullArgumentException {
        if(this.getGrupoAsociadoeliminarpthisGrupoAsociadoInstance() == null || this.getGrupoAsociadoeliminarpthisGrupoAsociadoInstance().isNull()) {
            throw new NotNullArgumentException(GrupoAsociadoConstants.SERV_ID_ELIMINAR, GrupoAsociadoConstants.CLASS_ID, GrupoAsociadoConstants.ARG_ID_ELIMINAR_GRUPOASOCIADOELIMINARPTHISGRUPOASOCIADO, GrupoAsociadoConstants.SERV_ALIAS_ELIMINAR, GrupoAsociadoConstants.CLASS_ALIAS, GrupoAsociadoConstants.ARG_ALIAS_ELIMINAR_GRUPOASOCIADOELIMINARPTHISGRUPOASOCIADO);
        }
    }
}
