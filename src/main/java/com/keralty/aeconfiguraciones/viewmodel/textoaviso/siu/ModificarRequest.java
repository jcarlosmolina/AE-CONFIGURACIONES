package com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TextoAvisoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TextoAvisoOid;
import com.keralty.aeconfiguraciones.persistence.TextoAviso;

public class ModificarRequest {

    @JsonProperty("p_thistextoaviso_oid")
    private TextoAvisoOid textoAvisomodificarpthisTextoAviso;
    
    @JsonIgnore
    private TextoAviso textoAvisomodificarpthisTextoAvisoInstance;

    @JsonProperty("p_descripcion")
    private String textoAvisomodificarpDescripcion;

    @JsonProperty("p_valor")
    private String textoAvisomodificarpValor;

    public void checkArguments() throws NotNullArgumentException {
        checkTextoAvisomodificarpthisTextoAviso();
        checkTextoAvisomodificarpValor();
    }

    @JsonIgnore
    public TextoAvisoOid getTextoAvisomodificarpthisTextoAviso() {
        return this.textoAvisomodificarpthisTextoAviso;
    }
    
    public void setTextoAvisomodificarpthisTextoAviso(TextoAvisoOid textoAvisomodificarpthisTextoAviso) {
        this.textoAvisomodificarpthisTextoAviso = textoAvisomodificarpthisTextoAviso;
    }

    @JsonIgnore
    public TextoAviso getTextoAvisomodificarpthisTextoAvisoInstance() {
        return this.textoAvisomodificarpthisTextoAvisoInstance;
    }
    
    public void setTextoAvisomodificarpthisTextoAvisoInstance(TextoAviso textoAvisomodificarpthisTextoAviso) {
        this.textoAvisomodificarpthisTextoAvisoInstance = textoAvisomodificarpthisTextoAviso;
        this.textoAvisomodificarpthisTextoAviso = textoAvisomodificarpthisTextoAviso != null ? textoAvisomodificarpthisTextoAviso.getOid() : null;
    }

    private void checkTextoAvisomodificarpthisTextoAviso() throws NotNullArgumentException {
        if(this.getTextoAvisomodificarpthisTextoAvisoInstance() == null || this.getTextoAvisomodificarpthisTextoAvisoInstance().isNull()) {
            throw new NotNullArgumentException(TextoAvisoConstants.SERV_ID_MODIFICAR, TextoAvisoConstants.CLASS_ID, TextoAvisoConstants.ARG_ID_MODIFICAR_TEXTOAVISOMODIFICARPTHISTEXTOAVISO, TextoAvisoConstants.SERV_ALIAS_MODIFICAR, TextoAvisoConstants.CLASS_ALIAS, TextoAvisoConstants.ARG_ALIAS_MODIFICAR_TEXTOAVISOMODIFICARPTHISTEXTOAVISO);
        }
    }

    @JsonIgnore
    public String getTextoAvisomodificarpDescripcion() {
        return this.textoAvisomodificarpDescripcion;
    }
    
    public void setTextoAvisomodificarpDescripcion(String textoAvisomodificarpDescripcion) {
        this.textoAvisomodificarpDescripcion = textoAvisomodificarpDescripcion;
    }

    @JsonIgnore
    public String getTextoAvisomodificarpValor() {
        return this.textoAvisomodificarpValor;
    }
    
    public void setTextoAvisomodificarpValor(String textoAvisomodificarpValor) {
        this.textoAvisomodificarpValor = textoAvisomodificarpValor;
    }

    private void checkTextoAvisomodificarpValor() throws NotNullArgumentException {
        if(this.getTextoAvisomodificarpValor() == null) {
            throw new NotNullArgumentException(TextoAvisoConstants.SERV_ID_MODIFICAR, TextoAvisoConstants.CLASS_ID, TextoAvisoConstants.ARG_ID_MODIFICAR_TEXTOAVISOMODIFICARPVALOR, TextoAvisoConstants.SERV_ALIAS_MODIFICAR, TextoAvisoConstants.CLASS_ALIAS, TextoAvisoConstants.ARG_ALIAS_MODIFICAR_TEXTOAVISOMODIFICARPVALOR);
        }
    }
}
