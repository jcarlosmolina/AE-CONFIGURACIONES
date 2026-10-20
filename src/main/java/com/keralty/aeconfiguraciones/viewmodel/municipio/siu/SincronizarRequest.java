package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class SincronizarRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipiosincronizarpthisMunicipio;
    
    @JsonIgnore
    private Municipio municipiosincronizarpthisMunicipioInstance;

    @JsonProperty("p_descripcion")
    private String municipiosincronizarpDescripcion;

    @JsonProperty("p_codigolegal")
    private String municipiosincronizarpCodigoLegal;

    @JsonProperty("p_esvalidoeps")
    private Boolean municipiosincronizarpEsValidoEPS;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipiosincronizarpthisMunicipio();
        checkMunicipiosincronizarpDescripcion();
        checkMunicipiosincronizarpCodigoLegal();
        checkMunicipiosincronizarpEsValidoEPS();
    }

    @JsonIgnore
    public MunicipioOid getMunicipiosincronizarpthisMunicipio() {
        return this.municipiosincronizarpthisMunicipio;
    }
    
    public void setMunicipiosincronizarpthisMunicipio(MunicipioOid municipiosincronizarpthisMunicipio) {
        this.municipiosincronizarpthisMunicipio = municipiosincronizarpthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipiosincronizarpthisMunicipioInstance() {
        return this.municipiosincronizarpthisMunicipioInstance;
    }
    
    public void setMunicipiosincronizarpthisMunicipioInstance(Municipio municipiosincronizarpthisMunicipio) {
        this.municipiosincronizarpthisMunicipioInstance = municipiosincronizarpthisMunicipio;
        this.municipiosincronizarpthisMunicipio = municipiosincronizarpthisMunicipio != null ? municipiosincronizarpthisMunicipio.getOid() : null;
    }

    private void checkMunicipiosincronizarpthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipiosincronizarpthisMunicipioInstance() == null || this.getMunicipiosincronizarpthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_SINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_SINCRONIZAR_MUNICIPIOSINCRONIZARPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_SINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_SINCRONIZAR_MUNICIPIOSINCRONIZARPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public String getMunicipiosincronizarpDescripcion() {
        return this.municipiosincronizarpDescripcion;
    }
    
    public void setMunicipiosincronizarpDescripcion(String municipiosincronizarpDescripcion) {
        this.municipiosincronizarpDescripcion = municipiosincronizarpDescripcion;
    }

    private void checkMunicipiosincronizarpDescripcion() throws NotNullArgumentException {
        if(this.getMunicipiosincronizarpDescripcion() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_SINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_SINCRONIZAR_MUNICIPIOSINCRONIZARPDESCRIPCION, MunicipioConstants.SERV_ALIAS_SINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_SINCRONIZAR_MUNICIPIOSINCRONIZARPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getMunicipiosincronizarpCodigoLegal() {
        return this.municipiosincronizarpCodigoLegal;
    }
    
    public void setMunicipiosincronizarpCodigoLegal(String municipiosincronizarpCodigoLegal) {
        this.municipiosincronizarpCodigoLegal = municipiosincronizarpCodigoLegal;
    }

    private void checkMunicipiosincronizarpCodigoLegal() throws NotNullArgumentException {
        if(this.getMunicipiosincronizarpCodigoLegal() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_SINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_SINCRONIZAR_MUNICIPIOSINCRONIZARPCODIGOLEGAL, MunicipioConstants.SERV_ALIAS_SINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_SINCRONIZAR_MUNICIPIOSINCRONIZARPCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getMunicipiosincronizarpEsValidoEPS() {
        return this.municipiosincronizarpEsValidoEPS;
    }
    
    public void setMunicipiosincronizarpEsValidoEPS(Boolean municipiosincronizarpEsValidoEPS) {
        this.municipiosincronizarpEsValidoEPS = municipiosincronizarpEsValidoEPS;
    }

    private void checkMunicipiosincronizarpEsValidoEPS() throws NotNullArgumentException {
        if(this.getMunicipiosincronizarpEsValidoEPS() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_SINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_SINCRONIZAR_MUNICIPIOSINCRONIZARPESVALIDOEPS, MunicipioConstants.SERV_ALIAS_SINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_SINCRONIZAR_MUNICIPIOSINCRONIZARPESVALIDOEPS);
        }
    }
}
