package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.SeccionPConstants;
import com.keralty.aeconfiguraciones.persistence.oid.SeccionPOid;
import com.keralty.aeconfiguraciones.persistence.SeccionP;
import com.keralty.aeconfiguraciones.repository.SeccionPJpaRepository;
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
public class SeccionPService extends AbstractService {

    SeccionPJpaRepository repository;

    SeccionPService seccionPSrv;

    @Inject
    public SeccionPService(
            SeccionPService seccionPSrv,
            SeccionPJpaRepository repository) {
        this.seccionPSrv = seccionPSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of SeccionP by ID
     *
     * @param seccionPIdentificador Identification field seccionPIdentificador
     *
     * @return Instance of SeccionP
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public SeccionP getById(Long seccionPIdentificador) {
        return getByOID(new SeccionPOid(seccionPIdentificador));
    }
    
    /*
     * Returns an instance of SeccionP by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of SeccionP
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public SeccionP getByOID(SeccionPOid oid) {
        Optional<SeccionP> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of SeccionP by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of SeccionP
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public SeccionP getByOIDWithHV(SeccionPOid oid) {
        Optional<SeccionP> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<SeccionP> getInstanceByOid(SeccionPOid oid) {
        return oid != null && oid.getSeccionPIdentificador() != null
               ? repository.findById(oid.getSeccionPIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of SeccionP by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of SeccionP
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public SeccionP getByJsonOid(String jsonOid) {
        SeccionPOid oid = new SeccionPOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            SeccionPOid oid = new SeccionPOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(SeccionPConstants.ATTR_FIELD_SECCIONPIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getSeccionPIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(SeccionPService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
