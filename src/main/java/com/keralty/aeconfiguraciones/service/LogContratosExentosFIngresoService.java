package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogContratosExentosFIngresoConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.oid.LogContratosExentosFIngresoOid;
import com.keralty.aeconfiguraciones.repository.LogContratosExentosFIngresoJpaRepository;
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
public class LogContratosExentosFIngresoService extends AbstractService {

    LogContratosExentosFIngresoJpaRepository repository;

    LogContratosExentosFIngresoService logContratosExentosFIngresoSrv;

    @Inject
    public LogContratosExentosFIngresoService(
            LogContratosExentosFIngresoService logContratosExentosFIngresoSrv,
            LogContratosExentosFIngresoJpaRepository repository) {
        this.logContratosExentosFIngresoSrv = logContratosExentosFIngresoSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogContratosExentosFIngresoConstants.CLASS_NAME);
    }

    private List<LogContratosExentosFIngreso> solveLogContratosExentosFIngresoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogContratosExentosFIngresoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogContratosExentosFIngresoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogContratosExentosFIngresoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogContratosExentosFIngreso
     *
     * @return Population of class LogContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContratosExentosFIngreso> get() {
        List<LogContratosExentosFIngreso> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogContratosExentosFIngreso that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogContratosExentosFIngreso matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContratosExentosFIngreso> get(QueryRequest queryRequest) {
        return solveLogContratosExentosFIngresoTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogContratosExentosFIngresoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogContratosExentosFIngresoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of LogContratosExentosFIngreso by ID
     *
     * @param logContratosExentosFIngresoidLogContratosExentosFIngreso Identification field logContratosExentosFIngresoidLogContratosExentosFIngreso
     *
     * @return Instance of LogContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratosExentosFIngreso getById(Long logContratosExentosFIngresoidLogContratosExentosFIngreso) {
        return getByOID(new LogContratosExentosFIngresoOid(logContratosExentosFIngresoidLogContratosExentosFIngreso));
    }
    
    /*
     * Returns an instance of LogContratosExentosFIngreso by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratosExentosFIngreso getByOID(LogContratosExentosFIngresoOid oid) {
        Optional<LogContratosExentosFIngreso> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogContratosExentosFIngreso by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogContratosExentosFIngreso getByOIDWithHV(LogContratosExentosFIngresoOid oid) {
        Optional<LogContratosExentosFIngreso> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogContratosExentosFIngreso> getInstanceByOid(LogContratosExentosFIngresoOid oid) {
        return oid != null && oid.getLogContratosExentosFIngresoidLogContratosExentosFIngreso() != null
               ? repository.findById(oid.getLogContratosExentosFIngresoidLogContratosExentosFIngreso())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogContratosExentosFIngreso by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogContratosExentosFIngreso
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratosExentosFIngreso getByJsonOid(String jsonOid) {
        LogContratosExentosFIngresoOid oid = new LogContratosExentosFIngresoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of LogContratosExentosFIngreso
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogContratosExentosFIngreso
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogContratosExentosFIngresoDynamic(LogContratosExentosFIngresoOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogContratosExentosFIngresoOid oid, String dsName) {
        LogContratosExentosFIngreso instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogContratosExentosFIngreso instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (LogContratosExentosFIngresoConstants.FILTER_NAME_FLOGCONTRATOSEXENTOSFINGRESO.equalsIgnoreCase(filterRequest.getName()))
            fLogContratosExentosFIngreso(filterRequest, sql);
    }


    private void fLogContratosExentosFIngreso(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Date vfDesde = filterRequest.hasFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFDESDE) ? (Date) filterRequest.getFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFDESDE, FilterVariableRequest.DATE) : null;
        Date vfHasta = filterRequest.hasFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFHASTA) ? (Date) filterRequest.getFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFHASTA, FilterVariableRequest.DATE) : null;
        String vfNumCont = filterRequest.hasFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFNUMCONT) ? (String) filterRequest.getFilterVariable(LogContratosExentosFIngresoConstants.VAR_NAME_FLOGCONTRATOSEXENTOSFINGRESO_VFNUMCONT, FilterVariableRequest.STRING) : null;

        // Fecha > toDateTime(vfDesde, formatToTime(0, 0, 0))
        if (vfDesde != null) {
            // '>' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator(">");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogContratosExentosFIngresoConstants.ATTR_NAME_LOGCONTRATOSEXENTOSFINGRESOFECHA, Constants.Type.DATETIME.getTypeName());
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
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogContratosExentosFIngresoConstants.ATTR_NAME_LOGCONTRATOSEXENTOSFINGRESOFECHA, Constants.Type.DATETIME.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
                        if (STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))) != null) {
                            comparison2.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))), Constants.Type.DATETIME.getTypeName()));
                        } else {
                            comparison2.setSecondOperand(new SQLWhereOperandNull());
                                }
            conjunction.addOperand(comparison2);

        }        

        // NumContrato LIKE vfNumCont
        if (vfNumCont != null) {
            // 'like' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(LogContratosExentosFIngresoConstants.ATTR_NAME_LOGCONTRATOSEXENTOSFINGRESONUMCONTRATO, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vfNumCont, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIULogContratosExentosFIngres
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogContratosExentosFIngreso using displayset DSLogContratosExentosFIngreso that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.ds.DSLogContratosExentosFIngreso> piuPIULogContratosExentosFIngres(QueryRequest queryRequest) {
        return solveLogContratosExentosFIngresoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.ds.DSLogContratosExentosFIngreso::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogContratosExentosFIngres
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogContratosExentosFIngres
     */
    public int getCount4PIULogContratosExentosFIngres(QueryRequest queryRequest) {
        return solveLogContratosExentosFIngresoTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratosExentosFIngresoSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest request) throws ModelException {
        return logContratosExentosFIngresoSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String logContratosExentosFIngresocrearpatrLoginFuncionario = request.getLogContratosExentosFIngresocrearpatrLoginFuncionario();
        String logContratosExentosFIngresocrearpatrAccion = request.getLogContratosExentosFIngresocrearpatrAccion();
        String logContratosExentosFIngresocrearpatrNumContrato = request.getLogContratosExentosFIngresocrearpatrNumContrato();

        request.checkArguments();
        LogContratosExentosFIngreso instance = new LogContratosExentosFIngreso();
        instance.setLogContratosExentosFIngresoidLogContratosExentosFIngreso(Long.valueOf(0));
        instance.setLogContratosExentosFIngresoFecha(STDFunctions.systemDateTimeFun());
        instance.setLogContratosExentosFIngresoLoginFuncionario(logContratosExentosFIngresocrearpatrLoginFuncionario);
        instance.setLogContratosExentosFIngresoAccion(logContratosExentosFIngresocrearpatrAccion);
        instance.setLogContratosExentosFIngresoNumContrato(logContratosExentosFIngresocrearpatrNumContrato);

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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratosExentosFIngresoSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.EliminarRequest request) throws ModelException {
        request.setLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance(logContratosExentosFIngresoSrv.getByOIDWithHV(request.getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr()));
        return logContratosExentosFIngresoSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogContratosExentosFIngreso logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr = request.getLogContratosExentosFIngresoeliminarpthisLogContratosExentosFIngrInstance();

        request.checkArguments();
        LogContratosExentosFIngreso instance = logContratosExentosFIngresoeliminarpthisLogContratosExentosFIngr;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratosExentosFIngresoSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest request) throws ModelException {
        return logContratosExentosFIngresoSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String logContratosExentosFIngresoTCREARptpatrAccion = request.getLogContratosExentosFIngresoTCREARptpatrAccion();
        String logContratosExentosFIngresoTCREARptpatrNumContrato = request.getLogContratosExentosFIngresoTCREARptpatrNumContrato();

        request.checkArguments();
        LogContratosExentosFIngreso instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest();
            step10Request.setLogContratosExentosFIngresocrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogContratosExentosFIngresocrearpatrAccion(logContratosExentosFIngresoTCREARptpatrAccion);
            step10Request.setLogContratosExentosFIngresocrearpatrNumContrato(logContratosExentosFIngresoTCREARptpatrNumContrato);
            instance = (LogContratosExentosFIngreso) logContratosExentosFIngresoSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.CrearRequest();
            step11Request.setLogContratosExentosFIngresocrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogContratosExentosFIngresocrearpatrAccion(logContratosExentosFIngresoTCREARptpatrAccion);
            step11Request.setLogContratosExentosFIngresocrearpatrNumContrato(logContratosExentosFIngresoTCREARptpatrNumContrato);
            instance = (LogContratosExentosFIngreso) logContratosExentosFIngresoSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogContratosExentosFIngreso(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogContratosExentosFIngresoConstants.CLASS_NAME, LogContratosExentosFIngresoConstants.ATTR_FIELD_LOGCONTRATOSEXENTOSFINGRESOIDLOGCONTRATOSEXENTOSFINGRESO, false), true);
    }


    private void applyOCOCLogContratosExentosFIngreso(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogContratosExentosFIngreso".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogContratosExentosFIngresoConstants.CLASS_NAME, LogContratosExentosFIngresoConstants.ATTR_NAME_LOGCONTRATOSEXENTOSFINGRESOFECHA, false), false);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogContratosExentosFIngresoOid oid = new LogContratosExentosFIngresoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogContratosExentosFIngresoConstants.ATTR_FIELD_LOGCONTRATOSEXENTOSFINGRESOIDLOGCONTRATOSEXENTOSFINGRESO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogContratosExentosFIngresoidLogContratosExentosFIngreso(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogContratosExentosFIngresoService.class).error(e.getMessage());
        }
    }

}
