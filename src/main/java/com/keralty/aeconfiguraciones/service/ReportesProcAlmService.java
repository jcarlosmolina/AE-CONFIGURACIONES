package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AdminConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.ReportesProcAlmConstants;
import com.keralty.aeconfiguraciones.global.SuperAdminConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.oid.FuncionarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesProcAlmOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesProcAlm;
import com.keralty.aeconfiguraciones.repository.ReportesProcAlmJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ReportesProcAlmService extends AbstractService {

    ReportesProcAlmJpaRepository repository;

    ReportesProcAlmService reportesProcAlmSrv;

    ParamReporteService paramReporteSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public ReportesProcAlmService(
            ReportesProcAlmService reportesProcAlmSrv,
            ParamReporteService paramReporteSrv,
            UserFunctions userFunctionsSrv,
            ReportesProcAlmJpaRepository repository) {
        this.reportesProcAlmSrv = reportesProcAlmSrv;
        this.paramReporteSrv = paramReporteSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ReportesProcAlmConstants.CLASS_NAME);
    }

    private List<ReportesProcAlm> solveReportesProcAlmTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ReportesProcAlmConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveReportesProcAlmTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ReportesProcAlmConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class ReportesProcAlm
     *
     * @return Population of class ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ReportesProcAlm> get() {
        List<ReportesProcAlm> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class ReportesProcAlm
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<ReportesProcAlm> instances = get(queryRequest);
        if(ReportesProcAlmConstants.DSICREPORTESPROCALM.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICReportesProcAlm())).collect(Collectors.toList());
        }
        if(ReportesProcAlmConstants.DSDSREPORTESPROCALM.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSReportesProcAlm())).collect(Collectors.toList());
        }
        if(ReportesProcAlmConstants.DSDSMMD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSMMD())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class ReportesProcAlm that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class ReportesProcAlm matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ReportesProcAlm> get(QueryRequest queryRequest) {
        return solveReportesProcAlmTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveReportesProcAlmTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveReportesProcAlmTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveReportesProcAlmTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
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
        return new PopulationResponseViewModel(solveReportesProcAlmTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of ReportesProcAlm by ID
     *
     * @param reportesProcAlmidReporte Identification field reportesProcAlmidReporte
     *
     * @return Instance of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ReportesProcAlm getById(Long reportesProcAlmidReporte) {
        return getByOID(new ReportesProcAlmOid(reportesProcAlmidReporte));
    }
    
    /*
     * Returns an instance of ReportesProcAlm by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ReportesProcAlm getByOID(ReportesProcAlmOid oid) {
        Optional<ReportesProcAlm> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of ReportesProcAlm by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public ReportesProcAlm getByOIDWithHV(ReportesProcAlmOid oid) {
        Optional<ReportesProcAlm> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<ReportesProcAlm> getInstanceByOid(ReportesProcAlmOid oid) {
        return oid != null && oid.getReportesProcAlmidReporte() != null
               ? repository.findById(oid.getReportesProcAlmidReporte())
               : Optional.empty();
    }

    /*
     * Returns an instance of ReportesProcAlm by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of ReportesProcAlm
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public ReportesProcAlm getByJsonOid(String jsonOid) {
        ReportesProcAlmOid oid = new ReportesProcAlmOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of ReportesProcAlm related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ReportesProcAlm> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveReportesProcAlmTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of ReportesProcAlm related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<ReportesProcAlm> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of ReportesProcAlm
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of ReportesProcAlm
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getReportesProcAlmDynamic(ReportesProcAlmOid oid, String displaySetItems) {
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
    public String getSuppInfo(ReportesProcAlmOid oid, String dsName) {
        ReportesProcAlm instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(ReportesProcAlm instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (ReportesProcAlmConstants.DSICREPORTESPROCALM.equals(dsName))
            return instance.buildICReportesProcAlm();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(ReportesProcAlm instance, String dsName) {
        if (ReportesProcAlmConstants.DSICREPORTESPROCALM.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICReportesProcAlm());
        if (ReportesProcAlmConstants.DSDSREPORTESPROCALM.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSReportesProcAlm());
        if (ReportesProcAlmConstants.DSDSMMD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSMMD());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_ReportesProcAlm".equals(filterRequest.getName()))
            suppInfo4ICReportesProcAlm(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods

    /*
     * Returns a viewmodel for class ReportesProcAlm with displayset DSMMD for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class ReportesProcAlm with displayset DSMMD
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.ds.DSMMD iIUReportesProcAlm(ReportesProcAlmOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.ds.DSMMD(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUReportesProcAlm
     *
     * @param queryRequest Query request
     * @return List of viewmodels for ReportesProcAlm using displayset DSReportesProcAlm that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.ds.DSReportesProcAlm> piuPIUReportesProcAlm(QueryRequest queryRequest) {
        return solveReportesProcAlmTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.ds.DSReportesProcAlm::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUReportesProcAlm
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUReportesProcAlm
     */
    public int getCount4PIUReportesProcAlm(QueryRequest queryRequest) {
        return solveReportesProcAlmTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.CrearRequest request) throws ModelException {
        return reportesProcAlmSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String reportesProcAlmcrearpatrDescripcion = request.getReportesProcAlmcrearpatrDescripcion();
        String reportesProcAlmcrearpatrNombreProc = request.getReportesProcAlmcrearpatrNombreProc();
        String reportesProcAlmcrearpatrNombreFichero = request.getReportesProcAlmcrearpatrNombreFichero();

        request.checkArguments();
        ReportesProcAlm instance = new ReportesProcAlm();
        instance.setReportesProcAlmidReporte(Long.valueOf(0));
        instance.setReportesProcAlmDescripcion(reportesProcAlmcrearpatrDescripcion);
        instance.setReportesProcAlmNombreProc(reportesProcAlmcrearpatrNombreProc);
        instance.setReportesProcAlmNombreFichero(reportesProcAlmcrearpatrNombreFichero);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EliminarRequest request) throws ModelException {
        request.setReportesProcAlmeliminarpthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmeliminarpthisReportesProcAlm()));
        return reportesProcAlmSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm reportesProcAlmeliminarpthisReportesProcAlm = request.getReportesProcAlmeliminarpthisReportesProcAlmInstance();

        request.checkArguments();
        ReportesProcAlm instance = reportesProcAlmeliminarpthisReportesProcAlm;
        if(reportesProcAlmeliminarpthisReportesProcAlm.getParametros().size() > 0) {
            throw new MinCardinalityException(ReportesProcAlmConstants.CLASS_ALIAS, ReportesProcAlmConstants.ROLE_ALIAS_PARAMETROS);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        return response;
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.ModificarRequest request) throws ModelException {
        request.setReportesProcAlmmodificarpthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmmodificarpthisReportesProcAlm()));
        return reportesProcAlmSrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm reportesProcAlmmodificarpthisReportesProcAlm = request.getReportesProcAlmmodificarpthisReportesProcAlmInstance();
        String reportesProcAlmmodificarpDescripcion = request.getReportesProcAlmmodificarpDescripcion();
        String reportesProcAlmmodificarpNombreProc = request.getReportesProcAlmmodificarpNombreProc();
        String reportesProcAlmmodificarpNombreFichero = request.getReportesProcAlmmodificarpNombreFichero();

        request.checkArguments();
        ReportesProcAlm instance = reportesProcAlmmodificarpthisReportesProcAlm;
        instance.setReportesProcAlmDescripcion(reportesProcAlmmodificarpDescripcion);
        instance.setReportesProcAlmNombreProc(reportesProcAlmmodificarpNombreProc);
        instance.setReportesProcAlmNombreFichero(reportesProcAlmmodificarpNombreFichero);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        return response;
    }

    // Service: evGenerar

    /*
     * Entry point for service evGenerar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse evGenerar(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarRequest request) {
        com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse response = new com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse();
        try {
            response = reportesProcAlmSrv.evGenerarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service evGenerar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse evGenerarTxn(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarRequest request) throws ModelException {
        request.setReportesProcAlmevGenerarpthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmevGenerarpthisReportesProcAlm()));
        return reportesProcAlmSrv.evGenerarImplementation(request);
    }

    /*
     * Implements service evGenerar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse evGenerarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarRequest request) throws ModelException {
        com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse response = new com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarResponse();
        String reportesProcAlmevGenerarpValorArgumentos = request.getReportesProcAlmevGenerarpValorArgumentos();
        ReportesProcAlm reportesProcAlmevGenerarpthisReportesProcAlm = request.getReportesProcAlmevGenerarpthisReportesProcAlmInstance();

        request.checkArguments();

        evGenerarPreconditions(request);
        ReportesProcAlm instance = reportesProcAlmevGenerarpthisReportesProcAlm;


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        // Value expressions for outgoing argument 'reportesProcAlmevGeneraroaFichero'
        // DEFAULT  : fuGenerarReporteOracle(NombreProc, AGENTFuncionario.UsuarioLDAP, pValorArgumentos)
        response.setReportesProcAlmevGeneraroaFichero(userFunctionsSrv.fuGenerarReporteOracleFun(reportesProcAlmevGenerarpthisReportesProcAlm.getReportesProcAlmNombreProc(), ((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP(), reportesProcAlmevGenerarpValorArgumentos));


        // Value expressions for outgoing argument 'reportesProcAlmevGeneraroaNombreFichero'
        // DEFAULT  : NombreFichero
        response.setReportesProcAlmevGeneraroaNombreFichero(reportesProcAlmevGenerarpthisReportesProcAlm.getReportesProcAlmNombreFichero());


        return response;
    }

    private void evGenerarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarRequest request) 
            throws ModelException {
        checkClas1547029184512775Pre3(request);
    }

    private void checkClas1547029184512775Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.EvGenerarRequest request) 
            throws ModelException {
        // COUNT( Funcionarios ) WHERE (Funcionarios = AGENTFuncionario) > 0

        ReportesProcAlm reportesProcAlmevGenerarpthisReportesProcAlm = request.getReportesProcAlmevGenerarpthisReportesProcAlmInstance();
        try {
            boolean preconditionHolds = repository.assocOperator001(reportesProcAlmevGenerarpthisReportesProcAlm) > Long.valueOf(0);
            if (!preconditionHolds) throw new PreconditionException("No tiene permisos para ejecutar este procedimiento. Contecte con el administrador del sistema");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    // Service: InsReporteFuncionario

    /*
     * Entry point for service InsReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insReporteFuncionario(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.InsReporteFuncionarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.insReporteFuncionarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insReporteFuncionarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.InsReporteFuncionarioRequest request) throws ModelException {
        request.setReportesProcAlmInsReporteFuncionariopthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmInsReporteFuncionariopthisReportesProcAlm()));
        request.setReportesProcAlmInsReporteFuncionariopevcFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getReportesProcAlmInsReporteFuncionariopevcFuncionario()));
        return reportesProcAlmSrv.insReporteFuncionarioImplementation(request, true);
    }

    /*
     * Implements service InsReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insReporteFuncionarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.InsReporteFuncionarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm reportesProcAlmInsReporteFuncionariopthisReportesProcAlm = request.getReportesProcAlmInsReporteFuncionariopthisReportesProcAlmInstance();
        Funcionario reportesProcAlmInsReporteFuncionariopevcFuncionario = request.getReportesProcAlmInsReporteFuncionariopevcFuncionarioInstance();

        request.checkArguments();
        ReportesProcAlm instance = reportesProcAlmInsReporteFuncionariopthisReportesProcAlm;

        instance.add2Funcionarios(reportesProcAlmInsReporteFuncionariopevcFuncionario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.InsReporteFuncionarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.InsReporteFuncionarioRequest();
            relatedRequest.setFuncionarioInsReporteFuncionariopthisFuncionarioInstance(reportesProcAlmInsReporteFuncionariopevcFuncionario);
            relatedRequest.setFuncionarioInsReporteFuncionariopevcReportesProcAlmInstance(reportesProcAlmInsReporteFuncionariopthisReportesProcAlm);
            funcionarioSrv.insReporteFuncionarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        return response;
    }

    // Service: DelReporteFuncionario

    /*
     * Entry point for service DelReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delReporteFuncionario(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.DelReporteFuncionarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.delReporteFuncionarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delReporteFuncionarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.DelReporteFuncionarioRequest request) throws ModelException {
        request.setReportesProcAlmDelReporteFuncionariopthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmDelReporteFuncionariopthisReportesProcAlm()));
        request.setReportesProcAlmDelReporteFuncionariopevcFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getReportesProcAlmDelReporteFuncionariopevcFuncionario()));
        return reportesProcAlmSrv.delReporteFuncionarioImplementation(request, true);
    }

    /*
     * Implements service DelReporteFuncionario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delReporteFuncionarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.DelReporteFuncionarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ReportesProcAlm reportesProcAlmDelReporteFuncionariopthisReportesProcAlm = request.getReportesProcAlmDelReporteFuncionariopthisReportesProcAlmInstance();
        Funcionario reportesProcAlmDelReporteFuncionariopevcFuncionario = request.getReportesProcAlmDelReporteFuncionariopevcFuncionarioInstance();

        request.checkArguments();
        ReportesProcAlm instance = reportesProcAlmDelReporteFuncionariopthisReportesProcAlm;

        instance.removeFromFuncionarios(reportesProcAlmDelReporteFuncionariopevcFuncionario);
        reportesProcAlmDelReporteFuncionariopevcFuncionario.removeFromReportes(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.DelReporteFuncionarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.DelReporteFuncionarioRequest();
            relatedRequest.setFuncionarioDelReporteFuncionariopthisFuncionarioInstance(reportesProcAlmDelReporteFuncionariopevcFuncionario);
            relatedRequest.setFuncionarioDelReporteFuncionariopevcReportesProcAlmInstance(reportesProcAlmDelReporteFuncionariopthisReportesProcAlm);
            funcionarioSrv.delReporteFuncionarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        return response;
    }

    // Service: TCAMBIARORDENPARAMS

    /*
     * Entry point for service TCAMBIARORDENPARAMS
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCAMBIARORDENPARAMS(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = reportesProcAlmSrv.tCAMBIARORDENPARAMSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCAMBIARORDENPARAMS
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCAMBIARORDENPARAMSTxn(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest request) throws ModelException, SystemException {
        request.setReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance(reportesProcAlmSrv.getByOIDWithHV(request.getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm()));
        return reportesProcAlmSrv.tCAMBIARORDENPARAMSImplementation(request);
    }

    /*
     * Implements service TCAMBIARORDENPARAMS
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCAMBIARORDENPARAMSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.reportesprocalm.siu.TCAMBIARORDENPARAMSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual = request.getReportesProcAlmTCAMBIARORDENPARAMSptOrdenActual();
        Long reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden = request.getReportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden();
        ReportesProcAlm reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm = request.getReportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlmInstance();

        request.checkArguments();
        ReportesProcAlm instance = reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm;

        for(ParamReporte step1Entity : repository.assocOperator002(reportesProcAlmTCAMBIARORDENPARAMSpthisReportesProcAlm, reportesProcAlmTCAMBIARORDENPARAMSptOrdenActual)) {
            com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.CambiarOrdenRequest();
            step1Request.setParamReportecambiarOrdenpthisParamReporteInstance(step1Entity);
            step1Request.setParamReportecambiarOrdennuevoOrden(reportesProcAlmTCAMBIARORDENPARAMSptNuevoOrden);
            paramReporteSrv.cambiarOrdenImplementation(step1Request);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICReportesProcAlm());

        return response;
    }


    @Override
    protected void applyHVFilter(SQLSelect sql) throws SystemException {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SQLWhereDisjunction disjunction = new SQLWhereDisjunction();
        if (getAgentRoles().contains(FuncionarioConstants.CLASS_NAME)) applyHVFilter4Funcionario(sql, disjunction);
        conjunction.addDisjunction(disjunction);
        sql.addConjunction(conjunction);
    }

    private void applyHVFilter4Funcionario(SQLSelect sql, SQLWhereDisjunction disjunction) throws SystemException{
        if (getAgentRoles().contains(AdminConstants.CLASS_NAME)) return;
        if (getAgentRoles().contains(SuperAdminConstants.CLASS_NAME)) return;
        // COUNT (Funcionarios) WHERE (Funcionarios = agent) > 0
        Funcionario agentInstance = getAgentFuncionario();
        // '>' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator(">");
        
        // Operand 1
            // 'count' Association operator
            SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(sql));
        comparison1.setFirstOperand(operandSubQueryAssocOperator000);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(0), Constants.Type.NAT.getTypeName()));
        disjunction.addOperand(comparison1);

    }


    private void suppInfo4ICReportesProcAlm(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ReportesProcAlmConstants.ATTR_NAME_REPORTESPROCALMDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ReportesProcAlmConstants.CLASS_NAME, ReportesProcAlmConstants.ATTR_FIELD_REPORTESPROCALMIDREPORTE, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildReportesProcAlmRelatedInstance(relatedInstanceJsonOid);
        }
        if (ReportesProcAlmConstants.ROLE_NAME_PARAMETROS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildReportesProcAlmRelatedInstance4ParamReporte(relatedInstanceJsonOid, remainingPath);
        }
        if (ReportesProcAlmConstants.ROLE_NAME_FUNCIONARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildReportesProcAlmRelatedInstance4Funcionario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildReportesProcAlmRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new ReportesProcAlm(mapper.readValue(relatedInstanceJsonOid, ReportesProcAlmOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildReportesProcAlmRelatedInstance4ParamReporte(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ParamReporte(mapper.readValue(relatedInstanceJsonOid, ParamReporteOid.class)): ParamReporte.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildReportesProcAlmRelatedInstance4Funcionario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Funcionario(mapper.readValue(relatedInstanceJsonOid, FuncionarioOid.class)): Funcionario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ReportesProcAlmOid oid = new ReportesProcAlmOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ReportesProcAlmConstants.ATTR_FIELD_REPORTESPROCALMIDREPORTE, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getReportesProcAlmidReporte(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(ReportesProcAlmService.class).error(e.getMessage());
        }
    }

}
