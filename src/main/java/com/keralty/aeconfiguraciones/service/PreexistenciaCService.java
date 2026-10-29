package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PersonaCConstants;
import com.keralty.aeconfiguraciones.global.PreexistenciaCConstants;
import com.keralty.aeconfiguraciones.persistence.oid.PreexistenciaCOid;
import com.keralty.aeconfiguraciones.persistence.PreexistenciaC;
import com.keralty.aeconfiguraciones.repository.PreexistenciaCJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class PreexistenciaCService extends AbstractService {

    PreexistenciaCJpaRepository repository;

    PreexistenciaCService preexistenciaCSrv;

    @Inject
    public PreexistenciaCService(
            PreexistenciaCService preexistenciaCSrv,
            PreexistenciaCJpaRepository repository) {
        this.preexistenciaCSrv = preexistenciaCSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of PreexistenciaC by ID
     *
     * @param personaCID Identification field personaCID
     *
     * @param preexistenciaCIdentificador Identification field preexistenciaCIdentificador
     *
     * @return Instance of PreexistenciaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PreexistenciaC getById(Long personaCID, Long preexistenciaCIdentificador) {
        return getByOID(new PreexistenciaCOid(personaCID, preexistenciaCIdentificador));
    }
    
    /*
     * Returns an instance of PreexistenciaC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PreexistenciaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PreexistenciaC getByOID(PreexistenciaCOid oid) {
        Optional<PreexistenciaC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of PreexistenciaC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PreexistenciaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PreexistenciaC getByOIDWithHV(PreexistenciaCOid oid) {
        Optional<PreexistenciaC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<PreexistenciaC> getInstanceByOid(PreexistenciaCOid oid) {
        return oid != null && oid.getPersonaCID() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of PreexistenciaC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of PreexistenciaC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public PreexistenciaC getByJsonOid(String jsonOid) {
        PreexistenciaCOid oid = new PreexistenciaCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PreexistenciaCOid oid = new PreexistenciaCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PreexistenciaCConstants.ROLE_NAME_PERSONAC + "." + PersonaCConstants.ATTR_FIELD_PERSONACID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPersonaCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(PreexistenciaCConstants.ATTR_FIELD_PREEXISTENCIACIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getPreexistenciaCIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(PreexistenciaCService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
