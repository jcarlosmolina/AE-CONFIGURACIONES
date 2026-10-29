package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratoComboConstants;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoComboOid;
import com.keralty.aeconfiguraciones.repository.ContratoComboJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
import com.keralty.aeconfiguraciones.viewmodel.FilterVariableRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ContratoComboService extends AbstractService {

    ContratoComboJpaRepository repository;

    ContratoComboService contratoComboSrv;

    @Inject
    public ContratoComboService(
            ContratoComboService contratoComboSrv,
            ContratoComboJpaRepository repository) {
        this.contratoComboSrv = contratoComboSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContratoComboConstants.CLASS_NAME);
    }

    private List<ContratoCombo> solveContratoComboTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContratoComboConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContratoComboTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContratoComboConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ContratoCombo
     *
     * @return Population of class ContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoCombo> get() {
        List<ContratoCombo> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ContratoCombo that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ContratoCombo matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratoCombo> get(QueryRequest queryRequest) {
        return solveContratoComboTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveContratoComboTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContratoComboTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of ContratoCombo by ID
     *
     * @param contratoComboId Identification field contratoComboId
     *
     * @return Instance of ContratoCombo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoCombo getById(Long contratoComboId) {
        return getByOID(new ContratoComboOid(contratoComboId));
    }
    
    /*
     * Returns an instance of ContratoCombo by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoCombo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoCombo getByOID(ContratoComboOid oid) {
        Optional<ContratoCombo> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ContratoCombo by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ContratoCombo getByOIDWithHV(ContratoComboOid oid) {
        Optional<ContratoCombo> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ContratoCombo> getInstanceByOid(ContratoComboOid oid) {
        return oid != null && oid.getContratoComboId() != null
               ? repository.findById(oid.getContratoComboId())
               : Optional.empty();
    }

    /*
     * Returns an instance of ContratoCombo by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ContratoCombo
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratoCombo getByJsonOid(String jsonOid) {
        ContratoComboOid oid = new ContratoComboOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of ContratoCombo
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContratoComboDynamic(ContratoComboOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(ContratoComboOid oid, String dsName) {
        ContratoCombo instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ContratoCombo instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ContratoComboConstants.FILTER_NAME_FCONTRATOCOMBOPORNUMERO.equalsIgnoreCase(filterRequest.getName()))
            fContratoComboPorNumero(filterRequest, sql);
    }


    private void fContratoComboPorNumero(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vfNum = filterRequest.hasFilterVariable(ContratoComboConstants.VAR_NAME_FCONTRATOCOMBOPORNUMERO_VFNUM) ? (String) filterRequest.getFilterVariable(ContratoComboConstants.VAR_NAME_FCONTRATOCOMBOPORNUMERO_VFNUM, FilterVariableRequest.STRING) : null;

        // ContratoIntegral LIKE "%" + vfNum OR ContratoOdontologico LIKE "%" + vfNum
        if (vfNum != null) {
        {
            // 'or' Logical operator
            SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
        
            // Operand 1
                    // 'like' Comparison
                    SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
                    comparison2.setOperator("like");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(ContratoComboConstants.ATTR_NAME_CONTRATOCOMBOCONTRATOINTEGRAL, Constants.Type.STRING.getTypeName());
                    comparison2.setFirstOperand(operand113);
                    
                    // Operand 2
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNum, Constants.Type.STRING.getTypeName()));
                    comparison1.addOperand(comparison2);
        
            // Operand 2
                    // 'like' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("like");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(ContratoComboConstants.ATTR_NAME_CONTRATOCOMBOCONTRATOODONTOLOGICO, Constants.Type.STRING.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNum, Constants.Type.STRING.getTypeName()));
                    comparison1.addOperand(comparison4);
            
            conjunction.addOperand(comparison1);
        }

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUContratoCombo
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ContratoCombo using displayset DSContratoCombo that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.contratocombo.ds.DSContratoCombo> piuPIUContratoCombo(QueryRequest queryRequest) {
        return solveContratoComboTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.contratocombo.ds.DSContratoCombo::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUContratoCombo
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUContratoCombo
     */
    public int getCount4PIUContratoCombo(QueryRequest queryRequest) {
        return solveContratoComboTypedQuery(queryRequest).size();
    }

    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoComboSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.CrearRequest request) throws ModelException {
        return contratoComboSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String contratoCombocrearpatrContratoIntegral = request.getContratoCombocrearpatrContratoIntegral();
        String contratoCombocrearpatrContratoOdontologico = request.getContratoCombocrearpatrContratoOdontologico();

        request.checkArguments();
        ContratoCombo instance = new ContratoCombo();
        instance.setContratoComboId(Long.valueOf(0));
        instance.setContratoComboContratoIntegral(contratoCombocrearpatrContratoIntegral);
        instance.setContratoComboContratoOdontologico(contratoCombocrearpatrContratoOdontologico);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: eliminar

    /*
     * Entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminar(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoComboSrv.eliminarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.EliminarRequest request) throws ModelException {
        request.setContratoComboeliminarpthisContratoComboInstance(contratoComboSrv.getByOIDWithHV(request.getContratoComboeliminarpthisContratoCombo()));
        return contratoComboSrv.eliminarImplementation(request);
    }

    /*
     * Implements service eliminar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoCombo contratoComboeliminarpthisContratoCombo = request.getContratoComboeliminarpthisContratoComboInstance();

        request.checkArguments();
        ContratoCombo instance = contratoComboeliminarpthisContratoCombo;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratoComboSrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.ModificarRequest request) throws ModelException {
        request.setContratoCombomodificarpthisContratoComboInstance(contratoComboSrv.getByOIDWithHV(request.getContratoCombomodificarpthisContratoCombo()));
        return contratoComboSrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratoCombo contratoCombomodificarpthisContratoCombo = request.getContratoCombomodificarpthisContratoComboInstance();
        String contratoCombomodificarpContratoIntegral = request.getContratoCombomodificarpContratoIntegral();
        String contratoCombomodificarpContratoOdontologico = request.getContratoCombomodificarpContratoOdontologico();

        request.checkArguments();
        ContratoCombo instance = contratoCombomodificarpthisContratoCombo;
        instance.setContratoComboContratoIntegral(contratoCombomodificarpContratoIntegral);
        instance.setContratoComboContratoOdontologico(contratoCombomodificarpContratoOdontologico);


        response.setInstance(repository.save(instance));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ContratoComboConstants.CLASS_NAME, ContratoComboConstants.ATTR_FIELD_CONTRATOCOMBOID, false), true);
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContratoComboOid oid = new ContratoComboOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratoComboConstants.ATTR_FIELD_CONTRATOCOMBOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoComboId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContratoComboService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
