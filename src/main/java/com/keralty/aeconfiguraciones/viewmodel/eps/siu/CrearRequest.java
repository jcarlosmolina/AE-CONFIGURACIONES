package com.keralty.aeconfiguraciones.viewmodel.eps.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.EpsConstants;

public class CrearRequest {

    @JsonProperty("p_atrcodigo")
    private Long epscrearpatrCodigo;

    @JsonProperty("p_atrdescripcion")
    private String epscrearpatrDescripcion;

    @JsonProperty("p_atrepssanitas")
    private Boolean epscrearpatrEpsSanitas;

    public void checkArguments() throws NotNullArgumentException {
        checkEpscrearpatrCodigo();
        checkEpscrearpatrDescripcion();
        checkEpscrearpatrEpsSanitas();
    }

    @JsonIgnore
    public Long getEpscrearpatrCodigo() {
        return this.epscrearpatrCodigo;
    }
    
    public void setEpscrearpatrCodigo(Long epscrearpatrCodigo) {
        this.epscrearpatrCodigo = epscrearpatrCodigo;
    }

    private void checkEpscrearpatrCodigo() throws NotNullArgumentException {
        if(this.getEpscrearpatrCodigo() == null) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_CREAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_CREAR_EPSCREARPATRCODIGO, EpsConstants.SERV_ALIAS_CREAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_CREAR_EPSCREARPATRCODIGO);
        }
    }

    @JsonIgnore
    public String getEpscrearpatrDescripcion() {
        return this.epscrearpatrDescripcion;
    }
    
    public void setEpscrearpatrDescripcion(String epscrearpatrDescripcion) {
        this.epscrearpatrDescripcion = epscrearpatrDescripcion;
    }

    private void checkEpscrearpatrDescripcion() throws NotNullArgumentException {
        if(this.getEpscrearpatrDescripcion() == null) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_CREAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_CREAR_EPSCREARPATRDESCRIPCION, EpsConstants.SERV_ALIAS_CREAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_CREAR_EPSCREARPATRDESCRIPCION);
        }
    }

    @JsonIgnore
    public Boolean getEpscrearpatrEpsSanitas() {
        return this.epscrearpatrEpsSanitas;
    }
    
    public void setEpscrearpatrEpsSanitas(Boolean epscrearpatrEpsSanitas) {
        this.epscrearpatrEpsSanitas = epscrearpatrEpsSanitas;
    }

    private void checkEpscrearpatrEpsSanitas() throws NotNullArgumentException {
        if(this.getEpscrearpatrEpsSanitas() == null) {
            throw new NotNullArgumentException(EpsConstants.SERV_ID_CREAR, EpsConstants.CLASS_ID, EpsConstants.ARG_ID_CREAR_EPSCREARPATREPSSANITAS, EpsConstants.SERV_ALIAS_CREAR, EpsConstants.CLASS_ALIAS, EpsConstants.ARG_ALIAS_CREAR_EPSCREARPATREPSSANITAS);
        }
    }
}
