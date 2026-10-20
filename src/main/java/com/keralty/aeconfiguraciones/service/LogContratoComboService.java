package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogContratoComboConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogContratoCombo;
import com.keralty.aeconfiguraciones.persistence.oid.LogContratoComboOid;
import com.keralty.aeconfiguraciones.repository.LogContratoComboJpaRepository;
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
public class LogContratoComboService extends AbstractService {

    LogContratoComboJpaRepository repository;

    LogContratoComboService logContratoComboSrv;

    @Inject
    public LogContratoComboService(
            LogContratoComboService logContratoComboSrv,
            LogContratoComboJpaRepository repository) {
        this.logContratoComboSrv = logContratoComboSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogContratoComboConstants.CLASS_NAME);
    }

    private List<LogContratoCombo> solveLogContratoComboTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogContratoComboConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogContratoComboTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogContratoComboConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogContratoCombo
     *
     * @return Population of class LogContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContratoCombo> get() {
        List<LogContratoCombo> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogContratoCombo that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogContratoCombo matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogContratoCombo> get(QueryRequest queryRequest) {
        return solveLogContratoComboTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogContratoComboTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogContratoComboTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of LogContratoCombo by ID
     *
     * @param logContratoComboId Identification field logContratoComboId
     *
     * @return Instance of LogContratoCombo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratoCombo getById(Long logContratoComboId) {
        return getByOID(new LogContratoComboOid(logContratoComboId));
    }
    
    /*
     * Returns an instance of LogContratoCombo by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContratoCombo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratoCombo getByOID(LogContratoComboOid oid) {
        Optional<LogContratoCombo> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogContratoCombo by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogContratoCombo getByOIDWithHV(LogContratoComboOid oid) {
        Optional<LogContratoCombo> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogContratoCombo> getInstanceByOid(LogContratoComboOid oid) {
        return oid != null && oid.getLogContratoComboId() != null
               ? repository.findById(oid.getLogContratoComboId())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogContratoCombo by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogContratoCombo
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogContratoCombo getByJsonOid(String jsonOid) {
        LogContratoComboOid oid = new LogContratoComboOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of LogContratoCombo
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogContratoCombo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogContratoComboDynamic(LogContratoComboOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogContratoComboOid oid, String dsName) {
        LogContratoCombo instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogContratoCombo instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (LogContratoComboConstants.FILTER_NAME_FLOGCONTRATOCOMBO.equalsIgnoreCase(filterRequest.getName()))
            fLogContratoCombo(filterRequest, sql);
    }


    private void fLogContratoCombo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Date vfDesde = filterRequest.hasFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFDESDE) ? (Date) filterRequest.getFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFDESDE, FilterVariableRequest.DATE) : null;
        Date vfHasta = filterRequest.hasFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFHASTA) ? (Date) filterRequest.getFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFHASTA, FilterVariableRequest.DATE) : null;
        String vfNum = filterRequest.hasFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFNUM) ? (String) filterRequest.getFilterVariable(LogContratoComboConstants.VAR_NAME_FLOGCONTRATOCOMBO_VFNUM, FilterVariableRequest.STRING) : null;

        // Fecha >= toDateTime(vfDesde, formatToTime(0, 0, 0))
        if (vfDesde != null) {
            // '>=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOFECHA, Constants.Type.DATETIME.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
                        if (STDFunctions.toDateTimeFun(vfDesde, STDFunctions.formatToTimeFun(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0))) != null) {
                            comparison1.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.toDateTimeFun(vfDesde, STDFunctions.formatToTimeFun(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0))), Constants.Type.DATETIME.getTypeName()));
                        } else {
                            comparison1.setSecondOperand(new SQLWhereOperandNull());
                                }
            conjunction.addOperand(comparison1);

        }        

        // Fecha <= toDateTime(vfHasta, formatToTime(23, 59, 59))
        if (vfHasta != null) {
            // '<=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("<=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOFECHA, Constants.Type.DATETIME.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
                        if (STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))) != null) {
                            comparison2.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.toDateTimeFun(vfHasta, STDFunctions.formatToTimeFun(Long.valueOf(23), Long.valueOf(59), Long.valueOf(59))), Constants.Type.DATETIME.getTypeName()));
                        } else {
                            comparison2.setSecondOperand(new SQLWhereOperandNull());
                                }
            conjunction.addOperand(comparison2);

        }        

        // ContratoIntegral LIKE "%" + vfNum
        if (vfNum != null) {
            // 'like' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOINTEGRAL, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNum, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // ContratoOdontologico LIKE "%" + vfNum
        if (vfNum != null) {
            // 'like' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOCONTRATOODONTOLOGICO, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNum, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIULogContratoCombo
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogContratoCombo using displayset DSLogContratoCombo that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.ds.DSLogContratoCombo> piuPIULogContratoCombo(QueryRequest queryRequest) {
        return solveLogContratoComboTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.ds.DSLogContratoCombo::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogContratoCombo
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogContratoCombo
     */
    public int getCount4PIULogContratoCombo(QueryRequest queryRequest) {
        return solveLogContratoComboTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratoComboSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest request) throws ModelException {
        return logContratoComboSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String logContratoCombocrearpatrLoginFuncionario = request.getLogContratoCombocrearpatrLoginFuncionario();
        String logContratoCombocrearpatrContratoIntegral = request.getLogContratoCombocrearpatrContratoIntegral();
        String logContratoCombocrearpatrContratoOdontologico = request.getLogContratoCombocrearpatrContratoOdontologico();
        String logContratoCombocrearpatrAccion = request.getLogContratoCombocrearpatrAccion();

        request.checkArguments();
        LogContratoCombo instance = new LogContratoCombo();
        instance.setLogContratoComboId(Long.valueOf(0));
        instance.setLogContratoComboFecha(STDFunctions.systemDateTimeFun());
        instance.setLogContratoComboLoginFuncionario(logContratoCombocrearpatrLoginFuncionario);
        instance.setLogContratoComboContratoIntegral(logContratoCombocrearpatrContratoIntegral);
        instance.setLogContratoComboContratoOdontologico(logContratoCombocrearpatrContratoOdontologico);
        instance.setLogContratoComboAccion(logContratoCombocrearpatrAccion);

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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratoComboSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.EliminarRequest request) throws ModelException {
        request.setLogContratoComboeliminarpthisLogContratoComboInstance(logContratoComboSrv.getByOIDWithHV(request.getLogContratoComboeliminarpthisLogContratoCombo()));
        return logContratoComboSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogContratoCombo logContratoComboeliminarpthisLogContratoCombo = request.getLogContratoComboeliminarpthisLogContratoComboInstance();

        request.checkArguments();
        LogContratoCombo instance = logContratoComboeliminarpthisLogContratoCombo;


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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logContratoComboSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest request) throws ModelException {
        return logContratoComboSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String logContratoComboTCREARptpatrContratoIntegral = request.getLogContratoComboTCREARptpatrContratoIntegral();
        String logContratoComboTCREARptpatrContratoOdontologico = request.getLogContratoComboTCREARptpatrContratoOdontologico();
        String logContratoComboTCREARptpatrAccion = request.getLogContratoComboTCREARptpatrAccion();

        request.checkArguments();
        LogContratoCombo instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest();
            step10Request.setLogContratoCombocrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogContratoCombocrearpatrContratoIntegral(logContratoComboTCREARptpatrContratoIntegral);
            step10Request.setLogContratoCombocrearpatrContratoOdontologico(logContratoComboTCREARptpatrContratoOdontologico);
            step10Request.setLogContratoCombocrearpatrAccion(logContratoComboTCREARptpatrAccion);
            instance = (LogContratoCombo) logContratoComboSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.CrearRequest();
            step11Request.setLogContratoCombocrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogContratoCombocrearpatrContratoIntegral(logContratoComboTCREARptpatrContratoIntegral);
            step11Request.setLogContratoCombocrearpatrContratoOdontologico(logContratoComboTCREARptpatrContratoOdontologico);
            step11Request.setLogContratoCombocrearpatrAccion(logContratoComboTCREARptpatrAccion);
            instance = (LogContratoCombo) logContratoComboSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogContratoCombo(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.ATTR_FIELD_LOGCONTRATOCOMBOID, false), true);
    }


    private void applyOCOCLogContratoCombo(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogContratoCombo".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogContratoComboConstants.CLASS_NAME, LogContratoComboConstants.ATTR_NAME_LOGCONTRATOCOMBOFECHA, false), false);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogContratoComboOid oid = new LogContratoComboOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogContratoComboConstants.ATTR_FIELD_LOGCONTRATOCOMBOID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogContratoComboId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogContratoComboService.class).error(e.getMessage());
        }
    }

}
