package com.keralty.aeconfiguraciones.viewmodel.motivoexcepcionsolnov.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MotivoExcepcionSolNovConstants;
import com.keralty.aeconfiguraciones.persistence.MotivoExcepcionSolNov;
import com.keralty.aeconfiguraciones.persistence.oid.MotivoExcepcionSolNovOid;

public class ModificarRequest {

    @JsonProperty("p_thismotivoexcepcionsolnov_oid")
    private MotivoExcepcionSolNovOid motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov;
    
    @JsonIgnore
    private MotivoExcepcionSolNov motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance;

    @JsonProperty("p_descripcion")
    private String motivoExcepcionSolNovmodificarpDescripcion;

    public void checkArguments() throws NotNullArgumentException {
        checkMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov();
        checkMotivoExcepcionSolNovmodificarpDescripcion();
    }

    @JsonIgnore
    public MotivoExcepcionSolNovOid getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov() {
        return this.motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov;
    }
    
    public void setMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov(MotivoExcepcionSolNovOid motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov) {
        this.motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov = motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov;
    }

    @JsonIgnore
    public MotivoExcepcionSolNov getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance() {
        return this.motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance;
    }
    
    public void setMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance(MotivoExcepcionSolNov motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov) {
        this.motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance = motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov;
        this.motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov = motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov != null ? motivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov.getOid() : null;
    }

    private void checkMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNov() throws NotNullArgumentException {
        if(this.getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance() == null || this.getMotivoExcepcionSolNovmodificarpthisMotivoExcepcionSolNovInstance().isNull()) {
            throw new NotNullArgumentException(MotivoExcepcionSolNovConstants.SERV_ID_MODIFICAR, MotivoExcepcionSolNovConstants.CLASS_ID, MotivoExcepcionSolNovConstants.ARG_ID_MODIFICAR_MOTIVOEXCEPCIONSOLNOVMODIFICARPTHISMOTIVOEXCEPCIONSOLNOV, MotivoExcepcionSolNovConstants.SERV_ALIAS_MODIFICAR, MotivoExcepcionSolNovConstants.CLASS_ALIAS, MotivoExcepcionSolNovConstants.ARG_ALIAS_MODIFICAR_MOTIVOEXCEPCIONSOLNOVMODIFICARPTHISMOTIVOEXCEPCIONSOLNOV);
        }
    }

    @JsonIgnore
    public String getMotivoExcepcionSolNovmodificarpDescripcion() {
        return this.motivoExcepcionSolNovmodificarpDescripcion;
    }
    
    public void setMotivoExcepcionSolNovmodificarpDescripcion(String motivoExcepcionSolNovmodificarpDescripcion) {
        this.motivoExcepcionSolNovmodificarpDescripcion = motivoExcepcionSolNovmodificarpDescripcion;
    }

    private void checkMotivoExcepcionSolNovmodificarpDescripcion() throws NotNullArgumentException {
        if(this.getMotivoExcepcionSolNovmodificarpDescripcion() == null) {
            throw new NotNullArgumentException(MotivoExcepcionSolNovConstants.SERV_ID_MODIFICAR, MotivoExcepcionSolNovConstants.CLASS_ID, MotivoExcepcionSolNovConstants.ARG_ID_MODIFICAR_MOTIVOEXCEPCIONSOLNOVMODIFICARPDESCRIPCION, MotivoExcepcionSolNovConstants.SERV_ALIAS_MODIFICAR, MotivoExcepcionSolNovConstants.CLASS_ALIAS, MotivoExcepcionSolNovConstants.ARG_ALIAS_MODIFICAR_MOTIVOEXCEPCIONSOLNOVMODIFICARPDESCRIPCION);
        }
    }
}
