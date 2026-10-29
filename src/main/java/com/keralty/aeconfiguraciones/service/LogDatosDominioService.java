package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogDatosDominioConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogDatosDominioOid;
import com.keralty.aeconfiguraciones.repository.LogDatosDominioJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class LogDatosDominioService extends AbstractService {

    LogDatosDominioJpaRepository repository;

    LogDatosDominioService logDatosDominioSrv;

    DominioService dominioSrv;

    @Inject
    public LogDatosDominioService(
            LogDatosDominioService logDatosDominioSrv,
            DominioService dominioSrv,
            LogDatosDominioJpaRepository repository) {
        this.logDatosDominioSrv = logDatosDominioSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogDatosDominioConstants.CLASS_NAME);
    }

    private List<LogDatosDominio> solveLogDatosDominioTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogDatosDominioConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogDatosDominioTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogDatosDominioConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogDatosDominio
     *
     * @return Population of class LogDatosDominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogDatosDominio> get() {
        List<LogDatosDominio> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogDatosDominio that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogDatosDominio matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogDatosDominio> get(QueryRequest queryRequest) {
        return solveLogDatosDominioTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogDatosDominioTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogDatosDominioTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveLogDatosDominioTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogDatosDominio by ID
     *
     * @param logDatosDominioidLogDatosDominio Identification field logDatosDominioidLogDatosDominio
     *
     * @return Instance of LogDatosDominio
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogDatosDominio getById(Long logDatosDominioidLogDatosDominio) {
        return getByOID(new LogDatosDominioOid(logDatosDominioidLogDatosDominio));
    }
    
    /*
     * Returns an instance of LogDatosDominio by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogDatosDominio
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogDatosDominio getByOID(LogDatosDominioOid oid) {
        Optional<LogDatosDominio> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogDatosDominio by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogDatosDominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogDatosDominio getByOIDWithHV(LogDatosDominioOid oid) {
        Optional<LogDatosDominio> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogDatosDominio> getInstanceByOid(LogDatosDominioOid oid) {
        return oid != null && oid.getLogDatosDominioidLogDatosDominio() != null
               ? repository.findById(oid.getLogDatosDominioidLogDatosDominio())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogDatosDominio by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogDatosDominio
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogDatosDominio getByJsonOid(String jsonOid) {
        LogDatosDominioOid oid = new LogDatosDominioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogDatosDominio related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogDatosDominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogDatosDominio> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogDatosDominioTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogDatosDominio related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogDatosDominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogDatosDominio> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogDatosDominio
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogDatosDominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogDatosDominioDynamic(LogDatosDominioOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogDatosDominioOid oid, String dsName) {
        LogDatosDominio instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogDatosDominio instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogDatosDominio
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogDatosDominio using displayset DSLogDatosDominio that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.ds.DSLogDatosDominio> piuPIULogDatosDominio(QueryRequest queryRequest) {
        return solveLogDatosDominioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.ds.DSLogDatosDominio::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogDatosDominio
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogDatosDominio
     */
    public int getCount4PIULogDatosDominio(QueryRequest queryRequest) {
        return solveLogDatosDominioTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logDatosDominioSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest request) throws ModelException {
        request.setLogDatosDominiocrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogDatosDominiocrearpagrDominio()));
        return logDatosDominioSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logDatosDominiocrearpagrDominio = request.getLogDatosDominiocrearpagrDominioInstance();
        String logDatosDominiocrearpatrLoginFuncionario = request.getLogDatosDominiocrearpatrLoginFuncionario();
        Boolean logDatosDominiocrearpatrTramiteEnLinea = request.getLogDatosDominiocrearpatrTramiteEnLinea();
        Boolean logDatosDominiocrearpatrVigenciaRadicacion = request.getLogDatosDominiocrearpatrVigenciaRadicacion();

        request.checkArguments();
        LogDatosDominio instance = new LogDatosDominio();
        instance.add2Dominio(logDatosDominiocrearpagrDominio);

        instance.setLogDatosDominioidLogDatosDominio(Long.valueOf(0));
        instance.setLogDatosDominioFecha(STDFunctions.systemDateTimeFun());
        instance.setLogDatosDominioLoginFuncionario(logDatosDominiocrearpatrLoginFuncionario);
        instance.setLogDatosDominioTramiteEnLinea(logDatosDominiocrearpatrTramiteEnLinea);
        instance.setLogDatosDominioVigenciaRadicacion(logDatosDominiocrearpatrVigenciaRadicacion);

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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logDatosDominioSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.EliminarRequest request) throws ModelException {
        request.setLogDatosDominioeliminarpthisLogDatosDominioInstance(logDatosDominioSrv.getByOIDWithHV(request.getLogDatosDominioeliminarpthisLogDatosDominio()));
        return logDatosDominioSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogDatosDominio logDatosDominioeliminarpthisLogDatosDominio = request.getLogDatosDominioeliminarpthisLogDatosDominioInstance();

        request.checkArguments();
        LogDatosDominio instance = logDatosDominioeliminarpthisLogDatosDominio;


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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logDatosDominioSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.TCREARRequest request) throws ModelException {
        request.setLogDatosDominioTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogDatosDominioTCREARptpagrDominio()));
        return logDatosDominioSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logDatosDominioTCREARptpagrDominio = request.getLogDatosDominioTCREARptpagrDominioInstance();
        Boolean logDatosDominioTCREARptpatrTramiteEnLinea = request.getLogDatosDominioTCREARptpatrTramiteEnLinea();
        Boolean logDatosDominioTCREARptVigenciaRadicacion = request.getLogDatosDominioTCREARptVigenciaRadicacion();

        request.checkArguments();
        LogDatosDominio instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest();
            step10Request.setLogDatosDominiocrearpagrDominioInstance(logDatosDominioTCREARptpagrDominio);
            step10Request.setLogDatosDominiocrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogDatosDominiocrearpatrTramiteEnLinea(logDatosDominioTCREARptpatrTramiteEnLinea);
            step10Request.setLogDatosDominiocrearpatrVigenciaRadicacion(logDatosDominioTCREARptVigenciaRadicacion);
            instance = (LogDatosDominio) logDatosDominioSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.CrearRequest();
            step11Request.setLogDatosDominiocrearpagrDominioInstance(logDatosDominioTCREARptpagrDominio);
            step11Request.setLogDatosDominiocrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogDatosDominiocrearpatrTramiteEnLinea(logDatosDominioTCREARptpatrTramiteEnLinea);
            step11Request.setLogDatosDominiocrearpatrVigenciaRadicacion(logDatosDominioTCREARptVigenciaRadicacion);
            instance = (LogDatosDominio) logDatosDominioSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogDatosDominio(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogDatosDominioConstants.CLASS_NAME, LogDatosDominioConstants.ATTR_FIELD_LOGDATOSDOMINIOIDLOGDATOSDOMINIO, false), true);
    }


    private void applyOCOCLogDatosDominio(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogDatosDominio".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogDatosDominioConstants.CLASS_NAME, LogDatosDominioConstants.ATTR_NAME_LOGDATOSDOMINIOFECHA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogDatosDominioRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogDatosDominioConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogDatosDominioRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogDatosDominioRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogDatosDominio(mapper.readValue(relatedInstanceJsonOid, LogDatosDominioOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildLogDatosDominioRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogDatosDominioOid oid = new LogDatosDominioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogDatosDominioConstants.ATTR_FIELD_LOGDATOSDOMINIOIDLOGDATOSDOMINIO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogDatosDominioidLogDatosDominio(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogDatosDominioService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
