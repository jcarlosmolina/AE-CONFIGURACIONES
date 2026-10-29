package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.NovedadMDConstants;
import com.keralty.aeconfiguraciones.persistence.NovedadMD;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadMDOid;
import com.keralty.aeconfiguraciones.repository.NovedadMDJpaRepository;
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
public class NovedadMDService extends AbstractService {

    NovedadMDJpaRepository repository;

    NovedadMDService novedadMDSrv;

    @Inject
    public NovedadMDService(
            NovedadMDService novedadMDSrv,
            NovedadMDJpaRepository repository) {
        this.novedadMDSrv = novedadMDSrv;
        this.repository = repository;
    }


    /*
     * Returns an instance of NovedadMD by ID
     *
     * @param novedadMDidNovedadMD Identification field novedadMDidNovedadMD
     *
     * @return Instance of NovedadMD
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadMD getById(Long novedadMDidNovedadMD) {
        return getByOID(new NovedadMDOid(novedadMDidNovedadMD));
    }
    
    /*
     * Returns an instance of NovedadMD by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of NovedadMD
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadMD getByOID(NovedadMDOid oid) {
        Optional<NovedadMD> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of NovedadMD by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of NovedadMD
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public NovedadMD getByOIDWithHV(NovedadMDOid oid) {
        Optional<NovedadMD> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<NovedadMD> getInstanceByOid(NovedadMDOid oid) {
        return oid != null && oid.getNovedadMDidNovedadMD() != null
               ? repository.findById(oid.getNovedadMDidNovedadMD())
               : Optional.empty();
    }

    /*
     * Returns an instance of NovedadMD by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of NovedadMD
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public NovedadMD getByJsonOid(String jsonOid) {
        NovedadMDOid oid = new NovedadMDOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }


    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            NovedadMDOid oid = new NovedadMDOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(NovedadMDConstants.ATTR_FIELD_NOVEDADMDIDNOVEDADMD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getNovedadMDidNovedadMD(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(NovedadMDService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
