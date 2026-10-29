package com.keralty.aeconfiguraciones.viewmodel.anexomat.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.AnexoMatConstants;
import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoMatOid;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;

public class CambiarInsInstitucionMaternalRequest {

    @JsonProperty("p_thisanexomat_oid")
    private AnexoMatOid anexoMatCambiarInsInstitucionMaternalpthisAnexoMat;
    
    @JsonIgnore
    private AnexoMat anexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance;

    @JsonProperty("p_evcinstitucionmatneo_oid")
    private InstitucionMatNeoOid anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo;
    
    @JsonIgnore
    private InstitucionMatNeo anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkAnexoMatCambiarInsInstitucionMaternalpthisAnexoMat();
        checkAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo();
    }

    @JsonIgnore
    public AnexoMatOid getAnexoMatCambiarInsInstitucionMaternalpthisAnexoMat() {
        return this.anexoMatCambiarInsInstitucionMaternalpthisAnexoMat;
    }
    
    public void setAnexoMatCambiarInsInstitucionMaternalpthisAnexoMat(AnexoMatOid anexoMatCambiarInsInstitucionMaternalpthisAnexoMat) {
        this.anexoMatCambiarInsInstitucionMaternalpthisAnexoMat = anexoMatCambiarInsInstitucionMaternalpthisAnexoMat;
    }

    @JsonIgnore
    public AnexoMat getAnexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance() {
        return this.anexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance;
    }
    
    public void setAnexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance(AnexoMat anexoMatCambiarInsInstitucionMaternalpthisAnexoMat) {
        this.anexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance = anexoMatCambiarInsInstitucionMaternalpthisAnexoMat;
        this.anexoMatCambiarInsInstitucionMaternalpthisAnexoMat = anexoMatCambiarInsInstitucionMaternalpthisAnexoMat != null ? anexoMatCambiarInsInstitucionMaternalpthisAnexoMat.getOid() : null;
    }

    private void checkAnexoMatCambiarInsInstitucionMaternalpthisAnexoMat() throws NotNullArgumentException {
        if(this.getAnexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance() == null || this.getAnexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance().isNull()) {
            throw new NotNullArgumentException(AnexoMatConstants.SERV_ID_CAMBIARINSINSTITUCIONMATERNAL, AnexoMatConstants.CLASS_ID, AnexoMatConstants.ARG_ID_CAMBIARINSINSTITUCIONMATERNAL_ANEXOMATCAMBIARINSINSTITUCIONMATERNALPTHISANEXOMAT, AnexoMatConstants.SERV_ALIAS_CAMBIARINSINSTITUCIONMATERNAL, AnexoMatConstants.CLASS_ALIAS, AnexoMatConstants.ARG_ALIAS_CAMBIARINSINSTITUCIONMATERNAL_ANEXOMATCAMBIARINSINSTITUCIONMATERNALPTHISANEXOMAT);
        }
    }

    @JsonIgnore
    public InstitucionMatNeoOid getAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo() {
        return this.anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo;
    }
    
    public void setAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo(InstitucionMatNeoOid anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo) {
        this.anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo = anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo;
    }

    @JsonIgnore
    public InstitucionMatNeo getAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance() {
        return this.anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance;
    }
    
    public void setAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance(InstitucionMatNeo anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo) {
        this.anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance = anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo;
        this.anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo = anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo != null ? anexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo.getOid() : null;
    }

    private void checkAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeo() throws NotNullArgumentException {
        if(this.getAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance() == null || this.getAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance().isNull()) {
            throw new NotNullArgumentException(AnexoMatConstants.SERV_ID_CAMBIARINSINSTITUCIONMATERNAL, AnexoMatConstants.CLASS_ID, AnexoMatConstants.ARG_ID_CAMBIARINSINSTITUCIONMATERNAL_ANEXOMATCAMBIARINSINSTITUCIONMATERNALPEVCINSTITUCIONMATNEO, AnexoMatConstants.SERV_ALIAS_CAMBIARINSINSTITUCIONMATERNAL, AnexoMatConstants.CLASS_ALIAS, AnexoMatConstants.ARG_ALIAS_CAMBIARINSINSTITUCIONMATERNAL_ANEXOMATCAMBIARINSINSTITUCIONMATERNALPEVCINSTITUCIONMATNEO);
        }
    }
}
