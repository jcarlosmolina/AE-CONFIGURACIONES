package com.keralty.aeconfiguraciones.viewmodel.municipio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.MunicipioConstants;
import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.ContratanteOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;

public class InsMunicipioResRequest {

    @JsonProperty("p_thismunicipio_oid")
    private MunicipioOid municipioInsMunicipioRespthisMunicipio;
    
    @JsonIgnore
    private Municipio municipioInsMunicipioRespthisMunicipioInstance;

    @JsonProperty("p_evccontratante_oid")
    private ContratanteOid municipioInsMunicipioRespevcContratante;
    
    @JsonIgnore
    private Contratante municipioInsMunicipioRespevcContratanteInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkMunicipioInsMunicipioRespthisMunicipio();
        checkMunicipioInsMunicipioRespevcContratante();
    }

    @JsonIgnore
    public MunicipioOid getMunicipioInsMunicipioRespthisMunicipio() {
        return this.municipioInsMunicipioRespthisMunicipio;
    }
    
    public void setMunicipioInsMunicipioRespthisMunicipio(MunicipioOid municipioInsMunicipioRespthisMunicipio) {
        this.municipioInsMunicipioRespthisMunicipio = municipioInsMunicipioRespthisMunicipio;
    }

    @JsonIgnore
    public Municipio getMunicipioInsMunicipioRespthisMunicipioInstance() {
        return this.municipioInsMunicipioRespthisMunicipioInstance;
    }
    
    public void setMunicipioInsMunicipioRespthisMunicipioInstance(Municipio municipioInsMunicipioRespthisMunicipio) {
        this.municipioInsMunicipioRespthisMunicipioInstance = municipioInsMunicipioRespthisMunicipio;
        this.municipioInsMunicipioRespthisMunicipio = municipioInsMunicipioRespthisMunicipio != null ? municipioInsMunicipioRespthisMunicipio.getOid() : null;
    }

    private void checkMunicipioInsMunicipioRespthisMunicipio() throws NotNullArgumentException {
        if(this.getMunicipioInsMunicipioRespthisMunicipioInstance() == null || this.getMunicipioInsMunicipioRespthisMunicipioInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_INSMUNICIPIORES, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_INSMUNICIPIORES_MUNICIPIOINSMUNICIPIORESPTHISMUNICIPIO, MunicipioConstants.SERV_ALIAS_INSMUNICIPIORES, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_INSMUNICIPIORES_MUNICIPIOINSMUNICIPIORESPTHISMUNICIPIO);
        }
    }

    @JsonIgnore
    public ContratanteOid getMunicipioInsMunicipioRespevcContratante() {
        return this.municipioInsMunicipioRespevcContratante;
    }
    
    public void setMunicipioInsMunicipioRespevcContratante(ContratanteOid municipioInsMunicipioRespevcContratante) {
        this.municipioInsMunicipioRespevcContratante = municipioInsMunicipioRespevcContratante;
    }

    @JsonIgnore
    public Contratante getMunicipioInsMunicipioRespevcContratanteInstance() {
        return this.municipioInsMunicipioRespevcContratanteInstance;
    }
    
    public void setMunicipioInsMunicipioRespevcContratanteInstance(Contratante municipioInsMunicipioRespevcContratante) {
        this.municipioInsMunicipioRespevcContratanteInstance = municipioInsMunicipioRespevcContratante;
        this.municipioInsMunicipioRespevcContratante = municipioInsMunicipioRespevcContratante != null ? municipioInsMunicipioRespevcContratante.getOid() : null;
    }

    private void checkMunicipioInsMunicipioRespevcContratante() throws NotNullArgumentException {
        if(this.getMunicipioInsMunicipioRespevcContratanteInstance() == null || this.getMunicipioInsMunicipioRespevcContratanteInstance().isNull()) {
            throw new NotNullArgumentException(MunicipioConstants.SERV_ID_INSMUNICIPIORES, MunicipioConstants.CLASS_ID, MunicipioConstants.ARG_ID_INSMUNICIPIORES_MUNICIPIOINSMUNICIPIORESPEVCCONTRATANTE, MunicipioConstants.SERV_ALIAS_INSMUNICIPIORES, MunicipioConstants.CLASS_ALIAS, MunicipioConstants.ARG_ALIAS_INSMUNICIPIORES_MUNICIPIOINSMUNICIPIORESPEVCCONTRATANTE);
        }
    }
}
