package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.LogLlamadasAEConstants;
import com.keralty.aeconfiguraciones.persistence.LogLlamadasAE;
import com.keralty.aeconfiguraciones.persistence.oid.LogLlamadasAEOid;
import com.keralty.aeconfiguraciones.repository.LogLlamadasAEJpaRepository;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class LogLlamadasAEService extends AbstractService {

    LogLlamadasAEJpaRepository repository;

    LogLlamadasAEService logLlamadasAESrv;

    @Inject
    public LogLlamadasAEService(
            LogLlamadasAEService logLlamadasAESrv,
            LogLlamadasAEJpaRepository repository) {
        this.logLlamadasAESrv = logLlamadasAESrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogLlamadasAEConstants.CLASS_NAME);
    }

    private List<LogLlamadasAE> solveLogLlamadasAETypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogLlamadasAEConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogLlamadasAETypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogLlamadasAEConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogLlamadasAE
     *
     * @return Population of class LogLlamadasAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogLlamadasAE> get() {
        List<LogLlamadasAE> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogLlamadasAE that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogLlamadasAE matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogLlamadasAE> get(QueryRequest queryRequest) {
        return solveLogLlamadasAETypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogLlamadasAETypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogLlamadasAETypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of LogLlamadasAE by ID
     *
     * @param logLlamadasAEID Identification field logLlamadasAEID
     *
     * @return Instance of LogLlamadasAE
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogLlamadasAE getById(Long logLlamadasAEID) {
        return getByOID(new LogLlamadasAEOid(logLlamadasAEID));
    }
    
    /*
     * Returns an instance of LogLlamadasAE by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogLlamadasAE
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogLlamadasAE getByOID(LogLlamadasAEOid oid) {
        Optional<LogLlamadasAE> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogLlamadasAE by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogLlamadasAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogLlamadasAE getByOIDWithHV(LogLlamadasAEOid oid) {
        Optional<LogLlamadasAE> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogLlamadasAE> getInstanceByOid(LogLlamadasAEOid oid) {
        return oid != null && oid.getLogLlamadasAEID() != null
               ? repository.findById(oid.getLogLlamadasAEID())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogLlamadasAE by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogLlamadasAE
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogLlamadasAE getByJsonOid(String jsonOid) {
        LogLlamadasAEOid oid = new LogLlamadasAEOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of LogLlamadasAE
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogLlamadasAE
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogLlamadasAEDynamic(LogLlamadasAEOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogLlamadasAEOid oid, String dsName) {
        LogLlamadasAE instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogLlamadasAE instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (LogLlamadasAEConstants.FILTER_NAME_FLOGLLAMADASAE.equalsIgnoreCase(filterRequest.getName()))
            fLogLlamadasAE(filterRequest, sql);
    }


    private void fLogLlamadasAE(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Timestamp vfDesde = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFDESDE) ? (Timestamp) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFDESDE, FilterVariableRequest.DATETIME) : null;
        Timestamp vfHasta = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFHASTA) ? (Timestamp) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFHASTA, FilterVariableRequest.DATETIME) : null;
        String vfCapa = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFCAPA) ? (String) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFCAPA, FilterVariableRequest.STRING) : null;
        String vfServicio = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFSERVICIO) ? (String) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFSERVICIO, FilterVariableRequest.STRING) : null;
        String vfTipoServicio = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFTIPOSERVICIO) ? (String) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFTIPOSERVICIO, FilterVariableRequest.STRING) : null;
        String vfNumSolicitud = filterRequest.hasFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFNUMSOLICITUD) ? (String) filterRequest.getFilterVariable(LogLlamadasAEConstants.VAR_NAME_FLOGLLAMADASAE_VFNUMSOLICITUD, FilterVariableRequest.STRING) : null;

        // FechaHora >= vfDesde
        if (vfDesde != null) {
            // '>=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAEFECHAHORA, Constants.Type.DATETIME.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfDesde, Constants.Type.DATETIME.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // FechaHora <= vfHasta
        if (vfHasta != null) {
            // '<=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("<=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAEFECHAHORA, Constants.Type.DATETIME.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vfHasta, Constants.Type.DATETIME.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // Capacidad LIKE "%" + vfCapa
        if (vfCapa != null) {
            // 'like' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAECAPACIDAD, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral("%" + vfCapa, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // Servicio LIKE "%" + vfServicio
        if (vfServicio != null) {
            // 'like' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAESERVICIO, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vfServicio, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // TipoServicio LIKE "%" + vfTipoServicio
        if (vfTipoServicio != null) {
            // 'like' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAETIPOSERVICIO, Constants.Type.STRING.getTypeName());
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            comparison5.setSecondOperand(new SQLWhereOperandLiteral("%" + vfTipoServicio, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison5);

        }        

        // Solicitud LIKE "%" + vfNumSolicitud
        if (vfNumSolicitud != null) {
            // 'like' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAESOLICITUD, Constants.Type.STRING.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNumSolicitud, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods

    /*
     * Returns a viewmodel for class LogLlamadasAE with displayset DSLogLlamadasAEIIU for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class LogLlamadasAE with displayset DSLogLlamadasAEIIU
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAEIIU iIULogLlamadasAE(LogLlamadasAEOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAEIIU(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIULogLlamadasAE
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogLlamadasAE using displayset DSLogLlamadasAE that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAE> piuPIULogLlamadasAE(QueryRequest queryRequest) {
        return solveLogLlamadasAETypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logllamadasae.ds.DSLogLlamadasAE::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogLlamadasAE
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogLlamadasAE
     */
    public int getCount4PIULogLlamadasAE(QueryRequest queryRequest) {
        return solveLogLlamadasAETypedQuery(queryRequest).size();
    }

    // Service: createinstance

    /*
     * Entry point for service createinstance
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse createinstance(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.CreateinstanceRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logLlamadasAESrv.createinstanceTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service createinstance
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse createinstanceTxn(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.CreateinstanceRequest request) throws ModelException {
        return logLlamadasAESrv.createinstanceImplementation(request);
    }

    /*
     * Implements service createinstance
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse createinstanceImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.CreateinstanceRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Timestamp logLlamadasAEcreateinstancepatrFechaHora = request.getLogLlamadasAEcreateinstancepatrFechaHora();
        String logLlamadasAEcreateinstancepatrCapacidad = request.getLogLlamadasAEcreateinstancepatrCapacidad();
        String logLlamadasAEcreateinstancepatrServicio = request.getLogLlamadasAEcreateinstancepatrServicio();
        String logLlamadasAEcreateinstancepatrSolicitud = request.getLogLlamadasAEcreateinstancepatrSolicitud();
        String logLlamadasAEcreateinstancepatrDatosRecibidos = request.getLogLlamadasAEcreateinstancepatrDatosRecibidos();
        String logLlamadasAEcreateinstancepatrDatosEnviados = request.getLogLlamadasAEcreateinstancepatrDatosEnviados();
        String logLlamadasAEcreateinstancepatrTipoServicio = request.getLogLlamadasAEcreateinstancepatrTipoServicio();
        Double logLlamadasAEcreateinstancepatrTiempoEjecucion = request.getLogLlamadasAEcreateinstancepatrTiempoEjecucion();
        String logLlamadasAEcreateinstancepatrTextoError = request.getLogLlamadasAEcreateinstancepatrTextoError();

        request.checkArguments();
        LogLlamadasAE instance = new LogLlamadasAE();
        instance.setLogLlamadasAEID(Long.valueOf(0));
        instance.setLogLlamadasAEFechaHora(logLlamadasAEcreateinstancepatrFechaHora);
        instance.setLogLlamadasAECapacidad(logLlamadasAEcreateinstancepatrCapacidad);
        instance.setLogLlamadasAEServicio(logLlamadasAEcreateinstancepatrServicio);
        instance.setLogLlamadasAESolicitud(logLlamadasAEcreateinstancepatrSolicitud);
        instance.setLogLlamadasAEDatosRecibidos(logLlamadasAEcreateinstancepatrDatosRecibidos);
        instance.setLogLlamadasAEDatosEnviados(logLlamadasAEcreateinstancepatrDatosEnviados);
        instance.setLogLlamadasAETipoServicio(logLlamadasAEcreateinstancepatrTipoServicio);
        instance.setLogLlamadasAETiempoEjecucion(logLlamadasAEcreateinstancepatrTiempoEjecucion);
        instance.setLogLlamadasAETextoError(logLlamadasAEcreateinstancepatrTextoError);

        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: deleteinstance

    /*
     * Entry point for service deleteinstance
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse deleteinstance(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.DeleteinstanceRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logLlamadasAESrv.deleteinstanceTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service deleteinstance
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse deleteinstanceTxn(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.DeleteinstanceRequest request) throws ModelException {
        request.setLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance(logLlamadasAESrv.getByOIDWithHV(request.getLogLlamadasAEdeleteinstancepthisLogLlamadasAE()));
        return logLlamadasAESrv.deleteinstanceImplementation(request);
    }

    /*
     * Implements service deleteinstance
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse deleteinstanceImplementation(
            com.keralty.aeconfiguraciones.viewmodel.logllamadasae.siu.DeleteinstanceRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogLlamadasAE logLlamadasAEdeleteinstancepthisLogLlamadasAE = request.getLogLlamadasAEdeleteinstancepthisLogLlamadasAEInstance();

        request.checkArguments();
        LogLlamadasAE instance = logLlamadasAEdeleteinstancepthisLogLlamadasAE;


        repository.delete(instance);
        
        response.setInstance(instance);

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogLlamadasAE(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogLlamadasAEConstants.CLASS_NAME, LogLlamadasAEConstants.ATTR_FIELD_LOGLLAMADASAEID, false), true);
    }


    private void applyOCOCLogLlamadasAE(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogLlamadasAE".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogLlamadasAEConstants.CLASS_NAME, LogLlamadasAEConstants.ATTR_NAME_LOGLLAMADASAEFECHAHORA, false), false);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogLlamadasAEOid oid = new LogLlamadasAEOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogLlamadasAEConstants.ATTR_FIELD_LOGLLAMADASAEID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogLlamadasAEID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogLlamadasAEService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
