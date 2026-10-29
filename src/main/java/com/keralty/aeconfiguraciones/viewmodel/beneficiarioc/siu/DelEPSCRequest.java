package com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.keralty.aeconfiguraciones.exception.NotNullArgumentException;
import com.keralty.aeconfiguraciones.global.BeneficiarioCConstants;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;

public class DelEPSCRequest {

    @JsonProperty("p_thisbeneficiarioc_oid")
    private BeneficiarioCOid beneficiarioCDelEPSCpthisBeneficiarioC;
    
    @JsonIgnore
    private BeneficiarioC beneficiarioCDelEPSCpthisBeneficiarioCInstance;

    @JsonProperty("p_evceps_oid")
    private EpsOid beneficiarioCDelEPSCpevcEps;
    
    @JsonIgnore
    private Eps beneficiarioCDelEPSCpevcEpsInstance;

    public void checkArguments() throws NotNullArgumentException {
        checkBeneficiarioCDelEPSCpthisBeneficiarioC();
        checkBeneficiarioCDelEPSCpevcEps();
    }

    @JsonIgnore
    public BeneficiarioCOid getBeneficiarioCDelEPSCpthisBeneficiarioC() {
        return this.beneficiarioCDelEPSCpthisBeneficiarioC;
    }
    
    public void setBeneficiarioCDelEPSCpthisBeneficiarioC(BeneficiarioCOid beneficiarioCDelEPSCpthisBeneficiarioC) {
        this.beneficiarioCDelEPSCpthisBeneficiarioC = beneficiarioCDelEPSCpthisBeneficiarioC;
    }

    @JsonIgnore
    public BeneficiarioC getBeneficiarioCDelEPSCpthisBeneficiarioCInstance() {
        return this.beneficiarioCDelEPSCpthisBeneficiarioCInstance;
    }
    
    public void setBeneficiarioCDelEPSCpthisBeneficiarioCInstance(BeneficiarioC beneficiarioCDelEPSCpthisBeneficiarioC) {
        this.beneficiarioCDelEPSCpthisBeneficiarioCInstance = beneficiarioCDelEPSCpthisBeneficiarioC;
        this.beneficiarioCDelEPSCpthisBeneficiarioC = beneficiarioCDelEPSCpthisBeneficiarioC != null ? beneficiarioCDelEPSCpthisBeneficiarioC.getOid() : null;
    }

    private void checkBeneficiarioCDelEPSCpthisBeneficiarioC() throws NotNullArgumentException {
        if(this.getBeneficiarioCDelEPSCpthisBeneficiarioCInstance() == null || this.getBeneficiarioCDelEPSCpthisBeneficiarioCInstance().isNull()) {
            throw new NotNullArgumentException(BeneficiarioCConstants.SERV_ID_DELEPSC, BeneficiarioCConstants.CLASS_ID, BeneficiarioCConstants.ARG_ID_DELEPSC_BENEFICIARIOCDELEPSCPTHISBENEFICIARIOC, BeneficiarioCConstants.SERV_ALIAS_DELEPSC, BeneficiarioCConstants.CLASS_ALIAS, BeneficiarioCConstants.ARG_ALIAS_DELEPSC_BENEFICIARIOCDELEPSCPTHISBENEFICIARIOC);
        }
    }

    @JsonIgnore
    public EpsOid getBeneficiarioCDelEPSCpevcEps() {
        return this.beneficiarioCDelEPSCpevcEps;
    }
    
    public void setBeneficiarioCDelEPSCpevcEps(EpsOid beneficiarioCDelEPSCpevcEps) {
        this.beneficiarioCDelEPSCpevcEps = beneficiarioCDelEPSCpevcEps;
    }

    @JsonIgnore
    public Eps getBeneficiarioCDelEPSCpevcEpsInstance() {
        return this.beneficiarioCDelEPSCpevcEpsInstance;
    }
    
    public void setBeneficiarioCDelEPSCpevcEpsInstance(Eps beneficiarioCDelEPSCpevcEps) {
        this.beneficiarioCDelEPSCpevcEpsInstance = beneficiarioCDelEPSCpevcEps;
        this.beneficiarioCDelEPSCpevcEps = beneficiarioCDelEPSCpevcEps != null ? beneficiarioCDelEPSCpevcEps.getOid() : null;
    }

    private void checkBeneficiarioCDelEPSCpevcEps() throws NotNullArgumentException {
        if(this.getBeneficiarioCDelEPSCpevcEpsInstance() == null || this.getBeneficiarioCDelEPSCpevcEpsInstance().isNull()) {
            throw new NotNullArgumentException(BeneficiarioCConstants.SERV_ID_DELEPSC, BeneficiarioCConstants.CLASS_ID, BeneficiarioCConstants.ARG_ID_DELEPSC_BENEFICIARIOCDELEPSCPEVCEPS, BeneficiarioCConstants.SERV_ALIAS_DELEPSC, BeneficiarioCConstants.CLASS_ALIAS, BeneficiarioCConstants.ARG_ALIAS_DELEPSC_BENEFICIARIOCDELEPSCPEVCEPS);
        }
    }
}
