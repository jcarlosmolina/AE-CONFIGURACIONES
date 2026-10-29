package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class TSINCRONIZARRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioTSINCRONIZARpthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioTSINCRONIZARpthisMunicipioInstance;

    @JsonProperty("pt_p_descripcion")
    private String municipioTSINCRONIZARptpDescripcion;

    @JsonProperty("pt_p_codigolegal")
    private String municipioTSINCRONIZARptpCodigoLegal;

    @JsonProperty("pt_p_esvalidoeps")
    private Boolean municipioTSINCRONIZARptpEsValidoEPS;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioTSINCRONIZARpthisMunicipio();
        checkMunicipioTSINCRONIZARptpDescripcion();
        checkMunicipioTSINCRONIZARptpCodigoLegal();
        checkMunicipioTSINCRONIZARptpEsValidoEPS();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioTSINCRONIZARpthisMunicipio() {
        return this.municipioTSINCRONIZARpthisMunicipio;
    }
    
    public void setMunicipioTSINCRONIZARpthisMunicipio(MunicipioOid municipioTSINCRONIZARpthisMunicipio) {
        this.municipioTSINCRONIZARpthisMunicipio = municipioTSINCRONIZARpthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioTSINCRONIZARpthisMunicipioInstance() {
        return this.municipioTSINCRONIZARpthisMunicipioInstance;
    }
    
    public void setMunicipioTSINCRONIZARpthisMunicipioInstance(Municipio municipioTSINCRONIZARpthisMunicipio) {
        this.municipioTSINCRONIZARpthisMunicipioInstance = municipioTSINCRONIZARpthisMunicipio;
        this.municipioTSINCRONIZARpthisMunicipio = municipioTSINCRONIZARpthisMunicipio != null ? municipioTSINCRONIZARpthisMunicipio.getOid() : null;
    }

    private void checkMunicipioTSINCRONIZARpthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioTSINCRONIZARpthisMunicipioInstance() == null || this.getMunicipioTSINCRONIZARpthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_TSINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_TSINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public String getMunicipioTSINCRONIZARptpDescripcion() {
        return this.municipioTSINCRONIZARptpDescripcion;
    }
    
    public void setMunicipioTSINCRONIZARptpDescripcion(String municipioTSINCRONIZARptpDescripcion) {
        this.municipioTSINCRONIZARptpDescripcion = municipioTSINCRONIZARptpDescripcion;
    }

    private void checkMunicipioTSINCRONIZARptpDescripcion() throws NotNullArgumentException {
        if(this.getMunicipioTSINCRONIZARptpDescripcion() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_TSINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPDESCRIPCION, MunicipioConstants.SERV_ALIAS_TSINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPDESCRIPCION);
        }
    }

    @JsonIgnore
    public String getMunicipioTSINCRONIZARptpCodigoLegal() {
        return this.municipioTSINCRONIZARptpCodigoLegal;
    }
    
    public void setMunicipioTSINCRONIZARptpCodigoLegal(String municipioTSINCRONIZARptpCodigoLegal) {
        this.municipioTSINCRONIZARptpCodigoLegal = municipioTSINCRONIZARptpCodigoLegal;
    }

    private void checkMunicipioTSINCRONIZARptpCodigoLegal() throws NotNullArgumentException {
        if(this.getMunicipioTSINCRONIZARptpCodigoLegal() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_TSINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPCODIGOLEGAL, MunicipioConstants.SERV_ALIAS_TSINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPCODIGOLEGAL);
        }
    }

    @JsonIgnore
    public Boolean getMunicipioTSINCRONIZARptpEsValidoEPS() {
        return this.municipioTSINCRONIZARptpEsValidoEPS;
    }
    
    public void setMunicipioTSINCRONIZARptpEsValidoEPS(Boolean municipioTSINCRONIZARptpEsValidoEPS) {
        this.municipioTSINCRONIZARptpEsValidoEPS = municipioTSINCRONIZARptpEsValidoEPS;
    }

    private void checkMunicipioTSINCRONIZARptpEsValidoEPS() throws NotNullArgumentException {
        if(this.getMunicipioTSINCRONIZARptpEsValidoEPS() == null) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_TSINCRONIZAR, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPESVALIDOEPS, MunicipioConstants.SERV_ALIAS_TSINCRONIZAR, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_TSINCRONIZAR_MUNICIPIOTSINCRONIZARPTPESVALIDOEPS);
        }
    }
}
