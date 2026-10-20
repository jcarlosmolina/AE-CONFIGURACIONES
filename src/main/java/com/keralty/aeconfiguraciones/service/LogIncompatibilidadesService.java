package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogIncompatibilidadesConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogIncompatibilidadesOid;
import com.keralty.aeconfiguraciones.repository.LogIncompatibilidadesJpaRepository;
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
public class LogIncompatibilidadesService extends AbstractService {

    LogIncompatibilidadesJpaRepository repository;

    LogIncompatibilidadesService logIncompatibilidadesSrv;

    DominioService dominioSrv;

    @Inject
    public LogIncompatibilidadesService(
            LogIncompatibilidadesService logIncompatibilidadesSrv,
            DominioService dominioSrv,
            LogIncompatibilidadesJpaRepository repository) {
        this.logIncompatibilidadesSrv = logIncompatibilidadesSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogIncompatibilidadesConstants.CLASS_NAME);
    }

    private List<LogIncompatibilidades> solveLogIncompatibilidadesTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogIncompatibilidadesConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogIncompatibilidadesTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogIncompatibilidadesConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogIncompatibilidades
     *
     * @return Population of class LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogIncompatibilidades> get() {
        List<LogIncompatibilidades> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogIncompatibilidades that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogIncompatibilidades matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogIncompatibilidades> get(QueryRequest queryRequest) {
        return solveLogIncompatibilidadesTypedQuery(queryRequest);
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
        int requestedPageSize = queryRequest.getPageSize();
        int pageSize = requestedPageSize >= 0
                && requestedPageSize <= Constants.MAX_BLOCKSIZE
                ? requestedPageSize
                : Constants.DEFAULT_BLOCKSIZE;
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveLogIncompatibilidadesTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), pageSize != 0 ? solveLogIncompatibilidadesTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogIncompatibilidadesTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogIncompatibilidades by ID
     *
     * @param logIncompatibilidadesidLogIncompatibilidades Identification field logIncompatibilidadesidLogIncompatibilidades
     *
     * @return Instance of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogIncompatibilidades getById(Long logIncompatibilidadesidLogIncompatibilidades) {
        return getByOID(new LogIncompatibilidadesOid(logIncompatibilidadesidLogIncompatibilidades));
    }
    
    /*
     * Returns an instance of LogIncompatibilidades by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogIncompatibilidades getByOID(LogIncompatibilidadesOid oid) {
        Optional<LogIncompatibilidades> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogIncompatibilidades by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogIncompatibilidades getByOIDWithHV(LogIncompatibilidadesOid oid) {
        Optional<LogIncompatibilidades> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogIncompatibilidades> getInstanceByOid(LogIncompatibilidadesOid oid) {
        return oid != null && oid.getLogIncompatibilidadesidLogIncompatibilidades() != null
               ? repository.findById(oid.getLogIncompatibilidadesidLogIncompatibilidades())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogIncompatibilidades by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogIncompatibilidades
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogIncompatibilidades getByJsonOid(String jsonOid) {
        LogIncompatibilidadesOid oid = new LogIncompatibilidadesOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogIncompatibilidades related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogIncompatibilidades> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogIncompatibilidadesTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogIncompatibilidades related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogIncompatibilidades> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogIncompatibilidades
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogIncompatibilidades
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogIncompatibilidadesDynamic(LogIncompatibilidadesOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogIncompatibilidadesOid oid, String dsName) {
        LogIncompatibilidades instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogIncompatibilidades instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogIncompatibilidades
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogIncompatibilidades using displayset DSLogIncompatibilidades that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.ds.DSLogIncompatibilidades> piuPIULogIncompatibilidades(QueryRequest queryRequest) {
        return solveLogIncompatibilidadesTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.ds.DSLogIncompatibilidades::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogIncompatibilidades
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogIncompatibilidades
     */
    public int getCount4PIULogIncompatibilidades(QueryRequest queryRequest) {
        return solveLogIncompatibilidadesTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logIncompatibilidadesSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest request) throws ModelException {
        request.setLogIncompatibilidadescrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogIncompatibilidadescrearpagrDominio()));
        return logIncompatibilidadesSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logIncompatibilidadescrearpagrDominio = request.getLogIncompatibilidadescrearpagrDominioInstance();
        String logIncompatibilidadescrearpatrLoginFuncionario = request.getLogIncompatibilidadescrearpatrLoginFuncionario();
        String logIncompatibilidadescrearpatrAccion = request.getLogIncompatibilidadescrearpatrAccion();
        String logIncompatibilidadescrearpatrNomDominio = request.getLogIncompatibilidadescrearpatrNomDominio();

        request.checkArguments();
        LogIncompatibilidades instance = new LogIncompatibilidades();
        instance.add2Dominio(logIncompatibilidadescrearpagrDominio);

        instance.setLogIncompatibilidadesidLogIncompatibilidades(Long.valueOf(0));
        instance.setLogIncompatibilidadesFecha(STDFunctions.systemDateTimeFun());
        instance.setLogIncompatibilidadesLoginFuncionario(logIncompatibilidadescrearpatrLoginFuncionario);
        instance.setLogIncompatibilidadesAccion(logIncompatibilidadescrearpatrAccion);
        instance.setLogIncompatibilidadesNomDominio(logIncompatibilidadescrearpatrNomDominio);

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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logIncompatibilidadesSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.EliminarRequest request) throws ModelException {
        request.setLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance(logIncompatibilidadesSrv.getByOIDWithHV(request.getLogIncompatibilidadeseliminarpthisLogIncompatibilidades()));
        return logIncompatibilidadesSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogIncompatibilidades logIncompatibilidadeseliminarpthisLogIncompatibilidades = request.getLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance();

        request.checkArguments();
        LogIncompatibilidades instance = logIncompatibilidadeseliminarpthisLogIncompatibilidades;


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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logIncompatibilidadesSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest request) throws ModelException {
        request.setLogIncompatibilidadesTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogIncompatibilidadesTCREARptpagrDominio()));
        return logIncompatibilidadesSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logIncompatibilidadesTCREARptpagrDominio = request.getLogIncompatibilidadesTCREARptpagrDominioInstance();
        String logIncompatibilidadesTCREARptpatrAccion = request.getLogIncompatibilidadesTCREARptpatrAccion();
        String logIncompatibilidadesTCREARptpatrNomDominio = request.getLogIncompatibilidadesTCREARptpatrNomDominio();

        request.checkArguments();
        LogIncompatibilidades instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest();
            step10Request.setLogIncompatibilidadescrearpagrDominioInstance(logIncompatibilidadesTCREARptpagrDominio);
            step10Request.setLogIncompatibilidadescrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogIncompatibilidadescrearpatrAccion(logIncompatibilidadesTCREARptpatrAccion);
            step10Request.setLogIncompatibilidadescrearpatrNomDominio(logIncompatibilidadesTCREARptpatrNomDominio);
            instance = (LogIncompatibilidades) logIncompatibilidadesSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.CrearRequest();
            step11Request.setLogIncompatibilidadescrearpagrDominioInstance(logIncompatibilidadesTCREARptpagrDominio);
            step11Request.setLogIncompatibilidadescrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogIncompatibilidadescrearpatrAccion(logIncompatibilidadesTCREARptpatrAccion);
            step11Request.setLogIncompatibilidadescrearpatrNomDominio(logIncompatibilidadesTCREARptpatrNomDominio);
            instance = (LogIncompatibilidades) logIncompatibilidadesSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogIncompatibilidades(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogIncompatibilidadesConstants.CLASS_NAME, LogIncompatibilidadesConstants.ATTR_FIELD_LOGINCOMPATIBILIDADESIDLOGINCOMPATIBILIDADES, false), true);
    }


    private void applyOCOCLogIncompatibilidades(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogIncompatibilidades".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogIncompatibilidadesConstants.CLASS_NAME, LogIncompatibilidadesConstants.ATTR_NAME_LOGINCOMPATIBILIDADESFECHA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogIncompatibilidadesRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogIncompatibilidadesConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogIncompatibilidadesRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogIncompatibilidadesRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogIncompatibilidades(mapper.readValue(relatedInstanceJsonOid, LogIncompatibilidadesOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildLogIncompatibilidadesRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogIncompatibilidadesOid oid = new LogIncompatibilidadesOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogIncompatibilidadesConstants.ATTR_FIELD_LOGINCOMPATIBILIDADESIDLOGINCOMPATIBILIDADES, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogIncompatibilidadesidLogIncompatibilidades(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogIncompatibilidadesService.class).error(e.getMessage());
        }
    }

}
