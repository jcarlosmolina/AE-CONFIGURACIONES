package com.keralty.aeconfiguraciones.viewmodel.textoaviso.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.TextoAvisoConstants;
import com.keralty.aeconfiguraciones.persistence.oid.TextoAvisoOid;
import com.keralty.aeconfiguraciones.persistence.TextoAviso;

public class EliminarRequest {

    @JsonProperty("p_thistextoaviso_oid")
    private TextoAvisoOid textoAvisoeliminarpthisTextoAviso;
    
    @JsonIgnore
    private TextoAviso textoAvisoeliminarpthisTextoAvisoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkTextoAvisoeliminarpthisTextoAviso();
    }

    @JsonIgnore
    public TextoAvisoOid getTextoAvisoeliminarpthisTextoAviso() {
        return this.textoAvisoeliminarpthisTextoAviso;
    }
    
    public void setTextoAvisoeliminarpthisTextoAviso(TextoAvisoOid textoAvisoeliminarpthisTextoAviso) {
        this.textoAvisoeliminarpthisTextoAviso = textoAvisoeliminarpthisTextoAviso;
    }

    @JsonIgnore
    public TextoAviso getTextoAvisoeliminarpthisTextoAvisoInstance() {
        return this.textoAvisoeliminarpthisTextoAvisoInstance;
    }
    
    public void setTextoAvisoeliminarpthisTextoAvisoInstance(TextoAviso textoAvisoeliminarpthisTextoAviso) {
        this.textoAvisoeliminarpthisTextoAvisoInstance = textoAvisoeliminarpthisTextoAviso;
        this.textoAvisoeliminarpthisTextoAviso = textoAvisoeliminarpthisTextoAviso != null ? textoAvisoeliminarpthisTextoAviso.getOid() : null;
    }

    private void checkTextoAvisoeliminarpthisTextoAviso() throws NotNullArgumentException {
        if(this.getTextoAvisoeliminarpthisTextoAvisoInstance() == null || this.getTextoAvisoeliminarpthisTextoAvisoInstance().isNull()) {
            throw new NotNullArgumentException(TextoAvisoConstants.SERV_ID_ELIMINAR, TextoAvisoConstants.CLASS_ID, TextoAvisoConstants.ARG_ID_ELIMINAR_TEXTOAVISOELIMINARPTHISTEXTOAVISO, TextoAvisoConstants.SERV_ALIAS_ELIMINAR, TextoAvisoConstants.CLASS_ALIAS, TextoAvisoConstants.ARG_ALIAS_ELIMINAR_TEXTOAVISOELIMINARPTHISTEXTOAVISO);
        }
    }
}
