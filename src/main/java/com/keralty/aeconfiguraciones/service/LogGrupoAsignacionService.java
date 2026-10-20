package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogGrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogGrupoAsignacionOid;
import com.keralty.aeconfiguraciones.repository.LogGrupoAsignacionJpaRepository;
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
public class LogGrupoAsignacionService extends AbstractService {

    LogGrupoAsignacionJpaRepository repository;

    LogGrupoAsignacionService logGrupoAsignacionSrv;

    GrupoAsignacionService grupoAsignacionSrv;

    @Inject
    public LogGrupoAsignacionService(
            LogGrupoAsignacionService logGrupoAsignacionSrv,
            GrupoAsignacionService grupoAsignacionSrv,
            LogGrupoAsignacionJpaRepository repository) {
        this.logGrupoAsignacionSrv = logGrupoAsignacionSrv;
        this.grupoAsignacionSrv = grupoAsignacionSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogGrupoAsignacionConstants.CLASS_NAME);
    }

    private List<LogGrupoAsignacion> solveLogGrupoAsignacionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogGrupoAsignacionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogGrupoAsignacionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogGrupoAsignacionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogGrupoAsignacion
     *
     * @return Population of class LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGrupoAsignacion> get() {
        List<LogGrupoAsignacion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogGrupoAsignacion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogGrupoAsignacion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGrupoAsignacion> get(QueryRequest queryRequest) {
        return solveLogGrupoAsignacionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogGrupoAsignacionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogGrupoAsignacionTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogGrupoAsignacionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogGrupoAsignacion by ID
     *
     * @param logGrupoAsignacionId Identification field logGrupoAsignacionId
     *
     * @return Instance of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGrupoAsignacion getById(Long logGrupoAsignacionId) {
        return getByOID(new LogGrupoAsignacionOid(logGrupoAsignacionId));
    }
    
    /*
     * Returns an instance of LogGrupoAsignacion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGrupoAsignacion getByOID(LogGrupoAsignacionOid oid) {
        Optional<LogGrupoAsignacion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogGrupoAsignacion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogGrupoAsignacion getByOIDWithHV(LogGrupoAsignacionOid oid) {
        Optional<LogGrupoAsignacion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogGrupoAsignacion> getInstanceByOid(LogGrupoAsignacionOid oid) {
        return oid != null && oid.getLogGrupoAsignacionId() != null
               ? repository.findById(oid.getLogGrupoAsignacionId())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogGrupoAsignacion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogGrupoAsignacion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGrupoAsignacion getByJsonOid(String jsonOid) {
        LogGrupoAsignacionOid oid = new LogGrupoAsignacionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogGrupoAsignacion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGrupoAsignacion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogGrupoAsignacionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogGrupoAsignacion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGrupoAsignacion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogGrupoAsignacion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogGrupoAsignacionDynamic(LogGrupoAsignacionOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogGrupoAsignacionOid oid, String dsName) {
        LogGrupoAsignacion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogGrupoAsignacion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogGrupoAsignacion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogGrupoAsignacion using displayset DSLogGrupoAsignacion that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.ds.DSLogGrupoAsignacion> piuPIULogGrupoAsignacion(QueryRequest queryRequest) {
        return solveLogGrupoAsignacionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.ds.DSLogGrupoAsignacion::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogGrupoAsignacion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogGrupoAsignacion
     */
    public int getCount4PIULogGrupoAsignacion(QueryRequest queryRequest) {
        return solveLogGrupoAsignacionTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGrupoAsignacionSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest request) throws ModelException {
        request.setLogGrupoAsignacioncrearpagrGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getLogGrupoAsignacioncrearpagrGrupoAsignacion()));
        return logGrupoAsignacionSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion logGrupoAsignacioncrearpagrGrupoAsignacion = request.getLogGrupoAsignacioncrearpagrGrupoAsignacionInstance();
        String logGrupoAsignacioncrearpatrLoginFuncionario = request.getLogGrupoAsignacioncrearpatrLoginFuncionario();
        String logGrupoAsignacioncrearpatrAccion = request.getLogGrupoAsignacioncrearpatrAccion();
        String logGrupoAsignacioncrearpatrValor = request.getLogGrupoAsignacioncrearpatrValor();

        request.checkArguments();
        LogGrupoAsignacion instance = new LogGrupoAsignacion();
        instance.add2GrupoAsignacion(logGrupoAsignacioncrearpagrGrupoAsignacion);

        instance.setLogGrupoAsignacionId(Long.valueOf(0));
        instance.setLogGrupoAsignacionFecha(STDFunctions.systemDateTimeFun());
        instance.setLogGrupoAsignacionLoginFuncionario(logGrupoAsignacioncrearpatrLoginFuncionario);
        instance.setLogGrupoAsignacionAccion(logGrupoAsignacioncrearpatrAccion);
        instance.setLogGrupoAsignacionValor(logGrupoAsignacioncrearpatrValor);

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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGrupoAsignacionSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.EliminarRequest request) throws ModelException {
        request.setLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance(logGrupoAsignacionSrv.getByOIDWithHV(request.getLogGrupoAsignacioneliminarpthisLogGrupoAsignacion()));
        return logGrupoAsignacionSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogGrupoAsignacion logGrupoAsignacioneliminarpthisLogGrupoAsignacion = request.getLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance();

        request.checkArguments();
        LogGrupoAsignacion instance = logGrupoAsignacioneliminarpthisLogGrupoAsignacion;


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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGrupoAsignacionSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest request) throws ModelException {
        request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getLogGrupoAsignacionTCREARptpagrGrupoAsignacion()));
        return logGrupoAsignacionSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion logGrupoAsignacionTCREARptpagrGrupoAsignacion = request.getLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance();
        String logGrupoAsignacionTCREARptpatrAccion = request.getLogGrupoAsignacionTCREARptpatrAccion();
        String logGrupoAsignacionTCREARptpatrValor = request.getLogGrupoAsignacionTCREARptpatrValor();

        request.checkArguments();
        LogGrupoAsignacion instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest();
            step10Request.setLogGrupoAsignacioncrearpagrGrupoAsignacionInstance(logGrupoAsignacionTCREARptpagrGrupoAsignacion);
            step10Request.setLogGrupoAsignacioncrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogGrupoAsignacioncrearpatrAccion(logGrupoAsignacionTCREARptpatrAccion);
            step10Request.setLogGrupoAsignacioncrearpatrValor(logGrupoAsignacionTCREARptpatrValor);
            instance = (LogGrupoAsignacion) logGrupoAsignacionSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.CrearRequest();
            step11Request.setLogGrupoAsignacioncrearpagrGrupoAsignacionInstance(logGrupoAsignacionTCREARptpagrGrupoAsignacion);
            step11Request.setLogGrupoAsignacioncrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogGrupoAsignacioncrearpatrAccion(logGrupoAsignacionTCREARptpatrAccion);
            step11Request.setLogGrupoAsignacioncrearpatrValor(logGrupoAsignacionTCREARptpatrValor);
            instance = (LogGrupoAsignacion) logGrupoAsignacionSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogGrupoAsignacion(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogGrupoAsignacionConstants.CLASS_NAME, LogGrupoAsignacionConstants.ATTR_FIELD_LOGGRUPOASIGNACIONID, false), true);
    }


    private void applyOCOCLogGrupoAsignacion(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogGrupoAsignacion".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogGrupoAsignacionConstants.CLASS_NAME, LogGrupoAsignacionConstants.ATTR_NAME_LOGGRUPOASIGNACIONFECHA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogGrupoAsignacionRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogGrupoAsignacionConstants.ROLE_NAME_GRUPOASIGNACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogGrupoAsignacionRelatedInstance4GrupoAsignacion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogGrupoAsignacionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogGrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, LogGrupoAsignacionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildLogGrupoAsignacionRelatedInstance4GrupoAsignacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionOid.class)): GrupoAsignacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogGrupoAsignacionOid oid = new LogGrupoAsignacionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogGrupoAsignacionConstants.ATTR_FIELD_LOGGRUPOASIGNACIONID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogGrupoAsignacionId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogGrupoAsignacionService.class).error(e.getMessage());
        }
    }

}
