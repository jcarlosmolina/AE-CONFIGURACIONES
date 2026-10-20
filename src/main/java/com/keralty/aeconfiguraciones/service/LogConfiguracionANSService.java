package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.LogConfiguracionANSConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.LogConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogConfiguracionANSOid;
import com.keralty.aeconfiguraciones.repository.LogConfiguracionANSJpaRepository;
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
public class LogConfiguracionANSService extends AbstractService {

    LogConfiguracionANSJpaRepository repository;

    LogConfiguracionANSService logConfiguracionANSSrv;

    ConfiguracionANSService configuracionANSSrv;

    @Inject
    public LogConfiguracionANSService(
            LogConfiguracionANSService logConfiguracionANSSrv,
            ConfiguracionANSService configuracionANSSrv,
            LogConfiguracionANSJpaRepository repository) {
        this.logConfiguracionANSSrv = logConfiguracionANSSrv;
        this.configuracionANSSrv = configuracionANSSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, LogConfiguracionANSConstants.CLASS_NAME);
    }

    private List<LogConfiguracionANS> solveLogConfiguracionANSTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, LogConfiguracionANSConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveLogConfiguracionANSTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, LogConfiguracionANSConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class LogConfiguracionANS
     *
     * @return Population of class LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogConfiguracionANS> get() {
        List<LogConfiguracionANS> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class LogConfiguracionANS that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class LogConfiguracionANS matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogConfiguracionANS> get(QueryRequest queryRequest) {
        return solveLogConfiguracionANSTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveLogConfiguracionANSTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveLogConfiguracionANSTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveLogConfiguracionANSTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of LogConfiguracionANS by ID
     *
     * @param logConfiguracionANSId Identification field logConfiguracionANSId
     *
     * @return Instance of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogConfiguracionANS getById(Long logConfiguracionANSId) {
        return getByOID(new LogConfiguracionANSOid(logConfiguracionANSId));
    }
    
    /*
     * Returns an instance of LogConfiguracionANS by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public LogConfiguracionANS getByOID(LogConfiguracionANSOid oid) {
        Optional<LogConfiguracionANS> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of LogConfiguracionANS by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public LogConfiguracionANS getByOIDWithHV(LogConfiguracionANSOid oid) {
        Optional<LogConfiguracionANS> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<LogConfiguracionANS> getInstanceByOid(LogConfiguracionANSOid oid) {
        return oid != null && oid.getLogConfiguracionANSId() != null
               ? repository.findById(oid.getLogConfiguracionANSId())
               : Optional.empty();
    }

    /*
     * Returns an instance of LogConfiguracionANS by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of LogConfiguracionANS
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public LogConfiguracionANS getByJsonOid(String jsonOid) {
        LogConfiguracionANSOid oid = new LogConfiguracionANSOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of LogConfiguracionANS related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogConfiguracionANS> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveLogConfiguracionANSTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of LogConfiguracionANS related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<LogConfiguracionANS> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of LogConfiguracionANS
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of LogConfiguracionANS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getLogConfiguracionANSDynamic(LogConfiguracionANSOid oid, String displaySetItems) {
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
    public String getSuppInfo(LogConfiguracionANSOid oid, String dsName) {
        LogConfiguracionANS instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(LogConfiguracionANS instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods


    /*
     * Solves the query needed for PIULogConfiguracionANS
     *
     * @param queryRequest Query request
     * @return List of viewmodels for LogConfiguracionANS using displayset DSLogConfiguracionANS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.ds.DSLogConfiguracionANS> piuPIULogConfiguracionANS(QueryRequest queryRequest) {
        return solveLogConfiguracionANSTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.ds.DSLogConfiguracionANS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIULogConfiguracionANS
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIULogConfiguracionANS
     */
    public int getCount4PIULogConfiguracionANS(QueryRequest queryRequest) {
        return solveLogConfiguracionANSTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logConfiguracionANSSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest request) throws ModelException {
        request.setLogConfiguracionANScrearpagrConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getLogConfiguracionANScrearpagrConfiguracionANS()));
        return logConfiguracionANSSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionANS logConfiguracionANScrearpagrConfiguracionANS = request.getLogConfiguracionANScrearpagrConfiguracionANSInstance();
        String logConfiguracionANScrearpatrLoginFuncionario = request.getLogConfiguracionANScrearpatrLoginFuncionario();
        Long logConfiguracionANScrearpatrANSAfiliaciones = request.getLogConfiguracionANScrearpatrANSAfiliaciones();
        Long logConfiguracionANScrearpatrANSAreaMedica = request.getLogConfiguracionANScrearpatrANSAreaMedica();
        Boolean logConfiguracionANScrearpatrHabilLunes = request.getLogConfiguracionANScrearpatrHabilLunes();
        Boolean logConfiguracionANScrearpatrHabilMartes = request.getLogConfiguracionANScrearpatrHabilMartes();
        Boolean logConfiguracionANScrearpatrHabilMiercoles = request.getLogConfiguracionANScrearpatrHabilMiercoles();
        Boolean logConfiguracionANScrearpatrHabilJueves = request.getLogConfiguracionANScrearpatrHabilJueves();
        Boolean logConfiguracionANScrearpatrHabilViernes = request.getLogConfiguracionANScrearpatrHabilViernes();
        Boolean logConfiguracionANScrearpatrHabilSabado = request.getLogConfiguracionANScrearpatrHabilSabado();
        Boolean logConfiguracionANScrearpatrHabilDomingo = request.getLogConfiguracionANScrearpatrHabilDomingo();

        request.checkArguments();
        LogConfiguracionANS instance = new LogConfiguracionANS();
        instance.add2ConfiguracionANS(logConfiguracionANScrearpagrConfiguracionANS);

        instance.setLogConfiguracionANSId(Long.valueOf(0));
        instance.setLogConfiguracionANSFecha(STDFunctions.systemDateTimeFun());
        instance.setLogConfiguracionANSLoginFuncionario(logConfiguracionANScrearpatrLoginFuncionario);
        instance.setLogConfiguracionANSANSAfiliaciones(logConfiguracionANScrearpatrANSAfiliaciones);
        instance.setLogConfiguracionANSANSAreaMedica(logConfiguracionANScrearpatrANSAreaMedica);
        instance.setLogConfiguracionANSHabilLunes(logConfiguracionANScrearpatrHabilLunes);
        instance.setLogConfiguracionANSHabilMartes(logConfiguracionANScrearpatrHabilMartes);
        instance.setLogConfiguracionANSHabilMiercoles(logConfiguracionANScrearpatrHabilMiercoles);
        instance.setLogConfiguracionANSHabilJueves(logConfiguracionANScrearpatrHabilJueves);
        instance.setLogConfiguracionANSHabilViernes(logConfiguracionANScrearpatrHabilViernes);
        instance.setLogConfiguracionANSHabilSabado(logConfiguracionANScrearpatrHabilSabado);
        instance.setLogConfiguracionANSHabilDomingo(logConfiguracionANScrearpatrHabilDomingo);

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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logConfiguracionANSSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.EliminarRequest request) throws ModelException {
        request.setLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance(logConfiguracionANSSrv.getByOIDWithHV(request.getLogConfiguracionANSeliminarpthisLogConfiguracionANS()));
        return logConfiguracionANSSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        LogConfiguracionANS logConfiguracionANSeliminarpthisLogConfiguracionANS = request.getLogConfiguracionANSeliminarpthisLogConfiguracionANSInstance();

        request.checkArguments();
        LogConfiguracionANS instance = logConfiguracionANSeliminarpthisLogConfiguracionANS;


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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = logConfiguracionANSSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest request) throws ModelException {
        request.setLogConfiguracionANSTCREARptpagrConfiguracionANSInstance(configuracionANSSrv.getByOIDWithHV(request.getLogConfiguracionANSTCREARptpagrConfiguracionANS()));
        return logConfiguracionANSSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ConfiguracionANS logConfiguracionANSTCREARptpagrConfiguracionANS = request.getLogConfiguracionANSTCREARptpagrConfiguracionANSInstance();
        Long logConfiguracionANSTCREARptpatrANSAfiliaciones = request.getLogConfiguracionANSTCREARptpatrANSAfiliaciones();
        Long logConfiguracionANSTCREARptpatrANSAreaMedica = request.getLogConfiguracionANSTCREARptpatrANSAreaMedica();
        Boolean logConfiguracionANSTCREARptpatrHabilLunes = request.getLogConfiguracionANSTCREARptpatrHabilLunes();
        Boolean logConfiguracionANSTCREARptpatrHabilMartes = request.getLogConfiguracionANSTCREARptpatrHabilMartes();
        Boolean logConfiguracionANSTCREARptpatrHabilMiercoles = request.getLogConfiguracionANSTCREARptpatrHabilMiercoles();
        Boolean logConfiguracionANSTCREARptpatrHabilJueves = request.getLogConfiguracionANSTCREARptpatrHabilJueves();
        Boolean logConfiguracionANSTCREARptpatrHabilViernes = request.getLogConfiguracionANSTCREARptpatrHabilViernes();
        Boolean logConfiguracionANSTCREARptpatrHabilSabado = request.getLogConfiguracionANSTCREARptpatrHabilSabado();
        Boolean logConfiguracionANSTCREARptpatrHabilDomingo = request.getLogConfiguracionANSTCREARptpatrHabilDomingo();

        request.checkArguments();
        LogConfiguracionANS instance;

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest();
            step10Request.setLogConfiguracionANScrearpagrConfiguracionANSInstance(logConfiguracionANSTCREARptpagrConfiguracionANS);
            step10Request.setLogConfiguracionANScrearpatrLoginFuncionario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
            step10Request.setLogConfiguracionANScrearpatrANSAfiliaciones(logConfiguracionANSTCREARptpatrANSAfiliaciones);
            step10Request.setLogConfiguracionANScrearpatrANSAreaMedica(logConfiguracionANSTCREARptpatrANSAreaMedica);
            step10Request.setLogConfiguracionANScrearpatrHabilLunes(logConfiguracionANSTCREARptpatrHabilLunes);
            step10Request.setLogConfiguracionANScrearpatrHabilMartes(logConfiguracionANSTCREARptpatrHabilMartes);
            step10Request.setLogConfiguracionANScrearpatrHabilMiercoles(logConfiguracionANSTCREARptpatrHabilMiercoles);
            step10Request.setLogConfiguracionANScrearpatrHabilJueves(logConfiguracionANSTCREARptpatrHabilJueves);
            step10Request.setLogConfiguracionANScrearpatrHabilViernes(logConfiguracionANSTCREARptpatrHabilViernes);
            step10Request.setLogConfiguracionANScrearpatrHabilSabado(logConfiguracionANSTCREARptpatrHabilSabado);
            step10Request.setLogConfiguracionANScrearpatrHabilDomingo(logConfiguracionANSTCREARptpatrHabilDomingo);
            instance = (LogConfiguracionANS) logConfiguracionANSSrv.crearImplementation(step10Request).getInstance();
        } else {
            com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logconfiguracionans.siu.CrearRequest();
            step11Request.setLogConfiguracionANScrearpagrConfiguracionANSInstance(logConfiguracionANSTCREARptpagrConfiguracionANS);
            step11Request.setLogConfiguracionANScrearpatrLoginFuncionario("usuicafile");
            step11Request.setLogConfiguracionANScrearpatrANSAfiliaciones(logConfiguracionANSTCREARptpatrANSAfiliaciones);
            step11Request.setLogConfiguracionANScrearpatrANSAreaMedica(logConfiguracionANSTCREARptpatrANSAreaMedica);
            step11Request.setLogConfiguracionANScrearpatrHabilLunes(logConfiguracionANSTCREARptpatrHabilLunes);
            step11Request.setLogConfiguracionANScrearpatrHabilMartes(logConfiguracionANSTCREARptpatrHabilMartes);
            step11Request.setLogConfiguracionANScrearpatrHabilMiercoles(logConfiguracionANSTCREARptpatrHabilMiercoles);
            step11Request.setLogConfiguracionANScrearpatrHabilJueves(logConfiguracionANSTCREARptpatrHabilJueves);
            step11Request.setLogConfiguracionANScrearpatrHabilViernes(logConfiguracionANSTCREARptpatrHabilViernes);
            step11Request.setLogConfiguracionANScrearpatrHabilSabado(logConfiguracionANSTCREARptpatrHabilSabado);
            step11Request.setLogConfiguracionANScrearpatrHabilDomingo(logConfiguracionANSTCREARptpatrHabilDomingo);
            instance = (LogConfiguracionANS) logConfiguracionANSSrv.crearImplementation(step11Request).getInstance();
        }

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCLogConfiguracionANS(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(LogConfiguracionANSConstants.CLASS_NAME, LogConfiguracionANSConstants.ATTR_FIELD_LOGCONFIGURACIONANSID, false), true);
    }


    private void applyOCOCLogConfiguracionANS(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_LogConfiguracionANS".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(LogConfiguracionANSConstants.CLASS_NAME, LogConfiguracionANSConstants.ATTR_NAME_LOGCONFIGURACIONANSFECHA, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildLogConfiguracionANSRelatedInstance(relatedInstanceJsonOid);
        }
        if (LogConfiguracionANSConstants.ROLE_NAME_CONFIGURACIONANS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildLogConfiguracionANSRelatedInstance4ConfiguracionANS(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildLogConfiguracionANSRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new LogConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, LogConfiguracionANSOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildLogConfiguracionANSRelatedInstance4ConfiguracionANS(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, ConfiguracionANSOid.class)): ConfiguracionANS.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            LogConfiguracionANSOid oid = new LogConfiguracionANSOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(LogConfiguracionANSConstants.ATTR_FIELD_LOGCONFIGURACIONANSID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getLogConfiguracionANSId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(LogConfiguracionANSService.class).error(e.getMessage());
        }
    }

}
