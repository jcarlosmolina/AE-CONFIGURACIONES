package com.keralty.aeconfiguraciones.viewmodel.dominio.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;

public class TINSPLANTILLARequest {

    @JsonProperty("p_thisdominio_oid")
    private DominioOid dominioTINSPLANTILLApthisDominio;
    
    @JsonIgnore
    private Dominio dominioTINSPLANTILLApthisDominioInstance;

    @JsonProperty("ptplantilla_oid")
    private PlantillaEmailOid dominioTINSPLANTILLAptPlantilla;
    
    @JsonIgnore
    private PlantillaEmail dominioTINSPLANTILLAptPlantillaInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkDominioTINSPLANTILLApthisDominio();
        checkDominioTINSPLANTILLAptPlantilla();
    }

    @JsonIgnore
    public DominioOid getDominioTINSPLANTILLApthisDominio() {
        return this.dominioTINSPLANTILLApthisDominio;
    }
    
    public void setDominioTINSPLANTILLApthisDominio(DominioOid dominioTINSPLANTILLApthisDominio) {
        this.dominioTINSPLANTILLApthisDominio = dominioTINSPLANTILLApthisDominio;
    }

    @JsonIgnore
    public Dominio getDominioTINSPLANTILLApthisDominioInstance() {
        return this.dominioTINSPLANTILLApthisDominioInstance;
    }
    
    public void setDominioTINSPLANTILLApthisDominioInstance(Dominio dominioTINSPLANTILLApthisDominio) {
        this.dominioTINSPLANTILLApthisDominioInstance = dominioTINSPLANTILLApthisDominio;
        this.dominioTINSPLANTILLApthisDominio = dominioTINSPLANTILLApthisDominio != null ? dominioTINSPLANTILLApthisDominio.getOid() : null;
    }

    private void checkDominioTINSPLANTILLApthisDominio() throws NotNullArgumentException {
        if(this.getDominioTINSPLANTILLApthisDominioInstance() == null || this.getDominioTINSPLANTILLApthisDominioInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSPLANTILLA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSPLANTILLA_DOMINIOTINSPLANTILLAPTHISDOMINIO, DominioConstants.SERV_ALIAS_TINSPLANTILLA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSPLANTILLA_DOMINIOTINSPLANTILLAPTHISDOMINIO);
        }
    }

    @JsonIgnore
    public PlantillaEmailOid getDominioTINSPLANTILLAptPlantilla() {
        return this.dominioTINSPLANTILLAptPlantilla;
    }
    
    public void setDominioTINSPLANTILLAptPlantilla(PlantillaEmailOid dominioTINSPLANTILLAptPlantilla) {
        this.dominioTINSPLANTILLAptPlantilla = dominioTINSPLANTILLAptPlantilla;
    }

    @JsonIgnore
    public PlantillaEmail getDominioTINSPLANTILLAptPlantillaInstance() {
        return this.dominioTINSPLANTILLAptPlantillaInstance;
    }
    
    public void setDominioTINSPLANTILLAptPlantillaInstance(PlantillaEmail dominioTINSPLANTILLAptPlantilla) {
        this.dominioTINSPLANTILLAptPlantillaInstance = dominioTINSPLANTILLAptPlantilla;
        this.dominioTINSPLANTILLAptPlantilla = dominioTINSPLANTILLAptPlantilla != null ? dominioTINSPLANTILLAptPlantilla.getOid() : null;
    }

    private void checkDominioTINSPLANTILLAptPlantilla() throws NotNullArgumentException {
        if(this.getDominioTINSPLANTILLAptPlantillaInstance() == null || this.getDominioTINSPLANTILLAptPlantillaInstance().isNull()) {
            throw new NotNullArgumentException(DominioConstants.SERV_ID_TINSPLANTILLA, DominioConstants.CLASS_ID, DominioConstants.ARG_ID_TINSPLANTILLA_DOMINIOTINSPLANTILLAPTPLANTILLA, DominioConstants.SERV_ALIAS_TINSPLANTILLA, DominioConstants.CLASS_ALIAS, DominioConstants.ARG_ALIAS_TINSPLANTILLA_DOMINIOTINSPLANTILLAPTPLANTILLA);
        }
    }
}
