package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.global.ConceptoCConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.persistence.ConceptoC;
import com.keralty.aeconfiguraciones.persistence.oid.ConceptoCOid;
import com.keralty.aeconfiguraciones.repository.ConceptoCJpaRepository;
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
public class ConceptoCService extends AbstractService {

    ConceptoCJpaRepository repository;

    ConceptoCService conceptoCSrv;

    @Inject
    public ConceptoCService(
            ConceptoCService conceptoCSrv,
            ConceptoCJpaRepository repository) {
        this.conceptoCSrv = conceptoCSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of ConceptoC by ID
     *
     * @param conceptoCIdConcepto Identification field conceptoCIdConcepto
     *
     * @return Instance of ConceptoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConceptoC getById(Long conceptoCIdConcepto) {
        return getByOID(new ConceptoCOid(conceptoCIdConcepto));
    }
    
    /*
     * Returns an instance of ConceptoC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConceptoC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ConceptoC getByOID(ConceptoCOid oid) {
        Optional<ConceptoC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ConceptoC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ConceptoC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ConceptoC getByOIDWithHV(ConceptoCOid oid) {
        Optional<ConceptoC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ConceptoC> getInstanceByOid(ConceptoCOid oid) {
        return oid != null && oid.getConceptoCIdConcepto() != null
               ? repository.findById(oid.getConceptoCIdConcepto())
               : Optional.empty();
    }

    /*
     * Returns an instance of ConceptoC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ConceptoC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ConceptoC getByJsonOid(String jsonOid) {
        ConceptoCOid oid = new ConceptoCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ConceptoCOid oid = new ConceptoCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ConceptoCConstants.ATTR_FIELD_CONCEPTOCIDCONCEPTO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getConceptoCIdConcepto(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ConceptoCService.class).error(e.getMessage());
        }
    }

}
