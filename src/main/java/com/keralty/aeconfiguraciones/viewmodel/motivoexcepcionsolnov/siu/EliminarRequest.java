package com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MotivoExcepcionSolNovConstants;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
import com.keralty.aeconfiguraciones.persistence.oid.MotivoExcepcionSolNovOid;

public class EliminarRequest {

    @JsonProperty("p_thismotivoexcepcionsolnov_oid")
    private MotivoExcepcionSolNovOid motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov;
    
    @JsonIgnore
    private MotivoExcepcionSolNov motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov();
    }

    @JsonIgnore
    public MotivoExcepcionSolNovOid getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov() {
        return this.motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov;
    }
    
    public void setMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov(MotivoExcepcionSolNovOid motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov) {
        this.motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov = motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov;
    }

    @JsonIgnore
    public MotivoExcepcionSolNov getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance() {
        return this.motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance;
    }
    
    public void setMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance(MotivoExcepcionSolNov motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov) {
        this.motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance = motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov;
        this.motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov = motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov != null ? motivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov.getOid() : null;
    }

    private void checkMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNov() throws NotNullArgumentException {
        if(this.getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance() == null || this.getMotivoExcepcionSolNoveliminarpthisMotivoExcepcionSolNovInstance().isNull()) {
            throw new NotNullArgumentException(MotivoExcepcionSolNovConstants.SERV_ID_ELIMINAR, MotivoExcepcionSolNovConstants.CLASS_ID, MotivoExcepcionSolNovConstants.ARG_ID_ELIMINAR_MOTIVOEXCEPCIONSOLNOVELIMINARPTHISMOTIVOEXCEPCIONSOLNOV, MotivoExcepcionSolNovConstants.SERV_ALIAS_ELIMINAR, MotivoExcepcionSolNovConstants.CLASS_ALIAS, MotivoExcepcionSolNovConstants.ARG_ALIAS_ELIMINAR_MOTIVOEXCEPCIONSOLNOVELIMINARPTHISMOTIVOEXCEPCIONSOLNOV);
        }
    }
}
