package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogGruposUsuariosConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogGruposUsuariosOid;
import com.keralty.aeconfiguraciones.repository.LogGruposUsuariosJpaRepository;
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
public class LogGruposUsuariosService extends AbstractService {

    LogGruposUsuariosJpaRepository repository;

    LogGruposUsuariosService logGruposUsuariosSrv;

    DominioService dominioSrv;

    @Inject
    public LogGruposUsuariosService(
            LogGruposUsuariosService logGruposUsuariosSrv,
            DominioService dominioSrv,
            LogGruposUsuariosJpaRepository repository) {
        this.logGruposUsuariosSrv = logGruposUsuariosSrv;
        this.dominioSrv = dominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogGruposUsuariosConstants.CLASS_NAME);
    }

    private List<LogGruposUsuarios> solveLogGruposUsuariosTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogGruposUsuariosConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogGruposUsuariosTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogGruposUsuariosConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogGruposUsuarios
     *
     * @return Population of class LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGruposUsuarios> get() {
        List<LogGruposUsuarios> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogGruposUsuarios that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogGruposUsuarios matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGruposUsuarios> get(QueryRequest queryRequest) {
        return solveLogGruposUsuariosTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogGruposUsuariosTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogGruposUsuariosTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogGruposUsuariosTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogGruposUsuarios by ID
     *
     * @param logGruposUsuariosId Identification field logGruposUsuariosId
     *
     * @return Instance of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGruposUsuarios getById(Long logGruposUsuariosId) {
        return getByOID(new LogGruposUsuariosOid(logGruposUsuariosId));
    }
    
    /*
     * Returns an instance of LogGruposUsuarios by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGruposUsuarios getByOID(LogGruposUsuariosOid oid) {
        Optional<LogGruposUsuarios> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogGruposUsuarios by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogGruposUsuarios getByOIDWithHV(LogGruposUsuariosOid oid) {
        Optional<LogGruposUsuarios> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogGruposUsuarios> getInstanceByOid(LogGruposUsuariosOid oid) {
        return oid != null && oid.getLogGruposUsuariosId() != null
               ? repository.findById(oid.getLogGruposUsuariosId())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogGruposUsuarios by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogGruposUsuarios
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogGruposUsuarios getByJsonOid(String jsonOid) {
        LogGruposUsuariosOid oid = new LogGruposUsuariosOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogGruposUsuarios related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGruposUsuarios> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogGruposUsuariosTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogGruposUsuarios related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogGruposUsuarios> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogGruposUsuarios
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogGruposUsuariosDynamic(LogGruposUsuariosOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogGruposUsuariosOid oid, String dsName) {
        LogGruposUsuarios instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogGruposUsuarios instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogGruposUsuarios
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogGruposUsuarios using displayset DSLogGruposUsuarios that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.ds.DSLogGruposUsuarios> piuPIULogGruposUsuarios(QueryRequest queryRequest) {
        return solveLogGruposUsuariosTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.ds.DSLogGruposUsuarios::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogGruposUsuarios
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogGruposUsuarios
     */
    public int getCount4PIULogGruposUsuarios(QueryRequest queryRequest) {
        return solveLogGruposUsuariosTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGruposUsuariosSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest request) throws ModelException {
        request.setLogGruposUsuarioscrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogGruposUsuarioscrearpagrDominio()));
        return logGruposUsuariosSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logGruposUsuarioscrearpagrDominio = request.getLogGruposUsuarioscrearpagrDominioInstance();
        String logGruposUsuarioscrearpatrLoginFuncionario = request.getLogGruposUsuarioscrearpatrLoginFuncionario();
        Long logGruposUsuarioscrearpatrTipoGrupo = request.getLogGruposUsuarioscrearpatrTipoGrupo();
        String logGruposUsuarioscrearpatrAccion = request.getLogGruposUsuarioscrearpatrAccion();
        String logGruposUsuarioscrearpatrGrupo = request.getLogGruposUsuarioscrearpatrGrupo();

        request.checkArguments();
        LogGruposUsuarios instance = new LogGruposUsuarios();
        instance.add2Dominio(logGruposUsuarioscrearpagrDominio);

        instance.setLogGruposUsuariosId(Long.valueOf(0));
        instance.setLogGruposUsuariosFecha(STDFunctions.systemDateTimeFun());
        instance.setLogGruposUsuariosLoginFuncionario(logGruposUsuarioscrearpatrLoginFuncionario);
        instance.setLogGruposUsuariosTipoGrupo(logGruposUsuarioscrearpatrTipoGrupo);
        instance.setLogGruposUsuariosAccion(logGruposUsuarioscrearpatrAccion);
        instance.setLogGruposUsuariosGrupo(logGruposUsuarioscrearpatrGrupo);

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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGruposUsuariosSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.EliminarRequest request) throws ModelException {
        request.setLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance(logGruposUsuariosSrv.getByOIDWithHV(request.getLogGruposUsuarioseliminarpthisLogGruposUsuarios()));
        return logGruposUsuariosSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogGruposUsuarios logGruposUsuarioseliminarpthisLogGruposUsuarios = request.getLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance();

        request.checkArguments();
        LogGruposUsuarios instance = logGruposUsuarioseliminarpthisLogGruposUsuarios;


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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logGruposUsuariosSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest request) throws ModelException {
        request.setLogGruposUsuariosTCREARptpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getLogGruposUsuariosTCREARptpagrDominio()));
        return logGruposUsuariosSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio logGruposUsuariosTCREARptpagrDominio = request.getLogGruposUsuariosTCREARptpagrDominioInstance();
        Long logGruposUsuariosTCREARptpatrTipoGrupo = request.getLogGruposUsuariosTCREARptpatrTipoGrupo();
        String logGruposUsuariosTCREARptpatrAccion = request.getLogGruposUsuariosTCREARptpatrAccion();
        String logGruposUsuariosTCREARptpatrGrupo = request.getLogGruposUsuariosTCREARptpatrGrupo();

        request.checkArguments();
        LogGruposUsuarios instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest();
            step10Request.setLogGruposUsuarioscrearpagrDominioInstance(logGruposUsuariosTCREARptpagrDominio);
            step10Request.setLogGruposUsuarioscrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogGruposUsuarioscrearpatrTipoGrupo(logGruposUsuariosTCREARptpatrTipoGrupo);
            step10Request.setLogGruposUsuarioscrearpatrAccion(logGruposUsuariosTCREARptpatrAccion);
            step10Request.setLogGruposUsuarioscrearpatrGrupo(logGruposUsuariosTCREARptpatrGrupo);
            instance = (LogGruposUsuarios) logGruposUsuariosSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.CrearRequest();
            step11Request.setLogGruposUsuarioscrearpagrDominioInstance(logGruposUsuariosTCREARptpagrDominio);
            step11Request.setLogGruposUsuarioscrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogGruposUsuarioscrearpatrTipoGrupo(logGruposUsuariosTCREARptpatrTipoGrupo);
            step11Request.setLogGruposUsuarioscrearpatrAccion(logGruposUsuariosTCREARptpatrAccion);
            step11Request.setLogGruposUsuarioscrearpatrGrupo(logGruposUsuariosTCREARptpatrGrupo);
            instance = (LogGruposUsuarios) logGruposUsuariosSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogGruposUsuarios(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogGruposUsuariosConstants.CLASS_NAME, LogGruposUsuariosConstants.ATTR_FIELD_LOGGRUPOSUSUARIOSID, false), true);
    }


    private void applyOCOCLogGruposUsuarios(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogGruposUsuarios".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogGruposUsuariosConstants.CLASS_NAME, LogGruposUsuariosConstants.ATTR_NAME_LOGGRUPOSUSUARIOSFECHA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogGruposUsuariosRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogGruposUsuariosConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogGruposUsuariosRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogGruposUsuariosRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogGruposUsuarios(mapper.readValue(relatedInstanceJsonOid, LogGruposUsuariosOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildLogGruposUsuariosRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
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
            LogGruposUsuariosOid oid = new LogGruposUsuariosOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogGruposUsuariosConstants.ATTR_FIELD_LOGGRUPOSUSUARIOSID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogGruposUsuariosId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogGruposUsuariosService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
