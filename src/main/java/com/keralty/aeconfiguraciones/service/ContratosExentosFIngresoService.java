package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.oid.ContratosExentosFIngresoOid;
import com.keralty.aeconfiguraciones.repository.ContratosExentosFIngresoJpaRepository;
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
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ContratosExentosFIngresoService extends AbstractService {

    ContratosExentosFIngresoJpaRepository repository;

    ContratosExentosFIngresoService contratosExentosFIngresoSrv;

    @Inject
    public ContratosExentosFIngresoService(
            ContratosExentosFIngresoService contratosExentosFIngresoSrv,
            ContratosExentosFIngresoJpaRepository repository) {
        this.contratosExentosFIngresoSrv = contratosExentosFIngresoSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ContratosExentosFIngresoConstants.CLASS_NAME);
    }

    private List<ContratosExentosFIngreso> solveContratosExentosFIngresoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ContratosExentosFIngresoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveContratosExentosFIngresoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ContratosExentosFIngresoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ContratosExentosFIngreso
     *
     * @return Population of class ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratosExentosFIngreso> get() {
        List<ContratosExentosFIngreso> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class ContratosExentosFIngreso
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<ContratosExentosFIngreso> instances = get(queryRequest);
        if(ContratosExentosFIngresoConstants.DSDSCONTRATOSEXENTOSFINGRESO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSContratosExentosFIngreso())).collect(Collectors.toList());
        }
        if(ContratosExentosFIngresoConstants.DSICCONTRATOSEXENTOSFINGRESO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICContratosExentosFIngreso())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class ContratosExentosFIngreso that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ContratosExentosFIngreso matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ContratosExentosFIngreso> get(QueryRequest queryRequest) {
        return solveContratosExentosFIngresoTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveContratosExentosFIngresoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveContratosExentosFIngresoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveContratosExentosFIngresoTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns an instance of ContratosExentosFIngreso by ID
     *
     * @param contratosExentosFIngresoidContratosExentosFIngreso Identification field contratosExentosFIngresoidContratosExentosFIngreso
     *
     * @return Instance of ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratosExentosFIngreso getById(Long contratosExentosFIngresoidContratosExentosFIngreso) {
        return getByOID(new ContratosExentosFIngresoOid(contratosExentosFIngresoidContratosExentosFIngreso));
    }
    
    /*
     * Returns an instance of ContratosExentosFIngreso by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratosExentosFIngreso getByOID(ContratosExentosFIngresoOid oid) {
        Optional<ContratosExentosFIngreso> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ContratosExentosFIngreso by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ContratosExentosFIngreso getByOIDWithHV(ContratosExentosFIngresoOid oid) {
        Optional<ContratosExentosFIngreso> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ContratosExentosFIngreso> getInstanceByOid(ContratosExentosFIngresoOid oid) {
        return oid != null && oid.getContratosExentosFIngresoidContratosExentosFIngreso() != null
               ? repository.findById(oid.getContratosExentosFIngresoidContratosExentosFIngreso())
               : Optional.empty();
    }

    /*
     * Returns an instance of ContratosExentosFIngreso by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ContratosExentosFIngreso
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ContratosExentosFIngreso getByJsonOid(String jsonOid) {
        ContratosExentosFIngresoOid oid = new ContratosExentosFIngresoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of ContratosExentosFIngreso
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getContratosExentosFIngresoDynamic(ContratosExentosFIngresoOid oid, String displaySetItems) {
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
    public String getSuppInfo(ContratosExentosFIngresoOid oid, String dsName) {
        ContratosExentosFIngreso instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ContratosExentosFIngreso instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (ContratosExentosFIngresoConstants.DSICCONTRATOSEXENTOSFINGRESO.equals(dsName))
            return instance.buildICContratosExentosFIngreso();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(ContratosExentosFIngreso instance, String dsName) {
        if (ContratosExentosFIngresoConstants.DSDSCONTRATOSEXENTOSFINGRESO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSContratosExentosFIngreso());
        if (ContratosExentosFIngresoConstants.DSICCONTRATOSEXENTOSFINGRESO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICContratosExentosFIngreso());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (ContratosExentosFIngresoConstants.FILTER_NAME_FCONTRATOSEXENTOSFINGRESO.equalsIgnoreCase(filterRequest.getName()))
            fContratosExentosFIngreso(filterRequest, sql);
        if (ContratosExentosFIngresoConstants.FILTER_NAME_IFCONTRATOSEXENTOSFINGRESONC.equalsIgnoreCase(filterRequest.getName()))
            iFContratosExentosFIngresoNC(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_ContratosExentosFIngreso".equals(filterRequest.getName()))
            suppInfo4ICContratosExentosFIngreso(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fContratosExentosFIngreso(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vfNum = filterRequest.hasFilterVariable(ContratosExentosFIngresoConstants.VAR_NAME_FCONTRATOSEXENTOSFINGRESO_VFNUM) ? (String) filterRequest.getFilterVariable(ContratosExentosFIngresoConstants.VAR_NAME_FCONTRATOSEXENTOSFINGRESO_VFNUM, FilterVariableRequest.STRING) : null;

        // NumContrato LIKE vfNum
        if (vfNum != null) {
            // 'like' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ContratosExentosFIngresoConstants.ATTR_NAME_CONTRATOSEXENTOSFINGRESONUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfNum, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void iFContratosExentosFIngresoNC(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vfNum = filterRequest.hasFilterVariable(ContratosExentosFIngresoConstants.VAR_NAME_IFCONTRATOSEXENTOSFINGRESONC_VFNUM) ? (String) filterRequest.getFilterVariable(ContratosExentosFIngresoConstants.VAR_NAME_IFCONTRATOSEXENTOSFINGRESONC_VFNUM, FilterVariableRequest.STRING) : null;

        // NumContrato = vfNum
        if (vfNum != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(ContratosExentosFIngresoConstants.ATTR_NAME_CONTRATOSEXENTOSFINGRESONUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfNum, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUContratosExentosFIngreso
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ContratosExentosFIngreso using displayset DSContratosExentosFIngreso that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.ds.DSContratosExentosFIngreso> piuPIUContratosExentosFIngreso(QueryRequest queryRequest) {
        return solveContratosExentosFIngresoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.ds.DSContratosExentosFIngreso::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUContratosExentosFIngreso
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUContratosExentosFIngreso
     */
    public int getCount4PIUContratosExentosFIngreso(QueryRequest queryRequest) {
        return solveContratosExentosFIngresoTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratosExentosFIngresoSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.CrearRequest request) throws ModelException {
        return contratosExentosFIngresoSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String contratosExentosFIngresocrearpatrNumContrato = request.getContratosExentosFIngresocrearpatrNumContrato();

        request.checkArguments();
        ContratosExentosFIngreso instance = new ContratosExentosFIngreso();
        instance.setContratosExentosFIngresoidContratosExentosFIngreso(Long.valueOf(0));
        instance.setContratosExentosFIngresoNumContrato(contratosExentosFIngresocrearpatrNumContrato);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICContratosExentosFIngreso());

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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = contratosExentosFIngresoSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.EliminarRequest request) throws ModelException {
        request.setContratosExentosFIngresoeliminarpthisContratosExentosFIngresoInstance(contratosExentosFIngresoSrv.getByOIDWithHV(request.getContratosExentosFIngresoeliminarpthisContratosExentosFIngreso()));
        return contratosExentosFIngresoSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContratosExentosFIngreso contratosExentosFIngresoeliminarpthisContratosExentosFIngreso = request.getContratosExentosFIngresoeliminarpthisContratosExentosFIngresoInstance();

        request.checkArguments();
        ContratosExentosFIngreso instance = contratosExentosFIngresoeliminarpthisContratosExentosFIngreso;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICContratosExentosFIngreso());

        return response;
    }


    private void suppInfo4ICContratosExentosFIngreso(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratosExentosFIngresoConstants.ATTR_NAME_CONTRATOSEXENTOSFINGRESONUMCONTRATO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCContratosExentosFIngreso(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ContratosExentosFIngresoConstants.CLASS_NAME, ContratosExentosFIngresoConstants.ATTR_FIELD_CONTRATOSEXENTOSFINGRESOIDCONTRATOSEXENTOSFINGRESO, false), true);
    }


    private void applyOCOCContratosExentosFIngreso(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_ContratosExentosFIngreso".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(ContratosExentosFIngresoConstants.CLASS_NAME, ContratosExentosFIngresoConstants.ATTR_NAME_CONTRATOSEXENTOSFINGRESONUMCONTRATO, false), true);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ContratosExentosFIngresoOid oid = new ContratosExentosFIngresoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ContratosExentosFIngresoConstants.ATTR_FIELD_CONTRATOSEXENTOSFINGRESOIDCONTRATOSEXENTOSFINGRESO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratosExentosFIngresoidContratosExentosFIngreso(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ContratosExentosFIngresoService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
