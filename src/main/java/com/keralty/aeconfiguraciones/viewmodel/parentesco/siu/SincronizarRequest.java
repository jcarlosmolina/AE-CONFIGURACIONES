package com.keralty.aeconfiguraciones.viewmodel.parentesco.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;

public class SincronizarRequest {

    @JsonProperty("p_thisparentesco_oid")
    private ParentescoOid parentescosincronizarpthisParentesco;
    
    @JsonIgnore
    private Parentesco parentescosincronizarpthisParentescoInstance;

    @JsonProperty("p_descripcion")
    private String parentescosincronizarpDescripcion;

    @JsonProperty("pnombrecorto")
    private String parentescosincronizarpNombreCorto;

    public void checkArguments() throws NotNullArgumentException {
        checkParentescosincronizarpthisParentesco();
        checkParentescosincronizarpDescripcion();
        checkParentescosincronizarpNombreCorto();
    }

    @JsonIgnore
    public ParentescoOid getParentescosincronizarpthisParentesco() {
        return this.parentescosincronizarpthisParentesco;
    }
    
    public void setParentescosincronizarpthisParentesco(ParentescoOid parentescosincronizarpthisParentesco) {
        this.parentescosincronizarpthisParentesco = parentescosincronizarpthisParentesco;
    }

    @JsonIgnore
    public Parentesco getParentescosincronizarpthisParentescoInstance() {
        return this.parentescosincronizarpthisParentescoInstance;
    }
    
    public void setParentescosincronizarpthisParentescoInstance(Parentesco parentescosincronizarpthisParentesco) {
        this.parentescosincronizarpthisParentescoInstance = parentescosincronizarpthisParentesco;
        this.parentescosincronizarpthisParentesco = parentescosincronizarpthisParentesco != null ? parentescosincronizarpthisParentesco.getOid() : null;
    }

    private void checkParentescosincronizarpthisParentesco() throws NotNullArgumentException {
        if(this.getParentescosincronizarpthisParentescoInstance() == null || this.getParentescosincronizarpthisParentescoInstance().isNull()) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_SINCRONIZAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_SINCRONIZAR_PARENTESCOSINCRONIZARPTHISPARENTESCO, ParentescoConstants.SERV_ALIAS_SINCRONIZAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_SINCRONIZAR_PARENTESCOSINCRONIZARPTHISPARENTESCO);
        }
    }

    @JsonIgnore
    public String getParentescosincronizarpDescripcion() {
        return this.parentescosincronizarpDescripcion;
    }
    
    public void setParentescosincronizarpDescripcion(String parentescosincronizarpDescripcion) {
        this.parentescosincronizarpDescripcion = parentescosincronizarpDescripcion;
    }

    private void checkParentescosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getParentescosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_SINCRONIZAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_SINCRONIZAR_PARENTESCOSINCRONIZARPDESCRIPCION, ParentescoConstants.SERV_ALIAS_SINCRONIZAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_SINCRONIZAR_PARENTESCOSINCRONIZARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getParentescosincronizarpNombreCorto() {
        return this.parentescosincronizarpNombreCorto;
    }
    
    public void setParentescosincronizarpNombreCorto(String parentescosincronizarpNombreCorto) {
        this.parentescosincronizarpNombreCorto = parentescosincronizarpNombreCorto;
    }

    private void checkParentescosincronizarpNombreCorto() throws NotNullArgumentException {
        if(this.getParentescosincronizarpNombreCorto() == null) {
            throw new NotNullArgumentException(ParentescoConstants.SERV_ID_SINCRONIZAR, ParentescoConstants.CLASS_ID, ParentescoConstants.ARG_ID_SINCRONIZAR_PARENTESCOSINCRONIZARPNOMBRECORTO, ParentescoConstants.SERV_ALIAS_SINCRONIZAR, ParentescoConstants.CLASS_ALIAS, ParentescoConstants.ARG_ALIAS_SINCRONIZAR_PARENTESCOSINCRONIZARPNOMBRECORTO);
        }
    }
}
