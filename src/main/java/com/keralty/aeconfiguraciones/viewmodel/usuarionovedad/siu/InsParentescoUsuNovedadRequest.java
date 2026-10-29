package com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.UsuarioNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioNovedadOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;

public class InsParentescoUsuNovedadRequest {

    @JsonProperty("p_thisusuarionovedad_oid")
    private UsuarioNovedadOid usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad;
    
    @JsonIgnore
    private UsuarioNovedad usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance;

    @JsonProperty("p_evcparentesco_oid")
    private ParentescoOid usuarioNovedadInsParentescoUsuNovedadpevcParentesco;
    
    @JsonIgnore
    private Parentesco usuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad();
        checkUsuarioNovedadInsParentescoUsuNovedadpevcParentesco();
    }

    @JsonIgnore
    public UsuarioNovedadOid getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad() {
        return this.usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad;
    }
    
    public void setUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad(UsuarioNovedadOid usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad) {
        this.usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad = usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad;
    }

    @JsonIgnore
    public UsuarioNovedad getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance() {
        return this.usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance;
    }
    
    public void setUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance(UsuarioNovedad usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad) {
        this.usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance = usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad;
        this.usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad = usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad != null ? usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad.getOid() : null;
    }

    private void checkUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad() throws NotNullArgumentException {
        if(this.getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance() == null || this.getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance().isNull()) {
            throw new NotNullArgumentException(UsuarioNovedadConstants.SERV_ID_INSPARENTESCOUSUNOVEDAD, UsuarioNovedadConstants.CLASS_ID, UsuarioNovedadConstants.ARG_ID_INSPARENTESCOUSUNOVEDAD_USUARIONOVEDADINSPARENTESCOUSUNOVEDADPTHISUSUARIONOVEDAD, UsuarioNovedadConstants.SERV_ALIAS_INSPARENTESCOUSUNOVEDAD, UsuarioNovedadConstants.CLASS_ALIAS, UsuarioNovedadConstants.ARG_ALIAS_INSPARENTESCOUSUNOVEDAD_USUARIONOVEDADINSPARENTESCOUSUNOVEDADPTHISUSUARIONOVEDAD);
        }
    }

    @JsonIgnore
    public ParentescoOid getUsuarioNovedadInsParentescoUsuNovedadpevcParentesco() {
        return this.usuarioNovedadInsParentescoUsuNovedadpevcParentesco;
    }
    
    public void setUsuarioNovedadInsParentescoUsuNovedadpevcParentesco(ParentescoOid usuarioNovedadInsParentescoUsuNovedadpevcParentesco) {
        this.usuarioNovedadInsParentescoUsuNovedadpevcParentesco = usuarioNovedadInsParentescoUsuNovedadpevcParentesco;
    }

    @JsonIgnore
    public Parentesco getUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance() {
        return this.usuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance;
    }
    
    public void setUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance(Parentesco usuarioNovedadInsParentescoUsuNovedadpevcParentesco) {
        this.usuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance = usuarioNovedadInsParentescoUsuNovedadpevcParentesco;
        this.usuarioNovedadInsParentescoUsuNovedadpevcParentesco = usuarioNovedadInsParentescoUsuNovedadpevcParentesco != null ? usuarioNovedadInsParentescoUsuNovedadpevcParentesco.getOid() : null;
    }

    private void checkUsuarioNovedadInsParentescoUsuNovedadpevcParentesco() throws NotNullArgumentException {
        if(this.getUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance() == null || this.getUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance().isNull()) {
            throw new NotNullArgumentException(UsuarioNovedadConstants.SERV_ID_INSPARENTESCOUSUNOVEDAD, UsuarioNovedadConstants.CLASS_ID, UsuarioNovedadConstants.ARG_ID_INSPARENTESCOUSUNOVEDAD_USUARIONOVEDADINSPARENTESCOUSUNOVEDADPEVCPARENTESCO, UsuarioNovedadConstants.SERV_ALIAS_INSPARENTESCOUSUNOVEDAD, UsuarioNovedadConstants.CLASS_ALIAS, UsuarioNovedadConstants.ARG_ALIAS_INSPARENTESCOUSUNOVEDAD_USUARIONOVEDADINSPARENTESCOUSUNOVEDADPEVCPARENTESCO);
        }
    }
}
