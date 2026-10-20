package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContSinDocSolConstants;
import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.ContSinDocSolOid;
import com.keralty.aeconfiguraciones.repository.ContSinDocSolJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
public class ContSinDocSolService extends AbstractService {

    ContSinDocSolJpaRepository repository;

    ContSinDocSolService contSinDocSolSrv;

    @Inject
    public ContSinDocSolService(
            ContSinDocSolService contSinDocSolSrv,
            ContSinDocSolJpaRepository repository) {
        this.contSinDocSolSrv = contSinDocSolSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContSinDocSolConstants.CLASS_NAME);
    }

    private List<ContSinDocSol> solveContSinDocSolTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContSinDocSolConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContSinDocSolTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContSinDocSolConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ContSinDocSol
     *
     * @return Population of class ContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContSinDocSol> get() {
        List<ContSinDocSol> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class ContSinDocSol that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ContSinDocSol matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContSinDocSol> get(QueryRequest queryRequest) {
        return solveContSinDocSolTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveContSinDocSolTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContSinDocSolTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of ContSinDocSol by ID
     *
     * @param contSinDocSolidContSinDocSol Identification field contSinDocSolidContSinDocSol
     *
     * @return Instance of ContSinDocSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContSinDocSol getById(Long contSinDocSolidContSinDocSol) {
        return getByOID(new ContSinDocSolOid(contSinDocSolidContSinDocSol));
    }
    
    /*
     * Returns an instance of ContSinDocSol by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContSinDocSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContSinDocSol getByOID(ContSinDocSolOid oid) {
        Optional<ContSinDocSol> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ContSinDocSol by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ContSinDocSol getByOIDWithHV(ContSinDocSolOid oid) {
        Optional<ContSinDocSol> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ContSinDocSol> getInstanceByOid(ContSinDocSolOid oid) {
        return oid != null && oid.getContSinDocSolidContSinDocSol() != null
               ? repository.findById(oid.getContSinDocSolidContSinDocSol())
               : Optional.empty();
    }

    /*
     * Returns an instance of ContSinDocSol by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ContSinDocSol
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ContSinDocSol getByJsonOid(String jsonOid) {
        ContSinDocSolOid oid = new ContSinDocSolOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of ContSinDocSol
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContSinDocSolDynamic(ContSinDocSolOid oid, String displaySetItems) {
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
    public String getSuppInfo(ContSinDocSolOid oid, String dsName) {
        ContSinDocSol instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ContSinDocSol instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ContSinDocSolConstants.FILTER_NAME_FCONTSINDOCSOLNUMCONTRATO.equalsIgnoreCase(filterRequest.getName()))
            fContSinDocSolNumContrato(filterRequest, sql);
        if (ContSinDocSolConstants.FILTER_NAME_FIPORNUMCONT.equalsIgnoreCase(filterRequest.getName()))
            fiPorNumCont(filterRequest, sql);
    }


    private void fContSinDocSolNumContrato(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vfNumCont = filterRequest.hasFilterVariable(ContSinDocSolConstants.VAR_NAME_FCONTSINDOCSOLNUMCONTRATO_VFNUMCONT) ? (String) filterRequest.getFilterVariable(ContSinDocSolConstants.VAR_NAME_FCONTSINDOCSOLNUMCONTRATO_VFNUMCONT, FilterVariableRequest.STRING) : null;

        // NumContrato LIKE "%" + vfNumCont
        if (vfNumCont != null) {
            // 'like' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ContSinDocSolConstants.ATTR_NAME_CONTSINDOCSOLNUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNumCont, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void fiPorNumCont(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vfNumCont = filterRequest.hasFilterVariable(ContSinDocSolConstants.VAR_NAME_FIPORNUMCONT_VFNUMCONT) ? (String) filterRequest.getFilterVariable(ContSinDocSolConstants.VAR_NAME_FIPORNUMCONT_VFNUMCONT, FilterVariableRequest.STRING) : null;

        // NumContrato = vfNumCont
        if (vfNumCont != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ContSinDocSolConstants.ATTR_NAME_CONTSINDOCSOLNUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfNumCont, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUContSinDocSol
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ContSinDocSol using displayset DSContSinDocSol that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.contsindocsol.ds.DSContSinDocSol> piuPIUContSinDocSol(QueryRequest queryRequest) {
        return solveContSinDocSolTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.contsindocsol.ds.DSContSinDocSol::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUContSinDocSol
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUContSinDocSol
     */
    public int getCount4PIUContSinDocSol(QueryRequest queryRequest) {
        return solveContSinDocSolTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contSinDocSolSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.CrearRequest request) throws ModelException {
        return contSinDocSolSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String contSinDocSolcrearpatrNumContrato = request.getContSinDocSolcrearpatrNumContrato();

        request.checkArguments();
        ContSinDocSol instance = new ContSinDocSol();
        instance.setContSinDocSolidContSinDocSol(Long.valueOf(0));
        instance.setContSinDocSolNumContrato(contSinDocSolcrearpatrNumContrato);

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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contSinDocSolSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.EliminarRequest request) throws ModelException {
        request.setContSinDocSoleliminarpthisContSinDocSolInstance(contSinDocSolSrv.getByOIDWithHV(request.getContSinDocSoleliminarpthisContSinDocSol()));
        return contSinDocSolSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContSinDocSol contSinDocSoleliminarpthisContSinDocSol = request.getContSinDocSoleliminarpthisContSinDocSolInstance();

        request.checkArguments();
        ContSinDocSol instance = contSinDocSoleliminarpthisContSinDocSol;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCContSinDocSol(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ContSinDocSolConstants.CLASS_NAME, ContSinDocSolConstants.ATTR_FIELD_CONTSINDOCSOLIDCONTSINDOCSOL, false), true);
    }


    private void applyOCOCContSinDocSol(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_ContSinDocSol".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(ContSinDocSolConstants.CLASS_NAME, ContSinDocSolConstants.ATTR_NAME_CONTSINDOCSOLNUMCONTRATO, false), true);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContSinDocSolOid oid = new ContSinDocSolOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContSinDocSolConstants.ATTR_FIELD_CONTSINDOCSOLIDCONTSINDOCSOL, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContSinDocSolidContSinDocSol(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContSinDocSolService.class).error(e.getMessage());
        }
    }

}
