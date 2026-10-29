package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.LogContSinDocSolConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.LogContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.oid.LogContSinDocSolOid;
import com.keralty.aeconfiguraciones.repository.LogContSinDocSolJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandNull;
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
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class LogContSinDocSolService extends AbstractService {

    LogContSinDocSolJpaRepository repository;

    LogContSinDocSolService logContSinDocSolSrv;

    @Inject
    public LogContSinDocSolService(
            LogContSinDocSolService logContSinDocSolSrv,
            LogContSinDocSolJpaRepository repository) {
        this.logContSinDocSolSrv = logContSinDocSolSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogContSinDocSolConstants.CLASS_NAME);
    }

    private List<LogContSinDocSol> solveLogContSinDocSolTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogContSinDocSolConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogContSinDocSolTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogContSinDocSolConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogContSinDocSol
     *
     * @return Population of class LogContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContSinDocSol> get() {
        List<LogContSinDocSol> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogContSinDocSol that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogContSinDocSol matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContSinDocSol> get(QueryRequest queryRequest) {
        return solveLogContSinDocSolTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogContSinDocSolTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogContSinDocSolTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of LogContSinDocSol by ID
     *
     * @param logContSinDocSolidLogContSinDocSol Identification field logContSinDocSolidLogContSinDocSol
     *
     * @return Instance of LogContSinDocSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContSinDocSol getById(Long logContSinDocSolidLogContSinDocSol) {
        return getByOID(new LogContSinDocSolOid(logContSinDocSolidLogContSinDocSol));
    }
    
    /*
     * Returns an instance of LogContSinDocSol by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContSinDocSol
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContSinDocSol getByOID(LogContSinDocSolOid oid) {
        Optional<LogContSinDocSol> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogContSinDocSol by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogContSinDocSol getByOIDWithHV(LogContSinDocSolOid oid) {
        Optional<LogContSinDocSol> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogContSinDocSol> getInstanceByOid(LogContSinDocSolOid oid) {
        return oid != null && oid.getLogContSinDocSolidLogContSinDocSol() != null
               ? repository.findById(oid.getLogContSinDocSolidLogContSinDocSol())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogContSinDocSol by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogContSinDocSol
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContSinDocSol getByJsonOid(String jsonOid) {
        LogContSinDocSolOid oid = new LogContSinDocSolOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of LogContSinDocSol
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogContSinDocSol
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogContSinDocSolDynamic(LogContSinDocSolOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogContSinDocSolOid oid, String dsName) {
        LogContSinDocSol instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogContSinDocSol instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (LogContSinDocSolConstants.FILTER_NAME_FLOGCONTSINDOCSOL.equalsIgnoreCase(filterRequest.getName()))
            fLogContSinDocSol(filterRequest, sql);
    }


    private void fLogContSinDocSol(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Date vfDesde = filterRequest.hasFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFDESDE) ? (Date) filterRequest.getFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFDESDE, FilterVariableRequest.DATE) : null;
        Date vfHasta = filterRequest.hasFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFHASTA) ? (Date) filterRequest.getFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFHASTA, FilterVariableRequest.DATE) : null;
        String vfNumCont = filterRequest.hasFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFNUMCONT) ? (String) filterRequest.getFilterVariable(LogContSinDocSolConstants.VAR_NAME_FLOGCONTSINDOCSOL_VFNUMCONT, FilterVariableRequest.STRING) : null;

        // Fecha > toDateTime(vfDesde, formatToTime(0, 0, 0))
        if (vfDesde != null) {
            // '>' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator(">");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogContSinDocSolConstants.ATTR_NAME_LOGCONTSINDOCSOLFECHA, Constants.Type.DATETIME.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
                        if (STDFunctions.toDateTimeFun(vfDesde, STDFunctions.formatToTimeFun(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0))) != null) {
                            comparison1.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.toDateTimeFun(vfDesde, STDFunctions.formatToTimeFun(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0))), Constants.Type.DATETIME.getTypeName()));
                        } else {
                            comparison1.setSecondOperand(new SQLWhereOperandNull());
                                }
            conjunction.addOperand(comparison1);

        }        

        // Fecha < toDateTime(vfHasta, formatToTime(23, 59, 59))
        if (vfHasta != null) {
            // '<' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("<");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogContSinDocSolConstants.ATTR_NAME_LOGCONTSINDOCSOLFECHA, Constants.Type.DATETIME.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
                        if (STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))) != null) {
                            comparison2.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))), Constants.Type.DATETIME.getTypeName()));
                        } else {
                            comparison2.setSecondOperand(new SQLWhereOperandNull());
                                }
            conjunction.addOperand(comparison2);

        }        

        // NumContrato LIKE "%" + vfNumCont
        if (vfNumCont != null) {
            // 'like' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(LogContSinDocSolConstants.ATTR_NAME_LOGCONTSINDOCSOLNUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNumCont, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIULogContSinDocSol
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogContSinDocSol using displayset DSLogContSinDocSol that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.ds.DSLogContSinDocSol> piuPIULogContSinDocSol(QueryRequest queryRequest) {
        return solveLogContSinDocSolTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.ds.DSLogContSinDocSol::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogContSinDocSol
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogContSinDocSol
     */
    public int getCount4PIULogContSinDocSol(QueryRequest queryRequest) {
        return solveLogContSinDocSolTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContSinDocSolSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.CrearRequest request) throws ModelException {
        return logContSinDocSolSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String logContSinDocSolcrearpatrNumContrato = request.getLogContSinDocSolcrearpatrNumContrato();
        String logContSinDocSolcrearpatrUsuario = request.getLogContSinDocSolcrearpatrUsuario();

        request.checkArguments();
        LogContSinDocSol instance = new LogContSinDocSol();
        instance.setLogContSinDocSolidLogContSinDocSol(Long.valueOf(0));
        instance.setLogContSinDocSolFecha(STDFunctions.systemDateTimeFun());
        instance.setLogContSinDocSolNumContrato(logContSinDocSolcrearpatrNumContrato);
        instance.setLogContSinDocSolUsuario(logContSinDocSolcrearpatrUsuario);

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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContSinDocSolSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.EliminarRequest request) throws ModelException {
        request.setLogContSinDocSoleliminarpthisLogContSinDocSolInstance(logContSinDocSolSrv.getByOIDWithHV(request.getLogContSinDocSoleliminarpthisLogContSinDocSol()));
        return logContSinDocSolSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogContSinDocSol logContSinDocSoleliminarpthisLogContSinDocSol = request.getLogContSinDocSoleliminarpthisLogContSinDocSolInstance();

        request.checkArguments();
        LogContSinDocSol instance = logContSinDocSoleliminarpthisLogContSinDocSol;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogContSinDocSol(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogContSinDocSolConstants.CLASS_NAME, LogContSinDocSolConstants.ATTR_FIELD_LOGCONTSINDOCSOLIDLOGCONTSINDOCSOL, false), true);
    }


    private void applyOCOCLogContSinDocSol(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogContSinDocSol".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogContSinDocSolConstants.CLASS_NAME, LogContSinDocSolConstants.ATTR_NAME_LOGCONTSINDOCSOLFECHA, false), false);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogContSinDocSolOid oid = new LogContSinDocSolOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogContSinDocSolConstants.ATTR_FIELD_LOGCONTSINDOCSOLIDLOGCONTSINDOCSOL, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogContSinDocSolidLogContSinDocSol(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogContSinDocSolService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
